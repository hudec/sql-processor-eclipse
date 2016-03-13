/**
 * generated by Xtext
 */
package org.sqlproc.model.validation;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.validation.Check;
import org.sqlproc.model.processorModel.AbstractEntity;
import org.sqlproc.model.processorModel.AnnotatedEntity;
import org.sqlproc.model.processorModel.Artifacts;
import org.sqlproc.model.processorModel.DaoEntity;
import org.sqlproc.model.processorModel.Entity;
import org.sqlproc.model.processorModel.EnumEntity;
import org.sqlproc.model.processorModel.FunctionDefinitionModel;
import org.sqlproc.model.processorModel.PojoAttribute;
import org.sqlproc.model.processorModel.PojoDefinitionModel;
import org.sqlproc.model.processorModel.PojoEntity;
import org.sqlproc.model.processorModel.ProcedureDefinitionModel;
import org.sqlproc.model.processorModel.ProcessorModelPackage;
import org.sqlproc.model.processorModel.Property;
import org.sqlproc.model.processorModel.TableDefinitionModel;
import org.sqlproc.model.validation.AbstractProcessorModelValidator;
import org.sqlproc.plugin.lib.property.ModelProperty;
import org.sqlproc.plugin.lib.resolver.DbResolver;
import org.sqlproc.plugin.lib.util.CommonUtils;

/**
 * Custom validation rules.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#validation
 */
@SuppressWarnings("all")
public class ProcessorModelValidator extends AbstractProcessorModelValidator {
  @Inject
  private DbResolver dbResolver;
  
  @Inject
  private ModelProperty modelProperty;
  
  @Check
  public void checkUniquePojoDefinition(final PojoDefinitionModel pojoDefinition) {
    boolean _skipVerification = CommonUtils.skipVerification(pojoDefinition, this.modelProperty);
    if (_skipVerification) {
      return;
    }
    final Artifacts artifacts = this.getArtifacts(pojoDefinition);
    boolean _equals = Objects.equal(artifacts, null);
    if (_equals) {
      return;
    }
    EList<PojoDefinitionModel> _pojos = artifacts.getPojos();
    for (final PojoDefinitionModel definition : _pojos) {
      boolean _and = false;
      boolean _notEquals = (!Objects.equal(definition, null));
      if (!_notEquals) {
        _and = false;
      } else {
        _and = (definition != pojoDefinition);
      }
      if (_and) {
        String _name = pojoDefinition.getName();
        String _name_1 = definition.getName();
        boolean _equals_1 = _name.equals(_name_1);
        if (_equals_1) {
          String _name_2 = pojoDefinition.getName();
          String _plus = ("Duplicate name : " + _name_2);
          this.error(_plus, 
            ProcessorModelPackage.Literals.POJO_DEFINITION_MODEL__NAME);
          return;
        }
      }
    }
  }
  
