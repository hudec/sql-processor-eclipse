/*
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.model

import org.sqlproc.model.generator.ProcessorModelGenerator
import org.sqlproc.plugin.lib.property.ModelProperty
import org.sqlproc.model.property.ModelPropertyBean
import org.sqlproc.plugin.lib.resolver.PojoResolverFactory
import org.sqlproc.plugin.lib.resolver.PojoResolverFactoryBean
import org.sqlproc.plugin.lib.resolver.DbResolver
import org.sqlproc.plugin.lib.resolver.DbResolverBean
import org.eclipse.xtext.resource.IResourceFactory
import org.eclipse.xtext.xbase.compiler.ErrorSafeExtensions
import org.sqlproc.model.generator.ProcessorModelErrorSafeExtensions
import org.eclipse.xtext.common.types.access.IJvmTypeProvider
import org.eclipse.xtext.common.types.xtext.AbstractTypeScopeProvider
import org.sqlproc.model.scoping.ProcessorModelClasspathTypeProviderFactory
import org.sqlproc.model.scoping.ProcessorModelClasspathBasedTypeScopeProvider

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
class ProcessorModelRuntimeModule extends AbstractProcessorModelRuntimeModule {

    override Class<? extends org.eclipse.xtext.generator.IGenerator> bindIGenerator() {
        return typeof(ProcessorModelGenerator)
    }

    def Class<? extends ModelProperty> bindModelProperty() {
        return typeof(ModelPropertyBean)
    }

    def Class<? extends PojoResolverFactory> bindPojoResolverFactory() {
        return typeof(PojoResolverFactoryBean)
    }

    def Class<? extends DbResolver> bindDbResolver() {
        return typeof(DbResolverBean)
    }

    override Class<? extends IResourceFactory> bindIResourceFactory() {
        return typeof(ProcessorResourceFactory)
    }

    override Class<? extends org.eclipse.xtext.xbase.scoping.batch.XbaseBatchScopeProvider> bindXbaseBatchScopeProvider() {
        return typeof(org.sqlproc.model.scoping.ProcessorModelScopeProvider)
    }

    override Class<? extends org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy> bindIDefaultResourceDescriptionStrategy() {
        return typeof(ProcesorModelResourceDescriptionStrategy)
    }

    def Class<? extends ErrorSafeExtensions> bindErrorSafeExtensions() {
        return typeof(ProcessorModelErrorSafeExtensions)
    }

    override Class<? extends IJvmTypeProvider.Factory> bindIJvmTypeProvider$Factory() {
        return typeof(ProcessorModelClasspathTypeProviderFactory)
    }

    override Class<? extends AbstractTypeScopeProvider> bindAbstractTypeScopeProvider() {
        return typeof(ProcessorModelClasspathBasedTypeScopeProvider)
    }
}
