package org.sqlproc.dsl.tests;

import com.google.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sqlproc.dsl.ProcessorDslInjectorProvider;
import org.sqlproc.dsl.processorDsl.Artifacts;
import org.sqlproc.dsl.processorDsl.Property;

@RunWith(XtextRunner.class)
@InjectWith(ProcessorDslInjectorProvider.class)
@SuppressWarnings("all")
public class SimpleParsingTest {
  @Inject
  @Extension
  private ParseHelper<Artifacts> _parseHelper;
  
  @Test
  public void testParse() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("resolve-pojo-on;");
      _builder.newLine();
      _builder.append("database-is-online;");
      _builder.newLine();
      final Artifacts artifacts = this._parseHelper.parse(_builder);
      EList<Property> _properties = artifacts.getProperties();
      int _size = _properties.size();
      Assert.assertEquals(2, _size);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
