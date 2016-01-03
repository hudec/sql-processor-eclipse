package org.sqlproc.dsl.ui.syntaxcoloring;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultAntlrTokenToAttributeIdMapper;
import org.sqlproc.dsl.parser.antlr.internal.InternalProcessorDslLexer;

public class TokenToIdMapper extends DefaultAntlrTokenToAttributeIdMapper {

    private static final Set<Integer> punctations = new HashSet<Integer>();
    static {
        punctations.add(InternalProcessorDslLexer.RULE_IDENT_DOT);
        punctations.add(InternalProcessorDslLexer.RULE_IDENT);
        punctations.add(InternalProcessorDslLexer.RULE_NUMBER);
        punctations.add(InternalProcessorDslLexer.RULE_ESC_CHAR);
        punctations.add(InternalProcessorDslLexer.RULE_AND);
        punctations.add(InternalProcessorDslLexer.RULE_OR);
        punctations.add(InternalProcessorDslLexer.RULE_COLON);
        punctations.add(InternalProcessorDslLexer.RULE_SEMICOLON);
        punctations.add(InternalProcessorDslLexer.RULE_STRING);
        punctations.add(InternalProcessorDslLexer.RULE_COMMA);
        punctations.add(InternalProcessorDslLexer.RULE_MINUS);
        punctations.add(InternalProcessorDslLexer.RULE_PLUS);
        punctations.add(InternalProcessorDslLexer.RULE_LPAREN);
        punctations.add(InternalProcessorDslLexer.RULE_RPAREN);
        punctations.add(InternalProcessorDslLexer.RULE_LBRACE);
        punctations.add(InternalProcessorDslLexer.RULE_RBRACE);
        punctations.add(InternalProcessorDslLexer.RULE_QUESTI);
        punctations.add(InternalProcessorDslLexer.RULE_NOT);
        punctations.add(InternalProcessorDslLexer.RULE_BAND);
        punctations.add(InternalProcessorDslLexer.RULE_BOR);
        punctations.add(InternalProcessorDslLexer.RULE_HASH);
        punctations.add(InternalProcessorDslLexer.RULE_AT);
        punctations.add(InternalProcessorDslLexer.RULE_CARET);
        punctations.add(InternalProcessorDslLexer.RULE_EQUALS);
        punctations.add(InternalProcessorDslLexer.RULE_LESS_THAN);
        punctations.add(InternalProcessorDslLexer.RULE_MORE_THAN);
        punctations.add(InternalProcessorDslLexer.RULE_PERCENT);
        punctations.add(InternalProcessorDslLexer.RULE_DOT);

        punctations.add(InternalProcessorDslLexer.RULE_REST);
    }

    private static final Set<String> types = new HashSet<String>();
    static {
        types.add("'QRY'");
        types.add("'CRUD'");
        types.add("'CALL'");
        types.add("'OUT'");
        types.add("'OPT'");
        types.add("'LOPT'");
        types.add("'IOPT'");
        types.add("'SOPT'");
        types.add("'BOPT'");
        types.add("'MOPT'");
    }

    private static final Set<String> keywords = new HashSet<String>();
    static {
        keywords.add("'pojo'");
        keywords.add("'table'");
        keywords.add("'procedure'");
        keywords.add("'function'");
        keywords.add("'package'");
        keywords.add("'import'");
        keywords.add("'implements'");
        keywords.add("'extends'");
        keywords.add("'onlyPojos'");
        keywords.add("'onlyDaos'");
        keywords.add("'exceptPojos'");
        keywords.add("'exceptDaos'");
        keywords.add("'final'");
        keywords.add("'abstract'");
        keywords.add("'enum'");
        keywords.add("'dao'");
    }

    @Override
    protected String calculateId(String tokenName, int tokenType) {
        if (punctations.contains(tokenType))
            return HighlightingConfiguration.PUNCTATION;

        if (tokenType == InternalProcessorDslLexer.RULE_ML_COMMENT
                || tokenType == InternalProcessorDslLexer.RULE_SL_COMMENT)
            return HighlightingConfiguration.COMMENT;

        if (types.contains(tokenName))
            return HighlightingConfiguration.TYPE;

        if (keywords.contains(tokenName))
            return HighlightingConfiguration.KEYWORD;

        return HighlightingConfiguration.DEFAULT;
    }
}
