package org.sqlproc.model.jvmmodel;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmEnumerationLiteral;
import org.eclipse.xtext.common.types.JvmEnumerationType;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XNumberLiteral;
import org.eclipse.xtext.xbase.XStringLiteral;
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.JvmAnnotationReferenceBuilder;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypeReferenceBuilder;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.sqlproc.model.jvmmodel.ProcessorGeneratorUtils;
import org.sqlproc.model.jvmmodel.ProcessorTypesBuilder;
import org.sqlproc.model.processorModel.Annotation;
import org.sqlproc.model.processorModel.EnumAttribute;
import org.sqlproc.model.processorModel.EnumAttributeDirective;
import org.sqlproc.model.processorModel.EnumAttributeDirectiveValues;
import org.sqlproc.model.processorModel.EnumAttributeValue;
import org.sqlproc.model.processorModel.EnumEntity;

/**
 * <p>Infers a JVM model from the source model.</p>
 * 
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
@SuppressWarnings("all")
public class EnumJvmModelInferrer extends AbstractModelInferrer {
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
  public void inferEnum(final EnumEntity entity, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(entity);
    String _string = _fullyQualifiedName.toString();
    final Procedure1<JvmEnumerationType> _function = new Procedure1<JvmEnumerationType>() {
      @Override
      public void apply(final JvmEnumerationType it) {
      }
    };
    final JvmEnumerationType entityType = this._processorTypesBuilder.toEnumerationType(entity, _string, _function);
    final String simpleName = entity.getName();
    final Procedure1<JvmEnumerationType> _function_1 = new Procedure1<JvmEnumerationType>() {
      @Override
      public void apply(final JvmEnumerationType it) {
        String _documentation = EnumJvmModelInferrer.this._processorTypesBuilder.getDocumentation(entity);
        EnumJvmModelInferrer.this._processorTypesBuilder.setDocumentation(it, _documentation);
        EList<Annotation> _annotations = EnumJvmModelInferrer.this._processorGeneratorUtils.annotations(entity);
        final Function1<Annotation, XAnnotation> _function = new Function1<Annotation, XAnnotation>() {
          @Override
          public XAnnotation apply(final Annotation a) {
            return a.getAnnotation();
          }
        };
        List<XAnnotation> _map = ListExtensions.<Annotation, XAnnotation>map(_annotations, _function);
        for (final XAnnotation a : _map) {
          JvmType _annotationType = a.getAnnotationType();
          String _identifier = _annotationType.getIdentifier();
          boolean _equals = Objects.equal(_identifier, EnumJvmModelInferrer.this.SERIALIZABLE);
          if (_equals) {
            EList<JvmTypeReference> _superTypes = it.getSuperTypes();
            JvmType _annotationType_1 = a.getAnnotationType();
            JvmTypeReference _typeRef = EnumJvmModelInferrer.this._typeReferenceBuilder.typeRef(_annotationType_1);
            EnumJvmModelInferrer.this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _typeRef);
          } else {
            EnumJvmModelInferrer.this._processorTypesBuilder.addAnnotation(it, a);
          }
        }
        final List<EnumAttributeValue> values = CollectionLiterals.<EnumAttributeValue>newArrayList();
        EnumAttribute _attribute = entity.getAttribute();
        EList<EnumAttributeDirective> _directives = _attribute.getDirectives();
        for (final EnumAttributeDirective dir : _directives) {
          if ((dir instanceof EnumAttributeDirectiveValues)) {
            final EnumAttributeDirectiveValues dv = ((EnumAttributeDirectiveValues) dir);
            EList<EnumAttributeValue> _values = dv.getValues();
            for (final EnumAttributeValue epv : _values) {
              {
                String _switchResult = null;
                XExpression _value = epv.getValue();
                boolean _matched = false;
                if (!_matched) {
                  if (_value instanceof XStringLiteral) {
                    _matched=true;
                    XExpression _value_1 = epv.getValue();
                    String _value_2 = ((XStringLiteral) _value_1).getValue();
                    String _plus = ("\"" + _value_2);
                    _switchResult = (_plus + "\"");
                  }
                }
                if (!_matched) {
                  if (_value instanceof XNumberLiteral) {
                    _matched=true;
                    XExpression _value_1 = epv.getValue();
                    _switchResult = ((XNumberLiteral) _value_1).getValue();
                  }
                }
                final String value = _switchResult;
                EList<JvmMember> _members = it.getMembers();
                String _name = epv.getName();
                final Procedure1<JvmEnumerationLiteral> _function_1 = new Procedure1<JvmEnumerationLiteral>() {
                  @Override
                  public void apply(final JvmEnumerationLiteral it) {
                    final Procedure1<ITreeAppendable> _function = new Procedure1<ITreeAppendable>() {
                      @Override
                      public void apply(final ITreeAppendable it) {
                        it.append((("(" + value) + ")"));
                      }
                    };
                    EnumJvmModelInferrer.this._processorTypesBuilder.setInitializer(it, _function);
                  }
                };
                JvmEnumerationLiteral _enumerationLiteral = EnumJvmModelInferrer.this._processorTypesBuilder.toEnumerationLiteral(entity, _name, _function_1);
                EnumJvmModelInferrer.this._processorTypesBuilder.<JvmEnumerationLiteral>operator_add(_members, _enumerationLiteral);
                values.add(epv);
              }
            }
          }
        }
        EnumAttribute _attribute_1 = entity.getAttribute();
        JvmTypeReference _type = _attribute_1.getType();
        JvmTypeReference _typeRef_1 = EnumJvmModelInferrer.this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies = EnumJvmModelInferrer.this._processorTypesBuilder.cloneWithProxies(_typeRef_1);
        final JvmTypeReference identifierMapType = EnumJvmModelInferrer.this._typeReferenceBuilder.typeRef(Map.class, _type, _cloneWithProxies);
        EList<JvmMember> _members = it.getMembers();
        final Procedure1<JvmField> _function_1 = new Procedure1<JvmField>() {
          @Override
          public void apply(final JvmField it) {
            it.setStatic(true);
            StringConcatenationClient _client = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append(" ");
                _builder.append("identifierMapBuild()");
              }
            };
            EnumJvmModelInferrer.this._processorTypesBuilder.setInitializer(it, _client);
          }
        };
        JvmField _field = EnumJvmModelInferrer.this._processorTypesBuilder.toField(entity, "identifierMap", identifierMapType, _function_1);
        EnumJvmModelInferrer.this._processorTypesBuilder.<JvmField>operator_add(_members, _field);
        EList<JvmMember> _members_1 = it.getMembers();
        final Procedure1<JvmOperation> _function_2 = new Procedure1<JvmOperation>() {
          @Override
          public void apply(final JvmOperation it) {
            it.setStatic(true);
            StringConcatenationClient _client = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append("Map<");
                EnumAttribute _attribute = entity.getAttribute();
                JvmTypeReference _type = _attribute.getType();
                _builder.append(_type, "");
                _builder.append(", ");
                _builder.append(simpleName, "");
                _builder.append("> _identifierMap = new ");
                _builder.append(EnumJvmModelInferrer.this.HASH_MAP, "");
                _builder.append("<");
                EnumAttribute _attribute_1 = entity.getAttribute();
                JvmTypeReference _type_1 = _attribute_1.getType();
                _builder.append(_type_1, "");
                _builder.append(", ");
                _builder.append(simpleName, "");
                _builder.append(">();");
                _builder.newLineIfNotEmpty();
                _builder.append("for (");
                _builder.append(simpleName, "");
                _builder.append(" value : ");
                _builder.append(simpleName, "");
                _builder.append(".values()) {");
                _builder.newLineIfNotEmpty();
                _builder.append("\t");
                _builder.append("_identifierMap.put(value.getValue(), value);");
                _builder.newLine();
                _builder.append("}");
                _builder.newLine();
                _builder.append("return _identifierMap;");
                _builder.newLine();
              }
            };
            EnumJvmModelInferrer.this._processorTypesBuilder.setBody(it, _client);
          }
        };
        JvmOperation _method = EnumJvmModelInferrer.this._processorTypesBuilder.toMethod(entity, "identifierMapBuild", identifierMapType, _function_2);
        EnumJvmModelInferrer.this._processorTypesBuilder.<JvmOperation>operator_add(_members_1, _method);
        EList<JvmMember> _members_2 = it.getMembers();
        EnumAttribute _attribute_2 = entity.getAttribute();
        JvmTypeReference _type_1 = _attribute_2.getType();
        JvmField _field_1 = EnumJvmModelInferrer.this._processorTypesBuilder.toField(entity, "value", _type_1);
        EnumJvmModelInferrer.this._processorTypesBuilder.<JvmField>operator_add(_members_2, _field_1);
        EList<JvmMember> _members_3 = it.getMembers();
        final Procedure1<JvmConstructor> _function_3 = new Procedure1<JvmConstructor>() {
          @Override
          public void apply(final JvmConstructor it) {
            EList<JvmFormalParameter> _parameters = it.getParameters();
            EnumAttribute _attribute = entity.getAttribute();
            JvmTypeReference _type = _attribute.getType();
            JvmFormalParameter _parameter = EnumJvmModelInferrer.this._processorTypesBuilder.toParameter(entity, "value", _type);
            EnumJvmModelInferrer.this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
            it.setVisibility(JvmVisibility.PRIVATE);
            StringConcatenationClient _client = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append("this.value = value;");
                _builder.newLine();
              }
            };
            EnumJvmModelInferrer.this._processorTypesBuilder.setBody(it, _client);
          }
        };
        JvmConstructor _constructor = EnumJvmModelInferrer.this._processorTypesBuilder.toConstructor(entity, _function_3);
        EnumJvmModelInferrer.this._processorTypesBuilder.<JvmConstructor>operator_add(_members_3, _constructor);
        EList<JvmMember> _members_4 = it.getMembers();
        JvmTypeReference _typeRef_2 = EnumJvmModelInferrer.this._typeReferenceBuilder.typeRef(entityType);
        JvmTypeReference _cloneWithProxies_1 = EnumJvmModelInferrer.this._processorTypesBuilder.cloneWithProxies(_typeRef_2);
        final Procedure1<JvmOperation> _function_4 = new Procedure1<JvmOperation>() {
          @Override
          public void apply(final JvmOperation it) {
            EList<JvmFormalParameter> _parameters = it.getParameters();
            EnumAttribute _attribute = entity.getAttribute();
            JvmTypeReference _type = _attribute.getType();
            JvmFormalParameter _parameter = EnumJvmModelInferrer.this._processorTypesBuilder.toParameter(entity, "value", _type);
            EnumJvmModelInferrer.this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
            it.setStatic(true);
            StringConcatenationClient _client = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append(simpleName, "");
                _builder.append(" result = identifierMap.get(value);");
                _builder.newLineIfNotEmpty();
                _builder.append("if (result == null) {");
                _builder.newLine();
                _builder.append("\t");
                _builder.append("throw new IllegalArgumentException(\"No ");
                _builder.append(simpleName, "\t");
                _builder.append(" for value: \" + value);");
                _builder.newLineIfNotEmpty();
                _builder.append("}");
                _builder.newLine();
                _builder.append("return result;");
                _builder.newLine();
              }
            };
            EnumJvmModelInferrer.this._processorTypesBuilder.setBody(it, _client);
          }
        };
        JvmOperation _method_1 = EnumJvmModelInferrer.this._processorTypesBuilder.toMethod(entity, "fromValue", _cloneWithProxies_1, _function_4);
        EnumJvmModelInferrer.this._processorTypesBuilder.<JvmOperation>operator_add(_members_4, _method_1);
        EList<JvmMember> _members_5 = it.getMembers();
        EnumAttribute _attribute_3 = entity.getAttribute();
        JvmTypeReference _type_2 = _attribute_3.getType();
        final Procedure1<JvmOperation> _function_5 = new Procedure1<JvmOperation>() {
          @Override
          public void apply(final JvmOperation it) {
            StringConcatenationClient _client = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append("return value;");
                _builder.newLine();
              }
            };
            EnumJvmModelInferrer.this._processorTypesBuilder.setBody(it, _client);
          }
        };
        JvmOperation _method_2 = EnumJvmModelInferrer.this._processorTypesBuilder.toMethod(entity, "getValue", _type_2, _function_5);
        EnumJvmModelInferrer.this._processorTypesBuilder.<JvmOperation>operator_add(_members_5, _method_2);
        EList<JvmMember> _members_6 = it.getMembers();
        JvmTypeReference _typeRef_3 = EnumJvmModelInferrer.this._typeReferenceBuilder.typeRef(String.class);
        final Procedure1<JvmOperation> _function_6 = new Procedure1<JvmOperation>() {
          @Override
          public void apply(final JvmOperation it) {
            StringConcatenationClient _client = new StringConcatenationClient() {
              @Override
              protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
                _builder.append("return name();");
                _builder.newLine();
              }
            };
            EnumJvmModelInferrer.this._processorTypesBuilder.setBody(it, _client);
          }
        };
        JvmOperation _method_3 = EnumJvmModelInferrer.this._processorTypesBuilder.toMethod(entity, "getName", _typeRef_3, _function_6);
        EnumJvmModelInferrer.this._processorTypesBuilder.<JvmOperation>operator_add(_members_6, _method_3);
      }
    };
    acceptor.<JvmEnumerationType>accept(entityType, _function_1);
  }
}
