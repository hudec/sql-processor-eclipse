/**
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.model;

import org.sqlproc.model.ProcessorModelStandaloneSetupGenerated;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
@SuppressWarnings("all")
public class ProcessorModelStandaloneSetup extends ProcessorModelStandaloneSetupGenerated {
  public static void doSetup() {
    ProcessorModelStandaloneSetup _processorModelStandaloneSetup = new ProcessorModelStandaloneSetup();
    _processorModelStandaloneSetup.createInjectorAndDoEMFRegistration();
  }
}