  @Check
  public void checkUniqueProperty(final Property property) {
    boolean _skipVerification = CommonUtils.skipVerification(property, this.modelProperty);
    if (_skipVerification) {
      return;
    }
    final Artifacts artifacts = this.getArtifacts(property);
    boolean _equals = Objects.equal(artifacts, null);
    if (_equals) {
      return;
    }
    EList<Property> _properties = artifacts.getProperties();
    for (final Property prop : _properties) {
      boolean _and = false;
      boolean _notEquals = (!Objects.equal(prop, null));
      if (!_notEquals) {
        _and = false;
      } else {
        _and = (prop != property);
      }
      if (_and) {
        boolean _and_1 = false;
        boolean _and_2 = false;
        boolean _and_3 = false;
        boolean _and_4 = false;
        boolean _and_5 = false;
        String _name = prop.getName();
        String _name_1 = property.getName();
        boolean _equals_1 = _name.equals(_name_1);
        if (!_equals_1) {
          _and_5 = false;
        } else {
          String _name_2 = prop.getName();
          boolean _startsWith = _name_2.startsWith("pojogen");
          boolean _not = (!_startsWith);
          _and_5 = _not;
        }
        if (!_and_5) {
          _and_4 = false;
        } else {
          String _name_3 = prop.getName();
          boolean _startsWith_1 = _name_3.startsWith("database");
          boolean _not_1 = (!_startsWith_1);
          _and_4 = _not_1;
        }
        if (!_and_4) {
          _and_3 = false;
        } else {
          String _name_4 = prop.getName();
          boolean _startsWith_2 = _name_4.startsWith("metagen");
          boolean _not_2 = (!_startsWith_2);
          _and_3 = _not_2;
        }
        if (!_and_3) {
          _and_2 = false;
        } else {
          String _name_5 = prop.getName();
          boolean _startsWith_3 = _name_5.startsWith("daogen");
          boolean _not_3 = (!_startsWith_3);
          _and_2 = _not_3;
        }
        if (!_and_2) {
          _and_1 = false;
        } else {
          String _name_6 = prop.getName();
          boolean _startsWith_4 = _name_6.startsWith("replace-text");
          boolean _not_4 = (!_startsWith_4);
          _and_1 = _not_4;
        }
        if (_and_1) {
          String _name_7 = property.getName();
          String _plus = ("Duplicate name : " + _name_7);
          this.error(_plus, ProcessorModelPackage.Literals.PROPERTY__NAME);
          return;
        }
      }
    }
  }
  
  @Check
  public void checkTableDefinition(final TableDefinitionModel tableDefinition) {
    boolean _skipVerification = CommonUtils.skipVerification(tableDefinition, this.modelProperty);
    if (_skipVerification) {
      return;
    }
    final Artifacts artifacts = this.getArtifacts(tableDefinition);
    boolean _equals = Objects.equal(artifacts, null);
    if (_equals) {
      return;
    }
    EList<TableDefinitionModel> _tables = artifacts.getTables();
    for (final TableDefinitionModel table : _tables) {
      boolean _and = false;
      boolean _notEquals = (!Objects.equal(table, null));
      if (!_notEquals) {
        _and = false;
      } else {
        _and = (table != tableDefinition);
      }
      if (_and) {
        String _name = tableDefinition.getName();
        String _name_1 = table.getName();
        boolean _equals_1 = _name.equals(_name_1);
        if (_equals_1) {
          String _name_2 = tableDefinition.getName();
          String _plus = ("Duplicate name : " + _name_2);
          String _plus_1 = (_plus + "[table]");
          this.error(_plus_1, 
            ProcessorModelPackage.Literals.TABLE_DEFINITION_MODEL__NAME);
          return;
        }
      }
    }
    boolean _and_1 = false;
    boolean _isResolveDb = this.isResolveDb(tableDefinition);
    if (!_isResolveDb) {
      _and_1 = false;
    } else {
      String _table = tableDefinition.getTable();
      boolean _checkTable = this.dbResolver.checkTable(tableDefinition, _table);
      boolean _not = (!_checkTable);
      _and_1 = _not;
    }
    if (_and_1) {
      String _table_1 = tableDefinition.getTable();
      String _plus_2 = ("Cannot find table in DB : " + _table_1);
      this.error(_plus_2, 
        ProcessorModelPackage.Literals.TABLE_DEFINITION_MODEL__TABLE);
    }
  }
  
