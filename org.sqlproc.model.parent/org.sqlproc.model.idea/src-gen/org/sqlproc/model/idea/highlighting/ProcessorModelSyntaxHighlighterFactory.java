/*
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.model.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.jetbrains.annotations.NotNull;
import org.sqlproc.model.idea.lang.ProcessorModelLanguage;

public class ProcessorModelSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
	@NotNull
	protected SyntaxHighlighter createHighlighter() {
		return ProcessorModelLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
	}
}
