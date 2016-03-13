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

/**
 * <p>Infers a JVM model from the source model.</p> 
 *
 * <p>The JVM model should contain all elements that would appear in the Java code 
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>     
 */
class ProcessorModelJvmModelInferrer extends AbstractModelInferrer {

    /**
     * convenience API to build and initialize JVM types and their members.
     */
	@Inject extension PojoJvmModelInferrer _pojoJvmModelInferrer
	@Inject extension EnumJvmModelInferrer _enumJvmModelInferrer
	@Inject extension DaoJvmModelInferrer _daoJvmModelInferrer
	@Inject extension ProcessorGeneratorUtils

   	def dispatch void infer(PojoEntity entity, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		_pojoJvmModelInferrer._annotationTypesBuilder = _annotationTypesBuilder
   		_pojoJvmModelInferrer._typeReferenceBuilder = _typeReferenceBuilder
   		inferPojo(entity, acceptor, isPreIndexingPhase)
   	}
	
   	def dispatch void infer(EnumEntity entity, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		_enumJvmModelInferrer._annotationTypesBuilder = _annotationTypesBuilder
   		_enumJvmModelInferrer._typeReferenceBuilder = _typeReferenceBuilder
   		inferEnum(entity, acceptor, isPreIndexingPhase)
   	}

   	def dispatch void infer(DaoEntity entity, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		_daoJvmModelInferrer._annotationTypesBuilder = _annotationTypesBuilder
   		_daoJvmModelInferrer._typeReferenceBuilder = _typeReferenceBuilder
   		val implPackage = getImplPackage(entity)
   		if (implPackage != null) {
   			inferDaoIfx(entity, acceptor, isPreIndexingPhase)
   			inferDao(entity, acceptor, isPreIndexingPhase, implPackage)
   		}
   		else {
   			inferDao(entity, acceptor, isPreIndexingPhase, null)
		}
   	}
}

