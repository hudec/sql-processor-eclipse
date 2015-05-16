/*
 * generated by Xtext
 */
package org.sqlproc.model.validation

import org.eclipse.xtext.validation.Check
import org.sqlproc.model.processorModel.PojoDefinitionModel
import org.sqlproc.model.processorModel.ProcessorModelPackage
import org.eclipse.emf.common.util.URI
import org.sqlproc.model.processorModel.Artifacts
import com.google.inject.Inject
import org.sqlproc.plugin.lib.resolver.PojoResolverFactory
import org.sqlproc.plugin.lib.resolver.DbResolver
import org.eclipse.xtext.scoping.IScopeProvider
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.sqlproc.plugin.lib.property.ModelProperty
import org.eclipse.emf.ecore.EObject
import static extension org.eclipse.xtext.EcoreUtil2.*
import static extension org.eclipse.emf.ecore.util.EcoreUtil.*
import org.sqlproc.model.processorModel.FunctionDefinitionModel
import org.sqlproc.model.processorModel.ProcedureDefinitionModel
import org.sqlproc.model.processorModel.TableDefinitionModel
import org.sqlproc.model.processorModel.Property
import org.sqlproc.model.processorModel.PojoEntity
import org.sqlproc.model.processorModel.Package
import org.sqlproc.model.processorModel.AbstractEntity
import org.sqlproc.model.processorModel.AnnotatedEntity
import org.sqlproc.model.processorModel.EnumEntity
import org.sqlproc.model.processorModel.DaoEntity
import org.sqlproc.model.processorModel.PojoAttribute
import org.sqlproc.model.processorModel.Entity
import org.sqlproc.plugin.lib.util.CommonUtils
import org.sqlproc.model.processorModel.PojoDefinitionModel

