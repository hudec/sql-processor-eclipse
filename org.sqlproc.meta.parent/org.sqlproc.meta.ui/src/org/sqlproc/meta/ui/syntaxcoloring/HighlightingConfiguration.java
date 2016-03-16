package org.sqlproc.meta.ui.syntaxcoloring;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfigurationAcceptor;
import org.eclipse.xtext.ui.editor.utils.TextStyle;

public class HighlightingConfiguration extends DefaultHighlightingConfiguration {

    public final static String NAME = "name";
    public static final String CONSTANT = "constant";
    public static final String IDENTIFIER = "identifier";
    public static final String COLUMN = "column";
    public static final String DATABASE_COLUMN = "dbcolumn";
    public static final String DATABASE_TABLE = "dbtable";
    public final static String MODIFIER = "modifier";
    public final static String STATEMENT_MODIFIER = "statementModifier";
    public static final String STRING = "string";

    public void configure(IHighlightingConfigurationAcceptor acceptor) {
        super.configure(acceptor);

        acceptor.acceptDefaultHighlighting(NAME, "Artifact name", nameStyle());
        acceptor.acceptDefaultHighlighting(CONSTANT, "Constant", constantStyle());
        acceptor.acceptDefaultHighlighting(IDENTIFIER, "Identifier", identifierStyle());
        acceptor.acceptDefaultHighlighting(COLUMN, "Column", columnStyle());
        acceptor.acceptDefaultHighlighting(DATABASE_COLUMN, "Database column", databaseColumnStyle());
        acceptor.acceptDefaultHighlighting(DATABASE_TABLE, "Database table", databaseTableStyle());
        acceptor.acceptDefaultHighlighting(MODIFIER, "Modifier", metaModifierStyle());
        acceptor.acceptDefaultHighlighting(STATEMENT_MODIFIER, "Statement modifier", statementModifierStyle());
    }

    public TextStyle defaultStyle() {
        TextStyle textStyle = new TextStyle();
        textStyle.setStyle(SWT.NORMAL);
        textStyle.setColor(new RGB(0, 0, 0));
        return textStyle;
    }

    public TextStyle nameStyle() {
        TextStyle textStyle = defaultStyle();
        textStyle.setColor(new RGB(127, 0, 85));
        textStyle.setStyle(SWT.BOLD);
        return textStyle;
    }

    public TextStyle constantStyle() {
        TextStyle textStyle = defaultStyle();
        textStyle.setColor(new RGB(42, 0, 255));
        return textStyle;
    }

    public TextStyle identifierStyle() {
        TextStyle textStyle = defaultStyle();
        textStyle.setColor(new RGB(42, 0, 255));
        return textStyle;
    }

    public TextStyle columnStyle() {
        TextStyle textStyle = defaultStyle();
        textStyle.setColor(new RGB(42, 0, 255));
        return textStyle;
    }

    public TextStyle databaseColumnStyle() {
        TextStyle textStyle = defaultStyle();
        textStyle.setColor(new RGB(165, 42, 42));
        return textStyle;
    }

    public TextStyle databaseTableStyle() {
        TextStyle textStyle = defaultStyle();
        textStyle.setColor(new RGB(165, 42, 42));
        return textStyle;
    }

    public TextStyle metaModifierStyle() {
        TextStyle textStyle = defaultStyle();
        textStyle.setColor(new RGB(210, 105, 30));
        return textStyle;
    }

    public TextStyle statementModifierStyle() {
        TextStyle textStyle = defaultStyle();
        textStyle.setStyle(SWT.ITALIC);
        return textStyle;
    }
}
