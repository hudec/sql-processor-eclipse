/**
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.model.tests;

import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sqlproc.model.processorModel.Artifacts;
import org.sqlproc.model.tests.ProcessorModelInjectorProvider;

@RunWith(XtextRunner.class)
@InjectWith(ProcessorModelInjectorProvider.class)
@SuppressWarnings("all")
public class ProcessorModelParsingTest {
  @Inject
  private ParseHelper<Artifacts> parseHelper;
  
  @Test
  public void loadModel() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Hello Xtext!");
      _builder.newLine();
      final Artifacts result = this.parseHelper.parse(_builder);
      Assert.assertNotNull(result);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
