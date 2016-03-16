package org.sqlproc.meta.ui.syntaxcoloring;

import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultAntlrTokenToAttributeIdMapper;
import org.sqlproc.meta.parser.antlr.internal.InternalProcessorMetaLexer;

public class TokenToIdMapper extends DefaultAntlrTokenToAttributeIdMapper {

    @Override
    protected String calculateId(String tokenName, int tokenType) {

        if (tokenType == InternalProcessorMetaLexer.RULE_STRING_VALUE)
            return HighlightingConfiguration.STRING;

        return super.calculateId(tokenName, tokenType);
    }
}
