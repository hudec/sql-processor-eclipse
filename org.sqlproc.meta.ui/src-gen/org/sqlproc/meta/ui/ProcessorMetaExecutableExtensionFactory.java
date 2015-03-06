/*
 * generated by Xtext
 */
package org.sqlproc.meta.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import org.sqlproc.meta.ui.internal.ProcessorMetaActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class ProcessorMetaExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return ProcessorMetaActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return ProcessorMetaActivator.getInstance().getInjector(ProcessorMetaActivator.ORG_SQLPROC_META_PROCESSORMETA);
	}
	
}