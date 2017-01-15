package org.sqlproc.model.jvmmodel;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.common.types.JvmConstructor;
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
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.JvmAnnotationReferenceBuilder;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypeReferenceBuilder;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.sqlproc.model.jvmmodel.ProcessorGeneratorUtils;
import org.sqlproc.model.jvmmodel.ProcessorTypesBuilder;
import org.sqlproc.model.processorModel.AnnotatedFeature;
import org.sqlproc.model.processorModel.Annotation;
import org.sqlproc.model.processorModel.DaoDirective;
import org.sqlproc.model.processorModel.DaoDirectiveCrud;
import org.sqlproc.model.processorModel.DaoDirectiveParameters;
import org.sqlproc.model.processorModel.DaoDirectiveQuery;
import org.sqlproc.model.processorModel.DaoEntity;
import org.sqlproc.model.processorModel.DaoFunProcDirective;
import org.sqlproc.model.processorModel.Extends;
import org.sqlproc.model.processorModel.Feature;
import org.sqlproc.model.processorModel.FunProcType;
import org.sqlproc.model.processorModel.FunctionCall;
import org.sqlproc.model.processorModel.FunctionCallQuery;
import org.sqlproc.model.processorModel.FunctionQuery;
import org.sqlproc.model.processorModel.Implements;
import org.sqlproc.model.processorModel.PojoAttribute;
import org.sqlproc.model.processorModel.PojoAttributeDirectivePrimaryKey;
import org.sqlproc.model.processorModel.PojoEntity;
import org.sqlproc.model.processorModel.PojoProcedure;
import org.sqlproc.model.processorModel.ProcedureCallQuery;
import org.sqlproc.model.processorModel.ProcedureUpdate;

