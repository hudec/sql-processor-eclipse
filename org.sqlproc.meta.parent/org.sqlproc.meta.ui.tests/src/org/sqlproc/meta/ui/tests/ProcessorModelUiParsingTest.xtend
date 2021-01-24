/*
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.meta.ui.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.sqlproc.meta.processorMeta.Artifacts

@RunWith(XtextRunner)
@InjectWith(ProcessorMetaUiInjectorProvider)
class ProcessorModelUiParsingTest{

	@Inject
	ParseHelper<Artifacts> parseHelper

	@Test 
	def void loadModel() {
		val artifacts = parseHelper.parse('''
			resolve-pojo-on;
			database-is-online;		''')
		Assert.assertEquals(2, artifacts.properties.size)
	}
}