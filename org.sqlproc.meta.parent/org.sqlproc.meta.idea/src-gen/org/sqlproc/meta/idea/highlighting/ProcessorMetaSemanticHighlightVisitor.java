/*
 * generated by Xtext 2.10.0
 */
package org.sqlproc.meta.idea.highlighting;

import org.eclipse.xtext.idea.highlighting.SemanticHighlightVisitor;
import org.sqlproc.meta.idea.lang.ProcessorMetaLanguage;

public class ProcessorMetaSemanticHighlightVisitor extends SemanticHighlightVisitor {
	public ProcessorMetaSemanticHighlightVisitor() {
		ProcessorMetaLanguage.INSTANCE.injectMembers(this);
	}
}
