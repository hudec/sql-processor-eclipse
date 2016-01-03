/**
 */
package org.sqlproc.dsl.processorDsl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.sqlproc.dsl.processorDsl.ProcessorDslFactory
 * @model kind="package"
 * @generated
 */
public interface ProcessorDslPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "processorDsl";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.sqlproc.org/dsl/ProcessorDsl";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "processorDsl";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ProcessorDslPackage eINSTANCE = org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl.init();

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ArtifactsImpl <em>Artifacts</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ArtifactsImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getArtifacts()
   * @generated
   */
  int ARTIFACTS = 0;

  /**
   * The feature id for the '<em><b>Properties</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS__PROPERTIES = 0;

  /**
   * The feature id for the '<em><b>Pojos</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS__POJOS = 1;

  /**
   * The feature id for the '<em><b>Tables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS__TABLES = 2;

  /**
   * The feature id for the '<em><b>Procedures</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS__PROCEDURES = 3;

  /**
   * The feature id for the '<em><b>Functions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS__FUNCTIONS = 4;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS__STATEMENTS = 5;

  /**
   * The feature id for the '<em><b>Mappings</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS__MAPPINGS = 6;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS__FEATURES = 7;

  /**
   * The feature id for the '<em><b>Packages</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS__PACKAGES = 8;

  /**
   * The number of structural features of the '<em>Artifacts</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARTIFACTS_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DatabaseCatalogAssignementImpl <em>Database Catalog Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DatabaseCatalogAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDatabaseCatalogAssignement()
   * @generated
   */
  int DATABASE_CATALOG_ASSIGNEMENT = 1;

  /**
   * The feature id for the '<em><b>Db Catalog</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_CATALOG_ASSIGNEMENT__DB_CATALOG = 0;

  /**
   * The number of structural features of the '<em>Database Catalog Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_CATALOG_ASSIGNEMENT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DatabaseSchemaAssignementImpl <em>Database Schema Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DatabaseSchemaAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDatabaseSchemaAssignement()
   * @generated
   */
  int DATABASE_SCHEMA_ASSIGNEMENT = 2;

  /**
   * The feature id for the '<em><b>Db Schema</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_SCHEMA_ASSIGNEMENT__DB_SCHEMA = 0;

  /**
   * The number of structural features of the '<em>Database Schema Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_SCHEMA_ASSIGNEMENT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DatabaseTypeAssignementImpl <em>Database Type Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DatabaseTypeAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDatabaseTypeAssignement()
   * @generated
   */
  int DATABASE_TYPE_ASSIGNEMENT = 3;

  /**
   * The feature id for the '<em><b>Db Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_TYPE_ASSIGNEMENT__DB_TYPE = 0;

  /**
   * The number of structural features of the '<em>Database Type Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_TYPE_ASSIGNEMENT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DatabaseMetaInfoAssignementImpl <em>Database Meta Info Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DatabaseMetaInfoAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDatabaseMetaInfoAssignement()
   * @generated
   */
  int DATABASE_META_INFO_ASSIGNEMENT = 4;

  /**
   * The feature id for the '<em><b>Db Meta Info</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_META_INFO_ASSIGNEMENT__DB_META_INFO = 0;

  /**
   * The number of structural features of the '<em>Database Meta Info Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_META_INFO_ASSIGNEMENT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DriverMetaInfoAssignementImpl <em>Driver Meta Info Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DriverMetaInfoAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDriverMetaInfoAssignement()
   * @generated
   */
  int DRIVER_META_INFO_ASSIGNEMENT = 5;

  /**
   * The feature id for the '<em><b>Db Driver Info</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DRIVER_META_INFO_ASSIGNEMENT__DB_DRIVER_INFO = 0;

  /**
   * The number of structural features of the '<em>Driver Meta Info Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DRIVER_META_INFO_ASSIGNEMENT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DriverMethodOutputAssignementImpl <em>Driver Method Output Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DriverMethodOutputAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDriverMethodOutputAssignement()
   * @generated
   */
  int DRIVER_METHOD_OUTPUT_ASSIGNEMENT = 6;

  /**
   * The feature id for the '<em><b>Driver Method</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DRIVER_METHOD_OUTPUT_ASSIGNEMENT__DRIVER_METHOD = 0;

  /**
   * The feature id for the '<em><b>Call Output</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DRIVER_METHOD_OUTPUT_ASSIGNEMENT__CALL_OUTPUT = 1;

  /**
   * The number of structural features of the '<em>Driver Method Output Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DRIVER_METHOD_OUTPUT_ASSIGNEMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.SqlTypeAssignementImpl <em>Sql Type Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.SqlTypeAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getSqlTypeAssignement()
   * @generated
   */
  int SQL_TYPE_ASSIGNEMENT = 7;

  /**
   * The feature id for the '<em><b>Type Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL_TYPE_ASSIGNEMENT__TYPE_NAME = 0;

  /**
   * The feature id for the '<em><b>Size</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL_TYPE_ASSIGNEMENT__SIZE = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL_TYPE_ASSIGNEMENT__TYPE = 2;

  /**
   * The number of structural features of the '<em>Sql Type Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL_TYPE_ASSIGNEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ColumnTypeAssignementImpl <em>Column Type Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ColumnTypeAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getColumnTypeAssignement()
   * @generated
   */
  int COLUMN_TYPE_ASSIGNEMENT = 8;

  /**
   * The feature id for the '<em><b>Db Column</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_TYPE_ASSIGNEMENT__DB_COLUMN = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_TYPE_ASSIGNEMENT__TYPE = 1;

  /**
   * The number of structural features of the '<em>Column Type Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_TYPE_ASSIGNEMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ShowColumnTypeAssignementImpl <em>Show Column Type Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ShowColumnTypeAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getShowColumnTypeAssignement()
   * @generated
   */
  int SHOW_COLUMN_TYPE_ASSIGNEMENT = 9;

  /**
   * The feature id for the '<em><b>Db Column</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SHOW_COLUMN_TYPE_ASSIGNEMENT__DB_COLUMN = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SHOW_COLUMN_TYPE_ASSIGNEMENT__TYPE = 1;

  /**
   * The number of structural features of the '<em>Show Column Type Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SHOW_COLUMN_TYPE_ASSIGNEMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.TableAssignementImpl <em>Table Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.TableAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getTableAssignement()
   * @generated
   */
  int TABLE_ASSIGNEMENT = 10;

  /**
   * The feature id for the '<em><b>Db Table</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TABLE_ASSIGNEMENT__DB_TABLE = 0;

  /**
   * The feature id for the '<em><b>New Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TABLE_ASSIGNEMENT__NEW_NAME = 1;

  /**
   * The number of structural features of the '<em>Table Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TABLE_ASSIGNEMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.JoinTableAssignementImpl <em>Join Table Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.JoinTableAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getJoinTableAssignement()
   * @generated
   */
  int JOIN_TABLE_ASSIGNEMENT = 11;

  /**
   * The feature id for the '<em><b>Db Table</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JOIN_TABLE_ASSIGNEMENT__DB_TABLE = 0;

  /**
   * The feature id for the '<em><b>Db Tables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JOIN_TABLE_ASSIGNEMENT__DB_TABLES = 1;

  /**
   * The number of structural features of the '<em>Join Table Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JOIN_TABLE_ASSIGNEMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ColumnAssignementImpl <em>Column Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ColumnAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getColumnAssignement()
   * @generated
   */
  int COLUMN_ASSIGNEMENT = 12;

  /**
   * The feature id for the '<em><b>Db Column</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_ASSIGNEMENT__DB_COLUMN = 0;

  /**
   * The feature id for the '<em><b>New Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_ASSIGNEMENT__NEW_NAME = 1;

  /**
   * The number of structural features of the '<em>Column Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_ASSIGNEMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ImportAssignementImpl <em>Import Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ImportAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getImportAssignement()
   * @generated
   */
  int IMPORT_ASSIGNEMENT = 13;

  /**
   * The feature id for the '<em><b>Db Column</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_ASSIGNEMENT__DB_COLUMN = 0;

  /**
   * The feature id for the '<em><b>Pk Table</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_ASSIGNEMENT__PK_TABLE = 1;

  /**
   * The feature id for the '<em><b>Pk Column</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_ASSIGNEMENT__PK_COLUMN = 2;

  /**
   * The number of structural features of the '<em>Import Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_ASSIGNEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ExportAssignementImpl <em>Export Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ExportAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getExportAssignement()
   * @generated
   */
  int EXPORT_ASSIGNEMENT = 14;

  /**
   * The feature id for the '<em><b>Db Column</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPORT_ASSIGNEMENT__DB_COLUMN = 0;

  /**
   * The feature id for the '<em><b>Fk Table</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPORT_ASSIGNEMENT__FK_TABLE = 1;

  /**
   * The feature id for the '<em><b>Fk Column</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPORT_ASSIGNEMENT__FK_COLUMN = 2;

  /**
   * The number of structural features of the '<em>Export Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPORT_ASSIGNEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.InheritanceAssignementImpl <em>Inheritance Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.InheritanceAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getInheritanceAssignement()
   * @generated
   */
  int INHERITANCE_ASSIGNEMENT = 15;

  /**
   * The feature id for the '<em><b>Discriminator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INHERITANCE_ASSIGNEMENT__DISCRIMINATOR = 0;

  /**
   * The feature id for the '<em><b>Db Table</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INHERITANCE_ASSIGNEMENT__DB_TABLE = 1;

  /**
   * The feature id for the '<em><b>Db Columns</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INHERITANCE_ASSIGNEMENT__DB_COLUMNS = 2;

  /**
   * The number of structural features of the '<em>Inheritance Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INHERITANCE_ASSIGNEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ManyToManyAssignementImpl <em>Many To Many Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ManyToManyAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getManyToManyAssignement()
   * @generated
   */
  int MANY_TO_MANY_ASSIGNEMENT = 16;

  /**
   * The feature id for the '<em><b>Pk Column</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MANY_TO_MANY_ASSIGNEMENT__PK_COLUMN = 0;

  /**
   * The feature id for the '<em><b>Pk Table</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MANY_TO_MANY_ASSIGNEMENT__PK_TABLE = 1;

  /**
   * The feature id for the '<em><b>Fk Column</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MANY_TO_MANY_ASSIGNEMENT__FK_COLUMN = 2;

  /**
   * The number of structural features of the '<em>Many To Many Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MANY_TO_MANY_ASSIGNEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DebugLevelAssignementImpl <em>Debug Level Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DebugLevelAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDebugLevelAssignement()
   * @generated
   */
  int DEBUG_LEVEL_ASSIGNEMENT = 17;

  /**
   * The feature id for the '<em><b>Debug</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEBUG_LEVEL_ASSIGNEMENT__DEBUG = 0;

  /**
   * The feature id for the '<em><b>Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEBUG_LEVEL_ASSIGNEMENT__SCOPE = 1;

  /**
   * The number of structural features of the '<em>Debug Level Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DEBUG_LEVEL_ASSIGNEMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ProcedurePojoAssignementImpl <em>Procedure Pojo Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ProcedurePojoAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getProcedurePojoAssignement()
   * @generated
   */
  int PROCEDURE_POJO_ASSIGNEMENT = 18;

  /**
   * The feature id for the '<em><b>Db Procedure</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROCEDURE_POJO_ASSIGNEMENT__DB_PROCEDURE = 0;

  /**
   * The feature id for the '<em><b>Pojo</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROCEDURE_POJO_ASSIGNEMENT__POJO = 1;

  /**
   * The number of structural features of the '<em>Procedure Pojo Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROCEDURE_POJO_ASSIGNEMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.FunctionPojoAssignementImpl <em>Function Pojo Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.FunctionPojoAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getFunctionPojoAssignement()
   * @generated
   */
  int FUNCTION_POJO_ASSIGNEMENT = 19;

  /**
   * The feature id for the '<em><b>Db Function</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_POJO_ASSIGNEMENT__DB_FUNCTION = 0;

  /**
   * The feature id for the '<em><b>Pojo</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_POJO_ASSIGNEMENT__POJO = 1;

  /**
   * The number of structural features of the '<em>Function Pojo Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_POJO_ASSIGNEMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ImplementsAssignementImpl <em>Implements Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ImplementsAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getImplementsAssignement()
   * @generated
   */
  int IMPLEMENTS_ASSIGNEMENT = 20;

  /**
   * The feature id for the '<em><b>To Implement</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS_ASSIGNEMENT__TO_IMPLEMENT = 0;

  /**
   * The feature id for the '<em><b>Db Tables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS_ASSIGNEMENT__DB_TABLES = 1;

  /**
   * The feature id for the '<em><b>Db Not Tables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS_ASSIGNEMENT__DB_NOT_TABLES = 2;

  /**
   * The number of structural features of the '<em>Implements Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS_ASSIGNEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ExtendsAssignementImpl <em>Extends Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ExtendsAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getExtendsAssignement()
   * @generated
   */
  int EXTENDS_ASSIGNEMENT = 21;

  /**
   * The feature id for the '<em><b>To Extends</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_ASSIGNEMENT__TO_EXTENDS = 0;

  /**
   * The feature id for the '<em><b>Db Tables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_ASSIGNEMENT__DB_TABLES = 1;

  /**
   * The feature id for the '<em><b>Db Not Tables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_ASSIGNEMENT__DB_NOT_TABLES = 2;

  /**
   * The number of structural features of the '<em>Extends Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_ASSIGNEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ImplementsAssignementGenericsImpl <em>Implements Assignement Generics</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ImplementsAssignementGenericsImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getImplementsAssignementGenerics()
   * @generated
   */
  int IMPLEMENTS_ASSIGNEMENT_GENERICS = 22;

  /**
   * The feature id for the '<em><b>To Implement</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS_ASSIGNEMENT_GENERICS__TO_IMPLEMENT = 0;

  /**
   * The feature id for the '<em><b>Db Tables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_TABLES = 1;

  /**
   * The feature id for the '<em><b>Db Not Tables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_NOT_TABLES = 2;

  /**
   * The number of structural features of the '<em>Implements Assignement Generics</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS_ASSIGNEMENT_GENERICS_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ExtendsAssignementGenericsImpl <em>Extends Assignement Generics</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ExtendsAssignementGenericsImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getExtendsAssignementGenerics()
   * @generated
   */
  int EXTENDS_ASSIGNEMENT_GENERICS = 23;

  /**
   * The feature id for the '<em><b>To Extends</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_ASSIGNEMENT_GENERICS__TO_EXTENDS = 0;

  /**
   * The feature id for the '<em><b>Db Tables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_ASSIGNEMENT_GENERICS__DB_TABLES = 1;

  /**
   * The feature id for the '<em><b>Db Not Tables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_ASSIGNEMENT_GENERICS__DB_NOT_TABLES = 2;

  /**
   * The number of structural features of the '<em>Extends Assignement Generics</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_ASSIGNEMENT_GENERICS_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PropertyImpl <em>Property</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PropertyImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getProperty()
   * @generated
   */
  int PROPERTY = 24;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY__NAME = 0;

  /**
   * The feature id for the '<em><b>Database</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY__DATABASE = 1;

  /**
   * The feature id for the '<em><b>Pojogen</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY__POJOGEN = 2;

  /**
   * The feature id for the '<em><b>Metagen</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY__METAGEN = 3;

  /**
   * The feature id for the '<em><b>Daogen</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY__DAOGEN = 4;

  /**
   * The feature id for the '<em><b>Replace Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY__REPLACE_ID = 5;

  /**
   * The feature id for the '<em><b>Regex</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY__REGEX = 6;

  /**
   * The feature id for the '<em><b>Replacement</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY__REPLACEMENT = 7;

  /**
   * The number of structural features of the '<em>Property</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_FEATURE_COUNT = 8;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DatabasePropertyImpl <em>Database Property</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DatabasePropertyImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDatabaseProperty()
   * @generated
   */
  int DATABASE_PROPERTY = 25;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__NAME = 0;

  /**
   * The feature id for the '<em><b>Db Url</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__DB_URL = 1;

  /**
   * The feature id for the '<em><b>Db Username</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__DB_USERNAME = 2;

  /**
   * The feature id for the '<em><b>Db Password</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__DB_PASSWORD = 3;

  /**
   * The feature id for the '<em><b>Db Catalog</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__DB_CATALOG = 4;

  /**
   * The feature id for the '<em><b>Db Schema</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__DB_SCHEMA = 5;

  /**
   * The feature id for the '<em><b>Db Driverx</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__DB_DRIVERX = 6;

  /**
   * The feature id for the '<em><b>Db Driver</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__DB_DRIVER = 7;

  /**
   * The feature id for the '<em><b>Db Execute Before</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__DB_EXECUTE_BEFORE = 8;

  /**
   * The feature id for the '<em><b>Db Execute After</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__DB_EXECUTE_AFTER = 9;

  /**
   * The feature id for the '<em><b>Db Index Types</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__DB_INDEX_TYPES = 10;

  /**
   * The feature id for the '<em><b>Db Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__DB_TYPE = 11;

  /**
   * The feature id for the '<em><b>Db Meta Info</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__DB_META_INFO = 12;

  /**
   * The feature id for the '<em><b>Db Driver Info</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__DB_DRIVER_INFO = 13;

  /**
   * The feature id for the '<em><b>Db Methods Calls</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__DB_METHODS_CALLS = 14;

  /**
   * The feature id for the '<em><b>Debug</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY__DEBUG = 15;

  /**
   * The number of structural features of the '<em>Database Property</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_PROPERTY_FEATURE_COUNT = 16;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojogenPropertyImpl <em>Pojogen Property</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojogenPropertyImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojogenProperty()
   * @generated
   */
  int POJOGEN_PROPERTY = 26;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__NAME = 0;

  /**
   * The feature id for the '<em><b>Sql Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__SQL_TYPES = 1;

  /**
   * The feature id for the '<em><b>Db Table</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__DB_TABLE = 2;

  /**
   * The feature id for the '<em><b>Column Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__COLUMN_TYPES = 3;

  /**
   * The feature id for the '<em><b>Db Procedure</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__DB_PROCEDURE = 4;

  /**
   * The feature id for the '<em><b>Db Function</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__DB_FUNCTION = 5;

  /**
   * The feature id for the '<em><b>Column Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__COLUMN_TYPE = 6;

  /**
   * The feature id for the '<em><b>Db Tables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__DB_TABLES = 7;

  /**
   * The feature id for the '<em><b>Join Tables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__JOIN_TABLES = 8;

  /**
   * The feature id for the '<em><b>Db Columns</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__DB_COLUMNS = 9;

  /**
   * The feature id for the '<em><b>Tables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__TABLES = 10;

  /**
   * The feature id for the '<em><b>Columns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__COLUMNS = 11;

  /**
   * The feature id for the '<em><b>Exports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__EXPORTS = 12;

  /**
   * The feature id for the '<em><b>Imports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__IMPORTS = 13;

  /**
   * The feature id for the '<em><b>Many2s</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__MANY2S = 14;

  /**
   * The feature id for the '<em><b>Db Column</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__DB_COLUMN = 15;

  /**
   * The feature id for the '<em><b>Inheritance</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__INHERITANCE = 16;

  /**
   * The feature id for the '<em><b>Methods</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__METHODS = 17;

  /**
   * The feature id for the '<em><b>Operators Suffix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__OPERATORS_SUFFIX = 18;

  /**
   * The feature id for the '<em><b>To Implements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__TO_IMPLEMENTS = 19;

  /**
   * The feature id for the '<em><b>To Extends</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__TO_EXTENDS = 20;

  /**
   * The feature id for the '<em><b>To Implements Generics</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__TO_IMPLEMENTS_GENERICS = 21;

  /**
   * The feature id for the '<em><b>To Extends Generics</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__TO_EXTENDS_GENERICS = 22;

  /**
   * The feature id for the '<em><b>Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__VERSION = 23;

  /**
   * The feature id for the '<em><b>Db Not Tables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__DB_NOT_TABLES = 24;

  /**
   * The feature id for the '<em><b>Debug</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__DEBUG = 25;

  /**
   * The feature id for the '<em><b>Proc Pojos</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__PROC_POJOS = 26;

  /**
   * The feature id for the '<em><b>Fun Pojos</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__FUN_POJOS = 27;

  /**
   * The feature id for the '<em><b>Active Filter</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__ACTIVE_FILTER = 28;

  /**
   * The feature id for the '<em><b>Pckg</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__PCKG = 29;

  /**
   * The feature id for the '<em><b>Enum Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__ENUM_NAME = 30;

  /**
   * The feature id for the '<em><b>Db Check Constraints</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY__DB_CHECK_CONSTRAINTS = 31;

  /**
   * The number of structural features of the '<em>Pojogen Property</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJOGEN_PROPERTY_FEATURE_COUNT = 32;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.MetaTypeAssignementImpl <em>Meta Type Assignement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.MetaTypeAssignementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMetaTypeAssignement()
   * @generated
   */
  int META_TYPE_ASSIGNEMENT = 27;

  /**
   * The feature id for the '<em><b>Db Column</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int META_TYPE_ASSIGNEMENT__DB_COLUMN = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int META_TYPE_ASSIGNEMENT__TYPE = 1;

  /**
   * The feature id for the '<em><b>Extension</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int META_TYPE_ASSIGNEMENT__EXTENSION = 2;

  /**
   * The number of structural features of the '<em>Meta Type Assignement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int META_TYPE_ASSIGNEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.MetagenPropertyImpl <em>Metagen Property</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.MetagenPropertyImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMetagenProperty()
   * @generated
   */
  int METAGEN_PROPERTY = 28;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY__NAME = 0;

  /**
   * The feature id for the '<em><b>Sequence</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY__SEQUENCE = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY__TYPE = 2;

  /**
   * The feature id for the '<em><b>Db Tables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY__DB_TABLES = 3;

  /**
   * The feature id for the '<em><b>Db Not Tables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY__DB_NOT_TABLES = 4;

  /**
   * The feature id for the '<em><b>Db Table</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY__DB_TABLE = 5;

  /**
   * The feature id for the '<em><b>Identity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY__IDENTITY = 6;

  /**
   * The feature id for the '<em><b>Meta Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY__META_TYPES = 7;

  /**
   * The feature id for the '<em><b>Db Statement</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY__DB_STATEMENT = 8;

  /**
   * The feature id for the '<em><b>Db Columns</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY__DB_COLUMNS = 9;

  /**
   * The feature id for the '<em><b>Db Function</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY__DB_FUNCTION = 10;

  /**
   * The feature id for the '<em><b>Db Procedure</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY__DB_PROCEDURE = 11;

  /**
   * The feature id for the '<em><b>Debug</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY__DEBUG = 12;

  /**
   * The feature id for the '<em><b>Optional Features</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY__OPTIONAL_FEATURES = 13;

  /**
   * The feature id for the '<em><b>Active Filter</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY__ACTIVE_FILTER = 14;

  /**
   * The number of structural features of the '<em>Metagen Property</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METAGEN_PROPERTY_FEATURE_COUNT = 15;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DaogenPropertyImpl <em>Daogen Property</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DaogenPropertyImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDaogenProperty()
   * @generated
   */
  int DAOGEN_PROPERTY = 29;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAOGEN_PROPERTY__NAME = 0;

  /**
   * The feature id for the '<em><b>Db Tables</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAOGEN_PROPERTY__DB_TABLES = 1;

  /**
   * The feature id for the '<em><b>To Implements</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAOGEN_PROPERTY__TO_IMPLEMENTS = 2;

  /**
   * The feature id for the '<em><b>To Extends</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAOGEN_PROPERTY__TO_EXTENDS = 3;

  /**
   * The feature id for the '<em><b>To Implements Generics</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAOGEN_PROPERTY__TO_IMPLEMENTS_GENERICS = 4;

  /**
   * The feature id for the '<em><b>To Extends Generics</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAOGEN_PROPERTY__TO_EXTENDS_GENERICS = 5;

  /**
   * The feature id for the '<em><b>Db Function</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAOGEN_PROPERTY__DB_FUNCTION = 6;

  /**
   * The feature id for the '<em><b>Result Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAOGEN_PROPERTY__RESULT_TYPE = 7;

  /**
   * The feature id for the '<em><b>Debug</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAOGEN_PROPERTY__DEBUG = 8;

  /**
   * The feature id for the '<em><b>Active Filter</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAOGEN_PROPERTY__ACTIVE_FILTER = 9;

  /**
   * The feature id for the '<em><b>Pckg</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAOGEN_PROPERTY__PCKG = 10;

  /**
   * The number of structural features of the '<em>Daogen Property</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAOGEN_PROPERTY_FEATURE_COUNT = 11;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDefinitionImpl <em>Pojo Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoDefinitionImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDefinition()
   * @generated
   */
  int POJO_DEFINITION = 30;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DEFINITION__NAME = 0;

  /**
   * The feature id for the '<em><b>Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DEFINITION__CLASS = 1;

  /**
   * The feature id for the '<em><b>Classx</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DEFINITION__CLASSX = 2;

  /**
   * The number of structural features of the '<em>Pojo Definition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DEFINITION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.TableDefinitionImpl <em>Table Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.TableDefinitionImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getTableDefinition()
   * @generated
   */
  int TABLE_DEFINITION = 31;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TABLE_DEFINITION__NAME = 0;

  /**
   * The feature id for the '<em><b>Table</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TABLE_DEFINITION__TABLE = 1;

  /**
   * The number of structural features of the '<em>Table Definition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TABLE_DEFINITION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ProcedureDefinitionImpl <em>Procedure Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ProcedureDefinitionImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getProcedureDefinition()
   * @generated
   */
  int PROCEDURE_DEFINITION = 32;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROCEDURE_DEFINITION__NAME = 0;

  /**
   * The feature id for the '<em><b>Table</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROCEDURE_DEFINITION__TABLE = 1;

  /**
   * The number of structural features of the '<em>Procedure Definition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROCEDURE_DEFINITION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.FunctionDefinitionImpl <em>Function Definition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.FunctionDefinitionImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getFunctionDefinition()
   * @generated
   */
  int FUNCTION_DEFINITION = 33;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_DEFINITION__NAME = 0;

  /**
   * The feature id for the '<em><b>Table</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_DEFINITION__TABLE = 1;

  /**
   * The number of structural features of the '<em>Function Definition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_DEFINITION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.MetaStatementImpl <em>Meta Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.MetaStatementImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMetaStatement()
   * @generated
   */
  int META_STATEMENT = 34;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int META_STATEMENT__NAME = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int META_STATEMENT__TYPE = 1;

  /**
   * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int META_STATEMENT__MODIFIERS = 2;

  /**
   * The feature id for the '<em><b>Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int META_STATEMENT__STATEMENT = 3;

  /**
   * The number of structural features of the '<em>Meta Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int META_STATEMENT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.SqlImpl <em>Sql</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.SqlImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getSql()
   * @generated
   */
  int SQL = 35;

  /**
   * The feature id for the '<em><b>Sqls</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL__SQLS = 0;

  /**
   * The number of structural features of the '<em>Sql</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.SqlFragmentImpl <em>Sql Fragment</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.SqlFragmentImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getSqlFragment()
   * @generated
   */
  int SQL_FRAGMENT = 36;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL_FRAGMENT__VALUE = 0;

  /**
   * The feature id for the '<em><b>Col</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL_FRAGMENT__COL = 1;

  /**
   * The feature id for the '<em><b>Cnst</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL_FRAGMENT__CNST = 2;

  /**
   * The feature id for the '<em><b>Ident</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL_FRAGMENT__IDENT = 3;

  /**
   * The feature id for the '<em><b>Cnst Oper</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL_FRAGMENT__CNST_OPER = 4;

  /**
   * The feature id for the '<em><b>Ident Oper</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL_FRAGMENT__IDENT_OPER = 5;

  /**
   * The feature id for the '<em><b>Meta</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL_FRAGMENT__META = 6;

  /**
   * The feature id for the '<em><b>Dbtab</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL_FRAGMENT__DBTAB = 7;

  /**
   * The feature id for the '<em><b>Dbcol</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL_FRAGMENT__DBCOL = 8;

  /**
   * The number of structural features of the '<em>Sql Fragment</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SQL_FRAGMENT_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.MetaSqlImpl <em>Meta Sql</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.MetaSqlImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMetaSql()
   * @generated
   */
  int META_SQL = 37;

  /**
   * The feature id for the '<em><b>Ifs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int META_SQL__IFS = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int META_SQL__TYPE = 1;

  /**
   * The feature id for the '<em><b>Cond</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int META_SQL__COND = 2;

  /**
   * The feature id for the '<em><b>Ftype</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int META_SQL__FTYPE = 3;

  /**
   * The feature id for the '<em><b>Ord</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int META_SQL__ORD = 4;

  /**
   * The number of structural features of the '<em>Meta Sql</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int META_SQL_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.IfSqlImpl <em>If Sql</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.IfSqlImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getIfSql()
   * @generated
   */
  int IF_SQL = 38;

  /**
   * The feature id for the '<em><b>Sqls</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL__SQLS = 0;

  /**
   * The number of structural features of the '<em>If Sql</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.IfSqlFragmentImpl <em>If Sql Fragment</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.IfSqlFragmentImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getIfSqlFragment()
   * @generated
   */
  int IF_SQL_FRAGMENT = 39;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_FRAGMENT__VALUE = 0;

  /**
   * The feature id for the '<em><b>Col</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_FRAGMENT__COL = 1;

  /**
   * The feature id for the '<em><b>Cnst</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_FRAGMENT__CNST = 2;

  /**
   * The feature id for the '<em><b>Ident</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_FRAGMENT__IDENT = 3;

  /**
   * The feature id for the '<em><b>Cnst Oper</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_FRAGMENT__CNST_OPER = 4;

  /**
   * The feature id for the '<em><b>Ident Oper</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_FRAGMENT__IDENT_OPER = 5;

  /**
   * The feature id for the '<em><b>Dbtab</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_FRAGMENT__DBTAB = 6;

  /**
   * The feature id for the '<em><b>Dbcol</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_FRAGMENT__DBCOL = 7;

  /**
   * The feature id for the '<em><b>Meta</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_FRAGMENT__META = 8;

  /**
   * The number of structural features of the '<em>If Sql Fragment</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_FRAGMENT_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.IfMetaSqlImpl <em>If Meta Sql</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.IfMetaSqlImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getIfMetaSql()
   * @generated
   */
  int IF_META_SQL = 40;

  /**
   * The feature id for the '<em><b>Ifs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_META_SQL__IFS = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_META_SQL__TYPE = 1;

  /**
   * The feature id for the '<em><b>Cond</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_META_SQL__COND = 2;

  /**
   * The number of structural features of the '<em>If Meta Sql</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_META_SQL_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.IfSqlCondImpl <em>If Sql Cond</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.IfSqlCondImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getIfSqlCond()
   * @generated
   */
  int IF_SQL_COND = 41;

  /**
   * The feature id for the '<em><b>Bool1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_COND__BOOL1 = 0;

  /**
   * The feature id for the '<em><b>Oper</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_COND__OPER = 1;

  /**
   * The feature id for the '<em><b>Bool2</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_COND__BOOL2 = 2;

  /**
   * The number of structural features of the '<em>If Sql Cond</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_COND_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.IfSqlBoolImpl <em>If Sql Bool</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.IfSqlBoolImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getIfSqlBool()
   * @generated
   */
  int IF_SQL_BOOL = 42;

  /**
   * The feature id for the '<em><b>Not</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_BOOL__NOT = 0;

  /**
   * The feature id for the '<em><b>Cnst</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_BOOL__CNST = 1;

  /**
   * The feature id for the '<em><b>Ident</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_BOOL__IDENT = 2;

  /**
   * The feature id for the '<em><b>Cond</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_BOOL__COND = 3;

  /**
   * The number of structural features of the '<em>If Sql Bool</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_SQL_BOOL_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.OrdSqlImpl <em>Ord Sql</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.OrdSqlImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getOrdSql()
   * @generated
   */
  int ORD_SQL = 43;

  /**
   * The feature id for the '<em><b>Sqls</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORD_SQL__SQLS = 0;

  /**
   * The number of structural features of the '<em>Ord Sql</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORD_SQL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.OrdSql2Impl <em>Ord Sql2</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.OrdSql2Impl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getOrdSql2()
   * @generated
   */
  int ORD_SQL2 = 44;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORD_SQL2__VALUE = 0;

  /**
   * The feature id for the '<em><b>Cnst</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORD_SQL2__CNST = 1;

  /**
   * The feature id for the '<em><b>Ident</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORD_SQL2__IDENT = 2;

  /**
   * The feature id for the '<em><b>Dbcol</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORD_SQL2__DBCOL = 3;

  /**
   * The number of structural features of the '<em>Ord Sql2</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ORD_SQL2_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ColumnImpl <em>Column</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ColumnImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getColumn()
   * @generated
   */
  int COLUMN = 45;

  /**
   * The feature id for the '<em><b>Columns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN__COLUMNS = 0;

  /**
   * The number of structural features of the '<em>Column</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ExtendedColumnImpl <em>Extended Column</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ExtendedColumnImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getExtendedColumn()
   * @generated
   */
  int EXTENDED_COLUMN = 46;

  /**
   * The feature id for the '<em><b>Col</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDED_COLUMN__COL = 0;

  /**
   * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDED_COLUMN__MODIFIERS = 1;

  /**
   * The number of structural features of the '<em>Extended Column</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDED_COLUMN_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ExtendedColumnNameImpl <em>Extended Column Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ExtendedColumnNameImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getExtendedColumnName()
   * @generated
   */
  int EXTENDED_COLUMN_NAME = 47;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDED_COLUMN_NAME__NAME = 0;

  /**
   * The number of structural features of the '<em>Extended Column Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDED_COLUMN_NAME_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ConstantImpl <em>Constant</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ConstantImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getConstant()
   * @generated
   */
  int CONSTANT = 48;

  /**
   * The feature id for the '<em><b>Case</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTANT__CASE = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTANT__NAME = 1;

  /**
   * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTANT__MODIFIERS = 2;

  /**
   * The number of structural features of the '<em>Constant</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTANT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.IdentifierImpl <em>Identifier</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.IdentifierImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getIdentifier()
   * @generated
   */
  int IDENTIFIER = 49;

  /**
   * The feature id for the '<em><b>Mode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER__MODE = 0;

  /**
   * The feature id for the '<em><b>Case</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER__CASE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER__NAME = 2;

  /**
   * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER__MODIFIERS = 3;

  /**
   * The number of structural features of the '<em>Identifier</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ConstantOperatorImpl <em>Constant Operator</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ConstantOperatorImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getConstantOperator()
   * @generated
   */
  int CONSTANT_OPERATOR = 50;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTANT_OPERATOR__NAME = 0;

  /**
   * The number of structural features of the '<em>Constant Operator</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTANT_OPERATOR_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.IdentifierOperatorImpl <em>Identifier Operator</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.IdentifierOperatorImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getIdentifierOperator()
   * @generated
   */
  int IDENTIFIER_OPERATOR = 51;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER_OPERATOR__NAME = 0;

  /**
   * The number of structural features of the '<em>Identifier Operator</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IDENTIFIER_OPERATOR_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DatabaseColumnImpl <em>Database Column</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DatabaseColumnImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDatabaseColumn()
   * @generated
   */
  int DATABASE_COLUMN = 52;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_COLUMN__NAME = 0;

  /**
   * The number of structural features of the '<em>Database Column</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_COLUMN_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DatabaseTableImpl <em>Database Table</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DatabaseTableImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDatabaseTable()
   * @generated
   */
  int DATABASE_TABLE = 53;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_TABLE__NAME = 0;

  /**
   * The number of structural features of the '<em>Database Table</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_TABLE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.MappingRuleImpl <em>Mapping Rule</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.MappingRuleImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMappingRule()
   * @generated
   */
  int MAPPING_RULE = 54;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_RULE__NAME = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_RULE__TYPE = 1;

  /**
   * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_RULE__MODIFIERS = 2;

  /**
   * The feature id for the '<em><b>Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_RULE__MAPPING = 3;

  /**
   * The number of structural features of the '<em>Mapping Rule</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_RULE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.MappingImpl <em>Mapping</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.MappingImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMapping()
   * @generated
   */
  int MAPPING = 55;

  /**
   * The feature id for the '<em><b>Mapping Items</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING__MAPPING_ITEMS = 0;

  /**
   * The number of structural features of the '<em>Mapping</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.MappingItemImpl <em>Mapping Item</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.MappingItemImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMappingItem()
   * @generated
   */
  int MAPPING_ITEM = 56;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_ITEM__NAME = 0;

  /**
   * The feature id for the '<em><b>Attr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_ITEM__ATTR = 1;

  /**
   * The number of structural features of the '<em>Mapping Item</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_ITEM_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.MappingColumnImpl <em>Mapping Column</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.MappingColumnImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMappingColumn()
   * @generated
   */
  int MAPPING_COLUMN = 57;

  /**
   * The feature id for the '<em><b>Items</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_COLUMN__ITEMS = 0;

  /**
   * The number of structural features of the '<em>Mapping Column</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_COLUMN_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ExtendedMappingItemImpl <em>Extended Mapping Item</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ExtendedMappingItemImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getExtendedMappingItem()
   * @generated
   */
  int EXTENDED_MAPPING_ITEM = 58;

  /**
   * The feature id for the '<em><b>Attr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDED_MAPPING_ITEM__ATTR = 0;

  /**
   * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDED_MAPPING_ITEM__MODIFIERS = 1;

  /**
   * The number of structural features of the '<em>Extended Mapping Item</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDED_MAPPING_ITEM_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.MappingColumnNameImpl <em>Mapping Column Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.MappingColumnNameImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMappingColumnName()
   * @generated
   */
  int MAPPING_COLUMN_NAME = 59;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_COLUMN_NAME__NAME = 0;

  /**
   * The number of structural features of the '<em>Mapping Column Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MAPPING_COLUMN_NAME_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.OptionalFeatureImpl <em>Optional Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.OptionalFeatureImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getOptionalFeature()
   * @generated
   */
  int OPTIONAL_FEATURE = 60;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPTIONAL_FEATURE__NAME = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPTIONAL_FEATURE__TYPE = 1;

  /**
   * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPTIONAL_FEATURE__MODIFIERS = 2;

  /**
   * The feature id for the '<em><b>Option</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPTIONAL_FEATURE__OPTION = 3;

  /**
   * The number of structural features of the '<em>Optional Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPTIONAL_FEATURE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoTypeImpl <em>Pojo Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoTypeImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoType()
   * @generated
   */
  int POJO_TYPE = 61;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_TYPE__TYPE = 0;

  /**
   * The feature id for the '<em><b>Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_TYPE__REF = 1;

  /**
   * The feature id for the '<em><b>Gtype</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_TYPE__GTYPE = 2;

  /**
   * The feature id for the '<em><b>Gref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_TYPE__GREF = 3;

  /**
   * The feature id for the '<em><b>Array</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_TYPE__ARRAY = 4;

  /**
   * The number of structural features of the '<em>Pojo Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PackageDirectiveImpl <em>Package Directive</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PackageDirectiveImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPackageDirective()
   * @generated
   */
  int PACKAGE_DIRECTIVE = 62;

  /**
   * The number of structural features of the '<em>Package Directive</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_DIRECTIVE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.AbstractPojoEntityImpl <em>Abstract Pojo Entity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.AbstractPojoEntityImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAbstractPojoEntity()
   * @generated
   */
  int ABSTRACT_POJO_ENTITY = 69;

  /**
   * The number of structural features of the '<em>Abstract Pojo Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ABSTRACT_POJO_ENTITY_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PackageImpl <em>Package</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PackageImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPackage()
   * @generated
   */
  int PACKAGE = 63;

  /**
   * The feature id for the '<em><b>Directives</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE__DIRECTIVES = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE__NAME = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE__ELEMENTS = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Package</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_FEATURE_COUNT = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveImpl <em>Annotation Directive</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirective()
   * @generated
   */
  int ANNOTATION_DIRECTIVE = 64;

  /**
   * The number of structural features of the '<em>Annotation Directive</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_DIRECTIVE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationImpl <em>Annotation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.AnnotationImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotation()
   * @generated
   */
  int ANNOTATION = 65;

  /**
   * The feature id for the '<em><b>Directives</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__DIRECTIVES = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__TYPE = 1;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION__FEATURES = 2;

  /**
   * The number of structural features of the '<em>Annotation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationPropertyImpl <em>Annotation Property</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.AnnotationPropertyImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationProperty()
   * @generated
   */
  int ANNOTATION_PROPERTY = 66;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_PROPERTY__NAME = 0;

  /**
   * The feature id for the '<em><b>Number</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_PROPERTY__NUMBER = 1;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_PROPERTY__VALUE = 2;

  /**
   * The feature id for the '<em><b>Constant</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_PROPERTY__CONSTANT = 3;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_PROPERTY__TYPE = 4;

  /**
   * The feature id for the '<em><b>Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_PROPERTY__REF = 5;

  /**
   * The number of structural features of the '<em>Annotation Property</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_PROPERTY_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.EntityImpl <em>Entity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.EntityImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEntity()
   * @generated
   */
  int ENTITY = 67;

  /**
   * The feature id for the '<em><b>Directives</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__DIRECTIVES = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__NAME = 1;

  /**
   * The number of structural features of the '<em>Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotatedEntityImpl <em>Annotated Entity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.AnnotatedEntityImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotatedEntity()
   * @generated
   */
  int ANNOTATED_ENTITY = 68;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATED_ENTITY__ANNOTATIONS = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Entity</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATED_ENTITY__ENTITY = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Annotated Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATED_ENTITY_FEATURE_COUNT = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ImportImpl <em>Import</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ImportImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getImport()
   * @generated
   */
  int IMPORT = 70;

  /**
   * The feature id for the '<em><b>Imported Namespace</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT__IMPORTED_NAMESPACE = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Import</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_FEATURE_COUNT = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ImplementsExtendsDirectiveImpl <em>Implements Extends Directive</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ImplementsExtendsDirectiveImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getImplementsExtendsDirective()
   * @generated
   */
  int IMPLEMENTS_EXTENDS_DIRECTIVE = 71;

  /**
   * The number of structural features of the '<em>Implements Extends Directive</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS_EXTENDS_DIRECTIVE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ImplementsImpl <em>Implements</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ImplementsImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getImplements()
   * @generated
   */
  int IMPLEMENTS = 72;

  /**
   * The feature id for the '<em><b>Directives</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS__DIRECTIVES = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Implements</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS__IMPLEMENTS = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Only Pojos</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS__ONLY_POJOS = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Only Daos</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS__ONLY_DAOS = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Except Pojos</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS__EXCEPT_POJOS = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Except Daos</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS__EXCEPT_DAOS = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 5;

  /**
   * The number of structural features of the '<em>Implements</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS_FEATURE_COUNT = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 6;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ExtendsImpl <em>Extends</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ExtendsImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getExtends()
   * @generated
   */
  int EXTENDS = 73;

  /**
   * The feature id for the '<em><b>Directives</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS__DIRECTIVES = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Extends</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS__EXTENDS = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Only Pojos</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS__ONLY_POJOS = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Only Daos</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS__ONLY_DAOS = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Except Pojos</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS__EXCEPT_POJOS = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Except Daos</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS__EXCEPT_DAOS = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 5;

  /**
   * The number of structural features of the '<em>Extends</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENDS_FEATURE_COUNT = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 6;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoEntityModifier1Impl <em>Pojo Entity Modifier1</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoEntityModifier1Impl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoEntityModifier1()
   * @generated
   */
  int POJO_ENTITY_MODIFIER1 = 74;

  /**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_ENTITY_MODIFIER1__FINAL = 0;

  /**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_ENTITY_MODIFIER1__ABSTRACT = 1;

  /**
   * The number of structural features of the '<em>Pojo Entity Modifier1</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_ENTITY_MODIFIER1_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DirectivePropertiesImpl <em>Directive Properties</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DirectivePropertiesImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDirectiveProperties()
   * @generated
   */
  int DIRECTIVE_PROPERTIES = 75;

  /**
   * The feature id for the '<em><b>Features</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTIVE_PROPERTIES__FEATURES = 0;

  /**
   * The number of structural features of the '<em>Directive Properties</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIRECTIVE_PROPERTIES_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveImpl <em>Pojo Directive</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirective()
   * @generated
   */
  int POJO_DIRECTIVE = 76;

  /**
   * The number of structural features of the '<em>Pojo Directive</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoEntityModifier2Impl <em>Pojo Entity Modifier2</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoEntityModifier2Impl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoEntityModifier2()
   * @generated
   */
  int POJO_ENTITY_MODIFIER2 = 77;

  /**
   * The feature id for the '<em><b>Super Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_ENTITY_MODIFIER2__SUPER_TYPE = 0;

  /**
   * The number of structural features of the '<em>Pojo Entity Modifier2</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_ENTITY_MODIFIER2_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoEntityImpl <em>Pojo Entity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoEntityImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoEntity()
   * @generated
   */
  int POJO_ENTITY = 78;

  /**
   * The feature id for the '<em><b>Directives</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_ENTITY__DIRECTIVES = ENTITY__DIRECTIVES;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_ENTITY__NAME = ENTITY__NAME;

  /**
   * The feature id for the '<em><b>Modifiers1</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_ENTITY__MODIFIERS1 = ENTITY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Modifiers2</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_ENTITY__MODIFIERS2 = ENTITY_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_ENTITY__FEATURES = ENTITY_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Pojo Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_ENTITY_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoAnnotatedPropertyImpl <em>Pojo Annotated Property</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoAnnotatedPropertyImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoAnnotatedProperty()
   * @generated
   */
  int POJO_ANNOTATED_PROPERTY = 79;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_ANNOTATED_PROPERTY__ANNOTATIONS = 0;

  /**
   * The feature id for the '<em><b>Feature</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_ANNOTATED_PROPERTY__FEATURE = 1;

  /**
   * The number of structural features of the '<em>Pojo Annotated Property</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_ANNOTATED_PROPERTY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveImpl <em>Pojo Property Directive</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirective()
   * @generated
   */
  int POJO_PROPERTY_DIRECTIVE = 80;

  /**
   * The number of structural features of the '<em>Pojo Property Directive</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyImpl <em>Pojo Property</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoProperty()
   * @generated
   */
  int POJO_PROPERTY = 81;

  /**
   * The feature id for the '<em><b>Directives</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY__DIRECTIVES = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY__NAME = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY__TYPE = 2;

  /**
   * The feature id for the '<em><b>Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY__REF = 3;

  /**
   * The feature id for the '<em><b>Gtype</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY__GTYPE = 4;

  /**
   * The feature id for the '<em><b>Gref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY__GREF = 5;

  /**
   * The feature id for the '<em><b>Array</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY__ARRAY = 6;

  /**
   * The number of structural features of the '<em>Pojo Property</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.EnumEntityModifier1Impl <em>Enum Entity Modifier1</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.EnumEntityModifier1Impl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEnumEntityModifier1()
   * @generated
   */
  int ENUM_ENTITY_MODIFIER1 = 82;

  /**
   * The feature id for the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_ENTITY_MODIFIER1__FINAL = 0;

  /**
   * The number of structural features of the '<em>Enum Entity Modifier1</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_ENTITY_MODIFIER1_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.EnumEntityModifier2Impl <em>Enum Entity Modifier2</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.EnumEntityModifier2Impl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEnumEntityModifier2()
   * @generated
   */
  int ENUM_ENTITY_MODIFIER2 = 83;

  /**
   * The feature id for the '<em><b>Super Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_ENTITY_MODIFIER2__SUPER_TYPE = 0;

  /**
   * The number of structural features of the '<em>Enum Entity Modifier2</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_ENTITY_MODIFIER2_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.EnumEntityImpl <em>Enum Entity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.EnumEntityImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEnumEntity()
   * @generated
   */
  int ENUM_ENTITY = 84;

  /**
   * The feature id for the '<em><b>Directives</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_ENTITY__DIRECTIVES = ENTITY__DIRECTIVES;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_ENTITY__NAME = ENTITY__NAME;

  /**
   * The feature id for the '<em><b>Modifiers1</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_ENTITY__MODIFIERS1 = ENTITY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Modifiers2</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_ENTITY__MODIFIERS2 = ENTITY_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_ENTITY__FEATURES = ENTITY_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Enum Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_ENTITY_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.EnumPropertyValueImpl <em>Enum Property Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.EnumPropertyValueImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEnumPropertyValue()
   * @generated
   */
  int ENUM_PROPERTY_VALUE = 85;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_PROPERTY_VALUE__NAME = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_PROPERTY_VALUE__VALUE = 1;

  /**
   * The number of structural features of the '<em>Enum Property Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_PROPERTY_VALUE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.EnumPropertyDirectiveImpl <em>Enum Property Directive</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.EnumPropertyDirectiveImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEnumPropertyDirective()
   * @generated
   */
  int ENUM_PROPERTY_DIRECTIVE = 86;

  /**
   * The number of structural features of the '<em>Enum Property Directive</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_PROPERTY_DIRECTIVE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.EnumPropertyImpl <em>Enum Property</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.EnumPropertyImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEnumProperty()
   * @generated
   */
  int ENUM_PROPERTY = 87;

  /**
   * The feature id for the '<em><b>Directives</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_PROPERTY__DIRECTIVES = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_PROPERTY__NAME = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_PROPERTY__TYPE = 2;

  /**
   * The number of structural features of the '<em>Enum Property</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_PROPERTY_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DaoDirectiveParametersImpl <em>Dao Directive Parameters</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DaoDirectiveParametersImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDaoDirectiveParameters()
   * @generated
   */
  int DAO_DIRECTIVE_PARAMETERS = 88;

  /**
   * The feature id for the '<em><b>Out</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAO_DIRECTIVE_PARAMETERS__OUT = 0;

  /**
   * The feature id for the '<em><b>Ins</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAO_DIRECTIVE_PARAMETERS__INS = 1;

  /**
   * The number of structural features of the '<em>Dao Directive Parameters</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAO_DIRECTIVE_PARAMETERS_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DescendantAssignmentImpl <em>Descendant Assignment</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DescendantAssignmentImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDescendantAssignment()
   * @generated
   */
  int DESCENDANT_ASSIGNMENT = 89;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESCENDANT_ASSIGNMENT__VALUE = 0;

  /**
   * The feature id for the '<em><b>Descendant</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESCENDANT_ASSIGNMENT__DESCENDANT = 1;

  /**
   * The number of structural features of the '<em>Descendant Assignment</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESCENDANT_ASSIGNMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.FunProcTypeImpl <em>Fun Proc Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.FunProcTypeImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getFunProcType()
   * @generated
   */
  int FUN_PROC_TYPE = 90;

  /**
   * The number of structural features of the '<em>Fun Proc Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUN_PROC_TYPE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DaoDirectiveImpl <em>Dao Directive</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DaoDirectiveImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDaoDirective()
   * @generated
   */
  int DAO_DIRECTIVE = 91;

  /**
   * The number of structural features of the '<em>Dao Directive</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAO_DIRECTIVE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDaoModifierImpl <em>Pojo Dao Modifier</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoDaoModifierImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDaoModifier()
   * @generated
   */
  int POJO_DAO_MODIFIER = 92;

  /**
   * The feature id for the '<em><b>Super Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DAO_MODIFIER__SUPER_TYPE = 0;

  /**
   * The number of structural features of the '<em>Pojo Dao Modifier</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DAO_MODIFIER_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDaoImpl <em>Pojo Dao</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoDaoImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDao()
   * @generated
   */
  int POJO_DAO = 93;

  /**
   * The feature id for the '<em><b>Directives</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DAO__DIRECTIVES = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Modifiers1</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DAO__MODIFIERS1 = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DAO__NAME = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Modifiers2</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DAO__MODIFIERS2 = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Pojo Dao</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DAO_FEATURE_COUNT = ABSTRACT_POJO_ENTITY_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PackageDirectiveSuffixImpl <em>Package Directive Suffix</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PackageDirectiveSuffixImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPackageDirectiveSuffix()
   * @generated
   */
  int PACKAGE_DIRECTIVE_SUFFIX = 94;

  /**
   * The feature id for the '<em><b>Suffix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_DIRECTIVE_SUFFIX__SUFFIX = PACKAGE_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Package Directive Suffix</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_DIRECTIVE_SUFFIX_FEATURE_COUNT = PACKAGE_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PackageDirectiveImplementationImpl <em>Package Directive Implementation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PackageDirectiveImplementationImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPackageDirectiveImplementation()
   * @generated
   */
  int PACKAGE_DIRECTIVE_IMPLEMENTATION = 95;

  /**
   * The feature id for the '<em><b>Implementation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_DIRECTIVE_IMPLEMENTATION__IMPLEMENTATION = PACKAGE_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Package Directive Implementation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_DIRECTIVE_IMPLEMENTATION_FEATURE_COUNT = PACKAGE_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveConflictImpl <em>Annotation Directive Conflict</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveConflictImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirectiveConflict()
   * @generated
   */
  int ANNOTATION_DIRECTIVE_CONFLICT = 96;

  /**
   * The number of structural features of the '<em>Annotation Directive Conflict</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_DIRECTIVE_CONFLICT_FEATURE_COUNT = ANNOTATION_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveStaticImpl <em>Annotation Directive Static</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveStaticImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirectiveStatic()
   * @generated
   */
  int ANNOTATION_DIRECTIVE_STATIC = 97;

  /**
   * The number of structural features of the '<em>Annotation Directive Static</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_DIRECTIVE_STATIC_FEATURE_COUNT = ANNOTATION_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveConstructorImpl <em>Annotation Directive Constructor</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveConstructorImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirectiveConstructor()
   * @generated
   */
  int ANNOTATION_DIRECTIVE_CONSTRUCTOR = 98;

  /**
   * The number of structural features of the '<em>Annotation Directive Constructor</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_DIRECTIVE_CONSTRUCTOR_FEATURE_COUNT = ANNOTATION_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveStandardImpl <em>Annotation Directive Standard</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveStandardImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirectiveStandard()
   * @generated
   */
  int ANNOTATION_DIRECTIVE_STANDARD = 99;

  /**
   * The number of structural features of the '<em>Annotation Directive Standard</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_DIRECTIVE_STANDARD_FEATURE_COUNT = ANNOTATION_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveSetterImpl <em>Annotation Directive Setter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveSetterImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirectiveSetter()
   * @generated
   */
  int ANNOTATION_DIRECTIVE_SETTER = 100;

  /**
   * The number of structural features of the '<em>Annotation Directive Setter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_DIRECTIVE_SETTER_FEATURE_COUNT = ANNOTATION_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveGetterImpl <em>Annotation Directive Getter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveGetterImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirectiveGetter()
   * @generated
   */
  int ANNOTATION_DIRECTIVE_GETTER = 101;

  /**
   * The number of structural features of the '<em>Annotation Directive Getter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_DIRECTIVE_GETTER_FEATURE_COUNT = ANNOTATION_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveAttributeImpl <em>Annotation Directive Attribute</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveAttributeImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirectiveAttribute()
   * @generated
   */
  int ANNOTATION_DIRECTIVE_ATTRIBUTE = 102;

  /**
   * The number of structural features of the '<em>Annotation Directive Attribute</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANNOTATION_DIRECTIVE_ATTRIBUTE_FEATURE_COUNT = ANNOTATION_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ImplementsExtendsDirectiveGenericsImpl <em>Implements Extends Directive Generics</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ImplementsExtendsDirectiveGenericsImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getImplementsExtendsDirectiveGenerics()
   * @generated
   */
  int IMPLEMENTS_EXTENDS_DIRECTIVE_GENERICS = 103;

  /**
   * The number of structural features of the '<em>Implements Extends Directive Generics</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPLEMENTS_EXTENDS_DIRECTIVE_GENERICS_FEATURE_COUNT = IMPLEMENTS_EXTENDS_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveToStringImpl <em>Pojo Directive To String</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveToStringImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirectiveToString()
   * @generated
   */
  int POJO_DIRECTIVE_TO_STRING = 104;

  /**
   * The feature id for the '<em><b>Proplist</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_TO_STRING__PROPLIST = POJO_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Pojo Directive To String</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_TO_STRING_FEATURE_COUNT = POJO_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveIndexImpl <em>Pojo Directive Index</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveIndexImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirectiveIndex()
   * @generated
   */
  int POJO_DIRECTIVE_INDEX = 105;

  /**
   * The feature id for the '<em><b>Index</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_INDEX__INDEX = POJO_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Proplist</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_INDEX__PROPLIST = POJO_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Pojo Directive Index</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_INDEX_FEATURE_COUNT = POJO_DIRECTIVE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveOperatorsImpl <em>Pojo Directive Operators</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveOperatorsImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirectiveOperators()
   * @generated
   */
  int POJO_DIRECTIVE_OPERATORS = 106;

  /**
   * The feature id for the '<em><b>Operators Suffix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_OPERATORS__OPERATORS_SUFFIX = POJO_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Pojo Directive Operators</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_OPERATORS_FEATURE_COUNT = POJO_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveSerializableImpl <em>Pojo Directive Serializable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveSerializableImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirectiveSerializable()
   * @generated
   */
  int POJO_DIRECTIVE_SERIALIZABLE = 107;

  /**
   * The feature id for the '<em><b>Sernum</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_SERIALIZABLE__SERNUM = POJO_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Pojo Directive Serializable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_SERIALIZABLE_FEATURE_COUNT = POJO_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveDiscriminatorImpl <em>Pojo Directive Discriminator</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveDiscriminatorImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirectiveDiscriminator()
   * @generated
   */
  int POJO_DIRECTIVE_DISCRIMINATOR = 108;

  /**
   * The feature id for the '<em><b>Discriminator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_DISCRIMINATOR__DISCRIMINATOR = POJO_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Pojo Directive Discriminator</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_DISCRIMINATOR_FEATURE_COUNT = POJO_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveEqualsImpl <em>Pojo Directive Equals</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveEqualsImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirectiveEquals()
   * @generated
   */
  int POJO_DIRECTIVE_EQUALS = 109;

  /**
   * The feature id for the '<em><b>Proplist</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_EQUALS__PROPLIST = POJO_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Pojo Directive Equals</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_EQUALS_FEATURE_COUNT = POJO_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveHashCodeImpl <em>Pojo Directive Hash Code</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveHashCodeImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirectiveHashCode()
   * @generated
   */
  int POJO_DIRECTIVE_HASH_CODE = 110;

  /**
   * The feature id for the '<em><b>Proplist</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_HASH_CODE__PROPLIST = POJO_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Pojo Directive Hash Code</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_DIRECTIVE_HASH_CODE_FEATURE_COUNT = POJO_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveRequiredImpl <em>Pojo Property Directive Required</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveRequiredImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveRequired()
   * @generated
   */
  int POJO_PROPERTY_DIRECTIVE_REQUIRED = 111;

  /**
   * The number of structural features of the '<em>Pojo Property Directive Required</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_REQUIRED_FEATURE_COUNT = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectivePrimaryKeyImpl <em>Pojo Property Directive Primary Key</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectivePrimaryKeyImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectivePrimaryKey()
   * @generated
   */
  int POJO_PROPERTY_DIRECTIVE_PRIMARY_KEY = 112;

  /**
   * The number of structural features of the '<em>Pojo Property Directive Primary Key</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_PRIMARY_KEY_FEATURE_COUNT = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveDiscriminatorImpl <em>Pojo Property Directive Discriminator</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveDiscriminatorImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveDiscriminator()
   * @generated
   */
  int POJO_PROPERTY_DIRECTIVE_DISCRIMINATOR = 113;

  /**
   * The number of structural features of the '<em>Pojo Property Directive Discriminator</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_DISCRIMINATOR_FEATURE_COUNT = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveIndexImpl <em>Pojo Property Directive Index</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveIndexImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveIndex()
   * @generated
   */
  int POJO_PROPERTY_DIRECTIVE_INDEX = 114;

  /**
   * The feature id for the '<em><b>Index</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_INDEX__INDEX = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Pojo Property Directive Index</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_INDEX_FEATURE_COUNT = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveVersionImpl <em>Pojo Property Directive Version</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveVersionImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveVersion()
   * @generated
   */
  int POJO_PROPERTY_DIRECTIVE_VERSION = 115;

  /**
   * The number of structural features of the '<em>Pojo Property Directive Version</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_VERSION_FEATURE_COUNT = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveUpdateColImpl <em>Pojo Property Directive Update Col</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveUpdateColImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveUpdateCol()
   * @generated
   */
  int POJO_PROPERTY_DIRECTIVE_UPDATE_COL = 116;

  /**
   * The feature id for the '<em><b>Update Column1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN1 = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Update Column2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN2 = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Pojo Property Directive Update Col</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_UPDATE_COL_FEATURE_COUNT = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveCreateColImpl <em>Pojo Property Directive Create Col</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveCreateColImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveCreateCol()
   * @generated
   */
  int POJO_PROPERTY_DIRECTIVE_CREATE_COL = 117;

  /**
   * The feature id for the '<em><b>Create Column1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_CREATE_COL__CREATE_COLUMN1 = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Create Column2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_CREATE_COL__CREATE_COLUMN2 = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Pojo Property Directive Create Col</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_CREATE_COL_FEATURE_COUNT = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveToInitImpl <em>Pojo Property Directive To Init</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveToInitImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveToInit()
   * @generated
   */
  int POJO_PROPERTY_DIRECTIVE_TO_INIT = 118;

  /**
   * The number of structural features of the '<em>Pojo Property Directive To Init</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_TO_INIT_FEATURE_COUNT = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveEnumInitImpl <em>Pojo Property Directive Enum Init</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveEnumInitImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveEnumInit()
   * @generated
   */
  int POJO_PROPERTY_DIRECTIVE_ENUM_INIT = 119;

  /**
   * The number of structural features of the '<em>Pojo Property Directive Enum Init</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_ENUM_INIT_FEATURE_COUNT = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveIsDefImpl <em>Pojo Property Directive Is Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveIsDefImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveIsDef()
   * @generated
   */
  int POJO_PROPERTY_DIRECTIVE_IS_DEF = 120;

  /**
   * The number of structural features of the '<em>Pojo Property Directive Is Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_IS_DEF_FEATURE_COUNT = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveEnumDefImpl <em>Pojo Property Directive Enum Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveEnumDefImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveEnumDef()
   * @generated
   */
  int POJO_PROPERTY_DIRECTIVE_ENUM_DEF = 121;

  /**
   * The number of structural features of the '<em>Pojo Property Directive Enum Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POJO_PROPERTY_DIRECTIVE_ENUM_DEF_FEATURE_COUNT = POJO_PROPERTY_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.EnumPropertyDirectiveValuesImpl <em>Enum Property Directive Values</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.EnumPropertyDirectiveValuesImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEnumPropertyDirectiveValues()
   * @generated
   */
  int ENUM_PROPERTY_DIRECTIVE_VALUES = 122;

  /**
   * The feature id for the '<em><b>Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_PROPERTY_DIRECTIVE_VALUES__VALUES = ENUM_PROPERTY_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Enum Property Directive Values</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_PROPERTY_DIRECTIVE_VALUES_FEATURE_COUNT = ENUM_PROPERTY_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.FunctionCallQueryImpl <em>Function Call Query</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.FunctionCallQueryImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getFunctionCallQuery()
   * @generated
   */
  int FUNCTION_CALL_QUERY = 123;

  /**
   * The number of structural features of the '<em>Function Call Query</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_QUERY_FEATURE_COUNT = FUN_PROC_TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ProcedureCallQueryImpl <em>Procedure Call Query</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ProcedureCallQueryImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getProcedureCallQuery()
   * @generated
   */
  int PROCEDURE_CALL_QUERY = 124;

  /**
   * The number of structural features of the '<em>Procedure Call Query</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROCEDURE_CALL_QUERY_FEATURE_COUNT = FUN_PROC_TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.FunctionCallImpl <em>Function Call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.FunctionCallImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getFunctionCall()
   * @generated
   */
  int FUNCTION_CALL = 125;

  /**
   * The number of structural features of the '<em>Function Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_FEATURE_COUNT = FUN_PROC_TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.ProcedureUpdateImpl <em>Procedure Update</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.ProcedureUpdateImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getProcedureUpdate()
   * @generated
   */
  int PROCEDURE_UPDATE = 126;

  /**
   * The number of structural features of the '<em>Procedure Update</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROCEDURE_UPDATE_FEATURE_COUNT = FUN_PROC_TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.FunctionQueryImpl <em>Function Query</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.FunctionQueryImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getFunctionQuery()
   * @generated
   */
  int FUNCTION_QUERY = 127;

  /**
   * The number of structural features of the '<em>Function Query</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_QUERY_FEATURE_COUNT = FUN_PROC_TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DaoDirectiveSerializableImpl <em>Dao Directive Serializable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DaoDirectiveSerializableImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDaoDirectiveSerializable()
   * @generated
   */
  int DAO_DIRECTIVE_SERIALIZABLE = 128;

  /**
   * The feature id for the '<em><b>Sernum</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAO_DIRECTIVE_SERIALIZABLE__SERNUM = DAO_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Dao Directive Serializable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAO_DIRECTIVE_SERIALIZABLE_FEATURE_COUNT = DAO_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DaoDirectiveDiscriminatorImpl <em>Dao Directive Discriminator</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DaoDirectiveDiscriminatorImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDaoDirectiveDiscriminator()
   * @generated
   */
  int DAO_DIRECTIVE_DISCRIMINATOR = 129;

  /**
   * The feature id for the '<em><b>Ancestor</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAO_DIRECTIVE_DISCRIMINATOR__ANCESTOR = DAO_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Descendants</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAO_DIRECTIVE_DISCRIMINATOR__DESCENDANTS = DAO_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Dao Directive Discriminator</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAO_DIRECTIVE_DISCRIMINATOR_FEATURE_COUNT = DAO_DIRECTIVE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DaoDirectiveCrudImpl <em>Dao Directive Crud</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DaoDirectiveCrudImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDaoDirectiveCrud()
   * @generated
   */
  int DAO_DIRECTIVE_CRUD = 130;

  /**
   * The feature id for the '<em><b>Pojo</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAO_DIRECTIVE_CRUD__POJO = DAO_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Dao Directive Crud</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAO_DIRECTIVE_CRUD_FEATURE_COUNT = DAO_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.DaoDirectiveQueryImpl <em>Dao Directive Query</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.DaoDirectiveQueryImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDaoDirectiveQuery()
   * @generated
   */
  int DAO_DIRECTIVE_QUERY = 131;

  /**
   * The feature id for the '<em><b>Pojo</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAO_DIRECTIVE_QUERY__POJO = DAO_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Dao Directive Query</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DAO_DIRECTIVE_QUERY_FEATURE_COUNT = DAO_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.sqlproc.dsl.processorDsl.impl.FunProcDirectiveImpl <em>Fun Proc Directive</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.sqlproc.dsl.processorDsl.impl.FunProcDirectiveImpl
   * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getFunProcDirective()
   * @generated
   */
  int FUN_PROC_DIRECTIVE = 132;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUN_PROC_DIRECTIVE__TYPE = DAO_DIRECTIVE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Paramlist</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUN_PROC_DIRECTIVE__PARAMLIST = DAO_DIRECTIVE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Fun Proc Directive</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUN_PROC_DIRECTIVE_FEATURE_COUNT = DAO_DIRECTIVE_FEATURE_COUNT + 2;


  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.Artifacts <em>Artifacts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Artifacts</em>'.
   * @see org.sqlproc.dsl.processorDsl.Artifacts
   * @generated
   */
  EClass getArtifacts();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Artifacts#getProperties <em>Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Properties</em>'.
   * @see org.sqlproc.dsl.processorDsl.Artifacts#getProperties()
   * @see #getArtifacts()
   * @generated
   */
  EReference getArtifacts_Properties();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Artifacts#getPojos <em>Pojos</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Pojos</em>'.
   * @see org.sqlproc.dsl.processorDsl.Artifacts#getPojos()
   * @see #getArtifacts()
   * @generated
   */
  EReference getArtifacts_Pojos();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Artifacts#getTables <em>Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.Artifacts#getTables()
   * @see #getArtifacts()
   * @generated
   */
  EReference getArtifacts_Tables();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Artifacts#getProcedures <em>Procedures</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Procedures</em>'.
   * @see org.sqlproc.dsl.processorDsl.Artifacts#getProcedures()
   * @see #getArtifacts()
   * @generated
   */
  EReference getArtifacts_Procedures();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Artifacts#getFunctions <em>Functions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Functions</em>'.
   * @see org.sqlproc.dsl.processorDsl.Artifacts#getFunctions()
   * @see #getArtifacts()
   * @generated
   */
  EReference getArtifacts_Functions();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Artifacts#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.sqlproc.dsl.processorDsl.Artifacts#getStatements()
   * @see #getArtifacts()
   * @generated
   */
  EReference getArtifacts_Statements();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Artifacts#getMappings <em>Mappings</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Mappings</em>'.
   * @see org.sqlproc.dsl.processorDsl.Artifacts#getMappings()
   * @see #getArtifacts()
   * @generated
   */
  EReference getArtifacts_Mappings();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Artifacts#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see org.sqlproc.dsl.processorDsl.Artifacts#getFeatures()
   * @see #getArtifacts()
   * @generated
   */
  EReference getArtifacts_Features();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Artifacts#getPackages <em>Packages</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Packages</em>'.
   * @see org.sqlproc.dsl.processorDsl.Artifacts#getPackages()
   * @see #getArtifacts()
   * @generated
   */
  EReference getArtifacts_Packages();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DatabaseCatalogAssignement <em>Database Catalog Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Database Catalog Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseCatalogAssignement
   * @generated
   */
  EClass getDatabaseCatalogAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DatabaseCatalogAssignement#getDbCatalog <em>Db Catalog</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Catalog</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseCatalogAssignement#getDbCatalog()
   * @see #getDatabaseCatalogAssignement()
   * @generated
   */
  EAttribute getDatabaseCatalogAssignement_DbCatalog();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DatabaseSchemaAssignement <em>Database Schema Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Database Schema Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseSchemaAssignement
   * @generated
   */
  EClass getDatabaseSchemaAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DatabaseSchemaAssignement#getDbSchema <em>Db Schema</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Schema</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseSchemaAssignement#getDbSchema()
   * @see #getDatabaseSchemaAssignement()
   * @generated
   */
  EAttribute getDatabaseSchemaAssignement_DbSchema();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DatabaseTypeAssignement <em>Database Type Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Database Type Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseTypeAssignement
   * @generated
   */
  EClass getDatabaseTypeAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DatabaseTypeAssignement#getDbType <em>Db Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseTypeAssignement#getDbType()
   * @see #getDatabaseTypeAssignement()
   * @generated
   */
  EAttribute getDatabaseTypeAssignement_DbType();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DatabaseMetaInfoAssignement <em>Database Meta Info Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Database Meta Info Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseMetaInfoAssignement
   * @generated
   */
  EClass getDatabaseMetaInfoAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DatabaseMetaInfoAssignement#getDbMetaInfo <em>Db Meta Info</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Meta Info</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseMetaInfoAssignement#getDbMetaInfo()
   * @see #getDatabaseMetaInfoAssignement()
   * @generated
   */
  EAttribute getDatabaseMetaInfoAssignement_DbMetaInfo();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DriverMetaInfoAssignement <em>Driver Meta Info Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Driver Meta Info Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.DriverMetaInfoAssignement
   * @generated
   */
  EClass getDriverMetaInfoAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DriverMetaInfoAssignement#getDbDriverInfo <em>Db Driver Info</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Driver Info</em>'.
   * @see org.sqlproc.dsl.processorDsl.DriverMetaInfoAssignement#getDbDriverInfo()
   * @see #getDriverMetaInfoAssignement()
   * @generated
   */
  EAttribute getDriverMetaInfoAssignement_DbDriverInfo();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DriverMethodOutputAssignement <em>Driver Method Output Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Driver Method Output Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.DriverMethodOutputAssignement
   * @generated
   */
  EClass getDriverMethodOutputAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DriverMethodOutputAssignement#getDriverMethod <em>Driver Method</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Driver Method</em>'.
   * @see org.sqlproc.dsl.processorDsl.DriverMethodOutputAssignement#getDriverMethod()
   * @see #getDriverMethodOutputAssignement()
   * @generated
   */
  EAttribute getDriverMethodOutputAssignement_DriverMethod();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DriverMethodOutputAssignement#getCallOutput <em>Call Output</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Call Output</em>'.
   * @see org.sqlproc.dsl.processorDsl.DriverMethodOutputAssignement#getCallOutput()
   * @see #getDriverMethodOutputAssignement()
   * @generated
   */
  EAttribute getDriverMethodOutputAssignement_CallOutput();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.SqlTypeAssignement <em>Sql Type Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Sql Type Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.SqlTypeAssignement
   * @generated
   */
  EClass getSqlTypeAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.SqlTypeAssignement#getTypeName <em>Type Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.SqlTypeAssignement#getTypeName()
   * @see #getSqlTypeAssignement()
   * @generated
   */
  EAttribute getSqlTypeAssignement_TypeName();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.SqlTypeAssignement#getSize <em>Size</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Size</em>'.
   * @see org.sqlproc.dsl.processorDsl.SqlTypeAssignement#getSize()
   * @see #getSqlTypeAssignement()
   * @generated
   */
  EAttribute getSqlTypeAssignement_Size();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.SqlTypeAssignement#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.SqlTypeAssignement#getType()
   * @see #getSqlTypeAssignement()
   * @generated
   */
  EReference getSqlTypeAssignement_Type();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ColumnTypeAssignement <em>Column Type Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Column Type Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.ColumnTypeAssignement
   * @generated
   */
  EClass getColumnTypeAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ColumnTypeAssignement#getDbColumn <em>Db Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Column</em>'.
   * @see org.sqlproc.dsl.processorDsl.ColumnTypeAssignement#getDbColumn()
   * @see #getColumnTypeAssignement()
   * @generated
   */
  EAttribute getColumnTypeAssignement_DbColumn();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.ColumnTypeAssignement#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.ColumnTypeAssignement#getType()
   * @see #getColumnTypeAssignement()
   * @generated
   */
  EReference getColumnTypeAssignement_Type();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ShowColumnTypeAssignement <em>Show Column Type Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Show Column Type Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.ShowColumnTypeAssignement
   * @generated
   */
  EClass getShowColumnTypeAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ShowColumnTypeAssignement#getDbColumn <em>Db Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Column</em>'.
   * @see org.sqlproc.dsl.processorDsl.ShowColumnTypeAssignement#getDbColumn()
   * @see #getShowColumnTypeAssignement()
   * @generated
   */
  EAttribute getShowColumnTypeAssignement_DbColumn();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ShowColumnTypeAssignement#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.ShowColumnTypeAssignement#getType()
   * @see #getShowColumnTypeAssignement()
   * @generated
   */
  EAttribute getShowColumnTypeAssignement_Type();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.TableAssignement <em>Table Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Table Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.TableAssignement
   * @generated
   */
  EClass getTableAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.TableAssignement#getDbTable <em>Db Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Table</em>'.
   * @see org.sqlproc.dsl.processorDsl.TableAssignement#getDbTable()
   * @see #getTableAssignement()
   * @generated
   */
  EAttribute getTableAssignement_DbTable();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.TableAssignement#getNewName <em>New Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>New Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.TableAssignement#getNewName()
   * @see #getTableAssignement()
   * @generated
   */
  EAttribute getTableAssignement_NewName();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.JoinTableAssignement <em>Join Table Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Join Table Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.JoinTableAssignement
   * @generated
   */
  EClass getJoinTableAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.JoinTableAssignement#getDbTable <em>Db Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Table</em>'.
   * @see org.sqlproc.dsl.processorDsl.JoinTableAssignement#getDbTable()
   * @see #getJoinTableAssignement()
   * @generated
   */
  EAttribute getJoinTableAssignement_DbTable();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.JoinTableAssignement#getDbTables <em>Db Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.JoinTableAssignement#getDbTables()
   * @see #getJoinTableAssignement()
   * @generated
   */
  EAttribute getJoinTableAssignement_DbTables();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ColumnAssignement <em>Column Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Column Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.ColumnAssignement
   * @generated
   */
  EClass getColumnAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ColumnAssignement#getDbColumn <em>Db Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Column</em>'.
   * @see org.sqlproc.dsl.processorDsl.ColumnAssignement#getDbColumn()
   * @see #getColumnAssignement()
   * @generated
   */
  EAttribute getColumnAssignement_DbColumn();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ColumnAssignement#getNewName <em>New Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>New Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.ColumnAssignement#getNewName()
   * @see #getColumnAssignement()
   * @generated
   */
  EAttribute getColumnAssignement_NewName();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ImportAssignement <em>Import Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Import Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.ImportAssignement
   * @generated
   */
  EClass getImportAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ImportAssignement#getDbColumn <em>Db Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Column</em>'.
   * @see org.sqlproc.dsl.processorDsl.ImportAssignement#getDbColumn()
   * @see #getImportAssignement()
   * @generated
   */
  EAttribute getImportAssignement_DbColumn();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ImportAssignement#getPkTable <em>Pk Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pk Table</em>'.
   * @see org.sqlproc.dsl.processorDsl.ImportAssignement#getPkTable()
   * @see #getImportAssignement()
   * @generated
   */
  EAttribute getImportAssignement_PkTable();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ImportAssignement#getPkColumn <em>Pk Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pk Column</em>'.
   * @see org.sqlproc.dsl.processorDsl.ImportAssignement#getPkColumn()
   * @see #getImportAssignement()
   * @generated
   */
  EAttribute getImportAssignement_PkColumn();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ExportAssignement <em>Export Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Export Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExportAssignement
   * @generated
   */
  EClass getExportAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ExportAssignement#getDbColumn <em>Db Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Column</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExportAssignement#getDbColumn()
   * @see #getExportAssignement()
   * @generated
   */
  EAttribute getExportAssignement_DbColumn();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ExportAssignement#getFkTable <em>Fk Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Fk Table</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExportAssignement#getFkTable()
   * @see #getExportAssignement()
   * @generated
   */
  EAttribute getExportAssignement_FkTable();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ExportAssignement#getFkColumn <em>Fk Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Fk Column</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExportAssignement#getFkColumn()
   * @see #getExportAssignement()
   * @generated
   */
  EAttribute getExportAssignement_FkColumn();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.InheritanceAssignement <em>Inheritance Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Inheritance Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.InheritanceAssignement
   * @generated
   */
  EClass getInheritanceAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.InheritanceAssignement#getDiscriminator <em>Discriminator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Discriminator</em>'.
   * @see org.sqlproc.dsl.processorDsl.InheritanceAssignement#getDiscriminator()
   * @see #getInheritanceAssignement()
   * @generated
   */
  EAttribute getInheritanceAssignement_Discriminator();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.InheritanceAssignement#getDbTable <em>Db Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Table</em>'.
   * @see org.sqlproc.dsl.processorDsl.InheritanceAssignement#getDbTable()
   * @see #getInheritanceAssignement()
   * @generated
   */
  EAttribute getInheritanceAssignement_DbTable();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.InheritanceAssignement#getDbColumns <em>Db Columns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Columns</em>'.
   * @see org.sqlproc.dsl.processorDsl.InheritanceAssignement#getDbColumns()
   * @see #getInheritanceAssignement()
   * @generated
   */
  EAttribute getInheritanceAssignement_DbColumns();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ManyToManyAssignement <em>Many To Many Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Many To Many Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.ManyToManyAssignement
   * @generated
   */
  EClass getManyToManyAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ManyToManyAssignement#getPkColumn <em>Pk Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pk Column</em>'.
   * @see org.sqlproc.dsl.processorDsl.ManyToManyAssignement#getPkColumn()
   * @see #getManyToManyAssignement()
   * @generated
   */
  EAttribute getManyToManyAssignement_PkColumn();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ManyToManyAssignement#getPkTable <em>Pk Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pk Table</em>'.
   * @see org.sqlproc.dsl.processorDsl.ManyToManyAssignement#getPkTable()
   * @see #getManyToManyAssignement()
   * @generated
   */
  EAttribute getManyToManyAssignement_PkTable();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ManyToManyAssignement#getFkColumn <em>Fk Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Fk Column</em>'.
   * @see org.sqlproc.dsl.processorDsl.ManyToManyAssignement#getFkColumn()
   * @see #getManyToManyAssignement()
   * @generated
   */
  EAttribute getManyToManyAssignement_FkColumn();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DebugLevelAssignement <em>Debug Level Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Debug Level Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.DebugLevelAssignement
   * @generated
   */
  EClass getDebugLevelAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DebugLevelAssignement#getDebug <em>Debug</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Debug</em>'.
   * @see org.sqlproc.dsl.processorDsl.DebugLevelAssignement#getDebug()
   * @see #getDebugLevelAssignement()
   * @generated
   */
  EAttribute getDebugLevelAssignement_Debug();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DebugLevelAssignement#getScope <em>Scope</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Scope</em>'.
   * @see org.sqlproc.dsl.processorDsl.DebugLevelAssignement#getScope()
   * @see #getDebugLevelAssignement()
   * @generated
   */
  EAttribute getDebugLevelAssignement_Scope();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ProcedurePojoAssignement <em>Procedure Pojo Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Procedure Pojo Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.ProcedurePojoAssignement
   * @generated
   */
  EClass getProcedurePojoAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ProcedurePojoAssignement#getDbProcedure <em>Db Procedure</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Procedure</em>'.
   * @see org.sqlproc.dsl.processorDsl.ProcedurePojoAssignement#getDbProcedure()
   * @see #getProcedurePojoAssignement()
   * @generated
   */
  EAttribute getProcedurePojoAssignement_DbProcedure();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.ProcedurePojoAssignement#getPojo <em>Pojo</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pojo</em>'.
   * @see org.sqlproc.dsl.processorDsl.ProcedurePojoAssignement#getPojo()
   * @see #getProcedurePojoAssignement()
   * @generated
   */
  EReference getProcedurePojoAssignement_Pojo();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.FunctionPojoAssignement <em>Function Pojo Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Pojo Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.FunctionPojoAssignement
   * @generated
   */
  EClass getFunctionPojoAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.FunctionPojoAssignement#getDbFunction <em>Db Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Function</em>'.
   * @see org.sqlproc.dsl.processorDsl.FunctionPojoAssignement#getDbFunction()
   * @see #getFunctionPojoAssignement()
   * @generated
   */
  EAttribute getFunctionPojoAssignement_DbFunction();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.FunctionPojoAssignement#getPojo <em>Pojo</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pojo</em>'.
   * @see org.sqlproc.dsl.processorDsl.FunctionPojoAssignement#getPojo()
   * @see #getFunctionPojoAssignement()
   * @generated
   */
  EReference getFunctionPojoAssignement_Pojo();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ImplementsAssignement <em>Implements Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Implements Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.ImplementsAssignement
   * @generated
   */
  EClass getImplementsAssignement();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.ImplementsAssignement#getToImplement <em>To Implement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>To Implement</em>'.
   * @see org.sqlproc.dsl.processorDsl.ImplementsAssignement#getToImplement()
   * @see #getImplementsAssignement()
   * @generated
   */
  EReference getImplementsAssignement_ToImplement();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.ImplementsAssignement#getDbTables <em>Db Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.ImplementsAssignement#getDbTables()
   * @see #getImplementsAssignement()
   * @generated
   */
  EAttribute getImplementsAssignement_DbTables();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.ImplementsAssignement#getDbNotTables <em>Db Not Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Not Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.ImplementsAssignement#getDbNotTables()
   * @see #getImplementsAssignement()
   * @generated
   */
  EAttribute getImplementsAssignement_DbNotTables();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ExtendsAssignement <em>Extends Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Extends Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendsAssignement
   * @generated
   */
  EClass getExtendsAssignement();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.ExtendsAssignement#getToExtends <em>To Extends</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>To Extends</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendsAssignement#getToExtends()
   * @see #getExtendsAssignement()
   * @generated
   */
  EReference getExtendsAssignement_ToExtends();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.ExtendsAssignement#getDbTables <em>Db Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendsAssignement#getDbTables()
   * @see #getExtendsAssignement()
   * @generated
   */
  EAttribute getExtendsAssignement_DbTables();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.ExtendsAssignement#getDbNotTables <em>Db Not Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Not Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendsAssignement#getDbNotTables()
   * @see #getExtendsAssignement()
   * @generated
   */
  EAttribute getExtendsAssignement_DbNotTables();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ImplementsAssignementGenerics <em>Implements Assignement Generics</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Implements Assignement Generics</em>'.
   * @see org.sqlproc.dsl.processorDsl.ImplementsAssignementGenerics
   * @generated
   */
  EClass getImplementsAssignementGenerics();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.ImplementsAssignementGenerics#getToImplement <em>To Implement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>To Implement</em>'.
   * @see org.sqlproc.dsl.processorDsl.ImplementsAssignementGenerics#getToImplement()
   * @see #getImplementsAssignementGenerics()
   * @generated
   */
  EReference getImplementsAssignementGenerics_ToImplement();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.ImplementsAssignementGenerics#getDbTables <em>Db Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.ImplementsAssignementGenerics#getDbTables()
   * @see #getImplementsAssignementGenerics()
   * @generated
   */
  EAttribute getImplementsAssignementGenerics_DbTables();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.ImplementsAssignementGenerics#getDbNotTables <em>Db Not Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Not Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.ImplementsAssignementGenerics#getDbNotTables()
   * @see #getImplementsAssignementGenerics()
   * @generated
   */
  EAttribute getImplementsAssignementGenerics_DbNotTables();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ExtendsAssignementGenerics <em>Extends Assignement Generics</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Extends Assignement Generics</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendsAssignementGenerics
   * @generated
   */
  EClass getExtendsAssignementGenerics();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.ExtendsAssignementGenerics#getToExtends <em>To Extends</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>To Extends</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendsAssignementGenerics#getToExtends()
   * @see #getExtendsAssignementGenerics()
   * @generated
   */
  EReference getExtendsAssignementGenerics_ToExtends();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.ExtendsAssignementGenerics#getDbTables <em>Db Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendsAssignementGenerics#getDbTables()
   * @see #getExtendsAssignementGenerics()
   * @generated
   */
  EAttribute getExtendsAssignementGenerics_DbTables();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.ExtendsAssignementGenerics#getDbNotTables <em>Db Not Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Not Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendsAssignementGenerics#getDbNotTables()
   * @see #getExtendsAssignementGenerics()
   * @generated
   */
  EAttribute getExtendsAssignementGenerics_DbNotTables();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.Property <em>Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property</em>'.
   * @see org.sqlproc.dsl.processorDsl.Property
   * @generated
   */
  EClass getProperty();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.Property#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.Property#getName()
   * @see #getProperty()
   * @generated
   */
  EAttribute getProperty_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.Property#getDatabase <em>Database</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Database</em>'.
   * @see org.sqlproc.dsl.processorDsl.Property#getDatabase()
   * @see #getProperty()
   * @generated
   */
  EReference getProperty_Database();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.Property#getPojogen <em>Pojogen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pojogen</em>'.
   * @see org.sqlproc.dsl.processorDsl.Property#getPojogen()
   * @see #getProperty()
   * @generated
   */
  EReference getProperty_Pojogen();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.Property#getMetagen <em>Metagen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Metagen</em>'.
   * @see org.sqlproc.dsl.processorDsl.Property#getMetagen()
   * @see #getProperty()
   * @generated
   */
  EReference getProperty_Metagen();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.Property#getDaogen <em>Daogen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Daogen</em>'.
   * @see org.sqlproc.dsl.processorDsl.Property#getDaogen()
   * @see #getProperty()
   * @generated
   */
  EReference getProperty_Daogen();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.Property#getReplaceId <em>Replace Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Replace Id</em>'.
   * @see org.sqlproc.dsl.processorDsl.Property#getReplaceId()
   * @see #getProperty()
   * @generated
   */
  EAttribute getProperty_ReplaceId();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.Property#getRegex <em>Regex</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Regex</em>'.
   * @see org.sqlproc.dsl.processorDsl.Property#getRegex()
   * @see #getProperty()
   * @generated
   */
  EAttribute getProperty_Regex();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.Property#getReplacement <em>Replacement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Replacement</em>'.
   * @see org.sqlproc.dsl.processorDsl.Property#getReplacement()
   * @see #getProperty()
   * @generated
   */
  EAttribute getProperty_Replacement();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty <em>Database Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Database Property</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty
   * @generated
   */
  EClass getDatabaseProperty();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getName()
   * @see #getDatabaseProperty()
   * @generated
   */
  EAttribute getDatabaseProperty_Name();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbUrl <em>Db Url</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Url</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbUrl()
   * @see #getDatabaseProperty()
   * @generated
   */
  EAttribute getDatabaseProperty_DbUrl();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbUsername <em>Db Username</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Username</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbUsername()
   * @see #getDatabaseProperty()
   * @generated
   */
  EAttribute getDatabaseProperty_DbUsername();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbPassword <em>Db Password</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Password</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbPassword()
   * @see #getDatabaseProperty()
   * @generated
   */
  EAttribute getDatabaseProperty_DbPassword();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbCatalog <em>Db Catalog</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Db Catalog</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbCatalog()
   * @see #getDatabaseProperty()
   * @generated
   */
  EReference getDatabaseProperty_DbCatalog();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbSchema <em>Db Schema</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Db Schema</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbSchema()
   * @see #getDatabaseProperty()
   * @generated
   */
  EReference getDatabaseProperty_DbSchema();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbDriverx <em>Db Driverx</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Db Driverx</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbDriverx()
   * @see #getDatabaseProperty()
   * @generated
   */
  EReference getDatabaseProperty_DbDriverx();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbDriver <em>Db Driver</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Driver</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbDriver()
   * @see #getDatabaseProperty()
   * @generated
   */
  EAttribute getDatabaseProperty_DbDriver();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbExecuteBefore <em>Db Execute Before</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Execute Before</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbExecuteBefore()
   * @see #getDatabaseProperty()
   * @generated
   */
  EAttribute getDatabaseProperty_DbExecuteBefore();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbExecuteAfter <em>Db Execute After</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Execute After</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbExecuteAfter()
   * @see #getDatabaseProperty()
   * @generated
   */
  EAttribute getDatabaseProperty_DbExecuteAfter();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbIndexTypes <em>Db Index Types</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Index Types</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbIndexTypes()
   * @see #getDatabaseProperty()
   * @generated
   */
  EAttribute getDatabaseProperty_DbIndexTypes();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbType <em>Db Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Db Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbType()
   * @see #getDatabaseProperty()
   * @generated
   */
  EReference getDatabaseProperty_DbType();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbMetaInfo <em>Db Meta Info</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Db Meta Info</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbMetaInfo()
   * @see #getDatabaseProperty()
   * @generated
   */
  EReference getDatabaseProperty_DbMetaInfo();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbDriverInfo <em>Db Driver Info</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Db Driver Info</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbDriverInfo()
   * @see #getDatabaseProperty()
   * @generated
   */
  EReference getDatabaseProperty_DbDriverInfo();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbMethodsCalls <em>Db Methods Calls</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Db Methods Calls</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getDbMethodsCalls()
   * @see #getDatabaseProperty()
   * @generated
   */
  EReference getDatabaseProperty_DbMethodsCalls();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DatabaseProperty#getDebug <em>Debug</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Debug</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseProperty#getDebug()
   * @see #getDatabaseProperty()
   * @generated
   */
  EReference getDatabaseProperty_Debug();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojogenProperty <em>Pojogen Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojogen Property</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty
   * @generated
   */
  EClass getPojogenProperty();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getName()
   * @see #getPojogenProperty()
   * @generated
   */
  EAttribute getPojogenProperty_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getSqlTypes <em>Sql Types</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Sql Types</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getSqlTypes()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_SqlTypes();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getDbTable <em>Db Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Table</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getDbTable()
   * @see #getPojogenProperty()
   * @generated
   */
  EAttribute getPojogenProperty_DbTable();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getColumnTypes <em>Column Types</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Column Types</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getColumnTypes()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_ColumnTypes();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getDbProcedure <em>Db Procedure</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Procedure</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getDbProcedure()
   * @see #getPojogenProperty()
   * @generated
   */
  EAttribute getPojogenProperty_DbProcedure();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getDbFunction <em>Db Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Function</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getDbFunction()
   * @see #getPojogenProperty()
   * @generated
   */
  EAttribute getPojogenProperty_DbFunction();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getColumnType <em>Column Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Column Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getColumnType()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_ColumnType();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getDbTables <em>Db Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getDbTables()
   * @see #getPojogenProperty()
   * @generated
   */
  EAttribute getPojogenProperty_DbTables();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getJoinTables <em>Join Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Join Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getJoinTables()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_JoinTables();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getDbColumns <em>Db Columns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Columns</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getDbColumns()
   * @see #getPojogenProperty()
   * @generated
   */
  EAttribute getPojogenProperty_DbColumns();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getTables <em>Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getTables()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_Tables();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getColumns <em>Columns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Columns</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getColumns()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_Columns();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getExports <em>Exports</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Exports</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getExports()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_Exports();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getImports <em>Imports</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Imports</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getImports()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_Imports();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getMany2s <em>Many2s</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Many2s</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getMany2s()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_Many2s();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getDbColumn <em>Db Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Column</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getDbColumn()
   * @see #getPojogenProperty()
   * @generated
   */
  EAttribute getPojogenProperty_DbColumn();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getInheritance <em>Inheritance</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Inheritance</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getInheritance()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_Inheritance();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getMethods <em>Methods</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Methods</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getMethods()
   * @see #getPojogenProperty()
   * @generated
   */
  EAttribute getPojogenProperty_Methods();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getOperatorsSuffix <em>Operators Suffix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operators Suffix</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getOperatorsSuffix()
   * @see #getPojogenProperty()
   * @generated
   */
  EAttribute getPojogenProperty_OperatorsSuffix();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getToImplements <em>To Implements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>To Implements</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getToImplements()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_ToImplements();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getToExtends <em>To Extends</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>To Extends</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getToExtends()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_ToExtends();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getToImplementsGenerics <em>To Implements Generics</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>To Implements Generics</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getToImplementsGenerics()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_ToImplementsGenerics();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getToExtendsGenerics <em>To Extends Generics</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>To Extends Generics</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getToExtendsGenerics()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_ToExtendsGenerics();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getVersion <em>Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Version</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getVersion()
   * @see #getPojogenProperty()
   * @generated
   */
  EAttribute getPojogenProperty_Version();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getDbNotTables <em>Db Not Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Not Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getDbNotTables()
   * @see #getPojogenProperty()
   * @generated
   */
  EAttribute getPojogenProperty_DbNotTables();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getDebug <em>Debug</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Debug</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getDebug()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_Debug();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getProcPojos <em>Proc Pojos</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Proc Pojos</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getProcPojos()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_ProcPojos();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getFunPojos <em>Fun Pojos</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Fun Pojos</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getFunPojos()
   * @see #getPojogenProperty()
   * @generated
   */
  EReference getPojogenProperty_FunPojos();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getActiveFilter <em>Active Filter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Active Filter</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getActiveFilter()
   * @see #getPojogenProperty()
   * @generated
   */
  EAttribute getPojogenProperty_ActiveFilter();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getPckg <em>Pckg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pckg</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getPckg()
   * @see #getPojogenProperty()
   * @generated
   */
  EAttribute getPojogenProperty_Pckg();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getEnumName <em>Enum Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Enum Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getEnumName()
   * @see #getPojogenProperty()
   * @generated
   */
  EAttribute getPojogenProperty_EnumName();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.PojogenProperty#getDbCheckConstraints <em>Db Check Constraints</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Check Constraints</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojogenProperty#getDbCheckConstraints()
   * @see #getPojogenProperty()
   * @generated
   */
  EAttribute getPojogenProperty_DbCheckConstraints();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.MetaTypeAssignement <em>Meta Type Assignement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Meta Type Assignement</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetaTypeAssignement
   * @generated
   */
  EClass getMetaTypeAssignement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetaTypeAssignement#getDbColumn <em>Db Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Column</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetaTypeAssignement#getDbColumn()
   * @see #getMetaTypeAssignement()
   * @generated
   */
  EAttribute getMetaTypeAssignement_DbColumn();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetaTypeAssignement#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetaTypeAssignement#getType()
   * @see #getMetaTypeAssignement()
   * @generated
   */
  EAttribute getMetaTypeAssignement_Type();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetaTypeAssignement#getExtension <em>Extension</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Extension</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetaTypeAssignement#getExtension()
   * @see #getMetaTypeAssignement()
   * @generated
   */
  EAttribute getMetaTypeAssignement_Extension();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.MetagenProperty <em>Metagen Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Metagen Property</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty
   * @generated
   */
  EClass getMetagenProperty();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetagenProperty#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty#getName()
   * @see #getMetagenProperty()
   * @generated
   */
  EAttribute getMetagenProperty_Name();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetagenProperty#getSequence <em>Sequence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sequence</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty#getSequence()
   * @see #getMetagenProperty()
   * @generated
   */
  EAttribute getMetagenProperty_Sequence();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetagenProperty#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty#getType()
   * @see #getMetagenProperty()
   * @generated
   */
  EAttribute getMetagenProperty_Type();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.MetagenProperty#getDbTables <em>Db Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty#getDbTables()
   * @see #getMetagenProperty()
   * @generated
   */
  EAttribute getMetagenProperty_DbTables();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.MetagenProperty#getDbNotTables <em>Db Not Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Not Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty#getDbNotTables()
   * @see #getMetagenProperty()
   * @generated
   */
  EAttribute getMetagenProperty_DbNotTables();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetagenProperty#getDbTable <em>Db Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Table</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty#getDbTable()
   * @see #getMetagenProperty()
   * @generated
   */
  EAttribute getMetagenProperty_DbTable();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetagenProperty#getIdentity <em>Identity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identity</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty#getIdentity()
   * @see #getMetagenProperty()
   * @generated
   */
  EAttribute getMetagenProperty_Identity();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.MetagenProperty#getMetaTypes <em>Meta Types</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Meta Types</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty#getMetaTypes()
   * @see #getMetagenProperty()
   * @generated
   */
  EReference getMetagenProperty_MetaTypes();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetagenProperty#getDbStatement <em>Db Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Statement</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty#getDbStatement()
   * @see #getMetagenProperty()
   * @generated
   */
  EAttribute getMetagenProperty_DbStatement();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.MetagenProperty#getDbColumns <em>Db Columns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Columns</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty#getDbColumns()
   * @see #getMetagenProperty()
   * @generated
   */
  EAttribute getMetagenProperty_DbColumns();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetagenProperty#getDbFunction <em>Db Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Function</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty#getDbFunction()
   * @see #getMetagenProperty()
   * @generated
   */
  EAttribute getMetagenProperty_DbFunction();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetagenProperty#getDbProcedure <em>Db Procedure</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Procedure</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty#getDbProcedure()
   * @see #getMetagenProperty()
   * @generated
   */
  EAttribute getMetagenProperty_DbProcedure();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.MetagenProperty#getDebug <em>Debug</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Debug</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty#getDebug()
   * @see #getMetagenProperty()
   * @generated
   */
  EReference getMetagenProperty_Debug();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.MetagenProperty#getOptionalFeatures <em>Optional Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Optional Features</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty#getOptionalFeatures()
   * @see #getMetagenProperty()
   * @generated
   */
  EAttribute getMetagenProperty_OptionalFeatures();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetagenProperty#getActiveFilter <em>Active Filter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Active Filter</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetagenProperty#getActiveFilter()
   * @see #getMetagenProperty()
   * @generated
   */
  EAttribute getMetagenProperty_ActiveFilter();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DaogenProperty <em>Daogen Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Daogen Property</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaogenProperty
   * @generated
   */
  EClass getDaogenProperty();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DaogenProperty#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaogenProperty#getName()
   * @see #getDaogenProperty()
   * @generated
   */
  EAttribute getDaogenProperty_Name();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.DaogenProperty#getDbTables <em>Db Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Db Tables</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaogenProperty#getDbTables()
   * @see #getDaogenProperty()
   * @generated
   */
  EAttribute getDaogenProperty_DbTables();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DaogenProperty#getToImplements <em>To Implements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>To Implements</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaogenProperty#getToImplements()
   * @see #getDaogenProperty()
   * @generated
   */
  EReference getDaogenProperty_ToImplements();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DaogenProperty#getToExtends <em>To Extends</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>To Extends</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaogenProperty#getToExtends()
   * @see #getDaogenProperty()
   * @generated
   */
  EReference getDaogenProperty_ToExtends();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DaogenProperty#getToImplementsGenerics <em>To Implements Generics</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>To Implements Generics</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaogenProperty#getToImplementsGenerics()
   * @see #getDaogenProperty()
   * @generated
   */
  EReference getDaogenProperty_ToImplementsGenerics();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DaogenProperty#getToExtendsGenerics <em>To Extends Generics</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>To Extends Generics</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaogenProperty#getToExtendsGenerics()
   * @see #getDaogenProperty()
   * @generated
   */
  EReference getDaogenProperty_ToExtendsGenerics();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DaogenProperty#getDbFunction <em>Db Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Function</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaogenProperty#getDbFunction()
   * @see #getDaogenProperty()
   * @generated
   */
  EAttribute getDaogenProperty_DbFunction();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DaogenProperty#getResultType <em>Result Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Result Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaogenProperty#getResultType()
   * @see #getDaogenProperty()
   * @generated
   */
  EReference getDaogenProperty_ResultType();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DaogenProperty#getDebug <em>Debug</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Debug</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaogenProperty#getDebug()
   * @see #getDaogenProperty()
   * @generated
   */
  EReference getDaogenProperty_Debug();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DaogenProperty#getActiveFilter <em>Active Filter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Active Filter</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaogenProperty#getActiveFilter()
   * @see #getDaogenProperty()
   * @generated
   */
  EAttribute getDaogenProperty_ActiveFilter();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DaogenProperty#getPckg <em>Pckg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pckg</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaogenProperty#getPckg()
   * @see #getDaogenProperty()
   * @generated
   */
  EAttribute getDaogenProperty_Pckg();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoDefinition <em>Pojo Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Definition</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDefinition
   * @generated
   */
  EClass getPojoDefinition();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoDefinition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDefinition#getName()
   * @see #getPojoDefinition()
   * @generated
   */
  EAttribute getPojoDefinition_Name();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoDefinition#getClass_ <em>Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Class</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDefinition#getClass_()
   * @see #getPojoDefinition()
   * @generated
   */
  EAttribute getPojoDefinition_Class();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.PojoDefinition#getClassx <em>Classx</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Classx</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDefinition#getClassx()
   * @see #getPojoDefinition()
   * @generated
   */
  EReference getPojoDefinition_Classx();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.TableDefinition <em>Table Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Table Definition</em>'.
   * @see org.sqlproc.dsl.processorDsl.TableDefinition
   * @generated
   */
  EClass getTableDefinition();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.TableDefinition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.TableDefinition#getName()
   * @see #getTableDefinition()
   * @generated
   */
  EAttribute getTableDefinition_Name();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.TableDefinition#getTable <em>Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Table</em>'.
   * @see org.sqlproc.dsl.processorDsl.TableDefinition#getTable()
   * @see #getTableDefinition()
   * @generated
   */
  EAttribute getTableDefinition_Table();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ProcedureDefinition <em>Procedure Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Procedure Definition</em>'.
   * @see org.sqlproc.dsl.processorDsl.ProcedureDefinition
   * @generated
   */
  EClass getProcedureDefinition();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ProcedureDefinition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.ProcedureDefinition#getName()
   * @see #getProcedureDefinition()
   * @generated
   */
  EAttribute getProcedureDefinition_Name();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ProcedureDefinition#getTable <em>Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Table</em>'.
   * @see org.sqlproc.dsl.processorDsl.ProcedureDefinition#getTable()
   * @see #getProcedureDefinition()
   * @generated
   */
  EAttribute getProcedureDefinition_Table();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.FunctionDefinition <em>Function Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Definition</em>'.
   * @see org.sqlproc.dsl.processorDsl.FunctionDefinition
   * @generated
   */
  EClass getFunctionDefinition();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.FunctionDefinition#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.FunctionDefinition#getName()
   * @see #getFunctionDefinition()
   * @generated
   */
  EAttribute getFunctionDefinition_Name();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.FunctionDefinition#getTable <em>Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Table</em>'.
   * @see org.sqlproc.dsl.processorDsl.FunctionDefinition#getTable()
   * @see #getFunctionDefinition()
   * @generated
   */
  EAttribute getFunctionDefinition_Table();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.MetaStatement <em>Meta Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Meta Statement</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetaStatement
   * @generated
   */
  EClass getMetaStatement();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetaStatement#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetaStatement#getName()
   * @see #getMetaStatement()
   * @generated
   */
  EAttribute getMetaStatement_Name();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetaStatement#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetaStatement#getType()
   * @see #getMetaStatement()
   * @generated
   */
  EAttribute getMetaStatement_Type();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.MetaStatement#getModifiers <em>Modifiers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Modifiers</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetaStatement#getModifiers()
   * @see #getMetaStatement()
   * @generated
   */
  EAttribute getMetaStatement_Modifiers();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.MetaStatement#getStatement <em>Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statement</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetaStatement#getStatement()
   * @see #getMetaStatement()
   * @generated
   */
  EReference getMetaStatement_Statement();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.Sql <em>Sql</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Sql</em>'.
   * @see org.sqlproc.dsl.processorDsl.Sql
   * @generated
   */
  EClass getSql();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Sql#getSqls <em>Sqls</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Sqls</em>'.
   * @see org.sqlproc.dsl.processorDsl.Sql#getSqls()
   * @see #getSql()
   * @generated
   */
  EReference getSql_Sqls();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.SqlFragment <em>Sql Fragment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Sql Fragment</em>'.
   * @see org.sqlproc.dsl.processorDsl.SqlFragment
   * @generated
   */
  EClass getSqlFragment();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.SqlFragment#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.sqlproc.dsl.processorDsl.SqlFragment#getValue()
   * @see #getSqlFragment()
   * @generated
   */
  EAttribute getSqlFragment_Value();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.SqlFragment#getCol <em>Col</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Col</em>'.
   * @see org.sqlproc.dsl.processorDsl.SqlFragment#getCol()
   * @see #getSqlFragment()
   * @generated
   */
  EReference getSqlFragment_Col();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.SqlFragment#getCnst <em>Cnst</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cnst</em>'.
   * @see org.sqlproc.dsl.processorDsl.SqlFragment#getCnst()
   * @see #getSqlFragment()
   * @generated
   */
  EReference getSqlFragment_Cnst();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.SqlFragment#getIdent <em>Ident</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ident</em>'.
   * @see org.sqlproc.dsl.processorDsl.SqlFragment#getIdent()
   * @see #getSqlFragment()
   * @generated
   */
  EReference getSqlFragment_Ident();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.SqlFragment#getCnstOper <em>Cnst Oper</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cnst Oper</em>'.
   * @see org.sqlproc.dsl.processorDsl.SqlFragment#getCnstOper()
   * @see #getSqlFragment()
   * @generated
   */
  EReference getSqlFragment_CnstOper();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.SqlFragment#getIdentOper <em>Ident Oper</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ident Oper</em>'.
   * @see org.sqlproc.dsl.processorDsl.SqlFragment#getIdentOper()
   * @see #getSqlFragment()
   * @generated
   */
  EReference getSqlFragment_IdentOper();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.SqlFragment#getMeta <em>Meta</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Meta</em>'.
   * @see org.sqlproc.dsl.processorDsl.SqlFragment#getMeta()
   * @see #getSqlFragment()
   * @generated
   */
  EReference getSqlFragment_Meta();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.SqlFragment#getDbtab <em>Dbtab</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dbtab</em>'.
   * @see org.sqlproc.dsl.processorDsl.SqlFragment#getDbtab()
   * @see #getSqlFragment()
   * @generated
   */
  EReference getSqlFragment_Dbtab();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.SqlFragment#getDbcol <em>Dbcol</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dbcol</em>'.
   * @see org.sqlproc.dsl.processorDsl.SqlFragment#getDbcol()
   * @see #getSqlFragment()
   * @generated
   */
  EReference getSqlFragment_Dbcol();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.MetaSql <em>Meta Sql</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Meta Sql</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetaSql
   * @generated
   */
  EClass getMetaSql();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.MetaSql#getIfs <em>Ifs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Ifs</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetaSql#getIfs()
   * @see #getMetaSql()
   * @generated
   */
  EReference getMetaSql_Ifs();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetaSql#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetaSql#getType()
   * @see #getMetaSql()
   * @generated
   */
  EAttribute getMetaSql_Type();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.MetaSql#getCond <em>Cond</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cond</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetaSql#getCond()
   * @see #getMetaSql()
   * @generated
   */
  EReference getMetaSql_Cond();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MetaSql#getFtype <em>Ftype</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ftype</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetaSql#getFtype()
   * @see #getMetaSql()
   * @generated
   */
  EAttribute getMetaSql_Ftype();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.MetaSql#getOrd <em>Ord</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ord</em>'.
   * @see org.sqlproc.dsl.processorDsl.MetaSql#getOrd()
   * @see #getMetaSql()
   * @generated
   */
  EReference getMetaSql_Ord();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.IfSql <em>If Sql</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>If Sql</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSql
   * @generated
   */
  EClass getIfSql();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.IfSql#getSqls <em>Sqls</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Sqls</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSql#getSqls()
   * @see #getIfSql()
   * @generated
   */
  EReference getIfSql_Sqls();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.IfSqlFragment <em>If Sql Fragment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>If Sql Fragment</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlFragment
   * @generated
   */
  EClass getIfSqlFragment();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.IfSqlFragment#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlFragment#getValue()
   * @see #getIfSqlFragment()
   * @generated
   */
  EAttribute getIfSqlFragment_Value();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.IfSqlFragment#getCol <em>Col</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Col</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlFragment#getCol()
   * @see #getIfSqlFragment()
   * @generated
   */
  EReference getIfSqlFragment_Col();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.IfSqlFragment#getCnst <em>Cnst</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cnst</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlFragment#getCnst()
   * @see #getIfSqlFragment()
   * @generated
   */
  EReference getIfSqlFragment_Cnst();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.IfSqlFragment#getIdent <em>Ident</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ident</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlFragment#getIdent()
   * @see #getIfSqlFragment()
   * @generated
   */
  EReference getIfSqlFragment_Ident();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.IfSqlFragment#getCnstOper <em>Cnst Oper</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cnst Oper</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlFragment#getCnstOper()
   * @see #getIfSqlFragment()
   * @generated
   */
  EReference getIfSqlFragment_CnstOper();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.IfSqlFragment#getIdentOper <em>Ident Oper</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ident Oper</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlFragment#getIdentOper()
   * @see #getIfSqlFragment()
   * @generated
   */
  EReference getIfSqlFragment_IdentOper();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.IfSqlFragment#getDbtab <em>Dbtab</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dbtab</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlFragment#getDbtab()
   * @see #getIfSqlFragment()
   * @generated
   */
  EReference getIfSqlFragment_Dbtab();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.IfSqlFragment#getDbcol <em>Dbcol</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dbcol</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlFragment#getDbcol()
   * @see #getIfSqlFragment()
   * @generated
   */
  EReference getIfSqlFragment_Dbcol();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.IfSqlFragment#getMeta <em>Meta</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Meta</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlFragment#getMeta()
   * @see #getIfSqlFragment()
   * @generated
   */
  EReference getIfSqlFragment_Meta();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.IfMetaSql <em>If Meta Sql</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>If Meta Sql</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfMetaSql
   * @generated
   */
  EClass getIfMetaSql();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.IfMetaSql#getIfs <em>Ifs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Ifs</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfMetaSql#getIfs()
   * @see #getIfMetaSql()
   * @generated
   */
  EReference getIfMetaSql_Ifs();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.IfMetaSql#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfMetaSql#getType()
   * @see #getIfMetaSql()
   * @generated
   */
  EAttribute getIfMetaSql_Type();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.IfMetaSql#getCond <em>Cond</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cond</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfMetaSql#getCond()
   * @see #getIfMetaSql()
   * @generated
   */
  EReference getIfMetaSql_Cond();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.IfSqlCond <em>If Sql Cond</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>If Sql Cond</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlCond
   * @generated
   */
  EClass getIfSqlCond();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.IfSqlCond#getBool1 <em>Bool1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Bool1</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlCond#getBool1()
   * @see #getIfSqlCond()
   * @generated
   */
  EReference getIfSqlCond_Bool1();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.IfSqlCond#getOper <em>Oper</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Oper</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlCond#getOper()
   * @see #getIfSqlCond()
   * @generated
   */
  EAttribute getIfSqlCond_Oper();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.IfSqlCond#getBool2 <em>Bool2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Bool2</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlCond#getBool2()
   * @see #getIfSqlCond()
   * @generated
   */
  EReference getIfSqlCond_Bool2();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.IfSqlBool <em>If Sql Bool</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>If Sql Bool</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlBool
   * @generated
   */
  EClass getIfSqlBool();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.IfSqlBool#isNot <em>Not</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Not</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlBool#isNot()
   * @see #getIfSqlBool()
   * @generated
   */
  EAttribute getIfSqlBool_Not();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.IfSqlBool#getCnst <em>Cnst</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cnst</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlBool#getCnst()
   * @see #getIfSqlBool()
   * @generated
   */
  EReference getIfSqlBool_Cnst();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.IfSqlBool#getIdent <em>Ident</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ident</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlBool#getIdent()
   * @see #getIfSqlBool()
   * @generated
   */
  EReference getIfSqlBool_Ident();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.IfSqlBool#getCond <em>Cond</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cond</em>'.
   * @see org.sqlproc.dsl.processorDsl.IfSqlBool#getCond()
   * @see #getIfSqlBool()
   * @generated
   */
  EReference getIfSqlBool_Cond();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.OrdSql <em>Ord Sql</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ord Sql</em>'.
   * @see org.sqlproc.dsl.processorDsl.OrdSql
   * @generated
   */
  EClass getOrdSql();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.OrdSql#getSqls <em>Sqls</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Sqls</em>'.
   * @see org.sqlproc.dsl.processorDsl.OrdSql#getSqls()
   * @see #getOrdSql()
   * @generated
   */
  EReference getOrdSql_Sqls();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.OrdSql2 <em>Ord Sql2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ord Sql2</em>'.
   * @see org.sqlproc.dsl.processorDsl.OrdSql2
   * @generated
   */
  EClass getOrdSql2();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.OrdSql2#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.sqlproc.dsl.processorDsl.OrdSql2#getValue()
   * @see #getOrdSql2()
   * @generated
   */
  EAttribute getOrdSql2_Value();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.OrdSql2#getCnst <em>Cnst</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cnst</em>'.
   * @see org.sqlproc.dsl.processorDsl.OrdSql2#getCnst()
   * @see #getOrdSql2()
   * @generated
   */
  EReference getOrdSql2_Cnst();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.OrdSql2#getIdent <em>Ident</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ident</em>'.
   * @see org.sqlproc.dsl.processorDsl.OrdSql2#getIdent()
   * @see #getOrdSql2()
   * @generated
   */
  EReference getOrdSql2_Ident();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.OrdSql2#getDbcol <em>Dbcol</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dbcol</em>'.
   * @see org.sqlproc.dsl.processorDsl.OrdSql2#getDbcol()
   * @see #getOrdSql2()
   * @generated
   */
  EReference getOrdSql2_Dbcol();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.Column <em>Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Column</em>'.
   * @see org.sqlproc.dsl.processorDsl.Column
   * @generated
   */
  EClass getColumn();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Column#getColumns <em>Columns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Columns</em>'.
   * @see org.sqlproc.dsl.processorDsl.Column#getColumns()
   * @see #getColumn()
   * @generated
   */
  EReference getColumn_Columns();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ExtendedColumn <em>Extended Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Extended Column</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendedColumn
   * @generated
   */
  EClass getExtendedColumn();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.ExtendedColumn#getCol <em>Col</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Col</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendedColumn#getCol()
   * @see #getExtendedColumn()
   * @generated
   */
  EReference getExtendedColumn_Col();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.ExtendedColumn#getModifiers <em>Modifiers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Modifiers</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendedColumn#getModifiers()
   * @see #getExtendedColumn()
   * @generated
   */
  EAttribute getExtendedColumn_Modifiers();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ExtendedColumnName <em>Extended Column Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Extended Column Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendedColumnName
   * @generated
   */
  EClass getExtendedColumnName();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ExtendedColumnName#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendedColumnName#getName()
   * @see #getExtendedColumnName()
   * @generated
   */
  EAttribute getExtendedColumnName_Name();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.Constant <em>Constant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Constant</em>'.
   * @see org.sqlproc.dsl.processorDsl.Constant
   * @generated
   */
  EClass getConstant();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.Constant#getCase <em>Case</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Case</em>'.
   * @see org.sqlproc.dsl.processorDsl.Constant#getCase()
   * @see #getConstant()
   * @generated
   */
  EAttribute getConstant_Case();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.Constant#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.Constant#getName()
   * @see #getConstant()
   * @generated
   */
  EAttribute getConstant_Name();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.Constant#getModifiers <em>Modifiers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Modifiers</em>'.
   * @see org.sqlproc.dsl.processorDsl.Constant#getModifiers()
   * @see #getConstant()
   * @generated
   */
  EAttribute getConstant_Modifiers();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.Identifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Identifier</em>'.
   * @see org.sqlproc.dsl.processorDsl.Identifier
   * @generated
   */
  EClass getIdentifier();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.Identifier#getMode <em>Mode</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Mode</em>'.
   * @see org.sqlproc.dsl.processorDsl.Identifier#getMode()
   * @see #getIdentifier()
   * @generated
   */
  EAttribute getIdentifier_Mode();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.Identifier#getCase <em>Case</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Case</em>'.
   * @see org.sqlproc.dsl.processorDsl.Identifier#getCase()
   * @see #getIdentifier()
   * @generated
   */
  EAttribute getIdentifier_Case();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.Identifier#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.Identifier#getName()
   * @see #getIdentifier()
   * @generated
   */
  EAttribute getIdentifier_Name();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.Identifier#getModifiers <em>Modifiers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Modifiers</em>'.
   * @see org.sqlproc.dsl.processorDsl.Identifier#getModifiers()
   * @see #getIdentifier()
   * @generated
   */
  EAttribute getIdentifier_Modifiers();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ConstantOperator <em>Constant Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Constant Operator</em>'.
   * @see org.sqlproc.dsl.processorDsl.ConstantOperator
   * @generated
   */
  EClass getConstantOperator();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.ConstantOperator#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.ConstantOperator#getName()
   * @see #getConstantOperator()
   * @generated
   */
  EAttribute getConstantOperator_Name();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.IdentifierOperator <em>Identifier Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Identifier Operator</em>'.
   * @see org.sqlproc.dsl.processorDsl.IdentifierOperator
   * @generated
   */
  EClass getIdentifierOperator();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.IdentifierOperator#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.IdentifierOperator#getName()
   * @see #getIdentifierOperator()
   * @generated
   */
  EAttribute getIdentifierOperator_Name();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DatabaseColumn <em>Database Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Database Column</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseColumn
   * @generated
   */
  EClass getDatabaseColumn();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DatabaseColumn#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseColumn#getName()
   * @see #getDatabaseColumn()
   * @generated
   */
  EAttribute getDatabaseColumn_Name();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DatabaseTable <em>Database Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Database Table</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseTable
   * @generated
   */
  EClass getDatabaseTable();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DatabaseTable#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.DatabaseTable#getName()
   * @see #getDatabaseTable()
   * @generated
   */
  EAttribute getDatabaseTable_Name();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.MappingRule <em>Mapping Rule</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mapping Rule</em>'.
   * @see org.sqlproc.dsl.processorDsl.MappingRule
   * @generated
   */
  EClass getMappingRule();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MappingRule#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.MappingRule#getName()
   * @see #getMappingRule()
   * @generated
   */
  EAttribute getMappingRule_Name();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MappingRule#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.MappingRule#getType()
   * @see #getMappingRule()
   * @generated
   */
  EAttribute getMappingRule_Type();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.MappingRule#getModifiers <em>Modifiers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Modifiers</em>'.
   * @see org.sqlproc.dsl.processorDsl.MappingRule#getModifiers()
   * @see #getMappingRule()
   * @generated
   */
  EAttribute getMappingRule_Modifiers();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.MappingRule#getMapping <em>Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mapping</em>'.
   * @see org.sqlproc.dsl.processorDsl.MappingRule#getMapping()
   * @see #getMappingRule()
   * @generated
   */
  EReference getMappingRule_Mapping();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.Mapping <em>Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mapping</em>'.
   * @see org.sqlproc.dsl.processorDsl.Mapping
   * @generated
   */
  EClass getMapping();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Mapping#getMappingItems <em>Mapping Items</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Mapping Items</em>'.
   * @see org.sqlproc.dsl.processorDsl.Mapping#getMappingItems()
   * @see #getMapping()
   * @generated
   */
  EReference getMapping_MappingItems();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.MappingItem <em>Mapping Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mapping Item</em>'.
   * @see org.sqlproc.dsl.processorDsl.MappingItem
   * @generated
   */
  EClass getMappingItem();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MappingItem#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.MappingItem#getName()
   * @see #getMappingItem()
   * @generated
   */
  EAttribute getMappingItem_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.MappingItem#getAttr <em>Attr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Attr</em>'.
   * @see org.sqlproc.dsl.processorDsl.MappingItem#getAttr()
   * @see #getMappingItem()
   * @generated
   */
  EReference getMappingItem_Attr();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.MappingColumn <em>Mapping Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mapping Column</em>'.
   * @see org.sqlproc.dsl.processorDsl.MappingColumn
   * @generated
   */
  EClass getMappingColumn();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.MappingColumn#getItems <em>Items</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Items</em>'.
   * @see org.sqlproc.dsl.processorDsl.MappingColumn#getItems()
   * @see #getMappingColumn()
   * @generated
   */
  EReference getMappingColumn_Items();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ExtendedMappingItem <em>Extended Mapping Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Extended Mapping Item</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendedMappingItem
   * @generated
   */
  EClass getExtendedMappingItem();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.ExtendedMappingItem#getAttr <em>Attr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Attr</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendedMappingItem#getAttr()
   * @see #getExtendedMappingItem()
   * @generated
   */
  EReference getExtendedMappingItem_Attr();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.ExtendedMappingItem#getModifiers <em>Modifiers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Modifiers</em>'.
   * @see org.sqlproc.dsl.processorDsl.ExtendedMappingItem#getModifiers()
   * @see #getExtendedMappingItem()
   * @generated
   */
  EAttribute getExtendedMappingItem_Modifiers();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.MappingColumnName <em>Mapping Column Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mapping Column Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.MappingColumnName
   * @generated
   */
  EClass getMappingColumnName();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.MappingColumnName#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.MappingColumnName#getName()
   * @see #getMappingColumnName()
   * @generated
   */
  EAttribute getMappingColumnName_Name();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.OptionalFeature <em>Optional Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Optional Feature</em>'.
   * @see org.sqlproc.dsl.processorDsl.OptionalFeature
   * @generated
   */
  EClass getOptionalFeature();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.OptionalFeature#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.OptionalFeature#getName()
   * @see #getOptionalFeature()
   * @generated
   */
  EAttribute getOptionalFeature_Name();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.OptionalFeature#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.OptionalFeature#getType()
   * @see #getOptionalFeature()
   * @generated
   */
  EAttribute getOptionalFeature_Type();

  /**
   * Returns the meta object for the attribute list '{@link org.sqlproc.dsl.processorDsl.OptionalFeature#getModifiers <em>Modifiers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Modifiers</em>'.
   * @see org.sqlproc.dsl.processorDsl.OptionalFeature#getModifiers()
   * @see #getOptionalFeature()
   * @generated
   */
  EAttribute getOptionalFeature_Modifiers();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.OptionalFeature#getOption <em>Option</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Option</em>'.
   * @see org.sqlproc.dsl.processorDsl.OptionalFeature#getOption()
   * @see #getOptionalFeature()
   * @generated
   */
  EAttribute getOptionalFeature_Option();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoType <em>Pojo Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoType
   * @generated
   */
  EClass getPojoType();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.PojoType#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoType#getType()
   * @see #getPojoType()
   * @generated
   */
  EReference getPojoType_Type();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.PojoType#getRef <em>Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ref</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoType#getRef()
   * @see #getPojoType()
   * @generated
   */
  EReference getPojoType_Ref();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.PojoType#getGtype <em>Gtype</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Gtype</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoType#getGtype()
   * @see #getPojoType()
   * @generated
   */
  EReference getPojoType_Gtype();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.PojoType#getGref <em>Gref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Gref</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoType#getGref()
   * @see #getPojoType()
   * @generated
   */
  EReference getPojoType_Gref();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoType#isArray <em>Array</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Array</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoType#isArray()
   * @see #getPojoType()
   * @generated
   */
  EAttribute getPojoType_Array();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PackageDirective <em>Package Directive</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Package Directive</em>'.
   * @see org.sqlproc.dsl.processorDsl.PackageDirective
   * @generated
   */
  EClass getPackageDirective();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.Package <em>Package</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Package</em>'.
   * @see org.sqlproc.dsl.processorDsl.Package
   * @generated
   */
  EClass getPackage();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Package#getDirectives <em>Directives</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Directives</em>'.
   * @see org.sqlproc.dsl.processorDsl.Package#getDirectives()
   * @see #getPackage()
   * @generated
   */
  EReference getPackage_Directives();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.Package#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.Package#getName()
   * @see #getPackage()
   * @generated
   */
  EAttribute getPackage_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Package#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see org.sqlproc.dsl.processorDsl.Package#getElements()
   * @see #getPackage()
   * @generated
   */
  EReference getPackage_Elements();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.AnnotationDirective <em>Annotation Directive</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Annotation Directive</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotationDirective
   * @generated
   */
  EClass getAnnotationDirective();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.Annotation <em>Annotation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Annotation</em>'.
   * @see org.sqlproc.dsl.processorDsl.Annotation
   * @generated
   */
  EClass getAnnotation();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Annotation#getDirectives <em>Directives</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Directives</em>'.
   * @see org.sqlproc.dsl.processorDsl.Annotation#getDirectives()
   * @see #getAnnotation()
   * @generated
   */
  EReference getAnnotation_Directives();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.Annotation#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.Annotation#getType()
   * @see #getAnnotation()
   * @generated
   */
  EReference getAnnotation_Type();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Annotation#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see org.sqlproc.dsl.processorDsl.Annotation#getFeatures()
   * @see #getAnnotation()
   * @generated
   */
  EReference getAnnotation_Features();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.AnnotationProperty <em>Annotation Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Annotation Property</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotationProperty
   * @generated
   */
  EClass getAnnotationProperty();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.AnnotationProperty#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotationProperty#getName()
   * @see #getAnnotationProperty()
   * @generated
   */
  EAttribute getAnnotationProperty_Name();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.AnnotationProperty#getNumber <em>Number</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Number</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotationProperty#getNumber()
   * @see #getAnnotationProperty()
   * @generated
   */
  EAttribute getAnnotationProperty_Number();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.AnnotationProperty#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotationProperty#getValue()
   * @see #getAnnotationProperty()
   * @generated
   */
  EAttribute getAnnotationProperty_Value();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.AnnotationProperty#getConstant <em>Constant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Constant</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotationProperty#getConstant()
   * @see #getAnnotationProperty()
   * @generated
   */
  EAttribute getAnnotationProperty_Constant();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.AnnotationProperty#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotationProperty#getType()
   * @see #getAnnotationProperty()
   * @generated
   */
  EReference getAnnotationProperty_Type();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.AnnotationProperty#getRef <em>Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ref</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotationProperty#getRef()
   * @see #getAnnotationProperty()
   * @generated
   */
  EReference getAnnotationProperty_Ref();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.Entity <em>Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity</em>'.
   * @see org.sqlproc.dsl.processorDsl.Entity
   * @generated
   */
  EClass getEntity();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Entity#getDirectives <em>Directives</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Directives</em>'.
   * @see org.sqlproc.dsl.processorDsl.Entity#getDirectives()
   * @see #getEntity()
   * @generated
   */
  EReference getEntity_Directives();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.Entity#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.Entity#getName()
   * @see #getEntity()
   * @generated
   */
  EAttribute getEntity_Name();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.AnnotatedEntity <em>Annotated Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Annotated Entity</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotatedEntity
   * @generated
   */
  EClass getAnnotatedEntity();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.AnnotatedEntity#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Annotations</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotatedEntity#getAnnotations()
   * @see #getAnnotatedEntity()
   * @generated
   */
  EReference getAnnotatedEntity_Annotations();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.AnnotatedEntity#getEntity <em>Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Entity</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotatedEntity#getEntity()
   * @see #getAnnotatedEntity()
   * @generated
   */
  EReference getAnnotatedEntity_Entity();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.AbstractPojoEntity <em>Abstract Pojo Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Abstract Pojo Entity</em>'.
   * @see org.sqlproc.dsl.processorDsl.AbstractPojoEntity
   * @generated
   */
  EClass getAbstractPojoEntity();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.Import <em>Import</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Import</em>'.
   * @see org.sqlproc.dsl.processorDsl.Import
   * @generated
   */
  EClass getImport();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.Import#getImportedNamespace <em>Imported Namespace</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Imported Namespace</em>'.
   * @see org.sqlproc.dsl.processorDsl.Import#getImportedNamespace()
   * @see #getImport()
   * @generated
   */
  EAttribute getImport_ImportedNamespace();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ImplementsExtendsDirective <em>Implements Extends Directive</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Implements Extends Directive</em>'.
   * @see org.sqlproc.dsl.processorDsl.ImplementsExtendsDirective
   * @generated
   */
  EClass getImplementsExtendsDirective();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.Implements <em>Implements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Implements</em>'.
   * @see org.sqlproc.dsl.processorDsl.Implements
   * @generated
   */
  EClass getImplements();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Implements#getDirectives <em>Directives</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Directives</em>'.
   * @see org.sqlproc.dsl.processorDsl.Implements#getDirectives()
   * @see #getImplements()
   * @generated
   */
  EReference getImplements_Directives();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.Implements#getImplements <em>Implements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Implements</em>'.
   * @see org.sqlproc.dsl.processorDsl.Implements#getImplements()
   * @see #getImplements()
   * @generated
   */
  EReference getImplements_Implements();

  /**
   * Returns the meta object for the reference list '{@link org.sqlproc.dsl.processorDsl.Implements#getOnlyPojos <em>Only Pojos</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Only Pojos</em>'.
   * @see org.sqlproc.dsl.processorDsl.Implements#getOnlyPojos()
   * @see #getImplements()
   * @generated
   */
  EReference getImplements_OnlyPojos();

  /**
   * Returns the meta object for the reference list '{@link org.sqlproc.dsl.processorDsl.Implements#getOnlyDaos <em>Only Daos</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Only Daos</em>'.
   * @see org.sqlproc.dsl.processorDsl.Implements#getOnlyDaos()
   * @see #getImplements()
   * @generated
   */
  EReference getImplements_OnlyDaos();

  /**
   * Returns the meta object for the reference list '{@link org.sqlproc.dsl.processorDsl.Implements#getExceptPojos <em>Except Pojos</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Except Pojos</em>'.
   * @see org.sqlproc.dsl.processorDsl.Implements#getExceptPojos()
   * @see #getImplements()
   * @generated
   */
  EReference getImplements_ExceptPojos();

  /**
   * Returns the meta object for the reference list '{@link org.sqlproc.dsl.processorDsl.Implements#getExceptDaos <em>Except Daos</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Except Daos</em>'.
   * @see org.sqlproc.dsl.processorDsl.Implements#getExceptDaos()
   * @see #getImplements()
   * @generated
   */
  EReference getImplements_ExceptDaos();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.Extends <em>Extends</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Extends</em>'.
   * @see org.sqlproc.dsl.processorDsl.Extends
   * @generated
   */
  EClass getExtends();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.Extends#getDirectives <em>Directives</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Directives</em>'.
   * @see org.sqlproc.dsl.processorDsl.Extends#getDirectives()
   * @see #getExtends()
   * @generated
   */
  EReference getExtends_Directives();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.Extends#getExtends <em>Extends</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Extends</em>'.
   * @see org.sqlproc.dsl.processorDsl.Extends#getExtends()
   * @see #getExtends()
   * @generated
   */
  EReference getExtends_Extends();

  /**
   * Returns the meta object for the reference list '{@link org.sqlproc.dsl.processorDsl.Extends#getOnlyPojos <em>Only Pojos</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Only Pojos</em>'.
   * @see org.sqlproc.dsl.processorDsl.Extends#getOnlyPojos()
   * @see #getExtends()
   * @generated
   */
  EReference getExtends_OnlyPojos();

  /**
   * Returns the meta object for the reference list '{@link org.sqlproc.dsl.processorDsl.Extends#getOnlyDaos <em>Only Daos</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Only Daos</em>'.
   * @see org.sqlproc.dsl.processorDsl.Extends#getOnlyDaos()
   * @see #getExtends()
   * @generated
   */
  EReference getExtends_OnlyDaos();

  /**
   * Returns the meta object for the reference list '{@link org.sqlproc.dsl.processorDsl.Extends#getExceptPojos <em>Except Pojos</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Except Pojos</em>'.
   * @see org.sqlproc.dsl.processorDsl.Extends#getExceptPojos()
   * @see #getExtends()
   * @generated
   */
  EReference getExtends_ExceptPojos();

  /**
   * Returns the meta object for the reference list '{@link org.sqlproc.dsl.processorDsl.Extends#getExceptDaos <em>Except Daos</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Except Daos</em>'.
   * @see org.sqlproc.dsl.processorDsl.Extends#getExceptDaos()
   * @see #getExtends()
   * @generated
   */
  EReference getExtends_ExceptDaos();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoEntityModifier1 <em>Pojo Entity Modifier1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Entity Modifier1</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoEntityModifier1
   * @generated
   */
  EClass getPojoEntityModifier1();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoEntityModifier1#isFinal <em>Final</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Final</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoEntityModifier1#isFinal()
   * @see #getPojoEntityModifier1()
   * @generated
   */
  EAttribute getPojoEntityModifier1_Final();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoEntityModifier1#isAbstract <em>Abstract</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Abstract</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoEntityModifier1#isAbstract()
   * @see #getPojoEntityModifier1()
   * @generated
   */
  EAttribute getPojoEntityModifier1_Abstract();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DirectiveProperties <em>Directive Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Directive Properties</em>'.
   * @see org.sqlproc.dsl.processorDsl.DirectiveProperties
   * @generated
   */
  EClass getDirectiveProperties();

  /**
   * Returns the meta object for the reference list '{@link org.sqlproc.dsl.processorDsl.DirectiveProperties#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Features</em>'.
   * @see org.sqlproc.dsl.processorDsl.DirectiveProperties#getFeatures()
   * @see #getDirectiveProperties()
   * @generated
   */
  EReference getDirectiveProperties_Features();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoDirective <em>Pojo Directive</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Directive</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirective
   * @generated
   */
  EClass getPojoDirective();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoEntityModifier2 <em>Pojo Entity Modifier2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Entity Modifier2</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoEntityModifier2
   * @generated
   */
  EClass getPojoEntityModifier2();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.PojoEntityModifier2#getSuperType <em>Super Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Super Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoEntityModifier2#getSuperType()
   * @see #getPojoEntityModifier2()
   * @generated
   */
  EReference getPojoEntityModifier2_SuperType();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoEntity <em>Pojo Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Entity</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoEntity
   * @generated
   */
  EClass getPojoEntity();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojoEntity#getModifiers1 <em>Modifiers1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Modifiers1</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoEntity#getModifiers1()
   * @see #getPojoEntity()
   * @generated
   */
  EReference getPojoEntity_Modifiers1();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojoEntity#getModifiers2 <em>Modifiers2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Modifiers2</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoEntity#getModifiers2()
   * @see #getPojoEntity()
   * @generated
   */
  EReference getPojoEntity_Modifiers2();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojoEntity#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoEntity#getFeatures()
   * @see #getPojoEntity()
   * @generated
   */
  EReference getPojoEntity_Features();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoAnnotatedProperty <em>Pojo Annotated Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Annotated Property</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoAnnotatedProperty
   * @generated
   */
  EClass getPojoAnnotatedProperty();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojoAnnotatedProperty#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Annotations</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoAnnotatedProperty#getAnnotations()
   * @see #getPojoAnnotatedProperty()
   * @generated
   */
  EReference getPojoAnnotatedProperty_Annotations();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.PojoAnnotatedProperty#getFeature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Feature</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoAnnotatedProperty#getFeature()
   * @see #getPojoAnnotatedProperty()
   * @generated
   */
  EReference getPojoAnnotatedProperty_Feature();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirective <em>Pojo Property Directive</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Property Directive</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirective
   * @generated
   */
  EClass getPojoPropertyDirective();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoProperty <em>Pojo Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Property</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoProperty
   * @generated
   */
  EClass getPojoProperty();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojoProperty#getDirectives <em>Directives</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Directives</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoProperty#getDirectives()
   * @see #getPojoProperty()
   * @generated
   */
  EReference getPojoProperty_Directives();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoProperty#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoProperty#getName()
   * @see #getPojoProperty()
   * @generated
   */
  EAttribute getPojoProperty_Name();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.PojoProperty#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoProperty#getType()
   * @see #getPojoProperty()
   * @generated
   */
  EReference getPojoProperty_Type();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.PojoProperty#getRef <em>Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ref</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoProperty#getRef()
   * @see #getPojoProperty()
   * @generated
   */
  EReference getPojoProperty_Ref();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.PojoProperty#getGtype <em>Gtype</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Gtype</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoProperty#getGtype()
   * @see #getPojoProperty()
   * @generated
   */
  EReference getPojoProperty_Gtype();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.PojoProperty#getGref <em>Gref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Gref</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoProperty#getGref()
   * @see #getPojoProperty()
   * @generated
   */
  EReference getPojoProperty_Gref();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoProperty#isArray <em>Array</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Array</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoProperty#isArray()
   * @see #getPojoProperty()
   * @generated
   */
  EAttribute getPojoProperty_Array();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.EnumEntityModifier1 <em>Enum Entity Modifier1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enum Entity Modifier1</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumEntityModifier1
   * @generated
   */
  EClass getEnumEntityModifier1();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.EnumEntityModifier1#isFinal <em>Final</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Final</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumEntityModifier1#isFinal()
   * @see #getEnumEntityModifier1()
   * @generated
   */
  EAttribute getEnumEntityModifier1_Final();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.EnumEntityModifier2 <em>Enum Entity Modifier2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enum Entity Modifier2</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumEntityModifier2
   * @generated
   */
  EClass getEnumEntityModifier2();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.EnumEntityModifier2#getSuperType <em>Super Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Super Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumEntityModifier2#getSuperType()
   * @see #getEnumEntityModifier2()
   * @generated
   */
  EReference getEnumEntityModifier2_SuperType();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.EnumEntity <em>Enum Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enum Entity</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumEntity
   * @generated
   */
  EClass getEnumEntity();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.EnumEntity#getModifiers1 <em>Modifiers1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Modifiers1</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumEntity#getModifiers1()
   * @see #getEnumEntity()
   * @generated
   */
  EReference getEnumEntity_Modifiers1();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.EnumEntity#getModifiers2 <em>Modifiers2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Modifiers2</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumEntity#getModifiers2()
   * @see #getEnumEntity()
   * @generated
   */
  EReference getEnumEntity_Modifiers2();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.EnumEntity#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumEntity#getFeatures()
   * @see #getEnumEntity()
   * @generated
   */
  EReference getEnumEntity_Features();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.EnumPropertyValue <em>Enum Property Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enum Property Value</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumPropertyValue
   * @generated
   */
  EClass getEnumPropertyValue();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.EnumPropertyValue#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumPropertyValue#getName()
   * @see #getEnumPropertyValue()
   * @generated
   */
  EAttribute getEnumPropertyValue_Name();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.EnumPropertyValue#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumPropertyValue#getValue()
   * @see #getEnumPropertyValue()
   * @generated
   */
  EAttribute getEnumPropertyValue_Value();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.EnumPropertyDirective <em>Enum Property Directive</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enum Property Directive</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumPropertyDirective
   * @generated
   */
  EClass getEnumPropertyDirective();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.EnumProperty <em>Enum Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enum Property</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumProperty
   * @generated
   */
  EClass getEnumProperty();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.EnumProperty#getDirectives <em>Directives</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Directives</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumProperty#getDirectives()
   * @see #getEnumProperty()
   * @generated
   */
  EReference getEnumProperty_Directives();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.EnumProperty#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumProperty#getName()
   * @see #getEnumProperty()
   * @generated
   */
  EAttribute getEnumProperty_Name();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.EnumProperty#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumProperty#getType()
   * @see #getEnumProperty()
   * @generated
   */
  EReference getEnumProperty_Type();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DaoDirectiveParameters <em>Dao Directive Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Dao Directive Parameters</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaoDirectiveParameters
   * @generated
   */
  EClass getDaoDirectiveParameters();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DaoDirectiveParameters#getOut <em>Out</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Out</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaoDirectiveParameters#getOut()
   * @see #getDaoDirectiveParameters()
   * @generated
   */
  EReference getDaoDirectiveParameters_Out();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.DaoDirectiveParameters#getIns <em>Ins</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Ins</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaoDirectiveParameters#getIns()
   * @see #getDaoDirectiveParameters()
   * @generated
   */
  EReference getDaoDirectiveParameters_Ins();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DescendantAssignment <em>Descendant Assignment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Descendant Assignment</em>'.
   * @see org.sqlproc.dsl.processorDsl.DescendantAssignment
   * @generated
   */
  EClass getDescendantAssignment();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DescendantAssignment#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.sqlproc.dsl.processorDsl.DescendantAssignment#getValue()
   * @see #getDescendantAssignment()
   * @generated
   */
  EAttribute getDescendantAssignment_Value();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DescendantAssignment#getDescendant <em>Descendant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Descendant</em>'.
   * @see org.sqlproc.dsl.processorDsl.DescendantAssignment#getDescendant()
   * @see #getDescendantAssignment()
   * @generated
   */
  EReference getDescendantAssignment_Descendant();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.FunProcType <em>Fun Proc Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fun Proc Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.FunProcType
   * @generated
   */
  EClass getFunProcType();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DaoDirective <em>Dao Directive</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Dao Directive</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaoDirective
   * @generated
   */
  EClass getDaoDirective();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoDaoModifier <em>Pojo Dao Modifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Dao Modifier</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDaoModifier
   * @generated
   */
  EClass getPojoDaoModifier();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.PojoDaoModifier#getSuperType <em>Super Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Super Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDaoModifier#getSuperType()
   * @see #getPojoDaoModifier()
   * @generated
   */
  EReference getPojoDaoModifier_SuperType();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoDao <em>Pojo Dao</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Dao</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDao
   * @generated
   */
  EClass getPojoDao();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojoDao#getDirectives <em>Directives</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Directives</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDao#getDirectives()
   * @see #getPojoDao()
   * @generated
   */
  EReference getPojoDao_Directives();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojoDao#getModifiers1 <em>Modifiers1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Modifiers1</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDao#getModifiers1()
   * @see #getPojoDao()
   * @generated
   */
  EReference getPojoDao_Modifiers1();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoDao#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDao#getName()
   * @see #getPojoDao()
   * @generated
   */
  EAttribute getPojoDao_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.PojoDao#getModifiers2 <em>Modifiers2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Modifiers2</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDao#getModifiers2()
   * @see #getPojoDao()
   * @generated
   */
  EReference getPojoDao_Modifiers2();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PackageDirectiveSuffix <em>Package Directive Suffix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Package Directive Suffix</em>'.
   * @see org.sqlproc.dsl.processorDsl.PackageDirectiveSuffix
   * @generated
   */
  EClass getPackageDirectiveSuffix();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PackageDirectiveSuffix#getSuffix <em>Suffix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Suffix</em>'.
   * @see org.sqlproc.dsl.processorDsl.PackageDirectiveSuffix#getSuffix()
   * @see #getPackageDirectiveSuffix()
   * @generated
   */
  EAttribute getPackageDirectiveSuffix_Suffix();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PackageDirectiveImplementation <em>Package Directive Implementation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Package Directive Implementation</em>'.
   * @see org.sqlproc.dsl.processorDsl.PackageDirectiveImplementation
   * @generated
   */
  EClass getPackageDirectiveImplementation();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PackageDirectiveImplementation#getImplementation <em>Implementation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Implementation</em>'.
   * @see org.sqlproc.dsl.processorDsl.PackageDirectiveImplementation#getImplementation()
   * @see #getPackageDirectiveImplementation()
   * @generated
   */
  EAttribute getPackageDirectiveImplementation_Implementation();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.AnnotationDirectiveConflict <em>Annotation Directive Conflict</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Annotation Directive Conflict</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotationDirectiveConflict
   * @generated
   */
  EClass getAnnotationDirectiveConflict();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.AnnotationDirectiveStatic <em>Annotation Directive Static</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Annotation Directive Static</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotationDirectiveStatic
   * @generated
   */
  EClass getAnnotationDirectiveStatic();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.AnnotationDirectiveConstructor <em>Annotation Directive Constructor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Annotation Directive Constructor</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotationDirectiveConstructor
   * @generated
   */
  EClass getAnnotationDirectiveConstructor();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.AnnotationDirectiveStandard <em>Annotation Directive Standard</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Annotation Directive Standard</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotationDirectiveStandard
   * @generated
   */
  EClass getAnnotationDirectiveStandard();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.AnnotationDirectiveSetter <em>Annotation Directive Setter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Annotation Directive Setter</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotationDirectiveSetter
   * @generated
   */
  EClass getAnnotationDirectiveSetter();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.AnnotationDirectiveGetter <em>Annotation Directive Getter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Annotation Directive Getter</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotationDirectiveGetter
   * @generated
   */
  EClass getAnnotationDirectiveGetter();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.AnnotationDirectiveAttribute <em>Annotation Directive Attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Annotation Directive Attribute</em>'.
   * @see org.sqlproc.dsl.processorDsl.AnnotationDirectiveAttribute
   * @generated
   */
  EClass getAnnotationDirectiveAttribute();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ImplementsExtendsDirectiveGenerics <em>Implements Extends Directive Generics</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Implements Extends Directive Generics</em>'.
   * @see org.sqlproc.dsl.processorDsl.ImplementsExtendsDirectiveGenerics
   * @generated
   */
  EClass getImplementsExtendsDirectiveGenerics();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoDirectiveToString <em>Pojo Directive To String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Directive To String</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirectiveToString
   * @generated
   */
  EClass getPojoDirectiveToString();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.PojoDirectiveToString#getProplist <em>Proplist</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Proplist</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirectiveToString#getProplist()
   * @see #getPojoDirectiveToString()
   * @generated
   */
  EReference getPojoDirectiveToString_Proplist();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoDirectiveIndex <em>Pojo Directive Index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Directive Index</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirectiveIndex
   * @generated
   */
  EClass getPojoDirectiveIndex();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoDirectiveIndex#getIndex <em>Index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Index</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirectiveIndex#getIndex()
   * @see #getPojoDirectiveIndex()
   * @generated
   */
  EAttribute getPojoDirectiveIndex_Index();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.PojoDirectiveIndex#getProplist <em>Proplist</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Proplist</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirectiveIndex#getProplist()
   * @see #getPojoDirectiveIndex()
   * @generated
   */
  EReference getPojoDirectiveIndex_Proplist();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoDirectiveOperators <em>Pojo Directive Operators</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Directive Operators</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirectiveOperators
   * @generated
   */
  EClass getPojoDirectiveOperators();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoDirectiveOperators#getOperatorsSuffix <em>Operators Suffix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operators Suffix</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirectiveOperators#getOperatorsSuffix()
   * @see #getPojoDirectiveOperators()
   * @generated
   */
  EAttribute getPojoDirectiveOperators_OperatorsSuffix();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoDirectiveSerializable <em>Pojo Directive Serializable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Directive Serializable</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirectiveSerializable
   * @generated
   */
  EClass getPojoDirectiveSerializable();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoDirectiveSerializable#getSernum <em>Sernum</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sernum</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirectiveSerializable#getSernum()
   * @see #getPojoDirectiveSerializable()
   * @generated
   */
  EAttribute getPojoDirectiveSerializable_Sernum();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoDirectiveDiscriminator <em>Pojo Directive Discriminator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Directive Discriminator</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirectiveDiscriminator
   * @generated
   */
  EClass getPojoDirectiveDiscriminator();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoDirectiveDiscriminator#getDiscriminator <em>Discriminator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Discriminator</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirectiveDiscriminator#getDiscriminator()
   * @see #getPojoDirectiveDiscriminator()
   * @generated
   */
  EAttribute getPojoDirectiveDiscriminator_Discriminator();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoDirectiveEquals <em>Pojo Directive Equals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Directive Equals</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirectiveEquals
   * @generated
   */
  EClass getPojoDirectiveEquals();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.PojoDirectiveEquals#getProplist <em>Proplist</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Proplist</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirectiveEquals#getProplist()
   * @see #getPojoDirectiveEquals()
   * @generated
   */
  EReference getPojoDirectiveEquals_Proplist();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoDirectiveHashCode <em>Pojo Directive Hash Code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Directive Hash Code</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirectiveHashCode
   * @generated
   */
  EClass getPojoDirectiveHashCode();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.PojoDirectiveHashCode#getProplist <em>Proplist</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Proplist</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoDirectiveHashCode#getProplist()
   * @see #getPojoDirectiveHashCode()
   * @generated
   */
  EReference getPojoDirectiveHashCode_Proplist();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveRequired <em>Pojo Property Directive Required</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Property Directive Required</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveRequired
   * @generated
   */
  EClass getPojoPropertyDirectiveRequired();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectivePrimaryKey <em>Pojo Property Directive Primary Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Property Directive Primary Key</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectivePrimaryKey
   * @generated
   */
  EClass getPojoPropertyDirectivePrimaryKey();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveDiscriminator <em>Pojo Property Directive Discriminator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Property Directive Discriminator</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveDiscriminator
   * @generated
   */
  EClass getPojoPropertyDirectiveDiscriminator();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveIndex <em>Pojo Property Directive Index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Property Directive Index</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveIndex
   * @generated
   */
  EClass getPojoPropertyDirectiveIndex();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveIndex#getIndex <em>Index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Index</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveIndex#getIndex()
   * @see #getPojoPropertyDirectiveIndex()
   * @generated
   */
  EAttribute getPojoPropertyDirectiveIndex_Index();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveVersion <em>Pojo Property Directive Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Property Directive Version</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveVersion
   * @generated
   */
  EClass getPojoPropertyDirectiveVersion();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveUpdateCol <em>Pojo Property Directive Update Col</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Property Directive Update Col</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveUpdateCol
   * @generated
   */
  EClass getPojoPropertyDirectiveUpdateCol();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveUpdateCol#getUpdateColumn1 <em>Update Column1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Update Column1</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveUpdateCol#getUpdateColumn1()
   * @see #getPojoPropertyDirectiveUpdateCol()
   * @generated
   */
  EAttribute getPojoPropertyDirectiveUpdateCol_UpdateColumn1();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveUpdateCol#getUpdateColumn2 <em>Update Column2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Update Column2</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveUpdateCol#getUpdateColumn2()
   * @see #getPojoPropertyDirectiveUpdateCol()
   * @generated
   */
  EAttribute getPojoPropertyDirectiveUpdateCol_UpdateColumn2();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveCreateCol <em>Pojo Property Directive Create Col</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Property Directive Create Col</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveCreateCol
   * @generated
   */
  EClass getPojoPropertyDirectiveCreateCol();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveCreateCol#getCreateColumn1 <em>Create Column1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Create Column1</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveCreateCol#getCreateColumn1()
   * @see #getPojoPropertyDirectiveCreateCol()
   * @generated
   */
  EAttribute getPojoPropertyDirectiveCreateCol_CreateColumn1();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveCreateCol#getCreateColumn2 <em>Create Column2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Create Column2</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveCreateCol#getCreateColumn2()
   * @see #getPojoPropertyDirectiveCreateCol()
   * @generated
   */
  EAttribute getPojoPropertyDirectiveCreateCol_CreateColumn2();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveToInit <em>Pojo Property Directive To Init</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Property Directive To Init</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveToInit
   * @generated
   */
  EClass getPojoPropertyDirectiveToInit();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveEnumInit <em>Pojo Property Directive Enum Init</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Property Directive Enum Init</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveEnumInit
   * @generated
   */
  EClass getPojoPropertyDirectiveEnumInit();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveIsDef <em>Pojo Property Directive Is Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Property Directive Is Def</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveIsDef
   * @generated
   */
  EClass getPojoPropertyDirectiveIsDef();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveEnumDef <em>Pojo Property Directive Enum Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pojo Property Directive Enum Def</em>'.
   * @see org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveEnumDef
   * @generated
   */
  EClass getPojoPropertyDirectiveEnumDef();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.EnumPropertyDirectiveValues <em>Enum Property Directive Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enum Property Directive Values</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumPropertyDirectiveValues
   * @generated
   */
  EClass getEnumPropertyDirectiveValues();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.EnumPropertyDirectiveValues#getValues <em>Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Values</em>'.
   * @see org.sqlproc.dsl.processorDsl.EnumPropertyDirectiveValues#getValues()
   * @see #getEnumPropertyDirectiveValues()
   * @generated
   */
  EReference getEnumPropertyDirectiveValues_Values();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.FunctionCallQuery <em>Function Call Query</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Call Query</em>'.
   * @see org.sqlproc.dsl.processorDsl.FunctionCallQuery
   * @generated
   */
  EClass getFunctionCallQuery();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ProcedureCallQuery <em>Procedure Call Query</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Procedure Call Query</em>'.
   * @see org.sqlproc.dsl.processorDsl.ProcedureCallQuery
   * @generated
   */
  EClass getProcedureCallQuery();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.FunctionCall <em>Function Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Call</em>'.
   * @see org.sqlproc.dsl.processorDsl.FunctionCall
   * @generated
   */
  EClass getFunctionCall();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.ProcedureUpdate <em>Procedure Update</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Procedure Update</em>'.
   * @see org.sqlproc.dsl.processorDsl.ProcedureUpdate
   * @generated
   */
  EClass getProcedureUpdate();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.FunctionQuery <em>Function Query</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Query</em>'.
   * @see org.sqlproc.dsl.processorDsl.FunctionQuery
   * @generated
   */
  EClass getFunctionQuery();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DaoDirectiveSerializable <em>Dao Directive Serializable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Dao Directive Serializable</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaoDirectiveSerializable
   * @generated
   */
  EClass getDaoDirectiveSerializable();

  /**
   * Returns the meta object for the attribute '{@link org.sqlproc.dsl.processorDsl.DaoDirectiveSerializable#getSernum <em>Sernum</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sernum</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaoDirectiveSerializable#getSernum()
   * @see #getDaoDirectiveSerializable()
   * @generated
   */
  EAttribute getDaoDirectiveSerializable_Sernum();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DaoDirectiveDiscriminator <em>Dao Directive Discriminator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Dao Directive Discriminator</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaoDirectiveDiscriminator
   * @generated
   */
  EClass getDaoDirectiveDiscriminator();

  /**
   * Returns the meta object for the reference '{@link org.sqlproc.dsl.processorDsl.DaoDirectiveDiscriminator#getAncestor <em>Ancestor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ancestor</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaoDirectiveDiscriminator#getAncestor()
   * @see #getDaoDirectiveDiscriminator()
   * @generated
   */
  EReference getDaoDirectiveDiscriminator_Ancestor();

  /**
   * Returns the meta object for the containment reference list '{@link org.sqlproc.dsl.processorDsl.DaoDirectiveDiscriminator#getDescendants <em>Descendants</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Descendants</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaoDirectiveDiscriminator#getDescendants()
   * @see #getDaoDirectiveDiscriminator()
   * @generated
   */
  EReference getDaoDirectiveDiscriminator_Descendants();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DaoDirectiveCrud <em>Dao Directive Crud</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Dao Directive Crud</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaoDirectiveCrud
   * @generated
   */
  EClass getDaoDirectiveCrud();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DaoDirectiveCrud#getPojo <em>Pojo</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pojo</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaoDirectiveCrud#getPojo()
   * @see #getDaoDirectiveCrud()
   * @generated
   */
  EReference getDaoDirectiveCrud_Pojo();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.DaoDirectiveQuery <em>Dao Directive Query</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Dao Directive Query</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaoDirectiveQuery
   * @generated
   */
  EClass getDaoDirectiveQuery();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.DaoDirectiveQuery#getPojo <em>Pojo</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Pojo</em>'.
   * @see org.sqlproc.dsl.processorDsl.DaoDirectiveQuery#getPojo()
   * @see #getDaoDirectiveQuery()
   * @generated
   */
  EReference getDaoDirectiveQuery_Pojo();

  /**
   * Returns the meta object for class '{@link org.sqlproc.dsl.processorDsl.FunProcDirective <em>Fun Proc Directive</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fun Proc Directive</em>'.
   * @see org.sqlproc.dsl.processorDsl.FunProcDirective
   * @generated
   */
  EClass getFunProcDirective();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.FunProcDirective#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.sqlproc.dsl.processorDsl.FunProcDirective#getType()
   * @see #getFunProcDirective()
   * @generated
   */
  EReference getFunProcDirective_Type();

  /**
   * Returns the meta object for the containment reference '{@link org.sqlproc.dsl.processorDsl.FunProcDirective#getParamlist <em>Paramlist</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Paramlist</em>'.
   * @see org.sqlproc.dsl.processorDsl.FunProcDirective#getParamlist()
   * @see #getFunProcDirective()
   * @generated
   */
  EReference getFunProcDirective_Paramlist();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ProcessorDslFactory getProcessorDslFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ArtifactsImpl <em>Artifacts</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ArtifactsImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getArtifacts()
     * @generated
     */
    EClass ARTIFACTS = eINSTANCE.getArtifacts();

    /**
     * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARTIFACTS__PROPERTIES = eINSTANCE.getArtifacts_Properties();

    /**
     * The meta object literal for the '<em><b>Pojos</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARTIFACTS__POJOS = eINSTANCE.getArtifacts_Pojos();

    /**
     * The meta object literal for the '<em><b>Tables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARTIFACTS__TABLES = eINSTANCE.getArtifacts_Tables();

    /**
     * The meta object literal for the '<em><b>Procedures</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARTIFACTS__PROCEDURES = eINSTANCE.getArtifacts_Procedures();

    /**
     * The meta object literal for the '<em><b>Functions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARTIFACTS__FUNCTIONS = eINSTANCE.getArtifacts_Functions();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARTIFACTS__STATEMENTS = eINSTANCE.getArtifacts_Statements();

    /**
     * The meta object literal for the '<em><b>Mappings</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARTIFACTS__MAPPINGS = eINSTANCE.getArtifacts_Mappings();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARTIFACTS__FEATURES = eINSTANCE.getArtifacts_Features();

    /**
     * The meta object literal for the '<em><b>Packages</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARTIFACTS__PACKAGES = eINSTANCE.getArtifacts_Packages();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DatabaseCatalogAssignementImpl <em>Database Catalog Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DatabaseCatalogAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDatabaseCatalogAssignement()
     * @generated
     */
    EClass DATABASE_CATALOG_ASSIGNEMENT = eINSTANCE.getDatabaseCatalogAssignement();

    /**
     * The meta object literal for the '<em><b>Db Catalog</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATABASE_CATALOG_ASSIGNEMENT__DB_CATALOG = eINSTANCE.getDatabaseCatalogAssignement_DbCatalog();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DatabaseSchemaAssignementImpl <em>Database Schema Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DatabaseSchemaAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDatabaseSchemaAssignement()
     * @generated
     */
    EClass DATABASE_SCHEMA_ASSIGNEMENT = eINSTANCE.getDatabaseSchemaAssignement();

    /**
     * The meta object literal for the '<em><b>Db Schema</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATABASE_SCHEMA_ASSIGNEMENT__DB_SCHEMA = eINSTANCE.getDatabaseSchemaAssignement_DbSchema();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DatabaseTypeAssignementImpl <em>Database Type Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DatabaseTypeAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDatabaseTypeAssignement()
     * @generated
     */
    EClass DATABASE_TYPE_ASSIGNEMENT = eINSTANCE.getDatabaseTypeAssignement();

    /**
     * The meta object literal for the '<em><b>Db Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATABASE_TYPE_ASSIGNEMENT__DB_TYPE = eINSTANCE.getDatabaseTypeAssignement_DbType();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DatabaseMetaInfoAssignementImpl <em>Database Meta Info Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DatabaseMetaInfoAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDatabaseMetaInfoAssignement()
     * @generated
     */
    EClass DATABASE_META_INFO_ASSIGNEMENT = eINSTANCE.getDatabaseMetaInfoAssignement();

    /**
     * The meta object literal for the '<em><b>Db Meta Info</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATABASE_META_INFO_ASSIGNEMENT__DB_META_INFO = eINSTANCE.getDatabaseMetaInfoAssignement_DbMetaInfo();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DriverMetaInfoAssignementImpl <em>Driver Meta Info Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DriverMetaInfoAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDriverMetaInfoAssignement()
     * @generated
     */
    EClass DRIVER_META_INFO_ASSIGNEMENT = eINSTANCE.getDriverMetaInfoAssignement();

    /**
     * The meta object literal for the '<em><b>Db Driver Info</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DRIVER_META_INFO_ASSIGNEMENT__DB_DRIVER_INFO = eINSTANCE.getDriverMetaInfoAssignement_DbDriverInfo();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DriverMethodOutputAssignementImpl <em>Driver Method Output Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DriverMethodOutputAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDriverMethodOutputAssignement()
     * @generated
     */
    EClass DRIVER_METHOD_OUTPUT_ASSIGNEMENT = eINSTANCE.getDriverMethodOutputAssignement();

    /**
     * The meta object literal for the '<em><b>Driver Method</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DRIVER_METHOD_OUTPUT_ASSIGNEMENT__DRIVER_METHOD = eINSTANCE.getDriverMethodOutputAssignement_DriverMethod();

    /**
     * The meta object literal for the '<em><b>Call Output</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DRIVER_METHOD_OUTPUT_ASSIGNEMENT__CALL_OUTPUT = eINSTANCE.getDriverMethodOutputAssignement_CallOutput();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.SqlTypeAssignementImpl <em>Sql Type Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.SqlTypeAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getSqlTypeAssignement()
     * @generated
     */
    EClass SQL_TYPE_ASSIGNEMENT = eINSTANCE.getSqlTypeAssignement();

    /**
     * The meta object literal for the '<em><b>Type Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SQL_TYPE_ASSIGNEMENT__TYPE_NAME = eINSTANCE.getSqlTypeAssignement_TypeName();

    /**
     * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SQL_TYPE_ASSIGNEMENT__SIZE = eINSTANCE.getSqlTypeAssignement_Size();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SQL_TYPE_ASSIGNEMENT__TYPE = eINSTANCE.getSqlTypeAssignement_Type();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ColumnTypeAssignementImpl <em>Column Type Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ColumnTypeAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getColumnTypeAssignement()
     * @generated
     */
    EClass COLUMN_TYPE_ASSIGNEMENT = eINSTANCE.getColumnTypeAssignement();

    /**
     * The meta object literal for the '<em><b>Db Column</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLUMN_TYPE_ASSIGNEMENT__DB_COLUMN = eINSTANCE.getColumnTypeAssignement_DbColumn();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COLUMN_TYPE_ASSIGNEMENT__TYPE = eINSTANCE.getColumnTypeAssignement_Type();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ShowColumnTypeAssignementImpl <em>Show Column Type Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ShowColumnTypeAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getShowColumnTypeAssignement()
     * @generated
     */
    EClass SHOW_COLUMN_TYPE_ASSIGNEMENT = eINSTANCE.getShowColumnTypeAssignement();

    /**
     * The meta object literal for the '<em><b>Db Column</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SHOW_COLUMN_TYPE_ASSIGNEMENT__DB_COLUMN = eINSTANCE.getShowColumnTypeAssignement_DbColumn();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SHOW_COLUMN_TYPE_ASSIGNEMENT__TYPE = eINSTANCE.getShowColumnTypeAssignement_Type();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.TableAssignementImpl <em>Table Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.TableAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getTableAssignement()
     * @generated
     */
    EClass TABLE_ASSIGNEMENT = eINSTANCE.getTableAssignement();

    /**
     * The meta object literal for the '<em><b>Db Table</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TABLE_ASSIGNEMENT__DB_TABLE = eINSTANCE.getTableAssignement_DbTable();

    /**
     * The meta object literal for the '<em><b>New Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TABLE_ASSIGNEMENT__NEW_NAME = eINSTANCE.getTableAssignement_NewName();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.JoinTableAssignementImpl <em>Join Table Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.JoinTableAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getJoinTableAssignement()
     * @generated
     */
    EClass JOIN_TABLE_ASSIGNEMENT = eINSTANCE.getJoinTableAssignement();

    /**
     * The meta object literal for the '<em><b>Db Table</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute JOIN_TABLE_ASSIGNEMENT__DB_TABLE = eINSTANCE.getJoinTableAssignement_DbTable();

    /**
     * The meta object literal for the '<em><b>Db Tables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute JOIN_TABLE_ASSIGNEMENT__DB_TABLES = eINSTANCE.getJoinTableAssignement_DbTables();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ColumnAssignementImpl <em>Column Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ColumnAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getColumnAssignement()
     * @generated
     */
    EClass COLUMN_ASSIGNEMENT = eINSTANCE.getColumnAssignement();

    /**
     * The meta object literal for the '<em><b>Db Column</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLUMN_ASSIGNEMENT__DB_COLUMN = eINSTANCE.getColumnAssignement_DbColumn();

    /**
     * The meta object literal for the '<em><b>New Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLUMN_ASSIGNEMENT__NEW_NAME = eINSTANCE.getColumnAssignement_NewName();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ImportAssignementImpl <em>Import Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ImportAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getImportAssignement()
     * @generated
     */
    EClass IMPORT_ASSIGNEMENT = eINSTANCE.getImportAssignement();

    /**
     * The meta object literal for the '<em><b>Db Column</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPORT_ASSIGNEMENT__DB_COLUMN = eINSTANCE.getImportAssignement_DbColumn();

    /**
     * The meta object literal for the '<em><b>Pk Table</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPORT_ASSIGNEMENT__PK_TABLE = eINSTANCE.getImportAssignement_PkTable();

    /**
     * The meta object literal for the '<em><b>Pk Column</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPORT_ASSIGNEMENT__PK_COLUMN = eINSTANCE.getImportAssignement_PkColumn();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ExportAssignementImpl <em>Export Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ExportAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getExportAssignement()
     * @generated
     */
    EClass EXPORT_ASSIGNEMENT = eINSTANCE.getExportAssignement();

    /**
     * The meta object literal for the '<em><b>Db Column</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPORT_ASSIGNEMENT__DB_COLUMN = eINSTANCE.getExportAssignement_DbColumn();

    /**
     * The meta object literal for the '<em><b>Fk Table</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPORT_ASSIGNEMENT__FK_TABLE = eINSTANCE.getExportAssignement_FkTable();

    /**
     * The meta object literal for the '<em><b>Fk Column</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPORT_ASSIGNEMENT__FK_COLUMN = eINSTANCE.getExportAssignement_FkColumn();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.InheritanceAssignementImpl <em>Inheritance Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.InheritanceAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getInheritanceAssignement()
     * @generated
     */
    EClass INHERITANCE_ASSIGNEMENT = eINSTANCE.getInheritanceAssignement();

    /**
     * The meta object literal for the '<em><b>Discriminator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INHERITANCE_ASSIGNEMENT__DISCRIMINATOR = eINSTANCE.getInheritanceAssignement_Discriminator();

    /**
     * The meta object literal for the '<em><b>Db Table</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INHERITANCE_ASSIGNEMENT__DB_TABLE = eINSTANCE.getInheritanceAssignement_DbTable();

    /**
     * The meta object literal for the '<em><b>Db Columns</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INHERITANCE_ASSIGNEMENT__DB_COLUMNS = eINSTANCE.getInheritanceAssignement_DbColumns();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ManyToManyAssignementImpl <em>Many To Many Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ManyToManyAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getManyToManyAssignement()
     * @generated
     */
    EClass MANY_TO_MANY_ASSIGNEMENT = eINSTANCE.getManyToManyAssignement();

    /**
     * The meta object literal for the '<em><b>Pk Column</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MANY_TO_MANY_ASSIGNEMENT__PK_COLUMN = eINSTANCE.getManyToManyAssignement_PkColumn();

    /**
     * The meta object literal for the '<em><b>Pk Table</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MANY_TO_MANY_ASSIGNEMENT__PK_TABLE = eINSTANCE.getManyToManyAssignement_PkTable();

    /**
     * The meta object literal for the '<em><b>Fk Column</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MANY_TO_MANY_ASSIGNEMENT__FK_COLUMN = eINSTANCE.getManyToManyAssignement_FkColumn();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DebugLevelAssignementImpl <em>Debug Level Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DebugLevelAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDebugLevelAssignement()
     * @generated
     */
    EClass DEBUG_LEVEL_ASSIGNEMENT = eINSTANCE.getDebugLevelAssignement();

    /**
     * The meta object literal for the '<em><b>Debug</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DEBUG_LEVEL_ASSIGNEMENT__DEBUG = eINSTANCE.getDebugLevelAssignement_Debug();

    /**
     * The meta object literal for the '<em><b>Scope</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DEBUG_LEVEL_ASSIGNEMENT__SCOPE = eINSTANCE.getDebugLevelAssignement_Scope();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ProcedurePojoAssignementImpl <em>Procedure Pojo Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ProcedurePojoAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getProcedurePojoAssignement()
     * @generated
     */
    EClass PROCEDURE_POJO_ASSIGNEMENT = eINSTANCE.getProcedurePojoAssignement();

    /**
     * The meta object literal for the '<em><b>Db Procedure</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROCEDURE_POJO_ASSIGNEMENT__DB_PROCEDURE = eINSTANCE.getProcedurePojoAssignement_DbProcedure();

    /**
     * The meta object literal for the '<em><b>Pojo</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROCEDURE_POJO_ASSIGNEMENT__POJO = eINSTANCE.getProcedurePojoAssignement_Pojo();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.FunctionPojoAssignementImpl <em>Function Pojo Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.FunctionPojoAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getFunctionPojoAssignement()
     * @generated
     */
    EClass FUNCTION_POJO_ASSIGNEMENT = eINSTANCE.getFunctionPojoAssignement();

    /**
     * The meta object literal for the '<em><b>Db Function</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_POJO_ASSIGNEMENT__DB_FUNCTION = eINSTANCE.getFunctionPojoAssignement_DbFunction();

    /**
     * The meta object literal for the '<em><b>Pojo</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_POJO_ASSIGNEMENT__POJO = eINSTANCE.getFunctionPojoAssignement_Pojo();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ImplementsAssignementImpl <em>Implements Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ImplementsAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getImplementsAssignement()
     * @generated
     */
    EClass IMPLEMENTS_ASSIGNEMENT = eINSTANCE.getImplementsAssignement();

    /**
     * The meta object literal for the '<em><b>To Implement</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IMPLEMENTS_ASSIGNEMENT__TO_IMPLEMENT = eINSTANCE.getImplementsAssignement_ToImplement();

    /**
     * The meta object literal for the '<em><b>Db Tables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPLEMENTS_ASSIGNEMENT__DB_TABLES = eINSTANCE.getImplementsAssignement_DbTables();

    /**
     * The meta object literal for the '<em><b>Db Not Tables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPLEMENTS_ASSIGNEMENT__DB_NOT_TABLES = eINSTANCE.getImplementsAssignement_DbNotTables();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ExtendsAssignementImpl <em>Extends Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ExtendsAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getExtendsAssignement()
     * @generated
     */
    EClass EXTENDS_ASSIGNEMENT = eINSTANCE.getExtendsAssignement();

    /**
     * The meta object literal for the '<em><b>To Extends</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENDS_ASSIGNEMENT__TO_EXTENDS = eINSTANCE.getExtendsAssignement_ToExtends();

    /**
     * The meta object literal for the '<em><b>Db Tables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXTENDS_ASSIGNEMENT__DB_TABLES = eINSTANCE.getExtendsAssignement_DbTables();

    /**
     * The meta object literal for the '<em><b>Db Not Tables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXTENDS_ASSIGNEMENT__DB_NOT_TABLES = eINSTANCE.getExtendsAssignement_DbNotTables();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ImplementsAssignementGenericsImpl <em>Implements Assignement Generics</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ImplementsAssignementGenericsImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getImplementsAssignementGenerics()
     * @generated
     */
    EClass IMPLEMENTS_ASSIGNEMENT_GENERICS = eINSTANCE.getImplementsAssignementGenerics();

    /**
     * The meta object literal for the '<em><b>To Implement</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IMPLEMENTS_ASSIGNEMENT_GENERICS__TO_IMPLEMENT = eINSTANCE.getImplementsAssignementGenerics_ToImplement();

    /**
     * The meta object literal for the '<em><b>Db Tables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_TABLES = eINSTANCE.getImplementsAssignementGenerics_DbTables();

    /**
     * The meta object literal for the '<em><b>Db Not Tables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_NOT_TABLES = eINSTANCE.getImplementsAssignementGenerics_DbNotTables();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ExtendsAssignementGenericsImpl <em>Extends Assignement Generics</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ExtendsAssignementGenericsImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getExtendsAssignementGenerics()
     * @generated
     */
    EClass EXTENDS_ASSIGNEMENT_GENERICS = eINSTANCE.getExtendsAssignementGenerics();

    /**
     * The meta object literal for the '<em><b>To Extends</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENDS_ASSIGNEMENT_GENERICS__TO_EXTENDS = eINSTANCE.getExtendsAssignementGenerics_ToExtends();

    /**
     * The meta object literal for the '<em><b>Db Tables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXTENDS_ASSIGNEMENT_GENERICS__DB_TABLES = eINSTANCE.getExtendsAssignementGenerics_DbTables();

    /**
     * The meta object literal for the '<em><b>Db Not Tables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXTENDS_ASSIGNEMENT_GENERICS__DB_NOT_TABLES = eINSTANCE.getExtendsAssignementGenerics_DbNotTables();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PropertyImpl <em>Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PropertyImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getProperty()
     * @generated
     */
    EClass PROPERTY = eINSTANCE.getProperty();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROPERTY__NAME = eINSTANCE.getProperty_Name();

    /**
     * The meta object literal for the '<em><b>Database</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTY__DATABASE = eINSTANCE.getProperty_Database();

    /**
     * The meta object literal for the '<em><b>Pojogen</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTY__POJOGEN = eINSTANCE.getProperty_Pojogen();

    /**
     * The meta object literal for the '<em><b>Metagen</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTY__METAGEN = eINSTANCE.getProperty_Metagen();

    /**
     * The meta object literal for the '<em><b>Daogen</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTY__DAOGEN = eINSTANCE.getProperty_Daogen();

    /**
     * The meta object literal for the '<em><b>Replace Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROPERTY__REPLACE_ID = eINSTANCE.getProperty_ReplaceId();

    /**
     * The meta object literal for the '<em><b>Regex</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROPERTY__REGEX = eINSTANCE.getProperty_Regex();

    /**
     * The meta object literal for the '<em><b>Replacement</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROPERTY__REPLACEMENT = eINSTANCE.getProperty_Replacement();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DatabasePropertyImpl <em>Database Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DatabasePropertyImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDatabaseProperty()
     * @generated
     */
    EClass DATABASE_PROPERTY = eINSTANCE.getDatabaseProperty();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATABASE_PROPERTY__NAME = eINSTANCE.getDatabaseProperty_Name();

    /**
     * The meta object literal for the '<em><b>Db Url</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATABASE_PROPERTY__DB_URL = eINSTANCE.getDatabaseProperty_DbUrl();

    /**
     * The meta object literal for the '<em><b>Db Username</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATABASE_PROPERTY__DB_USERNAME = eINSTANCE.getDatabaseProperty_DbUsername();

    /**
     * The meta object literal for the '<em><b>Db Password</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATABASE_PROPERTY__DB_PASSWORD = eINSTANCE.getDatabaseProperty_DbPassword();

    /**
     * The meta object literal for the '<em><b>Db Catalog</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATABASE_PROPERTY__DB_CATALOG = eINSTANCE.getDatabaseProperty_DbCatalog();

    /**
     * The meta object literal for the '<em><b>Db Schema</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATABASE_PROPERTY__DB_SCHEMA = eINSTANCE.getDatabaseProperty_DbSchema();

    /**
     * The meta object literal for the '<em><b>Db Driverx</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATABASE_PROPERTY__DB_DRIVERX = eINSTANCE.getDatabaseProperty_DbDriverx();

    /**
     * The meta object literal for the '<em><b>Db Driver</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATABASE_PROPERTY__DB_DRIVER = eINSTANCE.getDatabaseProperty_DbDriver();

    /**
     * The meta object literal for the '<em><b>Db Execute Before</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATABASE_PROPERTY__DB_EXECUTE_BEFORE = eINSTANCE.getDatabaseProperty_DbExecuteBefore();

    /**
     * The meta object literal for the '<em><b>Db Execute After</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATABASE_PROPERTY__DB_EXECUTE_AFTER = eINSTANCE.getDatabaseProperty_DbExecuteAfter();

    /**
     * The meta object literal for the '<em><b>Db Index Types</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATABASE_PROPERTY__DB_INDEX_TYPES = eINSTANCE.getDatabaseProperty_DbIndexTypes();

    /**
     * The meta object literal for the '<em><b>Db Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATABASE_PROPERTY__DB_TYPE = eINSTANCE.getDatabaseProperty_DbType();

    /**
     * The meta object literal for the '<em><b>Db Meta Info</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATABASE_PROPERTY__DB_META_INFO = eINSTANCE.getDatabaseProperty_DbMetaInfo();

    /**
     * The meta object literal for the '<em><b>Db Driver Info</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATABASE_PROPERTY__DB_DRIVER_INFO = eINSTANCE.getDatabaseProperty_DbDriverInfo();

    /**
     * The meta object literal for the '<em><b>Db Methods Calls</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATABASE_PROPERTY__DB_METHODS_CALLS = eINSTANCE.getDatabaseProperty_DbMethodsCalls();

    /**
     * The meta object literal for the '<em><b>Debug</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATABASE_PROPERTY__DEBUG = eINSTANCE.getDatabaseProperty_Debug();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojogenPropertyImpl <em>Pojogen Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojogenPropertyImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojogenProperty()
     * @generated
     */
    EClass POJOGEN_PROPERTY = eINSTANCE.getPojogenProperty();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJOGEN_PROPERTY__NAME = eINSTANCE.getPojogenProperty_Name();

    /**
     * The meta object literal for the '<em><b>Sql Types</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__SQL_TYPES = eINSTANCE.getPojogenProperty_SqlTypes();

    /**
     * The meta object literal for the '<em><b>Db Table</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJOGEN_PROPERTY__DB_TABLE = eINSTANCE.getPojogenProperty_DbTable();

    /**
     * The meta object literal for the '<em><b>Column Types</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__COLUMN_TYPES = eINSTANCE.getPojogenProperty_ColumnTypes();

    /**
     * The meta object literal for the '<em><b>Db Procedure</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJOGEN_PROPERTY__DB_PROCEDURE = eINSTANCE.getPojogenProperty_DbProcedure();

    /**
     * The meta object literal for the '<em><b>Db Function</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJOGEN_PROPERTY__DB_FUNCTION = eINSTANCE.getPojogenProperty_DbFunction();

    /**
     * The meta object literal for the '<em><b>Column Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__COLUMN_TYPE = eINSTANCE.getPojogenProperty_ColumnType();

    /**
     * The meta object literal for the '<em><b>Db Tables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJOGEN_PROPERTY__DB_TABLES = eINSTANCE.getPojogenProperty_DbTables();

    /**
     * The meta object literal for the '<em><b>Join Tables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__JOIN_TABLES = eINSTANCE.getPojogenProperty_JoinTables();

    /**
     * The meta object literal for the '<em><b>Db Columns</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJOGEN_PROPERTY__DB_COLUMNS = eINSTANCE.getPojogenProperty_DbColumns();

    /**
     * The meta object literal for the '<em><b>Tables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__TABLES = eINSTANCE.getPojogenProperty_Tables();

    /**
     * The meta object literal for the '<em><b>Columns</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__COLUMNS = eINSTANCE.getPojogenProperty_Columns();

    /**
     * The meta object literal for the '<em><b>Exports</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__EXPORTS = eINSTANCE.getPojogenProperty_Exports();

    /**
     * The meta object literal for the '<em><b>Imports</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__IMPORTS = eINSTANCE.getPojogenProperty_Imports();

    /**
     * The meta object literal for the '<em><b>Many2s</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__MANY2S = eINSTANCE.getPojogenProperty_Many2s();

    /**
     * The meta object literal for the '<em><b>Db Column</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJOGEN_PROPERTY__DB_COLUMN = eINSTANCE.getPojogenProperty_DbColumn();

    /**
     * The meta object literal for the '<em><b>Inheritance</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__INHERITANCE = eINSTANCE.getPojogenProperty_Inheritance();

    /**
     * The meta object literal for the '<em><b>Methods</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJOGEN_PROPERTY__METHODS = eINSTANCE.getPojogenProperty_Methods();

    /**
     * The meta object literal for the '<em><b>Operators Suffix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJOGEN_PROPERTY__OPERATORS_SUFFIX = eINSTANCE.getPojogenProperty_OperatorsSuffix();

    /**
     * The meta object literal for the '<em><b>To Implements</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__TO_IMPLEMENTS = eINSTANCE.getPojogenProperty_ToImplements();

    /**
     * The meta object literal for the '<em><b>To Extends</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__TO_EXTENDS = eINSTANCE.getPojogenProperty_ToExtends();

    /**
     * The meta object literal for the '<em><b>To Implements Generics</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__TO_IMPLEMENTS_GENERICS = eINSTANCE.getPojogenProperty_ToImplementsGenerics();

    /**
     * The meta object literal for the '<em><b>To Extends Generics</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__TO_EXTENDS_GENERICS = eINSTANCE.getPojogenProperty_ToExtendsGenerics();

    /**
     * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJOGEN_PROPERTY__VERSION = eINSTANCE.getPojogenProperty_Version();

    /**
     * The meta object literal for the '<em><b>Db Not Tables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJOGEN_PROPERTY__DB_NOT_TABLES = eINSTANCE.getPojogenProperty_DbNotTables();

    /**
     * The meta object literal for the '<em><b>Debug</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__DEBUG = eINSTANCE.getPojogenProperty_Debug();

    /**
     * The meta object literal for the '<em><b>Proc Pojos</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__PROC_POJOS = eINSTANCE.getPojogenProperty_ProcPojos();

    /**
     * The meta object literal for the '<em><b>Fun Pojos</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJOGEN_PROPERTY__FUN_POJOS = eINSTANCE.getPojogenProperty_FunPojos();

    /**
     * The meta object literal for the '<em><b>Active Filter</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJOGEN_PROPERTY__ACTIVE_FILTER = eINSTANCE.getPojogenProperty_ActiveFilter();

    /**
     * The meta object literal for the '<em><b>Pckg</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJOGEN_PROPERTY__PCKG = eINSTANCE.getPojogenProperty_Pckg();

    /**
     * The meta object literal for the '<em><b>Enum Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJOGEN_PROPERTY__ENUM_NAME = eINSTANCE.getPojogenProperty_EnumName();

    /**
     * The meta object literal for the '<em><b>Db Check Constraints</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJOGEN_PROPERTY__DB_CHECK_CONSTRAINTS = eINSTANCE.getPojogenProperty_DbCheckConstraints();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.MetaTypeAssignementImpl <em>Meta Type Assignement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.MetaTypeAssignementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMetaTypeAssignement()
     * @generated
     */
    EClass META_TYPE_ASSIGNEMENT = eINSTANCE.getMetaTypeAssignement();

    /**
     * The meta object literal for the '<em><b>Db Column</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute META_TYPE_ASSIGNEMENT__DB_COLUMN = eINSTANCE.getMetaTypeAssignement_DbColumn();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute META_TYPE_ASSIGNEMENT__TYPE = eINSTANCE.getMetaTypeAssignement_Type();

    /**
     * The meta object literal for the '<em><b>Extension</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute META_TYPE_ASSIGNEMENT__EXTENSION = eINSTANCE.getMetaTypeAssignement_Extension();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.MetagenPropertyImpl <em>Metagen Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.MetagenPropertyImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMetagenProperty()
     * @generated
     */
    EClass METAGEN_PROPERTY = eINSTANCE.getMetagenProperty();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METAGEN_PROPERTY__NAME = eINSTANCE.getMetagenProperty_Name();

    /**
     * The meta object literal for the '<em><b>Sequence</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METAGEN_PROPERTY__SEQUENCE = eINSTANCE.getMetagenProperty_Sequence();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METAGEN_PROPERTY__TYPE = eINSTANCE.getMetagenProperty_Type();

    /**
     * The meta object literal for the '<em><b>Db Tables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METAGEN_PROPERTY__DB_TABLES = eINSTANCE.getMetagenProperty_DbTables();

    /**
     * The meta object literal for the '<em><b>Db Not Tables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METAGEN_PROPERTY__DB_NOT_TABLES = eINSTANCE.getMetagenProperty_DbNotTables();

    /**
     * The meta object literal for the '<em><b>Db Table</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METAGEN_PROPERTY__DB_TABLE = eINSTANCE.getMetagenProperty_DbTable();

    /**
     * The meta object literal for the '<em><b>Identity</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METAGEN_PROPERTY__IDENTITY = eINSTANCE.getMetagenProperty_Identity();

    /**
     * The meta object literal for the '<em><b>Meta Types</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference METAGEN_PROPERTY__META_TYPES = eINSTANCE.getMetagenProperty_MetaTypes();

    /**
     * The meta object literal for the '<em><b>Db Statement</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METAGEN_PROPERTY__DB_STATEMENT = eINSTANCE.getMetagenProperty_DbStatement();

    /**
     * The meta object literal for the '<em><b>Db Columns</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METAGEN_PROPERTY__DB_COLUMNS = eINSTANCE.getMetagenProperty_DbColumns();

    /**
     * The meta object literal for the '<em><b>Db Function</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METAGEN_PROPERTY__DB_FUNCTION = eINSTANCE.getMetagenProperty_DbFunction();

    /**
     * The meta object literal for the '<em><b>Db Procedure</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METAGEN_PROPERTY__DB_PROCEDURE = eINSTANCE.getMetagenProperty_DbProcedure();

    /**
     * The meta object literal for the '<em><b>Debug</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference METAGEN_PROPERTY__DEBUG = eINSTANCE.getMetagenProperty_Debug();

    /**
     * The meta object literal for the '<em><b>Optional Features</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METAGEN_PROPERTY__OPTIONAL_FEATURES = eINSTANCE.getMetagenProperty_OptionalFeatures();

    /**
     * The meta object literal for the '<em><b>Active Filter</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METAGEN_PROPERTY__ACTIVE_FILTER = eINSTANCE.getMetagenProperty_ActiveFilter();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DaogenPropertyImpl <em>Daogen Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DaogenPropertyImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDaogenProperty()
     * @generated
     */
    EClass DAOGEN_PROPERTY = eINSTANCE.getDaogenProperty();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DAOGEN_PROPERTY__NAME = eINSTANCE.getDaogenProperty_Name();

    /**
     * The meta object literal for the '<em><b>Db Tables</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DAOGEN_PROPERTY__DB_TABLES = eINSTANCE.getDaogenProperty_DbTables();

    /**
     * The meta object literal for the '<em><b>To Implements</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DAOGEN_PROPERTY__TO_IMPLEMENTS = eINSTANCE.getDaogenProperty_ToImplements();

    /**
     * The meta object literal for the '<em><b>To Extends</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DAOGEN_PROPERTY__TO_EXTENDS = eINSTANCE.getDaogenProperty_ToExtends();

    /**
     * The meta object literal for the '<em><b>To Implements Generics</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DAOGEN_PROPERTY__TO_IMPLEMENTS_GENERICS = eINSTANCE.getDaogenProperty_ToImplementsGenerics();

    /**
     * The meta object literal for the '<em><b>To Extends Generics</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DAOGEN_PROPERTY__TO_EXTENDS_GENERICS = eINSTANCE.getDaogenProperty_ToExtendsGenerics();

    /**
     * The meta object literal for the '<em><b>Db Function</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DAOGEN_PROPERTY__DB_FUNCTION = eINSTANCE.getDaogenProperty_DbFunction();

    /**
     * The meta object literal for the '<em><b>Result Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DAOGEN_PROPERTY__RESULT_TYPE = eINSTANCE.getDaogenProperty_ResultType();

    /**
     * The meta object literal for the '<em><b>Debug</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DAOGEN_PROPERTY__DEBUG = eINSTANCE.getDaogenProperty_Debug();

    /**
     * The meta object literal for the '<em><b>Active Filter</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DAOGEN_PROPERTY__ACTIVE_FILTER = eINSTANCE.getDaogenProperty_ActiveFilter();

    /**
     * The meta object literal for the '<em><b>Pckg</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DAOGEN_PROPERTY__PCKG = eINSTANCE.getDaogenProperty_Pckg();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDefinitionImpl <em>Pojo Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoDefinitionImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDefinition()
     * @generated
     */
    EClass POJO_DEFINITION = eINSTANCE.getPojoDefinition();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_DEFINITION__NAME = eINSTANCE.getPojoDefinition_Name();

    /**
     * The meta object literal for the '<em><b>Class</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_DEFINITION__CLASS = eINSTANCE.getPojoDefinition_Class();

    /**
     * The meta object literal for the '<em><b>Classx</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_DEFINITION__CLASSX = eINSTANCE.getPojoDefinition_Classx();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.TableDefinitionImpl <em>Table Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.TableDefinitionImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getTableDefinition()
     * @generated
     */
    EClass TABLE_DEFINITION = eINSTANCE.getTableDefinition();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TABLE_DEFINITION__NAME = eINSTANCE.getTableDefinition_Name();

    /**
     * The meta object literal for the '<em><b>Table</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TABLE_DEFINITION__TABLE = eINSTANCE.getTableDefinition_Table();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ProcedureDefinitionImpl <em>Procedure Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ProcedureDefinitionImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getProcedureDefinition()
     * @generated
     */
    EClass PROCEDURE_DEFINITION = eINSTANCE.getProcedureDefinition();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROCEDURE_DEFINITION__NAME = eINSTANCE.getProcedureDefinition_Name();

    /**
     * The meta object literal for the '<em><b>Table</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROCEDURE_DEFINITION__TABLE = eINSTANCE.getProcedureDefinition_Table();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.FunctionDefinitionImpl <em>Function Definition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.FunctionDefinitionImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getFunctionDefinition()
     * @generated
     */
    EClass FUNCTION_DEFINITION = eINSTANCE.getFunctionDefinition();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_DEFINITION__NAME = eINSTANCE.getFunctionDefinition_Name();

    /**
     * The meta object literal for the '<em><b>Table</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_DEFINITION__TABLE = eINSTANCE.getFunctionDefinition_Table();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.MetaStatementImpl <em>Meta Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.MetaStatementImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMetaStatement()
     * @generated
     */
    EClass META_STATEMENT = eINSTANCE.getMetaStatement();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute META_STATEMENT__NAME = eINSTANCE.getMetaStatement_Name();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute META_STATEMENT__TYPE = eINSTANCE.getMetaStatement_Type();

    /**
     * The meta object literal for the '<em><b>Modifiers</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute META_STATEMENT__MODIFIERS = eINSTANCE.getMetaStatement_Modifiers();

    /**
     * The meta object literal for the '<em><b>Statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference META_STATEMENT__STATEMENT = eINSTANCE.getMetaStatement_Statement();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.SqlImpl <em>Sql</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.SqlImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getSql()
     * @generated
     */
    EClass SQL = eINSTANCE.getSql();

    /**
     * The meta object literal for the '<em><b>Sqls</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SQL__SQLS = eINSTANCE.getSql_Sqls();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.SqlFragmentImpl <em>Sql Fragment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.SqlFragmentImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getSqlFragment()
     * @generated
     */
    EClass SQL_FRAGMENT = eINSTANCE.getSqlFragment();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SQL_FRAGMENT__VALUE = eINSTANCE.getSqlFragment_Value();

    /**
     * The meta object literal for the '<em><b>Col</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SQL_FRAGMENT__COL = eINSTANCE.getSqlFragment_Col();

    /**
     * The meta object literal for the '<em><b>Cnst</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SQL_FRAGMENT__CNST = eINSTANCE.getSqlFragment_Cnst();

    /**
     * The meta object literal for the '<em><b>Ident</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SQL_FRAGMENT__IDENT = eINSTANCE.getSqlFragment_Ident();

    /**
     * The meta object literal for the '<em><b>Cnst Oper</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SQL_FRAGMENT__CNST_OPER = eINSTANCE.getSqlFragment_CnstOper();

    /**
     * The meta object literal for the '<em><b>Ident Oper</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SQL_FRAGMENT__IDENT_OPER = eINSTANCE.getSqlFragment_IdentOper();

    /**
     * The meta object literal for the '<em><b>Meta</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SQL_FRAGMENT__META = eINSTANCE.getSqlFragment_Meta();

    /**
     * The meta object literal for the '<em><b>Dbtab</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SQL_FRAGMENT__DBTAB = eINSTANCE.getSqlFragment_Dbtab();

    /**
     * The meta object literal for the '<em><b>Dbcol</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SQL_FRAGMENT__DBCOL = eINSTANCE.getSqlFragment_Dbcol();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.MetaSqlImpl <em>Meta Sql</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.MetaSqlImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMetaSql()
     * @generated
     */
    EClass META_SQL = eINSTANCE.getMetaSql();

    /**
     * The meta object literal for the '<em><b>Ifs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference META_SQL__IFS = eINSTANCE.getMetaSql_Ifs();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute META_SQL__TYPE = eINSTANCE.getMetaSql_Type();

    /**
     * The meta object literal for the '<em><b>Cond</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference META_SQL__COND = eINSTANCE.getMetaSql_Cond();

    /**
     * The meta object literal for the '<em><b>Ftype</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute META_SQL__FTYPE = eINSTANCE.getMetaSql_Ftype();

    /**
     * The meta object literal for the '<em><b>Ord</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference META_SQL__ORD = eINSTANCE.getMetaSql_Ord();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.IfSqlImpl <em>If Sql</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.IfSqlImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getIfSql()
     * @generated
     */
    EClass IF_SQL = eINSTANCE.getIfSql();

    /**
     * The meta object literal for the '<em><b>Sqls</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_SQL__SQLS = eINSTANCE.getIfSql_Sqls();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.IfSqlFragmentImpl <em>If Sql Fragment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.IfSqlFragmentImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getIfSqlFragment()
     * @generated
     */
    EClass IF_SQL_FRAGMENT = eINSTANCE.getIfSqlFragment();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IF_SQL_FRAGMENT__VALUE = eINSTANCE.getIfSqlFragment_Value();

    /**
     * The meta object literal for the '<em><b>Col</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_SQL_FRAGMENT__COL = eINSTANCE.getIfSqlFragment_Col();

    /**
     * The meta object literal for the '<em><b>Cnst</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_SQL_FRAGMENT__CNST = eINSTANCE.getIfSqlFragment_Cnst();

    /**
     * The meta object literal for the '<em><b>Ident</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_SQL_FRAGMENT__IDENT = eINSTANCE.getIfSqlFragment_Ident();

    /**
     * The meta object literal for the '<em><b>Cnst Oper</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_SQL_FRAGMENT__CNST_OPER = eINSTANCE.getIfSqlFragment_CnstOper();

    /**
     * The meta object literal for the '<em><b>Ident Oper</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_SQL_FRAGMENT__IDENT_OPER = eINSTANCE.getIfSqlFragment_IdentOper();

    /**
     * The meta object literal for the '<em><b>Dbtab</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_SQL_FRAGMENT__DBTAB = eINSTANCE.getIfSqlFragment_Dbtab();

    /**
     * The meta object literal for the '<em><b>Dbcol</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_SQL_FRAGMENT__DBCOL = eINSTANCE.getIfSqlFragment_Dbcol();

    /**
     * The meta object literal for the '<em><b>Meta</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_SQL_FRAGMENT__META = eINSTANCE.getIfSqlFragment_Meta();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.IfMetaSqlImpl <em>If Meta Sql</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.IfMetaSqlImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getIfMetaSql()
     * @generated
     */
    EClass IF_META_SQL = eINSTANCE.getIfMetaSql();

    /**
     * The meta object literal for the '<em><b>Ifs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_META_SQL__IFS = eINSTANCE.getIfMetaSql_Ifs();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IF_META_SQL__TYPE = eINSTANCE.getIfMetaSql_Type();

    /**
     * The meta object literal for the '<em><b>Cond</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_META_SQL__COND = eINSTANCE.getIfMetaSql_Cond();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.IfSqlCondImpl <em>If Sql Cond</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.IfSqlCondImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getIfSqlCond()
     * @generated
     */
    EClass IF_SQL_COND = eINSTANCE.getIfSqlCond();

    /**
     * The meta object literal for the '<em><b>Bool1</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_SQL_COND__BOOL1 = eINSTANCE.getIfSqlCond_Bool1();

    /**
     * The meta object literal for the '<em><b>Oper</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IF_SQL_COND__OPER = eINSTANCE.getIfSqlCond_Oper();

    /**
     * The meta object literal for the '<em><b>Bool2</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_SQL_COND__BOOL2 = eINSTANCE.getIfSqlCond_Bool2();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.IfSqlBoolImpl <em>If Sql Bool</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.IfSqlBoolImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getIfSqlBool()
     * @generated
     */
    EClass IF_SQL_BOOL = eINSTANCE.getIfSqlBool();

    /**
     * The meta object literal for the '<em><b>Not</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IF_SQL_BOOL__NOT = eINSTANCE.getIfSqlBool_Not();

    /**
     * The meta object literal for the '<em><b>Cnst</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_SQL_BOOL__CNST = eINSTANCE.getIfSqlBool_Cnst();

    /**
     * The meta object literal for the '<em><b>Ident</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_SQL_BOOL__IDENT = eINSTANCE.getIfSqlBool_Ident();

    /**
     * The meta object literal for the '<em><b>Cond</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_SQL_BOOL__COND = eINSTANCE.getIfSqlBool_Cond();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.OrdSqlImpl <em>Ord Sql</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.OrdSqlImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getOrdSql()
     * @generated
     */
    EClass ORD_SQL = eINSTANCE.getOrdSql();

    /**
     * The meta object literal for the '<em><b>Sqls</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ORD_SQL__SQLS = eINSTANCE.getOrdSql_Sqls();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.OrdSql2Impl <em>Ord Sql2</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.OrdSql2Impl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getOrdSql2()
     * @generated
     */
    EClass ORD_SQL2 = eINSTANCE.getOrdSql2();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ORD_SQL2__VALUE = eINSTANCE.getOrdSql2_Value();

    /**
     * The meta object literal for the '<em><b>Cnst</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ORD_SQL2__CNST = eINSTANCE.getOrdSql2_Cnst();

    /**
     * The meta object literal for the '<em><b>Ident</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ORD_SQL2__IDENT = eINSTANCE.getOrdSql2_Ident();

    /**
     * The meta object literal for the '<em><b>Dbcol</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ORD_SQL2__DBCOL = eINSTANCE.getOrdSql2_Dbcol();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ColumnImpl <em>Column</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ColumnImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getColumn()
     * @generated
     */
    EClass COLUMN = eINSTANCE.getColumn();

    /**
     * The meta object literal for the '<em><b>Columns</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COLUMN__COLUMNS = eINSTANCE.getColumn_Columns();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ExtendedColumnImpl <em>Extended Column</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ExtendedColumnImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getExtendedColumn()
     * @generated
     */
    EClass EXTENDED_COLUMN = eINSTANCE.getExtendedColumn();

    /**
     * The meta object literal for the '<em><b>Col</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENDED_COLUMN__COL = eINSTANCE.getExtendedColumn_Col();

    /**
     * The meta object literal for the '<em><b>Modifiers</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXTENDED_COLUMN__MODIFIERS = eINSTANCE.getExtendedColumn_Modifiers();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ExtendedColumnNameImpl <em>Extended Column Name</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ExtendedColumnNameImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getExtendedColumnName()
     * @generated
     */
    EClass EXTENDED_COLUMN_NAME = eINSTANCE.getExtendedColumnName();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXTENDED_COLUMN_NAME__NAME = eINSTANCE.getExtendedColumnName_Name();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ConstantImpl <em>Constant</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ConstantImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getConstant()
     * @generated
     */
    EClass CONSTANT = eINSTANCE.getConstant();

    /**
     * The meta object literal for the '<em><b>Case</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONSTANT__CASE = eINSTANCE.getConstant_Case();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONSTANT__NAME = eINSTANCE.getConstant_Name();

    /**
     * The meta object literal for the '<em><b>Modifiers</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONSTANT__MODIFIERS = eINSTANCE.getConstant_Modifiers();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.IdentifierImpl <em>Identifier</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.IdentifierImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getIdentifier()
     * @generated
     */
    EClass IDENTIFIER = eINSTANCE.getIdentifier();

    /**
     * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IDENTIFIER__MODE = eINSTANCE.getIdentifier_Mode();

    /**
     * The meta object literal for the '<em><b>Case</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IDENTIFIER__CASE = eINSTANCE.getIdentifier_Case();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IDENTIFIER__NAME = eINSTANCE.getIdentifier_Name();

    /**
     * The meta object literal for the '<em><b>Modifiers</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IDENTIFIER__MODIFIERS = eINSTANCE.getIdentifier_Modifiers();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ConstantOperatorImpl <em>Constant Operator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ConstantOperatorImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getConstantOperator()
     * @generated
     */
    EClass CONSTANT_OPERATOR = eINSTANCE.getConstantOperator();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONSTANT_OPERATOR__NAME = eINSTANCE.getConstantOperator_Name();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.IdentifierOperatorImpl <em>Identifier Operator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.IdentifierOperatorImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getIdentifierOperator()
     * @generated
     */
    EClass IDENTIFIER_OPERATOR = eINSTANCE.getIdentifierOperator();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IDENTIFIER_OPERATOR__NAME = eINSTANCE.getIdentifierOperator_Name();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DatabaseColumnImpl <em>Database Column</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DatabaseColumnImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDatabaseColumn()
     * @generated
     */
    EClass DATABASE_COLUMN = eINSTANCE.getDatabaseColumn();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATABASE_COLUMN__NAME = eINSTANCE.getDatabaseColumn_Name();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DatabaseTableImpl <em>Database Table</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DatabaseTableImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDatabaseTable()
     * @generated
     */
    EClass DATABASE_TABLE = eINSTANCE.getDatabaseTable();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATABASE_TABLE__NAME = eINSTANCE.getDatabaseTable_Name();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.MappingRuleImpl <em>Mapping Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.MappingRuleImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMappingRule()
     * @generated
     */
    EClass MAPPING_RULE = eINSTANCE.getMappingRule();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MAPPING_RULE__NAME = eINSTANCE.getMappingRule_Name();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MAPPING_RULE__TYPE = eINSTANCE.getMappingRule_Type();

    /**
     * The meta object literal for the '<em><b>Modifiers</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MAPPING_RULE__MODIFIERS = eINSTANCE.getMappingRule_Modifiers();

    /**
     * The meta object literal for the '<em><b>Mapping</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING_RULE__MAPPING = eINSTANCE.getMappingRule_Mapping();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.MappingImpl <em>Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.MappingImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMapping()
     * @generated
     */
    EClass MAPPING = eINSTANCE.getMapping();

    /**
     * The meta object literal for the '<em><b>Mapping Items</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING__MAPPING_ITEMS = eINSTANCE.getMapping_MappingItems();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.MappingItemImpl <em>Mapping Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.MappingItemImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMappingItem()
     * @generated
     */
    EClass MAPPING_ITEM = eINSTANCE.getMappingItem();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MAPPING_ITEM__NAME = eINSTANCE.getMappingItem_Name();

    /**
     * The meta object literal for the '<em><b>Attr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING_ITEM__ATTR = eINSTANCE.getMappingItem_Attr();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.MappingColumnImpl <em>Mapping Column</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.MappingColumnImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMappingColumn()
     * @generated
     */
    EClass MAPPING_COLUMN = eINSTANCE.getMappingColumn();

    /**
     * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MAPPING_COLUMN__ITEMS = eINSTANCE.getMappingColumn_Items();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ExtendedMappingItemImpl <em>Extended Mapping Item</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ExtendedMappingItemImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getExtendedMappingItem()
     * @generated
     */
    EClass EXTENDED_MAPPING_ITEM = eINSTANCE.getExtendedMappingItem();

    /**
     * The meta object literal for the '<em><b>Attr</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENDED_MAPPING_ITEM__ATTR = eINSTANCE.getExtendedMappingItem_Attr();

    /**
     * The meta object literal for the '<em><b>Modifiers</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXTENDED_MAPPING_ITEM__MODIFIERS = eINSTANCE.getExtendedMappingItem_Modifiers();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.MappingColumnNameImpl <em>Mapping Column Name</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.MappingColumnNameImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getMappingColumnName()
     * @generated
     */
    EClass MAPPING_COLUMN_NAME = eINSTANCE.getMappingColumnName();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MAPPING_COLUMN_NAME__NAME = eINSTANCE.getMappingColumnName_Name();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.OptionalFeatureImpl <em>Optional Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.OptionalFeatureImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getOptionalFeature()
     * @generated
     */
    EClass OPTIONAL_FEATURE = eINSTANCE.getOptionalFeature();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OPTIONAL_FEATURE__NAME = eINSTANCE.getOptionalFeature_Name();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OPTIONAL_FEATURE__TYPE = eINSTANCE.getOptionalFeature_Type();

    /**
     * The meta object literal for the '<em><b>Modifiers</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OPTIONAL_FEATURE__MODIFIERS = eINSTANCE.getOptionalFeature_Modifiers();

    /**
     * The meta object literal for the '<em><b>Option</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OPTIONAL_FEATURE__OPTION = eINSTANCE.getOptionalFeature_Option();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoTypeImpl <em>Pojo Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoTypeImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoType()
     * @generated
     */
    EClass POJO_TYPE = eINSTANCE.getPojoType();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_TYPE__TYPE = eINSTANCE.getPojoType_Type();

    /**
     * The meta object literal for the '<em><b>Ref</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_TYPE__REF = eINSTANCE.getPojoType_Ref();

    /**
     * The meta object literal for the '<em><b>Gtype</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_TYPE__GTYPE = eINSTANCE.getPojoType_Gtype();

    /**
     * The meta object literal for the '<em><b>Gref</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_TYPE__GREF = eINSTANCE.getPojoType_Gref();

    /**
     * The meta object literal for the '<em><b>Array</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_TYPE__ARRAY = eINSTANCE.getPojoType_Array();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PackageDirectiveImpl <em>Package Directive</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PackageDirectiveImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPackageDirective()
     * @generated
     */
    EClass PACKAGE_DIRECTIVE = eINSTANCE.getPackageDirective();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PackageImpl <em>Package</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PackageImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPackage()
     * @generated
     */
    EClass PACKAGE = eINSTANCE.getPackage();

    /**
     * The meta object literal for the '<em><b>Directives</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PACKAGE__DIRECTIVES = eINSTANCE.getPackage_Directives();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PACKAGE__NAME = eINSTANCE.getPackage_Name();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PACKAGE__ELEMENTS = eINSTANCE.getPackage_Elements();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveImpl <em>Annotation Directive</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirective()
     * @generated
     */
    EClass ANNOTATION_DIRECTIVE = eINSTANCE.getAnnotationDirective();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationImpl <em>Annotation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.AnnotationImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotation()
     * @generated
     */
    EClass ANNOTATION = eINSTANCE.getAnnotation();

    /**
     * The meta object literal for the '<em><b>Directives</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANNOTATION__DIRECTIVES = eINSTANCE.getAnnotation_Directives();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANNOTATION__TYPE = eINSTANCE.getAnnotation_Type();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANNOTATION__FEATURES = eINSTANCE.getAnnotation_Features();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationPropertyImpl <em>Annotation Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.AnnotationPropertyImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationProperty()
     * @generated
     */
    EClass ANNOTATION_PROPERTY = eINSTANCE.getAnnotationProperty();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANNOTATION_PROPERTY__NAME = eINSTANCE.getAnnotationProperty_Name();

    /**
     * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANNOTATION_PROPERTY__NUMBER = eINSTANCE.getAnnotationProperty_Number();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANNOTATION_PROPERTY__VALUE = eINSTANCE.getAnnotationProperty_Value();

    /**
     * The meta object literal for the '<em><b>Constant</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ANNOTATION_PROPERTY__CONSTANT = eINSTANCE.getAnnotationProperty_Constant();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANNOTATION_PROPERTY__TYPE = eINSTANCE.getAnnotationProperty_Type();

    /**
     * The meta object literal for the '<em><b>Ref</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANNOTATION_PROPERTY__REF = eINSTANCE.getAnnotationProperty_Ref();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.EntityImpl <em>Entity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.EntityImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEntity()
     * @generated
     */
    EClass ENTITY = eINSTANCE.getEntity();

    /**
     * The meta object literal for the '<em><b>Directives</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY__DIRECTIVES = eINSTANCE.getEntity_Directives();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY__NAME = eINSTANCE.getEntity_Name();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotatedEntityImpl <em>Annotated Entity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.AnnotatedEntityImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotatedEntity()
     * @generated
     */
    EClass ANNOTATED_ENTITY = eINSTANCE.getAnnotatedEntity();

    /**
     * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANNOTATED_ENTITY__ANNOTATIONS = eINSTANCE.getAnnotatedEntity_Annotations();

    /**
     * The meta object literal for the '<em><b>Entity</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANNOTATED_ENTITY__ENTITY = eINSTANCE.getAnnotatedEntity_Entity();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.AbstractPojoEntityImpl <em>Abstract Pojo Entity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.AbstractPojoEntityImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAbstractPojoEntity()
     * @generated
     */
    EClass ABSTRACT_POJO_ENTITY = eINSTANCE.getAbstractPojoEntity();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ImportImpl <em>Import</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ImportImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getImport()
     * @generated
     */
    EClass IMPORT = eINSTANCE.getImport();

    /**
     * The meta object literal for the '<em><b>Imported Namespace</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPORT__IMPORTED_NAMESPACE = eINSTANCE.getImport_ImportedNamespace();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ImplementsExtendsDirectiveImpl <em>Implements Extends Directive</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ImplementsExtendsDirectiveImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getImplementsExtendsDirective()
     * @generated
     */
    EClass IMPLEMENTS_EXTENDS_DIRECTIVE = eINSTANCE.getImplementsExtendsDirective();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ImplementsImpl <em>Implements</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ImplementsImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getImplements()
     * @generated
     */
    EClass IMPLEMENTS = eINSTANCE.getImplements();

    /**
     * The meta object literal for the '<em><b>Directives</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IMPLEMENTS__DIRECTIVES = eINSTANCE.getImplements_Directives();

    /**
     * The meta object literal for the '<em><b>Implements</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IMPLEMENTS__IMPLEMENTS = eINSTANCE.getImplements_Implements();

    /**
     * The meta object literal for the '<em><b>Only Pojos</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IMPLEMENTS__ONLY_POJOS = eINSTANCE.getImplements_OnlyPojos();

    /**
     * The meta object literal for the '<em><b>Only Daos</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IMPLEMENTS__ONLY_DAOS = eINSTANCE.getImplements_OnlyDaos();

    /**
     * The meta object literal for the '<em><b>Except Pojos</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IMPLEMENTS__EXCEPT_POJOS = eINSTANCE.getImplements_ExceptPojos();

    /**
     * The meta object literal for the '<em><b>Except Daos</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IMPLEMENTS__EXCEPT_DAOS = eINSTANCE.getImplements_ExceptDaos();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ExtendsImpl <em>Extends</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ExtendsImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getExtends()
     * @generated
     */
    EClass EXTENDS = eINSTANCE.getExtends();

    /**
     * The meta object literal for the '<em><b>Directives</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENDS__DIRECTIVES = eINSTANCE.getExtends_Directives();

    /**
     * The meta object literal for the '<em><b>Extends</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENDS__EXTENDS = eINSTANCE.getExtends_Extends();

    /**
     * The meta object literal for the '<em><b>Only Pojos</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENDS__ONLY_POJOS = eINSTANCE.getExtends_OnlyPojos();

    /**
     * The meta object literal for the '<em><b>Only Daos</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENDS__ONLY_DAOS = eINSTANCE.getExtends_OnlyDaos();

    /**
     * The meta object literal for the '<em><b>Except Pojos</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENDS__EXCEPT_POJOS = eINSTANCE.getExtends_ExceptPojos();

    /**
     * The meta object literal for the '<em><b>Except Daos</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENDS__EXCEPT_DAOS = eINSTANCE.getExtends_ExceptDaos();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoEntityModifier1Impl <em>Pojo Entity Modifier1</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoEntityModifier1Impl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoEntityModifier1()
     * @generated
     */
    EClass POJO_ENTITY_MODIFIER1 = eINSTANCE.getPojoEntityModifier1();

    /**
     * The meta object literal for the '<em><b>Final</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_ENTITY_MODIFIER1__FINAL = eINSTANCE.getPojoEntityModifier1_Final();

    /**
     * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_ENTITY_MODIFIER1__ABSTRACT = eINSTANCE.getPojoEntityModifier1_Abstract();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DirectivePropertiesImpl <em>Directive Properties</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DirectivePropertiesImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDirectiveProperties()
     * @generated
     */
    EClass DIRECTIVE_PROPERTIES = eINSTANCE.getDirectiveProperties();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DIRECTIVE_PROPERTIES__FEATURES = eINSTANCE.getDirectiveProperties_Features();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveImpl <em>Pojo Directive</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirective()
     * @generated
     */
    EClass POJO_DIRECTIVE = eINSTANCE.getPojoDirective();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoEntityModifier2Impl <em>Pojo Entity Modifier2</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoEntityModifier2Impl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoEntityModifier2()
     * @generated
     */
    EClass POJO_ENTITY_MODIFIER2 = eINSTANCE.getPojoEntityModifier2();

    /**
     * The meta object literal for the '<em><b>Super Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_ENTITY_MODIFIER2__SUPER_TYPE = eINSTANCE.getPojoEntityModifier2_SuperType();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoEntityImpl <em>Pojo Entity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoEntityImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoEntity()
     * @generated
     */
    EClass POJO_ENTITY = eINSTANCE.getPojoEntity();

    /**
     * The meta object literal for the '<em><b>Modifiers1</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_ENTITY__MODIFIERS1 = eINSTANCE.getPojoEntity_Modifiers1();

    /**
     * The meta object literal for the '<em><b>Modifiers2</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_ENTITY__MODIFIERS2 = eINSTANCE.getPojoEntity_Modifiers2();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_ENTITY__FEATURES = eINSTANCE.getPojoEntity_Features();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoAnnotatedPropertyImpl <em>Pojo Annotated Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoAnnotatedPropertyImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoAnnotatedProperty()
     * @generated
     */
    EClass POJO_ANNOTATED_PROPERTY = eINSTANCE.getPojoAnnotatedProperty();

    /**
     * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_ANNOTATED_PROPERTY__ANNOTATIONS = eINSTANCE.getPojoAnnotatedProperty_Annotations();

    /**
     * The meta object literal for the '<em><b>Feature</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_ANNOTATED_PROPERTY__FEATURE = eINSTANCE.getPojoAnnotatedProperty_Feature();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveImpl <em>Pojo Property Directive</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirective()
     * @generated
     */
    EClass POJO_PROPERTY_DIRECTIVE = eINSTANCE.getPojoPropertyDirective();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyImpl <em>Pojo Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoProperty()
     * @generated
     */
    EClass POJO_PROPERTY = eINSTANCE.getPojoProperty();

    /**
     * The meta object literal for the '<em><b>Directives</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_PROPERTY__DIRECTIVES = eINSTANCE.getPojoProperty_Directives();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_PROPERTY__NAME = eINSTANCE.getPojoProperty_Name();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_PROPERTY__TYPE = eINSTANCE.getPojoProperty_Type();

    /**
     * The meta object literal for the '<em><b>Ref</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_PROPERTY__REF = eINSTANCE.getPojoProperty_Ref();

    /**
     * The meta object literal for the '<em><b>Gtype</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_PROPERTY__GTYPE = eINSTANCE.getPojoProperty_Gtype();

    /**
     * The meta object literal for the '<em><b>Gref</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_PROPERTY__GREF = eINSTANCE.getPojoProperty_Gref();

    /**
     * The meta object literal for the '<em><b>Array</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_PROPERTY__ARRAY = eINSTANCE.getPojoProperty_Array();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.EnumEntityModifier1Impl <em>Enum Entity Modifier1</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.EnumEntityModifier1Impl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEnumEntityModifier1()
     * @generated
     */
    EClass ENUM_ENTITY_MODIFIER1 = eINSTANCE.getEnumEntityModifier1();

    /**
     * The meta object literal for the '<em><b>Final</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENUM_ENTITY_MODIFIER1__FINAL = eINSTANCE.getEnumEntityModifier1_Final();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.EnumEntityModifier2Impl <em>Enum Entity Modifier2</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.EnumEntityModifier2Impl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEnumEntityModifier2()
     * @generated
     */
    EClass ENUM_ENTITY_MODIFIER2 = eINSTANCE.getEnumEntityModifier2();

    /**
     * The meta object literal for the '<em><b>Super Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_ENTITY_MODIFIER2__SUPER_TYPE = eINSTANCE.getEnumEntityModifier2_SuperType();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.EnumEntityImpl <em>Enum Entity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.EnumEntityImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEnumEntity()
     * @generated
     */
    EClass ENUM_ENTITY = eINSTANCE.getEnumEntity();

    /**
     * The meta object literal for the '<em><b>Modifiers1</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_ENTITY__MODIFIERS1 = eINSTANCE.getEnumEntity_Modifiers1();

    /**
     * The meta object literal for the '<em><b>Modifiers2</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_ENTITY__MODIFIERS2 = eINSTANCE.getEnumEntity_Modifiers2();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_ENTITY__FEATURES = eINSTANCE.getEnumEntity_Features();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.EnumPropertyValueImpl <em>Enum Property Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.EnumPropertyValueImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEnumPropertyValue()
     * @generated
     */
    EClass ENUM_PROPERTY_VALUE = eINSTANCE.getEnumPropertyValue();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENUM_PROPERTY_VALUE__NAME = eINSTANCE.getEnumPropertyValue_Name();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENUM_PROPERTY_VALUE__VALUE = eINSTANCE.getEnumPropertyValue_Value();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.EnumPropertyDirectiveImpl <em>Enum Property Directive</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.EnumPropertyDirectiveImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEnumPropertyDirective()
     * @generated
     */
    EClass ENUM_PROPERTY_DIRECTIVE = eINSTANCE.getEnumPropertyDirective();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.EnumPropertyImpl <em>Enum Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.EnumPropertyImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEnumProperty()
     * @generated
     */
    EClass ENUM_PROPERTY = eINSTANCE.getEnumProperty();

    /**
     * The meta object literal for the '<em><b>Directives</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_PROPERTY__DIRECTIVES = eINSTANCE.getEnumProperty_Directives();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENUM_PROPERTY__NAME = eINSTANCE.getEnumProperty_Name();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_PROPERTY__TYPE = eINSTANCE.getEnumProperty_Type();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DaoDirectiveParametersImpl <em>Dao Directive Parameters</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DaoDirectiveParametersImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDaoDirectiveParameters()
     * @generated
     */
    EClass DAO_DIRECTIVE_PARAMETERS = eINSTANCE.getDaoDirectiveParameters();

    /**
     * The meta object literal for the '<em><b>Out</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DAO_DIRECTIVE_PARAMETERS__OUT = eINSTANCE.getDaoDirectiveParameters_Out();

    /**
     * The meta object literal for the '<em><b>Ins</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DAO_DIRECTIVE_PARAMETERS__INS = eINSTANCE.getDaoDirectiveParameters_Ins();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DescendantAssignmentImpl <em>Descendant Assignment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DescendantAssignmentImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDescendantAssignment()
     * @generated
     */
    EClass DESCENDANT_ASSIGNMENT = eINSTANCE.getDescendantAssignment();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DESCENDANT_ASSIGNMENT__VALUE = eINSTANCE.getDescendantAssignment_Value();

    /**
     * The meta object literal for the '<em><b>Descendant</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DESCENDANT_ASSIGNMENT__DESCENDANT = eINSTANCE.getDescendantAssignment_Descendant();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.FunProcTypeImpl <em>Fun Proc Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.FunProcTypeImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getFunProcType()
     * @generated
     */
    EClass FUN_PROC_TYPE = eINSTANCE.getFunProcType();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DaoDirectiveImpl <em>Dao Directive</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DaoDirectiveImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDaoDirective()
     * @generated
     */
    EClass DAO_DIRECTIVE = eINSTANCE.getDaoDirective();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDaoModifierImpl <em>Pojo Dao Modifier</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoDaoModifierImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDaoModifier()
     * @generated
     */
    EClass POJO_DAO_MODIFIER = eINSTANCE.getPojoDaoModifier();

    /**
     * The meta object literal for the '<em><b>Super Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_DAO_MODIFIER__SUPER_TYPE = eINSTANCE.getPojoDaoModifier_SuperType();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDaoImpl <em>Pojo Dao</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoDaoImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDao()
     * @generated
     */
    EClass POJO_DAO = eINSTANCE.getPojoDao();

    /**
     * The meta object literal for the '<em><b>Directives</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_DAO__DIRECTIVES = eINSTANCE.getPojoDao_Directives();

    /**
     * The meta object literal for the '<em><b>Modifiers1</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_DAO__MODIFIERS1 = eINSTANCE.getPojoDao_Modifiers1();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_DAO__NAME = eINSTANCE.getPojoDao_Name();

    /**
     * The meta object literal for the '<em><b>Modifiers2</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_DAO__MODIFIERS2 = eINSTANCE.getPojoDao_Modifiers2();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PackageDirectiveSuffixImpl <em>Package Directive Suffix</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PackageDirectiveSuffixImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPackageDirectiveSuffix()
     * @generated
     */
    EClass PACKAGE_DIRECTIVE_SUFFIX = eINSTANCE.getPackageDirectiveSuffix();

    /**
     * The meta object literal for the '<em><b>Suffix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PACKAGE_DIRECTIVE_SUFFIX__SUFFIX = eINSTANCE.getPackageDirectiveSuffix_Suffix();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PackageDirectiveImplementationImpl <em>Package Directive Implementation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PackageDirectiveImplementationImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPackageDirectiveImplementation()
     * @generated
     */
    EClass PACKAGE_DIRECTIVE_IMPLEMENTATION = eINSTANCE.getPackageDirectiveImplementation();

    /**
     * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PACKAGE_DIRECTIVE_IMPLEMENTATION__IMPLEMENTATION = eINSTANCE.getPackageDirectiveImplementation_Implementation();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveConflictImpl <em>Annotation Directive Conflict</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveConflictImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirectiveConflict()
     * @generated
     */
    EClass ANNOTATION_DIRECTIVE_CONFLICT = eINSTANCE.getAnnotationDirectiveConflict();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveStaticImpl <em>Annotation Directive Static</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveStaticImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirectiveStatic()
     * @generated
     */
    EClass ANNOTATION_DIRECTIVE_STATIC = eINSTANCE.getAnnotationDirectiveStatic();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveConstructorImpl <em>Annotation Directive Constructor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveConstructorImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirectiveConstructor()
     * @generated
     */
    EClass ANNOTATION_DIRECTIVE_CONSTRUCTOR = eINSTANCE.getAnnotationDirectiveConstructor();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveStandardImpl <em>Annotation Directive Standard</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveStandardImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirectiveStandard()
     * @generated
     */
    EClass ANNOTATION_DIRECTIVE_STANDARD = eINSTANCE.getAnnotationDirectiveStandard();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveSetterImpl <em>Annotation Directive Setter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveSetterImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirectiveSetter()
     * @generated
     */
    EClass ANNOTATION_DIRECTIVE_SETTER = eINSTANCE.getAnnotationDirectiveSetter();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveGetterImpl <em>Annotation Directive Getter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveGetterImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirectiveGetter()
     * @generated
     */
    EClass ANNOTATION_DIRECTIVE_GETTER = eINSTANCE.getAnnotationDirectiveGetter();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveAttributeImpl <em>Annotation Directive Attribute</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.AnnotationDirectiveAttributeImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getAnnotationDirectiveAttribute()
     * @generated
     */
    EClass ANNOTATION_DIRECTIVE_ATTRIBUTE = eINSTANCE.getAnnotationDirectiveAttribute();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ImplementsExtendsDirectiveGenericsImpl <em>Implements Extends Directive Generics</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ImplementsExtendsDirectiveGenericsImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getImplementsExtendsDirectiveGenerics()
     * @generated
     */
    EClass IMPLEMENTS_EXTENDS_DIRECTIVE_GENERICS = eINSTANCE.getImplementsExtendsDirectiveGenerics();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveToStringImpl <em>Pojo Directive To String</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveToStringImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirectiveToString()
     * @generated
     */
    EClass POJO_DIRECTIVE_TO_STRING = eINSTANCE.getPojoDirectiveToString();

    /**
     * The meta object literal for the '<em><b>Proplist</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_DIRECTIVE_TO_STRING__PROPLIST = eINSTANCE.getPojoDirectiveToString_Proplist();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveIndexImpl <em>Pojo Directive Index</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveIndexImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirectiveIndex()
     * @generated
     */
    EClass POJO_DIRECTIVE_INDEX = eINSTANCE.getPojoDirectiveIndex();

    /**
     * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_DIRECTIVE_INDEX__INDEX = eINSTANCE.getPojoDirectiveIndex_Index();

    /**
     * The meta object literal for the '<em><b>Proplist</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_DIRECTIVE_INDEX__PROPLIST = eINSTANCE.getPojoDirectiveIndex_Proplist();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveOperatorsImpl <em>Pojo Directive Operators</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveOperatorsImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirectiveOperators()
     * @generated
     */
    EClass POJO_DIRECTIVE_OPERATORS = eINSTANCE.getPojoDirectiveOperators();

    /**
     * The meta object literal for the '<em><b>Operators Suffix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_DIRECTIVE_OPERATORS__OPERATORS_SUFFIX = eINSTANCE.getPojoDirectiveOperators_OperatorsSuffix();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveSerializableImpl <em>Pojo Directive Serializable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveSerializableImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirectiveSerializable()
     * @generated
     */
    EClass POJO_DIRECTIVE_SERIALIZABLE = eINSTANCE.getPojoDirectiveSerializable();

    /**
     * The meta object literal for the '<em><b>Sernum</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_DIRECTIVE_SERIALIZABLE__SERNUM = eINSTANCE.getPojoDirectiveSerializable_Sernum();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveDiscriminatorImpl <em>Pojo Directive Discriminator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveDiscriminatorImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirectiveDiscriminator()
     * @generated
     */
    EClass POJO_DIRECTIVE_DISCRIMINATOR = eINSTANCE.getPojoDirectiveDiscriminator();

    /**
     * The meta object literal for the '<em><b>Discriminator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_DIRECTIVE_DISCRIMINATOR__DISCRIMINATOR = eINSTANCE.getPojoDirectiveDiscriminator_Discriminator();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveEqualsImpl <em>Pojo Directive Equals</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveEqualsImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirectiveEquals()
     * @generated
     */
    EClass POJO_DIRECTIVE_EQUALS = eINSTANCE.getPojoDirectiveEquals();

    /**
     * The meta object literal for the '<em><b>Proplist</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_DIRECTIVE_EQUALS__PROPLIST = eINSTANCE.getPojoDirectiveEquals_Proplist();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveHashCodeImpl <em>Pojo Directive Hash Code</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoDirectiveHashCodeImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoDirectiveHashCode()
     * @generated
     */
    EClass POJO_DIRECTIVE_HASH_CODE = eINSTANCE.getPojoDirectiveHashCode();

    /**
     * The meta object literal for the '<em><b>Proplist</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POJO_DIRECTIVE_HASH_CODE__PROPLIST = eINSTANCE.getPojoDirectiveHashCode_Proplist();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveRequiredImpl <em>Pojo Property Directive Required</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveRequiredImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveRequired()
     * @generated
     */
    EClass POJO_PROPERTY_DIRECTIVE_REQUIRED = eINSTANCE.getPojoPropertyDirectiveRequired();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectivePrimaryKeyImpl <em>Pojo Property Directive Primary Key</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectivePrimaryKeyImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectivePrimaryKey()
     * @generated
     */
    EClass POJO_PROPERTY_DIRECTIVE_PRIMARY_KEY = eINSTANCE.getPojoPropertyDirectivePrimaryKey();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveDiscriminatorImpl <em>Pojo Property Directive Discriminator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveDiscriminatorImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveDiscriminator()
     * @generated
     */
    EClass POJO_PROPERTY_DIRECTIVE_DISCRIMINATOR = eINSTANCE.getPojoPropertyDirectiveDiscriminator();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveIndexImpl <em>Pojo Property Directive Index</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveIndexImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveIndex()
     * @generated
     */
    EClass POJO_PROPERTY_DIRECTIVE_INDEX = eINSTANCE.getPojoPropertyDirectiveIndex();

    /**
     * The meta object literal for the '<em><b>Index</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_PROPERTY_DIRECTIVE_INDEX__INDEX = eINSTANCE.getPojoPropertyDirectiveIndex_Index();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveVersionImpl <em>Pojo Property Directive Version</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveVersionImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveVersion()
     * @generated
     */
    EClass POJO_PROPERTY_DIRECTIVE_VERSION = eINSTANCE.getPojoPropertyDirectiveVersion();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveUpdateColImpl <em>Pojo Property Directive Update Col</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveUpdateColImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveUpdateCol()
     * @generated
     */
    EClass POJO_PROPERTY_DIRECTIVE_UPDATE_COL = eINSTANCE.getPojoPropertyDirectiveUpdateCol();

    /**
     * The meta object literal for the '<em><b>Update Column1</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_PROPERTY_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN1 = eINSTANCE.getPojoPropertyDirectiveUpdateCol_UpdateColumn1();

    /**
     * The meta object literal for the '<em><b>Update Column2</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_PROPERTY_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN2 = eINSTANCE.getPojoPropertyDirectiveUpdateCol_UpdateColumn2();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveCreateColImpl <em>Pojo Property Directive Create Col</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveCreateColImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveCreateCol()
     * @generated
     */
    EClass POJO_PROPERTY_DIRECTIVE_CREATE_COL = eINSTANCE.getPojoPropertyDirectiveCreateCol();

    /**
     * The meta object literal for the '<em><b>Create Column1</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_PROPERTY_DIRECTIVE_CREATE_COL__CREATE_COLUMN1 = eINSTANCE.getPojoPropertyDirectiveCreateCol_CreateColumn1();

    /**
     * The meta object literal for the '<em><b>Create Column2</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POJO_PROPERTY_DIRECTIVE_CREATE_COL__CREATE_COLUMN2 = eINSTANCE.getPojoPropertyDirectiveCreateCol_CreateColumn2();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveToInitImpl <em>Pojo Property Directive To Init</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveToInitImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveToInit()
     * @generated
     */
    EClass POJO_PROPERTY_DIRECTIVE_TO_INIT = eINSTANCE.getPojoPropertyDirectiveToInit();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveEnumInitImpl <em>Pojo Property Directive Enum Init</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveEnumInitImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveEnumInit()
     * @generated
     */
    EClass POJO_PROPERTY_DIRECTIVE_ENUM_INIT = eINSTANCE.getPojoPropertyDirectiveEnumInit();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveIsDefImpl <em>Pojo Property Directive Is Def</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveIsDefImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveIsDef()
     * @generated
     */
    EClass POJO_PROPERTY_DIRECTIVE_IS_DEF = eINSTANCE.getPojoPropertyDirectiveIsDef();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveEnumDefImpl <em>Pojo Property Directive Enum Def</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveEnumDefImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getPojoPropertyDirectiveEnumDef()
     * @generated
     */
    EClass POJO_PROPERTY_DIRECTIVE_ENUM_DEF = eINSTANCE.getPojoPropertyDirectiveEnumDef();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.EnumPropertyDirectiveValuesImpl <em>Enum Property Directive Values</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.EnumPropertyDirectiveValuesImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getEnumPropertyDirectiveValues()
     * @generated
     */
    EClass ENUM_PROPERTY_DIRECTIVE_VALUES = eINSTANCE.getEnumPropertyDirectiveValues();

    /**
     * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_PROPERTY_DIRECTIVE_VALUES__VALUES = eINSTANCE.getEnumPropertyDirectiveValues_Values();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.FunctionCallQueryImpl <em>Function Call Query</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.FunctionCallQueryImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getFunctionCallQuery()
     * @generated
     */
    EClass FUNCTION_CALL_QUERY = eINSTANCE.getFunctionCallQuery();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ProcedureCallQueryImpl <em>Procedure Call Query</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ProcedureCallQueryImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getProcedureCallQuery()
     * @generated
     */
    EClass PROCEDURE_CALL_QUERY = eINSTANCE.getProcedureCallQuery();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.FunctionCallImpl <em>Function Call</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.FunctionCallImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getFunctionCall()
     * @generated
     */
    EClass FUNCTION_CALL = eINSTANCE.getFunctionCall();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.ProcedureUpdateImpl <em>Procedure Update</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.ProcedureUpdateImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getProcedureUpdate()
     * @generated
     */
    EClass PROCEDURE_UPDATE = eINSTANCE.getProcedureUpdate();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.FunctionQueryImpl <em>Function Query</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.FunctionQueryImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getFunctionQuery()
     * @generated
     */
    EClass FUNCTION_QUERY = eINSTANCE.getFunctionQuery();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DaoDirectiveSerializableImpl <em>Dao Directive Serializable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DaoDirectiveSerializableImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDaoDirectiveSerializable()
     * @generated
     */
    EClass DAO_DIRECTIVE_SERIALIZABLE = eINSTANCE.getDaoDirectiveSerializable();

    /**
     * The meta object literal for the '<em><b>Sernum</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DAO_DIRECTIVE_SERIALIZABLE__SERNUM = eINSTANCE.getDaoDirectiveSerializable_Sernum();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DaoDirectiveDiscriminatorImpl <em>Dao Directive Discriminator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DaoDirectiveDiscriminatorImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDaoDirectiveDiscriminator()
     * @generated
     */
    EClass DAO_DIRECTIVE_DISCRIMINATOR = eINSTANCE.getDaoDirectiveDiscriminator();

    /**
     * The meta object literal for the '<em><b>Ancestor</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DAO_DIRECTIVE_DISCRIMINATOR__ANCESTOR = eINSTANCE.getDaoDirectiveDiscriminator_Ancestor();

    /**
     * The meta object literal for the '<em><b>Descendants</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DAO_DIRECTIVE_DISCRIMINATOR__DESCENDANTS = eINSTANCE.getDaoDirectiveDiscriminator_Descendants();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DaoDirectiveCrudImpl <em>Dao Directive Crud</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DaoDirectiveCrudImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDaoDirectiveCrud()
     * @generated
     */
    EClass DAO_DIRECTIVE_CRUD = eINSTANCE.getDaoDirectiveCrud();

    /**
     * The meta object literal for the '<em><b>Pojo</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DAO_DIRECTIVE_CRUD__POJO = eINSTANCE.getDaoDirectiveCrud_Pojo();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.DaoDirectiveQueryImpl <em>Dao Directive Query</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.DaoDirectiveQueryImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getDaoDirectiveQuery()
     * @generated
     */
    EClass DAO_DIRECTIVE_QUERY = eINSTANCE.getDaoDirectiveQuery();

    /**
     * The meta object literal for the '<em><b>Pojo</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DAO_DIRECTIVE_QUERY__POJO = eINSTANCE.getDaoDirectiveQuery_Pojo();

    /**
     * The meta object literal for the '{@link org.sqlproc.dsl.processorDsl.impl.FunProcDirectiveImpl <em>Fun Proc Directive</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.sqlproc.dsl.processorDsl.impl.FunProcDirectiveImpl
     * @see org.sqlproc.dsl.processorDsl.impl.ProcessorDslPackageImpl#getFunProcDirective()
     * @generated
     */
    EClass FUN_PROC_DIRECTIVE = eINSTANCE.getFunProcDirective();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUN_PROC_DIRECTIVE__TYPE = eINSTANCE.getFunProcDirective_Type();

    /**
     * The meta object literal for the '<em><b>Paramlist</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUN_PROC_DIRECTIVE__PARAMLIST = eINSTANCE.getFunProcDirective_Paramlist();

  }

} //ProcessorDslPackage