/**
 * <p>Infers a JVM model from the source model.</p>
 * 
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
@SuppressWarnings("all")
public class DaoJvmModelInferrer extends AbstractModelInferrer {
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
  
  private final String ENGINE_FACTORY = "org.sqlproc.engine.SqlEngineFactory";
  
  private final String SESSION_FACTORY = "org.sqlproc.engine.SqlSessionFactory";
  
  private final String SQL_SESSION = "org.sqlproc.engine.SqlSession";
  
  private final String SQL_CONTROL = "org.sqlproc.engine.SqlControl";
  
  private final String SQL_RUNTIME_EXCEPTION = "org.sqlproc.engine.SqlRuntimeException";
  
  private final String LOGGER = "org.slf4j.Logger";
  
  private final String LOGGER_FACTORY = "org.slf4j.LoggerFactory";
  
  private final String CRUD_ENGINE = "org.sqlproc.engine.SqlCrudEngine";
  
  private final String QUERY_ENGINE = "org.sqlproc.engine.SqlQueryEngine";
  
  private final String PROCEDURE_ENGINE = "org.sqlproc.engine.SqlProcedureEngine";
  
  private final String SQL_ROW_PROCESSOR = "org.sqlproc.engine.SqlRowProcessor";
  
  private final String SET = "java.util.Set";
  
  private final String HASH_SET = "java.util.HashSet";
  
  private final String MAP = "java.util.Map";
  
  private final String HASH_MAP = "java.util.HashMap";
  
  private final String LIST = "java.util.List";
  
  private final String ARRAY_LIST = "java.util.ArrayList";
  
  private final String SQL_STANDARD_CONTROL = "org.sqlproc.engine.impl.SqlStandardControl";
  
  public void inferDaoIfx(final DaoEntity entity, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    final PojoEntity pojo = this._processorGeneratorUtils.getPojo(entity);
    if ((Objects.equal(pojo, null) && (!this._processorGeneratorUtils.isFunctionProcedure(entity)))) {
      InputOutput.<String>println(("Missing POJO for " + entity));
      return;
    }
    QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(entity);
    String _string = _fullyQualifiedName.toString();
    final Procedure1<JvmGenericType> _function = (JvmGenericType it) -> {
    };
    final JvmGenericType entityType = this._processorTypesBuilder.toInterface(entity, _string, _function);
    JvmGenericType _class = null;
    if (pojo!=null) {
      QualifiedName _fullyQualifiedName_1 = null;
      if (pojo!=null) {
        _fullyQualifiedName_1=this._iQualifiedNameProvider.getFullyQualifiedName(pojo);
      }
      _class=this._processorTypesBuilder.toClass(pojo, _fullyQualifiedName_1);
    }
    final JvmGenericType pojoType = _class;
    if ((Objects.equal(pojoType, null) && (!this._processorGeneratorUtils.isFunctionProcedure(entity)))) {
      InputOutput.<String>println(("Missing POJOTYPE for " + entity));
      return;
    }
    final PojoAttribute primaryKey = this._processorGeneratorUtils.getPrimaryKey(pojo);
    PojoAttributeDirectivePrimaryKey _primaryKeyDir = null;
    if (primaryKey!=null) {
      _primaryKeyDir=this._processorGeneratorUtils.getPrimaryKeyDir(primaryKey);
    }
    final PojoAttributeDirectivePrimaryKey pkDir = _primaryKeyDir;
    final String simpleName = entity.getName();
    final Integer sernum = this._processorGeneratorUtils.getSernum(entity);
    final Procedure1<JvmGenericType> _function_1 = (JvmGenericType it) -> {
      String _documentation = this._processorTypesBuilder.getDocumentation(entity);
      this._processorTypesBuilder.setDocumentation(it, _documentation);
      List<Implements> _implements = this._processorGeneratorUtils.getImplements(entity);
      for (final Implements impl : _implements) {
        boolean _isGenerics = this._processorGeneratorUtils.isGenerics(impl);
        if (_isGenerics) {
          JvmParameterizedTypeReference _implements_1 = impl.getImplements();
          JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(pojoType);
          final JvmTypeReference genericType = this._typeReferenceBuilder.typeRef(it, _implements_1, _typeRef);
          EList<JvmTypeReference> _superTypes = it.getSuperTypes();
          this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes, genericType);
        } else {
          EList<JvmTypeReference> _superTypes_1 = it.getSuperTypes();
          JvmParameterizedTypeReference _implements_2 = impl.getImplements();
          JvmTypeReference _cloneWithProxies = this._processorTypesBuilder.cloneWithProxies(_implements_2);
          this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes_1, _cloneWithProxies);
        }
      }
      boolean _notEquals = (!Objects.equal(sernum, null));
      if (_notEquals) {
        EList<JvmTypeReference> _superTypes_2 = it.getSuperTypes();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SERIALIZABLE);
        this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes_2, _typeRef_1);
      }
      Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses = null;
      EList<DaoDirective> _directives = entity.getDirectives();
      for (final DaoDirective dir : _directives) {
        if ((dir instanceof DaoDirectiveCrud)) {
          boolean _equals = Objects.equal(moreResultClasses, null);
          if (_equals) {
            Map<String, Map<String, JvmParameterizedTypeReference>> _moreResultClasses = this._processorGeneratorUtils.getMoreResultClasses(entity);
            moreResultClasses = _moreResultClasses;
          }
          EList<JvmMember> _members = it.getMembers();
          this.inferInsertIfx(entity, ((DaoDirectiveCrud) dir), entityType, simpleName, pojo, pojoType, _members);
          EList<JvmMember> _members_1 = it.getMembers();
          this.inferGetIfx(entity, ((DaoDirectiveCrud) dir), entityType, simpleName, pojo, pojoType, _members_1, moreResultClasses);
          EList<JvmMember> _members_2 = it.getMembers();
          this.inferUpdateIfx(entity, ((DaoDirectiveCrud) dir), entityType, simpleName, pojo, pojoType, _members_2);
          EList<JvmMember> _members_3 = it.getMembers();
          this.inferDeleteIfx(entity, ((DaoDirectiveCrud) dir), entityType, simpleName, pojo, pojoType, _members_3);
        } else {
          if ((dir instanceof DaoDirectiveQuery)) {
            boolean _equals_1 = Objects.equal(moreResultClasses, null);
            if (_equals_1) {
              Map<String, Map<String, JvmParameterizedTypeReference>> _moreResultClasses_1 = this._processorGeneratorUtils.getMoreResultClasses(entity);
              moreResultClasses = _moreResultClasses_1;
            }
            EList<JvmMember> _members_4 = it.getMembers();
            this.inferListIfx(entity, ((DaoDirectiveQuery) dir), entityType, simpleName, pojo, pojoType, _members_4, moreResultClasses);
            EList<JvmMember> _members_5 = it.getMembers();
            this.inferQueryIfx(entity, ((DaoDirectiveQuery) dir), entityType, simpleName, pojo, pojoType, _members_5, moreResultClasses);
            if ((((!Objects.equal(primaryKey, null)) && (!Objects.equal(pkDir.getFromTo(), null))) && (!this._processorGeneratorUtils.toInitAttributes(pojo).isEmpty()))) {
              EList<JvmMember> _members_6 = it.getMembers();
              this.inferListFromToIfx(entity, ((DaoDirectiveQuery) dir), entityType, simpleName, pojo, pojoType, _members_6, moreResultClasses);
            }
            EList<JvmMember> _members_7 = it.getMembers();
            this.inferCountIfx(entity, ((DaoDirectiveQuery) dir), entityType, simpleName, pojo, pojoType, _members_7, moreResultClasses);
          } else {
            if ((dir instanceof DaoFunProcDirective)) {
              FunProcType _type = ((DaoFunProcDirective) dir).getType();
              DaoDirectiveParameters _paramlist = ((DaoFunProcDirective) dir).getParamlist();
              EList<JvmMember> _members_8 = it.getMembers();
              this.inferFunctionProcedureIfx(entity, _type, _paramlist, entityType, simpleName, _members_8);
            }
          }
        }
      }
      if (((!Objects.equal(moreResultClasses, null)) && (!moreResultClasses.isEmpty()))) {
        EList<JvmMember> _members_9 = it.getMembers();
        this.inferMoreResultClassesIfx(entity, entityType, simpleName, pojo, pojoType, _members_9, moreResultClasses);
      }
    };
    acceptor.<JvmGenericType>accept(entityType, _function_1);
  }
  
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
  public void inferDao(final DaoEntity entity, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase, final String implPackage) {
    final PojoEntity pojo = this._processorGeneratorUtils.getPojo(entity);
    if ((Objects.equal(pojo, null) && (!this._processorGeneratorUtils.isFunctionProcedure(entity)))) {
      InputOutput.<String>println(("Missing POJO for " + entity));
      return;
    }
    QualifiedName _daoFullyQualifiedName = this.daoFullyQualifiedName(entity, implPackage);
    final JvmGenericType entityType = this._processorTypesBuilder.toClass(entity, _daoFullyQualifiedName);
    JvmGenericType _xifexpression = null;
    boolean _notEquals = (!Objects.equal(implPackage, null));
    if (_notEquals) {
      QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(entity);
      String _string = _fullyQualifiedName.toString();
      final Procedure1<JvmGenericType> _function = (JvmGenericType it) -> {
      };
      _xifexpression = this._processorTypesBuilder.toInterface(entity, _string, _function);
    }
    final JvmGenericType entityTypeIfx = _xifexpression;
    JvmGenericType _class = null;
    if (pojo!=null) {
      QualifiedName _fullyQualifiedName_1 = null;
      if (pojo!=null) {
        _fullyQualifiedName_1=this._iQualifiedNameProvider.getFullyQualifiedName(pojo);
      }
      _class=this._processorTypesBuilder.toClass(pojo, _fullyQualifiedName_1);
    }
    final JvmGenericType pojoType = _class;
    if ((Objects.equal(pojoType, null) && (!this._processorGeneratorUtils.isFunctionProcedure(entity)))) {
      InputOutput.<String>println(("Missing POJOTYPE for " + entity));
      return;
    }
    final PojoAttribute primaryKey = this._processorGeneratorUtils.getPrimaryKey(pojo);
    PojoAttributeDirectivePrimaryKey _primaryKeyDir = null;
    if (primaryKey!=null) {
      _primaryKeyDir=this._processorGeneratorUtils.getPrimaryKeyDir(primaryKey);
    }
    final PojoAttributeDirectivePrimaryKey pkDir = _primaryKeyDir;
    final String simpleName = entity.getName();
    final Integer sernum = this._processorGeneratorUtils.getSernum(entity);
    final Procedure1<JvmGenericType> _function_1 = (JvmGenericType it) -> {
      String _documentation = this._processorTypesBuilder.getDocumentation(entity);
      this._processorTypesBuilder.setDocumentation(it, _documentation);
      List<Annotation> _standardAnnotations = this._processorGeneratorUtils.standardAnnotations(entity);
      final Function1<Annotation, XAnnotation> _function_2 = (Annotation a) -> {
        return a.getAnnotation();
      };
      List<XAnnotation> _map = ListExtensions.<Annotation, XAnnotation>map(_standardAnnotations, _function_2);
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
          this._processorTypesBuilder.addAnnotation(it, an);
        }
      }
      boolean _notEquals_1 = (!Objects.equal(entityTypeIfx, null));
      if (_notEquals_1) {
        EList<JvmTypeReference> _superTypes_1 = it.getSuperTypes();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(entityTypeIfx);
        this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes_1, _typeRef_1);
      }
      List<Implements> _implements = this._processorGeneratorUtils.getImplements(entity);
      for (final Implements impl : _implements) {
        boolean _isGenerics = this._processorGeneratorUtils.isGenerics(impl);
        if (_isGenerics) {
          JvmParameterizedTypeReference _implements_1 = impl.getImplements();
          final String name = _implements_1.getIdentifier();
          JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
          final JvmTypeReference ref = this._typeReferenceBuilder.typeRef(name, _typeRef_2);
          EList<JvmTypeReference> _superTypes_2 = it.getSuperTypes();
          this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes_2, ref);
        } else {
          EList<JvmTypeReference> _superTypes_3 = it.getSuperTypes();
          JvmParameterizedTypeReference _implements_2 = impl.getImplements();
          JvmTypeReference _cloneWithProxies = this._processorTypesBuilder.cloneWithProxies(_implements_2);
          this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes_3, _cloneWithProxies);
        }
      }
      final Extends ext = this._processorGeneratorUtils.getExtends(entity);
      boolean _notEquals_2 = (!Objects.equal(ext, null));
      if (_notEquals_2) {
        EList<JvmTypeReference> _superTypes_4 = it.getSuperTypes();
        JvmParameterizedTypeReference _extends = ext.getExtends();
        JvmTypeReference _cloneWithProxies_1 = this._processorTypesBuilder.cloneWithProxies(_extends);
        this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes_4, _cloneWithProxies_1);
      }
      JvmParameterizedTypeReference _superType = entity.getSuperType();
      boolean _notEquals_3 = (!Objects.equal(_superType, null));
      if (_notEquals_3) {
        EList<JvmTypeReference> _superTypes_5 = it.getSuperTypes();
        JvmParameterizedTypeReference _superType_1 = entity.getSuperType();
        JvmTypeReference _cloneWithProxies_2 = this._processorTypesBuilder.cloneWithProxies(_superType_1);
        this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes_5, _cloneWithProxies_2);
      }
      boolean _notEquals_4 = (!Objects.equal(sernum, null));
      if (_notEquals_4) {
        EList<JvmTypeReference> _superTypes_6 = it.getSuperTypes();
        JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SERIALIZABLE);
        this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes_6, _typeRef_3);
        EList<JvmMember> _members = it.getMembers();
        JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(long.class);
        final Procedure1<JvmField> _function_3 = (JvmField it_1) -> {
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
        JvmField _field = this._processorTypesBuilder.toField(entity, "serialVersionUID", _typeRef_4, _function_3);
        this._processorTypesBuilder.<JvmField>operator_add(_members, _field);
      }
      EList<JvmMember> _members_1 = it.getMembers();
      JvmTypeReference _typeRef_5 = this._typeReferenceBuilder.typeRef(this.LOGGER);
      final Procedure1<JvmField> _function_4 = (JvmField it_1) -> {
        it_1.setVisibility(JvmVisibility.PROTECTED);
        it_1.setFinal(true);
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
            _builder.append(DaoJvmModelInferrer.this.LOGGER_FACTORY, "");
            _builder.append(".getLogger(getClass())");
          }
        };
        this._processorTypesBuilder.setInitializer(it_1, _client);
      };
      JvmField _field_1 = this._processorTypesBuilder.toField(entity, "logger", _typeRef_5, _function_4);
      this._processorTypesBuilder.<JvmField>operator_add(_members_1, _field_1);
      EList<JvmMember> _members_2 = it.getMembers();
      final Procedure1<JvmConstructor> _function_5 = (JvmConstructor it_1) -> {
        List<Annotation> _constructorAnnotations = this._processorGeneratorUtils.constructorAnnotations(entity);
        final Function1<Annotation, XAnnotation> _function_6 = (Annotation a) -> {
          return a.getAnnotation();
        };
        List<XAnnotation> _map_1 = ListExtensions.<Annotation, XAnnotation>map(_constructorAnnotations, _function_6);
        this._processorTypesBuilder.addAnnotations(it_1, _map_1);
      };
      JvmConstructor _constructor = this._processorTypesBuilder.toConstructor(entity, _function_5);
      this._processorTypesBuilder.<JvmConstructor>operator_add(_members_2, _constructor);
      EList<JvmMember> _members_3 = it.getMembers();
      final Procedure1<JvmConstructor> _function_6 = (JvmConstructor it_1) -> {
        EList<JvmFormalParameter> _parameters = it_1.getParameters();
        JvmTypeReference _typeRef_6 = this._typeReferenceBuilder.typeRef(this.ENGINE_FACTORY);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlEngineFactory", _typeRef_6);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
        List<Annotation> _constructorAnnotations = this._processorGeneratorUtils.constructorAnnotations(entity);
        final Function1<Annotation, XAnnotation> _function_7 = (Annotation a) -> {
          return a.getAnnotation();
        };
        List<XAnnotation> _map_1 = ListExtensions.<Annotation, XAnnotation>map(_constructorAnnotations, _function_7);
        this._processorTypesBuilder.addAnnotations(it_1, _map_1);
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
            _builder.append("this.sqlEngineFactory = sqlEngineFactory;");
            _builder.newLine();
          }
        };
        this._processorTypesBuilder.setBody(it_1, _client);
      };
      JvmConstructor _constructor_1 = this._processorTypesBuilder.toConstructor(entity, _function_6);
      this._processorTypesBuilder.<JvmConstructor>operator_add(_members_3, _constructor_1);
      EList<JvmMember> _members_4 = it.getMembers();
      final Procedure1<JvmConstructor> _function_7 = (JvmConstructor it_1) -> {
        EList<JvmFormalParameter> _parameters = it_1.getParameters();
        JvmTypeReference _typeRef_6 = this._typeReferenceBuilder.typeRef(this.ENGINE_FACTORY);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlEngineFactory", _typeRef_6);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
        EList<JvmFormalParameter> _parameters_1 = it_1.getParameters();
        JvmTypeReference _typeRef_7 = this._typeReferenceBuilder.typeRef(this.SESSION_FACTORY);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlSessionFactory", _typeRef_7);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
        List<Annotation> _constructorAnnotations = this._processorGeneratorUtils.constructorAnnotations(entity);
        final Function1<Annotation, XAnnotation> _function_8 = (Annotation a) -> {
          return a.getAnnotation();
        };
        List<XAnnotation> _map_1 = ListExtensions.<Annotation, XAnnotation>map(_constructorAnnotations, _function_8);
        this._processorTypesBuilder.addAnnotations(it_1, _map_1);
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
            _builder.append("this.sqlEngineFactory = sqlEngineFactory;");
            _builder.newLine();
            _builder.append("this.sqlSessionFactory = sqlSessionFactory;");
            _builder.newLine();
          }
        };
        this._processorTypesBuilder.setBody(it_1, _client);
      };
      JvmConstructor _constructor_2 = this._processorTypesBuilder.toConstructor(entity, _function_7);
      this._processorTypesBuilder.<JvmConstructor>operator_add(_members_4, _constructor_2);
      EList<JvmMember> _members_5 = it.getMembers();
      JvmTypeReference _typeRef_6 = this._typeReferenceBuilder.typeRef(this.ENGINE_FACTORY);
      final Procedure1<JvmField> _function_8 = (JvmField it_1) -> {
        it_1.setVisibility(JvmVisibility.PROTECTED);
      };
      JvmField _field_2 = this._processorTypesBuilder.toField(entity, "sqlEngineFactory", _typeRef_6, _function_8);
      this._processorTypesBuilder.<JvmField>operator_add(_members_5, _field_2);
      EList<JvmMember> _members_6 = it.getMembers();
      JvmTypeReference _typeRef_7 = this._typeReferenceBuilder.typeRef(this.SESSION_FACTORY);
      final Procedure1<JvmField> _function_9 = (JvmField it_1) -> {
        it_1.setVisibility(JvmVisibility.PROTECTED);
      };
      JvmField _field_3 = this._processorTypesBuilder.toField(entity, "sqlSessionFactory", _typeRef_7, _function_9);
      this._processorTypesBuilder.<JvmField>operator_add(_members_6, _field_3);
      Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses = null;
      EList<DaoDirective> _directives = entity.getDirectives();
      for (final DaoDirective dir : _directives) {
        if ((dir instanceof DaoDirectiveCrud)) {
          boolean _equals_1 = Objects.equal(moreResultClasses, null);
          if (_equals_1) {
            Map<String, Map<String, JvmParameterizedTypeReference>> _moreResultClasses = this._processorGeneratorUtils.getMoreResultClasses(entity);
            moreResultClasses = _moreResultClasses;
          }
          EList<JvmMember> _members_7 = it.getMembers();
          this.inferInsert(entity, ((DaoDirectiveCrud) dir), entityType, simpleName, pojo, pojoType, _members_7);
          EList<JvmMember> _members_8 = it.getMembers();
          this.inferGet(entity, ((DaoDirectiveCrud) dir), entityType, simpleName, pojo, pojoType, _members_8, moreResultClasses);
          EList<JvmMember> _members_9 = it.getMembers();
          this.inferUpdate(entity, ((DaoDirectiveCrud) dir), entityType, simpleName, pojo, pojoType, _members_9);
          EList<JvmMember> _members_10 = it.getMembers();
          this.inferDelete(entity, ((DaoDirectiveCrud) dir), entityType, simpleName, pojo, pojoType, _members_10);
        } else {
          if ((dir instanceof DaoDirectiveQuery)) {
            boolean _equals_2 = Objects.equal(moreResultClasses, null);
            if (_equals_2) {
              Map<String, Map<String, JvmParameterizedTypeReference>> _moreResultClasses_1 = this._processorGeneratorUtils.getMoreResultClasses(entity);
              moreResultClasses = _moreResultClasses_1;
            }
            EList<JvmMember> _members_11 = it.getMembers();
            this.inferList(entity, ((DaoDirectiveQuery) dir), entityType, simpleName, pojo, pojoType, _members_11, moreResultClasses);
            EList<JvmMember> _members_12 = it.getMembers();
            this.inferQuery(entity, ((DaoDirectiveQuery) dir), entityType, simpleName, pojo, pojoType, _members_12, moreResultClasses);
            if ((((!Objects.equal(primaryKey, null)) && (!Objects.equal(pkDir.getFromTo(), null))) && (!this._processorGeneratorUtils.toInitAttributes(pojo).isEmpty()))) {
              EList<JvmMember> _members_13 = it.getMembers();
              this.inferListFromTo(entity, ((DaoDirectiveQuery) dir), entityType, simpleName, pojo, pojoType, _members_13, moreResultClasses, primaryKey);
            }
            EList<JvmMember> _members_14 = it.getMembers();
            this.inferCount(entity, ((DaoDirectiveQuery) dir), entityType, simpleName, pojo, pojoType, _members_14, moreResultClasses);
          } else {
            if ((dir instanceof DaoFunProcDirective)) {
              FunProcType _type = ((DaoFunProcDirective) dir).getType();
              DaoDirectiveParameters _paramlist = ((DaoFunProcDirective) dir).getParamlist();
              EList<JvmMember> _members_15 = it.getMembers();
              this.inferFunctionProcedure(entity, _type, _paramlist, entityType, simpleName, _members_15);
            }
          }
        }
      }
      if (((!Objects.equal(moreResultClasses, null)) && (!moreResultClasses.isEmpty()))) {
        EList<JvmMember> _members_16 = it.getMembers();
        this.inferMoreResultClasses(entity, entityType, simpleName, pojo, pojoType, _members_16, moreResultClasses);
      }
      final HashSet<String> procNames = new HashSet<String>();
      EList<AnnotatedFeature> _features = entity.getFeatures();
      final Function1<AnnotatedFeature, Feature> _function_10 = (AnnotatedFeature it_1) -> {
        return it_1.getFeature();
      };
      List<Feature> _map_1 = ListExtensions.<AnnotatedFeature, Feature>map(_features, _function_10);
      Iterable<PojoAttribute> _filter = Iterables.<PojoAttribute>filter(_map_1, PojoAttribute.class);
      for (final PojoAttribute attr : _filter) {
        {
          JvmTypeReference _elvis = null;
          JvmTypeReference _elvis_1 = null;
          JvmParameterizedTypeReference _type_1 = attr.getType();
          if (_type_1 != null) {
            _elvis_1 = _type_1;
          } else {
            XExpression _initExpr = attr.getInitExpr();
            JvmTypeReference _inferredType = null;
            if (_initExpr!=null) {
              _inferredType=this._processorTypesBuilder.inferredType(_initExpr);
            }
            _elvis_1 = _inferredType;
          }
          if (_elvis_1 != null) {
            _elvis = _elvis_1;
          } else {
            JvmTypeReference _typeRef_8 = this._typeReferenceBuilder.typeRef(String.class);
            _elvis = _typeRef_8;
          }
          final JvmTypeReference type = _elvis;
          EList<JvmMember> _members_17 = it.getMembers();
          String _name = attr.getName();
          final Procedure1<JvmField> _function_11 = (JvmField it_1) -> {
            String _documentation_1 = this._processorTypesBuilder.getDocumentation(attr);
            this._processorTypesBuilder.setDocumentation(it_1, _documentation_1);
            List<Annotation> _attributeAnnotations = this._processorGeneratorUtils.attributeAnnotations(attr);
            final Function1<Annotation, XAnnotation> _function_12 = (Annotation a) -> {
              return a.getAnnotation();
            };
            List<XAnnotation> _map_2 = ListExtensions.<Annotation, XAnnotation>map(_attributeAnnotations, _function_12);
            this._processorTypesBuilder.addAnnotations(it_1, _map_2);
            boolean _isStatic = attr.isStatic();
            it_1.setStatic(_isStatic);
            boolean _isStatic_1 = attr.isStatic();
            if (_isStatic_1) {
              it_1.setVisibility(JvmVisibility.PUBLIC);
            }
            XExpression _initExpr_1 = attr.getInitExpr();
            boolean _notEquals_5 = (!Objects.equal(_initExpr_1, null));
            if (_notEquals_5) {
              XExpression _initExpr_2 = attr.getInitExpr();
              this._processorTypesBuilder.setInitializer(it_1, _initExpr_2);
            } else {
              boolean _isList = this._processorGeneratorUtils.isList(attr);
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
              }
            }
          };
          JvmField _field_4 = this._processorTypesBuilder.toField(entity, _name, type, _function_11);
          this._processorTypesBuilder.<JvmField>operator_add(_members_17, _field_4);
          boolean _isStatic = attr.isStatic();
          boolean _not = (!_isStatic);
          if (_not) {
            String _name_1 = attr.getName();
            String _name_2 = attr.getName();
            final Procedure1<JvmOperation> _function_12 = (JvmOperation it_1) -> {
              List<Annotation> _terAnnotations = this._processorGeneratorUtils.getterAnnotations(attr);
              final Function1<Annotation, XAnnotation> _function_13 = (Annotation a) -> {
                return a.getAnnotation();
              };
              List<XAnnotation> _map_2 = ListExtensions.<Annotation, XAnnotation>map(_terAnnotations, _function_13);
              this._processorTypesBuilder.addAnnotations(it_1, _map_2);
            };
            final JvmOperation m1 = this._processorTypesBuilder.toGetter(attr, _name_1, _name_2, type, procNames, _function_12);
            boolean _notEquals_5 = (!Objects.equal(m1, null));
            if (_notEquals_5) {
              EList<JvmMember> _members_18 = it.getMembers();
              this._processorTypesBuilder.<JvmOperation>operator_add(_members_18, m1);
            }
            String _name_3 = attr.getName();
            String _name_4 = attr.getName();
            JvmTypeReference _typeRef_9 = this._typeReferenceBuilder.typeRef(entityType);
            final Procedure1<JvmOperation> _function_13 = (JvmOperation it_1) -> {
              List<Annotation> _setterAnnotations = this._processorGeneratorUtils.setterAnnotations(attr);
              final Function1<Annotation, XAnnotation> _function_14 = (Annotation a) -> {
                return a.getAnnotation();
              };
              List<XAnnotation> _map_2 = ListExtensions.<Annotation, XAnnotation>map(_setterAnnotations, _function_14);
              this._processorTypesBuilder.addAnnotations(it_1, _map_2);
            };
            final JvmOperation m2 = this._processorTypesBuilder.toSetter(attr, _name_3, _name_4, type, _typeRef_9, procNames, _function_13);
            boolean _notEquals_6 = (!Objects.equal(m2, null));
            if (_notEquals_6) {
              EList<JvmMember> _members_19 = it.getMembers();
              this._processorTypesBuilder.<JvmOperation>operator_add(_members_19, m2);
            }
          }
        }
      }
      EList<AnnotatedFeature> _features_1 = entity.getFeatures();
      final Function1<AnnotatedFeature, Feature> _function_11 = (AnnotatedFeature it_1) -> {
        return it_1.getFeature();
      };
      List<Feature> _map_2 = ListExtensions.<AnnotatedFeature, Feature>map(_features_1, _function_11);
      Iterable<PojoProcedure> _filter_1 = Iterables.<PojoProcedure>filter(_map_2, PojoProcedure.class);
      for (final PojoProcedure proc : _filter_1) {
        EList<JvmMember> _members_17 = it.getMembers();
        String _name = proc.getName();
        JvmTypeReference _elvis = null;
        JvmParameterizedTypeReference _type_1 = proc.getType();
        if (_type_1 != null) {
          _elvis = _type_1;
        } else {
          JvmTypeReference _inferredType = this._processorTypesBuilder.inferredType();
          _elvis = _inferredType;
        }
        final Procedure1<JvmOperation> _function_12 = (JvmOperation it_1) -> {
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
        JvmOperation _method = this._processorTypesBuilder.toMethod(proc, _name, _elvis, _function_12);
        this._processorTypesBuilder.<JvmOperation>operator_add(_members_17, _method);
      }
    };
    acceptor.<JvmGenericType>accept(entityType, _function_1);
  }
  
  public void inferInsert(final DaoEntity entity, final DaoDirectiveCrud dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    final PojoEntity parent = this._processorGeneratorUtils.getParentReflectInheritance(pojo);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql insert ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(": \" + ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(" + \" \" + sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append(DaoJvmModelInferrer.this.CRUD_ENGINE, "");
          _builder.append(" sqlInsert");
          String _name = pojo.getName();
          _builder.append(_name, "");
          _builder.append(" = sqlEngineFactory.getCheckedCrudEngine(\"INSERT_");
          String _name_1 = pojo.getName();
          String _dbName = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(_name_1);
          _builder.append(_dbName, "");
          _builder.append("\");");
          {
            boolean _notEquals = (!Objects.equal(parent, null));
            if (_notEquals) {
              _builder.newLineIfNotEmpty();
              _builder.append(DaoJvmModelInferrer.this.CRUD_ENGINE, "");
              _builder.append(" sqlInsert");
              String _name_2 = parent.getName();
              _builder.append(_name_2, "");
              _builder.append(" = sqlEngineFactory.getCheckedCrudEngine(\"INSERT_");
              String _name_3 = parent.getName();
              String _dbName_1 = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(_name_3);
              _builder.append(_dbName_1, "");
              _builder.append("\");");
              _builder.newLineIfNotEmpty();
              _builder.append("int count = sqlInsert");
              String _name_4 = parent.getName();
              _builder.append(_name_4, "");
              _builder.append(".insert(sqlSession, ");
              _builder.append(pojoAttrName, "");
              _builder.append(", sqlControl);");
              _builder.newLineIfNotEmpty();
              _builder.append("if (count > 0) {");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("sqlInsert");
              String _name_5 = pojo.getName();
              _builder.append(_name_5, "\t");
              _builder.append(".insert(sqlSession, ");
              _builder.append(pojoAttrName, "\t");
              _builder.append(", sqlControl);");
              _builder.newLineIfNotEmpty();
              _builder.append("}");
            } else {
              _builder.newLineIfNotEmpty();
              _builder.append("int count = sqlInsert");
              String _name_6 = pojo.getName();
              _builder.append(_name_6, "");
              _builder.append(".insert(sqlSession, ");
              _builder.append(pojoAttrName, "");
              _builder.append(", sqlControl);");
            }
          }
          _builder.newLineIfNotEmpty();
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql insert ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(" result: \" + count + \" \" + ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(");");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append("return (count > 0) ? ");
          _builder.append(pojoAttrName, "");
          _builder.append(" : null;");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "insert", _typeRef, _function);
    members.add(_method);
    JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return insert(sqlSessionFactory.getSqlSession(), ");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "insert", _typeRef_1, _function_1);
    members.add(_method_1);
    JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return insert(sqlSession, ");
          _builder.append(pojoAttrName, "");
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "insert", _typeRef_2, _function_2);
    members.add(_method_2);
    JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return insert(");
          _builder.append(pojoAttrName, "");
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "insert", _typeRef_3, _function_3);
    members.add(_method_3);
  }
  
  public void inferInsertIfx(final DaoEntity entity, final DaoDirectiveCrud dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "insert", _typeRef, _function);
    members.add(_method);
    JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "insert", _typeRef_1, _function_1);
    members.add(_method_1);
    JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "insert", _typeRef_2, _function_2);
    members.add(_method_2);
    JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "insert", _typeRef_3, _function_3);
    members.add(_method_3);
  }
  
  public void inferGet(final DaoEntity entity, final DaoDirectiveCrud dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members, final Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql get: \" + ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(" + \" \" + sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append(DaoJvmModelInferrer.this.CRUD_ENGINE, "");
          _builder.append(" sqlGetEngine");
          String _name = pojo.getName();
          _builder.append(_name, "");
          _builder.append(" = sqlEngineFactory.getCheckedCrudEngine(\"GET_");
          String _name_1 = pojo.getName();
          String _dbName = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(_name_1);
          _builder.append(_dbName, "");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          {
            boolean _isEmpty = moreResultClasses.isEmpty();
            if (_isEmpty) {
              _builder.append("//");
            }
          }
          _builder.append("sqlControl = getMoreResultClasses(");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
          String _name_2 = pojo.getName();
          _builder.append(_name_2, "");
          _builder.append(" ");
          _builder.append(pojoAttrName, "");
          _builder.append("Got = sqlGetEngine");
          String _name_3 = pojo.getName();
          _builder.append(_name_3, "");
          _builder.append(".get(sqlSession, ");
          String _name_4 = pojo.getName();
          _builder.append(_name_4, "");
          _builder.append(".class, ");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql get ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(" result: \" + ");
          _builder.append(pojoAttrName, "\t");
          _builder.append("Got);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append("return ");
          _builder.append(pojoAttrName, "");
          _builder.append("Got;");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "get", _typeRef, _function);
    members.add(_method);
    JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return get(sqlSessionFactory.getSqlSession(), ");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "get", _typeRef_1, _function_1);
    members.add(_method_1);
    JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return get(sqlSession, ");
          _builder.append(pojoAttrName, "");
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "get", _typeRef_2, _function_2);
    members.add(_method_2);
    JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return get(");
          _builder.append(pojoAttrName, "");
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "get", _typeRef_3, _function_3);
    members.add(_method_3);
  }
  
  public void inferGetIfx(final DaoEntity entity, final DaoDirectiveCrud dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members, final Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "get", _typeRef, _function);
    members.add(_method);
    JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "get", _typeRef_1, _function_1);
    members.add(_method_1);
    JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "get", _typeRef_2, _function_2);
    members.add(_method_2);
    JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(pojoType);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "get", _typeRef_3, _function_3);
    members.add(_method_3);
  }
  
  public void inferUpdate(final DaoEntity entity, final DaoDirectiveCrud dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    final PojoEntity parent = this._processorGeneratorUtils.getParentReflectInheritance(pojo);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql update ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(": \" + ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(" + \" \" + sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append(DaoJvmModelInferrer.this.CRUD_ENGINE, "");
          _builder.append(" sqlUpdateEngine");
          String _name = pojo.getName();
          _builder.append(_name, "");
          _builder.append(" = sqlEngineFactory.getCheckedCrudEngine(\"UPDATE_");
          String _name_1 = pojo.getName();
          String _dbName = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(_name_1);
          _builder.append(_dbName, "");
          _builder.append("\");");
          {
            boolean _notEquals = (!Objects.equal(parent, null));
            if (_notEquals) {
              _builder.newLineIfNotEmpty();
              _builder.append(DaoJvmModelInferrer.this.CRUD_ENGINE, "");
              _builder.append(" sqlUpdate");
              String _name_2 = parent.getName();
              _builder.append(_name_2, "");
              _builder.append(" = sqlEngineFactory.getCheckedCrudEngine(\"UPDATE_");
              String _name_3 = parent.getName();
              String _dbName_1 = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(_name_3);
              _builder.append(_dbName_1, "");
              _builder.append("\");");
            }
          }
          _builder.newLineIfNotEmpty();
          _builder.append("int count = sqlUpdateEngine");
          String _name_4 = pojo.getName();
          _builder.append(_name_4, "");
          _builder.append(".update(sqlSession, ");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          {
            boolean _notEquals_1 = (!Objects.equal(parent, null));
            if (_notEquals_1) {
              _builder.newLineIfNotEmpty();
              _builder.append("if (count > 0) {");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("sqlUpdate");
              String _name_5 = parent.getName();
              _builder.append(_name_5, "\t");
              _builder.append(".update(sqlSession, ");
              _builder.append(pojoAttrName, "\t");
              _builder.append(", sqlControl);");
              _builder.newLineIfNotEmpty();
              _builder.append("}");
            }
          }
          final PojoAttribute f = DaoJvmModelInferrer.this._processorGeneratorUtils.getOptLock(pojo);
          {
            boolean _notEquals_2 = (!Objects.equal(f, null));
            if (_notEquals_2) {
              _builder.newLineIfNotEmpty();
              _builder.append("if (count > 0) {");
              _builder.newLine();
              _builder.append("\t");
              _builder.append(pojoAttrName, "\t");
              _builder.append(".set");
              String _name_6 = f.getName();
              String _firstUpper = DaoJvmModelInferrer.this._processorTypesBuilder.toFirstUpper(_name_6);
              _builder.append(_firstUpper, "\t");
              _builder.append("(");
              _builder.append(pojoAttrName, "\t");
              _builder.append(".get");
              String _name_7 = f.getName();
              String _firstUpper_1 = DaoJvmModelInferrer.this._processorTypesBuilder.toFirstUpper(_name_7);
              _builder.append(_firstUpper_1, "\t");
              _builder.append("() + 1);");
              _builder.newLineIfNotEmpty();
              _builder.append("}");
            }
          }
          _builder.newLineIfNotEmpty();
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql update ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(" result count: \" + count);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append("return count;");
          _builder.newLine();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "update", _typeRef, _function);
    members.add(_method);
    JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return update(sqlSessionFactory.getSqlSession(), ");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "update", _typeRef_1, _function_1);
    members.add(_method_1);
    JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return update(sqlSession, ");
          _builder.append(pojoAttrName, "");
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "update", _typeRef_2, _function_2);
    members.add(_method_2);
    JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return update(");
          _builder.append(pojoAttrName, "");
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "update", _typeRef_3, _function_3);
    members.add(_method_3);
  }
  
  public void inferUpdateIfx(final DaoEntity entity, final DaoDirectiveCrud dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "update", _typeRef, _function);
    members.add(_method);
    JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "update", _typeRef_1, _function_1);
    members.add(_method_1);
    JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "update", _typeRef_2, _function_2);
    members.add(_method_2);
    JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "update", _typeRef_3, _function_3);
    members.add(_method_3);
  }
  
  public void inferDelete(final DaoEntity entity, final DaoDirectiveCrud dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    final PojoEntity parent = this._processorGeneratorUtils.getParentReflectInheritance(pojo);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql delete ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(": \" + ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(" + \" \" + sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append(DaoJvmModelInferrer.this.CRUD_ENGINE, "");
          _builder.append(" sqlDeleteEngine");
          String _name = pojo.getName();
          _builder.append(_name, "");
          _builder.append(" = sqlEngineFactory.getCheckedCrudEngine(\"DELETE_");
          String _name_1 = pojo.getName();
          String _dbName = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(_name_1);
          _builder.append(_dbName, "");
          _builder.append("\");");
          {
            boolean _notEquals = (!Objects.equal(parent, null));
            if (_notEquals) {
              _builder.newLineIfNotEmpty();
              _builder.append(DaoJvmModelInferrer.this.CRUD_ENGINE, "");
              _builder.append(" sqlDelete");
              String _name_2 = parent.getName();
              _builder.append(_name_2, "");
              _builder.append(" = sqlEngineFactory.getCheckedCrudEngine(\"DELETE_");
              String _name_3 = parent.getName();
              String _dbName_1 = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(_name_3);
              _builder.append(_dbName_1, "");
              _builder.append("\");");
            }
          }
          _builder.newLineIfNotEmpty();
          _builder.append("int count = sqlDeleteEngine");
          String _name_4 = pojo.getName();
          _builder.append(_name_4, "");
          _builder.append(".delete(sqlSession, ");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          {
            boolean _notEquals_1 = (!Objects.equal(parent, null));
            if (_notEquals_1) {
              _builder.newLineIfNotEmpty();
              _builder.append("if (count > 0) {");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("sqlDelete");
              String _name_5 = parent.getName();
              _builder.append(_name_5, "\t");
              _builder.append(".delete(sqlSession, ");
              _builder.append(pojoAttrName, "\t");
              _builder.append(", sqlControl);");
              _builder.newLineIfNotEmpty();
              _builder.append("}");
            }
          }
          final PojoAttribute f = DaoJvmModelInferrer.this._processorGeneratorUtils.getOptLock(pojo);
          {
            boolean _notEquals_2 = (!Objects.equal(f, null));
            if (_notEquals_2) {
              _builder.newLineIfNotEmpty();
              _builder.append("if (count > 0) {");
              _builder.newLine();
              _builder.append("\t");
              _builder.append(pojoAttrName, "\t");
              _builder.append(".set");
              String _name_6 = f.getName();
              String _firstUpper = DaoJvmModelInferrer.this._processorTypesBuilder.toFirstUpper(_name_6);
              _builder.append(_firstUpper, "\t");
              _builder.append("(");
              _builder.append(pojoAttrName, "\t");
              _builder.append(".get");
              String _name_7 = f.getName();
              String _firstUpper_1 = DaoJvmModelInferrer.this._processorTypesBuilder.toFirstUpper(_name_7);
              _builder.append(_firstUpper_1, "\t");
              _builder.append("() + 1);");
              _builder.newLineIfNotEmpty();
              _builder.append("}");
            }
          }
          _builder.newLineIfNotEmpty();
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql delete ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(" result count: \" + count);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append("return count;");
          _builder.newLine();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "delete", _typeRef, _function);
    members.add(_method);
    JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return delete(sqlSessionFactory.getSqlSession(), ");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "delete", _typeRef_1, _function_1);
    members.add(_method_1);
    JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return delete(sqlSession, ");
          _builder.append(pojoAttrName, "");
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "delete", _typeRef_2, _function_2);
    members.add(_method_2);
    JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return delete(");
          _builder.append(pojoAttrName, "");
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "delete", _typeRef_3, _function_3);
    members.add(_method_3);
  }
  
  public void inferDeleteIfx(final DaoEntity entity, final DaoDirectiveCrud dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "delete", _typeRef, _function);
    members.add(_method);
    JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "delete", _typeRef_1, _function_1);
    members.add(_method_1);
    JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "delete", _typeRef_2, _function_2);
    members.add(_method_2);
    JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "delete", _typeRef_3, _function_3);
    members.add(_method_3);
  }
  
  public void inferList(final DaoEntity entity, final DaoDirectiveQuery dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members, final Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(pojoType);
    final JvmTypeReference listType = this._typeReferenceBuilder.typeRef(List.class, _typeRef);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql list ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(": \" + ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(" + \" \" + sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append(DaoJvmModelInferrer.this.QUERY_ENGINE, "");
          _builder.append(" sqlEngine");
          String _name = pojo.getName();
          _builder.append(_name, "");
          _builder.append(" = sqlEngineFactory.getCheckedQueryEngine(\"SELECT_");
          String _name_1 = pojo.getName();
          String _dbName = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(_name_1);
          _builder.append(_dbName, "");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          {
            boolean _isEmpty = moreResultClasses.isEmpty();
            if (_isEmpty) {
              _builder.append("//");
            }
          }
          _builder.append("sqlControl = getMoreResultClasses(");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("List<");
          String _name_2 = pojo.getName();
          _builder.append(_name_2, "");
          _builder.append("> ");
          _builder.append(pojoAttrName, "");
          _builder.append("List = sqlEngine");
          String _name_3 = pojo.getName();
          _builder.append(_name_3, "");
          _builder.append(".query(sqlSession, ");
          String _name_4 = pojo.getName();
          _builder.append(_name_4, "");
          _builder.append(".class, ");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql list ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(" size: \" + ((");
          _builder.append(pojoAttrName, "\t");
          _builder.append("List != null) ? ");
          _builder.append(pojoAttrName, "\t");
          _builder.append("List.size() : \"null\"));");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append("return ");
          _builder.append(pojoAttrName, "");
          _builder.append("List;");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "list", listType, _function);
    members.add(_method);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return list(sqlSessionFactory.getSqlSession(), ");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "list", listType, _function_1);
    members.add(_method_1);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return list(sqlSession, ");
          _builder.append(pojoAttrName, "");
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "list", listType, _function_2);
    members.add(_method_2);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return list(");
          _builder.append(pojoAttrName, "");
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "list", listType, _function_3);
    members.add(_method_3);
  }
  
  public void inferListIfx(final DaoEntity entity, final DaoDirectiveQuery dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members, final Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(pojoType);
    final JvmTypeReference listType = this._typeReferenceBuilder.typeRef(List.class, _typeRef);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "list", listType, _function);
    members.add(_method);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "list", listType, _function_1);
    members.add(_method_1);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "list", listType, _function_2);
    members.add(_method_2);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "list", listType, _function_3);
    members.add(_method_3);
  }
  
  public void inferListFromTo(final DaoEntity entity, final DaoDirectiveQuery dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members, final Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses, final PojoAttribute primaryKey) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(pojoType);
    final JvmTypeReference listType = this._typeReferenceBuilder.typeRef(List.class, _typeRef);
    final PojoAttributeDirectivePrimaryKey pkDir = this._processorGeneratorUtils.getPrimaryKeyDir(primaryKey);
    String _xifexpression = null;
    String _type = pkDir.getType();
    boolean _notEquals = (!Objects.equal(_type, null));
    if (_notEquals) {
      _xifexpression = pkDir.getType();
    } else {
      JvmTypeReference _elvis = null;
      JvmTypeReference _elvis_1 = null;
      JvmParameterizedTypeReference _type_1 = primaryKey.getType();
      if (_type_1 != null) {
        _elvis_1 = _type_1;
      } else {
        XExpression _initExpr = primaryKey.getInitExpr();
        JvmTypeReference _inferredType = null;
        if (_initExpr!=null) {
          _inferredType=this._processorTypesBuilder.inferredType(_initExpr);
        }
        _elvis_1 = _inferredType;
      }
      if (_elvis_1 != null) {
        _elvis = _elvis_1;
      } else {
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(String.class);
        _elvis = _typeRef_1;
      }
      _xifexpression = _elvis.getQualifiedName();
    }
    final String pkType = _xifexpression;
    String _xifexpression_1 = null;
    String _name_1 = pkDir.getName();
    boolean _notEquals_1 = (!Objects.equal(_name_1, null));
    if (_notEquals_1) {
      String _name_2 = pkDir.getName();
      String _firstUpper = this._processorTypesBuilder.toFirstUpper(_name_2);
      String _plus = (".get" + _firstUpper);
      _xifexpression_1 = (_plus + "()");
    } else {
      _xifexpression_1 = "";
    }
    final String pkGetDep = _xifexpression_1;
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("if (sqlControl == null || sqlControl.getFirstResult() == null || sqlControl.getMaxResults() == null || ");
          _builder.append(pojoAttrName, "");
          _builder.append(" == null)");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("return list(sqlSession, ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.newLine();
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql list ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(": \" + ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(" + \" \" + sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append(DaoJvmModelInferrer.this.QUERY_ENGINE, "");
          _builder.append(" sqlEngine");
          String _name = pojo.getName();
          _builder.append(_name, "");
          _builder.append(" = sqlEngineFactory.getCheckedQueryEngine(\"SELECT_");
          String _name_1 = pojo.getName();
          String _dbName = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(_name_1);
          _builder.append(_dbName, "");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          {
            boolean _isEmpty = moreResultClasses.isEmpty();
            if (_isEmpty) {
              _builder.append("//");
            }
          }
          _builder.append("sqlControl = getMoreResultClasses(");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append(pojoAttrName, "");
          _builder.append(".setOnlyIds_(true);");
          _builder.newLineIfNotEmpty();
          _builder.append(DaoJvmModelInferrer.this.SET, "");
          _builder.append("<String> initAssociations = ");
          _builder.append(pojoAttrName, "");
          _builder.append(".getInitAssociations_();");
          _builder.newLineIfNotEmpty();
          _builder.append(pojoAttrName, "");
          _builder.append(".setInitAssociations_(new ");
          _builder.append(DaoJvmModelInferrer.this.HASH_SET, "");
          _builder.append("<String>());");
          _builder.newLineIfNotEmpty();
          _builder.append("final ");
          _builder.append(DaoJvmModelInferrer.this.LIST, "");
          _builder.append("<");
          String _wrapperPrimitive = DaoJvmModelInferrer.this._processorGeneratorUtils.wrapperPrimitive(pkType);
          _builder.append(_wrapperPrimitive, "");
          _builder.append("> ids_ = sqlEngine");
          String _name_2 = pojo.getName();
          _builder.append(_name_2, "");
          _builder.append(".query(sqlSession, ");
          _builder.append(pkType, "");
          _builder.append(".class, ");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append(pojoAttrName, "");
          _builder.append(".setInitAssociations_(initAssociations);");
          _builder.newLineIfNotEmpty();
          _builder.newLine();
          _builder.append("List<");
          String _name_3 = pojo.getName();
          _builder.append(_name_3, "");
          _builder.append("> ");
          _builder.append(pojoAttrName, "");
          _builder.append("List = new ");
          _builder.append(DaoJvmModelInferrer.this.ARRAY_LIST, "");
          _builder.append("<");
          String _name_4 = pojo.getName();
          _builder.append(_name_4, "");
          _builder.append(">();");
          _builder.newLineIfNotEmpty();
          _builder.append("if (!ids_.isEmpty()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append(DaoJvmModelInferrer.this.SQL_STANDARD_CONTROL, "\t");
          _builder.append(" sqlc = new ");
          _builder.append(DaoJvmModelInferrer.this.SQL_STANDARD_CONTROL, "\t");
          _builder.append("(sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("sqlc.setFirstResult(0);");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("sqlc.setMaxResults(0);");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("sqlc.setOrder(null);");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("final ");
          _builder.append(Map.class, "\t");
          _builder.append("<");
          String _wrapperPrimitive_1 = DaoJvmModelInferrer.this._processorGeneratorUtils.wrapperPrimitive(pkType);
          _builder.append(_wrapperPrimitive_1, "\t");
          _builder.append(", ");
          String _name_5 = pojo.getName();
          _builder.append(_name_5, "\t");
          _builder.append("> map = new ");
          _builder.append(DaoJvmModelInferrer.this.HASH_MAP, "\t");
          _builder.append("<");
          String _wrapperPrimitive_2 = DaoJvmModelInferrer.this._processorGeneratorUtils.wrapperPrimitive(pkType);
          _builder.append(_wrapperPrimitive_2, "\t");
          _builder.append(", ");
          String _name_6 = pojo.getName();
          _builder.append(_name_6, "\t");
          _builder.append(">();");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("final SqlRowProcessor<");
          String _name_7 = pojo.getName();
          _builder.append(_name_7, "\t");
          _builder.append("> sqlRowProcessor = new SqlRowProcessor<");
          String _name_8 = pojo.getName();
          _builder.append(_name_8, "\t");
          _builder.append(">() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("@Override");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("public boolean processRow(");
          String _name_9 = pojo.getName();
          _builder.append(_name_9, "\t\t");
          _builder.append(" result, int rownum) throws ");
          _builder.append(DaoJvmModelInferrer.this.SQL_RUNTIME_EXCEPTION, "\t\t");
          _builder.append(" {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          _builder.append("map.put(result.get");
          String _name_10 = primaryKey.getName();
          String _firstUpper = DaoJvmModelInferrer.this._processorTypesBuilder.toFirstUpper(_name_10);
          _builder.append(_firstUpper, "\t\t\t");
          _builder.append("()");
          _builder.append(pkGetDep, "\t\t\t");
          _builder.append(", result);");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          _builder.append("return true;");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("}");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("};");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("sqlEngine");
          String _name_11 = pojo.getName();
          _builder.append(_name_11, "\t");
          _builder.append(".query(sqlSession, ");
          String _name_12 = pojo.getName();
          _builder.append(_name_12, "\t");
          _builder.append(".class, new ");
          String _name_13 = pojo.getName();
          _builder.append(_name_13, "\t");
          _builder.append("()._setIds_(ids_), sqlc, sqlRowProcessor);");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("for (");
          _builder.append(pkType, "\t");
          _builder.append(" id : ids_)");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append(pojoAttrName, "\t\t");
          _builder.append("List.add(map.get(id));");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql list ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(" size: \" + ((");
          _builder.append(pojoAttrName, "\t");
          _builder.append("List != null) ? ");
          _builder.append(pojoAttrName, "\t");
          _builder.append("List.size() : \"null\"));");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append("return ");
          _builder.append(pojoAttrName, "");
          _builder.append("List;");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "listFromTo", listType, _function);
    members.add(_method);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return listFromTo(sqlSessionFactory.getSqlSession(), ");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "listFromTo", listType, _function_1);
    members.add(_method_1);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return listFromTo(sqlSession, ");
          _builder.append(pojoAttrName, "");
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "listFromTo", listType, _function_2);
    members.add(_method_2);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return listFromTo(");
          _builder.append(pojoAttrName, "");
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "listFromTo", listType, _function_3);
    members.add(_method_3);
  }
  
  public void inferListFromToIfx(final DaoEntity entity, final DaoDirectiveQuery dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members, final Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(pojoType);
    final JvmTypeReference listType = this._typeReferenceBuilder.typeRef(List.class, _typeRef);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "listFromTo", listType, _function);
    members.add(_method);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "listFromTo", listType, _function_1);
    members.add(_method_1);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "listFromTo", listType, _function_2);
    members.add(_method_2);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "listFromTo", listType, _function_3);
    members.add(_method_3);
  }
  
  public void inferQuery(final DaoEntity entity, final DaoDirectiveQuery dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members, final Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(pojoType);
    final JvmTypeReference srpType = this._typeReferenceBuilder.typeRef(this.SQL_ROW_PROCESSOR, _typeRef);
    JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      EList<JvmFormalParameter> _parameters_3 = it.getParameters();
      JvmFormalParameter _parameter_3 = this._processorTypesBuilder.toParameter(entity, "sqlRowProcessor", srpType);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_3, _parameter_3);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql query ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(": \" + ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(" + \" \" + sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append(DaoJvmModelInferrer.this.QUERY_ENGINE, "");
          _builder.append(" sqlEngine");
          String _name = pojo.getName();
          _builder.append(_name, "");
          _builder.append(" = sqlEngineFactory.getCheckedQueryEngine(\"SELECT_");
          String _name_1 = pojo.getName();
          String _dbName = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(_name_1);
          _builder.append(_dbName, "");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          {
            boolean _isEmpty = moreResultClasses.isEmpty();
            if (_isEmpty) {
              _builder.append("//");
            }
          }
          _builder.append("sqlControl = getMoreResultClasses(");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("int rownums = sqlEngine");
          String _name_2 = pojo.getName();
          _builder.append(_name_2, "");
          _builder.append(".query(sqlSession, ");
          String _name_3 = pojo.getName();
          _builder.append(_name_3, "");
          _builder.append(".class, ");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl, sqlRowProcessor);");
          _builder.newLineIfNotEmpty();
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql query ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(" size: \" + rownums);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append("return rownums;");
          _builder.newLine();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "query", _typeRef_1, _function);
    members.add(_method);
    JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlRowProcessor", srpType);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return query(sqlSessionFactory.getSqlSession(), ");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl, sqlRowProcessor);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "query", _typeRef_2, _function_1);
    members.add(_method_1);
    JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_5 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_5);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlRowProcessor", srpType);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return query(sqlSession, ");
          _builder.append(pojoAttrName, "");
          _builder.append(", null, sqlRowProcessor);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "query", _typeRef_3, _function_2);
    members.add(_method_2);
    JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_5 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_5);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlRowProcessor", srpType);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return query(");
          _builder.append(pojoAttrName, "");
          _builder.append(", null, sqlRowProcessor);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "query", _typeRef_4, _function_3);
    members.add(_method_3);
  }
  
  public void inferQueryIfx(final DaoEntity entity, final DaoDirectiveQuery dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members, final Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(pojoType);
    final JvmTypeReference srpType = this._typeReferenceBuilder.typeRef(this.SQL_ROW_PROCESSOR, _typeRef);
    JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      EList<JvmFormalParameter> _parameters_3 = it.getParameters();
      JvmFormalParameter _parameter_3 = this._processorTypesBuilder.toParameter(entity, "sqlRowProcessor", srpType);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_3, _parameter_3);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "query", _typeRef_1, _function);
    members.add(_method);
    JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlRowProcessor", srpType);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "query", _typeRef_2, _function_1);
    members.add(_method_1);
    JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_5 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_5);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlRowProcessor", srpType);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "query", _typeRef_3, _function_2);
    members.add(_method_2);
    JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_5 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_5);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlRowProcessor", srpType);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "query", _typeRef_4, _function_3);
    members.add(_method_3);
  }
  
  public void inferCount(final DaoEntity entity, final DaoDirectiveQuery dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members, final Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"count ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(": \" + ");
          _builder.append(pojoAttrName, "\t");
          _builder.append(" + \" \" + sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append(DaoJvmModelInferrer.this.QUERY_ENGINE, "");
          _builder.append(" sqlEngine");
          String _name = pojo.getName();
          _builder.append(_name, "");
          _builder.append(" = sqlEngineFactory.getCheckedQueryEngine(\"SELECT_");
          String _name_1 = pojo.getName();
          String _dbName = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(_name_1);
          _builder.append(_dbName, "");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          {
            boolean _isEmpty = moreResultClasses.isEmpty();
            if (_isEmpty) {
              _builder.append("//");
            }
          }
          _builder.append("sqlControl = getMoreResultClasses(");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("int count = sqlEngine");
          String _name_2 = pojo.getName();
          _builder.append(_name_2, "");
          _builder.append(".queryCount(sqlSession, ");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"count: \" + count);");
          _builder.newLine();
          _builder.append("}");
          _builder.newLine();
          _builder.append("return count;");
          _builder.newLine();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "count", _typeRef, _function);
    members.add(_method);
    JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return count(sqlSessionFactory.getSqlSession(), ");
          _builder.append(pojoAttrName, "");
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "count", _typeRef_1, _function_1);
    members.add(_method_1);
    JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return count(sqlSession, ");
          _builder.append(pojoAttrName, "");
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "count", _typeRef_2, _function_2);
    members.add(_method_2);
    JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return count(");
          _builder.append(pojoAttrName, "");
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "count", _typeRef_3, _function_3);
    members.add(_method_3);
  }
  
  public void inferCountIfx(final DaoEntity entity, final DaoDirectiveQuery dir, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members, final Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "count", _typeRef, _function);
    members.add(_method);
    JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "count", _typeRef_1, _function_1);
    members.add(_method_1);
    JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "count", _typeRef_2, _function_2);
    members.add(_method_2);
    JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_4);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "count", _typeRef_3, _function_3);
    members.add(_method_3);
  }
  
  public void inferMoreResultClasses(final DaoEntity entity, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members, final Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("if (sqlControl != null && sqlControl.getMoreResultClasses() != null)");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("return sqlControl;");
          _builder.newLine();
          _builder.append(DaoJvmModelInferrer.this.MAP, "");
          _builder.append("<String, Class<?>> moreResultClasses = null;");
          _builder.newLineIfNotEmpty();
          {
            Set<Map.Entry<String, Map<String, JvmParameterizedTypeReference>>> _entrySet = moreResultClasses.entrySet();
            for(final Map.Entry<String, Map<String, JvmParameterizedTypeReference>> f : _entrySet) {
              _builder.append("if (");
              _builder.append(pojoAttrName, "");
              _builder.append(" != null && ");
              _builder.append(pojoAttrName, "");
              _builder.append(".toInit_(");
              String _name = pojo.getName();
              _builder.append(_name, "");
              _builder.append(".Association.");
              String _key = f.getKey();
              _builder.append(_key, "");
              _builder.append(".name())) {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("if (moreResultClasses == null)");
              _builder.newLine();
              _builder.append("\t\t");
              _builder.append("moreResultClasses = new ");
              _builder.append(DaoJvmModelInferrer.this.HASH_MAP, "\t\t");
              _builder.append("<String, Class<?>>();");
              _builder.newLineIfNotEmpty();
              {
                Map<String, JvmParameterizedTypeReference> _value = f.getValue();
                Set<Map.Entry<String, JvmParameterizedTypeReference>> _entrySet_1 = _value.entrySet();
                for(final Map.Entry<String, JvmParameterizedTypeReference> a : _entrySet_1) {
                  _builder.append("\t");
                  _builder.append("moreResultClasses.put(\"");
                  String _key_1 = a.getKey();
                  _builder.append(_key_1, "\t");
                  _builder.append("\", ");
                  JvmParameterizedTypeReference _value_1 = a.getValue();
                  _builder.append(_value_1, "\t");
                  _builder.append(".class);");
                  _builder.newLineIfNotEmpty();
                }
              }
              _builder.append("}");
              _builder.newLine();
            }
          }
          _builder.append("if (moreResultClasses != null) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("sqlControl = new ");
          _builder.append(DaoJvmModelInferrer.this.SQL_STANDARD_CONTROL, "\t");
          _builder.append("(sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("((");
          _builder.append(DaoJvmModelInferrer.this.SQL_STANDARD_CONTROL, "\t");
          _builder.append(") sqlControl).setMoreResultClasses(moreResultClasses);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append("return sqlControl;");
          _builder.newLine();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "getMoreResultClasses", _typeRef, _function);
    members.add(_method);
  }
  
  public void inferMoreResultClassesIfx(final DaoEntity entity, final JvmGenericType entityType, final String simpleName, final PojoEntity pojo, final JvmGenericType pojoType, final List<JvmMember> members, final Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses) {
    String _name = pojo.getName();
    final String pojoAttrName = StringExtensions.toFirstLower(_name);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(pojoType);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, pojoAttrName, _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "getMoreResultClasses", _typeRef, _function);
    members.add(_method);
  }
  
  protected void _inferFunctionProcedure(final DaoEntity entity, final FunctionCallQuery type, final DaoDirectiveParameters params, final JvmGenericType entityType, final String simpleName, final List<JvmMember> members) {
    JvmParameterizedTypeReference _out = params.getOut();
    JvmType _type = _out.getType();
    JvmParameterizedTypeReference _out_1 = params.getOut();
    EList<JvmTypeReference> _arguments = _out_1.getArguments();
    final JvmTypeReference listType = this._typeReferenceBuilder.typeRef(_type, ((JvmTypeReference[])Conversions.unwrapArray(_arguments, JvmTypeReference.class)));
    final String fname = this._processorGeneratorUtils.getFunProcName(entity);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql ");
          _builder.append(fname, "\t");
          _builder.append(": \" + ");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(" + \" \" ", "\t");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "\t");
            }
          }
          _builder.append(" + \" \" + sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append(DaoJvmModelInferrer.this.PROCEDURE_ENGINE, "");
          _builder.append(" sqlFun");
          String _name = entity.getName();
          _builder.append(_name, "");
          _builder.append(" = sqlEngineFactory.getCheckedProcedureEngine(\"FUN_");
          String _dbName = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(fname);
          _builder.append(_dbName, "");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          JvmParameterizedTypeReference _out = params.getOut();
          String _simpleName_1 = DaoJvmModelInferrer.this._processorGeneratorUtils.getSimpleName(_out);
          _builder.append(_simpleName_1, "");
          _builder.append(" list = sqlFun");
          String _name_1 = entity.getName();
          _builder.append(_name_1, "");
          _builder.append(".callQuery(sqlSession, ");
          JvmParameterizedTypeReference _out_1 = params.getOut();
          EList<JvmTypeReference> _arguments = _out_1.getArguments();
          JvmTypeReference _head = IterableExtensions.<JvmTypeReference>head(_arguments);
          String _simpleName_2 = _head.getSimpleName();
          _builder.append(_simpleName_2, "");
          _builder.append(".class, ");
          {
            EList<JvmParameterizedTypeReference> _ins_1 = params.getIns();
            boolean _hasElements_1 = false;
            for(final JvmParameterizedTypeReference in_1 : _ins_1) {
              if (!_hasElements_1) {
                _hasElements_1 = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName_3 = in_1.getSimpleName();
              String _firstLower_1 = StringExtensions.toFirstLower(_simpleName_3);
              _builder.append(_firstLower_1, "");
            }
          }
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql ");
          _builder.append(fname, "\t");
          _builder.append(" result: \" + list);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append("return list;");
          _builder.newLine();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, fname, listType, _function);
    members.add(_method);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return ");
          _builder.append(fname, "");
          _builder.append("(sqlSessionFactory.getSqlSession(), ");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "");
            }
          }
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, fname, listType, _function_1);
    members.add(_method_1);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return ");
          _builder.append(fname, "");
          _builder.append("(sqlSession, ");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "");
            }
          }
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, fname, listType, _function_2);
    members.add(_method_2);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return ");
          _builder.append(fname, "");
          _builder.append("(");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "");
            }
          }
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, fname, listType, _function_3);
    members.add(_method_3);
  }
  
  protected void _inferFunctionProcedureIfx(final DaoEntity entity, final FunctionCallQuery type, final DaoDirectiveParameters params, final JvmGenericType entityType, final String simpleName, final List<JvmMember> members) {
    JvmParameterizedTypeReference _out = params.getOut();
    JvmType _type = _out.getType();
    JvmParameterizedTypeReference _out_1 = params.getOut();
    EList<JvmTypeReference> _arguments = _out_1.getArguments();
    final JvmTypeReference listType = this._typeReferenceBuilder.typeRef(_type, ((JvmTypeReference[])Conversions.unwrapArray(_arguments, JvmTypeReference.class)));
    final String fname = this._processorGeneratorUtils.getFunProcName(entity);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, fname, listType, _function);
    members.add(_method);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, fname, listType, _function_1);
    members.add(_method_1);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, fname, listType, _function_2);
    members.add(_method_2);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, fname, listType, _function_3);
    members.add(_method_3);
  }
  
  protected void _inferFunctionProcedure(final DaoEntity entity, final ProcedureCallQuery type, final DaoDirectiveParameters params, final JvmGenericType entityType, final String simpleName, final List<JvmMember> members) {
    JvmParameterizedTypeReference _out = params.getOut();
    JvmType _type = _out.getType();
    JvmParameterizedTypeReference _out_1 = params.getOut();
    EList<JvmTypeReference> _arguments = _out_1.getArguments();
    final JvmTypeReference listType = this._typeReferenceBuilder.typeRef(_type, ((JvmTypeReference[])Conversions.unwrapArray(_arguments, JvmTypeReference.class)));
    final String fname = this._processorGeneratorUtils.getFunProcName(entity);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql ");
          _builder.append(fname, "\t");
          _builder.append(": \" + ");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(" + \" \" ", "\t");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "\t");
            }
          }
          _builder.append(" + \" \" + sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append(DaoJvmModelInferrer.this.PROCEDURE_ENGINE, "");
          _builder.append(" sqlProc");
          String _name = entity.getName();
          _builder.append(_name, "");
          _builder.append(" = sqlEngineFactory.getCheckedProcedureEngine(\"PROC_");
          String _dbName = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(fname);
          _builder.append(_dbName, "");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          JvmParameterizedTypeReference _out = params.getOut();
          String _simpleName_1 = DaoJvmModelInferrer.this._processorGeneratorUtils.getSimpleName(_out);
          _builder.append(_simpleName_1, "");
          _builder.append(" list = sqlProc");
          String _name_1 = entity.getName();
          _builder.append(_name_1, "");
          _builder.append(".callQuery(sqlSession, ");
          JvmParameterizedTypeReference _out_1 = params.getOut();
          EList<JvmTypeReference> _arguments = _out_1.getArguments();
          JvmTypeReference _head = IterableExtensions.<JvmTypeReference>head(_arguments);
          String _simpleName_2 = _head.getSimpleName();
          _builder.append(_simpleName_2, "");
          _builder.append(".class, ");
          {
            EList<JvmParameterizedTypeReference> _ins_1 = params.getIns();
            boolean _hasElements_1 = false;
            for(final JvmParameterizedTypeReference in_1 : _ins_1) {
              if (!_hasElements_1) {
                _hasElements_1 = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName_3 = in_1.getSimpleName();
              String _firstLower_1 = StringExtensions.toFirstLower(_simpleName_3);
              _builder.append(_firstLower_1, "");
            }
          }
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql ");
          _builder.append(fname, "\t");
          _builder.append(" result: \" + list);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append("return list;");
          _builder.newLine();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, fname, listType, _function);
    members.add(_method);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return ");
          _builder.append(fname, "");
          _builder.append("(sqlSessionFactory.getSqlSession(), ");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "");
            }
          }
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, fname, listType, _function_1);
    members.add(_method_1);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return ");
          _builder.append(fname, "");
          _builder.append("(sqlSession, ");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "");
            }
          }
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, fname, listType, _function_2);
    members.add(_method_2);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return ");
          _builder.append(fname, "");
          _builder.append("(");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "");
            }
          }
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, fname, listType, _function_3);
    members.add(_method_3);
  }
  
  protected void _inferFunctionProcedureIfx(final DaoEntity entity, final ProcedureCallQuery type, final DaoDirectiveParameters params, final JvmGenericType entityType, final String simpleName, final List<JvmMember> members) {
    JvmParameterizedTypeReference _out = params.getOut();
    JvmType _type = _out.getType();
    JvmParameterizedTypeReference _out_1 = params.getOut();
    EList<JvmTypeReference> _arguments = _out_1.getArguments();
    final JvmTypeReference listType = this._typeReferenceBuilder.typeRef(_type, ((JvmTypeReference[])Conversions.unwrapArray(_arguments, JvmTypeReference.class)));
    final String fname = this._processorGeneratorUtils.getFunProcName(entity);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, fname, listType, _function);
    members.add(_method);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, fname, listType, _function_1);
    members.add(_method_1);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, fname, listType, _function_2);
    members.add(_method_2);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, fname, listType, _function_3);
    members.add(_method_3);
  }
  
  protected void _inferFunctionProcedure(final DaoEntity entity, final FunctionCall type, final DaoDirectiveParameters params, final JvmGenericType entityType, final String simpleName, final List<JvmMember> members) {
    JvmParameterizedTypeReference _out = params.getOut();
    JvmType _type = _out.getType();
    JvmParameterizedTypeReference _out_1 = params.getOut();
    EList<JvmTypeReference> _arguments = _out_1.getArguments();
    final JvmTypeReference outType = this._typeReferenceBuilder.typeRef(_type, ((JvmTypeReference[])Conversions.unwrapArray(_arguments, JvmTypeReference.class)));
    final String fname = this._processorGeneratorUtils.getFunProcName(entity);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql ");
          _builder.append(fname, "\t");
          _builder.append(": \" + ");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(" + \" \" ", "\t");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "\t");
            }
          }
          _builder.append(" + \" \" + sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append(DaoJvmModelInferrer.this.PROCEDURE_ENGINE, "");
          _builder.append(" sqlFun");
          String _name = entity.getName();
          _builder.append(_name, "");
          _builder.append(" = sqlEngineFactory.getCheckedProcedureEngine(\"FUN_");
          String _dbName = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(fname);
          _builder.append(_dbName, "");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          _builder.append("Object result = sqlFun");
          String _name_1 = entity.getName();
          _builder.append(_name_1, "");
          _builder.append(".callFunction(sqlSession, ");
          {
            EList<JvmParameterizedTypeReference> _ins_1 = params.getIns();
            boolean _hasElements_1 = false;
            for(final JvmParameterizedTypeReference in_1 : _ins_1) {
              if (!_hasElements_1) {
                _hasElements_1 = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName_1 = in_1.getSimpleName();
              String _firstLower_1 = StringExtensions.toFirstLower(_simpleName_1);
              _builder.append(_firstLower_1, "");
            }
          }
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql ");
          _builder.append(fname, "\t");
          _builder.append(" result: \" + result);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append("return (");
          JvmParameterizedTypeReference _out = params.getOut();
          String _simpleName_2 = DaoJvmModelInferrer.this._processorGeneratorUtils.getSimpleName(_out);
          _builder.append(_simpleName_2, "");
          _builder.append(") result;");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, fname, outType, _function);
    members.add(_method);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return ");
          _builder.append(fname, "");
          _builder.append("(sqlSessionFactory.getSqlSession(), ");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "");
            }
          }
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, fname, outType, _function_1);
    members.add(_method_1);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return ");
          _builder.append(fname, "");
          _builder.append("(sqlSession, ");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "");
            }
          }
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, fname, outType, _function_2);
    members.add(_method_2);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return ");
          _builder.append(fname, "");
          _builder.append("(");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "");
            }
          }
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, fname, outType, _function_3);
    members.add(_method_3);
  }
  
  protected void _inferFunctionProcedureIfx(final DaoEntity entity, final FunctionCall type, final DaoDirectiveParameters params, final JvmGenericType entityType, final String simpleName, final List<JvmMember> members) {
    JvmParameterizedTypeReference _out = params.getOut();
    JvmType _type = _out.getType();
    JvmParameterizedTypeReference _out_1 = params.getOut();
    EList<JvmTypeReference> _arguments = _out_1.getArguments();
    final JvmTypeReference outType = this._typeReferenceBuilder.typeRef(_type, ((JvmTypeReference[])Conversions.unwrapArray(_arguments, JvmTypeReference.class)));
    final String fname = this._processorGeneratorUtils.getFunProcName(entity);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, fname, outType, _function);
    members.add(_method);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, fname, outType, _function_1);
    members.add(_method_1);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, fname, outType, _function_2);
    members.add(_method_2);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, fname, outType, _function_3);
    members.add(_method_3);
  }
  
  protected void _inferFunctionProcedure(final DaoEntity entity, final ProcedureUpdate type, final DaoDirectiveParameters params, final JvmGenericType entityType, final String simpleName, final List<JvmMember> members) {
    final String fname = this._processorGeneratorUtils.getFunProcName(entity);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type = in.getType();
        JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(_type);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_2);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql ");
          _builder.append(fname, "\t");
          _builder.append(": \" + ");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(" + \" \" ", "\t");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "\t");
            }
          }
          _builder.append(" + \" \" + sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append(DaoJvmModelInferrer.this.PROCEDURE_ENGINE, "");
          _builder.append(" sqlProc");
          String _name = entity.getName();
          _builder.append(_name, "");
          _builder.append(" = sqlEngineFactory.getCheckedProcedureEngine(\"PROC_");
          String _dbName = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(fname);
          _builder.append(_dbName, "");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          _builder.append("int count = sqlProc");
          String _name_1 = entity.getName();
          _builder.append(_name_1, "");
          _builder.append(".callUpdate(sqlSession, ");
          {
            EList<JvmParameterizedTypeReference> _ins_1 = params.getIns();
            boolean _hasElements_1 = false;
            for(final JvmParameterizedTypeReference in_1 : _ins_1) {
              if (!_hasElements_1) {
                _hasElements_1 = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName_1 = in_1.getSimpleName();
              String _firstLower_1 = StringExtensions.toFirstLower(_simpleName_1);
              _builder.append(_firstLower_1, "");
            }
          }
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql ");
          _builder.append(fname, "\t");
          _builder.append(" result: \" + count);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append("return count;");
          _builder.newLine();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, fname, _typeRef, _function);
    members.add(_method);
    JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type = in.getType();
        JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(_type);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_2);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return ");
          _builder.append(fname, "");
          _builder.append("(sqlSessionFactory.getSqlSession(), ");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "");
            }
          }
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, fname, _typeRef_1, _function_1);
    members.add(_method_1);
    JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type = in.getType();
        JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(_type);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_4);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return ");
          _builder.append(fname, "");
          _builder.append("(sqlSession, ");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "");
            }
          }
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, fname, _typeRef_2, _function_2);
    members.add(_method_2);
    JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type = in.getType();
        JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(_type);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_4);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return ");
          _builder.append(fname, "");
          _builder.append("(");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "");
            }
          }
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, fname, _typeRef_3, _function_3);
    members.add(_method_3);
  }
  
  protected void _inferFunctionProcedureIfx(final DaoEntity entity, final ProcedureUpdate type, final DaoDirectiveParameters params, final JvmGenericType entityType, final String simpleName, final List<JvmMember> members) {
    final String fname = this._processorGeneratorUtils.getFunProcName(entity);
    JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type = in.getType();
        JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(_type);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_2);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, fname, _typeRef, _function);
    members.add(_method);
    JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type = in.getType();
        JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(_type);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_2);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, fname, _typeRef_1, _function_1);
    members.add(_method_1);
    JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef_3);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type = in.getType();
        JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(_type);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_4);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, fname, _typeRef_2, _function_2);
    members.add(_method_2);
    JvmTypeReference _typeRef_3 = this._typeReferenceBuilder.typeRef(int.class);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type = in.getType();
        JvmTypeReference _typeRef_4 = this._typeReferenceBuilder.typeRef(_type);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_4);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, fname, _typeRef_3, _function_3);
    members.add(_method_3);
  }
  
  protected void _inferFunctionProcedure(final DaoEntity entity, final FunctionQuery type, final DaoDirectiveParameters params, final JvmGenericType entityType, final String simpleName, final List<JvmMember> members) {
    JvmParameterizedTypeReference _out = params.getOut();
    JvmType _type = _out.getType();
    JvmParameterizedTypeReference _out_1 = params.getOut();
    EList<JvmTypeReference> _arguments = _out_1.getArguments();
    final JvmTypeReference outType = this._typeReferenceBuilder.typeRef(_type, ((JvmTypeReference[])Conversions.unwrapArray(_arguments, JvmTypeReference.class)));
    final String fname = this._processorGeneratorUtils.getFunProcName(entity);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql ");
          _builder.append(fname, "\t");
          _builder.append(": \" + ");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(" + \" \" ", "\t");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "\t");
            }
          }
          _builder.append(" + \" \" + sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append(DaoJvmModelInferrer.this.QUERY_ENGINE, "");
          _builder.append(" sqlFun");
          String _name = entity.getName();
          _builder.append(_name, "");
          _builder.append(" = sqlEngineFactory.getCheckedQueryEngine(\"FUN_");
          String _dbName = DaoJvmModelInferrer.this._processorGeneratorUtils.dbName(fname);
          _builder.append(_dbName, "");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          _builder.append(DaoJvmModelInferrer.this.LIST, "");
          _builder.append("<");
          EList<JvmParameterizedTypeReference> _ins_1 = params.getIns();
          JvmParameterizedTypeReference _head = IterableExtensions.<JvmParameterizedTypeReference>head(_ins_1);
          String _simpleName_1 = _head.getSimpleName();
          _builder.append(_simpleName_1, "");
          _builder.append("> list = sqlFun");
          String _name_1 = entity.getName();
          _builder.append(_name_1, "");
          _builder.append(".query(sqlSession, ");
          EList<JvmParameterizedTypeReference> _ins_2 = params.getIns();
          JvmParameterizedTypeReference _head_1 = IterableExtensions.<JvmParameterizedTypeReference>head(_ins_2);
          String _simpleName_2 = _head_1.getSimpleName();
          _builder.append(_simpleName_2, "");
          _builder.append(".class, ");
          {
            EList<JvmParameterizedTypeReference> _ins_3 = params.getIns();
            boolean _hasElements_1 = false;
            for(final JvmParameterizedTypeReference in_1 : _ins_3) {
              if (!_hasElements_1) {
                _hasElements_1 = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName_3 = in_1.getSimpleName();
              String _firstLower_1 = StringExtensions.toFirstLower(_simpleName_3);
              _builder.append(_firstLower_1, "");
            }
          }
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
          _builder.append("if (logger.isTraceEnabled()) {");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("logger.trace(\"sql ");
          _builder.append(fname, "\t");
          _builder.append(" result: \" + list);");
          _builder.newLineIfNotEmpty();
          _builder.append("}");
          _builder.newLine();
          _builder.append("return (list != null && !list.isEmpty()) ? list.get(0).getResult() : null;");
          _builder.newLine();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, fname, outType, _function);
    members.add(_method);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return ");
          _builder.append(fname, "");
          _builder.append("(sqlSessionFactory.getSqlSession(), ");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "");
            }
          }
          _builder.append(", sqlControl);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, fname, outType, _function_1);
    members.add(_method_1);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return ");
          _builder.append(fname, "");
          _builder.append("(sqlSession, ");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "");
            }
          }
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, fname, outType, _function_2);
    members.add(_method_2);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("return ");
          _builder.append(fname, "");
          _builder.append("(");
          {
            EList<JvmParameterizedTypeReference> _ins = params.getIns();
            boolean _hasElements = false;
            for(final JvmParameterizedTypeReference in : _ins) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _simpleName = in.getSimpleName();
              String _firstLower = StringExtensions.toFirstLower(_simpleName);
              _builder.append(_firstLower, "");
            }
          }
          _builder.append(", null);");
          _builder.newLineIfNotEmpty();
        }
      };
      this._processorTypesBuilder.setBody(it, _client);
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, fname, outType, _function_3);
    members.add(_method_3);
  }
  
  protected void _inferFunctionProcedureIfx(final DaoEntity entity, final FunctionQuery type, final DaoDirectiveParameters params, final JvmGenericType entityType, final String simpleName, final List<JvmMember> members) {
    JvmParameterizedTypeReference _out = params.getOut();
    JvmType _type = _out.getType();
    JvmParameterizedTypeReference _out_1 = params.getOut();
    EList<JvmTypeReference> _arguments = _out_1.getArguments();
    final JvmTypeReference outType = this._typeReferenceBuilder.typeRef(_type, ((JvmTypeReference[])Conversions.unwrapArray(_arguments, JvmTypeReference.class)));
    final String fname = this._processorGeneratorUtils.getFunProcName(entity);
    final Procedure1<JvmOperation> _function = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
      EList<JvmFormalParameter> _parameters_2 = it.getParameters();
      JvmTypeReference _typeRef_2 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_2 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_2);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_2, _parameter_2);
    };
    JvmOperation _method = this._processorTypesBuilder.toMethod(entity, fname, outType, _function);
    members.add(_method);
    final Procedure1<JvmOperation> _function_1 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
      EList<JvmFormalParameter> _parameters_1 = it.getParameters();
      JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(this.SQL_CONTROL);
      JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, "sqlControl", _typeRef_1);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
    };
    JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, fname, outType, _function_1);
    members.add(_method_1);
    final Procedure1<JvmOperation> _function_2 = (JvmOperation it) -> {
      EList<JvmFormalParameter> _parameters = it.getParameters();
      JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(this.SQL_SESSION);
      JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "sqlSession", _typeRef);
      this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters_1 = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef_1 = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter_1 = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef_1);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters_1, _parameter_1);
      }
    };
    JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, fname, outType, _function_2);
    members.add(_method_2);
    final Procedure1<JvmOperation> _function_3 = (JvmOperation it) -> {
      EList<JvmParameterizedTypeReference> _ins = params.getIns();
      for (final JvmParameterizedTypeReference in : _ins) {
        EList<JvmFormalParameter> _parameters = it.getParameters();
        String _simpleName = in.getSimpleName();
        String _firstLower = StringExtensions.toFirstLower(_simpleName);
        JvmType _type_1 = in.getType();
        JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(_type_1);
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, _firstLower, _typeRef);
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
      }
    };
    JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, fname, outType, _function_3);
    members.add(_method_3);
  }
  
  public QualifiedName daoFullyQualifiedName(final DaoEntity entity, final String implPackage) {
    final QualifiedName fqname = this._iQualifiedNameProvider.getFullyQualifiedName(entity);
    boolean _equals = Objects.equal(implPackage, null);
    if (_equals) {
      return fqname;
    }
    int _indexOf = implPackage.indexOf(".");
    boolean _greaterEqualsThan = (_indexOf >= 0);
    if (_greaterEqualsThan) {
      final String[] segments = implPackage.split(".");
      final QualifiedName fqn = QualifiedName.create(segments);
      String _lastSegment = fqname.getLastSegment();
      return fqn.append(_lastSegment);
    } else {
      QualifiedName fqn_1 = fqname.skipLast(1);
      QualifiedName _append = fqn_1.append(implPackage);
      fqn_1 = _append;
      String _lastSegment_1 = fqname.getLastSegment();
      String _plus = (_lastSegment_1 + "Impl");
      return fqn_1.append(_plus);
    }
  }
  
  public void inferFunctionProcedure(final DaoEntity entity, final FunProcType type, final DaoDirectiveParameters params, final JvmGenericType entityType, final String simpleName, final List<JvmMember> members) {
    if (type instanceof FunctionCall) {
      _inferFunctionProcedure(entity, (FunctionCall)type, params, entityType, simpleName, members);
      return;
    } else if (type instanceof FunctionCallQuery) {
      _inferFunctionProcedure(entity, (FunctionCallQuery)type, params, entityType, simpleName, members);
      return;
    } else if (type instanceof FunctionQuery) {
      _inferFunctionProcedure(entity, (FunctionQuery)type, params, entityType, simpleName, members);
      return;
    } else if (type instanceof ProcedureCallQuery) {
      _inferFunctionProcedure(entity, (ProcedureCallQuery)type, params, entityType, simpleName, members);
      return;
    } else if (type instanceof ProcedureUpdate) {
      _inferFunctionProcedure(entity, (ProcedureUpdate)type, params, entityType, simpleName, members);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(entity, type, params, entityType, simpleName, members).toString());
    }
  }
  
  public void inferFunctionProcedureIfx(final DaoEntity entity, final FunProcType type, final DaoDirectiveParameters params, final JvmGenericType entityType, final String simpleName, final List<JvmMember> members) {
    if (type instanceof FunctionCall) {
      _inferFunctionProcedureIfx(entity, (FunctionCall)type, params, entityType, simpleName, members);
      return;
    } else if (type instanceof FunctionCallQuery) {
      _inferFunctionProcedureIfx(entity, (FunctionCallQuery)type, params, entityType, simpleName, members);
      return;
    } else if (type instanceof FunctionQuery) {
      _inferFunctionProcedureIfx(entity, (FunctionQuery)type, params, entityType, simpleName, members);
      return;
    } else if (type instanceof ProcedureCallQuery) {
      _inferFunctionProcedureIfx(entity, (ProcedureCallQuery)type, params, entityType, simpleName, members);
      return;
    } else if (type instanceof ProcedureUpdate) {
      _inferFunctionProcedureIfx(entity, (ProcedureUpdate)type, params, entityType, simpleName, members);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(entity, type, params, entityType, simpleName, members).toString());
    }
  }
}