  @Check
  public void checkProcedureDefinition(final ProcedureDefinitionModel procedureDefinition) {
    boolean _skipVerification = CommonUtils.skipVerification(procedureDefinition, this.modelProperty);
    if (_skipVerification) {
      return;
    }
    final Artifacts artifacts = this.getArtifacts(procedureDefinition);
    boolean _equals = Objects.equal(artifacts, null);
    if (_equals) {
      return;
    }
    EList<ProcedureDefinitionModel> _procedures = artifacts.getProcedures();
    for (final ProcedureDefinitionModel procedure : _procedures) {
      boolean _and = false;
      boolean _notEquals = (!Objects.equal(procedure, null));
      if (!_notEquals) {
        _and = false;
      } else {
        _and = (procedure != procedureDefinition);
      }
      if (_and) {
        String _name = procedureDefinition.getName();
        String _name_1 = procedure.getName();
        boolean _equals_1 = _name.equals(_name_1);
        if (_equals_1) {
          String _name_2 = procedureDefinition.getName();
          String _plus = ("Duplicate name : " + _name_2);
          String _plus_1 = (_plus + "[procedure]");
          this.error(_plus_1, 
            ProcessorModelPackage.Literals.PROCEDURE_DEFINITION_MODEL__NAME);
          return;
        }
      }
    }
    boolean _and_1 = false;
    boolean _isResolveDb = this.isResolveDb(procedureDefinition);
    if (!_isResolveDb) {
      _and_1 = false;
    } else {
      String _table = procedureDefinition.getTable();
      boolean _checkProcedure = this.dbResolver.checkProcedure(procedureDefinition, _table);
      boolean _not = (!_checkProcedure);
      _and_1 = _not;
    }
    if (_and_1) {
      String _table_1 = procedureDefinition.getTable();
      String _plus_2 = ("Cannot find procedure in DB : " + _table_1);
      this.error(_plus_2, 
        ProcessorModelPackage.Literals.PROCEDURE_DEFINITION_MODEL__NAME);
    }
  }
  
  @Check
  public void checkFunctionDefinition(final FunctionDefinitionModel functionDefinition) {
    boolean _skipVerification = CommonUtils.skipVerification(functionDefinition, this.modelProperty);
    if (_skipVerification) {
      return;
    }
    final Artifacts artifacts = this.getArtifacts(functionDefinition);
    boolean _equals = Objects.equal(artifacts, null);
    if (_equals) {
      return;
    }
    EList<FunctionDefinitionModel> _functions = artifacts.getFunctions();
    for (final FunctionDefinitionModel function : _functions) {
      boolean _and = false;
      boolean _notEquals = (!Objects.equal(function, null));
      if (!_notEquals) {
        _and = false;
      } else {
        _and = (function != functionDefinition);
      }
      if (_and) {
        String _name = functionDefinition.getName();
        String _name_1 = function.getName();
        boolean _equals_1 = _name.equals(_name_1);
        if (_equals_1) {
          String _name_2 = functionDefinition.getName();
          String _plus = ("Duplicate name : " + _name_2);
          String _plus_1 = (_plus + "[function]");
          this.error(_plus_1, 
            ProcessorModelPackage.Literals.FUNCTION_DEFINITION_MODEL__NAME);
          return;
        }
      }
    }
  }
  
  @Check
  public void checkUniquePojoEntity(final PojoEntity pojoEntity) {
    boolean _skipVerification = CommonUtils.skipVerification(pojoEntity, this.modelProperty);
    if (_skipVerification) {
      return;
    }
    final Artifacts artifacts = this.getArtifacts(pojoEntity);
    boolean _equals = Objects.equal(artifacts, null);
    if (_equals) {
      return;
    }
    EList<org.sqlproc.model.processorModel.Package> _packages = artifacts.getPackages();
    for (final org.sqlproc.model.processorModel.Package pkg : _packages) {
      boolean _notEquals = (!Objects.equal(pkg, null));
      if (_notEquals) {
        EList<AbstractEntity> _elements = pkg.getElements();
        for (final AbstractEntity entity : _elements) {
          boolean _and = false;
          boolean _notEquals_1 = (!Objects.equal(entity, null));
          if (!_notEquals_1) {
            _and = false;
          } else {
            _and = (entity instanceof AnnotatedEntity);
          }
          if (_and) {
            final AnnotatedEntity aentity = ((AnnotatedEntity) entity);
            boolean _and_1 = false;
            Entity _entity = aentity.getEntity();
            boolean _notEquals_2 = (!Objects.equal(_entity, null));
            if (!_notEquals_2) {
              _and_1 = false;
            } else {
              Entity _entity_1 = aentity.getEntity();
              _and_1 = (_entity_1 instanceof PojoEntity);
            }
            if (_and_1) {
              Entity _entity_2 = aentity.getEntity();
              final PojoEntity pentity = ((PojoEntity) _entity_2);
              if ((pentity != pojoEntity)) {
                String _name = pojoEntity.getName();
                String _name_1 = pentity.getName();
                boolean _equals_1 = _name.equals(_name_1);
                if (_equals_1) {
                  String _name_2 = pojoEntity.getName();
                  String _plus = ("Duplicate name : " + _name_2);
                  this.error(_plus, ProcessorModelPackage.Literals.ENTITY__NAME);
                  return;
                }
              }
            }
          }
        }
      }
    }
  }
  
