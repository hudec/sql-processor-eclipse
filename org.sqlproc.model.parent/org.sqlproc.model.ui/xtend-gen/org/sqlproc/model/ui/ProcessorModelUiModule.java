/**
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.model.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.common.types.xtext.AbstractTypeScopeProvider;
import org.eclipse.xtext.ui.editor.contentassist.ITemplateProposalProvider;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContextType;
import org.sqlproc.model.ui.AbstractProcessorModelUiModule;
import org.sqlproc.model.ui.resolver.WorkspacePojoResolverImpl;
import org.sqlproc.model.ui.scoping.ProcessorModelJdtBasedSimpleTypeScopeProvider;
import org.sqlproc.model.ui.scoping.ProcessorModelJdtTypeProviderFactory;
import org.sqlproc.model.ui.templates.ProcessorModelTemplateContextType;
import org.sqlproc.model.ui.templates.ProcessorTemplateProposalProvider;
import org.sqlproc.plugin.lib.resolver.PojoResolver;
import org.sqlproc.plugin.lib.resolver.PojoResolverFactory;
import org.sqlproc.plugin.lib.resolver.PojoResolverFactoryBean;

/**
 * Use this class to register components to be used within the Eclipse IDE.
 */
@FinalFieldsConstructor
@SuppressWarnings("all")
public class ProcessorModelUiModule extends AbstractProcessorModelUiModule {
  public Class<? extends PojoResolverFactory> bindPojoResolverFactory() {
    return PojoResolverFactoryBean.class;
  }
  
  public Class<? extends PojoResolver> bindPojoResolver() {
    return WorkspacePojoResolverImpl.class;
  }
  
  @Override
  public Class<? extends XtextTemplateContextType> bindXtextTemplateContextType() {
    return ProcessorModelTemplateContextType.class;
  }
  
  @Override
  public Class<? extends ITemplateProposalProvider> bindITemplateProposalProvider() {
    return ProcessorTemplateProposalProvider.class;
  }
  
  @Override
  public Class<? extends IJvmTypeProvider.Factory> bindIJvmTypeProvider$Factory() {
    return ProcessorModelJdtTypeProviderFactory.class;
  }
  
  @Override
  public Class<? extends AbstractTypeScopeProvider> bindAbstractTypeScopeProvider() {
    return ProcessorModelJdtBasedSimpleTypeScopeProvider.class;
  }
  
  public ProcessorModelUiModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }
}
