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
import org.sqlproc.model.processorModel.DaoDirectiveCrud
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference
import org.sqlproc.model.processorModel.DaoDirectiveQuery
import java.util.Map
import org.sqlproc.model.processorModel.DaoDirectiveParameters
import org.sqlproc.model.processorModel.FunctionCallQuery
import org.sqlproc.model.processorModel.ProcedureCallQuery
import org.sqlproc.model.processorModel.FunctionCall
import org.sqlproc.model.processorModel.ProcedureUpdate
import org.sqlproc.model.processorModel.FunctionQuery
import org.sqlproc.model.processorModel.DaoFunProcDirective
import org.eclipse.xtext.naming.QualifiedName

/**
 * <p>Infers a JVM model from the source model.</p> 
 *
 * <p>The JVM model should contain all elements that would appear in the Java code 
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>     
 */
class DaoJvmModelInferrer extends AbstractModelInferrer {

	public extension JvmAnnotationReferenceBuilder _annotationTypesBuilder
	public extension JvmTypeReferenceBuilder _typeReferenceBuilder

    /**
     * convenience API to build and initialize JVM types and their members.
     */
	@Inject extension ProcessorTypesBuilder
	@Inject extension IQualifiedNameProvider
	@Inject extension ProcessorGeneratorUtils

   	val SERIALIZABLE = 'java.io.Serializable'
   	val ENGINE_FACTORY = 'org.sqlproc.engine.SqlEngineFactory'
   	val SESSION_FACTORY = 'org.sqlproc.engine.SqlSessionFactory'
   	val SQL_SESSION = 'org.sqlproc.engine.SqlSession'
   	val SQL_CONTROL = 'org.sqlproc.engine.SqlControl'
   	val SQL_RUNTIME_EXCEPTION = 'org.sqlproc.engine.SqlRuntimeException'
   	val LOGGER = 'org.slf4j.Logger'
   	val LOGGER_FACTORY = 'org.slf4j.LoggerFactory'
   	val CRUD_ENGINE = 'org.sqlproc.engine.SqlCrudEngine'
   	val QUERY_ENGINE = 'org.sqlproc.engine.SqlQueryEngine'
   	val PROCEDURE_ENGINE = 'org.sqlproc.engine.SqlProcedureEngine'
   	val SQL_ROW_PROCESSOR = 'org.sqlproc.engine.SqlRowProcessor'
   	val SET = 'java.util.Set'
   	val HASH_SET = 'java.util.HashSet'
   	val MAP = 'java.util.Map'
   	val HASH_MAP = 'java.util.HashMap'
   	val LIST = 'java.util.List'
   	val ARRAY_LIST = 'java.util.ArrayList'
   	val SQL_STANDARD_CONTROL = 'org.sqlproc.engine.impl.SqlStandardControl'