  @Check
  public void checkUniqueEnumEntity(final EnumEntity enumEntity) {
    boolean _skipVerification = CommonUtils.skipVerification(enumEntity, this.modelProperty);
    if (_skipVerification) {
      return;
    }
    final Artifacts artifacts = this.getArtifacts(enumEntity);
    boolean _equals = Objects.equal(artifacts, null);
    if (_equals) {
      return;
    }
    EList<org.sqlproc.model.processorModel.Package> _packages = artifacts.getPackages();
    for (final org.sqlproc.model.processorModel.Package pkg : _packages) {
      boolean _notEquals = (!Objects.equal(pkg, null));
      if (_notEquals) {
        EList<AbstractEntity> _elements = pkg.getElements();
        for (final AbstractEntity entity : _elements) {
          boolean _and = false;
          boolean _notEquals_1 = (!Objects.equal(entity, null));
          if (!_notEquals_1) {
            _and = false;
          } else {
            _and = (entity instanceof AnnotatedEntity);
          }
          if (_and) {
            final AnnotatedEntity aentity = ((AnnotatedEntity) entity);
            boolean _and_1 = false;
            Entity _entity = aentity.getEntity();
            boolean _notEquals_2 = (!Objects.equal(_entity, null));
            if (!_notEquals_2) {
              _and_1 = false;
            } else {
              Entity _entity_1 = aentity.getEntity();
              _and_1 = (_entity_1 instanceof EnumEntity);
            }
            if (_and_1) {
              Entity _entity_2 = aentity.getEntity();
              final EnumEntity pentity = ((EnumEntity) _entity_2);
              boolean _notEquals_3 = (!Objects.equal(pentity, enumEntity));
              if (_notEquals_3) {
                String _name = enumEntity.getName();
                String _name_1 = pentity.getName();
                boolean _equals_1 = _name.equals(_name_1);
                if (_equals_1) {
                  String _name_2 = enumEntity.getName();
                  String _plus = ("Duplicate name : " + _name_2);
                  this.error(_plus, ProcessorModelPackage.Literals.ENTITY__NAME);
                  return;
                }
              }
            }
          }
        }
      }
    }
  }
  
