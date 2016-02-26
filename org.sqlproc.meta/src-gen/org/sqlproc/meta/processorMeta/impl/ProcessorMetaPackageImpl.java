/**
 */
package org.sqlproc.meta.processorMeta.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.xtext.common.types.TypesPackage;

import org.sqlproc.meta.processorMeta.Artifacts;
import org.sqlproc.meta.processorMeta.Column;
import org.sqlproc.meta.processorMeta.ColumnAssignement;
import org.sqlproc.meta.processorMeta.ColumnTypeAssignement;
import org.sqlproc.meta.processorMeta.Constant;
import org.sqlproc.meta.processorMeta.ConstantOperator;
import org.sqlproc.meta.processorMeta.DaogenProperty;
import org.sqlproc.meta.processorMeta.DatabaseCatalogAssignement;
import org.sqlproc.meta.processorMeta.DatabaseColumn;
import org.sqlproc.meta.processorMeta.DatabaseMetaInfoAssignement;
import org.sqlproc.meta.processorMeta.DatabaseProperty;
import org.sqlproc.meta.processorMeta.DatabaseSchemaAssignement;
import org.sqlproc.meta.processorMeta.DatabaseTable;
import org.sqlproc.meta.processorMeta.DatabaseTypeAssignement;
import org.sqlproc.meta.processorMeta.DebugLevelAssignement;
import org.sqlproc.meta.processorMeta.DriverMetaInfoAssignement;
import org.sqlproc.meta.processorMeta.DriverMethodOutputAssignement;
import org.sqlproc.meta.processorMeta.ExportAssignement;
import org.sqlproc.meta.processorMeta.ExtendedColumn;
import org.sqlproc.meta.processorMeta.ExtendedColumnName;
import org.sqlproc.meta.processorMeta.ExtendedMappingItem;
import org.sqlproc.meta.processorMeta.ExtendsAssignement;
import org.sqlproc.meta.processorMeta.ExtendsAssignementGenerics;
import org.sqlproc.meta.processorMeta.FunctionDefinitionModel;
import org.sqlproc.meta.processorMeta.FunctionPojoAssignement;
import org.sqlproc.meta.processorMeta.Identifier;
import org.sqlproc.meta.processorMeta.IdentifierOperator;
import org.sqlproc.meta.processorMeta.IfMetaSql;
import org.sqlproc.meta.processorMeta.IfSql;
import org.sqlproc.meta.processorMeta.IfSqlBool;
import org.sqlproc.meta.processorMeta.IfSqlCond;
import org.sqlproc.meta.processorMeta.IfSqlFragment;
import org.sqlproc.meta.processorMeta.ImplementsAssignement;
import org.sqlproc.meta.processorMeta.ImplementsAssignementGenerics;
import org.sqlproc.meta.processorMeta.ImportAssignement;
import org.sqlproc.meta.processorMeta.InheritanceAssignement;
import org.sqlproc.meta.processorMeta.JoinTableAssignement;
import org.sqlproc.meta.processorMeta.ManyToManyAssignement;
import org.sqlproc.meta.processorMeta.Mapping;
import org.sqlproc.meta.processorMeta.MappingColumn;
import org.sqlproc.meta.processorMeta.MappingColumnName;
import org.sqlproc.meta.processorMeta.MappingItem;
import org.sqlproc.meta.processorMeta.MappingRule;
import org.sqlproc.meta.processorMeta.MetaSql;
import org.sqlproc.meta.processorMeta.MetaSqlTypeAssignement;
import org.sqlproc.meta.processorMeta.MetaStatement;
import org.sqlproc.meta.processorMeta.MetaTypeAssignement;
import org.sqlproc.meta.processorMeta.MetagenProperty;
import org.sqlproc.meta.processorMeta.OptionalFeature;
import org.sqlproc.meta.processorMeta.OrdSql;
import org.sqlproc.meta.processorMeta.OrdSql2;
import org.sqlproc.meta.processorMeta.PojoDefinitionModel;
import org.sqlproc.meta.processorMeta.PojoType;
import org.sqlproc.meta.processorMeta.PojogenProperty;
import org.sqlproc.meta.processorMeta.ProcedureDefinitionModel;
import org.sqlproc.meta.processorMeta.ProcedurePojoAssignement;
import org.sqlproc.meta.processorMeta.ProcessorMetaFactory;
import org.sqlproc.meta.processorMeta.ProcessorMetaPackage;
import org.sqlproc.meta.processorMeta.Property;
import org.sqlproc.meta.processorMeta.PropertyCondition;
import org.sqlproc.meta.processorMeta.ShowColumnTypeAssignement;
import org.sqlproc.meta.processorMeta.Sql;
import org.sqlproc.meta.processorMeta.SqlFragment;
import org.sqlproc.meta.processorMeta.SqlTypeAssignement;
import org.sqlproc.meta.processorMeta.TableAssignement;
import org.sqlproc.meta.processorMeta.TableDefinitionModel;
import org.sqlproc.meta.processorMeta.ValueType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProcessorMetaPackageImpl extends EPackageImpl implements ProcessorMetaPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass artifactsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass valueTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass databaseCatalogAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass databaseSchemaAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass databaseTypeAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass databaseMetaInfoAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass driverMetaInfoAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass driverMethodOutputAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sqlTypeAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass columnTypeAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass showColumnTypeAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass tableAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass joinTableAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass columnAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass importAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass exportAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass inheritanceAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass manyToManyAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass debugLevelAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass procedurePojoAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass functionPojoAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass implementsAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass extendsAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass implementsAssignementGenericsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass extendsAssignementGenericsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass propertyConditionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass propertyEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass databasePropertyEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojogenPropertyEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass metaSqlTypeAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass metaTypeAssignementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass metagenPropertyEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass daogenPropertyEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoDefinitionModelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass tableDefinitionModelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass procedureDefinitionModelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass functionDefinitionModelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass metaStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sqlEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sqlFragmentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass metaSqlEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ifSqlEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ifSqlFragmentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ifMetaSqlEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ifSqlCondEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ifSqlBoolEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ordSqlEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ordSql2EClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass columnEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass extendedColumnEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass extendedColumnNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass constantEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass identifierEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass constantOperatorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass identifierOperatorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass databaseColumnEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass databaseTableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mappingRuleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mappingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mappingItemEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mappingColumnEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass extendedMappingItemEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mappingColumnNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass optionalFeatureEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ProcessorMetaPackageImpl()
  {
    super(eNS_URI, ProcessorMetaFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link ProcessorMetaPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ProcessorMetaPackage init()
  {
    if (isInited) return (ProcessorMetaPackage)EPackage.Registry.INSTANCE.getEPackage(ProcessorMetaPackage.eNS_URI);

    // Obtain or create and register package
    ProcessorMetaPackageImpl theProcessorMetaPackage = (ProcessorMetaPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ProcessorMetaPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ProcessorMetaPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    TypesPackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theProcessorMetaPackage.createPackageContents();

    // Initialize created meta-data
    theProcessorMetaPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theProcessorMetaPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ProcessorMetaPackage.eNS_URI, theProcessorMetaPackage);
    return theProcessorMetaPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getArtifacts()
  {
    return artifactsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getArtifacts_Properties()
  {
    return (EReference)artifactsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getArtifacts_Pojos()
  {
    return (EReference)artifactsEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getArtifacts_Tables()
  {
    return (EReference)artifactsEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getArtifacts_Procedures()
  {
    return (EReference)artifactsEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getArtifacts_Functions()
  {
    return (EReference)artifactsEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getArtifacts_Statements()
  {
    return (EReference)artifactsEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getArtifacts_Mappings()
  {
    return (EReference)artifactsEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getArtifacts_Features()
  {
    return (EReference)artifactsEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getValueType()
  {
    return valueTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getValueType_Value()
  {
    return (EAttribute)valueTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getValueType_Number()
  {
    return (EAttribute)valueTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getValueType_Id()
  {
    return (EAttribute)valueTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoType()
  {
    return pojoTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoType_Ident()
  {
    return (EReference)pojoTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoType_Ref()
  {
    return (EReference)pojoTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoType_Type()
  {
    return (EReference)pojoTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoType_Gtype()
  {
    return (EReference)pojoTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoType_Array()
  {
    return (EAttribute)pojoTypeEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDatabaseCatalogAssignement()
  {
    return databaseCatalogAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseCatalogAssignement_DbCatalog()
  {
    return (EReference)databaseCatalogAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDatabaseSchemaAssignement()
  {
    return databaseSchemaAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseSchemaAssignement_DbSchema()
  {
    return (EReference)databaseSchemaAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDatabaseTypeAssignement()
  {
    return databaseTypeAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDatabaseTypeAssignement_DbType()
  {
    return (EAttribute)databaseTypeAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDatabaseMetaInfoAssignement()
  {
    return databaseMetaInfoAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseMetaInfoAssignement_DbMetaInfo()
  {
    return (EReference)databaseMetaInfoAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDriverMetaInfoAssignement()
  {
    return driverMetaInfoAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDriverMetaInfoAssignement_DbDriverInfo()
  {
    return (EReference)driverMetaInfoAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDriverMethodOutputAssignement()
  {
    return driverMethodOutputAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDriverMethodOutputAssignement_DriverMethod()
  {
    return (EAttribute)driverMethodOutputAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDriverMethodOutputAssignement_CallOutput()
  {
    return (EReference)driverMethodOutputAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSqlTypeAssignement()
  {
    return sqlTypeAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSqlTypeAssignement_SqlType()
  {
    return (EReference)sqlTypeAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSqlTypeAssignement_SqlSize()
  {
    return (EAttribute)sqlTypeAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSqlTypeAssignement_Type()
  {
    return (EReference)sqlTypeAssignementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getColumnTypeAssignement()
  {
    return columnTypeAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getColumnTypeAssignement_DbColumn()
  {
    return (EAttribute)columnTypeAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getColumnTypeAssignement_Type()
  {
    return (EReference)columnTypeAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getShowColumnTypeAssignement()
  {
    return showColumnTypeAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getShowColumnTypeAssignement_DbColumn()
  {
    return (EAttribute)showColumnTypeAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getShowColumnTypeAssignement_Type()
  {
    return (EReference)showColumnTypeAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTableAssignement()
  {
    return tableAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTableAssignement_DbTable()
  {
    return (EAttribute)tableAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTableAssignement_NewName()
  {
    return (EAttribute)tableAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getJoinTableAssignement()
  {
    return joinTableAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJoinTableAssignement_DbTable()
  {
    return (EAttribute)joinTableAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getJoinTableAssignement_DbTables()
  {
    return (EAttribute)joinTableAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getColumnAssignement()
  {
    return columnAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getColumnAssignement_DbColumn()
  {
    return (EAttribute)columnAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getColumnAssignement_NewName()
  {
    return (EAttribute)columnAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImportAssignement()
  {
    return importAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImportAssignement_DbColumn()
  {
    return (EAttribute)importAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImportAssignement_PkTable()
  {
    return (EAttribute)importAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImportAssignement_PkColumn()
  {
    return (EAttribute)importAssignementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExportAssignement()
  {
    return exportAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExportAssignement_DbColumn()
  {
    return (EAttribute)exportAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExportAssignement_FkTable()
  {
    return (EAttribute)exportAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExportAssignement_FkColumn()
  {
    return (EAttribute)exportAssignementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getInheritanceAssignement()
  {
    return inheritanceAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getInheritanceAssignement_Discriminator()
  {
    return (EAttribute)inheritanceAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getInheritanceAssignement_DbTable()
  {
    return (EAttribute)inheritanceAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getInheritanceAssignement_DbColumns()
  {
    return (EAttribute)inheritanceAssignementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getManyToManyAssignement()
  {
    return manyToManyAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getManyToManyAssignement_PkColumn()
  {
    return (EAttribute)manyToManyAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getManyToManyAssignement_PkTable()
  {
    return (EAttribute)manyToManyAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getManyToManyAssignement_FkColumn()
  {
    return (EAttribute)manyToManyAssignementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDebugLevelAssignement()
  {
    return debugLevelAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDebugLevelAssignement_Debug()
  {
    return (EAttribute)debugLevelAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDebugLevelAssignement_Scope()
  {
    return (EAttribute)debugLevelAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getProcedurePojoAssignement()
  {
    return procedurePojoAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getProcedurePojoAssignement_DbProcedure()
  {
    return (EAttribute)procedurePojoAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProcedurePojoAssignement_Pojo()
  {
    return (EReference)procedurePojoAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFunctionPojoAssignement()
  {
    return functionPojoAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFunctionPojoAssignement_DbFunction()
  {
    return (EAttribute)functionPojoAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFunctionPojoAssignement_Pojo()
  {
    return (EReference)functionPojoAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImplementsAssignement()
  {
    return implementsAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getImplementsAssignement_ToImplement()
  {
    return (EReference)implementsAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImplementsAssignement_DbTables()
  {
    return (EAttribute)implementsAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImplementsAssignement_DbNotTables()
  {
    return (EAttribute)implementsAssignementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExtendsAssignement()
  {
    return extendsAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExtendsAssignement_ToExtends()
  {
    return (EReference)extendsAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExtendsAssignement_DbTables()
  {
    return (EAttribute)extendsAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExtendsAssignement_DbNotTables()
  {
    return (EAttribute)extendsAssignementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImplementsAssignementGenerics()
  {
    return implementsAssignementGenericsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getImplementsAssignementGenerics_ToImplement()
  {
    return (EReference)implementsAssignementGenericsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImplementsAssignementGenerics_DbTables()
  {
    return (EAttribute)implementsAssignementGenericsEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImplementsAssignementGenerics_DbNotTables()
  {
    return (EAttribute)implementsAssignementGenericsEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExtendsAssignementGenerics()
  {
    return extendsAssignementGenericsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExtendsAssignementGenerics_ToExtends()
  {
    return (EReference)extendsAssignementGenericsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExtendsAssignementGenerics_DbTables()
  {
    return (EAttribute)extendsAssignementGenericsEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExtendsAssignementGenerics_DbNotTables()
  {
    return (EAttribute)extendsAssignementGenericsEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPropertyCondition()
  {
    return propertyConditionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPropertyCondition_Name()
  {
    return (EAttribute)propertyConditionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPropertyCondition_Value()
  {
    return (EReference)propertyConditionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getProperty()
  {
    return propertyEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProperty_Condition()
  {
    return (EReference)propertyEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getProperty_Name()
  {
    return (EAttribute)propertyEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProperty_Database()
  {
    return (EReference)propertyEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProperty_Pojogen()
  {
    return (EReference)propertyEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProperty_Metagen()
  {
    return (EReference)propertyEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProperty_Daogen()
  {
    return (EReference)propertyEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getProperty_ReplaceId()
  {
    return (EAttribute)propertyEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProperty_Regex()
  {
    return (EReference)propertyEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProperty_Replacement()
  {
    return (EReference)propertyEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProperty_DoVerifyResources()
  {
    return (EReference)propertyEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProperty_DoNotVerifyResources()
  {
    return (EReference)propertyEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProperty_CaseFormatLibrary()
  {
    return (EReference)propertyEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDatabaseProperty()
  {
    return databasePropertyEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDatabaseProperty_Name()
  {
    return (EAttribute)databasePropertyEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseProperty_DbUrl()
  {
    return (EReference)databasePropertyEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseProperty_DbUsername()
  {
    return (EReference)databasePropertyEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseProperty_DbPassword()
  {
    return (EReference)databasePropertyEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseProperty_DbCatalog()
  {
    return (EReference)databasePropertyEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseProperty_DbSchema()
  {
    return (EReference)databasePropertyEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseProperty_DbDriver()
  {
    return (EReference)databasePropertyEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseProperty_DbExecuteBefore()
  {
    return (EReference)databasePropertyEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseProperty_DbExecuteAfter()
  {
    return (EReference)databasePropertyEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseProperty_DbIndexTypes()
  {
    return (EReference)databasePropertyEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseProperty_DbType()
  {
    return (EReference)databasePropertyEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseProperty_DbMetaInfo()
  {
    return (EReference)databasePropertyEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseProperty_DbDriverInfo()
  {
    return (EReference)databasePropertyEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseProperty_DbMethodsCalls()
  {
    return (EReference)databasePropertyEClass.getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDatabaseProperty_Debug()
  {
    return (EReference)databasePropertyEClass.getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojogenProperty()
  {
    return pojogenPropertyEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojogenProperty_Name()
  {
    return (EAttribute)pojogenPropertyEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_SqlTypes()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojogenProperty_DbTable()
  {
    return (EAttribute)pojogenPropertyEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_ColumnTypes()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojogenProperty_DbProcedure()
  {
    return (EAttribute)pojogenPropertyEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojogenProperty_DbFunction()
  {
    return (EAttribute)pojogenPropertyEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_ColumnType()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojogenProperty_DbTables()
  {
    return (EAttribute)pojogenPropertyEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_JoinTables()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojogenProperty_DbColumns()
  {
    return (EAttribute)pojogenPropertyEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_Tables()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_Columns()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_Exports()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_Imports()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_Many2s()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojogenProperty_DbColumn()
  {
    return (EAttribute)pojogenPropertyEClass.getEStructuralFeatures().get(15);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_Inheritance()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(16);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojogenProperty_Methods()
  {
    return (EAttribute)pojogenPropertyEClass.getEStructuralFeatures().get(17);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojogenProperty_OperatorsSuffix()
  {
    return (EAttribute)pojogenPropertyEClass.getEStructuralFeatures().get(18);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_ToImplements()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(19);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_ToExtends()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(20);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_ToImplementsGenerics()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(21);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_ToExtendsGenerics()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(22);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojogenProperty_Version()
  {
    return (EAttribute)pojogenPropertyEClass.getEStructuralFeatures().get(23);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojogenProperty_DbNotTables()
  {
    return (EAttribute)pojogenPropertyEClass.getEStructuralFeatures().get(24);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_Debug()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(25);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_ProcPojos()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(26);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_FunPojos()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(27);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojogenProperty_ActiveFilter()
  {
    return (EReference)pojogenPropertyEClass.getEStructuralFeatures().get(28);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojogenProperty_Pckg()
  {
    return (EAttribute)pojogenPropertyEClass.getEStructuralFeatures().get(29);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojogenProperty_EnumName()
  {
    return (EAttribute)pojogenPropertyEClass.getEStructuralFeatures().get(30);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojogenProperty_DbCheckConstraints()
  {
    return (EAttribute)pojogenPropertyEClass.getEStructuralFeatures().get(31);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMetaSqlTypeAssignement()
  {
    return metaSqlTypeAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMetaSqlTypeAssignement_SqlType()
  {
    return (EReference)metaSqlTypeAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetaSqlTypeAssignement_Type()
  {
    return (EAttribute)metaSqlTypeAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetaSqlTypeAssignement_Extension()
  {
    return (EAttribute)metaSqlTypeAssignementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMetaTypeAssignement()
  {
    return metaTypeAssignementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetaTypeAssignement_DbColumn()
  {
    return (EAttribute)metaTypeAssignementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetaTypeAssignement_Type()
  {
    return (EAttribute)metaTypeAssignementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetaTypeAssignement_Extension()
  {
    return (EAttribute)metaTypeAssignementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMetagenProperty()
  {
    return metagenPropertyEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetagenProperty_Name()
  {
    return (EAttribute)metagenPropertyEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetagenProperty_Sequence()
  {
    return (EAttribute)metagenPropertyEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetagenProperty_Type()
  {
    return (EAttribute)metagenPropertyEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetagenProperty_DbTables()
  {
    return (EAttribute)metagenPropertyEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetagenProperty_DbNotTables()
  {
    return (EAttribute)metagenPropertyEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetagenProperty_DbTable()
  {
    return (EAttribute)metagenPropertyEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetagenProperty_Identity()
  {
    return (EAttribute)metagenPropertyEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMetagenProperty_SqlTypes()
  {
    return (EReference)metagenPropertyEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMetagenProperty_MetaTypes()
  {
    return (EReference)metagenPropertyEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetagenProperty_DbStatement()
  {
    return (EAttribute)metagenPropertyEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetagenProperty_DbColumns()
  {
    return (EAttribute)metagenPropertyEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetagenProperty_DbFunction()
  {
    return (EAttribute)metagenPropertyEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetagenProperty_DbProcedure()
  {
    return (EAttribute)metagenPropertyEClass.getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMetagenProperty_Debug()
  {
    return (EReference)metagenPropertyEClass.getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMetagenProperty_OptionalFeatures()
  {
    return (EReference)metagenPropertyEClass.getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMetagenProperty_ActiveFilter()
  {
    return (EReference)metagenPropertyEClass.getEStructuralFeatures().get(15);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDaogenProperty()
  {
    return daogenPropertyEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDaogenProperty_Name()
  {
    return (EAttribute)daogenPropertyEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDaogenProperty_DbTables()
  {
    return (EAttribute)daogenPropertyEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaogenProperty_ToImplements()
  {
    return (EReference)daogenPropertyEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaogenProperty_ToExtends()
  {
    return (EReference)daogenPropertyEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaogenProperty_ToImplementsGenerics()
  {
    return (EReference)daogenPropertyEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaogenProperty_ToExtendsGenerics()
  {
    return (EReference)daogenPropertyEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDaogenProperty_DbFunction()
  {
    return (EAttribute)daogenPropertyEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaogenProperty_ResultType()
  {
    return (EReference)daogenPropertyEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaogenProperty_Debug()
  {
    return (EReference)daogenPropertyEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaogenProperty_ActiveFilter()
  {
    return (EReference)daogenPropertyEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDaogenProperty_Pckg()
  {
    return (EAttribute)daogenPropertyEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDaogenProperty_ImplPckg()
  {
    return (EAttribute)daogenPropertyEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoDefinitionModel()
  {
    return pojoDefinitionModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoDefinitionModel_Name()
  {
    return (EAttribute)pojoDefinitionModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoDefinitionModel_Class()
  {
    return (EAttribute)pojoDefinitionModelEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoDefinitionModel_Classx()
  {
    return (EReference)pojoDefinitionModelEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTableDefinitionModel()
  {
    return tableDefinitionModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTableDefinitionModel_Name()
  {
    return (EAttribute)tableDefinitionModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTableDefinitionModel_Table()
  {
    return (EAttribute)tableDefinitionModelEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getProcedureDefinitionModel()
  {
    return procedureDefinitionModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getProcedureDefinitionModel_Name()
  {
    return (EAttribute)procedureDefinitionModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getProcedureDefinitionModel_Table()
  {
    return (EAttribute)procedureDefinitionModelEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFunctionDefinitionModel()
  {
    return functionDefinitionModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFunctionDefinitionModel_Name()
  {
    return (EAttribute)functionDefinitionModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFunctionDefinitionModel_Table()
  {
    return (EAttribute)functionDefinitionModelEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMetaStatement()
  {
    return metaStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetaStatement_Name()
  {
    return (EAttribute)metaStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetaStatement_Type()
  {
    return (EAttribute)metaStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetaStatement_Modifiers()
  {
    return (EAttribute)metaStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMetaStatement_Statement()
  {
    return (EReference)metaStatementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSql()
  {
    return sqlEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSql_Sqls()
  {
    return (EReference)sqlEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSqlFragment()
  {
    return sqlFragmentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSqlFragment_Value()
  {
    return (EAttribute)sqlFragmentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSqlFragment_Col()
  {
    return (EReference)sqlFragmentEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSqlFragment_Cnst()
  {
    return (EReference)sqlFragmentEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSqlFragment_Ident()
  {
    return (EReference)sqlFragmentEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSqlFragment_CnstOper()
  {
    return (EReference)sqlFragmentEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSqlFragment_IdentOper()
  {
    return (EReference)sqlFragmentEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSqlFragment_Meta()
  {
    return (EReference)sqlFragmentEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSqlFragment_Dbtab()
  {
    return (EReference)sqlFragmentEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSqlFragment_Dbcol()
  {
    return (EReference)sqlFragmentEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMetaSql()
  {
    return metaSqlEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMetaSql_Ifs()
  {
    return (EReference)metaSqlEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetaSql_Type()
  {
    return (EAttribute)metaSqlEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMetaSql_Cond()
  {
    return (EReference)metaSqlEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMetaSql_Ftype()
  {
    return (EAttribute)metaSqlEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMetaSql_Ord()
  {
    return (EReference)metaSqlEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIfSql()
  {
    return ifSqlEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfSql_Sqls()
  {
    return (EReference)ifSqlEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIfSqlFragment()
  {
    return ifSqlFragmentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIfSqlFragment_Value()
  {
    return (EAttribute)ifSqlFragmentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfSqlFragment_Col()
  {
    return (EReference)ifSqlFragmentEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfSqlFragment_Cnst()
  {
    return (EReference)ifSqlFragmentEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfSqlFragment_Ident()
  {
    return (EReference)ifSqlFragmentEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfSqlFragment_CnstOper()
  {
    return (EReference)ifSqlFragmentEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfSqlFragment_IdentOper()
  {
    return (EReference)ifSqlFragmentEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfSqlFragment_Dbtab()
  {
    return (EReference)ifSqlFragmentEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfSqlFragment_Dbcol()
  {
    return (EReference)ifSqlFragmentEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfSqlFragment_Meta()
  {
    return (EReference)ifSqlFragmentEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIfMetaSql()
  {
    return ifMetaSqlEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfMetaSql_Ifs()
  {
    return (EReference)ifMetaSqlEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIfMetaSql_Type()
  {
    return (EAttribute)ifMetaSqlEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfMetaSql_Cond()
  {
    return (EReference)ifMetaSqlEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIfSqlCond()
  {
    return ifSqlCondEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfSqlCond_Bool1()
  {
    return (EReference)ifSqlCondEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIfSqlCond_Oper()
  {
    return (EAttribute)ifSqlCondEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfSqlCond_Bool2()
  {
    return (EReference)ifSqlCondEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIfSqlBool()
  {
    return ifSqlBoolEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIfSqlBool_Not()
  {
    return (EAttribute)ifSqlBoolEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfSqlBool_Cnst()
  {
    return (EReference)ifSqlBoolEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfSqlBool_Ident()
  {
    return (EReference)ifSqlBoolEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfSqlBool_Cond()
  {
    return (EReference)ifSqlBoolEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOrdSql()
  {
    return ordSqlEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOrdSql_Ident()
  {
    return (EAttribute)ordSqlEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOrdSql_Sqls()
  {
    return (EReference)ordSqlEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOrdSql2()
  {
    return ordSql2EClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOrdSql2_Value()
  {
    return (EAttribute)ordSql2EClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOrdSql2_Cnst()
  {
    return (EReference)ordSql2EClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOrdSql2_Ident()
  {
    return (EReference)ordSql2EClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOrdSql2_Dbcol()
  {
    return (EReference)ordSql2EClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getColumn()
  {
    return columnEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getColumn_Columns()
  {
    return (EReference)columnEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExtendedColumn()
  {
    return extendedColumnEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExtendedColumn_Col()
  {
    return (EReference)extendedColumnEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExtendedColumn_Modifiers()
  {
    return (EAttribute)extendedColumnEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExtendedColumnName()
  {
    return extendedColumnNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExtendedColumnName_Name()
  {
    return (EAttribute)extendedColumnNameEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConstant()
  {
    return constantEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getConstant_Case()
  {
    return (EAttribute)constantEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getConstant_Name()
  {
    return (EAttribute)constantEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getConstant_Modifiers()
  {
    return (EAttribute)constantEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIdentifier()
  {
    return identifierEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIdentifier_Mode()
  {
    return (EAttribute)identifierEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIdentifier_Case()
  {
    return (EAttribute)identifierEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIdentifier_Name()
  {
    return (EAttribute)identifierEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIdentifier_Modifiers()
  {
    return (EAttribute)identifierEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConstantOperator()
  {
    return constantOperatorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getConstantOperator_Name()
  {
    return (EAttribute)constantOperatorEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIdentifierOperator()
  {
    return identifierOperatorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIdentifierOperator_Name()
  {
    return (EAttribute)identifierOperatorEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDatabaseColumn()
  {
    return databaseColumnEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDatabaseColumn_Name()
  {
    return (EAttribute)databaseColumnEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDatabaseTable()
  {
    return databaseTableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDatabaseTable_Name()
  {
    return (EAttribute)databaseTableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMappingRule()
  {
    return mappingRuleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMappingRule_Name()
  {
    return (EAttribute)mappingRuleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMappingRule_Type()
  {
    return (EAttribute)mappingRuleEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMappingRule_Modifiers()
  {
    return (EAttribute)mappingRuleEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMappingRule_Mapping()
  {
    return (EReference)mappingRuleEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMapping()
  {
    return mappingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMapping_MappingItems()
  {
    return (EReference)mappingEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMappingItem()
  {
    return mappingItemEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMappingItem_Name()
  {
    return (EAttribute)mappingItemEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMappingItem_Attr()
  {
    return (EReference)mappingItemEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMappingColumn()
  {
    return mappingColumnEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMappingColumn_Items()
  {
    return (EReference)mappingColumnEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExtendedMappingItem()
  {
    return extendedMappingItemEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExtendedMappingItem_Attr()
  {
    return (EReference)extendedMappingItemEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExtendedMappingItem_Modifiers()
  {
    return (EAttribute)extendedMappingItemEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMappingColumnName()
  {
    return mappingColumnNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMappingColumnName_Name()
  {
    return (EAttribute)mappingColumnNameEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOptionalFeature()
  {
    return optionalFeatureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOptionalFeature_Name()
  {
    return (EAttribute)optionalFeatureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOptionalFeature_Type()
  {
    return (EAttribute)optionalFeatureEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOptionalFeature_Modifiers()
  {
    return (EAttribute)optionalFeatureEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOptionalFeature_Option()
  {
    return (EAttribute)optionalFeatureEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProcessorMetaFactory getProcessorMetaFactory()
  {
    return (ProcessorMetaFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    artifactsEClass = createEClass(ARTIFACTS);
    createEReference(artifactsEClass, ARTIFACTS__PROPERTIES);
    createEReference(artifactsEClass, ARTIFACTS__POJOS);
    createEReference(artifactsEClass, ARTIFACTS__TABLES);
    createEReference(artifactsEClass, ARTIFACTS__PROCEDURES);
    createEReference(artifactsEClass, ARTIFACTS__FUNCTIONS);
    createEReference(artifactsEClass, ARTIFACTS__STATEMENTS);
    createEReference(artifactsEClass, ARTIFACTS__MAPPINGS);
    createEReference(artifactsEClass, ARTIFACTS__FEATURES);

    valueTypeEClass = createEClass(VALUE_TYPE);
    createEAttribute(valueTypeEClass, VALUE_TYPE__VALUE);
    createEAttribute(valueTypeEClass, VALUE_TYPE__NUMBER);
    createEAttribute(valueTypeEClass, VALUE_TYPE__ID);

    pojoTypeEClass = createEClass(POJO_TYPE);
    createEReference(pojoTypeEClass, POJO_TYPE__IDENT);
    createEReference(pojoTypeEClass, POJO_TYPE__REF);
    createEReference(pojoTypeEClass, POJO_TYPE__TYPE);
    createEReference(pojoTypeEClass, POJO_TYPE__GTYPE);
    createEAttribute(pojoTypeEClass, POJO_TYPE__ARRAY);

    databaseCatalogAssignementEClass = createEClass(DATABASE_CATALOG_ASSIGNEMENT);
    createEReference(databaseCatalogAssignementEClass, DATABASE_CATALOG_ASSIGNEMENT__DB_CATALOG);

    databaseSchemaAssignementEClass = createEClass(DATABASE_SCHEMA_ASSIGNEMENT);
    createEReference(databaseSchemaAssignementEClass, DATABASE_SCHEMA_ASSIGNEMENT__DB_SCHEMA);

    databaseTypeAssignementEClass = createEClass(DATABASE_TYPE_ASSIGNEMENT);
    createEAttribute(databaseTypeAssignementEClass, DATABASE_TYPE_ASSIGNEMENT__DB_TYPE);

    databaseMetaInfoAssignementEClass = createEClass(DATABASE_META_INFO_ASSIGNEMENT);
    createEReference(databaseMetaInfoAssignementEClass, DATABASE_META_INFO_ASSIGNEMENT__DB_META_INFO);

    driverMetaInfoAssignementEClass = createEClass(DRIVER_META_INFO_ASSIGNEMENT);
    createEReference(driverMetaInfoAssignementEClass, DRIVER_META_INFO_ASSIGNEMENT__DB_DRIVER_INFO);

    driverMethodOutputAssignementEClass = createEClass(DRIVER_METHOD_OUTPUT_ASSIGNEMENT);
    createEAttribute(driverMethodOutputAssignementEClass, DRIVER_METHOD_OUTPUT_ASSIGNEMENT__DRIVER_METHOD);
    createEReference(driverMethodOutputAssignementEClass, DRIVER_METHOD_OUTPUT_ASSIGNEMENT__CALL_OUTPUT);

    sqlTypeAssignementEClass = createEClass(SQL_TYPE_ASSIGNEMENT);
    createEReference(sqlTypeAssignementEClass, SQL_TYPE_ASSIGNEMENT__SQL_TYPE);
    createEAttribute(sqlTypeAssignementEClass, SQL_TYPE_ASSIGNEMENT__SQL_SIZE);
    createEReference(sqlTypeAssignementEClass, SQL_TYPE_ASSIGNEMENT__TYPE);

    columnTypeAssignementEClass = createEClass(COLUMN_TYPE_ASSIGNEMENT);
    createEAttribute(columnTypeAssignementEClass, COLUMN_TYPE_ASSIGNEMENT__DB_COLUMN);
    createEReference(columnTypeAssignementEClass, COLUMN_TYPE_ASSIGNEMENT__TYPE);

    showColumnTypeAssignementEClass = createEClass(SHOW_COLUMN_TYPE_ASSIGNEMENT);
    createEAttribute(showColumnTypeAssignementEClass, SHOW_COLUMN_TYPE_ASSIGNEMENT__DB_COLUMN);
    createEReference(showColumnTypeAssignementEClass, SHOW_COLUMN_TYPE_ASSIGNEMENT__TYPE);

    tableAssignementEClass = createEClass(TABLE_ASSIGNEMENT);
    createEAttribute(tableAssignementEClass, TABLE_ASSIGNEMENT__DB_TABLE);
    createEAttribute(tableAssignementEClass, TABLE_ASSIGNEMENT__NEW_NAME);

    joinTableAssignementEClass = createEClass(JOIN_TABLE_ASSIGNEMENT);
    createEAttribute(joinTableAssignementEClass, JOIN_TABLE_ASSIGNEMENT__DB_TABLE);
    createEAttribute(joinTableAssignementEClass, JOIN_TABLE_ASSIGNEMENT__DB_TABLES);

    columnAssignementEClass = createEClass(COLUMN_ASSIGNEMENT);
    createEAttribute(columnAssignementEClass, COLUMN_ASSIGNEMENT__DB_COLUMN);
    createEAttribute(columnAssignementEClass, COLUMN_ASSIGNEMENT__NEW_NAME);

    importAssignementEClass = createEClass(IMPORT_ASSIGNEMENT);
    createEAttribute(importAssignementEClass, IMPORT_ASSIGNEMENT__DB_COLUMN);
    createEAttribute(importAssignementEClass, IMPORT_ASSIGNEMENT__PK_TABLE);
    createEAttribute(importAssignementEClass, IMPORT_ASSIGNEMENT__PK_COLUMN);

    exportAssignementEClass = createEClass(EXPORT_ASSIGNEMENT);
    createEAttribute(exportAssignementEClass, EXPORT_ASSIGNEMENT__DB_COLUMN);
    createEAttribute(exportAssignementEClass, EXPORT_ASSIGNEMENT__FK_TABLE);
    createEAttribute(exportAssignementEClass, EXPORT_ASSIGNEMENT__FK_COLUMN);

    inheritanceAssignementEClass = createEClass(INHERITANCE_ASSIGNEMENT);
    createEAttribute(inheritanceAssignementEClass, INHERITANCE_ASSIGNEMENT__DISCRIMINATOR);
    createEAttribute(inheritanceAssignementEClass, INHERITANCE_ASSIGNEMENT__DB_TABLE);
    createEAttribute(inheritanceAssignementEClass, INHERITANCE_ASSIGNEMENT__DB_COLUMNS);

    manyToManyAssignementEClass = createEClass(MANY_TO_MANY_ASSIGNEMENT);
    createEAttribute(manyToManyAssignementEClass, MANY_TO_MANY_ASSIGNEMENT__PK_COLUMN);
    createEAttribute(manyToManyAssignementEClass, MANY_TO_MANY_ASSIGNEMENT__PK_TABLE);
    createEAttribute(manyToManyAssignementEClass, MANY_TO_MANY_ASSIGNEMENT__FK_COLUMN);

    debugLevelAssignementEClass = createEClass(DEBUG_LEVEL_ASSIGNEMENT);
    createEAttribute(debugLevelAssignementEClass, DEBUG_LEVEL_ASSIGNEMENT__DEBUG);
    createEAttribute(debugLevelAssignementEClass, DEBUG_LEVEL_ASSIGNEMENT__SCOPE);

    procedurePojoAssignementEClass = createEClass(PROCEDURE_POJO_ASSIGNEMENT);
    createEAttribute(procedurePojoAssignementEClass, PROCEDURE_POJO_ASSIGNEMENT__DB_PROCEDURE);
    createEReference(procedurePojoAssignementEClass, PROCEDURE_POJO_ASSIGNEMENT__POJO);

    functionPojoAssignementEClass = createEClass(FUNCTION_POJO_ASSIGNEMENT);
    createEAttribute(functionPojoAssignementEClass, FUNCTION_POJO_ASSIGNEMENT__DB_FUNCTION);
    createEReference(functionPojoAssignementEClass, FUNCTION_POJO_ASSIGNEMENT__POJO);

    implementsAssignementEClass = createEClass(IMPLEMENTS_ASSIGNEMENT);
    createEReference(implementsAssignementEClass, IMPLEMENTS_ASSIGNEMENT__TO_IMPLEMENT);
    createEAttribute(implementsAssignementEClass, IMPLEMENTS_ASSIGNEMENT__DB_TABLES);
    createEAttribute(implementsAssignementEClass, IMPLEMENTS_ASSIGNEMENT__DB_NOT_TABLES);

    extendsAssignementEClass = createEClass(EXTENDS_ASSIGNEMENT);
    createEReference(extendsAssignementEClass, EXTENDS_ASSIGNEMENT__TO_EXTENDS);
    createEAttribute(extendsAssignementEClass, EXTENDS_ASSIGNEMENT__DB_TABLES);
    createEAttribute(extendsAssignementEClass, EXTENDS_ASSIGNEMENT__DB_NOT_TABLES);

    implementsAssignementGenericsEClass = createEClass(IMPLEMENTS_ASSIGNEMENT_GENERICS);
    createEReference(implementsAssignementGenericsEClass, IMPLEMENTS_ASSIGNEMENT_GENERICS__TO_IMPLEMENT);
    createEAttribute(implementsAssignementGenericsEClass, IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_TABLES);
    createEAttribute(implementsAssignementGenericsEClass, IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_NOT_TABLES);

    extendsAssignementGenericsEClass = createEClass(EXTENDS_ASSIGNEMENT_GENERICS);
    createEReference(extendsAssignementGenericsEClass, EXTENDS_ASSIGNEMENT_GENERICS__TO_EXTENDS);
    createEAttribute(extendsAssignementGenericsEClass, EXTENDS_ASSIGNEMENT_GENERICS__DB_TABLES);
    createEAttribute(extendsAssignementGenericsEClass, EXTENDS_ASSIGNEMENT_GENERICS__DB_NOT_TABLES);

    propertyConditionEClass = createEClass(PROPERTY_CONDITION);
    createEAttribute(propertyConditionEClass, PROPERTY_CONDITION__NAME);
    createEReference(propertyConditionEClass, PROPERTY_CONDITION__VALUE);

    propertyEClass = createEClass(PROPERTY);
    createEReference(propertyEClass, PROPERTY__CONDITION);
    createEAttribute(propertyEClass, PROPERTY__NAME);
    createEReference(propertyEClass, PROPERTY__DATABASE);
    createEReference(propertyEClass, PROPERTY__POJOGEN);
    createEReference(propertyEClass, PROPERTY__METAGEN);
    createEReference(propertyEClass, PROPERTY__DAOGEN);
    createEAttribute(propertyEClass, PROPERTY__REPLACE_ID);
    createEReference(propertyEClass, PROPERTY__REGEX);
    createEReference(propertyEClass, PROPERTY__REPLACEMENT);
    createEReference(propertyEClass, PROPERTY__DO_VERIFY_RESOURCES);
    createEReference(propertyEClass, PROPERTY__DO_NOT_VERIFY_RESOURCES);
    createEReference(propertyEClass, PROPERTY__CASE_FORMAT_LIBRARY);

    databasePropertyEClass = createEClass(DATABASE_PROPERTY);
    createEAttribute(databasePropertyEClass, DATABASE_PROPERTY__NAME);
    createEReference(databasePropertyEClass, DATABASE_PROPERTY__DB_URL);
    createEReference(databasePropertyEClass, DATABASE_PROPERTY__DB_USERNAME);
    createEReference(databasePropertyEClass, DATABASE_PROPERTY__DB_PASSWORD);
    createEReference(databasePropertyEClass, DATABASE_PROPERTY__DB_CATALOG);
    createEReference(databasePropertyEClass, DATABASE_PROPERTY__DB_SCHEMA);
    createEReference(databasePropertyEClass, DATABASE_PROPERTY__DB_DRIVER);
    createEReference(databasePropertyEClass, DATABASE_PROPERTY__DB_EXECUTE_BEFORE);
    createEReference(databasePropertyEClass, DATABASE_PROPERTY__DB_EXECUTE_AFTER);
    createEReference(databasePropertyEClass, DATABASE_PROPERTY__DB_INDEX_TYPES);
    createEReference(databasePropertyEClass, DATABASE_PROPERTY__DB_TYPE);
    createEReference(databasePropertyEClass, DATABASE_PROPERTY__DB_META_INFO);
    createEReference(databasePropertyEClass, DATABASE_PROPERTY__DB_DRIVER_INFO);
    createEReference(databasePropertyEClass, DATABASE_PROPERTY__DB_METHODS_CALLS);
    createEReference(databasePropertyEClass, DATABASE_PROPERTY__DEBUG);

    pojogenPropertyEClass = createEClass(POJOGEN_PROPERTY);
    createEAttribute(pojogenPropertyEClass, POJOGEN_PROPERTY__NAME);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__SQL_TYPES);
    createEAttribute(pojogenPropertyEClass, POJOGEN_PROPERTY__DB_TABLE);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__COLUMN_TYPES);
    createEAttribute(pojogenPropertyEClass, POJOGEN_PROPERTY__DB_PROCEDURE);
    createEAttribute(pojogenPropertyEClass, POJOGEN_PROPERTY__DB_FUNCTION);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__COLUMN_TYPE);
    createEAttribute(pojogenPropertyEClass, POJOGEN_PROPERTY__DB_TABLES);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__JOIN_TABLES);
    createEAttribute(pojogenPropertyEClass, POJOGEN_PROPERTY__DB_COLUMNS);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__TABLES);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__COLUMNS);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__EXPORTS);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__IMPORTS);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__MANY2S);
    createEAttribute(pojogenPropertyEClass, POJOGEN_PROPERTY__DB_COLUMN);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__INHERITANCE);
    createEAttribute(pojogenPropertyEClass, POJOGEN_PROPERTY__METHODS);
    createEAttribute(pojogenPropertyEClass, POJOGEN_PROPERTY__OPERATORS_SUFFIX);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__TO_IMPLEMENTS);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__TO_EXTENDS);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__TO_IMPLEMENTS_GENERICS);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__TO_EXTENDS_GENERICS);
    createEAttribute(pojogenPropertyEClass, POJOGEN_PROPERTY__VERSION);
    createEAttribute(pojogenPropertyEClass, POJOGEN_PROPERTY__DB_NOT_TABLES);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__DEBUG);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__PROC_POJOS);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__FUN_POJOS);
    createEReference(pojogenPropertyEClass, POJOGEN_PROPERTY__ACTIVE_FILTER);
    createEAttribute(pojogenPropertyEClass, POJOGEN_PROPERTY__PCKG);
    createEAttribute(pojogenPropertyEClass, POJOGEN_PROPERTY__ENUM_NAME);
    createEAttribute(pojogenPropertyEClass, POJOGEN_PROPERTY__DB_CHECK_CONSTRAINTS);

    metaSqlTypeAssignementEClass = createEClass(META_SQL_TYPE_ASSIGNEMENT);
    createEReference(metaSqlTypeAssignementEClass, META_SQL_TYPE_ASSIGNEMENT__SQL_TYPE);
    createEAttribute(metaSqlTypeAssignementEClass, META_SQL_TYPE_ASSIGNEMENT__TYPE);
    createEAttribute(metaSqlTypeAssignementEClass, META_SQL_TYPE_ASSIGNEMENT__EXTENSION);

    metaTypeAssignementEClass = createEClass(META_TYPE_ASSIGNEMENT);
    createEAttribute(metaTypeAssignementEClass, META_TYPE_ASSIGNEMENT__DB_COLUMN);
    createEAttribute(metaTypeAssignementEClass, META_TYPE_ASSIGNEMENT__TYPE);
    createEAttribute(metaTypeAssignementEClass, META_TYPE_ASSIGNEMENT__EXTENSION);

    metagenPropertyEClass = createEClass(METAGEN_PROPERTY);
    createEAttribute(metagenPropertyEClass, METAGEN_PROPERTY__NAME);
    createEAttribute(metagenPropertyEClass, METAGEN_PROPERTY__SEQUENCE);
    createEAttribute(metagenPropertyEClass, METAGEN_PROPERTY__TYPE);
    createEAttribute(metagenPropertyEClass, METAGEN_PROPERTY__DB_TABLES);
    createEAttribute(metagenPropertyEClass, METAGEN_PROPERTY__DB_NOT_TABLES);
    createEAttribute(metagenPropertyEClass, METAGEN_PROPERTY__DB_TABLE);
    createEAttribute(metagenPropertyEClass, METAGEN_PROPERTY__IDENTITY);
    createEReference(metagenPropertyEClass, METAGEN_PROPERTY__SQL_TYPES);
    createEReference(metagenPropertyEClass, METAGEN_PROPERTY__META_TYPES);
    createEAttribute(metagenPropertyEClass, METAGEN_PROPERTY__DB_STATEMENT);
    createEAttribute(metagenPropertyEClass, METAGEN_PROPERTY__DB_COLUMNS);
    createEAttribute(metagenPropertyEClass, METAGEN_PROPERTY__DB_FUNCTION);
    createEAttribute(metagenPropertyEClass, METAGEN_PROPERTY__DB_PROCEDURE);
    createEReference(metagenPropertyEClass, METAGEN_PROPERTY__DEBUG);
    createEReference(metagenPropertyEClass, METAGEN_PROPERTY__OPTIONAL_FEATURES);
    createEReference(metagenPropertyEClass, METAGEN_PROPERTY__ACTIVE_FILTER);

    daogenPropertyEClass = createEClass(DAOGEN_PROPERTY);
    createEAttribute(daogenPropertyEClass, DAOGEN_PROPERTY__NAME);
    createEAttribute(daogenPropertyEClass, DAOGEN_PROPERTY__DB_TABLES);
    createEReference(daogenPropertyEClass, DAOGEN_PROPERTY__TO_IMPLEMENTS);
    createEReference(daogenPropertyEClass, DAOGEN_PROPERTY__TO_EXTENDS);
    createEReference(daogenPropertyEClass, DAOGEN_PROPERTY__TO_IMPLEMENTS_GENERICS);
    createEReference(daogenPropertyEClass, DAOGEN_PROPERTY__TO_EXTENDS_GENERICS);
    createEAttribute(daogenPropertyEClass, DAOGEN_PROPERTY__DB_FUNCTION);
    createEReference(daogenPropertyEClass, DAOGEN_PROPERTY__RESULT_TYPE);
    createEReference(daogenPropertyEClass, DAOGEN_PROPERTY__DEBUG);
    createEReference(daogenPropertyEClass, DAOGEN_PROPERTY__ACTIVE_FILTER);
    createEAttribute(daogenPropertyEClass, DAOGEN_PROPERTY__PCKG);
    createEAttribute(daogenPropertyEClass, DAOGEN_PROPERTY__IMPL_PCKG);

    pojoDefinitionModelEClass = createEClass(POJO_DEFINITION_MODEL);
    createEAttribute(pojoDefinitionModelEClass, POJO_DEFINITION_MODEL__NAME);
    createEAttribute(pojoDefinitionModelEClass, POJO_DEFINITION_MODEL__CLASS);
    createEReference(pojoDefinitionModelEClass, POJO_DEFINITION_MODEL__CLASSX);

    tableDefinitionModelEClass = createEClass(TABLE_DEFINITION_MODEL);
    createEAttribute(tableDefinitionModelEClass, TABLE_DEFINITION_MODEL__NAME);
    createEAttribute(tableDefinitionModelEClass, TABLE_DEFINITION_MODEL__TABLE);

    procedureDefinitionModelEClass = createEClass(PROCEDURE_DEFINITION_MODEL);
    createEAttribute(procedureDefinitionModelEClass, PROCEDURE_DEFINITION_MODEL__NAME);
    createEAttribute(procedureDefinitionModelEClass, PROCEDURE_DEFINITION_MODEL__TABLE);

    functionDefinitionModelEClass = createEClass(FUNCTION_DEFINITION_MODEL);
    createEAttribute(functionDefinitionModelEClass, FUNCTION_DEFINITION_MODEL__NAME);
    createEAttribute(functionDefinitionModelEClass, FUNCTION_DEFINITION_MODEL__TABLE);

    metaStatementEClass = createEClass(META_STATEMENT);
    createEAttribute(metaStatementEClass, META_STATEMENT__NAME);
    createEAttribute(metaStatementEClass, META_STATEMENT__TYPE);
    createEAttribute(metaStatementEClass, META_STATEMENT__MODIFIERS);
    createEReference(metaStatementEClass, META_STATEMENT__STATEMENT);

    sqlEClass = createEClass(SQL);
    createEReference(sqlEClass, SQL__SQLS);

    sqlFragmentEClass = createEClass(SQL_FRAGMENT);
    createEAttribute(sqlFragmentEClass, SQL_FRAGMENT__VALUE);
    createEReference(sqlFragmentEClass, SQL_FRAGMENT__COL);
    createEReference(sqlFragmentEClass, SQL_FRAGMENT__CNST);
    createEReference(sqlFragmentEClass, SQL_FRAGMENT__IDENT);
    createEReference(sqlFragmentEClass, SQL_FRAGMENT__CNST_OPER);
    createEReference(sqlFragmentEClass, SQL_FRAGMENT__IDENT_OPER);
    createEReference(sqlFragmentEClass, SQL_FRAGMENT__META);
    createEReference(sqlFragmentEClass, SQL_FRAGMENT__DBTAB);
    createEReference(sqlFragmentEClass, SQL_FRAGMENT__DBCOL);

    metaSqlEClass = createEClass(META_SQL);
    createEReference(metaSqlEClass, META_SQL__IFS);
    createEAttribute(metaSqlEClass, META_SQL__TYPE);
    createEReference(metaSqlEClass, META_SQL__COND);
    createEAttribute(metaSqlEClass, META_SQL__FTYPE);
    createEReference(metaSqlEClass, META_SQL__ORD);

    ifSqlEClass = createEClass(IF_SQL);
    createEReference(ifSqlEClass, IF_SQL__SQLS);

    ifSqlFragmentEClass = createEClass(IF_SQL_FRAGMENT);
    createEAttribute(ifSqlFragmentEClass, IF_SQL_FRAGMENT__VALUE);
    createEReference(ifSqlFragmentEClass, IF_SQL_FRAGMENT__COL);
    createEReference(ifSqlFragmentEClass, IF_SQL_FRAGMENT__CNST);
    createEReference(ifSqlFragmentEClass, IF_SQL_FRAGMENT__IDENT);
    createEReference(ifSqlFragmentEClass, IF_SQL_FRAGMENT__CNST_OPER);
    createEReference(ifSqlFragmentEClass, IF_SQL_FRAGMENT__IDENT_OPER);
    createEReference(ifSqlFragmentEClass, IF_SQL_FRAGMENT__DBTAB);
    createEReference(ifSqlFragmentEClass, IF_SQL_FRAGMENT__DBCOL);
    createEReference(ifSqlFragmentEClass, IF_SQL_FRAGMENT__META);

    ifMetaSqlEClass = createEClass(IF_META_SQL);
    createEReference(ifMetaSqlEClass, IF_META_SQL__IFS);
    createEAttribute(ifMetaSqlEClass, IF_META_SQL__TYPE);
    createEReference(ifMetaSqlEClass, IF_META_SQL__COND);

    ifSqlCondEClass = createEClass(IF_SQL_COND);
    createEReference(ifSqlCondEClass, IF_SQL_COND__BOOL1);
    createEAttribute(ifSqlCondEClass, IF_SQL_COND__OPER);
    createEReference(ifSqlCondEClass, IF_SQL_COND__BOOL2);

    ifSqlBoolEClass = createEClass(IF_SQL_BOOL);
    createEAttribute(ifSqlBoolEClass, IF_SQL_BOOL__NOT);
    createEReference(ifSqlBoolEClass, IF_SQL_BOOL__CNST);
    createEReference(ifSqlBoolEClass, IF_SQL_BOOL__IDENT);
    createEReference(ifSqlBoolEClass, IF_SQL_BOOL__COND);

    ordSqlEClass = createEClass(ORD_SQL);
    createEAttribute(ordSqlEClass, ORD_SQL__IDENT);
    createEReference(ordSqlEClass, ORD_SQL__SQLS);

    ordSql2EClass = createEClass(ORD_SQL2);
    createEAttribute(ordSql2EClass, ORD_SQL2__VALUE);
    createEReference(ordSql2EClass, ORD_SQL2__CNST);
    createEReference(ordSql2EClass, ORD_SQL2__IDENT);
    createEReference(ordSql2EClass, ORD_SQL2__DBCOL);

    columnEClass = createEClass(COLUMN);
    createEReference(columnEClass, COLUMN__COLUMNS);

    extendedColumnEClass = createEClass(EXTENDED_COLUMN);
    createEReference(extendedColumnEClass, EXTENDED_COLUMN__COL);
    createEAttribute(extendedColumnEClass, EXTENDED_COLUMN__MODIFIERS);

    extendedColumnNameEClass = createEClass(EXTENDED_COLUMN_NAME);
    createEAttribute(extendedColumnNameEClass, EXTENDED_COLUMN_NAME__NAME);

    constantEClass = createEClass(CONSTANT);
    createEAttribute(constantEClass, CONSTANT__CASE);
    createEAttribute(constantEClass, CONSTANT__NAME);
    createEAttribute(constantEClass, CONSTANT__MODIFIERS);

    identifierEClass = createEClass(IDENTIFIER);
    createEAttribute(identifierEClass, IDENTIFIER__MODE);
    createEAttribute(identifierEClass, IDENTIFIER__CASE);
    createEAttribute(identifierEClass, IDENTIFIER__NAME);
    createEAttribute(identifierEClass, IDENTIFIER__MODIFIERS);

    constantOperatorEClass = createEClass(CONSTANT_OPERATOR);
    createEAttribute(constantOperatorEClass, CONSTANT_OPERATOR__NAME);

    identifierOperatorEClass = createEClass(IDENTIFIER_OPERATOR);
    createEAttribute(identifierOperatorEClass, IDENTIFIER_OPERATOR__NAME);

    databaseColumnEClass = createEClass(DATABASE_COLUMN);
    createEAttribute(databaseColumnEClass, DATABASE_COLUMN__NAME);

    databaseTableEClass = createEClass(DATABASE_TABLE);
    createEAttribute(databaseTableEClass, DATABASE_TABLE__NAME);

    mappingRuleEClass = createEClass(MAPPING_RULE);
    createEAttribute(mappingRuleEClass, MAPPING_RULE__NAME);
    createEAttribute(mappingRuleEClass, MAPPING_RULE__TYPE);
    createEAttribute(mappingRuleEClass, MAPPING_RULE__MODIFIERS);
    createEReference(mappingRuleEClass, MAPPING_RULE__MAPPING);

    mappingEClass = createEClass(MAPPING);
    createEReference(mappingEClass, MAPPING__MAPPING_ITEMS);

    mappingItemEClass = createEClass(MAPPING_ITEM);
    createEAttribute(mappingItemEClass, MAPPING_ITEM__NAME);
    createEReference(mappingItemEClass, MAPPING_ITEM__ATTR);

    mappingColumnEClass = createEClass(MAPPING_COLUMN);
    createEReference(mappingColumnEClass, MAPPING_COLUMN__ITEMS);

    extendedMappingItemEClass = createEClass(EXTENDED_MAPPING_ITEM);
    createEReference(extendedMappingItemEClass, EXTENDED_MAPPING_ITEM__ATTR);
    createEAttribute(extendedMappingItemEClass, EXTENDED_MAPPING_ITEM__MODIFIERS);

    mappingColumnNameEClass = createEClass(MAPPING_COLUMN_NAME);
    createEAttribute(mappingColumnNameEClass, MAPPING_COLUMN_NAME__NAME);

    optionalFeatureEClass = createEClass(OPTIONAL_FEATURE);
    createEAttribute(optionalFeatureEClass, OPTIONAL_FEATURE__NAME);
    createEAttribute(optionalFeatureEClass, OPTIONAL_FEATURE__TYPE);
    createEAttribute(optionalFeatureEClass, OPTIONAL_FEATURE__MODIFIERS);
    createEAttribute(optionalFeatureEClass, OPTIONAL_FEATURE__OPTION);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    TypesPackage theTypesPackage = (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(artifactsEClass, Artifacts.class, "Artifacts", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getArtifacts_Properties(), this.getProperty(), null, "properties", null, 0, -1, Artifacts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArtifacts_Pojos(), this.getPojoDefinitionModel(), null, "pojos", null, 0, -1, Artifacts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArtifacts_Tables(), this.getTableDefinitionModel(), null, "tables", null, 0, -1, Artifacts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArtifacts_Procedures(), this.getProcedureDefinitionModel(), null, "procedures", null, 0, -1, Artifacts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArtifacts_Functions(), this.getFunctionDefinitionModel(), null, "functions", null, 0, -1, Artifacts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArtifacts_Statements(), this.getMetaStatement(), null, "statements", null, 0, -1, Artifacts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArtifacts_Mappings(), this.getMappingRule(), null, "mappings", null, 0, -1, Artifacts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArtifacts_Features(), this.getOptionalFeature(), null, "features", null, 0, -1, Artifacts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(valueTypeEClass, ValueType.class, "ValueType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getValueType_Value(), ecorePackage.getEString(), "value", null, 0, 1, ValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getValueType_Number(), ecorePackage.getEString(), "number", null, 0, 1, ValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getValueType_Id(), ecorePackage.getEString(), "id", null, 0, 1, ValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoTypeEClass, PojoType.class, "PojoType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPojoType_Ident(), this.getValueType(), null, "ident", null, 0, 1, PojoType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoType_Ref(), this.getPojoDefinitionModel(), null, "ref", null, 0, 1, PojoType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoType_Type(), theTypesPackage.getJvmType(), null, "type", null, 0, 1, PojoType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoType_Gtype(), theTypesPackage.getJvmType(), null, "gtype", null, 0, 1, PojoType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojoType_Array(), ecorePackage.getEBoolean(), "array", null, 0, 1, PojoType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(databaseCatalogAssignementEClass, DatabaseCatalogAssignement.class, "DatabaseCatalogAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDatabaseCatalogAssignement_DbCatalog(), this.getValueType(), null, "dbCatalog", null, 0, 1, DatabaseCatalogAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(databaseSchemaAssignementEClass, DatabaseSchemaAssignement.class, "DatabaseSchemaAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDatabaseSchemaAssignement_DbSchema(), this.getValueType(), null, "dbSchema", null, 0, 1, DatabaseSchemaAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(databaseTypeAssignementEClass, DatabaseTypeAssignement.class, "DatabaseTypeAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDatabaseTypeAssignement_DbType(), ecorePackage.getEString(), "dbType", null, 0, 1, DatabaseTypeAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(databaseMetaInfoAssignementEClass, DatabaseMetaInfoAssignement.class, "DatabaseMetaInfoAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDatabaseMetaInfoAssignement_DbMetaInfo(), this.getValueType(), null, "dbMetaInfo", null, 0, 1, DatabaseMetaInfoAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(driverMetaInfoAssignementEClass, DriverMetaInfoAssignement.class, "DriverMetaInfoAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDriverMetaInfoAssignement_DbDriverInfo(), this.getValueType(), null, "dbDriverInfo", null, 0, 1, DriverMetaInfoAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(driverMethodOutputAssignementEClass, DriverMethodOutputAssignement.class, "DriverMethodOutputAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDriverMethodOutputAssignement_DriverMethod(), ecorePackage.getEString(), "driverMethod", null, 0, 1, DriverMethodOutputAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDriverMethodOutputAssignement_CallOutput(), this.getValueType(), null, "callOutput", null, 0, 1, DriverMethodOutputAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(sqlTypeAssignementEClass, SqlTypeAssignement.class, "SqlTypeAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSqlTypeAssignement_SqlType(), this.getValueType(), null, "sqlType", null, 0, 1, SqlTypeAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSqlTypeAssignement_SqlSize(), ecorePackage.getEString(), "sqlSize", null, 0, 1, SqlTypeAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSqlTypeAssignement_Type(), this.getPojoType(), null, "type", null, 0, 1, SqlTypeAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(columnTypeAssignementEClass, ColumnTypeAssignement.class, "ColumnTypeAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getColumnTypeAssignement_DbColumn(), ecorePackage.getEString(), "dbColumn", null, 0, 1, ColumnTypeAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getColumnTypeAssignement_Type(), this.getPojoType(), null, "type", null, 0, 1, ColumnTypeAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(showColumnTypeAssignementEClass, ShowColumnTypeAssignement.class, "ShowColumnTypeAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getShowColumnTypeAssignement_DbColumn(), ecorePackage.getEString(), "dbColumn", null, 0, 1, ShowColumnTypeAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getShowColumnTypeAssignement_Type(), this.getValueType(), null, "type", null, 0, 1, ShowColumnTypeAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(tableAssignementEClass, TableAssignement.class, "TableAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getTableAssignement_DbTable(), ecorePackage.getEString(), "dbTable", null, 0, 1, TableAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTableAssignement_NewName(), ecorePackage.getEString(), "newName", null, 0, 1, TableAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(joinTableAssignementEClass, JoinTableAssignement.class, "JoinTableAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getJoinTableAssignement_DbTable(), ecorePackage.getEString(), "dbTable", null, 0, 1, JoinTableAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getJoinTableAssignement_DbTables(), ecorePackage.getEString(), "dbTables", null, 0, -1, JoinTableAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(columnAssignementEClass, ColumnAssignement.class, "ColumnAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getColumnAssignement_DbColumn(), ecorePackage.getEString(), "dbColumn", null, 0, 1, ColumnAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getColumnAssignement_NewName(), ecorePackage.getEString(), "newName", null, 0, 1, ColumnAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(importAssignementEClass, ImportAssignement.class, "ImportAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getImportAssignement_DbColumn(), ecorePackage.getEString(), "dbColumn", null, 0, 1, ImportAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getImportAssignement_PkTable(), ecorePackage.getEString(), "pkTable", null, 0, 1, ImportAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getImportAssignement_PkColumn(), ecorePackage.getEString(), "pkColumn", null, 0, 1, ImportAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(exportAssignementEClass, ExportAssignement.class, "ExportAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getExportAssignement_DbColumn(), ecorePackage.getEString(), "dbColumn", null, 0, 1, ExportAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getExportAssignement_FkTable(), ecorePackage.getEString(), "fkTable", null, 0, 1, ExportAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getExportAssignement_FkColumn(), ecorePackage.getEString(), "fkColumn", null, 0, 1, ExportAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(inheritanceAssignementEClass, InheritanceAssignement.class, "InheritanceAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getInheritanceAssignement_Discriminator(), ecorePackage.getEString(), "discriminator", null, 0, 1, InheritanceAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getInheritanceAssignement_DbTable(), ecorePackage.getEString(), "dbTable", null, 0, 1, InheritanceAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getInheritanceAssignement_DbColumns(), ecorePackage.getEString(), "dbColumns", null, 0, -1, InheritanceAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(manyToManyAssignementEClass, ManyToManyAssignement.class, "ManyToManyAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getManyToManyAssignement_PkColumn(), ecorePackage.getEString(), "pkColumn", null, 0, 1, ManyToManyAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getManyToManyAssignement_PkTable(), ecorePackage.getEString(), "pkTable", null, 0, 1, ManyToManyAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getManyToManyAssignement_FkColumn(), ecorePackage.getEString(), "fkColumn", null, 0, 1, ManyToManyAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(debugLevelAssignementEClass, DebugLevelAssignement.class, "DebugLevelAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDebugLevelAssignement_Debug(), ecorePackage.getEString(), "debug", null, 0, 1, DebugLevelAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDebugLevelAssignement_Scope(), ecorePackage.getEString(), "scope", null, 0, 1, DebugLevelAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(procedurePojoAssignementEClass, ProcedurePojoAssignement.class, "ProcedurePojoAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getProcedurePojoAssignement_DbProcedure(), ecorePackage.getEString(), "dbProcedure", null, 0, 1, ProcedurePojoAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getProcedurePojoAssignement_Pojo(), this.getPojoType(), null, "pojo", null, 0, 1, ProcedurePojoAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(functionPojoAssignementEClass, FunctionPojoAssignement.class, "FunctionPojoAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getFunctionPojoAssignement_DbFunction(), ecorePackage.getEString(), "dbFunction", null, 0, 1, FunctionPojoAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFunctionPojoAssignement_Pojo(), this.getPojoType(), null, "pojo", null, 0, 1, FunctionPojoAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(implementsAssignementEClass, ImplementsAssignement.class, "ImplementsAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getImplementsAssignement_ToImplement(), this.getPojoType(), null, "toImplement", null, 0, 1, ImplementsAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getImplementsAssignement_DbTables(), ecorePackage.getEString(), "dbTables", null, 0, -1, ImplementsAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getImplementsAssignement_DbNotTables(), ecorePackage.getEString(), "dbNotTables", null, 0, -1, ImplementsAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(extendsAssignementEClass, ExtendsAssignement.class, "ExtendsAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getExtendsAssignement_ToExtends(), this.getPojoType(), null, "toExtends", null, 0, 1, ExtendsAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getExtendsAssignement_DbTables(), ecorePackage.getEString(), "dbTables", null, 0, -1, ExtendsAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getExtendsAssignement_DbNotTables(), ecorePackage.getEString(), "dbNotTables", null, 0, -1, ExtendsAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(implementsAssignementGenericsEClass, ImplementsAssignementGenerics.class, "ImplementsAssignementGenerics", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getImplementsAssignementGenerics_ToImplement(), this.getPojoType(), null, "toImplement", null, 0, 1, ImplementsAssignementGenerics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getImplementsAssignementGenerics_DbTables(), ecorePackage.getEString(), "dbTables", null, 0, -1, ImplementsAssignementGenerics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getImplementsAssignementGenerics_DbNotTables(), ecorePackage.getEString(), "dbNotTables", null, 0, -1, ImplementsAssignementGenerics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(extendsAssignementGenericsEClass, ExtendsAssignementGenerics.class, "ExtendsAssignementGenerics", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getExtendsAssignementGenerics_ToExtends(), this.getPojoType(), null, "toExtends", null, 0, 1, ExtendsAssignementGenerics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getExtendsAssignementGenerics_DbTables(), ecorePackage.getEString(), "dbTables", null, 0, -1, ExtendsAssignementGenerics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getExtendsAssignementGenerics_DbNotTables(), ecorePackage.getEString(), "dbNotTables", null, 0, -1, ExtendsAssignementGenerics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(propertyConditionEClass, PropertyCondition.class, "PropertyCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPropertyCondition_Name(), ecorePackage.getEString(), "name", null, 0, 1, PropertyCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPropertyCondition_Value(), this.getValueType(), null, "value", null, 0, 1, PropertyCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(propertyEClass, Property.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getProperty_Condition(), this.getPropertyCondition(), null, "condition", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getProperty_Name(), ecorePackage.getEString(), "name", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getProperty_Database(), this.getDatabaseProperty(), null, "database", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getProperty_Pojogen(), this.getPojogenProperty(), null, "pojogen", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getProperty_Metagen(), this.getMetagenProperty(), null, "metagen", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getProperty_Daogen(), this.getDaogenProperty(), null, "daogen", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getProperty_ReplaceId(), ecorePackage.getEString(), "replaceId", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getProperty_Regex(), this.getValueType(), null, "regex", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getProperty_Replacement(), this.getValueType(), null, "replacement", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getProperty_DoVerifyResources(), this.getValueType(), null, "doVerifyResources", null, 0, -1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getProperty_DoNotVerifyResources(), this.getValueType(), null, "doNotVerifyResources", null, 0, -1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getProperty_CaseFormatLibrary(), this.getValueType(), null, "caseFormatLibrary", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(databasePropertyEClass, DatabaseProperty.class, "DatabaseProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDatabaseProperty_Name(), ecorePackage.getEString(), "name", null, 0, 1, DatabaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatabaseProperty_DbUrl(), this.getValueType(), null, "dbUrl", null, 0, 1, DatabaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatabaseProperty_DbUsername(), this.getValueType(), null, "dbUsername", null, 0, 1, DatabaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatabaseProperty_DbPassword(), this.getValueType(), null, "dbPassword", null, 0, 1, DatabaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatabaseProperty_DbCatalog(), this.getDatabaseCatalogAssignement(), null, "dbCatalog", null, 0, 1, DatabaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatabaseProperty_DbSchema(), this.getDatabaseSchemaAssignement(), null, "dbSchema", null, 0, 1, DatabaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatabaseProperty_DbDriver(), this.getPojoType(), null, "dbDriver", null, 0, 1, DatabaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatabaseProperty_DbExecuteBefore(), this.getValueType(), null, "dbExecuteBefore", null, 0, 1, DatabaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatabaseProperty_DbExecuteAfter(), this.getValueType(), null, "dbExecuteAfter", null, 0, 1, DatabaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatabaseProperty_DbIndexTypes(), this.getValueType(), null, "dbIndexTypes", null, 0, 1, DatabaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatabaseProperty_DbType(), this.getDatabaseTypeAssignement(), null, "dbType", null, 0, 1, DatabaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatabaseProperty_DbMetaInfo(), this.getDatabaseMetaInfoAssignement(), null, "dbMetaInfo", null, 0, 1, DatabaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatabaseProperty_DbDriverInfo(), this.getDriverMetaInfoAssignement(), null, "dbDriverInfo", null, 0, 1, DatabaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatabaseProperty_DbMethodsCalls(), this.getDriverMethodOutputAssignement(), null, "dbMethodsCalls", null, 0, -1, DatabaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDatabaseProperty_Debug(), this.getDebugLevelAssignement(), null, "debug", null, 0, 1, DatabaseProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojogenPropertyEClass, PojogenProperty.class, "PojogenProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPojogenProperty_Name(), ecorePackage.getEString(), "name", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_SqlTypes(), this.getSqlTypeAssignement(), null, "sqlTypes", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojogenProperty_DbTable(), ecorePackage.getEString(), "dbTable", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_ColumnTypes(), this.getColumnTypeAssignement(), null, "columnTypes", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojogenProperty_DbProcedure(), ecorePackage.getEString(), "dbProcedure", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojogenProperty_DbFunction(), ecorePackage.getEString(), "dbFunction", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_ColumnType(), this.getShowColumnTypeAssignement(), null, "columnType", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojogenProperty_DbTables(), ecorePackage.getEString(), "dbTables", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_JoinTables(), this.getJoinTableAssignement(), null, "joinTables", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojogenProperty_DbColumns(), ecorePackage.getEString(), "dbColumns", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_Tables(), this.getTableAssignement(), null, "tables", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_Columns(), this.getColumnAssignement(), null, "columns", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_Exports(), this.getExportAssignement(), null, "exports", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_Imports(), this.getImportAssignement(), null, "imports", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_Many2s(), this.getManyToManyAssignement(), null, "many2s", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojogenProperty_DbColumn(), ecorePackage.getEString(), "dbColumn", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_Inheritance(), this.getInheritanceAssignement(), null, "inheritance", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojogenProperty_Methods(), ecorePackage.getEString(), "methods", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojogenProperty_OperatorsSuffix(), ecorePackage.getEString(), "operatorsSuffix", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_ToImplements(), this.getImplementsAssignement(), null, "toImplements", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_ToExtends(), this.getExtendsAssignement(), null, "toExtends", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_ToImplementsGenerics(), this.getImplementsAssignementGenerics(), null, "toImplementsGenerics", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_ToExtendsGenerics(), this.getExtendsAssignementGenerics(), null, "toExtendsGenerics", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojogenProperty_Version(), ecorePackage.getEString(), "version", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojogenProperty_DbNotTables(), ecorePackage.getEString(), "dbNotTables", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_Debug(), this.getDebugLevelAssignement(), null, "debug", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_ProcPojos(), this.getProcedurePojoAssignement(), null, "procPojos", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_FunPojos(), this.getFunctionPojoAssignement(), null, "funPojos", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojogenProperty_ActiveFilter(), this.getValueType(), null, "activeFilter", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojogenProperty_Pckg(), ecorePackage.getEString(), "pckg", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojogenProperty_EnumName(), ecorePackage.getEString(), "enumName", null, 0, 1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojogenProperty_DbCheckConstraints(), ecorePackage.getEString(), "dbCheckConstraints", null, 0, -1, PojogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(metaSqlTypeAssignementEClass, MetaSqlTypeAssignement.class, "MetaSqlTypeAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getMetaSqlTypeAssignement_SqlType(), this.getValueType(), null, "sqlType", null, 0, 1, MetaSqlTypeAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetaSqlTypeAssignement_Type(), ecorePackage.getEString(), "type", null, 0, 1, MetaSqlTypeAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetaSqlTypeAssignement_Extension(), ecorePackage.getEString(), "extension", null, 0, 1, MetaSqlTypeAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(metaTypeAssignementEClass, MetaTypeAssignement.class, "MetaTypeAssignement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMetaTypeAssignement_DbColumn(), ecorePackage.getEString(), "dbColumn", null, 0, 1, MetaTypeAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetaTypeAssignement_Type(), ecorePackage.getEString(), "type", null, 0, 1, MetaTypeAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetaTypeAssignement_Extension(), ecorePackage.getEString(), "extension", null, 0, 1, MetaTypeAssignement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(metagenPropertyEClass, MetagenProperty.class, "MetagenProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMetagenProperty_Name(), ecorePackage.getEString(), "name", null, 0, 1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetagenProperty_Sequence(), ecorePackage.getEString(), "sequence", null, 0, 1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetagenProperty_Type(), ecorePackage.getEString(), "type", null, 0, 1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetagenProperty_DbTables(), ecorePackage.getEString(), "dbTables", null, 0, -1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetagenProperty_DbNotTables(), ecorePackage.getEString(), "dbNotTables", null, 0, -1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetagenProperty_DbTable(), ecorePackage.getEString(), "dbTable", null, 0, 1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetagenProperty_Identity(), ecorePackage.getEString(), "identity", null, 0, 1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMetagenProperty_SqlTypes(), this.getMetaSqlTypeAssignement(), null, "sqlTypes", null, 0, -1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMetagenProperty_MetaTypes(), this.getMetaTypeAssignement(), null, "metaTypes", null, 0, -1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetagenProperty_DbStatement(), ecorePackage.getEString(), "dbStatement", null, 0, 1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetagenProperty_DbColumns(), ecorePackage.getEString(), "dbColumns", null, 0, -1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetagenProperty_DbFunction(), ecorePackage.getEString(), "dbFunction", null, 0, 1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetagenProperty_DbProcedure(), ecorePackage.getEString(), "dbProcedure", null, 0, 1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMetagenProperty_Debug(), this.getDebugLevelAssignement(), null, "debug", null, 0, 1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMetagenProperty_OptionalFeatures(), this.getValueType(), null, "optionalFeatures", null, 0, -1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMetagenProperty_ActiveFilter(), this.getValueType(), null, "activeFilter", null, 0, 1, MetagenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(daogenPropertyEClass, DaogenProperty.class, "DaogenProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDaogenProperty_Name(), ecorePackage.getEString(), "name", null, 0, 1, DaogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDaogenProperty_DbTables(), ecorePackage.getEString(), "dbTables", null, 0, -1, DaogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDaogenProperty_ToImplements(), this.getImplementsAssignement(), null, "toImplements", null, 0, 1, DaogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDaogenProperty_ToExtends(), this.getExtendsAssignement(), null, "toExtends", null, 0, 1, DaogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDaogenProperty_ToImplementsGenerics(), this.getImplementsAssignementGenerics(), null, "toImplementsGenerics", null, 0, 1, DaogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDaogenProperty_ToExtendsGenerics(), this.getExtendsAssignementGenerics(), null, "toExtendsGenerics", null, 0, 1, DaogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDaogenProperty_DbFunction(), ecorePackage.getEString(), "dbFunction", null, 0, 1, DaogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDaogenProperty_ResultType(), this.getPojoType(), null, "resultType", null, 0, 1, DaogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDaogenProperty_Debug(), this.getDebugLevelAssignement(), null, "debug", null, 0, 1, DaogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDaogenProperty_ActiveFilter(), this.getValueType(), null, "activeFilter", null, 0, 1, DaogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDaogenProperty_Pckg(), ecorePackage.getEString(), "pckg", null, 0, 1, DaogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDaogenProperty_ImplPckg(), ecorePackage.getEString(), "implPckg", null, 0, 1, DaogenProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoDefinitionModelEClass, PojoDefinitionModel.class, "PojoDefinitionModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPojoDefinitionModel_Name(), ecorePackage.getEString(), "name", null, 0, 1, PojoDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojoDefinitionModel_Class(), ecorePackage.getEString(), "class", null, 0, 1, PojoDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoDefinitionModel_Classx(), theTypesPackage.getJvmType(), null, "classx", null, 0, 1, PojoDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(tableDefinitionModelEClass, TableDefinitionModel.class, "TableDefinitionModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getTableDefinitionModel_Name(), ecorePackage.getEString(), "name", null, 0, 1, TableDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTableDefinitionModel_Table(), ecorePackage.getEString(), "table", null, 0, 1, TableDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(procedureDefinitionModelEClass, ProcedureDefinitionModel.class, "ProcedureDefinitionModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getProcedureDefinitionModel_Name(), ecorePackage.getEString(), "name", null, 0, 1, ProcedureDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getProcedureDefinitionModel_Table(), ecorePackage.getEString(), "table", null, 0, 1, ProcedureDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(functionDefinitionModelEClass, FunctionDefinitionModel.class, "FunctionDefinitionModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getFunctionDefinitionModel_Name(), ecorePackage.getEString(), "name", null, 0, 1, FunctionDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getFunctionDefinitionModel_Table(), ecorePackage.getEString(), "table", null, 0, 1, FunctionDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(metaStatementEClass, MetaStatement.class, "MetaStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMetaStatement_Name(), ecorePackage.getEString(), "name", null, 0, 1, MetaStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetaStatement_Type(), ecorePackage.getEString(), "type", null, 0, 1, MetaStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetaStatement_Modifiers(), ecorePackage.getEString(), "modifiers", null, 0, -1, MetaStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMetaStatement_Statement(), this.getSql(), null, "statement", null, 0, 1, MetaStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(sqlEClass, Sql.class, "Sql", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSql_Sqls(), this.getSqlFragment(), null, "sqls", null, 0, -1, Sql.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(sqlFragmentEClass, SqlFragment.class, "SqlFragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSqlFragment_Value(), ecorePackage.getEString(), "value", null, 0, 1, SqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSqlFragment_Col(), this.getColumn(), null, "col", null, 0, 1, SqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSqlFragment_Cnst(), this.getConstant(), null, "cnst", null, 0, 1, SqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSqlFragment_Ident(), this.getIdentifier(), null, "ident", null, 0, 1, SqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSqlFragment_CnstOper(), this.getConstantOperator(), null, "cnstOper", null, 0, 1, SqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSqlFragment_IdentOper(), this.getIdentifierOperator(), null, "identOper", null, 0, 1, SqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSqlFragment_Meta(), this.getMetaSql(), null, "meta", null, 0, 1, SqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSqlFragment_Dbtab(), this.getDatabaseTable(), null, "dbtab", null, 0, 1, SqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSqlFragment_Dbcol(), this.getDatabaseColumn(), null, "dbcol", null, 0, 1, SqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(metaSqlEClass, MetaSql.class, "MetaSql", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getMetaSql_Ifs(), this.getIfSql(), null, "ifs", null, 0, -1, MetaSql.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetaSql_Type(), ecorePackage.getEString(), "type", null, 0, 1, MetaSql.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMetaSql_Cond(), this.getIfSqlCond(), null, "cond", null, 0, 1, MetaSql.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMetaSql_Ftype(), ecorePackage.getEString(), "ftype", null, 0, 1, MetaSql.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMetaSql_Ord(), this.getOrdSql(), null, "ord", null, 0, 1, MetaSql.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ifSqlEClass, IfSql.class, "IfSql", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getIfSql_Sqls(), this.getIfSqlFragment(), null, "sqls", null, 0, -1, IfSql.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ifSqlFragmentEClass, IfSqlFragment.class, "IfSqlFragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIfSqlFragment_Value(), ecorePackage.getEString(), "value", null, 0, 1, IfSqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfSqlFragment_Col(), this.getColumn(), null, "col", null, 0, 1, IfSqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfSqlFragment_Cnst(), this.getConstant(), null, "cnst", null, 0, 1, IfSqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfSqlFragment_Ident(), this.getIdentifier(), null, "ident", null, 0, 1, IfSqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfSqlFragment_CnstOper(), this.getConstantOperator(), null, "cnstOper", null, 0, 1, IfSqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfSqlFragment_IdentOper(), this.getIdentifierOperator(), null, "identOper", null, 0, 1, IfSqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfSqlFragment_Dbtab(), this.getDatabaseTable(), null, "dbtab", null, 0, 1, IfSqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfSqlFragment_Dbcol(), this.getDatabaseColumn(), null, "dbcol", null, 0, 1, IfSqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfSqlFragment_Meta(), this.getIfMetaSql(), null, "meta", null, 0, 1, IfSqlFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ifMetaSqlEClass, IfMetaSql.class, "IfMetaSql", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getIfMetaSql_Ifs(), this.getIfSql(), null, "ifs", null, 0, -1, IfMetaSql.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getIfMetaSql_Type(), ecorePackage.getEString(), "type", null, 0, 1, IfMetaSql.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfMetaSql_Cond(), this.getIfSqlCond(), null, "cond", null, 0, 1, IfMetaSql.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ifSqlCondEClass, IfSqlCond.class, "IfSqlCond", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getIfSqlCond_Bool1(), this.getIfSqlBool(), null, "bool1", null, 0, 1, IfSqlCond.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getIfSqlCond_Oper(), ecorePackage.getEString(), "oper", null, 0, -1, IfSqlCond.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfSqlCond_Bool2(), this.getIfSqlBool(), null, "bool2", null, 0, -1, IfSqlCond.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ifSqlBoolEClass, IfSqlBool.class, "IfSqlBool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIfSqlBool_Not(), ecorePackage.getEBoolean(), "not", null, 0, 1, IfSqlBool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfSqlBool_Cnst(), this.getConstant(), null, "cnst", null, 0, 1, IfSqlBool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfSqlBool_Ident(), this.getIdentifier(), null, "ident", null, 0, 1, IfSqlBool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfSqlBool_Cond(), this.getIfSqlCond(), null, "cond", null, 0, 1, IfSqlBool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ordSqlEClass, OrdSql.class, "OrdSql", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getOrdSql_Ident(), ecorePackage.getEString(), "ident", null, 0, 1, OrdSql.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getOrdSql_Sqls(), this.getOrdSql2(), null, "sqls", null, 0, -1, OrdSql.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ordSql2EClass, OrdSql2.class, "OrdSql2", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getOrdSql2_Value(), ecorePackage.getEString(), "value", null, 0, 1, OrdSql2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getOrdSql2_Cnst(), this.getConstant(), null, "cnst", null, 0, 1, OrdSql2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getOrdSql2_Ident(), this.getIdentifier(), null, "ident", null, 0, 1, OrdSql2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getOrdSql2_Dbcol(), this.getDatabaseColumn(), null, "dbcol", null, 0, 1, OrdSql2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(columnEClass, Column.class, "Column", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getColumn_Columns(), this.getExtendedColumn(), null, "columns", null, 0, -1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(extendedColumnEClass, ExtendedColumn.class, "ExtendedColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getExtendedColumn_Col(), this.getExtendedColumnName(), null, "col", null, 0, 1, ExtendedColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getExtendedColumn_Modifiers(), ecorePackage.getEString(), "modifiers", null, 0, -1, ExtendedColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(extendedColumnNameEClass, ExtendedColumnName.class, "ExtendedColumnName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getExtendedColumnName_Name(), ecorePackage.getEString(), "name", null, 0, 1, ExtendedColumnName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(constantEClass, Constant.class, "Constant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getConstant_Case(), ecorePackage.getEString(), "case", null, 0, 1, Constant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getConstant_Name(), ecorePackage.getEString(), "name", null, 0, 1, Constant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getConstant_Modifiers(), ecorePackage.getEString(), "modifiers", null, 0, -1, Constant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(identifierEClass, Identifier.class, "Identifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIdentifier_Mode(), ecorePackage.getEString(), "mode", null, 0, 1, Identifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getIdentifier_Case(), ecorePackage.getEString(), "case", null, 0, 1, Identifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getIdentifier_Name(), ecorePackage.getEString(), "name", null, 0, 1, Identifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getIdentifier_Modifiers(), ecorePackage.getEString(), "modifiers", null, 0, -1, Identifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(constantOperatorEClass, ConstantOperator.class, "ConstantOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getConstantOperator_Name(), ecorePackage.getEString(), "name", null, 0, 1, ConstantOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(identifierOperatorEClass, IdentifierOperator.class, "IdentifierOperator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIdentifierOperator_Name(), ecorePackage.getEString(), "name", null, 0, 1, IdentifierOperator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(databaseColumnEClass, DatabaseColumn.class, "DatabaseColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDatabaseColumn_Name(), ecorePackage.getEString(), "name", null, 0, 1, DatabaseColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(databaseTableEClass, DatabaseTable.class, "DatabaseTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDatabaseTable_Name(), ecorePackage.getEString(), "name", null, 0, 1, DatabaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(mappingRuleEClass, MappingRule.class, "MappingRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMappingRule_Name(), ecorePackage.getEString(), "name", null, 0, 1, MappingRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMappingRule_Type(), ecorePackage.getEString(), "type", null, 0, 1, MappingRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMappingRule_Modifiers(), ecorePackage.getEString(), "modifiers", null, 0, -1, MappingRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMappingRule_Mapping(), this.getMapping(), null, "mapping", null, 0, 1, MappingRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(mappingEClass, Mapping.class, "Mapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getMapping_MappingItems(), this.getMappingItem(), null, "mappingItems", null, 0, -1, Mapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(mappingItemEClass, MappingItem.class, "MappingItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMappingItem_Name(), ecorePackage.getEString(), "name", null, 0, 1, MappingItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMappingItem_Attr(), this.getMappingColumn(), null, "attr", null, 0, 1, MappingItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(mappingColumnEClass, MappingColumn.class, "MappingColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getMappingColumn_Items(), this.getExtendedMappingItem(), null, "items", null, 0, -1, MappingColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(extendedMappingItemEClass, ExtendedMappingItem.class, "ExtendedMappingItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getExtendedMappingItem_Attr(), this.getMappingColumnName(), null, "attr", null, 0, 1, ExtendedMappingItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getExtendedMappingItem_Modifiers(), ecorePackage.getEString(), "modifiers", null, 0, -1, ExtendedMappingItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(mappingColumnNameEClass, MappingColumnName.class, "MappingColumnName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMappingColumnName_Name(), ecorePackage.getEString(), "name", null, 0, 1, MappingColumnName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(optionalFeatureEClass, OptionalFeature.class, "OptionalFeature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getOptionalFeature_Name(), ecorePackage.getEString(), "name", null, 0, 1, OptionalFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOptionalFeature_Type(), ecorePackage.getEString(), "type", null, 0, 1, OptionalFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOptionalFeature_Modifiers(), ecorePackage.getEString(), "modifiers", null, 0, -1, OptionalFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOptionalFeature_Option(), ecorePackage.getEString(), "option", null, 0, 1, OptionalFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //ProcessorMetaPackageImpl
