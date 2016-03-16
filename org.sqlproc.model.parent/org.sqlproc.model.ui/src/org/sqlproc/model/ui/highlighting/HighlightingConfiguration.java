package org.sqlproc.model.ui.highlighting;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfigurationAcceptor;
import org.eclipse.xtext.ui.editor.utils.TextStyle;

public class HighlightingConfiguration extends DefaultHighlightingConfiguration {

    public final static String NAME = "name";
    public static final String IDENTIFIER = "identifier";

    public void configure(IHighlightingConfigurationAcceptor acceptor) {
        super.configure(acceptor);

        acceptor.acceptDefaultHighlighting(NAME, "Artifact name", nameStyle());
        acceptor.acceptDefaultHighlighting(IDENTIFIER, "Identifier", identifierStyle());
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

    public TextStyle identifierStyle() {
        TextStyle textStyle = defaultStyle();
        textStyle.setColor(new RGB(42, 0, 255));
        return textStyle;
    }
}