   	def void inferDaoIfx(DaoEntity entity, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		val pojo = entity.pojo
   		if (pojo == null && !entity.isFunctionProcedure) {
   			println("Missing POJO for "+entity)
   			return
   		}

   		val entityType = entity.toInterface(entity.fullyQualifiedName.toString) []
   		val pojoType = pojo?.toClass(pojo?.fullyQualifiedName)
   		if (pojoType == null && !entity.isFunctionProcedure) {
   			println("Missing POJOTYPE for "+entity)
   			return
   		}
   		
   		val primaryKey = pojo.primaryKey
   		val pkDir = primaryKey?.primaryKeyDir
   		val simpleName = entity.name
   		val sernum = entity.sernum
   		
   		acceptor.accept(entityType) [
   			documentation = entity.documentation
   			for (impl : entity.getImplements) {
   				if (impl.isGenerics) {
   					val genericType = typeRef(impl.implements, typeRef(pojoType))
   					superTypes += genericType
   				}
   				else {
   					superTypes += impl.implements.cloneWithProxies
   				}
   			}
//   			val ext = entity.getExtends
//   			if (ext != null)
//   				superTypes += ext.extends.cloneWithProxies
//   			if (entity.superType != null)
//   				superTypes += entity.superType.cloneWithProxies
   				
   			if (sernum != null) {
   				superTypes += typeRef(SERIALIZABLE)
   			}

   			var Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses = null
   			
   			for (dir : entity.directives) {
   				if (dir instanceof DaoDirectiveCrud) {
   					if (moreResultClasses == null)
   						moreResultClasses = entity.getMoreResultClasses
   					inferInsertIfx(entity, dir as DaoDirectiveCrud, entityType, simpleName, pojo, pojoType, members)
   					inferGetIfx(entity, dir as DaoDirectiveCrud, entityType, simpleName, pojo, pojoType, members, moreResultClasses)
   					inferUpdateIfx(entity, dir as DaoDirectiveCrud, entityType, simpleName, pojo, pojoType, members)
   					inferDeleteIfx(entity, dir as DaoDirectiveCrud, entityType, simpleName, pojo, pojoType, members)
   				}
   				else if (dir instanceof DaoDirectiveQuery) {
   					if (moreResultClasses == null)
   						moreResultClasses = entity.getMoreResultClasses
   					inferListIfx(entity, dir as DaoDirectiveQuery, entityType, simpleName, pojo, pojoType, members, moreResultClasses)
   					inferQueryIfx(entity, dir as DaoDirectiveQuery, entityType, simpleName, pojo, pojoType, members, moreResultClasses)
   					if (primaryKey != null && pkDir.fromTo != null && !pojo.toInitAttributes.isEmpty)
   						inferListFromToIfx(entity, dir as DaoDirectiveQuery, entityType, simpleName, pojo, pojoType, members, moreResultClasses)
   					inferCountIfx(entity, dir as DaoDirectiveQuery, entityType, simpleName, pojo, pojoType, members, moreResultClasses)
   				}
   				else if (dir instanceof DaoFunProcDirective) {
   					inferFunctionProcedureIfx(entity, (dir as DaoFunProcDirective).type, (dir as DaoFunProcDirective).paramlist, entityType, simpleName, members)
   				}
   			}
   			if (moreResultClasses != null && !moreResultClasses.empty) {
   				inferMoreResultClassesIfx(entity, entityType, simpleName, pojo, pojoType, members, moreResultClasses)
			}
   		]
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
   	def void inferDao(DaoEntity entity, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase, String implPackage) {
   		val pojo = entity.pojo
   		if (pojo == null && !entity.isFunctionProcedure) {
   			println("Missing POJO for "+entity)
   			return
   		}

   		val entityType = entity.toClass(entity.daoFullyQualifiedName(implPackage))
   		val entityTypeIfx = if (implPackage != null ) entity.toInterface(entity.fullyQualifiedName.toString) []
   		val pojoType = pojo?.toClass(pojo?.fullyQualifiedName)
   		if (pojoType == null && !entity.isFunctionProcedure) {
   			println("Missing POJOTYPE for "+entity)
   			return
   		}
   		
   		val primaryKey = pojo.primaryKey
   		val pkDir = primaryKey?.primaryKeyDir
   		val simpleName = entity.name
   		val sernum = entity.sernum
   		
   		acceptor.accept(entityType) [
   			documentation = entity.documentation
   			for (an : entity.standardAnnotations.map[a|a.annotation]) {
   				if (an.annotationType.identifier == SERIALIZABLE) {
   					superTypes += typeRef(an.annotationType)
   				}
   				else {
   					addAnnotation(an)
   				}
   			}
   			if (entityTypeIfx != null)
   				superTypes += typeRef(entityTypeIfx)
   			for (impl : entity.getImplements) {
   				if (impl.isGenerics) {
					val name = impl.implements.identifier
					val ref = name.typeRef(pojoType.typeRef)
					superTypes += ref
   				}
   				else {
   					superTypes += impl.implements.cloneWithProxies
   				}
   			}
   			val ext = entity.getExtends
   			if (ext != null)
   				superTypes += ext.extends.cloneWithProxies
   			if (entity.superType != null)
   				superTypes += entity.superType.cloneWithProxies
   				
   			if (sernum != null) {
   				superTypes += typeRef(SERIALIZABLE)
				members += entity.toField('serialVersionUID', typeRef(long)) [
 					static = true
 					final = true
 					initializer = '''«sernum»L'''
   				]
   			}

   			members += entity.toField('logger', typeRef(LOGGER)) [
   				visibility = JvmVisibility.PROTECTED
   				final = true
   				initializer = '''«LOGGER_FACTORY».getLogger(getClass())'''
//   				val that = it
//   				initializer = [
//   					val StandardTypeReferenceOwner owner = new StandardTypeReferenceOwner(services, that);
//   					val OwnedConverter converter = new OwnedConverter(owner);
//					var t1 = converter.toLightweightReference(typeRef(LOGGER))
//					var t2 = converter.toLightweightReference(typeRef(LOGGER_FACTORY))
//					append(t2)
//	   				append('''.getLogger(getClass())''')
//   				]
   			]
   			
   			members += entity.toConstructor [
   				addAnnotations(entity.constructorAnnotations.map[a|a.annotation])
   			]
   			members += entity.toConstructor [
				parameters += entity.toParameter('sqlEngineFactory', typeRef(ENGINE_FACTORY))
   				addAnnotations(entity.constructorAnnotations.map[a|a.annotation])
   				body = '''
   					this.sqlEngineFactory = sqlEngineFactory;
   				'''
   			]
   			members += entity.toConstructor [
				parameters += entity.toParameter('sqlEngineFactory', typeRef(ENGINE_FACTORY))
				parameters += entity.toParameter('sqlSessionFactory', typeRef(SESSION_FACTORY))
   				addAnnotations(entity.constructorAnnotations.map[a|a.annotation])
   				body = '''
   					this.sqlEngineFactory = sqlEngineFactory;
   					this.sqlSessionFactory = sqlSessionFactory;
   				'''
   			]
   			
   			members += entity.toField('sqlEngineFactory', typeRef(ENGINE_FACTORY)) [
   				visibility = JvmVisibility.PROTECTED
   			]
   			members += entity.toField('sqlSessionFactory', typeRef(SESSION_FACTORY)) [
   				visibility = JvmVisibility.PROTECTED
   			]
   			
   			var Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses = null
   			
   			for (dir : entity.directives) {
   				if (dir instanceof DaoDirectiveCrud) {
   					if (moreResultClasses == null)
   						moreResultClasses = entity.getMoreResultClasses
   					inferInsert(entity, dir as DaoDirectiveCrud, entityType, simpleName, pojo, pojoType, members)
   					inferGet(entity, dir as DaoDirectiveCrud, entityType, simpleName, pojo, pojoType, members, moreResultClasses)
   					inferUpdate(entity, dir as DaoDirectiveCrud, entityType, simpleName, pojo, pojoType, members)
   					inferDelete(entity, dir as DaoDirectiveCrud, entityType, simpleName, pojo, pojoType, members)
   				}
   				else if (dir instanceof DaoDirectiveQuery) {
   					if (moreResultClasses == null)
   						moreResultClasses = entity.getMoreResultClasses
   					inferList(entity, dir as DaoDirectiveQuery, entityType, simpleName, pojo, pojoType, members, moreResultClasses)
   					inferQuery(entity, dir as DaoDirectiveQuery, entityType, simpleName, pojo, pojoType, members, moreResultClasses)
   					if (primaryKey != null && pkDir.fromTo != null && !pojo.toInitAttributes.isEmpty)
   						inferListFromTo(entity, dir as DaoDirectiveQuery, entityType, simpleName, pojo, pojoType, members, moreResultClasses, primaryKey)
   					inferCount(entity, dir as DaoDirectiveQuery, entityType, simpleName, pojo, pojoType, members, moreResultClasses)
   				}
   				else if (dir instanceof DaoFunProcDirective) {
   					inferFunctionProcedure(entity, (dir as DaoFunProcDirective).type, (dir as DaoFunProcDirective).paramlist, entityType, simpleName, members)
   				}
   			}
   			if (moreResultClasses != null && !moreResultClasses.empty) {
   				inferMoreResultClasses(entity, entityType, simpleName, pojo, pojoType, members, moreResultClasses)
			}

   			
   			for (attr : entity.attributes) {
   				val type = attr.type ?: attr.initExpr?.inferredType ?: typeRef(String)
   				members += entity.toField(attr.name, type) [
   					documentation = attr.documentation
   					addAnnotations(attr.attributeAnnotations.map[a|a.annotation])
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
   				]
   				if (!attr.static) {
	   				members += attr.toGetter(attr.name, attr.name, type) [
	   					addAnnotations(attr.getterAnnotations.map[a|a.annotation])
	   				]
	   				members += attr.toSetter(attr.name, attr.name, type, typeRef(entityType)) [
	   					addAnnotations(attr.setterAnnotations.map[a|a.annotation])
	   				]
   				}
   			}
   			
   			for (proc : entity.procedures) {
   				members += proc.toMethod(proc.name, proc.type ?: inferredType) [
   					documentation = proc.documentation
   					//addAnnotations(proc.annotations.map[a|a.annotation])
   					static = proc.static
   					//final = proc.final
   					for (param : proc.params) {
   						parameters += param.toParameter(param.name, param.parameterType)
   					}
   					body = proc.body
   				]
			}
   		]
   	}
   	
   	def void inferInsert(DaoEntity entity, DaoDirectiveCrud dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		val parent = pojo.parentReflectInheritance
   		
		members += entity.toMethod('insert', typeRef(pojoType)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				if (logger.isTraceEnabled()) {
					logger.trace("sql insert «pojoAttrName»: " + «pojoAttrName» + " " + sqlControl);
				}
				«CRUD_ENGINE» sqlInsert«pojo.name» = sqlEngineFactory.getCheckedCrudEngine("INSERT_«dbName(pojo.name)»");«IF parent != null»
				«CRUD_ENGINE» sqlInsert«parent.name» = sqlEngineFactory.getCheckedCrudEngine("INSERT_«dbName(parent.name)»");
				int count = sqlInsert«parent.name».insert(sqlSession, «pojoAttrName», sqlControl);
				if (count > 0) {
					sqlInsert«pojo.name».insert(sqlSession, «pojoAttrName», sqlControl);
				}«ELSE»
				int count = sqlInsert«pojo.name».insert(sqlSession, «pojoAttrName», sqlControl);«ENDIF»
				if (logger.isTraceEnabled()) {
					logger.trace("sql insert «pojoAttrName» result: " + count + " " + «pojoAttrName»);
				}
				return (count > 0) ? «pojoAttrName» : null;
   			'''
   		]	
   		
		members += entity.toMethod('insert', typeRef(pojoType)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				return insert(sqlSessionFactory.getSqlSession(), «pojoAttrName», sqlControl);
			'''
   		]	
   		
		members += entity.toMethod('insert', typeRef(pojoType)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			body = '''
				return insert(sqlSession, «pojoAttrName», null);
   			'''
   		]	
   		
		members += entity.toMethod('insert', typeRef(pojoType)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			body = '''
				return insert(«pojoAttrName», null);
   			'''
   		]	
	}

   	
   	def void inferInsertIfx(DaoEntity entity, DaoDirectiveCrud dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		
		members += entity.toMethod('insert', typeRef(pojoType)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod('insert', typeRef(pojoType)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod('insert', typeRef(pojoType)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
   		]	
   		
		members += entity.toMethod('insert', typeRef(pojoType)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
   		]	
	}

   	def void inferGet(DaoEntity entity, DaoDirectiveCrud dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members, 
   		Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		
		members += entity.toMethod('get', typeRef(pojoType)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				if (logger.isTraceEnabled()) {
					logger.trace("sql get: " + «pojoAttrName» + " " + sqlControl);
				}
				«CRUD_ENGINE» sqlGetEngine«pojo.name» = sqlEngineFactory.getCheckedCrudEngine("GET_«dbName(pojo.name)»");
				«IF moreResultClasses.empty»//«ENDIF»sqlControl = getMoreResultClasses(«pojoAttrName», sqlControl);
				«pojo.name» «pojoAttrName»Got = sqlGetEngine«pojo.name».get(sqlSession, «pojo.name».class, «pojoAttrName», sqlControl);
				if (logger.isTraceEnabled()) {
					logger.trace("sql get «pojoAttrName» result: " + «pojoAttrName»Got);
				}
				return «pojoAttrName»Got;
   				'''
   		]	
   		
		members += entity.toMethod('get', typeRef(pojoType)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				return get(sqlSessionFactory.getSqlSession(), «pojoAttrName», sqlControl);
			'''
   		]	
   		
		members += entity.toMethod('get', typeRef(pojoType)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			body = '''
				return get(sqlSession, «pojoAttrName», null);
   			'''
   		]	
   		
		members += entity.toMethod('get', typeRef(pojoType)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			body = '''
				return get(«pojoAttrName», null);
   			'''
   		]	
	}

   	def void inferGetIfx(DaoEntity entity, DaoDirectiveCrud dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members, 
   		Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		
		members += entity.toMethod('get', typeRef(pojoType)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod('get', typeRef(pojoType)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod('get', typeRef(pojoType)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
   		]	
   		
		members += entity.toMethod('get', typeRef(pojoType)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
   		]	
	}

   	def void inferUpdate(DaoEntity entity, DaoDirectiveCrud dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		val parent = pojo.parentReflectInheritance
   		
		members += entity.toMethod('update', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				if (logger.isTraceEnabled()) {
					logger.trace("sql update «pojoAttrName»: " + «pojoAttrName» + " " + sqlControl);
				}
				«CRUD_ENGINE» sqlUpdateEngine«pojo.name» = sqlEngineFactory.getCheckedCrudEngine("UPDATE_«dbName(pojo.name)»");«IF parent != null»
				«CRUD_ENGINE» sqlUpdate«parent.name» = sqlEngineFactory.getCheckedCrudEngine("UPDATE_«dbName(parent.name)»");«ENDIF»
				int count = sqlUpdateEngine«pojo.name».update(sqlSession, «pojoAttrName», sqlControl);«IF parent != null»
				if (count > 0) {
					sqlUpdate«parent.name».update(sqlSession, «pojoAttrName», sqlControl);
				}«ENDIF»«val f=getOptLock(pojo)»«IF f != null»
				if (count > 0) {
					«pojoAttrName».set«f.name.toFirstUpper»(«pojoAttrName».get«f.name.toFirstUpper»() + 1);
				}«ENDIF»
				if (logger.isTraceEnabled()) {
					logger.trace("sql update «pojoAttrName» result count: " + count);
				}
				return count;
   				'''
   		]	
   		
		members += entity.toMethod('update', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				return update(sqlSessionFactory.getSqlSession(), «pojoAttrName», sqlControl);
			'''
   		]	
   		
		members += entity.toMethod('update', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			body = '''
				return update(sqlSession, «pojoAttrName», null);
   			'''
   		]	
   		
		members += entity.toMethod('update', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			body = '''
				return update(«pojoAttrName», null);
   			'''
   		]	
	}

   	def void inferUpdateIfx(DaoEntity entity, DaoDirectiveCrud dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		
		members += entity.toMethod('update', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod('update', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod('update', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
   		]	
   		
		members += entity.toMethod('update', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
   		]	
	}

   	def void inferDelete(DaoEntity entity, DaoDirectiveCrud dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		val parent = pojo.parentReflectInheritance
   		
		members += entity.toMethod('delete', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				if (logger.isTraceEnabled()) {
					logger.trace("sql delete «pojoAttrName»: " + «pojoAttrName» + " " + sqlControl);
				}
				«CRUD_ENGINE» sqlDeleteEngine«pojo.name» = sqlEngineFactory.getCheckedCrudEngine("DELETE_«dbName(pojo.name)»");«IF parent != null»
				«CRUD_ENGINE» sqlDelete«parent.name» = sqlEngineFactory.getCheckedCrudEngine("DELETE_«dbName(parent.name)»");«ENDIF»
				int count = sqlDeleteEngine«pojo.name».delete(sqlSession, «pojoAttrName», sqlControl);«IF parent != null»
				if (count > 0) {
					sqlDelete«parent.name».delete(sqlSession, «pojoAttrName», sqlControl);
				}«ENDIF»«val f=getOptLock(pojo)»«IF f != null»
				if (count > 0) {
					«pojoAttrName».set«f.name.toFirstUpper»(«pojoAttrName».get«f.name.toFirstUpper»() + 1);
				}«ENDIF»
				if (logger.isTraceEnabled()) {
					logger.trace("sql delete «pojoAttrName» result count: " + count);
				}
				return count;
   				'''
   		]	
   		
		members += entity.toMethod('delete', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				return delete(sqlSessionFactory.getSqlSession(), «pojoAttrName», sqlControl);
			'''
   		]	
   		
		members += entity.toMethod('delete', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			body = '''
				return delete(sqlSession, «pojoAttrName», null);
   			'''
   		]	
   		
		members += entity.toMethod('delete', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			body = '''
				return delete(«pojoAttrName», null);
   			'''
   		]	
	}

   	def void inferDeleteIfx(DaoEntity entity, DaoDirectiveCrud dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		
		members += entity.toMethod('delete', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod('delete', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod('delete', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
   		]	
   		
		members += entity.toMethod('delete', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
   		]	
	}

   	def void inferList(DaoEntity entity, DaoDirectiveQuery dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members, 
   		Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		val listType = typeRef(java.util.List, typeRef(pojoType))
   			
		members += entity.toMethod('list', listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				if (logger.isTraceEnabled()) {
					logger.trace("sql list «pojoAttrName»: " + «pojoAttrName» + " " + sqlControl);
				}
				«QUERY_ENGINE» sqlEngine«pojo.name» = sqlEngineFactory.getCheckedQueryEngine("SELECT_«dbName(pojo.name)»");
				«IF moreResultClasses.empty»//«ENDIF»sqlControl = getMoreResultClasses(«pojoAttrName», sqlControl);
				List<«pojo.name»> «pojoAttrName»List = sqlEngine«pojo.name».query(sqlSession, «pojo.name».class, «pojoAttrName», sqlControl);
				if (logger.isTraceEnabled()) {
					logger.trace("sql list «pojoAttrName» size: " + ((«pojoAttrName»List != null) ? «pojoAttrName»List.size() : "null"));
				}
				return «pojoAttrName»List;
   				'''
   		]	
   		
		members += entity.toMethod('list', listType) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				return list(sqlSessionFactory.getSqlSession(), «pojoAttrName», sqlControl);
			'''
   		]	
   		
		members += entity.toMethod('list', listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			body = '''
				return list(sqlSession, «pojoAttrName», null);
   			'''
   		]	
   		
		members += entity.toMethod('list', listType) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			body = '''
				return list(«pojoAttrName», null);
   			'''
   		]	
	}

   	def void inferListIfx(DaoEntity entity, DaoDirectiveQuery dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members, 
   		Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		val listType = typeRef(java.util.List, typeRef(pojoType))
   			
		members += entity.toMethod('list', listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod('list', listType) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod('list', listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
   		]	
   		
		members += entity.toMethod('list', listType) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
   		]	
	}

   	def void inferListFromTo(DaoEntity entity, DaoDirectiveQuery dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members, 
   		Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses, PojoAttribute primaryKey
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		val listType = typeRef(java.util.List, typeRef(pojoType))
		val pkDir = primaryKey.primaryKeyDir
		val pkType = if (pkDir.type != null) pkDir.type else (primaryKey.type ?: primaryKey.initExpr?.inferredType ?: typeRef(String)).qualifiedName
		val pkGetDep = if (pkDir.name != null) ".get"+pkDir.name.toFirstUpper+"()" else ""
			
		members += entity.toMethod('listFromTo', listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				if (sqlControl == null || sqlControl.getFirstResult() == null || sqlControl.getMaxResults() == null || «pojoAttrName» == null)
					return list(sqlSession, «pojoAttrName», sqlControl);

				if (logger.isTraceEnabled()) {
					logger.trace("sql list «pojoAttrName»: " + «pojoAttrName» + " " + sqlControl);
				}
				«QUERY_ENGINE» sqlEngine«pojo.name» = sqlEngineFactory.getCheckedQueryEngine("SELECT_«dbName(pojo.name)»");
				«IF moreResultClasses.empty»//«ENDIF»sqlControl = getMoreResultClasses(«pojoAttrName», sqlControl);
				«pojoAttrName».setOnlyIds(true);
				«SET»<String> initAssociations = «pojoAttrName».getInitAssociations();
				«pojoAttrName».setInitAssociations(new «HASH_SET»<String>());
				final «LIST»<«wrapperPrimitive(pkType)»> ids = sqlEngine«pojo.name».query(sqlSession, «pkType».class, «pojoAttrName», sqlControl);
				«pojoAttrName».setInitAssociations(initAssociations);

				List<«pojo.name»> «pojoAttrName»List = new «ARRAY_LIST»<«pojo.name»>();
				if (!ids.isEmpty()) {
					«SQL_STANDARD_CONTROL» sqlc = new «SQL_STANDARD_CONTROL»(sqlControl);
					sqlc.setFirstResult(0);
					sqlc.setMaxResults(0);
					sqlc.setOrder(null);
					final «Map»<«wrapperPrimitive(pkType)», «pojo.name»> map = new «HASH_MAP»<«wrapperPrimitive(pkType)», «pojo.name»>();
					final SqlRowProcessor<«pojo.name»> sqlRowProcessor = new SqlRowProcessor<«pojo.name»>() {
						@Override
						public boolean processRow(«pojo.name» result, int rownum) throws «SQL_RUNTIME_EXCEPTION» {
							map.put(result.get«primaryKey.name.toFirstUpper»()«pkGetDep», result);
							return true;
						}
					};
					sqlEngine«pojo.name».query(sqlSession, «pojo.name».class, new «pojo.name»()._setIds(ids), sqlc, sqlRowProcessor);
					for («pkType» id : ids)
						«pojoAttrName»List.add(map.get(id));
				}
				if (logger.isTraceEnabled()) {
					logger.trace("sql list «pojoAttrName» size: " + ((«pojoAttrName»List != null) ? «pojoAttrName»List.size() : "null"));
				}
				return «pojoAttrName»List;
   				'''
   		]	
   		
		members += entity.toMethod('listFromTo', listType) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				return listFromTo(sqlSessionFactory.getSqlSession(), «pojoAttrName», sqlControl);
			'''
   		]	
   		
		members += entity.toMethod('listFromTo', listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			body = '''
				return listFromTo(sqlSession, «pojoAttrName», null);
   			'''
   		]	
   		
		members += entity.toMethod('listFromTo', listType) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			body = '''
				return listFromTo(«pojoAttrName», null);
   			'''
   		]	
	}

   	def void inferListFromToIfx(DaoEntity entity, DaoDirectiveQuery dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members, 
   		Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		val listType = typeRef(java.util.List, typeRef(pojoType))
   			
		members += entity.toMethod('listFromTo', listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod('listFromTo', listType) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod('listFromTo', listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
   		]	
   		
		members += entity.toMethod('listFromTo', listType) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
   		]	
	}

   	def void inferQuery(DaoEntity entity, DaoDirectiveQuery dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members, 
   		Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		val srpType = typeRef(SQL_ROW_PROCESSOR, typeRef(pojoType))
   			
		members += entity.toMethod('query', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			parameters += entity.toParameter("sqlRowProcessor", srpType)
			body = '''
				if (logger.isTraceEnabled()) {
					logger.trace("sql query «pojoAttrName»: " + «pojoAttrName» + " " + sqlControl);
				}
				«QUERY_ENGINE» sqlEngine«pojo.name» = sqlEngineFactory.getCheckedQueryEngine("SELECT_«dbName(pojo.name)»");
				«IF moreResultClasses.empty»//«ENDIF»sqlControl = getMoreResultClasses(«pojoAttrName», sqlControl);
				int rownums = sqlEngine«pojo.name».query(sqlSession, «pojo.name».class, «pojoAttrName», sqlControl, sqlRowProcessor);
				if (logger.isTraceEnabled()) {
					logger.trace("sql query «pojoAttrName» size: " + rownums);
				}
				return rownums;
   				'''
   		]	
   		
		members += entity.toMethod('query', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			parameters += entity.toParameter("sqlRowProcessor", srpType)
			body = '''
				return query(sqlSessionFactory.getSqlSession(), «pojoAttrName», sqlControl, sqlRowProcessor);
			'''
   		]	
   		
		members += entity.toMethod('query', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlRowProcessor", srpType)
			body = '''
				return query(sqlSession, «pojoAttrName», null, sqlRowProcessor);
   			'''
   		]	
   		
		members += entity.toMethod('query', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlRowProcessor", srpType)
			body = '''
				return query(«pojoAttrName», null, sqlRowProcessor);
   			'''
   		]	
	}

   	def void inferQueryIfx(DaoEntity entity, DaoDirectiveQuery dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members, 
   		Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		val srpType = typeRef(SQL_ROW_PROCESSOR, typeRef(pojoType))
   			
		members += entity.toMethod('query', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			parameters += entity.toParameter("sqlRowProcessor", srpType)
   		]	
   		
		members += entity.toMethod('query', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			parameters += entity.toParameter("sqlRowProcessor", srpType)
   		]	
   		
		members += entity.toMethod('query', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlRowProcessor", srpType)
   		]	
   		
		members += entity.toMethod('query', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlRowProcessor", srpType)
   		]	
	}

   	def void inferCount(DaoEntity entity, DaoDirectiveQuery dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members, 
   		Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		
		members += entity.toMethod('count', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				if (logger.isTraceEnabled()) {
					logger.trace("count «pojoAttrName»: " + «pojoAttrName» + " " + sqlControl);
				}
				«QUERY_ENGINE» sqlEngine«pojo.name» = sqlEngineFactory.getCheckedQueryEngine("SELECT_«dbName(pojo.name)»");
				«IF moreResultClasses.empty»//«ENDIF»sqlControl = getMoreResultClasses(«pojoAttrName», sqlControl);
				int count = sqlEngine«pojo.name».queryCount(sqlSession, «pojoAttrName», sqlControl);
				if (logger.isTraceEnabled()) {
					logger.trace("count: " + count);
				}
				return count;
   				'''
   		]	
   		
		members += entity.toMethod('count', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				return count(sqlSessionFactory.getSqlSession(), «pojoAttrName», sqlControl);
			'''
   		]	
   		
		members += entity.toMethod('count', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			body = '''
				return count(sqlSession, «pojoAttrName», null);
   			'''
   		]	
   		
		members += entity.toMethod('count', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			body = '''
				return count(«pojoAttrName», null);
   			'''
   		]	
	}

   	def void inferCountIfx(DaoEntity entity, DaoDirectiveQuery dir, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members, 
   		Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		
		members += entity.toMethod('count', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod('count', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod('count', typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
   		]	
   		
		members += entity.toMethod('count', typeRef(int)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
   		]	
	}

   	def void inferMoreResultClasses(DaoEntity entity, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members, 
   		Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		
		members += entity.toMethod('getMoreResultClasses', typeRef(SQL_CONTROL)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
			if (sqlControl != null && sqlControl.getMoreResultClasses() != null)
				return sqlControl;
			«MAP»<String, Class<?>> moreResultClasses = null;
			«FOR f:moreResultClasses.entrySet»
			if («pojoAttrName» != null && «pojoAttrName».toInit(«pojo.name».Association.«f.key».name())) {
				if (moreResultClasses == null)
					moreResultClasses = new «HASH_MAP»<String, Class<?>>();
				«FOR a:f.value.entrySet»
				moreResultClasses.put("«a.key»", «a.value».class);
				«ENDFOR»
			}
			«ENDFOR»
			if (moreResultClasses != null) {
				sqlControl = new «SQL_STANDARD_CONTROL»(sqlControl);
				((«SQL_STANDARD_CONTROL») sqlControl).setMoreResultClasses(moreResultClasses);
			}
			return sqlControl;
   				'''
   		]	
	}

   	def void inferMoreResultClassesIfx(DaoEntity entity, JvmGenericType entityType, String simpleName, 
   		PojoEntity pojo, JvmGenericType pojoType, List<JvmMember> members, 
   		Map<String, Map<String, JvmParameterizedTypeReference>> moreResultClasses
   	) {
   		val pojoAttrName = pojo.name.toFirstLower
   		
		members += entity.toMethod('getMoreResultClasses', typeRef(SQL_CONTROL)) [
			parameters += entity.toParameter(pojoAttrName, typeRef(pojoType))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
	}

   	dispatch def void inferFunctionProcedure(DaoEntity entity, FunctionCallQuery type, DaoDirectiveParameters params, 
   		JvmGenericType entityType, String simpleName, List<JvmMember> members
   	) {
   		val listType = typeRef(params.out.type, params.out.arguments)
   		val fname = entity.getFunProcName
   		
		members += entity.toMethod(fname, listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				if (logger.isTraceEnabled()) {
					logger.trace("sql «fname»: " + «FOR in:params.ins SEPARATOR " + \" \" "»«in.simpleName.toFirstLower»«ENDFOR» + " " + sqlControl);
				}
				«PROCEDURE_ENGINE» sqlFun«entity.name» = sqlEngineFactory.getCheckedProcedureEngine("FUN_«dbName(fname)»");
				«getSimpleName(params.out)» list = sqlFun«entity.name».callQuery(sqlSession, «params.out.arguments.head.simpleName».class, «FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», sqlControl);
				if (logger.isTraceEnabled()) {
					logger.trace("sql «fname» result: " + list);
				}
				return list;
   				'''
   		]	
   		
		members += entity.toMethod(fname, listType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				return «fname»(sqlSessionFactory.getSqlSession(), «FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», sqlControl);
			'''
   		]	
   		
		members += entity.toMethod(fname, listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			body = '''
				return «fname»(sqlSession, «FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», null);
   			'''
   		]	
   		
		members += entity.toMethod(fname, listType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			body = '''
				return «fname»(«FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», null);
   			'''
   		]	
	}

   	dispatch def void inferFunctionProcedureIfx(DaoEntity entity, FunctionCallQuery type, DaoDirectiveParameters params, 
   		JvmGenericType entityType, String simpleName, List<JvmMember> members
   	) {
   		val listType = typeRef(params.out.type, params.out.arguments)
   		val fname = entity.getFunProcName
   		
		members += entity.toMethod(fname, listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod(fname, listType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod(fname, listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
   		]	
   		
		members += entity.toMethod(fname, listType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
   		]	
	}

   	dispatch def void inferFunctionProcedure(DaoEntity entity, ProcedureCallQuery type, DaoDirectiveParameters params, 
   		JvmGenericType entityType, String simpleName, List<JvmMember> members
   	) {
   		val listType = typeRef(params.out.type, params.out.arguments)
   		val fname = entity.getFunProcName
   		
		members += entity.toMethod(fname, listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				if (logger.isTraceEnabled()) {
					logger.trace("sql «fname»: " + «FOR in:params.ins SEPARATOR " + \" \" "»«in.simpleName.toFirstLower»«ENDFOR» + " " + sqlControl);
				}
				«PROCEDURE_ENGINE» sqlProc«entity.name» = sqlEngineFactory.getCheckedProcedureEngine("PROC_«dbName(fname)»");
				«getSimpleName(params.out)» list = sqlProc«entity.name».callQuery(sqlSession, «params.out.arguments.head.simpleName».class, «FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», sqlControl);
				if (logger.isTraceEnabled()) {
					logger.trace("sql «fname» result: " + list);
				}
				return list;
   				'''
   		]	
   		
		members += entity.toMethod(fname, listType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				return «fname»(sqlSessionFactory.getSqlSession(), «FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», sqlControl);
			'''
   		]	
   		
		members += entity.toMethod(fname, listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			body = '''
				return «fname»(sqlSession, «FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», null);
   			'''
   		]	
   		
		members += entity.toMethod(fname, listType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			body = '''
				return «fname»(«FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», null);
   			'''
   		]	
   	}

   	dispatch def void inferFunctionProcedureIfx(DaoEntity entity, ProcedureCallQuery type, DaoDirectiveParameters params, 
   		JvmGenericType entityType, String simpleName, List<JvmMember> members
   	) {
   		val listType = typeRef(params.out.type, params.out.arguments)
   		val fname = entity.getFunProcName
   		
		members += entity.toMethod(fname, listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod(fname, listType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod(fname, listType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
   		]	
   		
		members += entity.toMethod(fname, listType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
   		]	
   	}

   	dispatch def void inferFunctionProcedure(DaoEntity entity, FunctionCall type, DaoDirectiveParameters params, 
   		JvmGenericType entityType, String simpleName, List<JvmMember> members
   	) {
   		val outType = typeRef(params.out.type, params.out.arguments)
   		val fname = entity.getFunProcName
   		
		members += entity.toMethod(fname, outType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				if (logger.isTraceEnabled()) {
					logger.trace("sql «fname»: " + «FOR in:params.ins SEPARATOR " + \" \" "»«in.simpleName.toFirstLower»«ENDFOR» + " " + sqlControl);
				}
				«PROCEDURE_ENGINE» sqlFun«entity.name» = sqlEngineFactory.getCheckedProcedureEngine("FUN_«dbName(fname)»");
				Object result = sqlFun«entity.name».callFunction(sqlSession, «FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», sqlControl);
				if (logger.isTraceEnabled()) {
					logger.trace("sql «fname» result: " + result);
				}
				return («getSimpleName(params.out)») result;
   				'''
   		]	
   		
		members += entity.toMethod(fname, outType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				return «fname»(sqlSessionFactory.getSqlSession(), «FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», sqlControl);
			'''
   		]	
   		
		members += entity.toMethod(fname, outType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			body = '''
				return «fname»(sqlSession, «FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», null);
   			'''
   		]	
   		
		members += entity.toMethod(fname, outType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			body = '''
				return «fname»(«FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», null);
   			'''
   		]	
   	}

   	dispatch def void inferFunctionProcedureIfx(DaoEntity entity, FunctionCall type, DaoDirectiveParameters params, 
   		JvmGenericType entityType, String simpleName, List<JvmMember> members
   	) {
   		val outType = typeRef(params.out.type, params.out.arguments)
   		val fname = entity.getFunProcName
   		
		members += entity.toMethod(fname, outType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod(fname, outType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod(fname, outType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
   		]	
   		
		members += entity.toMethod(fname, outType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
   		]	
   	}

   	dispatch def void inferFunctionProcedure(DaoEntity entity, ProcedureUpdate type, DaoDirectiveParameters params, 
   		JvmGenericType entityType, String simpleName, List<JvmMember> members
   	) {
   		val fname = entity.getFunProcName
   		
		members += entity.toMethod(fname, typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				if (logger.isTraceEnabled()) {
					logger.trace("sql «fname»: " + «FOR in:params.ins SEPARATOR " + \" \" "»«in.simpleName.toFirstLower»«ENDFOR» + " " + sqlControl);
				}
				«PROCEDURE_ENGINE» sqlProc«entity.name» = sqlEngineFactory.getCheckedProcedureEngine("PROC_«dbName(fname)»");
				int count = sqlProc«entity.name».callUpdate(sqlSession, «FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», sqlControl);
				if (logger.isTraceEnabled()) {
					logger.trace("sql «fname» result: " + count);
				}
				return count;
   				'''
   		]	
   		
		members += entity.toMethod(fname, typeRef(int)) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				return «fname»(sqlSessionFactory.getSqlSession(), «FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», sqlControl);
			'''
   		]	
   		
		members += entity.toMethod(fname, typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			body = '''
				return «fname»(sqlSession, «FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», null);
   			'''
   		]	
   		
		members += entity.toMethod(fname, typeRef(int)) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			body = '''
				return «fname»(«FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», null);
   			'''
   		]	
   	}

   	dispatch def void inferFunctionProcedureIfx(DaoEntity entity, ProcedureUpdate type, DaoDirectiveParameters params, 
   		JvmGenericType entityType, String simpleName, List<JvmMember> members
   	) {
   		val fname = entity.getFunProcName
   		
		members += entity.toMethod(fname, typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod(fname, typeRef(int)) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod(fname, typeRef(int)) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
   		]	
   		
		members += entity.toMethod(fname, typeRef(int)) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
   		]	
   	}

   	dispatch def void inferFunctionProcedure(DaoEntity entity, FunctionQuery type, DaoDirectiveParameters params, 
   		JvmGenericType entityType, String simpleName, List<JvmMember> members
   	) {
   		val outType = typeRef(params.out.type, params.out.arguments)
   		val fname = entity.getFunProcName
   		
		members += entity.toMethod(fname, outType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				if (logger.isTraceEnabled()) {
					logger.trace("sql «fname»: " + «FOR in:params.ins SEPARATOR " + \" \" "»«in.simpleName.toFirstLower»«ENDFOR» + " " + sqlControl);
				}
				«QUERY_ENGINE» sqlFun«entity.name» = sqlEngineFactory.getCheckedQueryEngine("FUN_«dbName(fname)»");
				«LIST»<«params.ins.head.simpleName»> list = sqlFun«entity.name».query(sqlSession, «params.ins.head.simpleName».class, «FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», sqlControl);
				if (logger.isTraceEnabled()) {
					logger.trace("sql «fname» result: " + list);
				}
				return (list != null && !list.isEmpty()) ? list.get(0).getResult() : null;
   				'''
   		]	
   		
		members += entity.toMethod(fname, outType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
			body = '''
				return «fname»(sqlSessionFactory.getSqlSession(), «FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», sqlControl);
			'''
   		]	
   		
		members += entity.toMethod(fname, outType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			body = '''
				return «fname»(sqlSession, «FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», null);
   			'''
   		]	
   		
		members += entity.toMethod(fname, outType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			body = '''
				return «fname»(«FOR in:params.ins SEPARATOR ", "»«in.simpleName.toFirstLower»«ENDFOR», null);
   			'''
   		]	
   	}

   	dispatch def void inferFunctionProcedureIfx(DaoEntity entity, FunctionQuery type, DaoDirectiveParameters params, 
   		JvmGenericType entityType, String simpleName, List<JvmMember> members
   	) {
   		val outType = typeRef(params.out.type, params.out.arguments)
   		val fname = entity.getFunProcName
   		
		members += entity.toMethod(fname, outType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod(fname, outType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
			parameters += entity.toParameter("sqlControl", typeRef(SQL_CONTROL))
   		]	
   		
		members += entity.toMethod(fname, outType) [
			parameters += entity.toParameter("sqlSession", typeRef(SQL_SESSION))
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
   		]	
   		
		members += entity.toMethod(fname, outType) [
			for (in : params.ins)
				parameters += entity.toParameter(in.simpleName.toFirstLower, typeRef(in.type))
   		]	
   	}
   	
   	def QualifiedName daoFullyQualifiedName(DaoEntity entity, String implPackage) {
   		val QualifiedName fqname = entity.fullyQualifiedName
   		if (implPackage == null)
			return fqname
   		if (implPackage.indexOf('.') >= 0) {
			val segments = implPackage.split('.')
			val fqn = QualifiedName.create(segments)
			return fqn.append(fqname.lastSegment)
   		}
   		else {
	   		var fqn = fqname.skipLast(1)
	   		fqn = fqn.append(implPackage)
	   		return fqn.append(fqname.lastSegment+'Impl')
   		}
   	}
}