  @Check
  public void checkUniqueDaoEntity(final DaoEntity daoEntity) {
    boolean _skipVerification = CommonUtils.skipVerification(daoEntity, this.modelProperty);
    if (_skipVerification) {
      return;
    }
    final Artifacts artifacts = this.getArtifacts(daoEntity);
    boolean _equals = Objects.equal(artifacts, null);
    if (_equals) {
      return;
    }
    EList<org.sqlproc.model.processorModel.Package> _packages = artifacts.getPackages();
    for (final org.sqlproc.model.processorModel.Package pkg : _packages) {
      boolean _notEquals = (!Objects.equal(pkg, null));
      if (_notEquals) {
        EList<AbstractEntity> _elements = pkg.getElements();
        for (final AbstractEntity entity : _elements) {
          boolean _and = false;
          boolean _notEquals_1 = (!Objects.equal(entity, null));
          if (!_notEquals_1) {
            _and = false;
          } else {
            _and = (entity instanceof AnnotatedEntity);
          }
          if (_and) {
            final AnnotatedEntity aentity = ((AnnotatedEntity) entity);
            boolean _and_1 = false;
            Entity _entity = aentity.getEntity();
            boolean _notEquals_2 = (!Objects.equal(_entity, null));
            if (!_notEquals_2) {
              _and_1 = false;
            } else {
              Entity _entity_1 = aentity.getEntity();
              _and_1 = (_entity_1 instanceof DaoEntity);
            }
            if (_and_1) {
              Entity _entity_2 = aentity.getEntity();
              final DaoEntity pentity = ((DaoEntity) _entity_2);
              boolean _notEquals_3 = (!Objects.equal(pentity, daoEntity));
              if (_notEquals_3) {
                String _name = daoEntity.getName();
                String _name_1 = pentity.getName();
                boolean _equals_1 = _name.equals(_name_1);
                if (_equals_1) {
                  String _name_2 = daoEntity.getName();
                  String _plus = ("Duplicate name : " + _name_2);
                  this.error(_plus, ProcessorModelPackage.Literals.ENTITY__NAME);
                  return;
                }
              }
            }
          }
        }
      }
    }
  }
  
  @Check
  public void checkUniquePojoAttribute(final PojoAttribute pojoProperty) {
    boolean _skipVerification = CommonUtils.skipVerification(pojoProperty, this.modelProperty);
    if (_skipVerification) {
      return;
    }
    final Entity entity = EcoreUtil2.<Entity>getContainerOfType(pojoProperty, Entity.class);
    boolean _notEquals = (!Objects.equal(entity, null));
    if (_notEquals) {
      if ((entity instanceof PojoEntity)) {
        final PojoEntity pentity = ((PojoEntity) entity);
        EList<PojoAttribute> _attributes = pentity.getAttributes();
        for (final PojoAttribute property : _attributes) {
          boolean _and = false;
          boolean _notEquals_1 = (!Objects.equal(property, null));
          if (!_notEquals_1) {
            _and = false;
          } else {
            _and = (property != pojoProperty);
          }
          if (_and) {
            String _name = pojoProperty.getName();
            String _name_1 = property.getName();
            boolean _equals = _name.equals(_name_1);
            if (_equals) {
              String _name_2 = pojoProperty.getName();
              String _plus = ("Duplicate name : " + _name_2);
              this.error(_plus, ProcessorModelPackage.Literals.POJO_ATTRIBUTE__NAME);
              return;
            }
          }
        }
      } else {
        if ((entity instanceof DaoEntity)) {
          final DaoEntity pentity_1 = ((DaoEntity) entity);
          EList<PojoAttribute> _attributes_1 = pentity_1.getAttributes();
          for (final PojoAttribute property_1 : _attributes_1) {
            boolean _and_1 = false;
            boolean _notEquals_2 = (!Objects.equal(property_1, null));
            if (!_notEquals_2) {
              _and_1 = false;
            } else {
              _and_1 = (property_1 != pojoProperty);
            }
            if (_and_1) {
              String _name_3 = pojoProperty.getName();
              String _name_4 = property_1.getName();
              boolean _equals_1 = _name_3.equals(_name_4);
              if (_equals_1) {
                String _name_5 = pojoProperty.getName();
                String _plus_1 = ("Duplicate name : " + _name_5);
                this.error(_plus_1, ProcessorModelPackage.Literals.POJO_ATTRIBUTE__NAME);
                return;
              }
            }
          }
        }
      }
    }
  }
  
  public boolean isResolveDb(final EObject model) {
    return this.dbResolver.isResolveDb(model);
  }
  
  public Artifacts getArtifacts(final EObject model) {
    final EObject root = EcoreUtil.getRootContainer(model);
    if ((!(root instanceof Artifacts))) {
      return null;
    }
    return ((Artifacts) root);
  }
}
