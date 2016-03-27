package org.sqlproc.model.ui.syntaxcoloring;

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.xbase.ui.highlighting.XbaseHighlightingCalculator;
import org.sqlproc.plugin.lib.resolver.PojoResolver;
import org.sqlproc.plugin.lib.resolver.PojoResolverFactory;

import com.google.inject.Inject;

public class SemanticHighlightingCalculator extends XbaseHighlightingCalculator {

    @Inject
    PojoResolver pojoResolver;

    @Inject
    PojoResolverFactory pojoResolverFactory;

    @Override
    public void provideHighlightingFor(XtextResource resource, IHighlightedPositionAcceptor acceptor) {
        // tohle je blbarna, jak dostat tridu z org.sqlproc.dsl.ui do org.sqlproc.dsl, mozna to jde jednoduseji
        if (pojoResolverFactory != null && pojoResolverFactory.getPojoResolver() == null)
            pojoResolverFactory.setPojoResolver(pojoResolver);

        if (resource == null)
            return;

        super.provideHighlightingFor(resource, acceptor);
    }
}
