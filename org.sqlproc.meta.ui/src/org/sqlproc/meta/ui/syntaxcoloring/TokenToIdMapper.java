package org.sqlproc.meta.ui.syntaxcoloring;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultAntlrTokenToAttributeIdMapper;
import org.sqlproc.meta.parser.antlr.internal.InternalProcessorMetaLexer;
import org.sqlproc.plugin.lib.property.ModelProperty;

public class TokenToIdMapper extends DefaultAntlrTokenToAttributeIdMapper {

    private static final Set<Integer> punctations = new HashSet<Integer>();

    static {
        punctations.add(InternalProcessorMetaLexer.RULE_IDENT_DOT);
        punctations.add(InternalProcessorMetaLexer.RULE_IDENT);
        punctations.add(InternalProcessorMetaLexer.RULE_NUMBER);
        punctations.add(InternalProcessorMetaLexer.RULE_ESC_CHAR);
        punctations.add(InternalProcessorMetaLexer.RULE_AND);
        punctations.add(InternalProcessorMetaLexer.RULE_OR);
        punctations.add(InternalProcessorMetaLexer.RULE_COLON);
        punctations.add(InternalProcessorMetaLexer.RULE_SEMICOLON);
        punctations.add(InternalProcessorMetaLexer.RULE_STRING);
        punctations.add(InternalProcessorMetaLexer.RULE_COMMA);
        punctations.add(InternalProcessorMetaLexer.RULE_MINUS);
        punctations.add(InternalProcessorMetaLexer.RULE_PLUS);
        punctations.add(InternalProcessorMetaLexer.RULE_LPAREN);
        punctations.add(InternalProcessorMetaLexer.RULE_RPAREN);
        punctations.add(InternalProcessorMetaLexer.RULE_LBRACE);
        punctations.add(InternalProcessorMetaLexer.RULE_RBRACE);
        punctations.add(InternalProcessorMetaLexer.RULE_QUESTI);
        punctations.add(InternalProcessorMetaLexer.RULE_NOT);
        punctations.add(InternalProcessorMetaLexer.RULE_BAND);
        punctations.add(InternalProcessorMetaLexer.RULE_BOR);
        punctations.add(InternalProcessorMetaLexer.RULE_HASH);
        punctations.add(InternalProcessorMetaLexer.RULE_AT);
        punctations.add(InternalProcessorMetaLexer.RULE_CARET);
        punctations.add(InternalProcessorMetaLexer.RULE_EQUALS);
        punctations.add(InternalProcessorMetaLexer.RULE_LESS_THAN);
        punctations.add(InternalProcessorMetaLexer.RULE_MORE_THAN);
        punctations.add(InternalProcessorMetaLexer.RULE_PERCENT);
        punctations.add(InternalProcessorMetaLexer.RULE_DOT);

        punctations.add(InternalProcessorMetaLexer.RULE_REST);
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
        keywords.add("'" + ModelProperty.RESOLVE_POJO_ON + "'");
        keywords.add("'" + ModelProperty.RESOLVE_POJO_OFF + "'");
        keywords.add("'" + ModelProperty.REPLACE_ALL_REGEX + "'");
        keywords.add("'" + ModelProperty.REPLACE_ALL_REPLACEMENT + "'");
        keywords.add("'" + ModelProperty.REPLACE_TEXT + "'");
        keywords.add("'" + ModelProperty.COMPRESS_META_DIRECTIVES + "'");
        keywords.add("'" + ModelProperty.VALIDATE_RESOURCES + "'");
        keywords.add("'" + ModelProperty.GENERATE_FROM_TO + "'");
        keywords.add("'" + ModelProperty.CASE_FORMAT_LIBRARY + "'");

        keywords.add("'" + ModelProperty.DATABASE + "-'");
        keywords.add("'" + ModelProperty.DATABASE_IS_ONLINE + "'");
        keywords.add("'" + ModelProperty.DATABASE_IS_OFFLINE + "'");
        keywords.add("'" + ModelProperty.DATABASE_HAS_URL + "'");
        keywords.add("'" + ModelProperty.DATABASE_EXECUTE_BEFORE + "'");
        keywords.add("'" + ModelProperty.DATABASE_EXECUTE_AFTER + "'");
        keywords.add("'" + ModelProperty.DATABASE_LOGIN_USERNAME + "'");
        keywords.add("'" + ModelProperty.DATABASE_LOGIN_PASSWORD + "'");
        keywords.add("'" + ModelProperty.DATABASE_IN_CATALOG + "'");
        keywords.add("'" + ModelProperty.DATABASE_ACTIVE_SCHEMA + "'");
        keywords.add("'" + ModelProperty.DATABASE_JDBC_DRIVER + "'");
        keywords.add("'" + ModelProperty.DATABASE_INDEX_TYPES + "'");
        keywords.add("'" + ModelProperty.DATABASE_SKIP_INDEXES + "'");
        keywords.add("'" + ModelProperty.DATABASE_SKIP_CHECK_CONSTRAINTS + "'");
        keywords.add("'" + ModelProperty.DATABASE_SKIP_PROCEDURES + "'");
        keywords.add("'" + ModelProperty.DATABASE_OF_TYPE + "'");
        keywords.add("'" + ModelProperty.DATABASE_DEBUG_LEVEL + "'");
        keywords.add("'" + ModelProperty.DATABASE_TAKE_COMMENTS + "'");
        keywords.add("'" + ModelProperty.DATABASE_LOWERCASE_NAMES + "'");
        keywords.add("'" + ModelProperty.DATABASE_UPPERCASE_NAMES + "'");

        keywords.add("'" + ModelProperty.POJOGEN + "-'");
        keywords.add("'" + ModelProperty.POJOGEN_TYPE_SQLTYPES + "'");
        keywords.add("'" + ModelProperty.POJOGEN_TYPE_IN_TABLE + "'");
        keywords.add("'" + ModelProperty.POJOGEN_TYPE_FOR_COLUMNS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_TYPE_FOR_PROCEDURE + "'");
        keywords.add("'" + ModelProperty.POJOGEN_TYPE_FOR_FUNCTION + "'");
        keywords.add("'" + ModelProperty.POJOGEN_SHOW_TYPE_FOR_COLUMN + "'");
        keywords.add("'" + ModelProperty.POJOGEN_SHOW_TYPE_FOR_PROCEDURE + "'");
        keywords.add("'" + ModelProperty.POJOGEN_SHOW_TYPE_FOR_FUNCTION + "'");
        keywords.add("'" + ModelProperty.POJOGEN_IGNORE_TABLES + "'");
        keywords.add("'" + ModelProperty.POJOGEN_ONLY_TABLES + "'");
        keywords.add("'" + ModelProperty.POJOGEN_CREATE_TABLES + "'");
        keywords.add("'" + ModelProperty.POJOGEN_IGNORE_COLUMNS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_REQUIRED_COLUMNS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_NOT_REQUIRED_COLUMNS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_CREATE_COLUMNS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_RENAME_TABLES + "'");
        keywords.add("'" + ModelProperty.POJOGEN_RENAME_COLUMNS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_IGNORE_EXPORTS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_IGNORE_IMPORTS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_CREATE_EXPORTS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_CREATE_IMPORTS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_CREATE_121_IMPORTS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_INHERIT_IMPORTS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_MANY_TO_MANY_IMPORTS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_INHERITANCE + "'");
        keywords.add("'" + ModelProperty.POJOGEN_GENERATE_METHODS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_GENERATE_OPERATORS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_IMPLEMENTS_INTERFACES + "'");
        keywords.add("'" + ModelProperty.POJOGEN_EXTENDS_CLASS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_IMPLEMENTS_INTERFACES_GENERICS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_EXTENDS_CLASS_GENERICS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_JOIN_TABLES + "'");
        keywords.add("'" + ModelProperty.POJOGEN_GENERATE_WRAPPERS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_GENERATE_VALIDATION_ANNOTATIONS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_NOT_ABSTRACT_TABLES_TABLES + "'");
        keywords.add("'" + ModelProperty.POJOGEN_MAKE_IT_FINAL + "'");
        keywords.add("'" + ModelProperty.POJOGEN_VERSION_COLUMN + "'");
        keywords.add("'" + ModelProperty.POJOGEN_DEBUG_LEVEL + "'");
        keywords.add("'" + ModelProperty.POJOGEN_PRESERVE_FOREIGN_KEYS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_POJOS_FOR_PROCEDURES + "'");
        keywords.add("'" + ModelProperty.POJOGEN_POJOS_FOR_FUNCTIONS + "'");
        keywords.add("'" + ModelProperty.POJOGEN_ACTIVE_FILTER + "'");
        keywords.add("'" + ModelProperty.POJOGEN_PACKAGE + "'");
        keywords.add("'" + ModelProperty.POJOGEN_ENUM_FOR_CHECK_CONSTRAINTS + "'");

        keywords.add("'" + ModelProperty.METAGEN + "-'");
        keywords.add("'" + ModelProperty.METAGEN_GLOBAL_SEQUENCE + "'");
        keywords.add("'" + ModelProperty.METAGEN_TABLE_SEQUENCE + "'");
        keywords.add("'" + ModelProperty.METAGEN_GLOBAL_IDENTITY + "'");
        keywords.add("'" + ModelProperty.METAGEN_TABLE_IDENTITY + "'");
        keywords.add("'" + ModelProperty.METAGEN_SQLTYPE_META_TYPE + "'");
        keywords.add("'" + ModelProperty.METAGEN_COLUMN_META_TYPE + "'");
        keywords.add("'" + ModelProperty.METAGEN_STATEMENT_META_TYPE + "'");
        keywords.add("'" + ModelProperty.METAGEN_MAKE_IT_FINAL + "'");
        keywords.add("'" + ModelProperty.METAGEN_LIKE_COLUMNS + "'");
        keywords.add("'" + ModelProperty.METAGEN_NOT_LIKE_COLUMNS + "'");
        keywords.add("'" + ModelProperty.METAGEN_GENERATE_SEQUENCES + "'");
        keywords.add("'" + ModelProperty.METAGEN_GENERATE_IDENTITIES + "'");
        keywords.add("'" + ModelProperty.METAGEN_GENERATE_IDGENERATORS + "'");
        keywords.add("'" + ModelProperty.METAGEN_GENERATE_INDIRECT_IDGENERATORS + "'");
        keywords.add("'" + ModelProperty.METAGEN_FUNCTION_RESULT + "'");
        keywords.add("'" + ModelProperty.METAGEN_FUNCTION_RESULT_SET + "'");
        keywords.add("'" + ModelProperty.METAGEN_PROCEDURE_RESULT_SET + "'");
        keywords.add("'" + ModelProperty.METAGEN_DEBUG_LEVEL + "'");
        keywords.add("'" + ModelProperty.METAGEN_GENERATE_OPERATORS + "'");
        keywords.add("'" + ModelProperty.METAGEN_OPTIMIZE_INSERT + "'");
        keywords.add("'" + ModelProperty.METAGEN_OPTIONAL_FEATURES + "'");
        keywords.add("'" + ModelProperty.METAGEN_ACTIVE_FILTER + "'");
        keywords.add("'" + ModelProperty.METAGEN_INSERT_SKIP_DEFAULT_VALUES + "'");

        keywords.add("'" + ModelProperty.DAOGEN + "-'");
        keywords.add("'" + ModelProperty.DAOGEN_IGNORE_TABLES + "'");
        keywords.add("'" + ModelProperty.DAOGEN_ONLY_TABLES + "'");
        keywords.add("'" + ModelProperty.DAOGEN_IMPLEMENTS_INTERFACES + "'");
        keywords.add("'" + ModelProperty.DAOGEN_EXTENDS_CLASS + "'");
        keywords.add("'" + ModelProperty.DAOGEN_IMPLEMENTS_INTERFACES_GENERICS + "'");
        keywords.add("'" + ModelProperty.DAOGEN_EXTENDS_CLASS_GENERICS + "'");
        keywords.add("'" + ModelProperty.DAOGEN_MAKE_IT_FINAL + "'");
        keywords.add("'" + ModelProperty.DAOGEN_FUNCTION_RESULT + "'");
        keywords.add("'" + ModelProperty.DAOGEN_DEBUG_LEVEL + "'");
        keywords.add("'" + ModelProperty.DAOGEN_ACTIVE_FILTER + "'");
        keywords.add("'" + ModelProperty.DAOGEN_PACKAGE + "'");
        keywords.add("'" + ModelProperty.DAOGEN_IMPLEMENTATION_PACKAGE + "'");

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

        if (tokenType == InternalProcessorMetaLexer.RULE_ML_COMMENT
                || tokenType == InternalProcessorMetaLexer.RULE_SL_COMMENT)
            return HighlightingConfiguration.COMMENT;

        if (tokenType == InternalProcessorMetaLexer.RULE_STRING_VALUE)
            return HighlightingConfiguration.STRING;

        if (types.contains(tokenName))
            return HighlightingConfiguration.TYPE;

        if (keywords.contains(tokenName))
            return HighlightingConfiguration.KEYWORD;

        return HighlightingConfiguration.DEFAULT;
    }
}
