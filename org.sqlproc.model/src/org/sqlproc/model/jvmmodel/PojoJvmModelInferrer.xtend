package org.sqlproc.model.jvmmodel

import com.google.inject.Inject
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder
import org.sqlproc.model.processorModel.Package
import org.eclipse.xtext.naming.IQualifiedNameProvider
import static extension org.eclipse.xtext.EcoreUtil2.*
import org.sqlproc.model.processorModel.PojoEntity
import org.sqlproc.model.processorModel.AnnotatedEntity
import org.sqlproc.model.processorModel.EnumEntity
import org.sqlproc.model.processorModel.EnumAttributeDirectiveValues
import org.sqlproc.model.processorModel.DaoEntity
import org.sqlproc.model.processorModel.EnumAttributeValue
import java.util.List
import org.eclipse.xtext.xbase.XStringLiteral
import org.eclipse.xtext.xbase.XNumberLiteral
import org.eclipse.xtext.common.types.JvmVisibility
import org.eclipse.xtext.common.types.JvmGenericType
import org.eclipse.xtext.common.types.JvmMember
import org.sqlproc.model.processorModel.PojoAttribute
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.xbase.jvmmodel.JvmAnnotationReferenceBuilder
import org.eclipse.xtext.xbase.jvmmodel.JvmTypeReferenceBuilder
import org.eclipse.xtext.common.types.JvmAnnotationTarget
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations
import org.eclipse.xtext.xbase.jvmmodel.JvmModelAssociator

/**
 * <p>Infers a JVM model from the source model.</p> 
 *
 * <p>The JVM model should contain all elements that would appear in the Java code 
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>     
 */
