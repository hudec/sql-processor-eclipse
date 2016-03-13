/*
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.meta.idea.highlighting;

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.jetbrains.annotations.NotNull;
import org.sqlproc.meta.idea.lang.ProcessorMetaLanguage;

public class ProcessorMetaSyntaxHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {
	
	@Override
	@NotNull
	protected SyntaxHighlighter createHighlighter() {
		return ProcessorMetaLanguage.INSTANCE.getInstance(SyntaxHighlighter.class);
	}
}