/*
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.meta.web;

import com.google.inject.Binder;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import java.util.concurrent.ExecutorService;
import org.eclipse.xtext.ide.LexerIdeBindings;
import org.eclipse.xtext.ide.editor.contentassist.antlr.IContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;
import org.eclipse.xtext.web.server.DefaultWebModule;
import org.sqlproc.meta.ide.contentassist.antlr.ProcessorMetaParser;
import org.sqlproc.meta.ide.contentassist.antlr.internal.InternalProcessorMetaLexer;

/**
 * Manual modifications go to {@link ProcessorMetaWebModule}.
 */
@SuppressWarnings("all")
public abstract class AbstractProcessorMetaWebModule extends DefaultWebModule {

	public AbstractProcessorMetaWebModule(Provider<ExecutorService> executorServiceProvider) {
		super(executorServiceProvider);
	}
	
	// contributed by org.eclipse.xtext.xtext.generator.web.WebIntegrationFragment
	public void configureContentAssistLexer(Binder binder) {
		binder.bind(Lexer.class).annotatedWith(Names.named(LexerIdeBindings.CONTENT_ASSIST)).to(InternalProcessorMetaLexer.class);
	}
	
	// contributed by org.eclipse.xtext.xtext.generator.web.WebIntegrationFragment
	public Class<? extends IContentAssistParser> bindIContentAssistParser() {
		return ProcessorMetaParser.class;
	}
	
}