class PojoJvmModelInferrer {

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
   	val HASH_SET = 'java.util.HashSet'
   	val POJO = 'org.sqlproc.engine.annotation.Pojo'

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
   	def void inferPojo(PojoEntity entity, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		val entityType = entity.toClass(entity.fullyQualifiedName)
   		entityType.getAnnotations().add(annotationRef(POJO))
   		val simpleName = entity.name
   		val sernum = entity.sernum
   		acceptor.accept(entityType) [
   			documentation = entity.documentation
   			if (entity.isAbstract)
   				abstract = true
   			for (an : entity.standardAnnotations.map[a|a.annotation]) {
   				if (an.annotationType.identifier == SERIALIZABLE) {
   					superTypes += typeRef(an.annotationType)
   				}
   				else {
   					addAnnotationX(an)
   				}
   			}
   			for (impl : entity.getImplements) {
   				superTypes += impl.implements.cloneWithProxies
   			}
   			val ext = entity.getExtends
   			if (entity.superType != null) {
   				superTypes += entity.superType.cloneWithProxies
   			}
   			else if (ext != null) {
   				superTypes += ext.extends.cloneWithProxies
			}
						   				
   			if (sernum != null) {
   				superTypes += typeRef(SERIALIZABLE)
				members += entity.toField('serialVersionUID', typeRef(long)) [
 					static = true
 					final = true
 					initializer = '''«sernum»L'''
   				]
   			}
   			for (attr : entity.attributes.filter(x | x.isIndex)) {
				members += entity.toField('ORDER_BY_'+attr.constName, typeRef(String)) [
 					static = true
 					final = true
 					visibility = JvmVisibility.PUBLIC
	   				addAnnotationsX(entity.staticAnnotations.map[a|a.annotation])
					initializer = if (attr.getIndex != null) '''"«attr.getIndex»"''' else '''"«attr.constName»"'''
   				]
   			}
   			for (entry : entity.index.entrySet) {
				members += entity.toField('ORDER_BY_'+constName(entry.value), typeRef(String)) [
 					static = true
 					final = true
 					visibility = JvmVisibility.PUBLIC
 					initializer = '''"«entry.key»"'''
	   				addAnnotationsX(entity.staticAnnotations.map[a|a.annotation])
   				]
   			}
   			val enumAttrIndexes = entity.attributes.filter(x | x.isEnumIndex)
   			if (!enumAttrIndexes.isEmpty) {
   				val orderType = entity.toEnumerationType('Order') [
 					static = true
 					visibility = JvmVisibility.PUBLIC
   				]
   				members += orderType
	   			for (attr: enumAttrIndexes)
	   				orderType.members += entity.toEnumerationLiteral(attr.constName)
   			}
   			val enumIndexes = entity.enumIndex.entrySet
   			if (!enumIndexes.isEmpty) {
   				val orderType = entity.toEnumerationType('Order') [
 					static = true
 					visibility = JvmVisibility.PUBLIC
   				]
   				members += orderType
	   			for (entry: enumIndexes)
	   				orderType.members += entity.toEnumerationLiteral(constName(entry.value))
   			}
   			
   			members += entity.toConstructor [
   				addAnnotationsX(entity.constructorAnnotations.map[a|a.annotation])
   			]
   			val allRequiredAttributes = entity.allRequiredAttributes
   			if (!allRequiredAttributes.empty) {
	   			members += entity.toConstructor [
		   			for (attr : allRequiredAttributes)
	   					parameters += entity.toParameter(attr.name, attr.type)
	   				addAnnotationsX(entity.constructorAnnotations.map[a|a.annotation])
	   				body = '''
	   				super(«FOR attr : entity.parentRequiredAttributes SEPARATOR ","»«attr.name»«ENDFOR»);
	   				«FOR attr : entity.requiredAttributes»
	   				set«_toFirstUpper(attr.name)»(«attr.name»);
					«ENDFOR»
					'''
	   			]
   			}
   			
   			for (attr : entity.attributes) {
   				val type = attr.type ?: attr.initExpr?.inferredType ?: typeRef(String)
   				members += entity.toField(attr.name, type) [
   					documentation = attr.documentation
   					addAnnotationsX(attr.attributeAnnotations.map[a|a.annotation])
   					static = attr.static
   					//final = attr.final
   					if (attr.static)
   						visibility = JvmVisibility.PUBLIC 
   					if (attr.initExpr != null) {
 						initializer = attr.initExpr
 					} 
 					else if (isList(attr)) {
 						initializer = '''new java.util.Array«type.simpleName»()'''
 					}
 					else if (isOptLock(attr)) {
 						initializer = '''0'''
					}
   				]
   				if (!attr.static) {
	   				members += attr.toGetter(attr.name, attr.name, type) [
	   					addAnnotationsX(attr.getterAnnotations.map[a|a.annotation])
	   				]
	   				members += attr.toSetterExt(attr.name, attr.name, type, typeRef(entityType), attr.updateColumn1, attr.updateColumn2, 
	   								attr.createColumn1, attr.createColumn2) [
	   					addAnnotationsX(attr.setterAnnotations.map[a|a.annotation])
	   				]
	   				members += attr._toSetterExt(attr.name, attr.name, type, typeRef(entityType), attr.updateColumn1, attr.updateColumn2, 
	   								attr.createColumn1, attr.createColumn2)
		   			val operSuffix = entity.operatorsSuffix
		   			if (operSuffix != null) {
	   					members += entity.toField(attr.name + operSuffix, typeRef(String)) []
		   				members += attr.toGetter(attr.name + operSuffix, typeRef(String))
	   					members += attr.toSetter(attr.name + operSuffix, attr.name + operSuffix, typeRef(String))
	   					members += attr._toSetter(attr.name + operSuffix, attr.name + operSuffix, typeRef(String), typeRef(entityType))
	   				}
   				}
   			}
   			
   			for (proc : entity.procedures) {
   				members += proc.toMethod(proc.name, proc.type ?: inferredType) [
   					documentation = proc.documentation
   					addAnnotationsX(proc.annotations.map[a|a.annotation])
   					static = proc.static
   					//final = proc.final
   					for (param : proc.params) {
   						parameters += param.toParameter(param.name, param.parameterType)
   					}
   					body = proc.body
   				]
			}
			   			
   			val equalsList = entity.equalsAttributes
   			if (!equalsList.isEmpty) {
	   			val method = entity.toMethod('equals', typeRef(boolean)) [
   					parameters += entity.toParameter("obj", typeRef(Object))
   					body = '''
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						«simpleName» other = («simpleName») obj;
						«FOR f2:equalsList»
						«IF f2.isNative»if («f2.name» != other.«f2.name»)«ELSE»if («f2.name» == null || !«f2.name».equals(other.«f2.name»))«ENDIF»
							return false;
						«ENDFOR»
						return true;
   					'''
	   			]
	   			method.getAnnotations().add(annotationRef(Override))
	   			members += method
			}
   			
   			val hashCodeList = entity.hashCodeAttributes
   			if (!hashCodeList.isEmpty) {
	   			val method = entity.toMethod('hashCode', typeRef(int)) [
	   				body = '''
						final int prime = 31;
						int result = 1;
						«FOR f2:hashCodeList»
						result = prime * result + «IF f2.isNative»(int) («f2.name» ^ («f2.name» >>> 32))«ELSE»((«f2.name» != null) ? «f2.name».hashCode() : 0)«ENDIF»;
						«ENDFOR»
						return result;
	   				'''
	   			]
	   			method.getAnnotations().add(annotationRef(Override))
	   			members += method
			}
   			
   			val toStringList = entity.toStringAttributes
   			if (!toStringList.isEmpty) {
	   			val method = entity.toMethod('toString', typeRef(String)) [
	   				body = '''
	   					return "«simpleName» [«FOR f2:toStringList SEPARATOR " + \", "»«f2.name»=" + «f2.name»«ENDFOR»«IF entity.superType != null && entity.superType instanceof PojoEntity» + super.toString()«ENDIF» + "]";
	   				'''
	   			]
	   			method.getAnnotations().add(annotationRef(Override))
	   			members += method
			}
			if (!entity.attributes.empty) {
	   			members += entity.toMethod('toStringFull', typeRef(String)) [
	  				body = '''
	 					return "«simpleName» [«FOR f2:entity.attributes SEPARATOR " + \", "»«f2.name»=" + «f2.name»«ENDFOR»«IF entity.superType != null && entity.superType instanceof PojoEntity» + super.toString()«ENDIF» + "]";
	 				'''
	   			]
   			}

   			val isDefList = entity.isDefAttributes
   			if (!isDefList.isEmpty) {
   				val isDefType = entity.toEnumerationType('Attribute') []
   				members += isDefType
	   			for (attr: isDefList)
	   				isDefType.members += entity.toEnumerationLiteral(attr.name)
				val identifierSetType = typeRef(java.util.Set, typeRef(String))
				members += entity.toField('nullValues', identifierSetType) [
 					initializer = ''' new «HASH_SET»<String>()'''
	   			]
	   			members += entity.toMethod('setNull', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(isDefType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (Attribute attribute : attributes)
							nullValues.add(attribute.name());
   					'''
   				]	
	   			members += entity.toMethod('_setNull', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(isDefType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						setNull(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('clearNull', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(isDefType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (Attribute attribute : attributes)
							nullValues.remove(attribute.name());
   					'''
   				]	
	   			members += entity.toMethod('_clearNull', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(isDefType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						clearNull(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('setNull', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (String attribute : attributes)
							nullValues.add(attribute);
   					'''
   				]	
	   			members += entity.toMethod('_setNull', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						setNull(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('clearNull', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (String attribute : attributes)
							nullValues.remove(attribute);
   					'''
   				]	
	   			members += entity.toMethod('_clearNull', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						clearNull(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('isNull', typeRef(Boolean)) [
   					parameters += entity.toParameter("attribute", typeRef(isDefType).cloneWithProxies)
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (attribute == null)
							throw new IllegalArgumentException();
						return nullValues.contains(attribute.name());
   					'''
   				]	
	   			members += entity.toMethod('isNull', typeRef(Boolean)) [
   					parameters += entity.toParameter("attrName", typeRef(String))
   					body = '''
						if (attrName == null)
							throw new IllegalArgumentException();
						return nullValues.contains(attrName);
   					'''
   				]	
	   			members += entity.toMethod('isDef', typeRef(Boolean)) [
   					parameters += entity.toParameter("attrName", typeRef(String))
   					parameters += entity.toParameter("isAttrNotNull", typeRef(Boolean))
   					body = '''
						if (attrName == null)
							throw new IllegalArgumentException();
						if (nullValues.contains(attrName))
							return true;
						if (isAttrNotNull != null)
							return isAttrNotNull;
						return false;
   					'''
   				]	
	   			members += entity.toMethod('clearAllNull', typeRef(Void.TYPE)) [
   					body = '''
						nullValues = new «HASH_SET»<String>();
   					'''
   				]	
   			}
   			
   			val enumDefList = entity.enumDefAttributes
   			if (!enumDefList.isEmpty) {
   				val isDefType = entity.toEnumerationType('Attribute') []
   				members += isDefType
	   			for (attr: enumDefList)
	   				isDefType.members += entity.toEnumerationLiteral(attr.name)
	   				
	   		}

   			val toInitList = entity.toInitAttributes
   			if (!toInitList.isEmpty) {
   				val toInitType = entity.toEnumerationType('Association') []
   				members += toInitType
	   			for (attr: toInitList)
	   				toInitType.members += entity.toEnumerationLiteral(attr.name)
				val identifierSetType = typeRef(java.util.Set, typeRef(String))
				members += entity.toField('initAssociations', identifierSetType) [
 					initializer = ''' new «HASH_SET»<String>()'''
	   			]
	   			members += entity.toMethod('getInitAssociations', identifierSetType.cloneWithProxies) [
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						return this.initAssociations;
   					'''
   				]	
	   			members += entity.toMethod('setInitAssociations', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("initAssociations", identifierSetType.cloneWithProxies)
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						this.initAssociations = initAssociations;
   					'''
   				]	
	   			members += entity.toMethod('setInit', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("associations", typeRef(toInitType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (associations == null)
							throw new IllegalArgumentException();
						for (Association association : associations)
							initAssociations.add(association.name());
   					'''
   				]	
	   			members += entity.toMethod('_setInit', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("associations", typeRef(toInitType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						setInit(associations);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('clearInit', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("associations", typeRef(toInitType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (associations == null)
							throw new IllegalArgumentException();
						for (Association association : associations)
							initAssociations.remove(association.name());
   					'''
   				]	
	   			members += entity.toMethod('_clearInit', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("associations", typeRef(toInitType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						clearInit(associations);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('setInit', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("associations", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						if (associations == null)
							throw new IllegalArgumentException();
						for (String association : associations)
							initAssociations.add(association);
   					'''
   				]	
	   			members += entity.toMethod('_setInit', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("associations", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						setInit(associations);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('clearInit', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("associations", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						if (associations == null)
							throw new IllegalArgumentException();
						for (String association : associations)
							initAssociations.remove(association);
   					'''
   				]	
	   			members += entity.toMethod('_clearInit', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("associations", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						clearInit(associations);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('toInit', typeRef(Boolean)) [
   					parameters += entity.toParameter("association", typeRef(toInitType).cloneWithProxies)
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (association == null)
							throw new IllegalArgumentException();
						return initAssociations.contains(association.name());
   					'''
   				]	
	   			members += entity.toMethod('toInit', typeRef(Boolean)) [
   					parameters += entity.toParameter("association", typeRef(String))
   					body = '''
						if (association == null)
							throw new IllegalArgumentException();
						return initAssociations.contains(association);
   					'''
   				]	
	   			members += entity.toMethod('clearAllInit', typeRef(Void.TYPE)) [
   					body = '''
						initAssociations = new «HASH_SET»<String>();
   					'''
   				]	
   			}
   			
   			val processingIdsList = entity.processingIdsAttributes
   			if (!processingIdsList.isEmpty || !toInitList.isEmpty) {
	   			val method = entity.toMethod('getProcessingId', typeRef(int)) [
	   				body = '''
						java.util.List<Object> attributes = new java.util.ArrayList<Object>();
						«FOR f2:processingIdsList»
						attributes.add(«f2.name»);
						«ENDFOR»
						return java.util.Objects.hashCode(attributes.toArray(new Object[0]));
	   				'''
	   			]
	   			members += method
			}

   			val enumInitList = entity.enumInitAttributes
   			if (!enumInitList.isEmpty) {
   				val toInitType = entity.toEnumerationType('Association') []
   				members += toInitType
	   			for (attr: enumInitList)
	   				toInitType.members += entity.toEnumerationLiteral(attr.name)
	   		}

			if (entity.hasOperators) {
   				val opAttrType = entity.toEnumerationType('OpAttribute') []
   				members += opAttrType
	   			for (attr: entity.attributes)
	   				opAttrType.members += entity.toEnumerationLiteral(attr.name)
				val identifierMapType = typeRef(java.util.Map, typeRef(String), typeRef(String))
				members += entity.toField('operators', identifierMapType) [
 					initializer = ''' new «HASH_MAP»<String, String>()'''
	   			]
	   			members += entity.toMethod('getOperators', identifierMapType) [
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						return operators;
   					'''
   				]
	   			members += entity.toMethod('setOp', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("operator", typeRef(String))
   					parameters += entity.toParameter("attributes", typeRef(opAttrType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (OpAttribute attribute : attributes)
							operators.put(attribute.name(), operator);
   					'''
   				]	
	   			members += entity.toMethod('_setOp', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("operator", typeRef(String))
   					parameters += entity.toParameter("attributes", typeRef(opAttrType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						setOp(operator, attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('clearOp', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(opAttrType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (OpAttribute attribute : attributes)
							operators.remove(attribute.name());
   					'''
   				]	
	   			members += entity.toMethod('_clearOp', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(opAttrType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						clearOp(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('setOp', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("operator", typeRef(String))
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (String attribute : attributes)
							operators.put(attribute, operator);
   					'''
   				]	
	   			members += entity.toMethod('_setOp', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("operator", typeRef(String))
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						setOp(operator, attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('clearOp', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (String attribute : attributes)
							operators.remove(attribute);
   					'''
   				]	
	   			members += entity.toMethod('_clearOp', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						clearOp(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('setNullOp', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(opAttrType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (OpAttribute attribute : attributes)
							operators.put(attribute.name(), "is null");
   					'''
   				]	
	   			members += entity.toMethod('_setNullOp', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(opAttrType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						setNullOp(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('setNullOp', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (String attribute : attributes)
							operators.put(attribute, "is null");
   					'''
   				]	
	   			members += entity.toMethod('_setNullOp', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						setNullOp(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('clearAllOps', typeRef(Void.TYPE)) [
   					body = '''
						operators = new «HASH_MAP»<String, String>();
   					'''
   				]	
			}
   		]
   	}

	@Inject extension JvmModelAssociator associator
   	
	def addAnnotationsX(/* @Nullable */ JvmAnnotationTarget target, /* @Nullable */ Iterable<? extends XAnnotation> annotations) {
		if(annotations == null || annotations.empty || target == null) 
			return;
		for (a : annotations)
			addAnnotationX(target, a)
	}
   	
	def addAnnotationX(/* @Nullable */ JvmAnnotationTarget target, /* @Nullable */ XAnnotation annotation) {
		if(annotation == null || annotation.annotationType == null || target == null) 
			return;
		val associatedElements = annotation.jvmElements
		if (!associatedElements.empty) {
			// transform it to List to avoid concurrent modification exception
			associatedElements.toList.forEach[assoc | associator.removeAllAssociation(assoc)]
		}
//		println("AAAAAAAAAAAAAA")
//		println(target.getAnnotations())
//		println(target)
//		println(annotation)
		addAnnotation(target, annotation);
	}

    def String toFirstUpper(String s) {
        if (s == null || s.length() == 0)
            return s;
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        if (s.length() == 1)
            return s.toUpperCase();
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    def String _toFirstUpper(String name) {
        val l = name.length();
        if (l == 0)
            return name;
        if (l == 1)
            return name.toUpperCase();
        val c = name.charAt(1);
        if (Character.isUpperCase(c))
            return name;
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}

