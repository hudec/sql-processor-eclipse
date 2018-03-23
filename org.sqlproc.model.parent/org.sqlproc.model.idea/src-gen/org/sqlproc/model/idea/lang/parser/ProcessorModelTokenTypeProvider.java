/*
 * generated by Xtext 2.10.0
 */
package org.sqlproc.model.idea.lang.parser;

import com.google.inject.Singleton;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.antlr.runtime.Token;
import org.eclipse.xtext.idea.parser.TokenTypeProvider;
import org.sqlproc.model.idea.lang.ProcessorModelLanguage;
import org.sqlproc.model.idea.parser.antlr.internal.PsiInternalProcessorModelParser;

@Singleton
public class ProcessorModelTokenTypeProvider implements TokenTypeProvider {

	private static final String[] TOKEN_NAMES = new PsiInternalProcessorModelParser(null).getTokenNames();

	private static final IElementType[] tokenTypes = new IElementType[TOKEN_NAMES.length];
	
	static {
		for (int i = 0; i < TOKEN_NAMES.length; i++) {
			tokenTypes[i] = new IndexedElementType(TOKEN_NAMES[i], i, ProcessorModelLanguage.INSTANCE);
		}
	}

	private static final TokenSet WHITESPACE_TOKENS = TokenSet.create(tokenTypes[PsiInternalProcessorModelParser.RULE_WS]);
	private static final TokenSet COMMENT_TOKENS = TokenSet.create(tokenTypes[PsiInternalProcessorModelParser.RULE_SL_COMMENT], tokenTypes[PsiInternalProcessorModelParser.RULE_ML_COMMENT]);
	private static final TokenSet STRING_TOKENS = TokenSet.create(tokenTypes[PsiInternalProcessorModelParser.RULE_STRING]);

	@Override
	public int getAntlrType(IElementType iElementType) {
		return (iElementType instanceof IndexedElementType) ? ((IndexedElementType) iElementType).getLocalIndex() : Token.INVALID_TOKEN_TYPE;
	}

	@Override
	public IElementType getIElementType(int antlrType) {
		return tokenTypes[antlrType];
	}

	@Override
	public TokenSet getWhitespaceTokens() {
		return WHITESPACE_TOKENS;
	}

	@Override
	public TokenSet getCommentTokens() {
		return COMMENT_TOKENS;
	}

	@Override
	public TokenSet getStringLiteralTokens() {
		return STRING_TOKENS;
	}

}
