package org.sqlproc.model.ui.highlighting;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.nodemodel.util.NodeTreeIterator;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.xbase.ide.highlighting.XbaseHighlightingCalculator;
import org.sqlproc.model.processorModel.FunctionDefinitionModel;
import org.sqlproc.model.processorModel.PojoDefinitionModel;
import org.sqlproc.model.processorModel.ProcedureDefinitionModel;
import org.sqlproc.model.processorModel.TableDefinitionModel;
import org.sqlproc.plugin.lib.resolver.PojoResolver;
import org.sqlproc.plugin.lib.resolver.PojoResolverFactory;

import com.google.inject.Inject;

public class SemanticHighlightingCalculator extends XbaseHighlightingCalculator {

    @Inject
    PojoResolver pojoResolver;

    @Inject
    PojoResolverFactory pojoResolverFactory;

    @Override
    public void provideHighlightingFor(XtextResource resource, IHighlightedPositionAcceptor acceptor,
            CancelIndicator cancelIndicator) {
        // tohle je blbarna, jak dostat tridu z org.sqlproc.dsl.ui do org.sqlproc.dsl, mozna to jde jednoduseji
        // if (pojoResolverFactory != null && pojoResolverFactory.getPojoResolver() == null)
        // pojoResolverFactory.setPojoResolver(pojoResolver);

        if (resource == null)
            return;

        super.provideHighlightingFor(resource, acceptor, cancelIndicator);

        Iterator<EObject> iter = EcoreUtil.getAllContents(resource, true);
        while (iter.hasNext()) {
            EObject current = iter.next();
            ICompositeNode node = NodeModelUtils.getNode(current);

            if (current instanceof PojoDefinitionModel) {
                PojoDefinitionModel pojo = (PojoDefinitionModel) current;
                provideHighlightingForPojo(null, pojo.getName(), node, acceptor);
            } else if (current instanceof TableDefinitionModel) {
                TableDefinitionModel table = (TableDefinitionModel) current;
                provideHighlightingForTable(null, table.getName(), node, acceptor);
            } else if (current instanceof ProcedureDefinitionModel) {
                ProcedureDefinitionModel procedure = (ProcedureDefinitionModel) current;
                provideHighlightingForTable(null, procedure.getName(), node, acceptor);
            } else if (current instanceof FunctionDefinitionModel) {
                FunctionDefinitionModel function = (FunctionDefinitionModel) current;
                provideHighlightingForTable(null, function.getName(), node, acceptor);
            }
        }
    }

    private void provideHighlightingForPojo(String name, String pojo, ICompositeNode node,
            IHighlightedPositionAcceptor acceptor) {
        if (name == null && pojo == null)
            return;
        Iterator<INode> iterator = new NodeTreeIterator(node);
        while (iterator.hasNext()) {
            INode inode = iterator.next();
            if (equals(name, inode)) {
                acceptor.addPosition(inode.getOffset(), inode.getLength(), HighlightingConfiguration.NAME);
                if (pojo == null)
                    return;
            }
            if (equals(pojo, inode)) {
                acceptor.addPosition(inode.getOffset(), inode.getLength(), HighlightingConfiguration.IDENTIFIER);
                return;
            }
        }
    }

    private void provideHighlightingForTable(String name, String table, ICompositeNode node,
            IHighlightedPositionAcceptor acceptor) {
        if (name == null && table == null)
            return;
        Iterator<INode> iterator = new NodeTreeIterator(node);
        while (iterator.hasNext()) {
            INode inode = iterator.next();
            if (equals(name, inode)) {
                acceptor.addPosition(inode.getOffset(), inode.getLength(), HighlightingConfiguration.NAME);
                if (table == null)
                    return;
            }
            if (equals(table, inode)) {
                acceptor.addPosition(inode.getOffset(), inode.getLength(), HighlightingConfiguration.IDENTIFIER);
                return;
            }
        }
    }

    private boolean equals(String name, INode inode) {
        if (name == null || inode == null)
            return false;
        String text = inode.getText();
        if (text == null)
            return false;
        if (name.equals(text.trim()))
            return true;
        return false;
    }
}
