/*
 * generated by Xtext
 */
package org.sqlproc.dsl.formatting

import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter
import org.eclipse.xtext.formatting.impl.FormattingConfig
import com.google.inject.Inject
import org.sqlproc.dsl.services.ProcessorDslGrammarAccess

/**
 * This class contains custom formatting description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation.html#formatting
 * on how and when to use it 
 * 
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an example
 */
class ProcessorDslFormatter extends AbstractDeclarativeFormatter {

	@Inject extension ProcessorDslGrammarAccess
	
	override protected void configureFormatting(FormattingConfig c) {
		
		c.setLinewrap(0, 1, 2).before(SL_COMMENTRule)
		c.setLinewrap(0, 1, 2).before(ML_COMMENTRule)
		c.setLinewrap(0, 1, 1).after(ML_COMMENTRule)
		
		c.setNoSpace().before(SEMICOLONRule);

        c.setLinewrap(1, 1, 2).after(artifactsRule);
        c.setLinewrap(1, 1, 2).after(propertyRule);
        c.setLinewrap(1, 1, 2).after(pojoDefinitionRule);
        c.setLinewrap(1, 1, 2).after(tableDefinitionRule);
        c.setLinewrap(1, 1, 2).after(procedureDefinitionRule);
        c.setLinewrap(1, 1, 2).after(functionDefinitionRule);
        c.setLinewrap(1, 1, 2).after(metaStatementRule);
        c.setLinewrap(1, 1, 2).after(mappingRuleRule);
        c.setLinewrap(1, 1, 2).after(optionalFeatureRule);
        c.setLinewrap(1, 1, 2).after(packageDeclarationRule);
	}
}