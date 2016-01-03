/**
 */
package org.sqlproc.dsl.processorDsl.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.sqlproc.dsl.processorDsl.AbstractPojoEntity;
import org.sqlproc.dsl.processorDsl.AnnotatedEntity;
import org.sqlproc.dsl.processorDsl.Annotation;
import org.sqlproc.dsl.processorDsl.AnnotationDirective;
import org.sqlproc.dsl.processorDsl.AnnotationDirectiveAttribute;
import org.sqlproc.dsl.processorDsl.AnnotationDirectiveConflict;
import org.sqlproc.dsl.processorDsl.AnnotationDirectiveConstructor;
import org.sqlproc.dsl.processorDsl.AnnotationDirectiveGetter;
import org.sqlproc.dsl.processorDsl.AnnotationDirectiveSetter;
import org.sqlproc.dsl.processorDsl.AnnotationDirectiveStandard;
import org.sqlproc.dsl.processorDsl.AnnotationDirectiveStatic;
import org.sqlproc.dsl.processorDsl.AnnotationProperty;
import org.sqlproc.dsl.processorDsl.Artifacts;
import org.sqlproc.dsl.processorDsl.Column;
import org.sqlproc.dsl.processorDsl.ColumnAssignement;
import org.sqlproc.dsl.processorDsl.ColumnTypeAssignement;
import org.sqlproc.dsl.processorDsl.Constant;
import org.sqlproc.dsl.processorDsl.ConstantOperator;
import org.sqlproc.dsl.processorDsl.DaoDirective;
import org.sqlproc.dsl.processorDsl.DaoDirectiveCrud;
import org.sqlproc.dsl.processorDsl.DaoDirectiveDiscriminator;
import org.sqlproc.dsl.processorDsl.DaoDirectiveParameters;
import org.sqlproc.dsl.processorDsl.DaoDirectiveQuery;
import org.sqlproc.dsl.processorDsl.DaoDirectiveSerializable;
import org.sqlproc.dsl.processorDsl.DaogenProperty;
import org.sqlproc.dsl.processorDsl.DatabaseCatalogAssignement;
import org.sqlproc.dsl.processorDsl.DatabaseColumn;
import org.sqlproc.dsl.processorDsl.DatabaseMetaInfoAssignement;
import org.sqlproc.dsl.processorDsl.DatabaseProperty;
import org.sqlproc.dsl.processorDsl.DatabaseSchemaAssignement;
import org.sqlproc.dsl.processorDsl.DatabaseTable;
import org.sqlproc.dsl.processorDsl.DatabaseTypeAssignement;
import org.sqlproc.dsl.processorDsl.DebugLevelAssignement;
import org.sqlproc.dsl.processorDsl.DescendantAssignment;
import org.sqlproc.dsl.processorDsl.DirectiveProperties;
import org.sqlproc.dsl.processorDsl.DriverMetaInfoAssignement;
import org.sqlproc.dsl.processorDsl.DriverMethodOutputAssignement;
import org.sqlproc.dsl.processorDsl.Entity;
import org.sqlproc.dsl.processorDsl.EnumEntity;
import org.sqlproc.dsl.processorDsl.EnumEntityModifier1;
import org.sqlproc.dsl.processorDsl.EnumEntityModifier2;
import org.sqlproc.dsl.processorDsl.EnumProperty;
import org.sqlproc.dsl.processorDsl.EnumPropertyDirective;
import org.sqlproc.dsl.processorDsl.EnumPropertyDirectiveValues;
import org.sqlproc.dsl.processorDsl.EnumPropertyValue;
import org.sqlproc.dsl.processorDsl.ExportAssignement;
import org.sqlproc.dsl.processorDsl.ExtendedColumn;
import org.sqlproc.dsl.processorDsl.ExtendedColumnName;
import org.sqlproc.dsl.processorDsl.ExtendedMappingItem;
import org.sqlproc.dsl.processorDsl.Extends;
import org.sqlproc.dsl.processorDsl.ExtendsAssignement;
import org.sqlproc.dsl.processorDsl.ExtendsAssignementGenerics;
import org.sqlproc.dsl.processorDsl.FunProcDirective;
import org.sqlproc.dsl.processorDsl.FunProcType;
import org.sqlproc.dsl.processorDsl.FunctionCall;
import org.sqlproc.dsl.processorDsl.FunctionCallQuery;
import org.sqlproc.dsl.processorDsl.FunctionDefinition;
import org.sqlproc.dsl.processorDsl.FunctionPojoAssignement;
import org.sqlproc.dsl.processorDsl.FunctionQuery;
import org.sqlproc.dsl.processorDsl.Identifier;
import org.sqlproc.dsl.processorDsl.IdentifierOperator;
import org.sqlproc.dsl.processorDsl.IfMetaSql;
import org.sqlproc.dsl.processorDsl.IfSql;
import org.sqlproc.dsl.processorDsl.IfSqlBool;
import org.sqlproc.dsl.processorDsl.IfSqlCond;
import org.sqlproc.dsl.processorDsl.IfSqlFragment;
import org.sqlproc.dsl.processorDsl.Implements;
import org.sqlproc.dsl.processorDsl.ImplementsAssignement;
import org.sqlproc.dsl.processorDsl.ImplementsAssignementGenerics;
import org.sqlproc.dsl.processorDsl.ImplementsExtendsDirective;
import org.sqlproc.dsl.processorDsl.ImplementsExtendsDirectiveGenerics;
import org.sqlproc.dsl.processorDsl.Import;
import org.sqlproc.dsl.processorDsl.ImportAssignement;
import org.sqlproc.dsl.processorDsl.InheritanceAssignement;
import org.sqlproc.dsl.processorDsl.JoinTableAssignement;
import org.sqlproc.dsl.processorDsl.ManyToManyAssignement;
import org.sqlproc.dsl.processorDsl.Mapping;
import org.sqlproc.dsl.processorDsl.MappingColumn;
import org.sqlproc.dsl.processorDsl.MappingColumnName;
import org.sqlproc.dsl.processorDsl.MappingItem;
import org.sqlproc.dsl.processorDsl.MappingRule;
import org.sqlproc.dsl.processorDsl.MetaSql;
import org.sqlproc.dsl.processorDsl.MetaStatement;
import org.sqlproc.dsl.processorDsl.MetaTypeAssignement;
import org.sqlproc.dsl.processorDsl.MetagenProperty;
import org.sqlproc.dsl.processorDsl.OptionalFeature;
import org.sqlproc.dsl.processorDsl.OrdSql;
import org.sqlproc.dsl.processorDsl.OrdSql2;
import org.sqlproc.dsl.processorDsl.PackageDirective;
import org.sqlproc.dsl.processorDsl.PackageDirectiveImplementation;
import org.sqlproc.dsl.processorDsl.PackageDirectiveSuffix;
import org.sqlproc.dsl.processorDsl.PojoAnnotatedProperty;
import org.sqlproc.dsl.processorDsl.PojoDao;
import org.sqlproc.dsl.processorDsl.PojoDaoModifier;
import org.sqlproc.dsl.processorDsl.PojoDefinition;
import org.sqlproc.dsl.processorDsl.PojoDirective;
import org.sqlproc.dsl.processorDsl.PojoDirectiveDiscriminator;
import org.sqlproc.dsl.processorDsl.PojoDirectiveEquals;
import org.sqlproc.dsl.processorDsl.PojoDirectiveHashCode;
import org.sqlproc.dsl.processorDsl.PojoDirectiveIndex;
import org.sqlproc.dsl.processorDsl.PojoDirectiveOperators;
import org.sqlproc.dsl.processorDsl.PojoDirectiveSerializable;
import org.sqlproc.dsl.processorDsl.PojoDirectiveToString;
import org.sqlproc.dsl.processorDsl.PojoEntity;
import org.sqlproc.dsl.processorDsl.PojoEntityModifier1;
import org.sqlproc.dsl.processorDsl.PojoEntityModifier2;
import org.sqlproc.dsl.processorDsl.PojoProperty;
import org.sqlproc.dsl.processorDsl.PojoPropertyDirective;
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveCreateCol;
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveDiscriminator;
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveEnumDef;
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveEnumInit;
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveIndex;
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveIsDef;
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectivePrimaryKey;
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveRequired;
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveToInit;
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveUpdateCol;
import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveVersion;
import org.sqlproc.dsl.processorDsl.PojoType;
import org.sqlproc.dsl.processorDsl.PojogenProperty;
import org.sqlproc.dsl.processorDsl.ProcedureCallQuery;
import org.sqlproc.dsl.processorDsl.ProcedureDefinition;
import org.sqlproc.dsl.processorDsl.ProcedurePojoAssignement;
import org.sqlproc.dsl.processorDsl.ProcedureUpdate;
import org.sqlproc.dsl.processorDsl.ProcessorDslFactory;
import org.sqlproc.dsl.processorDsl.ProcessorDslPackage;
import org.sqlproc.dsl.processorDsl.Property;
import org.sqlproc.dsl.processorDsl.ShowColumnTypeAssignement;
import org.sqlproc.dsl.processorDsl.Sql;
import org.sqlproc.dsl.processorDsl.SqlFragment;
import org.sqlproc.dsl.processorDsl.SqlTypeAssignement;
import org.sqlproc.dsl.processorDsl.TableAssignement;
import org.sqlproc.dsl.processorDsl.TableDefinition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProcessorDslFactoryImpl extends EFactoryImpl implements ProcessorDslFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ProcessorDslFactory init()
  {
    try
    {
      ProcessorDslFactory theProcessorDslFactory = (ProcessorDslFactory)EPackage.Registry.INSTANCE.getEFactory(ProcessorDslPackage.eNS_URI);
      if (theProcessorDslFactory != null)
      {
        return theProcessorDslFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ProcessorDslFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProcessorDslFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case ProcessorDslPackage.ARTIFACTS: return createArtifacts();
      case ProcessorDslPackage.DATABASE_CATALOG_ASSIGNEMENT: return createDatabaseCatalogAssignement();
      case ProcessorDslPackage.DATABASE_SCHEMA_ASSIGNEMENT: return createDatabaseSchemaAssignement();
      case ProcessorDslPackage.DATABASE_TYPE_ASSIGNEMENT: return createDatabaseTypeAssignement();
      case ProcessorDslPackage.DATABASE_META_INFO_ASSIGNEMENT: return createDatabaseMetaInfoAssignement();
      case ProcessorDslPackage.DRIVER_META_INFO_ASSIGNEMENT: return createDriverMetaInfoAssignement();
      case ProcessorDslPackage.DRIVER_METHOD_OUTPUT_ASSIGNEMENT: return createDriverMethodOutputAssignement();
      case ProcessorDslPackage.SQL_TYPE_ASSIGNEMENT: return createSqlTypeAssignement();
      case ProcessorDslPackage.COLUMN_TYPE_ASSIGNEMENT: return createColumnTypeAssignement();
      case ProcessorDslPackage.SHOW_COLUMN_TYPE_ASSIGNEMENT: return createShowColumnTypeAssignement();
      case ProcessorDslPackage.TABLE_ASSIGNEMENT: return createTableAssignement();
      case ProcessorDslPackage.JOIN_TABLE_ASSIGNEMENT: return createJoinTableAssignement();
      case ProcessorDslPackage.COLUMN_ASSIGNEMENT: return createColumnAssignement();
      case ProcessorDslPackage.IMPORT_ASSIGNEMENT: return createImportAssignement();
      case ProcessorDslPackage.EXPORT_ASSIGNEMENT: return createExportAssignement();
      case ProcessorDslPackage.INHERITANCE_ASSIGNEMENT: return createInheritanceAssignement();
      case ProcessorDslPackage.MANY_TO_MANY_ASSIGNEMENT: return createManyToManyAssignement();
      case ProcessorDslPackage.DEBUG_LEVEL_ASSIGNEMENT: return createDebugLevelAssignement();
      case ProcessorDslPackage.PROCEDURE_POJO_ASSIGNEMENT: return createProcedurePojoAssignement();
      case ProcessorDslPackage.FUNCTION_POJO_ASSIGNEMENT: return createFunctionPojoAssignement();
      case ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT: return createImplementsAssignement();
      case ProcessorDslPackage.EXTENDS_ASSIGNEMENT: return createExtendsAssignement();
      case ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS: return createImplementsAssignementGenerics();
      case ProcessorDslPackage.EXTENDS_ASSIGNEMENT_GENERICS: return createExtendsAssignementGenerics();
      case ProcessorDslPackage.PROPERTY: return createProperty();
      case ProcessorDslPackage.DATABASE_PROPERTY: return createDatabaseProperty();
      case ProcessorDslPackage.POJOGEN_PROPERTY: return createPojogenProperty();
      case ProcessorDslPackage.META_TYPE_ASSIGNEMENT: return createMetaTypeAssignement();
      case ProcessorDslPackage.METAGEN_PROPERTY: return createMetagenProperty();
      case ProcessorDslPackage.DAOGEN_PROPERTY: return createDaogenProperty();
      case ProcessorDslPackage.POJO_DEFINITION: return createPojoDefinition();
      case ProcessorDslPackage.TABLE_DEFINITION: return createTableDefinition();
      case ProcessorDslPackage.PROCEDURE_DEFINITION: return createProcedureDefinition();
      case ProcessorDslPackage.FUNCTION_DEFINITION: return createFunctionDefinition();
      case ProcessorDslPackage.META_STATEMENT: return createMetaStatement();
      case ProcessorDslPackage.SQL: return createSql();
      case ProcessorDslPackage.SQL_FRAGMENT: return createSqlFragment();
      case ProcessorDslPackage.META_SQL: return createMetaSql();
      case ProcessorDslPackage.IF_SQL: return createIfSql();
      case ProcessorDslPackage.IF_SQL_FRAGMENT: return createIfSqlFragment();
      case ProcessorDslPackage.IF_META_SQL: return createIfMetaSql();
      case ProcessorDslPackage.IF_SQL_COND: return createIfSqlCond();
      case ProcessorDslPackage.IF_SQL_BOOL: return createIfSqlBool();
      case ProcessorDslPackage.ORD_SQL: return createOrdSql();
      case ProcessorDslPackage.ORD_SQL2: return createOrdSql2();
      case ProcessorDslPackage.COLUMN: return createColumn();
      case ProcessorDslPackage.EXTENDED_COLUMN: return createExtendedColumn();
      case ProcessorDslPackage.EXTENDED_COLUMN_NAME: return createExtendedColumnName();
      case ProcessorDslPackage.CONSTANT: return createConstant();
      case ProcessorDslPackage.IDENTIFIER: return createIdentifier();
      case ProcessorDslPackage.CONSTANT_OPERATOR: return createConstantOperator();
      case ProcessorDslPackage.IDENTIFIER_OPERATOR: return createIdentifierOperator();
      case ProcessorDslPackage.DATABASE_COLUMN: return createDatabaseColumn();
      case ProcessorDslPackage.DATABASE_TABLE: return createDatabaseTable();
      case ProcessorDslPackage.MAPPING_RULE: return createMappingRule();
      case ProcessorDslPackage.MAPPING: return createMapping();
      case ProcessorDslPackage.MAPPING_ITEM: return createMappingItem();
      case ProcessorDslPackage.MAPPING_COLUMN: return createMappingColumn();
      case ProcessorDslPackage.EXTENDED_MAPPING_ITEM: return createExtendedMappingItem();
      case ProcessorDslPackage.MAPPING_COLUMN_NAME: return createMappingColumnName();
      case ProcessorDslPackage.OPTIONAL_FEATURE: return createOptionalFeature();
      case ProcessorDslPackage.POJO_TYPE: return createPojoType();
      case ProcessorDslPackage.PACKAGE_DIRECTIVE: return createPackageDirective();
      case ProcessorDslPackage.PACKAGE: return createPackage();
      case ProcessorDslPackage.ANNOTATION_DIRECTIVE: return createAnnotationDirective();
      case ProcessorDslPackage.ANNOTATION: return createAnnotation();
      case ProcessorDslPackage.ANNOTATION_PROPERTY: return createAnnotationProperty();
      case ProcessorDslPackage.ENTITY: return createEntity();
      case ProcessorDslPackage.ANNOTATED_ENTITY: return createAnnotatedEntity();
      case ProcessorDslPackage.ABSTRACT_POJO_ENTITY: return createAbstractPojoEntity();
      case ProcessorDslPackage.IMPORT: return createImport();
      case ProcessorDslPackage.IMPLEMENTS_EXTENDS_DIRECTIVE: return createImplementsExtendsDirective();
      case ProcessorDslPackage.IMPLEMENTS: return createImplements();
      case ProcessorDslPackage.EXTENDS: return createExtends();
      case ProcessorDslPackage.POJO_ENTITY_MODIFIER1: return createPojoEntityModifier1();
      case ProcessorDslPackage.DIRECTIVE_PROPERTIES: return createDirectiveProperties();
      case ProcessorDslPackage.POJO_DIRECTIVE: return createPojoDirective();
      case ProcessorDslPackage.POJO_ENTITY_MODIFIER2: return createPojoEntityModifier2();
      case ProcessorDslPackage.POJO_ENTITY: return createPojoEntity();
      case ProcessorDslPackage.POJO_ANNOTATED_PROPERTY: return createPojoAnnotatedProperty();
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE: return createPojoPropertyDirective();
      case ProcessorDslPackage.POJO_PROPERTY: return createPojoProperty();
      case ProcessorDslPackage.ENUM_ENTITY_MODIFIER1: return createEnumEntityModifier1();
      case ProcessorDslPackage.ENUM_ENTITY_MODIFIER2: return createEnumEntityModifier2();
      case ProcessorDslPackage.ENUM_ENTITY: return createEnumEntity();
      case ProcessorDslPackage.ENUM_PROPERTY_VALUE: return createEnumPropertyValue();
      case ProcessorDslPackage.ENUM_PROPERTY_DIRECTIVE: return createEnumPropertyDirective();
      case ProcessorDslPackage.ENUM_PROPERTY: return createEnumProperty();
      case ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS: return createDaoDirectiveParameters();
      case ProcessorDslPackage.DESCENDANT_ASSIGNMENT: return createDescendantAssignment();
      case ProcessorDslPackage.FUN_PROC_TYPE: return createFunProcType();
      case ProcessorDslPackage.DAO_DIRECTIVE: return createDaoDirective();
      case ProcessorDslPackage.POJO_DAO_MODIFIER: return createPojoDaoModifier();
      case ProcessorDslPackage.POJO_DAO: return createPojoDao();
      case ProcessorDslPackage.PACKAGE_DIRECTIVE_SUFFIX: return createPackageDirectiveSuffix();
      case ProcessorDslPackage.PACKAGE_DIRECTIVE_IMPLEMENTATION: return createPackageDirectiveImplementation();
      case ProcessorDslPackage.ANNOTATION_DIRECTIVE_CONFLICT: return createAnnotationDirectiveConflict();
      case ProcessorDslPackage.ANNOTATION_DIRECTIVE_STATIC: return createAnnotationDirectiveStatic();
      case ProcessorDslPackage.ANNOTATION_DIRECTIVE_CONSTRUCTOR: return createAnnotationDirectiveConstructor();
      case ProcessorDslPackage.ANNOTATION_DIRECTIVE_STANDARD: return createAnnotationDirectiveStandard();
      case ProcessorDslPackage.ANNOTATION_DIRECTIVE_SETTER: return createAnnotationDirectiveSetter();
      case ProcessorDslPackage.ANNOTATION_DIRECTIVE_GETTER: return createAnnotationDirectiveGetter();
      case ProcessorDslPackage.ANNOTATION_DIRECTIVE_ATTRIBUTE: return createAnnotationDirectiveAttribute();
      case ProcessorDslPackage.IMPLEMENTS_EXTENDS_DIRECTIVE_GENERICS: return createImplementsExtendsDirectiveGenerics();
      case ProcessorDslPackage.POJO_DIRECTIVE_TO_STRING: return createPojoDirectiveToString();
      case ProcessorDslPackage.POJO_DIRECTIVE_INDEX: return createPojoDirectiveIndex();
      case ProcessorDslPackage.POJO_DIRECTIVE_OPERATORS: return createPojoDirectiveOperators();
      case ProcessorDslPackage.POJO_DIRECTIVE_SERIALIZABLE: return createPojoDirectiveSerializable();
      case ProcessorDslPackage.POJO_DIRECTIVE_DISCRIMINATOR: return createPojoDirectiveDiscriminator();
      case ProcessorDslPackage.POJO_DIRECTIVE_EQUALS: return createPojoDirectiveEquals();
      case ProcessorDslPackage.POJO_DIRECTIVE_HASH_CODE: return createPojoDirectiveHashCode();
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_REQUIRED: return createPojoPropertyDirectiveRequired();
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_PRIMARY_KEY: return createPojoPropertyDirectivePrimaryKey();
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_DISCRIMINATOR: return createPojoPropertyDirectiveDiscriminator();
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_INDEX: return createPojoPropertyDirectiveIndex();
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_VERSION: return createPojoPropertyDirectiveVersion();
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_UPDATE_COL: return createPojoPropertyDirectiveUpdateCol();
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_CREATE_COL: return createPojoPropertyDirectiveCreateCol();
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_TO_INIT: return createPojoPropertyDirectiveToInit();
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_ENUM_INIT: return createPojoPropertyDirectiveEnumInit();
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_IS_DEF: return createPojoPropertyDirectiveIsDef();
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_ENUM_DEF: return createPojoPropertyDirectiveEnumDef();
      case ProcessorDslPackage.ENUM_PROPERTY_DIRECTIVE_VALUES: return createEnumPropertyDirectiveValues();
      case ProcessorDslPackage.FUNCTION_CALL_QUERY: return createFunctionCallQuery();
      case ProcessorDslPackage.PROCEDURE_CALL_QUERY: return createProcedureCallQuery();
      case ProcessorDslPackage.FUNCTION_CALL: return createFunctionCall();
      case ProcessorDslPackage.PROCEDURE_UPDATE: return createProcedureUpdate();
      case ProcessorDslPackage.FUNCTION_QUERY: return createFunctionQuery();
      case ProcessorDslPackage.DAO_DIRECTIVE_SERIALIZABLE: return createDaoDirectiveSerializable();
      case ProcessorDslPackage.DAO_DIRECTIVE_DISCRIMINATOR: return createDaoDirectiveDiscriminator();
      case ProcessorDslPackage.DAO_DIRECTIVE_CRUD: return createDaoDirectiveCrud();
      case ProcessorDslPackage.DAO_DIRECTIVE_QUERY: return createDaoDirectiveQuery();
      case ProcessorDslPackage.FUN_PROC_DIRECTIVE: return createFunProcDirective();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Artifacts createArtifacts()
  {
    ArtifactsImpl artifacts = new ArtifactsImpl();
    return artifacts;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DatabaseCatalogAssignement createDatabaseCatalogAssignement()
  {
    DatabaseCatalogAssignementImpl databaseCatalogAssignement = new DatabaseCatalogAssignementImpl();
    return databaseCatalogAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DatabaseSchemaAssignement createDatabaseSchemaAssignement()
  {
    DatabaseSchemaAssignementImpl databaseSchemaAssignement = new DatabaseSchemaAssignementImpl();
    return databaseSchemaAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DatabaseTypeAssignement createDatabaseTypeAssignement()
  {
    DatabaseTypeAssignementImpl databaseTypeAssignement = new DatabaseTypeAssignementImpl();
    return databaseTypeAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DatabaseMetaInfoAssignement createDatabaseMetaInfoAssignement()
  {
    DatabaseMetaInfoAssignementImpl databaseMetaInfoAssignement = new DatabaseMetaInfoAssignementImpl();
    return databaseMetaInfoAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DriverMetaInfoAssignement createDriverMetaInfoAssignement()
  {
    DriverMetaInfoAssignementImpl driverMetaInfoAssignement = new DriverMetaInfoAssignementImpl();
    return driverMetaInfoAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DriverMethodOutputAssignement createDriverMethodOutputAssignement()
  {
    DriverMethodOutputAssignementImpl driverMethodOutputAssignement = new DriverMethodOutputAssignementImpl();
    return driverMethodOutputAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SqlTypeAssignement createSqlTypeAssignement()
  {
    SqlTypeAssignementImpl sqlTypeAssignement = new SqlTypeAssignementImpl();
    return sqlTypeAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ColumnTypeAssignement createColumnTypeAssignement()
  {
    ColumnTypeAssignementImpl columnTypeAssignement = new ColumnTypeAssignementImpl();
    return columnTypeAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ShowColumnTypeAssignement createShowColumnTypeAssignement()
  {
    ShowColumnTypeAssignementImpl showColumnTypeAssignement = new ShowColumnTypeAssignementImpl();
    return showColumnTypeAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TableAssignement createTableAssignement()
  {
    TableAssignementImpl tableAssignement = new TableAssignementImpl();
    return tableAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JoinTableAssignement createJoinTableAssignement()
  {
    JoinTableAssignementImpl joinTableAssignement = new JoinTableAssignementImpl();
    return joinTableAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ColumnAssignement createColumnAssignement()
  {
    ColumnAssignementImpl columnAssignement = new ColumnAssignementImpl();
    return columnAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ImportAssignement createImportAssignement()
  {
    ImportAssignementImpl importAssignement = new ImportAssignementImpl();
    return importAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExportAssignement createExportAssignement()
  {
    ExportAssignementImpl exportAssignement = new ExportAssignementImpl();
    return exportAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InheritanceAssignement createInheritanceAssignement()
  {
    InheritanceAssignementImpl inheritanceAssignement = new InheritanceAssignementImpl();
    return inheritanceAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ManyToManyAssignement createManyToManyAssignement()
  {
    ManyToManyAssignementImpl manyToManyAssignement = new ManyToManyAssignementImpl();
    return manyToManyAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DebugLevelAssignement createDebugLevelAssignement()
  {
    DebugLevelAssignementImpl debugLevelAssignement = new DebugLevelAssignementImpl();
    return debugLevelAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProcedurePojoAssignement createProcedurePojoAssignement()
  {
    ProcedurePojoAssignementImpl procedurePojoAssignement = new ProcedurePojoAssignementImpl();
    return procedurePojoAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionPojoAssignement createFunctionPojoAssignement()
  {
    FunctionPojoAssignementImpl functionPojoAssignement = new FunctionPojoAssignementImpl();
    return functionPojoAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ImplementsAssignement createImplementsAssignement()
  {
    ImplementsAssignementImpl implementsAssignement = new ImplementsAssignementImpl();
    return implementsAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExtendsAssignement createExtendsAssignement()
  {
    ExtendsAssignementImpl extendsAssignement = new ExtendsAssignementImpl();
    return extendsAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ImplementsAssignementGenerics createImplementsAssignementGenerics()
  {
    ImplementsAssignementGenericsImpl implementsAssignementGenerics = new ImplementsAssignementGenericsImpl();
    return implementsAssignementGenerics;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExtendsAssignementGenerics createExtendsAssignementGenerics()
  {
    ExtendsAssignementGenericsImpl extendsAssignementGenerics = new ExtendsAssignementGenericsImpl();
    return extendsAssignementGenerics;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Property createProperty()
  {
    PropertyImpl property = new PropertyImpl();
    return property;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DatabaseProperty createDatabaseProperty()
  {
    DatabasePropertyImpl databaseProperty = new DatabasePropertyImpl();
    return databaseProperty;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojogenProperty createPojogenProperty()
  {
    PojogenPropertyImpl pojogenProperty = new PojogenPropertyImpl();
    return pojogenProperty;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MetaTypeAssignement createMetaTypeAssignement()
  {
    MetaTypeAssignementImpl metaTypeAssignement = new MetaTypeAssignementImpl();
    return metaTypeAssignement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MetagenProperty createMetagenProperty()
  {
    MetagenPropertyImpl metagenProperty = new MetagenPropertyImpl();
    return metagenProperty;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DaogenProperty createDaogenProperty()
  {
    DaogenPropertyImpl daogenProperty = new DaogenPropertyImpl();
    return daogenProperty;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoDefinition createPojoDefinition()
  {
    PojoDefinitionImpl pojoDefinition = new PojoDefinitionImpl();
    return pojoDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TableDefinition createTableDefinition()
  {
    TableDefinitionImpl tableDefinition = new TableDefinitionImpl();
    return tableDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProcedureDefinition createProcedureDefinition()
  {
    ProcedureDefinitionImpl procedureDefinition = new ProcedureDefinitionImpl();
    return procedureDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionDefinition createFunctionDefinition()
  {
    FunctionDefinitionImpl functionDefinition = new FunctionDefinitionImpl();
    return functionDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MetaStatement createMetaStatement()
  {
    MetaStatementImpl metaStatement = new MetaStatementImpl();
    return metaStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Sql createSql()
  {
    SqlImpl sql = new SqlImpl();
    return sql;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SqlFragment createSqlFragment()
  {
    SqlFragmentImpl sqlFragment = new SqlFragmentImpl();
    return sqlFragment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MetaSql createMetaSql()
  {
    MetaSqlImpl metaSql = new MetaSqlImpl();
    return metaSql;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IfSql createIfSql()
  {
    IfSqlImpl ifSql = new IfSqlImpl();
    return ifSql;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IfSqlFragment createIfSqlFragment()
  {
    IfSqlFragmentImpl ifSqlFragment = new IfSqlFragmentImpl();
    return ifSqlFragment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IfMetaSql createIfMetaSql()
  {
    IfMetaSqlImpl ifMetaSql = new IfMetaSqlImpl();
    return ifMetaSql;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IfSqlCond createIfSqlCond()
  {
    IfSqlCondImpl ifSqlCond = new IfSqlCondImpl();
    return ifSqlCond;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IfSqlBool createIfSqlBool()
  {
    IfSqlBoolImpl ifSqlBool = new IfSqlBoolImpl();
    return ifSqlBool;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrdSql createOrdSql()
  {
    OrdSqlImpl ordSql = new OrdSqlImpl();
    return ordSql;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrdSql2 createOrdSql2()
  {
    OrdSql2Impl ordSql2 = new OrdSql2Impl();
    return ordSql2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Column createColumn()
  {
    ColumnImpl column = new ColumnImpl();
    return column;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExtendedColumn createExtendedColumn()
  {
    ExtendedColumnImpl extendedColumn = new ExtendedColumnImpl();
    return extendedColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExtendedColumnName createExtendedColumnName()
  {
    ExtendedColumnNameImpl extendedColumnName = new ExtendedColumnNameImpl();
    return extendedColumnName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Constant createConstant()
  {
    ConstantImpl constant = new ConstantImpl();
    return constant;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Identifier createIdentifier()
  {
    IdentifierImpl identifier = new IdentifierImpl();
    return identifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConstantOperator createConstantOperator()
  {
    ConstantOperatorImpl constantOperator = new ConstantOperatorImpl();
    return constantOperator;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IdentifierOperator createIdentifierOperator()
  {
    IdentifierOperatorImpl identifierOperator = new IdentifierOperatorImpl();
    return identifierOperator;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DatabaseColumn createDatabaseColumn()
  {
    DatabaseColumnImpl databaseColumn = new DatabaseColumnImpl();
    return databaseColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DatabaseTable createDatabaseTable()
  {
    DatabaseTableImpl databaseTable = new DatabaseTableImpl();
    return databaseTable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MappingRule createMappingRule()
  {
    MappingRuleImpl mappingRule = new MappingRuleImpl();
    return mappingRule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mapping createMapping()
  {
    MappingImpl mapping = new MappingImpl();
    return mapping;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MappingItem createMappingItem()
  {
    MappingItemImpl mappingItem = new MappingItemImpl();
    return mappingItem;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MappingColumn createMappingColumn()
  {
    MappingColumnImpl mappingColumn = new MappingColumnImpl();
    return mappingColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExtendedMappingItem createExtendedMappingItem()
  {
    ExtendedMappingItemImpl extendedMappingItem = new ExtendedMappingItemImpl();
    return extendedMappingItem;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MappingColumnName createMappingColumnName()
  {
    MappingColumnNameImpl mappingColumnName = new MappingColumnNameImpl();
    return mappingColumnName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OptionalFeature createOptionalFeature()
  {
    OptionalFeatureImpl optionalFeature = new OptionalFeatureImpl();
    return optionalFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoType createPojoType()
  {
    PojoTypeImpl pojoType = new PojoTypeImpl();
    return pojoType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PackageDirective createPackageDirective()
  {
    PackageDirectiveImpl packageDirective = new PackageDirectiveImpl();
    return packageDirective;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public org.sqlproc.dsl.processorDsl.Package createPackage()
  {
    PackageImpl package_ = new PackageImpl();
    return package_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnnotationDirective createAnnotationDirective()
  {
    AnnotationDirectiveImpl annotationDirective = new AnnotationDirectiveImpl();
    return annotationDirective;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Annotation createAnnotation()
  {
    AnnotationImpl annotation = new AnnotationImpl();
    return annotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnnotationProperty createAnnotationProperty()
  {
    AnnotationPropertyImpl annotationProperty = new AnnotationPropertyImpl();
    return annotationProperty;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Entity createEntity()
  {
    EntityImpl entity = new EntityImpl();
    return entity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnnotatedEntity createAnnotatedEntity()
  {
    AnnotatedEntityImpl annotatedEntity = new AnnotatedEntityImpl();
    return annotatedEntity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AbstractPojoEntity createAbstractPojoEntity()
  {
    AbstractPojoEntityImpl abstractPojoEntity = new AbstractPojoEntityImpl();
    return abstractPojoEntity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Import createImport()
  {
    ImportImpl import_ = new ImportImpl();
    return import_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ImplementsExtendsDirective createImplementsExtendsDirective()
  {
    ImplementsExtendsDirectiveImpl implementsExtendsDirective = new ImplementsExtendsDirectiveImpl();
    return implementsExtendsDirective;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Implements createImplements()
  {
    ImplementsImpl implements_ = new ImplementsImpl();
    return implements_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Extends createExtends()
  {
    ExtendsImpl extends_ = new ExtendsImpl();
    return extends_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoEntityModifier1 createPojoEntityModifier1()
  {
    PojoEntityModifier1Impl pojoEntityModifier1 = new PojoEntityModifier1Impl();
    return pojoEntityModifier1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DirectiveProperties createDirectiveProperties()
  {
    DirectivePropertiesImpl directiveProperties = new DirectivePropertiesImpl();
    return directiveProperties;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoDirective createPojoDirective()
  {
    PojoDirectiveImpl pojoDirective = new PojoDirectiveImpl();
    return pojoDirective;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoEntityModifier2 createPojoEntityModifier2()
  {
    PojoEntityModifier2Impl pojoEntityModifier2 = new PojoEntityModifier2Impl();
    return pojoEntityModifier2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoEntity createPojoEntity()
  {
    PojoEntityImpl pojoEntity = new PojoEntityImpl();
    return pojoEntity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoAnnotatedProperty createPojoAnnotatedProperty()
  {
    PojoAnnotatedPropertyImpl pojoAnnotatedProperty = new PojoAnnotatedPropertyImpl();
    return pojoAnnotatedProperty;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoPropertyDirective createPojoPropertyDirective()
  {
    PojoPropertyDirectiveImpl pojoPropertyDirective = new PojoPropertyDirectiveImpl();
    return pojoPropertyDirective;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoProperty createPojoProperty()
  {
    PojoPropertyImpl pojoProperty = new PojoPropertyImpl();
    return pojoProperty;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EnumEntityModifier1 createEnumEntityModifier1()
  {
    EnumEntityModifier1Impl enumEntityModifier1 = new EnumEntityModifier1Impl();
    return enumEntityModifier1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EnumEntityModifier2 createEnumEntityModifier2()
  {
    EnumEntityModifier2Impl enumEntityModifier2 = new EnumEntityModifier2Impl();
    return enumEntityModifier2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EnumEntity createEnumEntity()
  {
    EnumEntityImpl enumEntity = new EnumEntityImpl();
    return enumEntity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EnumPropertyValue createEnumPropertyValue()
  {
    EnumPropertyValueImpl enumPropertyValue = new EnumPropertyValueImpl();
    return enumPropertyValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EnumPropertyDirective createEnumPropertyDirective()
  {
    EnumPropertyDirectiveImpl enumPropertyDirective = new EnumPropertyDirectiveImpl();
    return enumPropertyDirective;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EnumProperty createEnumProperty()
  {
    EnumPropertyImpl enumProperty = new EnumPropertyImpl();
    return enumProperty;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DaoDirectiveParameters createDaoDirectiveParameters()
  {
    DaoDirectiveParametersImpl daoDirectiveParameters = new DaoDirectiveParametersImpl();
    return daoDirectiveParameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DescendantAssignment createDescendantAssignment()
  {
    DescendantAssignmentImpl descendantAssignment = new DescendantAssignmentImpl();
    return descendantAssignment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunProcType createFunProcType()
  {
    FunProcTypeImpl funProcType = new FunProcTypeImpl();
    return funProcType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DaoDirective createDaoDirective()
  {
    DaoDirectiveImpl daoDirective = new DaoDirectiveImpl();
    return daoDirective;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoDaoModifier createPojoDaoModifier()
  {
    PojoDaoModifierImpl pojoDaoModifier = new PojoDaoModifierImpl();
    return pojoDaoModifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoDao createPojoDao()
  {
    PojoDaoImpl pojoDao = new PojoDaoImpl();
    return pojoDao;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PackageDirectiveSuffix createPackageDirectiveSuffix()
  {
    PackageDirectiveSuffixImpl packageDirectiveSuffix = new PackageDirectiveSuffixImpl();
    return packageDirectiveSuffix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PackageDirectiveImplementation createPackageDirectiveImplementation()
  {
    PackageDirectiveImplementationImpl packageDirectiveImplementation = new PackageDirectiveImplementationImpl();
    return packageDirectiveImplementation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnnotationDirectiveConflict createAnnotationDirectiveConflict()
  {
    AnnotationDirectiveConflictImpl annotationDirectiveConflict = new AnnotationDirectiveConflictImpl();
    return annotationDirectiveConflict;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnnotationDirectiveStatic createAnnotationDirectiveStatic()
  {
    AnnotationDirectiveStaticImpl annotationDirectiveStatic = new AnnotationDirectiveStaticImpl();
    return annotationDirectiveStatic;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnnotationDirectiveConstructor createAnnotationDirectiveConstructor()
  {
    AnnotationDirectiveConstructorImpl annotationDirectiveConstructor = new AnnotationDirectiveConstructorImpl();
    return annotationDirectiveConstructor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnnotationDirectiveStandard createAnnotationDirectiveStandard()
  {
    AnnotationDirectiveStandardImpl annotationDirectiveStandard = new AnnotationDirectiveStandardImpl();
    return annotationDirectiveStandard;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnnotationDirectiveSetter createAnnotationDirectiveSetter()
  {
    AnnotationDirectiveSetterImpl annotationDirectiveSetter = new AnnotationDirectiveSetterImpl();
    return annotationDirectiveSetter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnnotationDirectiveGetter createAnnotationDirectiveGetter()
  {
    AnnotationDirectiveGetterImpl annotationDirectiveGetter = new AnnotationDirectiveGetterImpl();
    return annotationDirectiveGetter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnnotationDirectiveAttribute createAnnotationDirectiveAttribute()
  {
    AnnotationDirectiveAttributeImpl annotationDirectiveAttribute = new AnnotationDirectiveAttributeImpl();
    return annotationDirectiveAttribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ImplementsExtendsDirectiveGenerics createImplementsExtendsDirectiveGenerics()
  {
    ImplementsExtendsDirectiveGenericsImpl implementsExtendsDirectiveGenerics = new ImplementsExtendsDirectiveGenericsImpl();
    return implementsExtendsDirectiveGenerics;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoDirectiveToString createPojoDirectiveToString()
  {
    PojoDirectiveToStringImpl pojoDirectiveToString = new PojoDirectiveToStringImpl();
    return pojoDirectiveToString;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoDirectiveIndex createPojoDirectiveIndex()
  {
    PojoDirectiveIndexImpl pojoDirectiveIndex = new PojoDirectiveIndexImpl();
    return pojoDirectiveIndex;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoDirectiveOperators createPojoDirectiveOperators()
  {
    PojoDirectiveOperatorsImpl pojoDirectiveOperators = new PojoDirectiveOperatorsImpl();
    return pojoDirectiveOperators;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoDirectiveSerializable createPojoDirectiveSerializable()
  {
    PojoDirectiveSerializableImpl pojoDirectiveSerializable = new PojoDirectiveSerializableImpl();
    return pojoDirectiveSerializable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoDirectiveDiscriminator createPojoDirectiveDiscriminator()
  {
    PojoDirectiveDiscriminatorImpl pojoDirectiveDiscriminator = new PojoDirectiveDiscriminatorImpl();
    return pojoDirectiveDiscriminator;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoDirectiveEquals createPojoDirectiveEquals()
  {
    PojoDirectiveEqualsImpl pojoDirectiveEquals = new PojoDirectiveEqualsImpl();
    return pojoDirectiveEquals;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoDirectiveHashCode createPojoDirectiveHashCode()
  {
    PojoDirectiveHashCodeImpl pojoDirectiveHashCode = new PojoDirectiveHashCodeImpl();
    return pojoDirectiveHashCode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoPropertyDirectiveRequired createPojoPropertyDirectiveRequired()
  {
    PojoPropertyDirectiveRequiredImpl pojoPropertyDirectiveRequired = new PojoPropertyDirectiveRequiredImpl();
    return pojoPropertyDirectiveRequired;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoPropertyDirectivePrimaryKey createPojoPropertyDirectivePrimaryKey()
  {
    PojoPropertyDirectivePrimaryKeyImpl pojoPropertyDirectivePrimaryKey = new PojoPropertyDirectivePrimaryKeyImpl();
    return pojoPropertyDirectivePrimaryKey;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoPropertyDirectiveDiscriminator createPojoPropertyDirectiveDiscriminator()
  {
    PojoPropertyDirectiveDiscriminatorImpl pojoPropertyDirectiveDiscriminator = new PojoPropertyDirectiveDiscriminatorImpl();
    return pojoPropertyDirectiveDiscriminator;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoPropertyDirectiveIndex createPojoPropertyDirectiveIndex()
  {
    PojoPropertyDirectiveIndexImpl pojoPropertyDirectiveIndex = new PojoPropertyDirectiveIndexImpl();
    return pojoPropertyDirectiveIndex;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoPropertyDirectiveVersion createPojoPropertyDirectiveVersion()
  {
    PojoPropertyDirectiveVersionImpl pojoPropertyDirectiveVersion = new PojoPropertyDirectiveVersionImpl();
    return pojoPropertyDirectiveVersion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoPropertyDirectiveUpdateCol createPojoPropertyDirectiveUpdateCol()
  {
    PojoPropertyDirectiveUpdateColImpl pojoPropertyDirectiveUpdateCol = new PojoPropertyDirectiveUpdateColImpl();
    return pojoPropertyDirectiveUpdateCol;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoPropertyDirectiveCreateCol createPojoPropertyDirectiveCreateCol()
  {
    PojoPropertyDirectiveCreateColImpl pojoPropertyDirectiveCreateCol = new PojoPropertyDirectiveCreateColImpl();
    return pojoPropertyDirectiveCreateCol;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoPropertyDirectiveToInit createPojoPropertyDirectiveToInit()
  {
    PojoPropertyDirectiveToInitImpl pojoPropertyDirectiveToInit = new PojoPropertyDirectiveToInitImpl();
    return pojoPropertyDirectiveToInit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoPropertyDirectiveEnumInit createPojoPropertyDirectiveEnumInit()
  {
    PojoPropertyDirectiveEnumInitImpl pojoPropertyDirectiveEnumInit = new PojoPropertyDirectiveEnumInitImpl();
    return pojoPropertyDirectiveEnumInit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoPropertyDirectiveIsDef createPojoPropertyDirectiveIsDef()
  {
    PojoPropertyDirectiveIsDefImpl pojoPropertyDirectiveIsDef = new PojoPropertyDirectiveIsDefImpl();
    return pojoPropertyDirectiveIsDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoPropertyDirectiveEnumDef createPojoPropertyDirectiveEnumDef()
  {
    PojoPropertyDirectiveEnumDefImpl pojoPropertyDirectiveEnumDef = new PojoPropertyDirectiveEnumDefImpl();
    return pojoPropertyDirectiveEnumDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EnumPropertyDirectiveValues createEnumPropertyDirectiveValues()
  {
    EnumPropertyDirectiveValuesImpl enumPropertyDirectiveValues = new EnumPropertyDirectiveValuesImpl();
    return enumPropertyDirectiveValues;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionCallQuery createFunctionCallQuery()
  {
    FunctionCallQueryImpl functionCallQuery = new FunctionCallQueryImpl();
    return functionCallQuery;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProcedureCallQuery createProcedureCallQuery()
  {
    ProcedureCallQueryImpl procedureCallQuery = new ProcedureCallQueryImpl();
    return procedureCallQuery;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionCall createFunctionCall()
  {
    FunctionCallImpl functionCall = new FunctionCallImpl();
    return functionCall;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProcedureUpdate createProcedureUpdate()
  {
    ProcedureUpdateImpl procedureUpdate = new ProcedureUpdateImpl();
    return procedureUpdate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionQuery createFunctionQuery()
  {
    FunctionQueryImpl functionQuery = new FunctionQueryImpl();
    return functionQuery;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DaoDirectiveSerializable createDaoDirectiveSerializable()
  {
    DaoDirectiveSerializableImpl daoDirectiveSerializable = new DaoDirectiveSerializableImpl();
    return daoDirectiveSerializable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DaoDirectiveDiscriminator createDaoDirectiveDiscriminator()
  {
    DaoDirectiveDiscriminatorImpl daoDirectiveDiscriminator = new DaoDirectiveDiscriminatorImpl();
    return daoDirectiveDiscriminator;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DaoDirectiveCrud createDaoDirectiveCrud()
  {
    DaoDirectiveCrudImpl daoDirectiveCrud = new DaoDirectiveCrudImpl();
    return daoDirectiveCrud;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DaoDirectiveQuery createDaoDirectiveQuery()
  {
    DaoDirectiveQueryImpl daoDirectiveQuery = new DaoDirectiveQueryImpl();
    return daoDirectiveQuery;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunProcDirective createFunProcDirective()
  {
    FunProcDirectiveImpl funProcDirective = new FunProcDirectiveImpl();
    return funProcDirective;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProcessorDslPackage getProcessorDslPackage()
  {
    return (ProcessorDslPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ProcessorDslPackage getPackage()
  {
    return ProcessorDslPackage.eINSTANCE;
  }

} //ProcessorDslFactoryImpl
