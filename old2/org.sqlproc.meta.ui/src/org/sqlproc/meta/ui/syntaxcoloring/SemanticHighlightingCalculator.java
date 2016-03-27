package org.sqlproc.meta.ui.syntaxcoloring;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.nodemodel.util.NodeTreeIterator;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.sqlproc.meta.processorMeta.Constant;
import org.sqlproc.meta.processorMeta.DatabaseColumn;
import org.sqlproc.meta.processorMeta.DatabaseTable;
import org.sqlproc.meta.processorMeta.ExtendedColumn;
import org.sqlproc.meta.processorMeta.ExtendedMappingItem;
import org.sqlproc.meta.processorMeta.FunctionDefinitionModel;
import org.sqlproc.meta.processorMeta.Identifier;
import org.sqlproc.meta.processorMeta.MappingItem;
import org.sqlproc.meta.processorMeta.MappingRule;
import org.sqlproc.meta.processorMeta.MetaStatement;
import org.sqlproc.meta.processorMeta.OptionalFeature;
import org.sqlproc.meta.processorMeta.PojoDefinitionModel;
import org.sqlproc.meta.processorMeta.ProcedureDefinitionModel;
import org.sqlproc.meta.processorMeta.TableDefinitionModel;
import org.sqlproc.plugin.lib.resolver.PojoResolver;
import org.sqlproc.plugin.lib.resolver.PojoResolverFactory;
import org.sqlproc.plugin.lib.util.Pair;

import com.google.inject.Inject;

public class SemanticHighlightingCalculator implements ISemanticHighlightingCalculator {

    public static final String CARET = "^";
    public static final String STRING = "$";
    public static final String LPAREN = "(";
    public static final String RPAREN = ")";

    @Inject
    PojoResolver pojoResolver;

    @Inject
    PojoResolverFactory pojoResolverFactory;

