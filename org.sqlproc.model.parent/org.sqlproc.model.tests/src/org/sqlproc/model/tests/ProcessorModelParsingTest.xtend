/*
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.model.tests

import com.google.inject.Inject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.sqlproc.model.processorModel.Artifacts

@RunWith(XtextRunner)
@InjectWith(ProcessorModelInjectorProvider)
class ProcessorModelParsingTest{

	@Inject
	ParseHelper<Artifacts> parseHelper

	@Test 
	def void loadModel() {
		val result = parseHelper.parse('''
			Hello Xtext!
		''')
		Assert.assertNotNull(result)
	}

}
