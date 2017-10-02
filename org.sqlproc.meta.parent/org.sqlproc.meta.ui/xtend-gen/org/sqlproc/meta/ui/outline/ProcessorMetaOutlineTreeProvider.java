/**
 * generated by Xtext
 */
package org.sqlproc.meta.ui.outline;

import com.google.common.base.Objects;
import java.util.Comparator;
import java.util.TreeSet;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.sqlproc.meta.processorMeta.Artifacts;
import org.sqlproc.meta.processorMeta.Column;
import org.sqlproc.meta.processorMeta.Constant;
import org.sqlproc.meta.processorMeta.DatabaseColumn;
import org.sqlproc.meta.processorMeta.Identifier;
import org.sqlproc.meta.processorMeta.MappingColumn;
import org.sqlproc.meta.processorMeta.MappingRule;
import org.sqlproc.meta.processorMeta.MetaStatement;
import org.sqlproc.meta.processorMeta.OptionalFeature;
import org.sqlproc.meta.util.Collector;
import org.sqlproc.meta.util.Utils;

/**
 * Customization of the default outline structure.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#outline
 */
@SuppressWarnings("all")
public class ProcessorMetaOutlineTreeProvider extends DefaultOutlineTreeProvider {
  @Override
  public void _createChildren(final DocumentRootNode parentNode, final EObject rootElement) {
    final Artifacts artifacts = ((Artifacts) rootElement);
    EList<OptionalFeature> _features = artifacts.getFeatures();
    boolean _notEquals = (!Objects.equal(_features, null));
    if (_notEquals) {
      EList<OptionalFeature> _features_1 = artifacts.getFeatures();
      for (final OptionalFeature optionalFeature : _features_1) {
        this.createNode(parentNode, optionalFeature);
      }
    }
    EList<MetaStatement> _statements = artifacts.getStatements();
    boolean _notEquals_1 = (!Objects.equal(_statements, null));
    if (_notEquals_1) {
      EList<MetaStatement> _statements_1 = artifacts.getStatements();
      for (final MetaStatement metaStatement : _statements_1) {
        this.createNode(parentNode, metaStatement);
      }
    }
    EList<MappingRule> _mappings = artifacts.getMappings();
    boolean _notEquals_2 = (!Objects.equal(_mappings, null));
    if (_notEquals_2) {
      EList<MappingRule> _mappings_1 = artifacts.getMappings();
      for (final MappingRule mappingRule : _mappings_1) {
        this.createNode(parentNode, mappingRule);
      }
    }
  }
  
  @Override
  public void _createChildren(final IOutlineNode parentNode, final EObject modelElement) {
    boolean _matched = false;
    if (modelElement instanceof MetaStatement) {
      _matched=true;
      final Comparator<Identifier> _function = (Identifier a, Identifier b) -> {
        return a.getName().compareTo(b.getName());
      };
      final TreeSet<Identifier> identifiers = CollectionLiterals.<Identifier>newTreeSet(_function);
      final Comparator<Constant> _function_1 = (Constant a, Constant b) -> {
        return a.getName().compareTo(b.getName());
      };
      final TreeSet<Constant> constants = CollectionLiterals.<Constant>newTreeSet(_function_1);
      final Comparator<Column> _function_2 = (Column a, Column b) -> {
        return Utils.getName(a).compareTo(Utils.getName(b));
      };
      final TreeSet<Column> columns = CollectionLiterals.<Column>newTreeSet(_function_2);
      final Comparator<DatabaseColumn> _function_3 = (DatabaseColumn a, DatabaseColumn b) -> {
        return a.getName().compareTo(b.getName());
      };
      final TreeSet<DatabaseColumn> databaseColumns = CollectionLiterals.<DatabaseColumn>newTreeSet(_function_3);
      Collector.allVariables(((MetaStatement) modelElement), identifiers, constants, columns, databaseColumns);
      for (final Identifier identifier : identifiers) {
        this.createNode(parentNode, identifier);
      }
      for (final Constant constant : constants) {
        this.createNode(parentNode, constant);
      }
      for (final Column column : columns) {
        this.createNode(parentNode, column);
      }
      for (final DatabaseColumn column_1 : databaseColumns) {
        this.createNode(parentNode, column_1);
      }
    }
    if (!_matched) {
      if (modelElement instanceof MappingRule) {
        _matched=true;
        final Comparator<MappingColumn> _function = (MappingColumn a, MappingColumn b) -> {
          return Utils.getName(a).compareTo(Utils.getName(b));
        };
        final TreeSet<MappingColumn> columns = CollectionLiterals.<MappingColumn>newTreeSet(_function);
        Collector.allVariables(((MappingRule) modelElement), columns);
        for (final MappingColumn column : columns) {
          this.createNode(parentNode, column);
        }
      }
    }
  }
  
  public boolean _isLeaf(final MetaStatement metaStatement) {
    return false;
  }
  
  public boolean _isLeaf(final MappingRule mappingRule) {
    return false;
  }
  
  public boolean _isLeaf(final OptionalFeature optionalFeature) {
    return true;
  }
  
  public boolean _isLeaf(final Identifier identifier) {
    return true;
  }
  
  public boolean _isLeaf(final Constant constant) {
    return true;
  }
  
  public boolean _isLeaf(final Column column) {
    return true;
  }
  
  public boolean _isLeaf(final DatabaseColumn column) {
    return true;
  }
  
  public boolean _isLeaf(final MappingColumn column) {
    return true;
  }
}
