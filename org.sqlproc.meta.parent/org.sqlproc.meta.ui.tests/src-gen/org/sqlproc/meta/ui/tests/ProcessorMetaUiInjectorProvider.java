/*
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.meta.ui.tests;

import com.google.inject.Injector;
import org.eclipse.xtext.junit4.IInjectorProvider;
import org.sqlproc.meta.ui.internal.MetaActivator;

public class ProcessorMetaUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return MetaActivator.getInstance().getInjector("org.sqlproc.meta.ProcessorMeta");
	}

}