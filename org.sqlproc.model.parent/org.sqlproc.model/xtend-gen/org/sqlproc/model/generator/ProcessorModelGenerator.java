package org.sqlproc.model.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmAnnotationAnnotationValue;
import org.eclipse.xtext.common.types.JvmAnnotationReference;
import org.eclipse.xtext.common.types.JvmAnnotationValue;
import org.eclipse.xtext.common.types.JvmBooleanAnnotationValue;
import org.eclipse.xtext.common.types.JvmByteAnnotationValue;
import org.eclipse.xtext.common.types.JvmCharAnnotationValue;
import org.eclipse.xtext.common.types.JvmCustomAnnotationValue;
import org.eclipse.xtext.common.types.JvmDoubleAnnotationValue;
import org.eclipse.xtext.common.types.JvmEnumAnnotationValue;
import org.eclipse.xtext.common.types.JvmEnumerationLiteral;
import org.eclipse.xtext.common.types.JvmFloatAnnotationValue;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmIntAnnotationValue;
import org.eclipse.xtext.common.types.JvmLongAnnotationValue;
import org.eclipse.xtext.common.types.JvmShortAnnotationValue;
import org.eclipse.xtext.common.types.JvmStringAnnotationValue;
import org.eclipse.xtext.common.types.JvmTypeAnnotationValue;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XNumberLiteral;
import org.eclipse.xtext.xbase.XStringLiteral;
import org.eclipse.xtext.xbase.compiler.GeneratorConfig;
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class ProcessorModelGenerator extends JvmModelGenerator {
  @Inject
  private XbaseCompiler compiler;
  
  @Override
  public void generateEnumLiteral(final JvmEnumerationLiteral it, final ITreeAppendable appendable, final GeneratorConfig config) {
    super.generateEnumLiteral(it, appendable, config);
    Procedure1<? super ITreeAppendable> _compilationStrategy = this._jvmTypeExtensions.getCompilationStrategy(it);
    if (_compilationStrategy!=null) {
      _compilationStrategy.apply(appendable);
    }
  }
  
  @Override
  public void generateParameter(final JvmFormalParameter it, final ITreeAppendable appendable, final boolean vararg, final GeneratorConfig config) {
    String _name = it.getName();
    boolean _notEquals = (!Objects.equal(_name, "sqlControl"));
    if (_notEquals) {
      super.generateParameter(it, appendable, vararg, config);
    } else {
      final ITreeAppendable tracedAppendable = appendable.trace(it);
      EList<JvmAnnotationReference> _annotations = it.getAnnotations();
      this.generateAnnotations(_annotations, tracedAppendable, false, config);
      JvmTypeReference _parameterType = it.getParameterType();
      this._errorSafeExtensions.serializeSafely(_parameterType, "Object", tracedAppendable);
      tracedAppendable.append(" ");
      String _simpleName = it.getSimpleName();
      String _makeJavaIdentifier = this.makeJavaIdentifier(_simpleName);
      final String name = tracedAppendable.declareVariable(it, _makeJavaIdentifier);
      ITreeAppendable _traceSignificant = this._treeAppendableUtil.traceSignificant(tracedAppendable, it);
      _traceSignificant.append(name);
    }
  }
  
  @Override
  protected void _toJavaLiteral(final JvmCustomAnnotationValue it, final ITreeAppendable appendable, final GeneratorConfig config) {
    EList<EObject> _values = it.getValues();
    boolean _isEmpty = _values.isEmpty();
    if (_isEmpty) {
      appendable.append("{}");
    } else {
      EList<EObject> _values_1 = it.getValues();
      Iterable<XExpression> _filter = Iterables.<XExpression>filter(_values_1, XExpression.class);
      final Procedure1<XExpression> _function = new Procedure1<XExpression>() {
        @Override
        public void apply(final XExpression it) {
          if ((it instanceof XStringLiteral)) {
            String _value = ((XStringLiteral)it).getValue();
            String _doConvertToJavaString = ProcessorModelGenerator.this.doConvertToJavaString(_value);
            String _plus = ("\"" + _doConvertToJavaString);
            String _plus_1 = (_plus + "\"");
            appendable.append(_plus_1);
          } else {
            if ((it instanceof XNumberLiteral)) {
              String _value_1 = ((XNumberLiteral)it).getValue();
              appendable.append(_value_1);
            } else {
              ProcessorModelGenerator.this.compiler.toJavaExpression(it, appendable);
            }
          }
        }
      };
      this._loopExtensions.<XExpression>forEachWithShortcut(appendable, _filter, _function);
    }
  }
  
  public void toJavaLiteral(final JvmAnnotationValue it, final ITreeAppendable appendable, final GeneratorConfig config) {
    if (it instanceof JvmAnnotationAnnotationValue) {
      _toJavaLiteral((JvmAnnotationAnnotationValue)it, appendable, config);
      return;
    } else if (it instanceof JvmBooleanAnnotationValue) {
      _toJavaLiteral((JvmBooleanAnnotationValue)it, appendable, config);
      return;
    } else if (it instanceof JvmByteAnnotationValue) {
      _toJavaLiteral((JvmByteAnnotationValue)it, appendable, config);
      return;
    } else if (it instanceof JvmCharAnnotationValue) {
      _toJavaLiteral((JvmCharAnnotationValue)it, appendable, config);
      return;
    } else if (it instanceof JvmCustomAnnotationValue) {
      _toJavaLiteral((JvmCustomAnnotationValue)it, appendable, config);
      return;
    } else if (it instanceof JvmDoubleAnnotationValue) {
      _toJavaLiteral((JvmDoubleAnnotationValue)it, appendable, config);
      return;
    } else if (it instanceof JvmEnumAnnotationValue) {
      _toJavaLiteral((JvmEnumAnnotationValue)it, appendable, config);
      return;
    } else if (it instanceof JvmFloatAnnotationValue) {
      _toJavaLiteral((JvmFloatAnnotationValue)it, appendable, config);
      return;
    } else if (it instanceof JvmIntAnnotationValue) {
      _toJavaLiteral((JvmIntAnnotationValue)it, appendable, config);
      return;
    } else if (it instanceof JvmLongAnnotationValue) {
      _toJavaLiteral((JvmLongAnnotationValue)it, appendable, config);
      return;
    } else if (it instanceof JvmShortAnnotationValue) {
      _toJavaLiteral((JvmShortAnnotationValue)it, appendable, config);
      return;
    } else if (it instanceof JvmStringAnnotationValue) {
      _toJavaLiteral((JvmStringAnnotationValue)it, appendable, config);
      return;
    } else if (it instanceof JvmTypeAnnotationValue) {
      _toJavaLiteral((JvmTypeAnnotationValue)it, appendable, config);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, appendable, config).toString());
    }
  }
}
