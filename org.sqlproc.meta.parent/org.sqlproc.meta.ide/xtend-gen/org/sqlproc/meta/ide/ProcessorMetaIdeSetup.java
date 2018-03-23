/**
 * generated by Xtext 2.12.0
 */
package org.sqlproc.meta.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.xtext.util.Modules2;
import org.sqlproc.meta.ProcessorMetaRuntimeModule;
import org.sqlproc.meta.ProcessorMetaStandaloneSetup;
import org.sqlproc.meta.ide.ProcessorMetaIdeModule;

/**
 * Initialization support for running Xtext languages as language servers.
 */
@SuppressWarnings("all")
public class ProcessorMetaIdeSetup extends ProcessorMetaStandaloneSetup {
  @Override
  public Injector createInjector() {
    ProcessorMetaRuntimeModule _processorMetaRuntimeModule = new ProcessorMetaRuntimeModule();
    ProcessorMetaIdeModule _processorMetaIdeModule = new ProcessorMetaIdeModule();
    return Guice.createInjector(Modules2.mixin(_processorMetaRuntimeModule, _processorMetaIdeModule));
  }
}