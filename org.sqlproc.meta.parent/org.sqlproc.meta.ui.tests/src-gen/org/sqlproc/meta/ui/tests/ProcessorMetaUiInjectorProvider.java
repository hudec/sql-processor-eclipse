/*
 * generated by Xtext 2.12.0
 */
package org.sqlproc.meta.ui.tests;

import com.google.inject.Injector;
import org.eclipse.xtext.testing.IInjectorProvider;
import org.sqlproc.meta.ui.internal.MetaActivator;

public class ProcessorMetaUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return MetaActivator.getInstance().getInjector("org.sqlproc.meta.ProcessorMeta");
	}

}