    @Override
    public void provideHighlightingFor(XtextResource resource, IHighlightedPositionAcceptor acceptor) {
        // tohle je blbarna, jak dostat tridu z org.sqlproc.dsl.ui do org.sqlproc.dsl, mozna to jde jednoduseji
        // if (pojoResolverFactory != null && pojoResolverFactory.getPojoResolver() == null)
        // pojoResolverFactory.setPojoResolver(pojoResolver);

        if (resource == null)
            return;

        Iterator<EObject> iter = EcoreUtil.getAllContents(resource, true);
        while (iter.hasNext()) {
            EObject current = iter.next();
            ICompositeNode node = NodeModelUtils.getNode(current);

            if (current instanceof MetaStatement) {
                MetaStatement statement = (MetaStatement) current;
                if (statement.getName() != null) {
                    // Nazev statementu je prvni pole, neni treba vyhledat node
                    acceptor.addPosition(node.getOffset(), statement.getName().length(),
                            HighlightingConfiguration.NAME);
                    provideHighlightingForModifiers(statement.getModifiers(), node, acceptor);
                }
            } else if (current instanceof MappingRule) {
                MappingRule rule = (MappingRule) current;
                if (rule.getName() != null) {
                    // Nazev pravidla je prvni pole, neni treba vyhledat node
                    acceptor.addPosition(node.getOffset(), rule.getName().length(), HighlightingConfiguration.NAME);
                    provideHighlightingForModifiers(rule.getModifiers(), node, acceptor);
                }
            } else if (current instanceof OptionalFeature) {
                OptionalFeature feature = (OptionalFeature) current;
                if (feature.getName() != null) {
                    // Nazev vlastnosti je prvni pole, neni treba vyhledat node
                    acceptor.addPosition(node.getOffset(), feature.getName().length(), HighlightingConfiguration.NAME);
                    provideHighlightingForModifiers(feature.getModifiers(), node, acceptor);
                }
            } else if (current instanceof Constant) {
                Constant constant = (Constant) current;
                provideHighlightingForFragment(HighlightingConfiguration.CONSTANT, node, constant.getName(),
                        constant.getModifiers(), acceptor);
            } else if (current instanceof Identifier) {
                Identifier identifier = (Identifier) current;
                provideHighlightingForFragment(HighlightingConfiguration.IDENTIFIER, node, identifier.getName(),
                        identifier.getModifiers(), acceptor);
            } else if (current instanceof ExtendedColumn) {
                ExtendedColumn column = (ExtendedColumn) current;
                provideHighlightingForFragment(HighlightingConfiguration.COLUMN, node, column.getCol().getName(),
                        column.getModifiers(), acceptor);
            } else if (current instanceof MappingItem) {
                MappingItem item = (MappingItem) current;
                if (item.getName() != null) {
                    // Nazev vlastnosti je prvni pole, neni treba vyhledat node
                    acceptor.addPosition(node.getOffset(), item.getName().length(), HighlightingConfiguration.COLUMN);
                }
            } else if (current instanceof ExtendedMappingItem) {
                ExtendedMappingItem mappingItem = (ExtendedMappingItem) current;
                provideHighlightingForFragment(HighlightingConfiguration.COLUMN, node, mappingItem.getAttr().getName(),
                        mappingItem.getModifiers(), acceptor);
            } else if (current instanceof DatabaseColumn) {
                acceptor.addPosition(node.getOffset(), node.getLength(), HighlightingConfiguration.DATABASE_COLUMN);
            } else if (current instanceof DatabaseTable) {
                acceptor.addPosition(node.getOffset(), node.getLength(), HighlightingConfiguration.DATABASE_TABLE);
            } else if (current instanceof PojoDefinitionModel) {
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

            else if (current instanceof Package) {
                provideSimpleHighlighting(node, acceptor,
                        newPair(((Package) current).getName(), HighlightingConfiguration.PACKAGE_NAME));
            }
        }
    }

    private Pair<String, String> newPair(String name, String highlightingId) {
        if (name != null)
            return new Pair<String, String>(name, highlightingId);
        return null;
    }

    private void provideSimpleHighlighting(ICompositeNode node, IHighlightedPositionAcceptor acceptor,
            List<Pair<String, String>> tokens) {
        if (tokens == null || tokens.isEmpty())
            return;
        int ix = 0, lx = tokens.size();
        Iterator<INode> iterator = new NodeTreeIterator(node);
        while (iterator.hasNext()) {
            INode inode = iterator.next();
            for (int i = ix; i < lx; i++) {
                if (equals(tokens.get(i).getFirst(), inode)) {
                    acceptor.addPosition(inode.getOffset(), inode.getLength(), tokens.get(i).getSecond());
                    if (i == lx - 1)
                        return;
                    else
                        ix = i + 1;
                }
            }
        }
    }

    private void provideSimpleHighlighting(ICompositeNode node, IHighlightedPositionAcceptor acceptor,
            Pair<String, String>... tokens) {
        if (tokens == null || tokens.length == 0)
            return;
        int ix = 0, lx = tokens.length;
        Iterator<INode> iterator = new NodeTreeIterator(node);
        while (iterator.hasNext()) {
            INode inode = iterator.next();
            for (int i = ix; i < lx; i++) {
                if (equals(tokens[i].getFirst(), inode)) {
                    acceptor.addPosition(inode.getOffset(), inode.getLength(), tokens[i].getSecond());
                    if (i == lx - 1)
                        return;
                    else
                        ix = i + 1;
                }
            }
        }
    }

    private void provideHighlightingForModifiers(EList<String> filters, ICompositeNode node,
            IHighlightedPositionAcceptor acceptor) {
        if (filters != null && !filters.isEmpty()) {
            Iterator<INode> iterator = new NodeTreeIterator(node);
            while (iterator.hasNext()) {
                INode inode = iterator.next();
                if (filters.contains(inode.getText())) {
                    acceptor.addPosition(inode.getOffset(), inode.getLength(),
                            HighlightingConfiguration.STATEMENT_MODIFIER);
                }
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

    private void provideHighlightingForFragment(String defaultColor, ICompositeNode node, String name,
            EList<String> modifiers, IHighlightedPositionAcceptor acceptor) {
        Iterator<INode> iterator = new NodeTreeIterator(node);
        boolean afterName = false;
        boolean inParenthesis = false;
        while (iterator.hasNext()) {
            INode inode = iterator.next();
            if (!afterName) {
                if (equals(name, inode)) {
                    acceptor.addPosition(inode.getOffset(), inode.getLength(), defaultColor);
                    afterName = true;
                }
            } else if (!inParenthesis && inode.getText().equals("(")) {
                inParenthesis = true;
            } else if (inParenthesis && inode.getText().equals(")")) {
                inParenthesis = false;
            } else if (inParenthesis) {
                if (modifiers != null && !modifiers.isEmpty() && modifiers.contains(inode.getText())) {
                    acceptor.addPosition(inode.getOffset(), inode.getLength(), HighlightingConfiguration.MODIFIER);
                }
            } else {
                acceptor.addPosition(inode.getOffset(), inode.getLength(), defaultColor);
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
