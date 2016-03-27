package org.sqlproc.model.generator

import org.eclipse.xtext.xbase.compiler.JvmModelGenerator
import org.eclipse.xtext.common.types.JvmEnumerationLiteral
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable
import org.eclipse.xtext.xbase.compiler.GeneratorConfig
import org.eclipse.xtext.common.types.JvmFormalParameter
import org.eclipse.xtext.common.types.JvmGenericArrayTypeReference
import org.eclipse.xtext.common.types.JvmCustomAnnotationValue
import org.eclipse.xtext.xbase.XExpression
import org.eclipse.xtext.xbase.impl.XStringLiteralImpl
import org.eclipse.xtext.xbase.XStringLiteral
import com.google.inject.Inject
import org.eclipse.xtext.xbase.compiler.XbaseCompiler
import org.eclipse.xtext.xbase.XNumberLiteral

class ProcessorModelGenerator extends JvmModelGenerator {
	
	@Inject XbaseCompiler compiler
	
	override generateEnumLiteral(JvmEnumerationLiteral it, ITreeAppendable appendable, GeneratorConfig config) {
      super.generateEnumLiteral(it, appendable, config)
      compilationStrategy?.apply(appendable)
    }

	override generateParameter(JvmFormalParameter it, ITreeAppendable appendable, boolean vararg, GeneratorConfig config) {
		if (it.name != 'sqlControl')
			super.generateParameter(it, appendable, vararg, config)
		else {
			val tracedAppendable = appendable.trace(it)
			annotations.generateAnnotations(tracedAppendable, false, config)
			parameterType.serializeSafely("Object", tracedAppendable)
			tracedAppendable.append(" ")
			val name = tracedAppendable.declareVariable(it, makeJavaIdentifier(simpleName))
			tracedAppendable.traceSignificant(it).append(name)
		}
	}

	override def dispatch void toJavaLiteral(JvmCustomAnnotationValue it, ITreeAppendable appendable, GeneratorConfig config) {
		if(values.isEmpty)
			appendable.append('{}')
		else 
			appendable.forEachWithShortcut(values.filter(XExpression), [
				if (it instanceof XStringLiteral) {
					appendable.append('"' + doConvertToJavaString(it.value) + '"')
				} else if (it instanceof XNumberLiteral) {
					appendable.append(it.value)
				}
				else {
					compiler.toJavaExpression(it, appendable)
				}
			])
	}
}