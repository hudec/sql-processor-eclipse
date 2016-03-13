/**
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.meta;

import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.common.types.xtext.AbstractTypeScopeProvider;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.resource.IResourceFactory;
import org.sqlproc.meta.AbstractProcessorMetaRuntimeModule;
import org.sqlproc.meta.ProcessorMetaClasspathBasedTypeScopeProvider;
import org.sqlproc.meta.ProcessorMetaClasspathTypeProviderFactory;
import org.sqlproc.meta.ProcessorNameProvider;
import org.sqlproc.meta.ProcessorResourceFactory;
import org.sqlproc.meta.property.ModelPropertyBean;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.resolver.DbResolver;
import org.sqlproc.plugin.lib.resolver.DbResolverBean;
import org.sqlproc.plugin.lib.resolver.PojoResolverFactory;
import org.sqlproc.plugin.lib.resolver.PojoResolverFactoryBean;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
@SuppressWarnings("all")
public class ProcessorMetaRuntimeModule extends AbstractProcessorMetaRuntimeModule {
  public Class<? extends ModelProperty> bindModelProperty() {
    return ModelPropertyBean.class;
  }
  
  @Override
  public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
    return ProcessorNameProvider.class;
  }
  
  public Class<? extends PojoResolverFactory> bindPojoResolverFactory() {
    return PojoResolverFactoryBean.class;
  }
  
  public Class<? extends DbResolver> bindDbResolver() {
    return DbResolverBean.class;
  }
  
  @Override
  public Class<? extends IResourceFactory> bindIResourceFactory() {
    return ProcessorResourceFactory.class;
  }
  
  public Class<? extends IJvmTypeProvider.Factory> bindIJvmTypeProvider$Factory() {
    return ProcessorMetaClasspathTypeProviderFactory.class;
  }
  
  public Class<? extends AbstractTypeScopeProvider> bindAbstractTypeScopeProvider() {
    return ProcessorMetaClasspathBasedTypeScopeProvider.class;
  }
}