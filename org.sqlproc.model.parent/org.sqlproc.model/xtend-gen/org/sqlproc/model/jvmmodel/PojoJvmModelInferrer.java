package org.sqlproc.model.jvmmodel;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.common.types.JvmAnnotationReference;
import org.eclipse.xtext.common.types.JvmAnnotationTarget;
import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmEnumerationLiteral;
import org.eclipse.xtext.common.types.JvmEnumerationType;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.JvmAnnotationReferenceBuilder;
import org.eclipse.xtext.xbase.jvmmodel.JvmModelAssociator;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypeReferenceBuilder;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.sqlproc.model.jvmmodel.ProcessorGeneratorUtils;
import org.sqlproc.model.jvmmodel.ProcessorTypesBuilder;
import org.sqlproc.model.processorModel.AnnotatedFeature;
import org.sqlproc.model.processorModel.Annotation;
import org.sqlproc.model.processorModel.Extends;
import org.sqlproc.model.processorModel.Feature;
import org.sqlproc.model.processorModel.Implements;
import org.sqlproc.model.processorModel.PojoAttribute;
import org.sqlproc.model.processorModel.PojoEntity;
import org.sqlproc.model.processorModel.PojoProcedure;

/**
 * <p>Infers a JVM model from the source model.</p>
 * 
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
@SuppressWarnings("all")
public class PojoJvmModelInferrer {
  @Extension
  public JvmAnnotationReferenceBuilder _annotationTypesBuilder;
  
  @Extension
  public JvmTypeReferenceBuilder _typeReferenceBuilder;
  
  /**
   * convenience API to build and initialize JVM types and their members.
   */
  @Inject
  @Extension
  private ProcessorTypesBuilder _processorTypesBuilder;
  
  @Inject
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider;
  
  @Inject
  @Extension
  private ProcessorGeneratorUtils _processorGeneratorUtils;
  
  private final String SERIALIZABLE = "java.io.Serializable";
  
  private final String HASH_MAP = "java.util.HashMap";
  
  private final String HASH_SET = "java.util.HashSet";
  
  private final String POJO = "org.sqlproc.engine.annotation.Pojo";
  
  /**
   * The dispatch method {@code infer} is called for each instance of the
   * given element's type that is contained in a resource.
   * 
   * @param element
   *            the model to create one or more
   *            {@link org.eclipse.xtext.common.types.JvmDeclaredType declared
   *            types} from.
   * @param acceptor
   *            each created
   *            {@link org.eclipse.xtext.common.types.JvmDeclaredType type}
   *            without a container should be passed to the acceptor in order
   *            get attached to the current resource. The acceptor's
   *            {@link IJvmDeclaredTypeAcceptor#accept(org.eclipse.xtext.common.types.JvmDeclaredType)
   *            accept(..)} method takes the constructed empty type for the
   *            pre-indexing phase. This one is further initialized in the
   *            indexing phase using the closure you pass to the returned
   *            {@link org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor.IPostIndexingInitializing#initializeLater(org.eclipse.xtext.xbase.lib.Procedures.Procedure1)
   *            initializeLater(..)}.
   * @param isPreIndexingPhase
   *            whether the method is called in a pre-indexing phase, i.e.
   *            when the global index is not yet fully updated. You must not
   *            rely on linking using the index if isPreIndexingPhase is
   *            <code>true</code>.
   */
  public void inferPojo(final PojoEntity entity, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(entity);
    final JvmGenericType entityType = this._processorTypesBuilder.toClass(entity, _fullyQualifiedName);
    EList<JvmAnnotationReference> _annotations = entityType.getAnnotations();
    JvmAnnotationReference _annotationRef = this._annotationTypesBuilder.annotationRef(this.POJO);
    _annotations.add(_annotationRef);
    final String simpleName = entity.getName();
    final Integer sernum = this._processorGeneratorUtils.getSernum(entity);
    final Procedure1<JvmGenericType> _function = (JvmGenericType it) -> {
      String _documentation = this._processorTypesBuilder.getDocumentation(entity);
      this._processorTypesBuilder.setDocumentation(it, _documentation);
      boolean _isAbstract = entity.isAbstract();
      if (_isAbstract) {
        it.setAbstract(true);
      }
      List<Annotation> _standardAnnotations = this._processorGeneratorUtils.standardAnnotations(entity);
      final Function1<Annotation, XAnnotation> _function_1 = (Annotation a) -> {
        return a.getAnnotation();
      };
      List<XAnnotation> _map = ListExtensions.<Annotation, XAnnotation>map(_standardAnnotations, _function_1);
      for (final XAnnotation an : _map) {
        JvmType _annotationType = an.getAnnotationType();
        String _identifier = _annotationType.getIdentifier();
        boolean _equals = Objects.equal(_identifier, this.SERIALIZABLE);
        if (_equals) {
          EList<JvmTypeReference> _superTypes = it.getSuperTypes();
          JvmType _annotationType_1 = an.getAnnotationType();
          JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_annotationType_1);
          this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _typeRef);
        } else {
          this.addAnnotationX(it, an);
        }
      }
      List<Implements> _implements = this._processorGeneratorUtils.getImplements(entity);
      for (final Implements impl : _implements) {
        EList<JvmTypeReference> _superTypes_1 = it.getSuperTypes();
        JvmParameterizedTypeReference _implements_1 = impl.getImplements();
        JvmTypeReference _cloneWithProxies = this._processorTypesBuilder.cloneWithProxies(_implements_1);
        this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes_1, _cloneWithProxies);
      }
      final Extends ext = this._processorGeneratorUtils.getExtends(entity);
      JvmParameterizedTypeReference _superType = entity.getSuperType();
      boolean _notEquals = (!Objects.equal(_superType, null));
      if (_notEquals) {
        EList<JvmTypeReference> _superTypes_2 = it.getSuperTypes();
        JvmParameterizedTypeReference _superType_1 = entity.getSuperType();
        JvmTypeReference _cloneWithProxies_1 = this._processorTypesBuilder.cloneWithProxies(_superType_1);
        this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes_2, _cloneWithProxies_1);
      } else {
        boolean _notEquals_1 = (!Objects.equal(ext, null));
        if (_notEquals_1) {
          EList<JvmTypeReference> _superTypes_3 = it.getSuperTypes();
          JvmParameterizedTypeReference _extends = ext.getExtends();
          JvmTypeReference _cloneWithProxies_2 = this._processorTypesBuilder.cloneWithProxies(_extends);
          this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes_3, _cloneWithProxies_2);
        }
      }
      boolean _notEquals_2 = (!Objects.equal(sernum, null));
      if (_notEquals_2) {
        EList<JvmTypeReference> _superTypes_4 = it.getSuperTypes();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SERIALIZABLE);
        this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes_4, _typeRef_1);
        EList<JvmMember> _members = it.getMembers();
        JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(long.class);
        final Procedure1<JvmField> _function_2 = (JvmField it_1) -> {
          it_1.setStatic(true);
          it_1.setFinal(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append(sernum, "");
              _builder.append("L");
            }
          };
          this._processorTypesBuilder.setInitializer(it_1, _client);
        };
        JvmField _field = this._processorTypesBuilder.toField(entity, "serialVersionUID", _typeRef_2, _function_2);
        this._processorTypesBuilder.<JvmField>operator_add(_members, _field);
      }
      EList<AnnotatedFeature> _features = entity.getFeatures();
      final Function1<AnnotatedFeature, Feature> _function_3 = (AnnotatedFeature it_1) -> {
        return it_1.getFeature();
      };
      List<Feature> _map_1 = ListExtensions.<AnnotatedFeature, Feature>map(_features, _function_3);
      Iterable<PojoAttribute> _filter = Iterables.<PojoAttribute>filter(_map_1, PojoAttribute.class);
      final Function1<PojoAttribute, Boolean> _function_4 = (PojoAttribute x) -> {
        return Boolean.valueOf(this._processorGeneratorUtils.isIndex(x));
      };
      Iterable<PojoAttribute> _filter_1 = IterableExtensions.<PojoAttribute>filter(_filter, _function_4);
      for (final PojoAttribute attr : _filter_1) {
        EList<JvmMember> _members_1 = it.getMembers();
        String _constName = this._processorGeneratorUtils.constName(attr);
        String _plus = ("ORDER_BY_" + _constName);
        JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(String.class);
        final Procedure1<JvmField> _function_5 = (JvmField it_1) -> {
          it_1.setStatic(true);
          it_1.setFinal(true);
          it_1.setVisibility(JvmVisibility.PUBLIC);
          List<Annotation> _staticAnnotations = this._processorGeneratorUtils.staticAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_6 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_2 = ListExtensions.<Annotation, XAnnotation>map(_staticAnnotations, _function_6);
          this.addAnnotationsX(it_1, _map_2);
          StringConcatenationClient _xifexpression = null;
          String _index = this._processorGeneratorUtils.getIndex(attr);
          boolean _notEquals_3 = (!Objects.equal(_index, null));
          if (_notEquals_3) {
            StringConcatenationClient _client = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append("\"");
                String _index = PojoJvmModelInferrer.this._processorGeneratorUtils.getIndex(attr);
                _builder.append(_index, "");
                _builder.append("\"");
              }
            };
            _xifexpression = _client;
          } else {
            StringConcatenationClient _client_1 = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append("\"");
                String _constName = PojoJvmModelInferrer.this._processorGeneratorUtils.constName(attr);
                _builder.append(_constName, "");
                _builder.append("\"");
              }
            };
            _xifexpression = _client_1;
          }
          this._processorTypesBuilder.setInitializer(it_1, _xifexpression);
        };
        JvmField _field_1 = this._processorTypesBuilder.toField(entity, _plus, _typeRef_3, _function_5);
        this._processorTypesBuilder.<JvmField>operator_add(_members_1, _field_1);
      }
      Map<String, List<PojoAttribute>> _index = this._processorGeneratorUtils.getIndex(entity);
      Set<Map.Entry<String, List<PojoAttribute>>> _entrySet = _index.entrySet();
      for (final Map.Entry<String, List<PojoAttribute>> entry : _entrySet) {
        EList<JvmMember> _members_2 = it.getMembers();
        List<PojoAttribute> _value = entry.getValue();
        String _constName_1 = this._processorGeneratorUtils.constName(_value);
        String _plus_1 = ("ORDER_BY_" + _constName_1);
        JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(String.class);
        final Procedure1<JvmField> _function_6 = (JvmField it_1) -> {
          it_1.setStatic(true);
          it_1.setFinal(true);
          it_1.setVisibility(JvmVisibility.PUBLIC);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("\"");
              String _key = entry.getKey();
              _builder.append(_key, "");
              _builder.append("\"");
            }
          };
          this._processorTypesBuilder.setInitializer(it_1, _client);
          List<Annotation> _staticAnnotations = this._processorGeneratorUtils.staticAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_7 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_2 = ListExtensions.<Annotation, XAnnotation>map(_staticAnnotations, _function_7);
          this.addAnnotationsX(it_1, _map_2);
        };
        JvmField _field_2 = this._processorTypesBuilder.toField(entity, _plus_1, _typeRef_4, _function_6);
        this._processorTypesBuilder.<JvmField>operator_add(_members_2, _field_2);
      }
      EList<AnnotatedFeature> _features_1 = entity.getFeatures();
      final Function1<AnnotatedFeature, Feature> _function_7 = (AnnotatedFeature it_1) -> {
        return it_1.getFeature();
      };
      List<Feature> _map_2 = ListExtensions.<AnnotatedFeature, Feature>map(_features_1, _function_7);
      Iterable<PojoAttribute> _filter_2 = Iterables.<PojoAttribute>filter(_map_2, PojoAttribute.class);
      final Function1<PojoAttribute, Boolean> _function_8 = (PojoAttribute x) -> {
        return Boolean.valueOf(this._processorGeneratorUtils.isEnumIndex(x));
      };
      final Iterable<PojoAttribute> enumAttrIndexes = IterableExtensions.<PojoAttribute>filter(_filter_2, _function_8);
      boolean _isEmpty = IterableExtensions.isEmpty(enumAttrIndexes);
      boolean _not = (!_isEmpty);
      if (_not) {
        final Procedure1<JvmEnumerationType> _function_9 = (JvmEnumerationType it_1) -> {
          it_1.setStatic(true);
          it_1.setVisibility(JvmVisibility.PUBLIC);
        };
        final JvmEnumerationType orderType = this._processorTypesBuilder.toEnumerationType(entity, "Order", _function_9);
        EList<JvmMember> _members_3 = it.getMembers();
        this._processorTypesBuilder.<JvmEnumerationType>operator_add(_members_3, orderType);
        for (final PojoAttribute attr_1 : enumAttrIndexes) {
          EList<JvmMember> _members_4 = orderType.getMembers();
          String _constName_2 = this._processorGeneratorUtils.constName(attr_1);
          JvmEnumerationLiteral _enumerationLiteral = this._processorTypesBuilder.toEnumerationLiteral(entity, _constName_2);
          this._processorTypesBuilder.<JvmEnumerationLiteral>operator_add(_members_4, _enumerationLiteral);
        }
      }
      Map<String, List<PojoAttribute>> _enumIndex = this._processorGeneratorUtils.getEnumIndex(entity);
      final Set<Map.Entry<String, List<PojoAttribute>>> enumIndexes = _enumIndex.entrySet();
      boolean _isEmpty_1 = enumIndexes.isEmpty();
      boolean _not_1 = (!_isEmpty_1);
      if (_not_1) {
        final Procedure1<JvmEnumerationType> _function_10 = (JvmEnumerationType it_1) -> {
          it_1.setStatic(true);
          it_1.setVisibility(JvmVisibility.PUBLIC);
        };
        final JvmEnumerationType orderType_1 = this._processorTypesBuilder.toEnumerationType(entity, "Order", _function_10);
        EList<JvmMember> _members_5 = it.getMembers();
        this._processorTypesBuilder.<JvmEnumerationType>operator_add(_members_5, orderType_1);
        for (final Map.Entry<String, List<PojoAttribute>> entry_1 : enumIndexes) {
          EList<JvmMember> _members_6 = orderType_1.getMembers();
          List<PojoAttribute> _value_1 = entry_1.getValue();
          String _constName_3 = this._processorGeneratorUtils.constName(_value_1);
          JvmEnumerationLiteral _enumerationLiteral_1 = this._processorTypesBuilder.toEnumerationLiteral(entity, _constName_3);
          this._processorTypesBuilder.<JvmEnumerationLiteral>operator_add(_members_6, _enumerationLiteral_1);
        }
      }
      EList<JvmMember> _members_7 = it.getMembers();
      final Procedure1<JvmConstructor> _function_11 = (JvmConstructor it_1) -> {
        List<Annotation> _constructorAnnotations = this._processorGeneratorUtils.constructorAnnotations(entity);
        final Function1<Annotation, XAnnotation> _function_12 = (Annotation a) -> {
          return a.getAnnotation();
        };
        List<XAnnotation> _map_3 = ListExtensions.<Annotation, XAnnotation>map(_constructorAnnotations, _function_12);
        this.addAnnotationsX(it_1, _map_3);
      };
      JvmConstructor _constructor = this._processorTypesBuilder.toConstructor(entity, _function_11);
      this._processorTypesBuilder.<JvmConstructor>operator_add(_members_7, _constructor);
      final List<PojoAttribute> allRequiredAttributes = this._processorGeneratorUtils.allRequiredAttributes(entity);
      boolean _isEmpty_2 = allRequiredAttributes.isEmpty();
      boolean _not_2 = (!_isEmpty_2);
      if (_not_2) {
        EList<JvmMember> _members_8 = it.getMembers();
        final Procedure1<JvmConstructor> _function_12 = (JvmConstructor it_1) -> {
          for (final PojoAttribute attr_2 : allRequiredAttributes) {
            {
              JvmTypeReference _xifexpression = null;
              boolean _isArray = attr_2.isArray();
              if (_isArray) {
                JvmParameterizedTypeReference _type = attr_2.getType();
                JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_type);
                _xifexpression = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
              } else {
                _xifexpression = attr_2.getType();
              }
              final JvmTypeReference type = _xifexpression;
              EList<JvmFormalParameter> _parameters = it_1.getParameters();
              String _name = attr_2.getName();
              JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _name, type);
              this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
            }
          }
          List<Annotation> _constructorAnnotations = this._processorGeneratorUtils.constructorAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_13 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_3 = ListExtensions.<Annotation, XAnnotation>map(_constructorAnnotations, _function_13);
          this.addAnnotationsX(it_1, _map_3);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("super(");
              {
                List<PojoAttribute> _parentRequiredAttributes = PojoJvmModelInferrer.this._processorGeneratorUtils.parentRequiredAttributes(entity);
                boolean _hasElements = false;
                for(final PojoAttribute attr : _parentRequiredAttributes) {
                  if (!_hasElements) {
                    _hasElements = true;
                  } else {
                    _builder.appendImmediate(",", "");
                  }
                  String _name = attr.getName();
                  _builder.append(_name, "");
                }
              }
              _builder.append(");");
              _builder.newLineIfNotEmpty();
              {
                List<PojoAttribute> _requiredAttributes = PojoJvmModelInferrer.this._processorGeneratorUtils.requiredAttributes(entity);
                for(final PojoAttribute attr_1 : _requiredAttributes) {
                  _builder.append("set");
                  String _name_1 = attr_1.getName();
                  String __toFirstUpper = PojoJvmModelInferrer.this._toFirstUpper(_name_1);
                  _builder.append(__toFirstUpper, "");
                  _builder.append("(");
                  String _name_2 = attr_1.getName();
                  _builder.append(_name_2, "");
                  _builder.append(");");
                  _builder.newLineIfNotEmpty();
                }
              }
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmConstructor _constructor_1 = this._processorTypesBuilder.toConstructor(entity, _function_12);
        this._processorTypesBuilder.<JvmConstructor>operator_add(_members_8, _constructor_1);
      }
      EList<AnnotatedFeature> _features_2 = entity.getFeatures();
      final Function1<AnnotatedFeature, Feature> _function_13 = (AnnotatedFeature it_1) -> {
        return it_1.getFeature();
      };
      List<Feature> _map_3 = ListExtensions.<AnnotatedFeature, Feature>map(_features_2, _function_13);
      Iterable<PojoProcedure> _filter_3 = Iterables.<PojoProcedure>filter(_map_3, PojoProcedure.class);
      final Function1<PojoProcedure, String> _function_14 = (PojoProcedure it_1) -> {
        return it_1.getName();
      };
      Iterable<String> _map_4 = IterableExtensions.<PojoProcedure, String>map(_filter_3, _function_14);
      Set<String> procNames = IterableExtensions.<String>toSet(_map_4);
      boolean _hasIds = false;
      EList<AnnotatedFeature> _features_3 = entity.getFeatures();
      final Function1<AnnotatedFeature, Feature> _function_15 = (AnnotatedFeature it_1) -> {
        return it_1.getFeature();
      };
      List<Feature> _map_5 = ListExtensions.<AnnotatedFeature, Feature>map(_features_3, _function_15);
      Iterable<PojoAttribute> _filter_4 = Iterables.<PojoAttribute>filter(_map_5, PojoAttribute.class);
      for (final PojoAttribute attr_2 : _filter_4) {
        {
          String _name = attr_2.getName();
          boolean _equals_1 = Objects.equal(_name, "ids_");
          if (_equals_1) {
            _hasIds = true;
          }
          JvmTypeReference _elvis = null;
          JvmTypeReference _elvis_1 = null;
          JvmParameterizedTypeReference _type = attr_2.getType();
          if (_type != null) {
            _elvis_1 = _type;
          } else {
            XExpression _initExpr = attr_2.getInitExpr();
            JvmTypeReference _inferredType = null;
            if (_initExpr!=null) {
              _inferredType=this._processorTypesBuilder.inferredType(_initExpr);
            }
            _elvis_1 = _inferredType;
          }
          if (_elvis_1 != null) {
            _elvis = _elvis_1;
          } else {
            JvmTypeReference _typeRef_5 = this._typeReferenceBuilder.typeRef(String.class);
            _elvis = _typeRef_5;
          }
          final JvmTypeReference type0 = _elvis;
          JvmTypeReference _xifexpression = null;
          boolean _isArray = attr_2.isArray();
          if (_isArray) {
            JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(type0);
            _xifexpression = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
          } else {
            _xifexpression = type0;
          }
          final JvmTypeReference type = _xifexpression;
          EList<JvmMember> _members_9 = it.getMembers();
          String _name_1 = attr_2.getName();
          final Procedure1<JvmField> _function_16 = (JvmField it_1) -> {
            String _documentation_1 = this._processorTypesBuilder.getDocumentation(attr_2);
            this._processorTypesBuilder.setDocumentation(it_1, _documentation_1);
            List<Annotation> _attributeAnnotations = this._processorGeneratorUtils.attributeAnnotations(attr_2);
            final Function1<Annotation, XAnnotation> _function_17 = (Annotation a) -> {
              return a.getAnnotation();
            };
            List<XAnnotation> _map_6 = ListExtensions.<Annotation, XAnnotation>map(_attributeAnnotations, _function_17);
            this.addAnnotationsX(it_1, _map_6);
            boolean _isStatic = attr_2.isStatic();
            it_1.setStatic(_isStatic);
            boolean _isStatic_1 = attr_2.isStatic();
            if (_isStatic_1) {
              it_1.setVisibility(JvmVisibility.PUBLIC);
            }
            XExpression _initExpr_1 = attr_2.getInitExpr();
            boolean _notEquals_3 = (!Objects.equal(_initExpr_1, null));
            if (_notEquals_3) {
              XExpression _initExpr_2 = attr_2.getInitExpr();
              this._processorTypesBuilder.setInitializer(it_1, _initExpr_2);
            } else {
              boolean _isList = this._processorGeneratorUtils.isList(attr_2);
              if (_isList) {
                StringConcatenationClient _client = new StringConcatenationClient() {
                  @Override
                  protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                    _builder.append("new java.util.Array");
                    String _simpleName = type.getSimpleName();
                    _builder.append(_simpleName, "");
                    _builder.append("()");
                  }
                };
                this._processorTypesBuilder.setInitializer(it_1, _client);
              } else {
                boolean _isOptLock = this._processorGeneratorUtils.isOptLock(attr_2);
                if (_isOptLock) {
                  StringConcatenationClient _client_1 = new StringConcatenationClient() {
                    @Override
                    protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                      _builder.append("0");
                    }
                  };
                  this._processorTypesBuilder.setInitializer(it_1, _client_1);
                }
              }
            }
          };
          JvmField _field_3 = this._processorTypesBuilder.toField(entity, _name_1, type, _function_16);
          this._processorTypesBuilder.<JvmField>operator_add(_members_9, _field_3);
          boolean _isStatic = attr_2.isStatic();
          boolean _not_3 = (!_isStatic);
          if (_not_3) {
            String _name_2 = attr_2.getName();
            String _name_3 = attr_2.getName();
            final Procedure1<JvmOperation> _function_17 = (JvmOperation it_1) -> {
              List<Annotation> _terAnnotations = this._processorGeneratorUtils.getterAnnotations(attr_2);
              final Function1<Annotation, XAnnotation> _function_18 = (Annotation a) -> {
                return a.getAnnotation();
              };
              List<XAnnotation> _map_6 = ListExtensions.<Annotation, XAnnotation>map(_terAnnotations, _function_18);
              this.addAnnotationsX(it_1, _map_6);
            };
            final JvmOperation m1 = this._processorTypesBuilder.toGetter(attr_2, _name_2, _name_3, type, procNames, _function_17);
            boolean _notEquals_3 = (!Objects.equal(m1, null));
            if (_notEquals_3) {
              EList<JvmMember> _members_10 = it.getMembers();
              this._processorTypesBuilder.<JvmOperation>operator_add(_members_10, m1);
            }
            String _name_4 = attr_2.getName();
            String _name_5 = attr_2.getName();
            JvmTypeReference _typeRef_6 = this._typeReferenceBuilder.typeRef(entityType);
            String _updateColumn1 = this._processorGeneratorUtils.getUpdateColumn1(attr_2);
            PojoAttribute _updateColumn2 = this._processorGeneratorUtils.getUpdateColumn2(attr_2);
            PojoAttribute _createColumn1 = this._processorGeneratorUtils.getCreateColumn1(attr_2);
            String _createColumn2 = this._processorGeneratorUtils.getCreateColumn2(attr_2);
            final Procedure1<JvmOperation> _function_18 = (JvmOperation it_1) -> {
              List<Annotation> _setterAnnotations = this._processorGeneratorUtils.setterAnnotations(attr_2);
              final Function1<Annotation, XAnnotation> _function_19 = (Annotation a) -> {
                return a.getAnnotation();
              };
              List<XAnnotation> _map_6 = ListExtensions.<Annotation, XAnnotation>map(_setterAnnotations, _function_19);
              this.addAnnotationsX(it_1, _map_6);
            };
            final JvmOperation m2 = this._processorTypesBuilder.toSetterExt(attr_2, _name_4, _name_5, type, _typeRef_6, _updateColumn1, _updateColumn2, _createColumn1, _createColumn2, procNames, _function_18);
            boolean _notEquals_4 = (!Objects.equal(m2, null));
            if (_notEquals_4) {
              EList<JvmMember> _members_11 = it.getMembers();
              this._processorTypesBuilder.<JvmOperation>operator_add(_members_11, m2);
            }
            String _name_6 = attr_2.getName();
            String _name_7 = attr_2.getName();
            JvmTypeReference _typeRef_7 = this._typeReferenceBuilder.typeRef(entityType);
            String _updateColumn1_1 = this._processorGeneratorUtils.getUpdateColumn1(attr_2);
            PojoAttribute _updateColumn2_1 = this._processorGeneratorUtils.getUpdateColumn2(attr_2);
            PojoAttribute _createColumn1_1 = this._processorGeneratorUtils.getCreateColumn1(attr_2);
            String _createColumn2_1 = this._processorGeneratorUtils.getCreateColumn2(attr_2);
            final JvmOperation m3 = this._processorTypesBuilder._toSetterExt(attr_2, _name_6, _name_7, type, _typeRef_7, _updateColumn1_1, _updateColumn2_1, _createColumn1_1, _createColumn2_1, procNames);
            boolean _notEquals_5 = (!Objects.equal(m3, null));
            if (_notEquals_5) {
              EList<JvmMember> _members_12 = it.getMembers();
              this._processorTypesBuilder.<JvmOperation>operator_add(_members_12, m3);
            }
            final String operSuffix = this._processorGeneratorUtils.getOperatorsSuffix(entity);
            boolean _notEquals_6 = (!Objects.equal(operSuffix, null));
            if (_notEquals_6) {
              EList<JvmMember> _members_13 = it.getMembers();
              String _name_8 = attr_2.getName();
              String _plus_2 = (_name_8 + operSuffix);
              JvmTypeReference _typeRef_8 = this._typeReferenceBuilder.typeRef(String.class);
              final Procedure1<JvmField> _function_19 = (JvmField it_1) -> {
              };
              JvmField _field_4 = this._processorTypesBuilder.toField(entity, _plus_2, _typeRef_8, _function_19);
              this._processorTypesBuilder.<JvmField>operator_add(_members_13, _field_4);
              EList<JvmMember> _members_14 = it.getMembers();
              String _name_9 = attr_2.getName();
              String _plus_3 = (_name_9 + operSuffix);
              JvmTypeReference _typeRef_9 = this._typeReferenceBuilder.typeRef(String.class);
              JvmOperation _getter = this._processorTypesBuilder.toGetter(attr_2, _plus_3, _typeRef_9);
              this._processorTypesBuilder.<JvmOperation>operator_add(_members_14, _getter);
              EList<JvmMember> _members_15 = it.getMembers();
              String _name_10 = attr_2.getName();
              String _plus_4 = (_name_10 + operSuffix);
              String _name_11 = attr_2.getName();
              String _plus_5 = (_name_11 + operSuffix);
              JvmTypeReference _typeRef_10 = this._typeReferenceBuilder.typeRef(String.class);
              JvmOperation _setter = this._processorTypesBuilder.toSetter(attr_2, _plus_4, _plus_5, _typeRef_10);
              this._processorTypesBuilder.<JvmOperation>operator_add(_members_15, _setter);
              EList<JvmMember> _members_16 = it.getMembers();
              String _name_12 = attr_2.getName();
              String _plus_6 = (_name_12 + operSuffix);
              String _name_13 = attr_2.getName();
              String _plus_7 = (_name_13 + operSuffix);
              JvmTypeReference _typeRef_11 = this._typeReferenceBuilder.typeRef(String.class);
              JvmTypeReference _typeRef_12 = this._typeReferenceBuilder.typeRef(entityType);
              JvmOperation __toSetter = this._processorTypesBuilder._toSetter(attr_2, _plus_6, _plus_7, _typeRef_11, _typeRef_12, procNames);
              this._processorTypesBuilder.<JvmOperation>operator_add(_members_16, __toSetter);
            }
          }
        }
      }
      EList<AnnotatedFeature> _features_4 = entity.getFeatures();
      final Function1<AnnotatedFeature, Feature> _function_16 = (AnnotatedFeature it_1) -> {
        return it_1.getFeature();
      };
      List<Feature> _map_6 = ListExtensions.<AnnotatedFeature, Feature>map(_features_4, _function_16);
      Iterable<PojoProcedure> _filter_5 = Iterables.<PojoProcedure>filter(_map_6, PojoProcedure.class);
      for (final PojoProcedure proc : _filter_5) {
        EList<JvmMember> _members_9 = it.getMembers();
        String _name = proc.getName();
        JvmTypeReference _elvis = null;
        JvmParameterizedTypeReference _type = proc.getType();
        if (_type != null) {
          _elvis = _type;
        } else {
          JvmTypeReference _inferredType = this._processorTypesBuilder.inferredType();
          _elvis = _inferredType;
        }
        final Procedure1<JvmOperation> _function_17 = (JvmOperation it_1) -> {
          String _documentation_1 = this._processorTypesBuilder.getDocumentation(proc);
          this._processorTypesBuilder.setDocumentation(it_1, _documentation_1);
          boolean _isStatic = proc.isStatic();
          it_1.setStatic(_isStatic);
          EList<JvmFormalParameter> _params = proc.getParams();
          for (final JvmFormalParameter param : _params) {
            EList<JvmFormalParameter> _parameters = it_1.getParameters();
            String _name_1 = param.getName();
            JvmTypeReference _parameterType = param.getParameterType();
            JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(param, _name_1, _parameterType);
            this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          }
          XExpression _body = proc.getBody();
          this._processorTypesBuilder.setBody(it_1, _body);
        };
        JvmOperation _method = this._processorTypesBuilder.toMethod(proc, _name, _elvis, _function_17);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_9, _method);
      }
      final List<PojoAttribute> processingIdsList = this._processorGeneratorUtils.processingIdsAttributes(entity);
      boolean _isEmpty_3 = processingIdsList.isEmpty();
      boolean _not_3 = (!_isEmpty_3);
      if (_not_3) {
        JvmTypeReference _typeRef_5 = this._typeReferenceBuilder.typeRef(StringBuilder.class);
        final Procedure1<JvmOperation> _function_18 = (JvmOperation it_1) -> {
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("StringBuilder result = new StringBuilder(\"");
              _builder.append(simpleName, "");
              _builder.append("\");");
              _builder.newLineIfNotEmpty();
              {
                for(final PojoAttribute f2 : processingIdsList) {
                  {
                    boolean _isNative = PojoJvmModelInferrer.this._processorGeneratorUtils.isNative(f2);
                    if (_isNative) {
                      _builder.append("result.append(\"@\").append(\"");
                      String _name = f2.getName();
                      _builder.append(_name, "");
                      _builder.append("\");");
                    } else {
                      _builder.append("if (");
                      String _name_1 = f2.getName();
                      _builder.append(_name_1, "");
                      _builder.append(" != null)");
                      _builder.newLineIfNotEmpty();
                      _builder.append("\t");
                      _builder.append("result.append(\"@\").append(");
                      {
                        boolean _isPojo = PojoJvmModelInferrer.this._processorGeneratorUtils.isPojo(f2);
                        if (_isPojo) {
                          _builder.append("\"{\").append(");
                          String _name_2 = f2.getName();
                          _builder.append(_name_2, "\t");
                          _builder.append(".getProcessingIdForAttributes_()).append(\"}\"");
                        } else {
                          _builder.append("\"");
                          String _name_3 = f2.getName();
                          _builder.append(_name_3, "\t");
                          _builder.append("\"");
                        }
                      }
                      _builder.append(");");
                    }
                  }
                  _builder.newLineIfNotEmpty();
                }
              }
              {
                PojoEntity _parent = PojoJvmModelInferrer.this._processorGeneratorUtils.getParent(entity);
                boolean _notEquals = (!Objects.equal(_parent, null));
                if (_notEquals) {
                  _builder.append("result.append(\"@@\").append(super.getProcessingIdForAttributes_());");
                  _builder.newLine();
                }
              }
              _builder.append("return result;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        final JvmOperation method = this._processorTypesBuilder.toMethod(entity, "getProcessingIdForAttributes_", _typeRef_5, _function_18);
        EList<JvmMember> _members_10 = it.getMembers();
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_10, method);
      }
      final List<PojoAttribute> equalsList = this._processorGeneratorUtils.equalsAttributes(entity);
      boolean _isEmpty_4 = equalsList.isEmpty();
      boolean _not_4 = (!_isEmpty_4);
      if (_not_4) {
        JvmTypeReference _typeRef_6 = this._typeReferenceBuilder.typeRef(boolean.class);
        final Procedure1<JvmOperation> _function_19 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_7 = this._typeReferenceBuilder.typeRef(Object.class);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "obj", _typeRef_7);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (this == obj)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("return true;");
              _builder.newLine();
              _builder.append("if (obj == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("return false;");
              _builder.newLine();
              _builder.append("if (getClass() != obj.getClass())");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("return false;");
              _builder.newLine();
              _builder.append(simpleName, "");
              _builder.append(" other = (");
              _builder.append(simpleName, "");
              _builder.append(") obj;");
              _builder.newLineIfNotEmpty();
              {
                for(final PojoAttribute f2 : equalsList) {
                  {
                    boolean _isNative = PojoJvmModelInferrer.this._processorGeneratorUtils.isNative(f2);
                    if (_isNative) {
                      _builder.append("if (");
                      String _name = f2.getName();
                      _builder.append(_name, "");
                      _builder.append(" != other.");
                      String _name_1 = f2.getName();
                      _builder.append(_name_1, "");
                      _builder.append(")");
                    } else {
                      _builder.append("if (");
                      String _name_2 = f2.getName();
                      _builder.append(_name_2, "");
                      _builder.append(" == null || !");
                      String _name_3 = f2.getName();
                      _builder.append(_name_3, "");
                      _builder.append(".equals(other.");
                      String _name_4 = f2.getName();
                      _builder.append(_name_4, "");
                      _builder.append("))");
                    }
                  }
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("return false;");
                  _builder.newLine();
                }
              }
              _builder.append("return true;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        final JvmOperation method_1 = this._processorTypesBuilder.toMethod(entity, "equals", _typeRef_6, _function_19);
        EList<JvmAnnotationReference> _annotations_1 = method_1.getAnnotations();
        JvmAnnotationReference _annotationRef_1 = this._annotationTypesBuilder.annotationRef(Override.class);
        _annotations_1.add(_annotationRef_1);
        EList<JvmMember> _members_11 = it.getMembers();
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_11, method_1);
      }
      final List<PojoAttribute> hashCodeList = this._processorGeneratorUtils.hashCodeAttributes(entity);
      boolean _isEmpty_5 = hashCodeList.isEmpty();
      boolean _not_5 = (!_isEmpty_5);
      if (_not_5) {
        JvmTypeReference _typeRef_7 = this._typeReferenceBuilder.typeRef(int.class);
        final Procedure1<JvmOperation> _function_20 = (JvmOperation it_1) -> {
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("return java.util.Objects.hash(");
              {
                boolean _hasElements = false;
                for(final PojoAttribute f2 : hashCodeList) {
                  if (!_hasElements) {
                    _hasElements = true;
                  } else {
                    _builder.appendImmediate(", ", "");
                  }
                  String _name = f2.getName();
                  _builder.append(_name, "");
                }
              }
              _builder.append(");");
              _builder.newLineIfNotEmpty();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        final JvmOperation method_2 = this._processorTypesBuilder.toMethod(entity, "hashCode", _typeRef_7, _function_20);
        EList<JvmAnnotationReference> _annotations_2 = method_2.getAnnotations();
        JvmAnnotationReference _annotationRef_2 = this._annotationTypesBuilder.annotationRef(Override.class);
        _annotations_2.add(_annotationRef_2);
        EList<JvmMember> _members_12 = it.getMembers();
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_12, method_2);
      }
      final List<PojoAttribute> toStringList = this._processorGeneratorUtils.toStringAttributes(entity);
      boolean _isEmpty_6 = toStringList.isEmpty();
      boolean _not_6 = (!_isEmpty_6);
      if (_not_6) {
        JvmTypeReference _typeRef_8 = this._typeReferenceBuilder.typeRef(String.class);
        final Procedure1<JvmOperation> _function_21 = (JvmOperation it_1) -> {
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("return \"");
              _builder.append(simpleName, "");
              _builder.append(" [");
              {
                boolean _hasElements = false;
                for(final PojoAttribute f2 : toStringList) {
                  if (!_hasElements) {
                    _hasElements = true;
                  } else {
                    _builder.appendImmediate(" + \", ", "");
                  }
                  String _name = f2.getName();
                  _builder.append(_name, "");
                  _builder.append("=\" + ");
                  String _name_1 = f2.getName();
                  _builder.append(_name_1, "");
                }
              }
              {
                if (((!Objects.equal(entity.getSuperType(), null)) && (entity.getSuperType() instanceof PojoEntity))) {
                  _builder.append(" + super.toString()");
                }
              }
              _builder.append(" + \"]\";");
              _builder.newLineIfNotEmpty();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        final JvmOperation method_3 = this._processorTypesBuilder.toMethod(entity, "toString", _typeRef_8, _function_21);
        EList<JvmAnnotationReference> _annotations_3 = method_3.getAnnotations();
        JvmAnnotationReference _annotationRef_3 = this._annotationTypesBuilder.annotationRef(Override.class);
        _annotations_3.add(_annotationRef_3);
        EList<JvmMember> _members_13 = it.getMembers();
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_13, method_3);
      }
      EList<AnnotatedFeature> _features_5 = entity.getFeatures();
      final Function1<AnnotatedFeature, Feature> _function_22 = (AnnotatedFeature it_1) -> {
        return it_1.getFeature();
      };
      List<Feature> _map_7 = ListExtensions.<AnnotatedFeature, Feature>map(_features_5, _function_22);
      Iterable<PojoAttribute> _filter_6 = Iterables.<PojoAttribute>filter(_map_7, PojoAttribute.class);
      boolean _isEmpty_7 = IterableExtensions.isEmpty(_filter_6);
      boolean _not_7 = (!_isEmpty_7);
      if (_not_7) {
        EList<JvmMember> _members_14 = it.getMembers();
        JvmTypeReference _typeRef_9 = this._typeReferenceBuilder.typeRef(String.class);
        final Procedure1<JvmOperation> _function_23 = (JvmOperation it_1) -> {
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("return \"");
              _builder.append(simpleName, "");
              _builder.append(" [");
              {
                EList<AnnotatedFeature> _features = entity.getFeatures();
                final Function1<AnnotatedFeature, Feature> _function = (AnnotatedFeature it_2) -> {
                  return it_2.getFeature();
                };
                List<Feature> _map = ListExtensions.<AnnotatedFeature, Feature>map(_features, _function);
                Iterable<PojoAttribute> _filter = Iterables.<PojoAttribute>filter(_map, PojoAttribute.class);
                boolean _hasElements = false;
                for(final PojoAttribute f2 : _filter) {
                  if (!_hasElements) {
                    _hasElements = true;
                  } else {
                    _builder.appendImmediate(" + \", ", "");
                  }
                  String _name = f2.getName();
                  _builder.append(_name, "");
                  _builder.append("=\" + ");
                  String _name_1 = f2.getName();
                  _builder.append(_name_1, "");
                }
              }
              {
                if (((!Objects.equal(entity.getSuperType(), null)) && (entity.getSuperType() instanceof PojoEntity))) {
                  _builder.append(" + super.toString()");
                }
              }
              _builder.append(" + \"]\";");
              _builder.newLineIfNotEmpty();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "toStringFull", _typeRef_9, _function_23);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_14, _method_1);
      }
      final List<PojoAttribute> isDefList = this._processorGeneratorUtils.isDefAttributes(entity);
      boolean _isEmpty_8 = isDefList.isEmpty();
      boolean _not_8 = (!_isEmpty_8);
      if (_not_8) {
        final Procedure1<JvmEnumerationType> _function_24 = (JvmEnumerationType it_1) -> {
        };
        final JvmEnumerationType isDefType = this._processorTypesBuilder.toEnumerationType(entity, "Attribute", _function_24);
        EList<JvmMember> _members_15 = it.getMembers();
        this._processorTypesBuilder.<JvmEnumerationType>operator_add(_members_15, isDefType);
        for (final PojoAttribute attr_3 : isDefList) {
          EList<JvmMember> _members_16 = isDefType.getMembers();
          String _name_1 = attr_3.getName();
          JvmEnumerationLiteral _enumerationLiteral_2 = this._processorTypesBuilder.toEnumerationLiteral(entity, _name_1);
          this._processorTypesBuilder.<JvmEnumerationLiteral>operator_add(_members_16, _enumerationLiteral_2);
        }
        JvmTypeReference _typeRef_10 = this._typeReferenceBuilder.typeRef(String.class);
        final JvmTypeReference identifierSetType = this._typeReferenceBuilder.typeRef(Set.class, _typeRef_10);
        EList<JvmMember> _members_17 = it.getMembers();
        final Procedure1<JvmField> _function_25 = (JvmField it_1) -> {
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append(" ");
              _builder.append("new ");
              _builder.append(PojoJvmModelInferrer.this.HASH_SET, " ");
              _builder.append("<String>()");
            }
          };
          this._processorTypesBuilder.setInitializer(it_1, _client);
        };
        JvmField _field_3 = this._processorTypesBuilder.toField(entity, "nullValues_", identifierSetType, _function_25);
        this._processorTypesBuilder.<JvmField>operator_add(_members_17, _field_3);
        EList<JvmMember> _members_18 = it.getMembers();
        JvmTypeReference _typeRef_11 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_26 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_12 = this._typeReferenceBuilder.typeRef(isDefType);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_12);
          JvmTypeReference _cloneWithProxies_3 = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _cloneWithProxies_3);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_27 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_8 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_27);
          this.addAnnotationsX(it_1, _map_8);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (attributes == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("for (Attribute attribute : attributes)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("nullValues_.add(attribute.name());");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "setNull_", _typeRef_11, _function_26);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_18, _method_2);
        EList<JvmMember> _members_19 = it.getMembers();
        JvmTypeReference _typeRef_12 = this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies_3 = this._processorTypesBuilder.cloneWithProxies(_typeRef_12);
        final Procedure1<JvmOperation> _function_27 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_13 = this._typeReferenceBuilder.typeRef(isDefType);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_13);
          JvmTypeReference _cloneWithProxies_4 = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _cloneWithProxies_4);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_28 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_8 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_28);
          this.addAnnotationsX(it_1, _map_8);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("setNull_(attributes);");
              _builder.newLine();
              _builder.append("return this;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "withNull_", _cloneWithProxies_3, _function_27);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_19, _method_3);
        EList<JvmMember> _members_20 = it.getMembers();
        JvmTypeReference _typeRef_13 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_28 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_14 = this._typeReferenceBuilder.typeRef(isDefType);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_14);
          JvmTypeReference _cloneWithProxies_4 = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _cloneWithProxies_4);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_29 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_8 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_29);
          this.addAnnotationsX(it_1, _map_8);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (attributes == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("for (Attribute attribute : attributes)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("nullValues_.remove(attribute.name());");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_4 = this._processorTypesBuilder.toMethod(entity, "clearNull_", _typeRef_13, _function_28);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_20, _method_4);
        EList<JvmMember> _members_21 = it.getMembers();
        JvmTypeReference _typeRef_14 = this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies_4 = this._processorTypesBuilder.cloneWithProxies(_typeRef_14);
        final Procedure1<JvmOperation> _function_29 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_15 = this._typeReferenceBuilder.typeRef(isDefType);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_15);
          JvmTypeReference _cloneWithProxies_5 = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _cloneWithProxies_5);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_30 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_8 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_30);
          this.addAnnotationsX(it_1, _map_8);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("clearNull_(attributes);");
              _builder.newLine();
              _builder.append("return this;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_5 = this._processorTypesBuilder.toMethod(entity, "_clearNull_", _cloneWithProxies_4, _function_29);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_21, _method_5);
        EList<JvmMember> _members_22 = it.getMembers();
        JvmTypeReference _typeRef_15 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_30 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_16 = this._typeReferenceBuilder.typeRef(String.class);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_16);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _addArrayTypeDimension);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (attributes == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("for (String attribute : attributes)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("nullValues_.add(attribute);");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_6 = this._processorTypesBuilder.toMethod(entity, "setNull_", _typeRef_15, _function_30);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_22, _method_6);
        EList<JvmMember> _members_23 = it.getMembers();
        JvmTypeReference _typeRef_16 = this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies_5 = this._processorTypesBuilder.cloneWithProxies(_typeRef_16);
        final Procedure1<JvmOperation> _function_31 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_17 = this._typeReferenceBuilder.typeRef(String.class);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_17);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _addArrayTypeDimension);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("setNull_(attributes);");
              _builder.newLine();
              _builder.append("return this;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_7 = this._processorTypesBuilder.toMethod(entity, "withNull_", _cloneWithProxies_5, _function_31);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_23, _method_7);
        EList<JvmMember> _members_24 = it.getMembers();
        JvmTypeReference _typeRef_17 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_32 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_18 = this._typeReferenceBuilder.typeRef(String.class);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_18);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _addArrayTypeDimension);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (attributes == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("for (String attribute : attributes)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("nullValues_.remove(attribute);");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_8 = this._processorTypesBuilder.toMethod(entity, "clearNull_", _typeRef_17, _function_32);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_24, _method_8);
        EList<JvmMember> _members_25 = it.getMembers();
        JvmTypeReference _typeRef_18 = this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies_6 = this._processorTypesBuilder.cloneWithProxies(_typeRef_18);
        final Procedure1<JvmOperation> _function_33 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_19 = this._typeReferenceBuilder.typeRef(String.class);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_19);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _addArrayTypeDimension);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("clearNull_(attributes);");
              _builder.newLine();
              _builder.append("return this;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_9 = this._processorTypesBuilder.toMethod(entity, "_clearNull_", _cloneWithProxies_6, _function_33);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_25, _method_9);
        EList<JvmMember> _members_26 = it.getMembers();
        JvmTypeReference _typeRef_19 = this._typeReferenceBuilder.typeRef(Boolean.class);
        final Procedure1<JvmOperation> _function_34 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_20 = this._typeReferenceBuilder.typeRef(isDefType);
          JvmTypeReference _cloneWithProxies_7 = this._processorTypesBuilder.cloneWithProxies(_typeRef_20);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attribute", _cloneWithProxies_7);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_35 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_8 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_35);
          this.addAnnotationsX(it_1, _map_8);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (attribute == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("return nullValues_.contains(attribute.name());");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_10 = this._processorTypesBuilder.toMethod(entity, "isNull_", _typeRef_19, _function_34);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_26, _method_10);
        EList<JvmMember> _members_27 = it.getMembers();
        JvmTypeReference _typeRef_20 = this._typeReferenceBuilder.typeRef(Boolean.class);
        final Procedure1<JvmOperation> _function_35 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_21 = this._typeReferenceBuilder.typeRef(String.class);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attrName", _typeRef_21);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (attrName == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("return nullValues_.contains(attrName);");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_11 = this._processorTypesBuilder.toMethod(entity, "isNull_", _typeRef_20, _function_35);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_27, _method_11);
        EList<JvmMember> _members_28 = it.getMembers();
        JvmTypeReference _typeRef_21 = this._typeReferenceBuilder.typeRef(Boolean.class);
        final Procedure1<JvmOperation> _function_36 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_22 = this._typeReferenceBuilder.typeRef(String.class);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attrName", _typeRef_22);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          EList<JvmFormalParameter> _parameters_1 = it_1.getParameters();
          JvmTypeReference _typeRef_23 = this._typeReferenceBuilder.typeRef(Boolean.class);
          JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "isAttrNotNull", _typeRef_23);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (attrName == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("if (nullValues_.contains(attrName))");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("return true;");
              _builder.newLine();
              _builder.append("if (isAttrNotNull != null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("return isAttrNotNull;");
              _builder.newLine();
              _builder.append("return false;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_12 = this._processorTypesBuilder.toMethod(entity, "isDef_", _typeRef_21, _function_36);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_28, _method_12);
        EList<JvmMember> _members_29 = it.getMembers();
        JvmTypeReference _typeRef_22 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_37 = (JvmOperation it_1) -> {
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("nullValues_ = new ");
              _builder.append(PojoJvmModelInferrer.this.HASH_SET, "");
              _builder.append("<String>();");
              _builder.newLineIfNotEmpty();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_13 = this._processorTypesBuilder.toMethod(entity, "clearAllNull_", _typeRef_22, _function_37);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_29, _method_13);
        boolean _isEmpty_9 = processingIdsList.isEmpty();
        boolean _not_9 = (!_isEmpty_9);
        if (_not_9) {
          EList<JvmMember> _members_30 = it.getMembers();
          JvmTypeReference _typeRef_23 = this._typeReferenceBuilder.typeRef(StringBuilder.class);
          final Procedure1<JvmOperation> _function_38 = (JvmOperation it_1) -> {
            StringConcatenationClient _client = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append("if (nullValues_ == null || nullValues_.isEmpty())");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("return null;");
                _builder.newLine();
                _builder.append("StringBuilder result = new StringBuilder(\"NULL\");");
                _builder.newLine();
                _builder.append("for (Attribute attribute : Attribute.values()) {");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("if (nullValues_.contains(attribute.name()))");
                _builder.newLine();
                _builder.append("\t\t");
                _builder.append("result.append(\"@\").append(attribute.name());");
                _builder.newLine();
                _builder.append("}");
                _builder.newLine();
                _builder.append("return result;");
                _builder.newLine();
              }
            };
            this._processorTypesBuilder.setBody(it_1, _client);
          };
          JvmOperation _method_14 = this._processorTypesBuilder.toMethod(entity, "getProcessingIdForNulls_", _typeRef_23, _function_38);
          this._processorTypesBuilder.<JvmOperation>operator_add(_members_30, _method_14);
        }
      }
      final List<PojoAttribute> enumDefList = this._processorGeneratorUtils.enumDefAttributes(entity);
      boolean _isEmpty_10 = enumDefList.isEmpty();
      boolean _not_10 = (!_isEmpty_10);
      if (_not_10) {
        final Procedure1<JvmEnumerationType> _function_39 = (JvmEnumerationType it_1) -> {
        };
        final JvmEnumerationType isDefType_1 = this._processorTypesBuilder.toEnumerationType(entity, "Attribute", _function_39);
        EList<JvmMember> _members_31 = it.getMembers();
        this._processorTypesBuilder.<JvmEnumerationType>operator_add(_members_31, isDefType_1);
        for (final PojoAttribute attr_4 : enumDefList) {
          EList<JvmMember> _members_32 = isDefType_1.getMembers();
          String _name_2 = attr_4.getName();
          JvmEnumerationLiteral _enumerationLiteral_3 = this._processorTypesBuilder.toEnumerationLiteral(entity, _name_2);
          this._processorTypesBuilder.<JvmEnumerationLiteral>operator_add(_members_32, _enumerationLiteral_3);
        }
      }
      final List<PojoAttribute> toInitList = this._processorGeneratorUtils.toInitAttributes(entity);
      boolean _isEmpty_11 = toInitList.isEmpty();
      boolean _not_11 = (!_isEmpty_11);
      if (_not_11) {
        final Procedure1<JvmEnumerationType> _function_40 = (JvmEnumerationType it_1) -> {
        };
        final JvmEnumerationType toInitType = this._processorTypesBuilder.toEnumerationType(entity, "Association", _function_40);
        EList<JvmMember> _members_33 = it.getMembers();
        this._processorTypesBuilder.<JvmEnumerationType>operator_add(_members_33, toInitType);
        for (final PojoAttribute attr_5 : toInitList) {
          EList<JvmMember> _members_34 = toInitType.getMembers();
          String _name_3 = attr_5.getName();
          JvmEnumerationLiteral _enumerationLiteral_4 = this._processorTypesBuilder.toEnumerationLiteral(entity, _name_3);
          this._processorTypesBuilder.<JvmEnumerationLiteral>operator_add(_members_34, _enumerationLiteral_4);
        }
        JvmTypeReference _typeRef_24 = this._typeReferenceBuilder.typeRef(String.class);
        final JvmTypeReference identifierSetType_1 = this._typeReferenceBuilder.typeRef(Set.class, _typeRef_24);
        EList<JvmMember> _members_35 = it.getMembers();
        final Procedure1<JvmField> _function_41 = (JvmField it_1) -> {
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append(" ");
              _builder.append("new ");
              _builder.append(PojoJvmModelInferrer.this.HASH_SET, " ");
              _builder.append("<String>()");
            }
          };
          this._processorTypesBuilder.setInitializer(it_1, _client);
        };
        JvmField _field_4 = this._processorTypesBuilder.toField(entity, "initAssociations_", identifierSetType_1, _function_41);
        this._processorTypesBuilder.<JvmField>operator_add(_members_35, _field_4);
        EList<JvmMember> _members_36 = it.getMembers();
        JvmTypeReference _cloneWithProxies_7 = this._processorTypesBuilder.cloneWithProxies(identifierSetType_1);
        final Procedure1<JvmOperation> _function_42 = (JvmOperation it_1) -> {
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_43 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_8 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_43);
          this.addAnnotationsX(it_1, _map_8);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("return this.initAssociations_;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_15 = this._processorTypesBuilder.toMethod(entity, "getInitAssociations_", _cloneWithProxies_7, _function_42);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_36, _method_15);
        EList<JvmMember> _members_37 = it.getMembers();
        JvmTypeReference _typeRef_25 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_43 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _cloneWithProxies_8 = this._processorTypesBuilder.cloneWithProxies(identifierSetType_1);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "initAssociations_", _cloneWithProxies_8);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_44 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_8 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_44);
          this.addAnnotationsX(it_1, _map_8);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("this.initAssociations_ = initAssociations_;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_16 = this._processorTypesBuilder.toMethod(entity, "setInitAssociations_", _typeRef_25, _function_43);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_37, _method_16);
        EList<JvmMember> _members_38 = it.getMembers();
        JvmTypeReference _typeRef_26 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_44 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_27 = this._typeReferenceBuilder.typeRef(toInitType);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_27);
          JvmTypeReference _cloneWithProxies_8 = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "associations", _cloneWithProxies_8);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_45 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_8 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_45);
          this.addAnnotationsX(it_1, _map_8);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (associations == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("for (Association association : associations)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("initAssociations_.add(association.name());");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_17 = this._processorTypesBuilder.toMethod(entity, "setInit_", _typeRef_26, _function_44);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_38, _method_17);
        EList<JvmMember> _members_39 = it.getMembers();
        JvmTypeReference _typeRef_27 = this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies_8 = this._processorTypesBuilder.cloneWithProxies(_typeRef_27);
        final Procedure1<JvmOperation> _function_45 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_28 = this._typeReferenceBuilder.typeRef(toInitType);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_28);
          JvmTypeReference _cloneWithProxies_9 = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "associations", _cloneWithProxies_9);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_46 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_8 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_46);
          this.addAnnotationsX(it_1, _map_8);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("setInit_(associations);");
              _builder.newLine();
              _builder.append("return this;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_18 = this._processorTypesBuilder.toMethod(entity, "withInit_", _cloneWithProxies_8, _function_45);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_39, _method_18);
        EList<JvmMember> _members_40 = it.getMembers();
        JvmTypeReference _typeRef_28 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_46 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_29 = this._typeReferenceBuilder.typeRef(toInitType);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_29);
          JvmTypeReference _cloneWithProxies_9 = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "associations", _cloneWithProxies_9);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_47 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_8 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_47);
          this.addAnnotationsX(it_1, _map_8);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (associations == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("for (Association association : associations)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("initAssociations_.remove(association.name());");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_19 = this._processorTypesBuilder.toMethod(entity, "clearInit_", _typeRef_28, _function_46);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_40, _method_19);
        EList<JvmMember> _members_41 = it.getMembers();
        JvmTypeReference _typeRef_29 = this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies_9 = this._processorTypesBuilder.cloneWithProxies(_typeRef_29);
        final Procedure1<JvmOperation> _function_47 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_30 = this._typeReferenceBuilder.typeRef(toInitType);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_30);
          JvmTypeReference _cloneWithProxies_10 = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "associations", _cloneWithProxies_10);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_48 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_8 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_48);
          this.addAnnotationsX(it_1, _map_8);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("clearInit_(associations);");
              _builder.newLine();
              _builder.append("return this;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_20 = this._processorTypesBuilder.toMethod(entity, "_clearInit_", _cloneWithProxies_9, _function_47);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_41, _method_20);
        EList<JvmMember> _members_42 = it.getMembers();
        JvmTypeReference _typeRef_30 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_48 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_31 = this._typeReferenceBuilder.typeRef(String.class);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_31);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "associations", _addArrayTypeDimension);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (associations == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("for (String association : associations)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("initAssociations_.add(association);");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_21 = this._processorTypesBuilder.toMethod(entity, "setInit_", _typeRef_30, _function_48);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_42, _method_21);
        EList<JvmMember> _members_43 = it.getMembers();
        JvmTypeReference _typeRef_31 = this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies_10 = this._processorTypesBuilder.cloneWithProxies(_typeRef_31);
        final Procedure1<JvmOperation> _function_49 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_32 = this._typeReferenceBuilder.typeRef(String.class);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_32);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "associations", _addArrayTypeDimension);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("setInit_(associations);");
              _builder.newLine();
              _builder.append("return this;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_22 = this._processorTypesBuilder.toMethod(entity, "withInit_", _cloneWithProxies_10, _function_49);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_43, _method_22);
        EList<JvmMember> _members_44 = it.getMembers();
        JvmTypeReference _typeRef_32 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_50 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_33 = this._typeReferenceBuilder.typeRef(String.class);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_33);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "associations", _addArrayTypeDimension);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (associations == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("for (String association : associations)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("initAssociations_.remove(association);");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_23 = this._processorTypesBuilder.toMethod(entity, "clearInit_", _typeRef_32, _function_50);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_44, _method_23);
        EList<JvmMember> _members_45 = it.getMembers();
        JvmTypeReference _typeRef_33 = this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies_11 = this._processorTypesBuilder.cloneWithProxies(_typeRef_33);
        final Procedure1<JvmOperation> _function_51 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_34 = this._typeReferenceBuilder.typeRef(String.class);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_34);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "associations", _addArrayTypeDimension);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("clearInit_(associations);");
              _builder.newLine();
              _builder.append("return this;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_24 = this._processorTypesBuilder.toMethod(entity, "_clearInit_", _cloneWithProxies_11, _function_51);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_45, _method_24);
        EList<JvmMember> _members_46 = it.getMembers();
        JvmTypeReference _typeRef_34 = this._typeReferenceBuilder.typeRef(Boolean.class);
        final Procedure1<JvmOperation> _function_52 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_35 = this._typeReferenceBuilder.typeRef(toInitType);
          JvmTypeReference _cloneWithProxies_12 = this._processorTypesBuilder.cloneWithProxies(_typeRef_35);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "association", _cloneWithProxies_12);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_53 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_8 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_53);
          this.addAnnotationsX(it_1, _map_8);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (association == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("return initAssociations_.contains(association.name());");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_25 = this._processorTypesBuilder.toMethod(entity, "toInit_", _typeRef_34, _function_52);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_46, _method_25);
        EList<JvmMember> _members_47 = it.getMembers();
        JvmTypeReference _typeRef_35 = this._typeReferenceBuilder.typeRef(Boolean.class);
        final Procedure1<JvmOperation> _function_53 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_36 = this._typeReferenceBuilder.typeRef(String.class);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "association", _typeRef_36);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (association == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("return initAssociations_.contains(association);");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_26 = this._processorTypesBuilder.toMethod(entity, "toInit_", _typeRef_35, _function_53);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_47, _method_26);
        EList<JvmMember> _members_48 = it.getMembers();
        JvmTypeReference _typeRef_36 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_54 = (JvmOperation it_1) -> {
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("initAssociations_ = new ");
              _builder.append(PojoJvmModelInferrer.this.HASH_SET, "");
              _builder.append("<String>();");
              _builder.newLineIfNotEmpty();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_27 = this._processorTypesBuilder.toMethod(entity, "clearAllInit_", _typeRef_36, _function_54);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_48, _method_27);
        boolean _isEmpty_12 = processingIdsList.isEmpty();
        boolean _not_12 = (!_isEmpty_12);
        if (_not_12) {
          EList<JvmMember> _members_49 = it.getMembers();
          JvmTypeReference _typeRef_37 = this._typeReferenceBuilder.typeRef(StringBuilder.class);
          final Procedure1<JvmOperation> _function_55 = (JvmOperation it_1) -> {
            StringConcatenationClient _client = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append("if (initAssociations_ == null || initAssociations_.isEmpty())");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("return null;");
                _builder.newLine();
                _builder.append("StringBuilder result = new StringBuilder(\"ASSOC\");");
                _builder.newLine();
                _builder.append("for (Association association : Association.values()) {");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("if (initAssociations_.contains(association.name()))");
                _builder.newLine();
                _builder.append("\t\t");
                _builder.append("result.append(\"@\").append(association.name());");
                _builder.newLine();
                _builder.append("}");
                _builder.newLine();
                _builder.append("return result;");
                _builder.newLine();
              }
            };
            this._processorTypesBuilder.setBody(it_1, _client);
          };
          JvmOperation _method_28 = this._processorTypesBuilder.toMethod(entity, "getProcessingIdForAssociations_", _typeRef_37, _function_55);
          this._processorTypesBuilder.<JvmOperation>operator_add(_members_49, _method_28);
        }
      }
      final List<PojoAttribute> enumInitList = this._processorGeneratorUtils.enumInitAttributes(entity);
      boolean _isEmpty_13 = enumInitList.isEmpty();
      boolean _not_13 = (!_isEmpty_13);
      if (_not_13) {
        final Procedure1<JvmEnumerationType> _function_56 = (JvmEnumerationType it_1) -> {
        };
        final JvmEnumerationType toInitType_1 = this._processorTypesBuilder.toEnumerationType(entity, "Association", _function_56);
        EList<JvmMember> _members_50 = it.getMembers();
        this._processorTypesBuilder.<JvmEnumerationType>operator_add(_members_50, toInitType_1);
        for (final PojoAttribute attr_6 : enumInitList) {
          EList<JvmMember> _members_51 = toInitType_1.getMembers();
          String _name_4 = attr_6.getName();
          JvmEnumerationLiteral _enumerationLiteral_5 = this._processorTypesBuilder.toEnumerationLiteral(entity, _name_4);
          this._processorTypesBuilder.<JvmEnumerationLiteral>operator_add(_members_51, _enumerationLiteral_5);
        }
      }
      boolean _hasOperators = this._processorGeneratorUtils.hasOperators(entity);
      if (_hasOperators) {
        final Procedure1<JvmEnumerationType> _function_57 = (JvmEnumerationType it_1) -> {
        };
        final JvmEnumerationType opAttrType = this._processorTypesBuilder.toEnumerationType(entity, "OpAttribute", _function_57);
        EList<JvmMember> _members_52 = it.getMembers();
        this._processorTypesBuilder.<JvmEnumerationType>operator_add(_members_52, opAttrType);
        EList<AnnotatedFeature> _features_6 = entity.getFeatures();
        final Function1<AnnotatedFeature, Feature> _function_58 = (AnnotatedFeature it_1) -> {
          return it_1.getFeature();
        };
        List<Feature> _map_8 = ListExtensions.<AnnotatedFeature, Feature>map(_features_6, _function_58);
        Iterable<PojoAttribute> _filter_7 = Iterables.<PojoAttribute>filter(_map_8, PojoAttribute.class);
        for (final PojoAttribute attr_7 : _filter_7) {
          EList<JvmMember> _members_53 = opAttrType.getMembers();
          String _name_5 = attr_7.getName();
          JvmEnumerationLiteral _enumerationLiteral_6 = this._processorTypesBuilder.toEnumerationLiteral(entity, _name_5);
          this._processorTypesBuilder.<JvmEnumerationLiteral>operator_add(_members_53, _enumerationLiteral_6);
        }
        JvmTypeReference _typeRef_38 = this._typeReferenceBuilder.typeRef(String.class);
        JvmTypeReference _typeRef_39 = this._typeReferenceBuilder.typeRef(String.class);
        final JvmTypeReference identifierMapType = this._typeReferenceBuilder.typeRef(Map.class, _typeRef_38, _typeRef_39);
        EList<JvmMember> _members_54 = it.getMembers();
        final Procedure1<JvmField> _function_59 = (JvmField it_1) -> {
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append(" ");
              _builder.append("new ");
              _builder.append(PojoJvmModelInferrer.this.HASH_MAP, " ");
              _builder.append("<String, String>()");
            }
          };
          this._processorTypesBuilder.setInitializer(it_1, _client);
        };
        JvmField _field_5 = this._processorTypesBuilder.toField(entity, "operators_", identifierMapType, _function_59);
        this._processorTypesBuilder.<JvmField>operator_add(_members_54, _field_5);
        EList<JvmMember> _members_55 = it.getMembers();
        final Procedure1<JvmOperation> _function_60 = (JvmOperation it_1) -> {
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_61 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_9 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_61);
          this.addAnnotationsX(it_1, _map_9);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("return operators_;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_29 = this._processorTypesBuilder.toMethod(entity, "getOperators_", identifierMapType, _function_60);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_55, _method_29);
        EList<JvmMember> _members_56 = it.getMembers();
        JvmTypeReference _typeRef_40 = this._typeReferenceBuilder.typeRef(String.class);
        final Procedure1<JvmOperation> _function_61 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_41 = this._typeReferenceBuilder.typeRef(String.class);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attrName", _typeRef_41);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (attrName == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("return operators_.get(attrName);");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_30 = this._processorTypesBuilder.toMethod(entity, "getOp_", _typeRef_40, _function_61);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_56, _method_30);
        EList<JvmMember> _members_57 = it.getMembers();
        JvmTypeReference _typeRef_41 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_62 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_42 = this._typeReferenceBuilder.typeRef(String.class);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "operator", _typeRef_42);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          EList<JvmFormalParameter> _parameters_1 = it_1.getParameters();
          JvmTypeReference _typeRef_43 = this._typeReferenceBuilder.typeRef(opAttrType);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_43);
          JvmTypeReference _cloneWithProxies_12 = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
          JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "attributes", _cloneWithProxies_12);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
          it_1.setVarArgs(true);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_63 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_9 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_63);
          this.addAnnotationsX(it_1, _map_9);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (attributes == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("for (OpAttribute attribute : attributes)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("operators_.put(attribute.name(), operator);");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_31 = this._processorTypesBuilder.toMethod(entity, "setOp_", _typeRef_41, _function_62);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_57, _method_31);
        EList<JvmMember> _members_58 = it.getMembers();
        JvmTypeReference _typeRef_42 = this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies_12 = this._processorTypesBuilder.cloneWithProxies(_typeRef_42);
        final Procedure1<JvmOperation> _function_63 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_43 = this._typeReferenceBuilder.typeRef(String.class);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "operator", _typeRef_43);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          EList<JvmFormalParameter> _parameters_1 = it_1.getParameters();
          JvmTypeReference _typeRef_44 = this._typeReferenceBuilder.typeRef(opAttrType);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_44);
          JvmTypeReference _cloneWithProxies_13 = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
          JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "attributes", _cloneWithProxies_13);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
          it_1.setVarArgs(true);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_64 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_9 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_64);
          this.addAnnotationsX(it_1, _map_9);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("setOp_(operator, attributes);");
              _builder.newLine();
              _builder.append("return this;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_32 = this._processorTypesBuilder.toMethod(entity, "withOp_", _cloneWithProxies_12, _function_63);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_58, _method_32);
        EList<JvmMember> _members_59 = it.getMembers();
        JvmTypeReference _typeRef_43 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_64 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_44 = this._typeReferenceBuilder.typeRef(opAttrType);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_44);
          JvmTypeReference _cloneWithProxies_13 = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _cloneWithProxies_13);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_65 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_9 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_65);
          this.addAnnotationsX(it_1, _map_9);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (attributes == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("for (OpAttribute attribute : attributes)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("operators_.remove(attribute.name());");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_33 = this._processorTypesBuilder.toMethod(entity, "clearOp_", _typeRef_43, _function_64);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_59, _method_33);
        EList<JvmMember> _members_60 = it.getMembers();
        JvmTypeReference _typeRef_44 = this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies_13 = this._processorTypesBuilder.cloneWithProxies(_typeRef_44);
        final Procedure1<JvmOperation> _function_65 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_45 = this._typeReferenceBuilder.typeRef(opAttrType);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_45);
          JvmTypeReference _cloneWithProxies_14 = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _cloneWithProxies_14);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_66 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_9 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_66);
          this.addAnnotationsX(it_1, _map_9);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("clearOp_(attributes);");
              _builder.newLine();
              _builder.append("return this;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_34 = this._processorTypesBuilder.toMethod(entity, "_clearOp_", _cloneWithProxies_13, _function_65);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_60, _method_34);
        EList<JvmMember> _members_61 = it.getMembers();
        JvmTypeReference _typeRef_45 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_66 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_46 = this._typeReferenceBuilder.typeRef(String.class);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "operator", _typeRef_46);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          EList<JvmFormalParameter> _parameters_1 = it_1.getParameters();
          JvmTypeReference _typeRef_47 = this._typeReferenceBuilder.typeRef(String.class);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_47);
          JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "attributes", _addArrayTypeDimension);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
          it_1.setVarArgs(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (attributes == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("for (String attribute : attributes)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("operators_.put(attribute, operator);");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_35 = this._processorTypesBuilder.toMethod(entity, "setOp_", _typeRef_45, _function_66);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_61, _method_35);
        EList<JvmMember> _members_62 = it.getMembers();
        JvmTypeReference _typeRef_46 = this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies_14 = this._processorTypesBuilder.cloneWithProxies(_typeRef_46);
        final Procedure1<JvmOperation> _function_67 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_47 = this._typeReferenceBuilder.typeRef(String.class);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "operator", _typeRef_47);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          EList<JvmFormalParameter> _parameters_1 = it_1.getParameters();
          JvmTypeReference _typeRef_48 = this._typeReferenceBuilder.typeRef(String.class);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_48);
          JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "attributes", _addArrayTypeDimension);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
          it_1.setVarArgs(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("setOp_(operator, attributes);");
              _builder.newLine();
              _builder.append("return this;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_36 = this._processorTypesBuilder.toMethod(entity, "withOp_", _cloneWithProxies_14, _function_67);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_62, _method_36);
        EList<JvmMember> _members_63 = it.getMembers();
        JvmTypeReference _typeRef_47 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_68 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_48 = this._typeReferenceBuilder.typeRef(String.class);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_48);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _addArrayTypeDimension);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (attributes == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("for (String attribute : attributes)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("operators_.remove(attribute);");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_37 = this._processorTypesBuilder.toMethod(entity, "clearOp_", _typeRef_47, _function_68);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_63, _method_37);
        EList<JvmMember> _members_64 = it.getMembers();
        JvmTypeReference _typeRef_48 = this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies_15 = this._processorTypesBuilder.cloneWithProxies(_typeRef_48);
        final Procedure1<JvmOperation> _function_69 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_49 = this._typeReferenceBuilder.typeRef(String.class);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_49);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _addArrayTypeDimension);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("clearOp_(attributes);");
              _builder.newLine();
              _builder.append("return this;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_38 = this._processorTypesBuilder.toMethod(entity, "_clearOp_", _cloneWithProxies_15, _function_69);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_64, _method_38);
        EList<JvmMember> _members_65 = it.getMembers();
        JvmTypeReference _typeRef_49 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_70 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_50 = this._typeReferenceBuilder.typeRef(opAttrType);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_50);
          JvmTypeReference _cloneWithProxies_16 = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _cloneWithProxies_16);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_71 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_9 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_71);
          this.addAnnotationsX(it_1, _map_9);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (attributes == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("for (OpAttribute attribute : attributes)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("operators_.put(attribute.name(), \"is null\");");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_39 = this._processorTypesBuilder.toMethod(entity, "setNullOp_", _typeRef_49, _function_70);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_65, _method_39);
        EList<JvmMember> _members_66 = it.getMembers();
        JvmTypeReference _typeRef_50 = this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies_16 = this._processorTypesBuilder.cloneWithProxies(_typeRef_50);
        final Procedure1<JvmOperation> _function_71 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_51 = this._typeReferenceBuilder.typeRef(opAttrType);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_51);
          JvmTypeReference _cloneWithProxies_17 = this._processorTypesBuilder.cloneWithProxies(_addArrayTypeDimension);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _cloneWithProxies_17);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          List<Annotation> _conflictAnnotations = this._processorGeneratorUtils.conflictAnnotations(entity);
          final Function1<Annotation, XAnnotation> _function_72 = (Annotation a) -> {
            return a.getAnnotation();
          };
          List<XAnnotation> _map_9 = ListExtensions.<Annotation, XAnnotation>map(_conflictAnnotations, _function_72);
          this.addAnnotationsX(it_1, _map_9);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("setNullOp_(attributes);");
              _builder.newLine();
              _builder.append("return this;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_40 = this._processorTypesBuilder.toMethod(entity, "withNullOp_", _cloneWithProxies_16, _function_71);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_66, _method_40);
        EList<JvmMember> _members_67 = it.getMembers();
        JvmTypeReference _typeRef_51 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_72 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_52 = this._typeReferenceBuilder.typeRef(String.class);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_52);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _addArrayTypeDimension);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("if (attributes == null)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("throw new IllegalArgumentException();");
              _builder.newLine();
              _builder.append("for (String attribute : attributes)");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("operators_.put(attribute, \"is null\");");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_41 = this._processorTypesBuilder.toMethod(entity, "setNullOp_", _typeRef_51, _function_72);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_67, _method_41);
        EList<JvmMember> _members_68 = it.getMembers();
        JvmTypeReference _typeRef_52 = this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies_17 = this._processorTypesBuilder.cloneWithProxies(_typeRef_52);
        final Procedure1<JvmOperation> _function_73 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_53 = this._typeReferenceBuilder.typeRef(String.class);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_53);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "attributes", _addArrayTypeDimension);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("setNullOp_(attributes);");
              _builder.newLine();
              _builder.append("return this;");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_42 = this._processorTypesBuilder.toMethod(entity, "withNullOp_", _cloneWithProxies_17, _function_73);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_68, _method_42);
        EList<JvmMember> _members_69 = it.getMembers();
        JvmTypeReference _typeRef_53 = this._typeReferenceBuilder.typeRef(Void.TYPE);
        final Procedure1<JvmOperation> _function_74 = (JvmOperation it_1) -> {
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("operators_ = new ");
              _builder.append(PojoJvmModelInferrer.this.HASH_MAP, "");
              _builder.append("<String, String>();");
              _builder.newLineIfNotEmpty();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        JvmOperation _method_43 = this._processorTypesBuilder.toMethod(entity, "clearAllOps_", _typeRef_53, _function_74);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_69, _method_43);
        boolean _isEmpty_14 = processingIdsList.isEmpty();
        boolean _not_14 = (!_isEmpty_14);
        if (_not_14) {
          EList<JvmMember> _members_70 = it.getMembers();
          JvmTypeReference _typeRef_54 = this._typeReferenceBuilder.typeRef(StringBuilder.class);
          final Procedure1<JvmOperation> _function_75 = (JvmOperation it_1) -> {
            StringConcatenationClient _client = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append("if (operators_ == null || operators_.isEmpty())");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("return null;");
                _builder.newLine();
                _builder.append("StringBuilder result = new StringBuilder(\"OPER\");");
                _builder.newLine();
                _builder.append("for (OpAttribute opAttribute : OpAttribute.values()) {");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("if (operators_.containsKey(opAttribute.name()))");
                _builder.newLine();
                _builder.append("\t\t");
                _builder.append("result.append(\"@\").append(opAttribute.name()).append(\"=\").append(operators_.get(opAttribute.name()));");
                _builder.newLine();
                _builder.append("}");
                _builder.newLine();
                _builder.append("return result;");
                _builder.newLine();
              }
            };
            this._processorTypesBuilder.setBody(it_1, _client);
          };
          JvmOperation _method_44 = this._processorTypesBuilder.toMethod(entity, "getProcessingIdForOperators_", _typeRef_54, _function_75);
          this._processorTypesBuilder.<JvmOperation>operator_add(_members_70, _method_44);
        }
      }
      boolean _isEmpty_15 = processingIdsList.isEmpty();
      boolean _not_15 = (!_isEmpty_15);
      if (_not_15) {
        final boolean hasIds = _hasIds;
        JvmTypeReference _typeRef_55 = this._typeReferenceBuilder.typeRef(String.class);
        final Procedure1<JvmOperation> _function_76 = (JvmOperation it_1) -> {
          EList<JvmFormalParameter> _parameters = it_1.getParameters();
          JvmTypeReference _typeRef_56 = this._typeReferenceBuilder.typeRef(String.class);
          JvmTypeReference _addArrayTypeDimension = this._processorTypesBuilder.addArrayTypeDimension(_typeRef_56);
          JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "moreAttributes", _addArrayTypeDimension);
          this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
          it_1.setVarArgs(true);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              {
                if (hasIds) {
                  _builder.append("if (ids_ != null && !ids_.isEmpty())");
                  _builder.newLine();
                  _builder.append("\t");
                  _builder.append("return null;");
                  _builder.newLine();
                }
              }
              _builder.append("StringBuilder result = getProcessingIdForAttributes_();");
              _builder.newLine();
              {
                boolean _isEmpty = isDefList.isEmpty();
                boolean _not = (!_isEmpty);
                if (_not) {
                  _builder.append("StringBuilder processingIdForNulls = getProcessingIdForNulls_();");
                  _builder.newLine();
                  _builder.append("if (processingIdForNulls != null)");
                  _builder.newLine();
                  _builder.append("\t");
                  _builder.append("result.append(\",\").append(processingIdForNulls);");
                  _builder.newLine();
                }
              }
              {
                boolean _isEmpty_1 = toInitList.isEmpty();
                boolean _not_1 = (!_isEmpty_1);
                if (_not_1) {
                  _builder.append("StringBuilder processingIdForAssociations = getProcessingIdForAssociations_();");
                  _builder.newLine();
                  _builder.append("if (processingIdForAssociations != null)");
                  _builder.newLine();
                  _builder.append("\t");
                  _builder.append("result.append(\",\").append(processingIdForAssociations);");
                  _builder.newLine();
                }
              }
              {
                boolean _hasOperators = PojoJvmModelInferrer.this._processorGeneratorUtils.hasOperators(entity);
                if (_hasOperators) {
                  _builder.append("StringBuilder processingIdForOperators = getProcessingIdForOperators_();");
                  _builder.newLine();
                  _builder.append("if (processingIdForOperators != null)");
                  _builder.newLine();
                  _builder.append("\t");
                  _builder.append("result.append(\",\").append(processingIdForOperators);");
                  _builder.newLine();
                }
              }
              _builder.append("if (moreAttributes != null && moreAttributes.length > 0) {");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("result.append(\",MORE\");");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("for (String moreAttr : moreAttributes)");
              _builder.newLine();
              _builder.append("\t\t");
              _builder.append("result.append(\"@\").append(moreAttr);");
              _builder.newLine();
              _builder.append("}");
              _builder.newLine();
              _builder.append("return result.toString();");
              _builder.newLine();
            }
          };
          this._processorTypesBuilder.setBody(it_1, _client);
        };
        final JvmOperation method_4 = this._processorTypesBuilder.toMethod(entity, "getProcessingId_", _typeRef_55, _function_76);
        EList<JvmMember> _members_71 = it.getMembers();
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_71, method_4);
      }
    };
    acceptor.<JvmGenericType>accept(entityType, _function);
  }
  
  @Inject
  @Extension
  private JvmModelAssociator associator;
  
  public void addAnnotationsX(final JvmAnnotationTarget target, final Iterable<? extends XAnnotation> annotations) {
    if (((Objects.equal(annotations, null) || IterableExtensions.isEmpty(annotations)) || Objects.equal(target, null))) {
      return;
    }
    for (final XAnnotation a : annotations) {
      this.addAnnotationX(target, a);
    }
  }
  
  public void addAnnotationX(final JvmAnnotationTarget target, final XAnnotation annotation) {
    if (((Objects.equal(annotation, null) || Objects.equal(annotation.getAnnotationType(), null)) || Objects.equal(target, null))) {
      return;
    }
    final Set<EObject> associatedElements = this.associator.getJvmElements(annotation);
    boolean _isEmpty = associatedElements.isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      List<EObject> _list = IterableExtensions.<EObject>toList(associatedElements);
      final Consumer<EObject> _function = (EObject assoc) -> {
        this.associator.removeAllAssociation(assoc);
      };
      _list.forEach(_function);
    }
    this._processorTypesBuilder.addAnnotation(target, annotation);
  }
  
  public String toFirstUpper(final String s) {
    if ((Objects.equal(s, null) || (s.length() == 0))) {
      return s;
    }
    char _charAt = s.charAt(0);
    boolean _isUpperCase = Character.isUpperCase(_charAt);
    if (_isUpperCase) {
      return s;
    }
    int _length = s.length();
    boolean _equals = (_length == 1);
    if (_equals) {
      return s.toUpperCase();
    }
    String _substring = s.substring(0, 1);
    String _upperCase = _substring.toUpperCase();
    String _substring_1 = s.substring(1);
    return (_upperCase + _substring_1);
  }
  
  public String _toFirstUpper(final String name) {
    final int l = name.length();
    if ((l == 0)) {
      return name;
    }
    if ((l == 1)) {
      return name.toUpperCase();
    }
    final char c = name.charAt(1);
    boolean _isUpperCase = Character.isUpperCase(c);
    if (_isUpperCase) {
      return name;
    }
    String _substring = name.substring(0, 1);
    String _upperCase = _substring.toUpperCase();
    String _substring_1 = name.substring(1);
    return (_upperCase + _substring_1);
  }
}
