package org.sqlproc.model.jvmmodel

import com.google.inject.Inject
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.sqlproc.model.processorModel.PojoEntity
import org.eclipse.xtext.common.types.JvmVisibility
import org.sqlproc.model.processorModel.PojoAttribute
import org.eclipse.xtext.xbase.jvmmodel.JvmAnnotationReferenceBuilder
import org.eclipse.xtext.xbase.jvmmodel.JvmTypeReferenceBuilder
import org.eclipse.xtext.common.types.JvmAnnotationTarget
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation
import org.eclipse.xtext.xbase.jvmmodel.JvmModelAssociator
import org.sqlproc.model.processorModel.PojoProcedure

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
   			for (attr : entity.features.map[feature].filter(PojoAttribute).filter(x | x.isIndex)) {
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
   			val enumAttrIndexes = entity.features.map[feature].filter(PojoAttribute).filter(x | x.isEnumIndex)
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
		   			for (attr : allRequiredAttributes) {
   						val type = if (attr.array) attr.type.addArrayTypeDimension.cloneWithProxies else attr.type
	   					parameters += entity.toParameter(attr.name, type)
   					}
	   				addAnnotationsX(entity.constructorAnnotations.map[a|a.annotation])
	   				body = '''
	   				super(«FOR attr : entity.parentRequiredAttributes SEPARATOR ","»«attr.name»«ENDFOR»);
	   				«FOR attr : entity.requiredAttributes»
	   				set«_toFirstUpper(attr.name)»(«attr.name»);
					«ENDFOR»
					'''
	   			]
   			}

			var procNames = entity.features.map[feature].filter(PojoProcedure).map[name].toSet
   			var _hasIds = false
   			for (attr : entity.features.map[feature].filter(PojoAttribute)) {
   				if (attr.name == "ids_")
   					_hasIds = true
   					
   				val type0 = attr.type ?: attr.initExpr?.inferredType ?: typeRef(String)
   				val type = if (attr.array) type0.addArrayTypeDimension.cloneWithProxies else type0
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
	   				val m1 = attr.toGetter(attr.name, attr.name, type, procNames) [
	   					addAnnotationsX(attr.getterAnnotations.map[a|a.annotation])
	   				]
	   				if (m1 != null)
	   					members += m1
	   				val m2 = attr.toSetterExt(attr.name, attr.name, type, typeRef(entityType), attr.updateColumn1, attr.updateColumn2, 
	   								attr.createColumn1, attr.createColumn2, procNames) [
	   					addAnnotationsX(attr.setterAnnotations.map[a|a.annotation])
	   				]
	   				if (m2 != null)
	   					members += m2
	   				val m3 = attr._toSetterExt(attr.name, attr.name, type, typeRef(entityType), attr.updateColumn1, attr.updateColumn2, 
	   								attr.createColumn1, attr.createColumn2, procNames)
	   				if (m3 != null)
	   					members += m3
		   			val operSuffix = entity.operatorsSuffix
		   			if (operSuffix != null) {
	   					members += entity.toField(attr.name + operSuffix, typeRef(String)) []
		   				members += attr.toGetter(attr.name + operSuffix, typeRef(String))
	   					members += attr.toSetter(attr.name + operSuffix, attr.name + operSuffix, typeRef(String))
	   					members += attr._toSetter(attr.name + operSuffix, attr.name + operSuffix, typeRef(String), typeRef(entityType), procNames)
	   				}
   				}
   			}
   			
   			for (proc : entity.features.map[feature].filter(PojoProcedure)) {
   				members += proc.toMethod(proc.name, proc.type ?: inferredType) [
   					documentation = proc.documentation
   					//addAnnotationsX(proc.annotations.map[a|a.annotation])
   					static = proc.static
   					//final = proc.final
   					for (param : proc.params) {
   						parameters += param.toParameter(param.name, param.parameterType)
   					}
   					body = proc.body
   				]
			}

			val processingIdsList = entity.processingIdsAttributes
			if (!processingIdsList.isEmpty) {
	   			val method = entity.toMethod('getProcessingIdForAttributes_', typeRef(StringBuilder)) [
	   				body = '''
						StringBuilder result = new StringBuilder("«simpleName»");
						«FOR f2:processingIdsList»
						«IF f2.isNative»result.append("@").append("«f2.name»");«ELSE»if («f2.name» != null)
							result.append("@").append(«IF f2.isPojo»"{").append(«f2.name».getProcessingIdForAttributes_()).append("}"«ELSE»"«f2.name»"«ENDIF»);«ENDIF»
						«ENDFOR»
						«IF entity.parent != null»
						result.append("@@").append(super.getProcessingIdForAttributes_());
						«ENDIF»
						return result;
	   				'''
	   			]
	   			members += method
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
						return java.util.Objects.hash(«FOR f2:hashCodeList SEPARATOR ", "»«f2.name»«ENDFOR»);
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
			if (!entity.features.map[feature].filter(PojoAttribute).empty) {
	   			members += entity.toMethod('toStringFull', typeRef(String)) [
	  				body = '''
	 					return "«simpleName» [«FOR f2:entity.features.map[feature].filter(PojoAttribute) SEPARATOR " + \", "»«f2.name»=" + «f2.name»«ENDFOR»«IF entity.superType != null && entity.superType instanceof PojoEntity» + super.toString()«ENDIF» + "]";
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
				members += entity.toField('nullValues_', identifierSetType) [
 					initializer = ''' new «HASH_SET»<String>()'''
	   			]
	   			members += entity.toMethod('setNull_', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(isDefType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (Attribute attribute : attributes)
							nullValues_.add(attribute.name());
   					'''
   				]	
	   			members += entity.toMethod('withNull_', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(isDefType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						setNull_(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('clearNull_', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(isDefType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (Attribute attribute : attributes)
							nullValues_.remove(attribute.name());
   					'''
   				]	
	   			members += entity.toMethod('_clearNull_', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(isDefType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						clearNull_(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('setNull_', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (String attribute : attributes)
							nullValues_.add(attribute);
   					'''
   				]	
	   			members += entity.toMethod('withNull_', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						setNull_(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('clearNull_', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (String attribute : attributes)
							nullValues_.remove(attribute);
   					'''
   				]	
	   			members += entity.toMethod('_clearNull_', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						clearNull_(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('isNull_', typeRef(Boolean)) [
   					parameters += entity.toParameter("attribute", typeRef(isDefType).cloneWithProxies)
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (attribute == null)
							throw new IllegalArgumentException();
						return nullValues_.contains(attribute.name());
   					'''
   				]	
	   			members += entity.toMethod('isNull_', typeRef(Boolean)) [
   					parameters += entity.toParameter("attrName", typeRef(String))
   					body = '''
						if (attrName == null)
							throw new IllegalArgumentException();
						return nullValues_.contains(attrName);
   					'''
   				]	
	   			members += entity.toMethod('isDef_', typeRef(Boolean)) [
   					parameters += entity.toParameter("attrName", typeRef(String))
   					parameters += entity.toParameter("isAttrNotNull", typeRef(Boolean))
   					body = '''
						if (attrName == null)
							throw new IllegalArgumentException();
						if (nullValues_.contains(attrName))
							return true;
						if (isAttrNotNull != null)
							return isAttrNotNull;
						return false;
   					'''
   				]	
	   			members += entity.toMethod('clearAllNull_', typeRef(Void.TYPE)) [
   					body = '''
						nullValues_ = new «HASH_SET»<String>();
   					'''
   				]	
				if (!processingIdsList.isEmpty) {
		   			members += entity.toMethod('getProcessingIdForNulls_', typeRef(StringBuilder)) [
	   					body = '''
							if (nullValues_ == null || nullValues_.isEmpty())
								return null;
							StringBuilder result = new StringBuilder("NULL");
							for (Attribute attribute : Attribute.values()) {
								if (nullValues_.contains(attribute.name()))
									result.append("@").append(attribute.name());
							}
							return result;
	   					'''
	   				]
   				}	
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
				members += entity.toField('initAssociations_', identifierSetType) [
 					initializer = ''' new «HASH_SET»<String>()'''
	   			]
	   			members += entity.toMethod('getInitAssociations_', identifierSetType.cloneWithProxies) [
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						return this.initAssociations_;
   					'''
   				]	
	   			members += entity.toMethod('setInitAssociations_', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("initAssociations_", identifierSetType.cloneWithProxies)
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						this.initAssociations_ = initAssociations_;
   					'''
   				]	
	   			members += entity.toMethod('setInit_', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("associations", typeRef(toInitType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (associations == null)
							throw new IllegalArgumentException();
						for (Association association : associations)
							initAssociations_.add(association.name());
   					'''
   				]	
	   			members += entity.toMethod('withInit_', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("associations", typeRef(toInitType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						setInit_(associations);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('clearInit_', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("associations", typeRef(toInitType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (associations == null)
							throw new IllegalArgumentException();
						for (Association association : associations)
							initAssociations_.remove(association.name());
   					'''
   				]	
	   			members += entity.toMethod('_clearInit_', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("associations", typeRef(toInitType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						clearInit_(associations);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('setInit_', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("associations", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						if (associations == null)
							throw new IllegalArgumentException();
						for (String association : associations)
							initAssociations_.add(association);
   					'''
   				]	
	   			members += entity.toMethod('withInit_', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("associations", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						setInit_(associations);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('clearInit_', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("associations", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						if (associations == null)
							throw new IllegalArgumentException();
						for (String association : associations)
							initAssociations_.remove(association);
   					'''
   				]	
	   			members += entity.toMethod('_clearInit_', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("associations", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						clearInit_(associations);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('toInit_', typeRef(Boolean)) [
   					parameters += entity.toParameter("association", typeRef(toInitType).cloneWithProxies)
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (association == null)
							throw new IllegalArgumentException();
						return initAssociations_.contains(association.name());
   					'''
   				]	
	   			members += entity.toMethod('toInit_', typeRef(Boolean)) [
   					parameters += entity.toParameter("association", typeRef(String))
   					body = '''
						if (association == null)
							throw new IllegalArgumentException();
						return initAssociations_.contains(association);
   					'''
   				]	
	   			members += entity.toMethod('clearAllInit_', typeRef(Void.TYPE)) [
   					body = '''
						initAssociations_ = new «HASH_SET»<String>();
   					'''
   				]	
				if (!processingIdsList.isEmpty) {
		   			members += entity.toMethod('getProcessingIdForAssociations_', typeRef(StringBuilder)) [
	   					body = '''
							if (initAssociations_ == null || initAssociations_.isEmpty())
								return null;
							StringBuilder result = new StringBuilder("ASSOC");
							for (Association association : Association.values()) {
								if (initAssociations_.contains(association.name()))
									result.append("@").append(association.name());
							}
							return result;
	   					'''
	   				]
   				}	
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
	   			for (attr: entity.features.map[feature].filter(PojoAttribute))
	   				opAttrType.members += entity.toEnumerationLiteral(attr.name)
				val identifierMapType = typeRef(java.util.Map, typeRef(String), typeRef(String))
				members += entity.toField('operators_', identifierMapType) [
 					initializer = ''' new «HASH_MAP»<String, String>()'''
	   			]
	   			members += entity.toMethod('getOperators_', identifierMapType) [
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						return operators_;
   					'''
   				]
	   			members += entity.toMethod('getOp_', typeRef(String)) [
   					parameters += entity.toParameter("attrName", typeRef(String))
   					body = '''
						if (attrName == null)
							throw new IllegalArgumentException();
						return operators_.get(attrName);
   					'''
   				]	
	   			members += entity.toMethod('setOp_', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("operator", typeRef(String))
   					parameters += entity.toParameter("attributes", typeRef(opAttrType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (OpAttribute attribute : attributes)
							operators_.put(attribute.name(), operator);
   					'''
   				]	
	   			members += entity.toMethod('withOp_', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("operator", typeRef(String))
   					parameters += entity.toParameter("attributes", typeRef(opAttrType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						setOp_(operator, attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('clearOp_', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(opAttrType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (OpAttribute attribute : attributes)
							operators_.remove(attribute.name());
   					'''
   				]	
	   			members += entity.toMethod('_clearOp_', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(opAttrType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						clearOp_(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('setOp_', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("operator", typeRef(String))
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (String attribute : attributes)
							operators_.put(attribute, operator);
   					'''
   				]	
	   			members += entity.toMethod('withOp_', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("operator", typeRef(String))
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						setOp_(operator, attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('clearOp_', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (String attribute : attributes)
							operators_.remove(attribute);
   					'''
   				]	
	   			members += entity.toMethod('_clearOp_', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						clearOp_(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('setNullOp_', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(opAttrType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (OpAttribute attribute : attributes)
							operators_.put(attribute.name(), "is null");
   					'''
   				]	
	   			members += entity.toMethod('withNullOp_', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(opAttrType).addArrayTypeDimension.cloneWithProxies)
	   				varArgs = true
	   				addAnnotationsX(entity.conflictAnnotations.map[a|a.annotation])
   					body = '''
						setNullOp_(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('setNullOp_', typeRef(Void.TYPE)) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						if (attributes == null)
							throw new IllegalArgumentException();
						for (String attribute : attributes)
							operators_.put(attribute, "is null");
   					'''
   				]	
	   			members += entity.toMethod('withNullOp_', typeRef(entityType).cloneWithProxies) [
   					parameters += entity.toParameter("attributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
   					body = '''
						setNullOp_(attributes);
						return this;
   					'''
   				]	
	   			members += entity.toMethod('clearAllOps_', typeRef(Void.TYPE)) [
   					body = '''
						operators_ = new «HASH_MAP»<String, String>();
   					'''
   				]	
				if (!processingIdsList.isEmpty) {
		   			members += entity.toMethod('getProcessingIdForOperators_', typeRef(StringBuilder)) [
	   					body = '''
							if (operators_ == null || operators_.isEmpty())
								return null;
							StringBuilder result = new StringBuilder("OPER");
							for (OpAttribute opAttribute : OpAttribute.values()) {
								if (operators_.containsKey(opAttribute.name()))
									result.append("@").append(opAttribute.name()).append("=").append(operators_.get(opAttribute.name()));
							}
							return result;
	   					'''
	   				]
   				}	
			}
			
			if (!processingIdsList.isEmpty) {
				val hasIds = _hasIds
	   			val method = entity.toMethod('getProcessingId_', typeRef(String)) [
   					parameters += entity.toParameter("moreAttributes", typeRef(String).addArrayTypeDimension)
	   				varArgs = true
	   				body = '''
						«IF hasIds»
						if (ids_ != null && !ids_.isEmpty())
							return null;
						«ENDIF»
						StringBuilder result = getProcessingIdForAttributes_();
						«IF !isDefList.isEmpty»
						StringBuilder processingIdForNulls = getProcessingIdForNulls_();
						if (processingIdForNulls != null)
							result.append(",").append(processingIdForNulls);
						«ENDIF»
						«IF !toInitList.isEmpty»
						StringBuilder processingIdForAssociations = getProcessingIdForAssociations_();
						if (processingIdForAssociations != null)
							result.append(",").append(processingIdForAssociations);
						«ENDIF»
						«IF entity.hasOperators»
						StringBuilder processingIdForOperators = getProcessingIdForOperators_();
						if (processingIdForOperators != null)
							result.append(",").append(processingIdForOperators);
						«ENDIF»
						if (moreAttributes != null && moreAttributes.length > 0) {
							result.append(",MORE");
							for (String moreAttr : moreAttributes)
								result.append("@").append(moreAttr);
						}
						return result.toString();
	   				'''
	   			]
	   			members += method
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

