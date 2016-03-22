package org.sqlproc.model.jvmmodel

import com.google.inject.Inject
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.sqlproc.model.processorModel.EnumEntity
import org.sqlproc.model.processorModel.EnumAttributeDirectiveValues
import org.sqlproc.model.processorModel.EnumAttributeValue
import java.util.List
import org.eclipse.xtext.xbase.XStringLiteral
import org.eclipse.xtext.xbase.XNumberLiteral
import org.eclipse.xtext.common.types.JvmVisibility
import org.eclipse.xtext.xbase.jvmmodel.JvmAnnotationReferenceBuilder
import org.eclipse.xtext.xbase.jvmmodel.JvmTypeReferenceBuilder

/**
 * <p>Infers a JVM model from the source model.</p> 
 *
 * <p>The JVM model should contain all elements that would appear in the Java code 
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>     
 */
class EnumJvmModelInferrer extends AbstractModelInferrer {

	public extension JvmAnnotationReferenceBuilder _annotationTypesBuilder
	public extension JvmTypeReferenceBuilder _typeReferenceBuilder

    /**
     * convenience API to build and initialize JVM types and their members.
     */
	@Inject extension ProcessorTypesBuilder
	@Inject extension IQualifiedNameProvider
	@Inject extension ProcessorGeneratorUtils
	
   	val SERIALIZABLE = 'java.io.Serializable'
   	val HASH_MAP = 'java.util.HashMap'

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
   	def void inferEnum(EnumEntity entity, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		val entityType = entity.toEnumerationType(entity.fullyQualifiedName.toString) []
   		val simpleName = entity.name
   		acceptor.accept(entityType) [
   			documentation = entity.documentation
   			for (a : entity.annotations.map[a|a.annotation]) {
   				if (a.annotationType.identifier == SERIALIZABLE) {
   					superTypes += typeRef(a.annotationType)
   				}
   				else {
   					addAnnotation(a)
   				}
   			}
			val List<EnumAttributeValue> values = newArrayList()
			for (dir : entity.attribute.directives) {
				if (dir instanceof EnumAttributeDirectiveValues) {
					val dv = dir as EnumAttributeDirectiveValues
					for (epv : dv.values) {
						val value = switch(epv.value) {
							XStringLiteral: '"'+(epv.value as XStringLiteral).value+'"'
							XNumberLiteral: (epv.value as XNumberLiteral).value
						}
						members += entity.toEnumerationLiteral(epv.name, [
							initializer = [
								append('('+value+')')
							]
						])
						values += epv
					}
				}
			}
			val identifierMapType = typeRef(java.util.Map, entity.attribute.type, typeRef(entityType).cloneWithProxies)
			members += entity.toField('identifierMap', identifierMapType) [
 				static = true
 				initializer = ''' identifierMapBuild()'''
   			]
   			members += entity.toMethod ('identifierMapBuild', identifierMapType) [
 				static = true
				body = '''
					Map<«entity.attribute.type», «simpleName»> _identifierMap = new «HASH_MAP»<«entity.attribute.type», «simpleName»>();
					for («simpleName» value : «simpleName».values()) {
						_identifierMap.put(value.getValue(), value);
					}
					return _identifierMap;
				'''
			]
			members += entity.toField('value', entity.attribute.type)
   			members += entity.toConstructor[
   				parameters += entity.toParameter('value', entity.attribute.type)
   				visibility = JvmVisibility.PRIVATE
   				body = '''
					this.value = value;
				'''
   			]
   			members += entity.toMethod ('fromValue', typeRef(entityType).cloneWithProxies) [
				parameters += entity.toParameter('value', entity.attribute.type)
 				static = true
				body = '''
					«simpleName» result = identifierMap.get(value);
					if (result == null) {
						throw new IllegalArgumentException("No «simpleName» for value: " + value);
					}
					return result;
				'''
			]
   			members += entity.toMethod ('getValue', entity.attribute.type) [
				body = '''
					return value;
				'''
			]
   			members += entity.toMethod ('getName', typeRef(String)) [
				body = '''
					return name();
				'''
			]

//   			for (proc : entity.procedures) {
//   				members += proc.toMethod(proc.name, proc.type ?: inferredType) [
//   					documentation = proc.documentation
//   					//addAnnotations(proc.annotations.map[a|a.annotation])
//   					static = proc.static
//   					//final = proc.final
//   					for (param : proc.params) {
//   						parameters += param.toParameter(param.name, param.parameterType)
//   					}
//   					body = proc.body
//   				]
//			}
   		]
   	}
}

