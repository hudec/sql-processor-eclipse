package org.sqlproc.model.tests

import com.google.inject.Inject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*
import org.sqlproc.model.ProcessorModelInjectorProvider
import org.sqlproc.model.processorModel.Artifacts

@RunWith(XtextRunner)
@InjectWith(ProcessorModelInjectorProvider)
class SimpleParsingTest {
	
	@Inject extension ParseHelper<Artifacts>
	
	@Test def void testParse() {
		val artifacts = '''
			resolve-pojo-on;
			database-is-online;
		'''.parse
		assertEquals(2, artifacts.properties.size)
	}
}