/**
 * Custom validation rules. 
 *
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
class ProcessorModelValidator extends AbstractProcessorModelValidator {

    @Inject
    PojoResolverFactory pojoResolverFactory

    @Inject
    DbResolver dbResolver

    @Inject
    IScopeProvider scopeProvider

    @Inject
    IQualifiedNameConverter qualifiedNameConverter

    @Inject
    ModelProperty modelProperty

    @Check
    def checkUniquePojoDefinition(PojoDefinitionModel pojoDefinition) {
    	if (CommonUtils.skipVerification(pojoDefinition, modelProperty))
            return;
    	
    	val URI uri = pojoDefinition.eResource?.URI
        if (isResolvePojo(pojoDefinition) && !checkClass(getClass(pojoDefinition), uri))
            error("Class name : " + getClass(pojoDefinition) + " not exists",
                    ProcessorModelPackage.Literals.POJO_DEFINITION_MODEL__NAME)
        val artifacts = getArtifacts(pojoDefinition)
        if (artifacts == null)
            return;
        for (PojoDefinitionModel definition : artifacts.getPojos()) {
            if (definition != null && definition !== pojoDefinition) {
	            if (pojoDefinition.getName().equals(definition.getName())) {
	                error("Duplicate name : " + pojoDefinition.getName(),
	                        ProcessorModelPackage.Literals.POJO_DEFINITION_MODEL__NAME)
	                return
	            }
            }
        }
    }
    
    def checkClass(String className, URI uri) {
        if (className == null || pojoResolverFactory.getPojoResolver() == null)
            return true

        val clazz = pojoResolverFactory.getPojoResolver().loadClass(className, uri)
        return clazz != null
    }

    def isResolvePojo(EObject model) {
        if (pojoResolverFactory.getPojoResolver() == null
                || !pojoResolverFactory.getPojoResolver().isResolvePojo(model))
            return false
        return true

    }

    def isResolveDb(EObject model) {
        return dbResolver.isResolveDb(model)
    }

    def String getClass(PojoDefinitionModel pojo) {
        if (pojo.getClassx() != null)
            return pojo.getClassx().getQualifiedName()
        return pojo.getClass_()
    }


    @Check
    def checkUniqueProperty(Property property) {
        if (CommonUtils.skipVerification(property, modelProperty))
            return;
        val artifacts = getArtifacts(property)
        if (artifacts == null)
            return;

        for (Property prop : artifacts.getProperties()) {
            if (prop != null && prop !== property) {
	            if (prop.getName().equals(property.getName()) && !prop.getName().startsWith("pojogen")
	                    && !prop.getName().startsWith("database") && !prop.getName().startsWith("metagen")
	                    && !prop.getName().startsWith("daogen") && !prop.getName().startsWith("replace-text")) {
	                error("Duplicate name : " + property.getName(), ProcessorModelPackage.Literals.PROPERTY__NAME)
	                return
	            }
            }
        }
    }

    @Check
    def checkTableDefinition(TableDefinitionModel tableDefinition) {
        if (CommonUtils.skipVerification(tableDefinition, modelProperty))
            return;
        val artifacts = getArtifacts(tableDefinition)
        if (artifacts == null)
            return;

        for (TableDefinitionModel table : artifacts.getTables()) {
            if (table != null && table !== tableDefinition) {
	            if (tableDefinition.getName().equals(table.getName())) {
	                error("Duplicate name : " + tableDefinition.getName() + "[table]",
	                        ProcessorModelPackage.Literals.TABLE_DEFINITION_MODEL__NAME)
	                return
				}
	           }
        }
        if (isResolveDb(tableDefinition) && !dbResolver.checkTable(tableDefinition, tableDefinition.getTable())) {
            error("Cannot find table in DB : " + tableDefinition.getTable(),
                    ProcessorModelPackage.Literals.TABLE_DEFINITION_MODEL__TABLE)
        }
    }

    @Check
    def checkProcedureDefinition(ProcedureDefinitionModel procedureDefinition) {
        if (CommonUtils.skipVerification(procedureDefinition, modelProperty))
            return;
        val artifacts = getArtifacts(procedureDefinition)
        if (artifacts == null)
            return;

        for (ProcedureDefinitionModel procedure : artifacts.getProcedures()) {
            if (procedure != null && procedure !== procedureDefinition) {
	            if (procedureDefinition.getName().equals(procedure.getName())) {
	                error("Duplicate name : " + procedureDefinition.getName() + "[procedure]",
	                        ProcessorModelPackage.Literals.PROCEDURE_DEFINITION_MODEL__NAME)
	                return
	            }
	        }
        }
        if (isResolveDb(procedureDefinition)
                && !dbResolver.checkProcedure(procedureDefinition, procedureDefinition.getTable())) {
            error("Cannot find procedure in DB : " + procedureDefinition.getTable(),
                    ProcessorModelPackage.Literals.PROCEDURE_DEFINITION_MODEL__NAME)
        }
    }

    @Check
    def checkFunctionDefinition(FunctionDefinitionModel functionDefinition) {
        if (CommonUtils.skipVerification(functionDefinition, modelProperty))
            return;
        val artifacts = getArtifacts(functionDefinition)
        if (artifacts == null)
            return;

        for (FunctionDefinitionModel function : artifacts.getFunctions()) {
            if (function != null && function !== functionDefinition) {
	            if (functionDefinition.getName().equals(function.getName())) {
	                error("Duplicate name : " + functionDefinition.getName() + "[function]",
	                        ProcessorModelPackage.Literals.FUNCTION_DEFINITION_MODEL__NAME)
	                return
	            }
	    	}
        }
    }
    
    @Check
    def checkUniquePojoEntity(PojoEntity pojoEntity) {
        if (CommonUtils.skipVerification(pojoEntity, modelProperty))
            return;
        val artifacts = getArtifacts(pojoEntity)
        if (artifacts == null)
            return;

        for (Package pkg : artifacts.getPackages()) {
            if (pkg != null) {
	            for (AbstractEntity entity : pkg.getElements()) {
	                if (entity != null && (entity instanceof AnnotatedEntity)) {
		                val aentity = entity as AnnotatedEntity
		                if (aentity.entity != null && aentity.entity instanceof PojoEntity) {
		                	val pentity = aentity.entity as PojoEntity
			                if (pentity !== pojoEntity) {
				                if (pojoEntity.getName().equals(pentity.getName())) {
				                    error("Duplicate name : " + pojoEntity.getName(), ProcessorModelPackage.Literals.ENTITY__NAME)
				                    return
				                }
			                }
		                }
					}
	            }
            }
        }
    }

    @Check
    def checkUniqueEnumEntity(EnumEntity enumEntity) {
        if (CommonUtils.skipVerification(enumEntity, modelProperty))
            return;
        val artifacts = getArtifacts(enumEntity)
        if (artifacts == null)
            return;

        for (Package pkg : artifacts.getPackages()) {
            if (pkg != null) {
	            for (AbstractEntity entity : pkg.getElements()) {
	            	if (entity != null && (entity instanceof AnnotatedEntity)) {
		                val aentity = entity as AnnotatedEntity
		                if (aentity.entity != null && aentity.entity instanceof EnumEntity) {
			                val pentity = aentity.entity as EnumEntity
			                if (pentity != enumEntity) {
				                if (enumEntity.getName().equals(pentity.getName())) {
				                    error("Duplicate name : " + enumEntity.getName(), ProcessorModelPackage.Literals.ENTITY__NAME)
				                    return
				                }
							}
						}
					}
	            }
            }
        }
    }

    @Check
    def checkUniqueDaoEntity(DaoEntity daoEntity) {
        if (CommonUtils.skipVerification(daoEntity, modelProperty))
            return;
        val artifacts = getArtifacts(daoEntity)
        if (artifacts == null)
            return;

        for (Package pkg : artifacts.getPackages()) {
            if (pkg != null) {
	            for (AbstractEntity entity : pkg.getElements()) {
	            	if (entity != null && (entity instanceof AnnotatedEntity)) {
		                val aentity = entity as AnnotatedEntity
		                if (aentity.entity != null && aentity.entity instanceof DaoEntity) {
			                val pentity = aentity.entity as DaoEntity
			                if (pentity != daoEntity) {
				                if (daoEntity.getName().equals(pentity.getName())) {
				                    error("Duplicate name : " + daoEntity.getName(), ProcessorModelPackage.Literals.ENTITY__NAME)
				                    return
				                }
							}
						}
					}
	            }
            }
        }
    }

    @Check
    def checkUniquePojoAttribute(PojoAttribute pojoProperty) {
        if (CommonUtils.skipVerification(pojoProperty, modelProperty))
            return;
            
        val entity = pojoProperty.getContainerOfType(typeof(Entity))
        if (entity != null) {
        	if (entity instanceof PojoEntity) {
        		val pentity = entity as PojoEntity
		        for (PojoAttribute property : pentity.getAttributes()) {
		            if (property != null && property !== pojoProperty) {
			            if (pojoProperty.getName().equals(property.getName())) {
			                error("Duplicate name : " + pojoProperty.getName(), ProcessorModelPackage.Literals.POJO_ATTRIBUTE__NAME)
			                return
			            }
		            }
		        }
        	} else if (entity instanceof DaoEntity) {
        		val pentity = entity as DaoEntity
		        for (PojoAttribute property : pentity.getAttributes()) {
		            if (property != null && property !== pojoProperty) {
			            if (pojoProperty.getName().equals(property.getName())) {
			                error("Duplicate name : " + pojoProperty.getName(), ProcessorModelPackage.Literals.POJO_ATTRIBUTE__NAME)
			                return
			            }
		            }
		        }
	        }
        }
    }
    
    def Artifacts getArtifacts(EObject model) {
        val root = model.rootContainer
        if (!(root instanceof Artifacts))
            return null;
        return root as Artifacts
    }
}
