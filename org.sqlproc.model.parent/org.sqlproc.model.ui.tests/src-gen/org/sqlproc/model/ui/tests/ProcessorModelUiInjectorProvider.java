/*
 * generated by Xtext 2.12.0
 */
package org.sqlproc.model.ui.tests;

import com.google.inject.Injector;
import org.eclipse.xtext.testing.IInjectorProvider;
import org.sqlproc.model.ui.internal.ModelActivator;

public class ProcessorModelUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return ModelActivator.getInstance().getInjector("org.sqlproc.model.ProcessorModel");
	}

}
