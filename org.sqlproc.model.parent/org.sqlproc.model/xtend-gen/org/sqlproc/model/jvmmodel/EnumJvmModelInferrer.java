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
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
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
    final Procedure1<JvmEnumerationType> _function = (JvmEnumerationType it) -> {
    };
    final JvmEnumerationType entityType = this._processorTypesBuilder.toEnumerationType(entity, this._iQualifiedNameProvider.getFullyQualifiedName(entity).toString(), _function);
    final String simpleName = entity.getName();
    final Procedure1<JvmEnumerationType> _function_1 = (JvmEnumerationType it) -> {
      this._processorTypesBuilder.setDocumentation(it, this._processorTypesBuilder.getDocumentation(entity));
      final Function1<Annotation, XAnnotation> _function_2 = (Annotation a) -> {
        return a.getAnnotation();
      };
      List<XAnnotation> _map = ListExtensions.<Annotation, XAnnotation>map(this._processorGeneratorUtils.annotations(entity), _function_2);
      for (final XAnnotation a : _map) {
        String _identifier = a.getAnnotationType().getIdentifier();
        boolean _equals = Objects.equal(_identifier, this.SERIALIZABLE);
        if (_equals) {
          EList<JvmTypeReference> _superTypes = it.getSuperTypes();
          JvmTypeReference _typeRef = this._typeReferenceBuilder.typeRef(a.getAnnotationType());
          this._processorTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _typeRef);
        } else {
          this._processorTypesBuilder.addAnnotation(it, a);
        }
      }
      final List<EnumAttributeValue> values = CollectionLiterals.<EnumAttributeValue>newArrayList();
      EList<EnumAttributeDirective> _directives = entity.getAttribute().getDirectives();
      for (final EnumAttributeDirective dir : _directives) {
        if ((dir instanceof EnumAttributeDirectiveValues)) {
          final EnumAttributeDirectiveValues dv = ((EnumAttributeDirectiveValues) dir);
          EList<EnumAttributeValue> _values = dv.getValues();
          for (final EnumAttributeValue epv : _values) {
            {
              String _switchResult = null;
              XExpression _value = epv.getValue();
              boolean _matched = false;
              if (_value instanceof XStringLiteral) {
                _matched=true;
                XExpression _value_1 = epv.getValue();
                String _value_2 = ((XStringLiteral) _value_1).getValue();
                String _plus = ("\"" + _value_2);
                _switchResult = (_plus + "\"");
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
              final Procedure1<JvmEnumerationLiteral> _function_3 = (JvmEnumerationLiteral it_1) -> {
                final Procedure1<ITreeAppendable> _function_4 = (ITreeAppendable it_2) -> {
                  it_2.append((("(" + value) + ")"));
                };
                this._processorTypesBuilder.setInitializer(it_1, _function_4);
              };
              JvmEnumerationLiteral _enumerationLiteral = this._processorTypesBuilder.toEnumerationLiteral(entity, epv.getName(), _function_3);
              this._processorTypesBuilder.<JvmEnumerationLiteral>operator_add(_members, _enumerationLiteral);
              values.add(epv);
            }
          }
        }
      }
      final JvmTypeReference identifierMapType = this._typeReferenceBuilder.typeRef(Map.class, entity.getAttribute().getType(), this._processorTypesBuilder.cloneWithProxies(this._typeReferenceBuilder.typeRef(entityType)));
      EList<JvmMember> _members = it.getMembers();
      final Procedure1<JvmField> _function_3 = (JvmField it_1) -> {
        it_1.setStatic(true);
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
            _builder.append(" ");
            _builder.append("identifierMapBuild()");
          }
        };
        this._processorTypesBuilder.setInitializer(it_1, _client);
      };
      JvmField _field = this._processorTypesBuilder.toField(entity, "identifierMap", identifierMapType, _function_3);
      this._processorTypesBuilder.<JvmField>operator_add(_members, _field);
      EList<JvmMember> _members_1 = it.getMembers();
      final Procedure1<JvmOperation> _function_4 = (JvmOperation it_1) -> {
        it_1.setStatic(true);
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
            _builder.append("Map<");
            JvmTypeReference _type = entity.getAttribute().getType();
            _builder.append(_type);
            _builder.append(", ");
            _builder.append(simpleName);
            _builder.append("> _identifierMap = new ");
            _builder.append(EnumJvmModelInferrer.this.HASH_MAP);
            _builder.append("<");
            JvmTypeReference _type_1 = entity.getAttribute().getType();
            _builder.append(_type_1);
            _builder.append(", ");
            _builder.append(simpleName);
            _builder.append(">();");
            _builder.newLineIfNotEmpty();
            _builder.append("for (");
            _builder.append(simpleName);
            _builder.append(" value : ");
            _builder.append(simpleName);
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
        this._processorTypesBuilder.setBody(it_1, _client);
      };
      JvmOperation _method = this._processorTypesBuilder.toMethod(entity, "identifierMapBuild", identifierMapType, _function_4);
      this._processorTypesBuilder.<JvmOperation>operator_add(_members_1, _method);
      EList<JvmMember> _members_2 = it.getMembers();
      JvmField _field_1 = this._processorTypesBuilder.toField(entity, "value", entity.getAttribute().getType());
      this._processorTypesBuilder.<JvmField>operator_add(_members_2, _field_1);
      EList<JvmMember> _members_3 = it.getMembers();
      final Procedure1<JvmConstructor> _function_5 = (JvmConstructor it_1) -> {
        EList<JvmFormalParameter> _parameters = it_1.getParameters();
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "value", entity.getAttribute().getType());
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
        it_1.setVisibility(JvmVisibility.PRIVATE);
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
            _builder.append("this.value = value;");
            _builder.newLine();
          }
        };
        this._processorTypesBuilder.setBody(it_1, _client);
      };
      JvmConstructor _constructor = this._processorTypesBuilder.toConstructor(entity, _function_5);
      this._processorTypesBuilder.<JvmConstructor>operator_add(_members_3, _constructor);
      EList<JvmMember> _members_4 = it.getMembers();
      final Procedure1<JvmOperation> _function_6 = (JvmOperation it_1) -> {
        EList<JvmFormalParameter> _parameters = it_1.getParameters();
        JvmFormalParameter _parameter = this._processorTypesBuilder.toParameter(entity, "value", entity.getAttribute().getType());
        this._processorTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
        it_1.setStatic(true);
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
            _builder.append(simpleName);
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
        this._processorTypesBuilder.setBody(it_1, _client);
      };
      JvmOperation _method_1 = this._processorTypesBuilder.toMethod(entity, "fromValue", this._processorTypesBuilder.cloneWithProxies(this._typeReferenceBuilder.typeRef(entityType)), _function_6);
      this._processorTypesBuilder.<JvmOperation>operator_add(_members_4, _method_1);
      EList<JvmMember> _members_5 = it.getMembers();
      final Procedure1<JvmOperation> _function_7 = (JvmOperation it_1) -> {
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
            _builder.append("return value;");
            _builder.newLine();
          }
        };
        this._processorTypesBuilder.setBody(it_1, _client);
      };
      JvmOperation _method_2 = this._processorTypesBuilder.toMethod(entity, "getValue", entity.getAttribute().getType(), _function_7);
      this._processorTypesBuilder.<JvmOperation>operator_add(_members_5, _method_2);
      EList<JvmMember> _members_6 = it.getMembers();
      final Procedure1<JvmOperation> _function_8 = (JvmOperation it_1) -> {
        StringConcatenationClient _client = new StringConcatenationClient() {
          @Override
          protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
            _builder.append("return name();");
            _builder.newLine();
          }
        };
        this._processorTypesBuilder.setBody(it_1, _client);
      };
      JvmOperation _method_3 = this._processorTypesBuilder.toMethod(entity, "getName", this._typeReferenceBuilder.typeRef(String.class), _function_8);
      this._processorTypesBuilder.<JvmOperation>operator_add(_members_6, _method_3);
    };
    acceptor.<JvmEnumerationType>accept(entityType, _function_1);
  }
}
