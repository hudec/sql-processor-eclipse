/**
 */
package org.sqlproc.model.processorModel.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.xtext.common.types.TypesPackage;

import org.eclipse.xtext.xbase.XbasePackage;

import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotationsPackage;

import org.eclipse.xtext.xtype.XtypePackage;

import org.sqlproc.model.processorModel.AbstractEntity;
import org.sqlproc.model.processorModel.AnnotatedEntity;
import org.sqlproc.model.processorModel.Annotation;
import org.sqlproc.model.processorModel.AnnotationDirective;
import org.sqlproc.model.processorModel.AnnotationDirectiveAttribute;
import org.sqlproc.model.processorModel.AnnotationDirectiveConflict;
import org.sqlproc.model.processorModel.AnnotationDirectiveConstructor;
import org.sqlproc.model.processorModel.AnnotationDirectiveGetter;
import org.sqlproc.model.processorModel.AnnotationDirectiveSetter;
import org.sqlproc.model.processorModel.AnnotationDirectiveStandard;
import org.sqlproc.model.processorModel.AnnotationDirectiveStatic;
import org.sqlproc.model.processorModel.Artifacts;
import org.sqlproc.model.processorModel.ColumnAssignement;
import org.sqlproc.model.processorModel.ColumnTypeAssignement;
import org.sqlproc.model.processorModel.DaoDirective;
import org.sqlproc.model.processorModel.DaoDirectiveCrud;
import org.sqlproc.model.processorModel.DaoDirectiveDiscriminator;
import org.sqlproc.model.processorModel.DaoDirectiveParameters;
import org.sqlproc.model.processorModel.DaoDirectivePojo;
import org.sqlproc.model.processorModel.DaoDirectiveQuery;
import org.sqlproc.model.processorModel.DaoDirectiveSerializable;
import org.sqlproc.model.processorModel.DaoEntity;
import org.sqlproc.model.processorModel.DaoFunProcDirective;
import org.sqlproc.model.processorModel.DaogenProperty;
import org.sqlproc.model.processorModel.DatabaseCatalogAssignement;
import org.sqlproc.model.processorModel.DatabaseMetaInfoAssignement;
import org.sqlproc.model.processorModel.DatabaseProperty;
import org.sqlproc.model.processorModel.DatabaseSchemaAssignement;
import org.sqlproc.model.processorModel.DatabaseTypeAssignement;
import org.sqlproc.model.processorModel.DebugLevelAssignement;
import org.sqlproc.model.processorModel.DescendantAssignment;
import org.sqlproc.model.processorModel.DirectiveProperties;
import org.sqlproc.model.processorModel.DriverMetaInfoAssignement;
import org.sqlproc.model.processorModel.DriverMethodOutputAssignement;
import org.sqlproc.model.processorModel.Entity;
import org.sqlproc.model.processorModel.EnumAttribute;
import org.sqlproc.model.processorModel.EnumAttributeDirective;
import org.sqlproc.model.processorModel.EnumAttributeDirectiveValues;
import org.sqlproc.model.processorModel.EnumAttributeValue;
import org.sqlproc.model.processorModel.EnumDirective;
import org.sqlproc.model.processorModel.EnumDirectiveSerializable;
import org.sqlproc.model.processorModel.EnumEntity;
import org.sqlproc.model.processorModel.ExportAssignement;
import org.sqlproc.model.processorModel.Extends;
import org.sqlproc.model.processorModel.ExtendsAssignement;
import org.sqlproc.model.processorModel.ExtendsAssignementGenerics;
import org.sqlproc.model.processorModel.FunProcType;
import org.sqlproc.model.processorModel.FunctionCall;
import org.sqlproc.model.processorModel.FunctionCallQuery;
import org.sqlproc.model.processorModel.FunctionDefinitionModel;
import org.sqlproc.model.processorModel.FunctionPojoAssignement;
import org.sqlproc.model.processorModel.FunctionQuery;
import org.sqlproc.model.processorModel.Implements;
import org.sqlproc.model.processorModel.ImplementsAssignement;
import org.sqlproc.model.processorModel.ImplementsAssignementGenerics;
import org.sqlproc.model.processorModel.ImplementsExtendsDirective;
import org.sqlproc.model.processorModel.ImplementsExtendsDirectiveExceptDaos;
import org.sqlproc.model.processorModel.ImplementsExtendsDirectiveExceptPojos;
import org.sqlproc.model.processorModel.ImplementsExtendsDirectiveGenerics;
import org.sqlproc.model.processorModel.ImplementsExtendsDirectiveOnlyDaos;
import org.sqlproc.model.processorModel.ImplementsExtendsDirectiveOnlyPojos;
import org.sqlproc.model.processorModel.ImportAssignement;
import org.sqlproc.model.processorModel.IndexType;
import org.sqlproc.model.processorModel.InheritanceAssignement;
import org.sqlproc.model.processorModel.JoinTableAssignement;
import org.sqlproc.model.processorModel.ManyToManyAssignement;
import org.sqlproc.model.processorModel.MetaSqlTypeAssignement;
import org.sqlproc.model.processorModel.MetaTypeAssignement;
import org.sqlproc.model.processorModel.MetagenProperty;
import org.sqlproc.model.processorModel.PackageDirective;
import org.sqlproc.model.processorModel.PackageDirectiveImplementation;
import org.sqlproc.model.processorModel.PojoAttribute;
import org.sqlproc.model.processorModel.PojoAttributeDirective;
import org.sqlproc.model.processorModel.PojoAttributeDirectiveCreateCol;
import org.sqlproc.model.processorModel.PojoAttributeDirectiveDiscriminator;
import org.sqlproc.model.processorModel.PojoAttributeDirectiveEnumDef;
import org.sqlproc.model.processorModel.PojoAttributeDirectiveEnumIndex;
import org.sqlproc.model.processorModel.PojoAttributeDirectiveEnumInit;
import org.sqlproc.model.processorModel.PojoAttributeDirectiveIndex;
import org.sqlproc.model.processorModel.PojoAttributeDirectiveIsDef;
import org.sqlproc.model.processorModel.PojoAttributeDirectivePrimaryKey;
import org.sqlproc.model.processorModel.PojoAttributeDirectiveRequired;
import org.sqlproc.model.processorModel.PojoAttributeDirectiveToInit;
import org.sqlproc.model.processorModel.PojoAttributeDirectiveUpdateCol;
import org.sqlproc.model.processorModel.PojoAttributeDirectiveVersion;
import org.sqlproc.model.processorModel.PojoDefinitionModel;
import org.sqlproc.model.processorModel.PojoDirective;
import org.sqlproc.model.processorModel.PojoDirectiveDiscriminator;
import org.sqlproc.model.processorModel.PojoDirectiveEnumIndex;
import org.sqlproc.model.processorModel.PojoDirectiveEquals;
import org.sqlproc.model.processorModel.PojoDirectiveHashCode;
import org.sqlproc.model.processorModel.PojoDirectiveIndex;
import org.sqlproc.model.processorModel.PojoDirectiveOperators;
import org.sqlproc.model.processorModel.PojoDirectiveProcessingId;
import org.sqlproc.model.processorModel.PojoDirectiveSerializable;
import org.sqlproc.model.processorModel.PojoDirectiveToString;
import org.sqlproc.model.processorModel.PojoEntity;
import org.sqlproc.model.processorModel.PojoProcedure;
import org.sqlproc.model.processorModel.PojoType;
import org.sqlproc.model.processorModel.PojogenProperty;
import org.sqlproc.model.processorModel.ProcedureCallQuery;
import org.sqlproc.model.processorModel.ProcedureDefinitionModel;
import org.sqlproc.model.processorModel.ProcedurePojoAssignement;
import org.sqlproc.model.processorModel.ProcedureUpdate;
import org.sqlproc.model.processorModel.ProcessorModelFactory;
import org.sqlproc.model.processorModel.ProcessorModelPackage;
import org.sqlproc.model.processorModel.Property;
import org.sqlproc.model.processorModel.PropertyCondition;
import org.sqlproc.model.processorModel.ShowColumnTypeAssignement;
import org.sqlproc.model.processorModel.SqlTypeAssignement;
import org.sqlproc.model.processorModel.TableAssignement;
import org.sqlproc.model.processorModel.TableDefinitionModel;
import org.sqlproc.model.processorModel.ValueType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProcessorModelPackageImpl extends EPackageImpl implements ProcessorModelPackage
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
  private EClass indexTypeEClass = null;

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
  private EClass packageDirectiveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass packageEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass abstractEntityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass implementsExtendsDirectiveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass implementsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass extendsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass annotatedEntityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass entityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass directivePropertiesEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoDirectiveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoEntityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoAttributeDirectiveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoAttributeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoProcedureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumDirectiveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumEntityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumAttributeValueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumAttributeDirectiveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumAttributeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass daoDirectiveParametersEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass descendantAssignmentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass funProcTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass daoDirectiveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass daoEntityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass annotationDirectiveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass annotationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass packageDirectiveImplementationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass implementsExtendsDirectiveGenericsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass implementsExtendsDirectiveOnlyPojosEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass implementsExtendsDirectiveOnlyDaosEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass implementsExtendsDirectiveExceptPojosEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass implementsExtendsDirectiveExceptDaosEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoDirectiveToStringEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoDirectiveIndexEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoDirectiveEnumIndexEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoDirectiveOperatorsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoDirectiveSerializableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoDirectiveDiscriminatorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoDirectiveEqualsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoDirectiveHashCodeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoDirectiveProcessingIdEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoAttributeDirectiveRequiredEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoAttributeDirectivePrimaryKeyEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoAttributeDirectiveDiscriminatorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoAttributeDirectiveIndexEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoAttributeDirectiveEnumIndexEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoAttributeDirectiveVersionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoAttributeDirectiveUpdateColEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoAttributeDirectiveCreateColEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoAttributeDirectiveToInitEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoAttributeDirectiveEnumInitEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoAttributeDirectiveIsDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pojoAttributeDirectiveEnumDefEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumDirectiveSerializableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumAttributeDirectiveValuesEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass functionCallQueryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass procedureCallQueryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass functionCallEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass procedureUpdateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass functionQueryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass daoDirectiveSerializableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass daoDirectiveDiscriminatorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass daoDirectiveCrudEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass daoDirectiveQueryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass daoFunProcDirectiveEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass daoDirectivePojoEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass annotationDirectiveConflictEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass annotationDirectiveStaticEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass annotationDirectiveConstructorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass annotationDirectiveStandardEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass annotationDirectiveSetterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass annotationDirectiveGetterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass annotationDirectiveAttributeEClass = null;

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
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private ProcessorModelPackageImpl()
  {
    super(eNS_URI, ProcessorModelFactory.eINSTANCE);
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
   * <p>This method is used to initialize {@link ProcessorModelPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static ProcessorModelPackage init()
  {
    if (isInited) return (ProcessorModelPackage)EPackage.Registry.INSTANCE.getEPackage(ProcessorModelPackage.eNS_URI);

    // Obtain or create and register package
    ProcessorModelPackageImpl theProcessorModelPackage = (ProcessorModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ProcessorModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ProcessorModelPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    XAnnotationsPackage.eINSTANCE.eClass();
    XbasePackage.eINSTANCE.eClass();
    XtypePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theProcessorModelPackage.createPackageContents();

    // Initialize created meta-data
    theProcessorModelPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theProcessorModelPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(ProcessorModelPackage.eNS_URI, theProcessorModelPackage);
    return theProcessorModelPackage;
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
  public EReference getArtifacts_Packages()
  {
    return (EReference)artifactsEClass.getEStructuralFeatures().get(5);
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
  public EClass getIndexType()
  {
    return indexTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIndexType_Number()
  {
    return (EAttribute)indexTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIndexType_Id()
  {
    return (EAttribute)indexTypeEClass.getEStructuralFeatures().get(1);
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
  public EReference getSqlTypeAssignement_Type()
  {
    return (EReference)sqlTypeAssignementEClass.getEStructuralFeatures().get(1);
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
  public EClass getPackageDirective()
  {
    return packageDirectiveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPackage()
  {
    return packageEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPackage_Directives()
  {
    return (EReference)packageEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPackage_Name()
  {
    return (EAttribute)packageEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPackage_ImportSection()
  {
    return (EReference)packageEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPackage_Elements()
  {
    return (EReference)packageEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAbstractEntity()
  {
    return abstractEntityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImplementsExtendsDirective()
  {
    return implementsExtendsDirectiveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImplements()
  {
    return implementsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getImplements_Directives()
  {
    return (EReference)implementsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getImplements_Implements()
  {
    return (EReference)implementsEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExtends()
  {
    return extendsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExtends_Directives()
  {
    return (EReference)extendsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExtends_Extends()
  {
    return (EReference)extendsEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnnotatedEntity()
  {
    return annotatedEntityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAnnotatedEntity_Annotations()
  {
    return (EReference)annotatedEntityEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAnnotatedEntity_Entity()
  {
    return (EReference)annotatedEntityEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEntity()
  {
    return entityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEntity_Final()
  {
    return (EAttribute)entityEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEntity_Name()
  {
    return (EAttribute)entityEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEntity_Procedures()
  {
    return (EReference)entityEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDirectiveProperties()
  {
    return directivePropertiesEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDirectiveProperties_Features()
  {
    return (EReference)directivePropertiesEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoDirective()
  {
    return pojoDirectiveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoEntity()
  {
    return pojoEntityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoEntity_Directives()
  {
    return (EReference)pojoEntityEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoEntity_Abstract()
  {
    return (EAttribute)pojoEntityEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoEntity_SuperType()
  {
    return (EReference)pojoEntityEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoEntity_Attributes()
  {
    return (EReference)pojoEntityEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoAttributeDirective()
  {
    return pojoAttributeDirectiveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoAttribute()
  {
    return pojoAttributeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoAttribute_Annotations()
  {
    return (EReference)pojoAttributeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoAttribute_Directives()
  {
    return (EReference)pojoAttributeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoAttribute_Final()
  {
    return (EAttribute)pojoAttributeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoAttribute_Static()
  {
    return (EAttribute)pojoAttributeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoAttribute_Kind()
  {
    return (EAttribute)pojoAttributeEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoAttribute_Type()
  {
    return (EReference)pojoAttributeEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoAttribute_Name()
  {
    return (EAttribute)pojoAttributeEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoAttribute_InitExpr()
  {
    return (EReference)pojoAttributeEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoProcedure()
  {
    return pojoProcedureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoProcedure_Annotations()
  {
    return (EReference)pojoProcedureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoProcedure_Final()
  {
    return (EAttribute)pojoProcedureEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoProcedure_Static()
  {
    return (EAttribute)pojoProcedureEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoProcedure_Type()
  {
    return (EReference)pojoProcedureEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoProcedure_Name()
  {
    return (EAttribute)pojoProcedureEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoProcedure_Params()
  {
    return (EReference)pojoProcedureEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoProcedure_Body()
  {
    return (EReference)pojoProcedureEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEnumDirective()
  {
    return enumDirectiveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEnumEntity()
  {
    return enumEntityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumEntity_Directives()
  {
    return (EReference)enumEntityEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumEntity_Attribute()
  {
    return (EReference)enumEntityEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEnumAttributeValue()
  {
    return enumAttributeValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEnumAttributeValue_Name()
  {
    return (EAttribute)enumAttributeValueEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumAttributeValue_Value()
  {
    return (EReference)enumAttributeValueEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEnumAttributeDirective()
  {
    return enumAttributeDirectiveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEnumAttribute()
  {
    return enumAttributeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumAttribute_Directives()
  {
    return (EReference)enumAttributeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEnumAttribute_Final()
  {
    return (EAttribute)enumAttributeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEnumAttribute_Static()
  {
    return (EAttribute)enumAttributeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumAttribute_Type()
  {
    return (EReference)enumAttributeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEnumAttribute_Name()
  {
    return (EAttribute)enumAttributeEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDaoDirectiveParameters()
  {
    return daoDirectiveParametersEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaoDirectiveParameters_Out()
  {
    return (EReference)daoDirectiveParametersEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaoDirectiveParameters_Ins()
  {
    return (EReference)daoDirectiveParametersEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDescendantAssignment()
  {
    return descendantAssignmentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDescendantAssignment_Value()
  {
    return (EReference)descendantAssignmentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDescendantAssignment_Descendant()
  {
    return (EReference)descendantAssignmentEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFunProcType()
  {
    return funProcTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDaoDirective()
  {
    return daoDirectiveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDaoEntity()
  {
    return daoEntityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaoEntity_Directives()
  {
    return (EReference)daoEntityEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDaoEntity_Abstract()
  {
    return (EAttribute)daoEntityEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaoEntity_SuperType()
  {
    return (EReference)daoEntityEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaoEntity_Attributes()
  {
    return (EReference)daoEntityEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnnotationDirective()
  {
    return annotationDirectiveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnnotation()
  {
    return annotationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAnnotation_Directives()
  {
    return (EReference)annotationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAnnotation_Final()
  {
    return (EAttribute)annotationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAnnotation_Annotation()
  {
    return (EReference)annotationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPackageDirectiveImplementation()
  {
    return packageDirectiveImplementationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPackageDirectiveImplementation_Implementation()
  {
    return (EAttribute)packageDirectiveImplementationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImplementsExtendsDirectiveGenerics()
  {
    return implementsExtendsDirectiveGenericsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImplementsExtendsDirectiveOnlyPojos()
  {
    return implementsExtendsDirectiveOnlyPojosEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getImplementsExtendsDirectiveOnlyPojos_OnlyPojos()
  {
    return (EReference)implementsExtendsDirectiveOnlyPojosEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImplementsExtendsDirectiveOnlyDaos()
  {
    return implementsExtendsDirectiveOnlyDaosEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getImplementsExtendsDirectiveOnlyDaos_OnlyDaos()
  {
    return (EReference)implementsExtendsDirectiveOnlyDaosEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImplementsExtendsDirectiveExceptPojos()
  {
    return implementsExtendsDirectiveExceptPojosEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getImplementsExtendsDirectiveExceptPojos_ExceptPojos()
  {
    return (EReference)implementsExtendsDirectiveExceptPojosEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImplementsExtendsDirectiveExceptDaos()
  {
    return implementsExtendsDirectiveExceptDaosEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getImplementsExtendsDirectiveExceptDaos_ExceptDaos()
  {
    return (EReference)implementsExtendsDirectiveExceptDaosEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoDirectiveToString()
  {
    return pojoDirectiveToStringEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoDirectiveToString_Proplist()
  {
    return (EReference)pojoDirectiveToStringEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoDirectiveIndex()
  {
    return pojoDirectiveIndexEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoDirectiveIndex_Index()
  {
    return (EReference)pojoDirectiveIndexEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoDirectiveIndex_Proplist()
  {
    return (EReference)pojoDirectiveIndexEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoDirectiveEnumIndex()
  {
    return pojoDirectiveEnumIndexEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoDirectiveEnumIndex_Index()
  {
    return (EReference)pojoDirectiveEnumIndexEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoDirectiveEnumIndex_Proplist()
  {
    return (EReference)pojoDirectiveEnumIndexEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoDirectiveOperators()
  {
    return pojoDirectiveOperatorsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoDirectiveOperators_OperatorsSuffix()
  {
    return (EAttribute)pojoDirectiveOperatorsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoDirectiveSerializable()
  {
    return pojoDirectiveSerializableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoDirectiveSerializable_Sernum()
  {
    return (EAttribute)pojoDirectiveSerializableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoDirectiveDiscriminator()
  {
    return pojoDirectiveDiscriminatorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoDirectiveDiscriminator_Discriminator()
  {
    return (EAttribute)pojoDirectiveDiscriminatorEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoDirectiveEquals()
  {
    return pojoDirectiveEqualsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoDirectiveEquals_Proplist()
  {
    return (EReference)pojoDirectiveEqualsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoDirectiveHashCode()
  {
    return pojoDirectiveHashCodeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoDirectiveHashCode_Proplist()
  {
    return (EReference)pojoDirectiveHashCodeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoDirectiveProcessingId()
  {
    return pojoDirectiveProcessingIdEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoDirectiveProcessingId_Proplist()
  {
    return (EReference)pojoDirectiveProcessingIdEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoAttributeDirectiveRequired()
  {
    return pojoAttributeDirectiveRequiredEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoAttributeDirectivePrimaryKey()
  {
    return pojoAttributeDirectivePrimaryKeyEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoAttributeDirectivePrimaryKey_FromTo()
  {
    return (EAttribute)pojoAttributeDirectivePrimaryKeyEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoAttributeDirectivePrimaryKey_Name()
  {
    return (EAttribute)pojoAttributeDirectivePrimaryKeyEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoAttributeDirectivePrimaryKey_Type()
  {
    return (EAttribute)pojoAttributeDirectivePrimaryKeyEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoAttributeDirectiveDiscriminator()
  {
    return pojoAttributeDirectiveDiscriminatorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoAttributeDirectiveIndex()
  {
    return pojoAttributeDirectiveIndexEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoAttributeDirectiveIndex_Index()
  {
    return (EReference)pojoAttributeDirectiveIndexEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoAttributeDirectiveEnumIndex()
  {
    return pojoAttributeDirectiveEnumIndexEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoAttributeDirectiveEnumIndex_Index()
  {
    return (EReference)pojoAttributeDirectiveEnumIndexEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoAttributeDirectiveVersion()
  {
    return pojoAttributeDirectiveVersionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoAttributeDirectiveUpdateCol()
  {
    return pojoAttributeDirectiveUpdateColEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoAttributeDirectiveUpdateCol_UpdateColumn1()
  {
    return (EAttribute)pojoAttributeDirectiveUpdateColEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoAttributeDirectiveUpdateCol_UpdateColumn2()
  {
    return (EReference)pojoAttributeDirectiveUpdateColEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoAttributeDirectiveCreateCol()
  {
    return pojoAttributeDirectiveCreateColEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPojoAttributeDirectiveCreateCol_CreateColumn1()
  {
    return (EReference)pojoAttributeDirectiveCreateColEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPojoAttributeDirectiveCreateCol_CreateColumn2()
  {
    return (EAttribute)pojoAttributeDirectiveCreateColEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoAttributeDirectiveToInit()
  {
    return pojoAttributeDirectiveToInitEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoAttributeDirectiveEnumInit()
  {
    return pojoAttributeDirectiveEnumInitEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoAttributeDirectiveIsDef()
  {
    return pojoAttributeDirectiveIsDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPojoAttributeDirectiveEnumDef()
  {
    return pojoAttributeDirectiveEnumDefEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEnumDirectiveSerializable()
  {
    return enumDirectiveSerializableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEnumDirectiveSerializable_Sernum()
  {
    return (EAttribute)enumDirectiveSerializableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEnumAttributeDirectiveValues()
  {
    return enumAttributeDirectiveValuesEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumAttributeDirectiveValues_Values()
  {
    return (EReference)enumAttributeDirectiveValuesEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFunctionCallQuery()
  {
    return functionCallQueryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getProcedureCallQuery()
  {
    return procedureCallQueryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFunctionCall()
  {
    return functionCallEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getProcedureUpdate()
  {
    return procedureUpdateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFunctionQuery()
  {
    return functionQueryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDaoDirectiveSerializable()
  {
    return daoDirectiveSerializableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDaoDirectiveSerializable_Sernum()
  {
    return (EAttribute)daoDirectiveSerializableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDaoDirectiveDiscriminator()
  {
    return daoDirectiveDiscriminatorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaoDirectiveDiscriminator_Ancestor()
  {
    return (EReference)daoDirectiveDiscriminatorEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaoDirectiveDiscriminator_Descendants()
  {
    return (EReference)daoDirectiveDiscriminatorEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDaoDirectiveCrud()
  {
    return daoDirectiveCrudEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaoDirectiveCrud_Pojo()
  {
    return (EReference)daoDirectiveCrudEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDaoDirectiveQuery()
  {
    return daoDirectiveQueryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaoDirectiveQuery_Pojo()
  {
    return (EReference)daoDirectiveQueryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDaoFunProcDirective()
  {
    return daoFunProcDirectiveEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaoFunProcDirective_Type()
  {
    return (EReference)daoFunProcDirectiveEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaoFunProcDirective_Paramlist()
  {
    return (EReference)daoFunProcDirectiveEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDaoDirectivePojo()
  {
    return daoDirectivePojoEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDaoDirectivePojo_Pojo()
  {
    return (EReference)daoDirectivePojoEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnnotationDirectiveConflict()
  {
    return annotationDirectiveConflictEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnnotationDirectiveStatic()
  {
    return annotationDirectiveStaticEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnnotationDirectiveConstructor()
  {
    return annotationDirectiveConstructorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnnotationDirectiveStandard()
  {
    return annotationDirectiveStandardEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnnotationDirectiveSetter()
  {
    return annotationDirectiveSetterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnnotationDirectiveGetter()
  {
    return annotationDirectiveGetterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnnotationDirectiveAttribute()
  {
    return annotationDirectiveAttributeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProcessorModelFactory getProcessorModelFactory()
  {
    return (ProcessorModelFactory)getEFactoryInstance();
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
    createEReference(artifactsEClass, ARTIFACTS__PACKAGES);

    valueTypeEClass = createEClass(VALUE_TYPE);
    createEAttribute(valueTypeEClass, VALUE_TYPE__VALUE);
    createEAttribute(valueTypeEClass, VALUE_TYPE__NUMBER);
    createEAttribute(valueTypeEClass, VALUE_TYPE__ID);

    indexTypeEClass = createEClass(INDEX_TYPE);
    createEAttribute(indexTypeEClass, INDEX_TYPE__NUMBER);
    createEAttribute(indexTypeEClass, INDEX_TYPE__ID);

    pojoTypeEClass = createEClass(POJO_TYPE);
    createEReference(pojoTypeEClass, POJO_TYPE__IDENT);
    createEReference(pojoTypeEClass, POJO_TYPE__REF);
    createEReference(pojoTypeEClass, POJO_TYPE__TYPE);

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

    packageDirectiveEClass = createEClass(PACKAGE_DIRECTIVE);

    packageEClass = createEClass(PACKAGE);
    createEReference(packageEClass, PACKAGE__DIRECTIVES);
    createEAttribute(packageEClass, PACKAGE__NAME);
    createEReference(packageEClass, PACKAGE__IMPORT_SECTION);
    createEReference(packageEClass, PACKAGE__ELEMENTS);

    abstractEntityEClass = createEClass(ABSTRACT_ENTITY);

    implementsExtendsDirectiveEClass = createEClass(IMPLEMENTS_EXTENDS_DIRECTIVE);

    implementsEClass = createEClass(IMPLEMENTS);
    createEReference(implementsEClass, IMPLEMENTS__DIRECTIVES);
    createEReference(implementsEClass, IMPLEMENTS__IMPLEMENTS);

    extendsEClass = createEClass(EXTENDS);
    createEReference(extendsEClass, EXTENDS__DIRECTIVES);
    createEReference(extendsEClass, EXTENDS__EXTENDS);

    annotatedEntityEClass = createEClass(ANNOTATED_ENTITY);
    createEReference(annotatedEntityEClass, ANNOTATED_ENTITY__ANNOTATIONS);
    createEReference(annotatedEntityEClass, ANNOTATED_ENTITY__ENTITY);

    entityEClass = createEClass(ENTITY);
    createEAttribute(entityEClass, ENTITY__FINAL);
    createEAttribute(entityEClass, ENTITY__NAME);
    createEReference(entityEClass, ENTITY__PROCEDURES);

    directivePropertiesEClass = createEClass(DIRECTIVE_PROPERTIES);
    createEReference(directivePropertiesEClass, DIRECTIVE_PROPERTIES__FEATURES);

    pojoDirectiveEClass = createEClass(POJO_DIRECTIVE);

    pojoEntityEClass = createEClass(POJO_ENTITY);
    createEReference(pojoEntityEClass, POJO_ENTITY__DIRECTIVES);
    createEAttribute(pojoEntityEClass, POJO_ENTITY__ABSTRACT);
    createEReference(pojoEntityEClass, POJO_ENTITY__SUPER_TYPE);
    createEReference(pojoEntityEClass, POJO_ENTITY__ATTRIBUTES);

    pojoAttributeDirectiveEClass = createEClass(POJO_ATTRIBUTE_DIRECTIVE);

    pojoAttributeEClass = createEClass(POJO_ATTRIBUTE);
    createEReference(pojoAttributeEClass, POJO_ATTRIBUTE__ANNOTATIONS);
    createEReference(pojoAttributeEClass, POJO_ATTRIBUTE__DIRECTIVES);
    createEAttribute(pojoAttributeEClass, POJO_ATTRIBUTE__FINAL);
    createEAttribute(pojoAttributeEClass, POJO_ATTRIBUTE__STATIC);
    createEAttribute(pojoAttributeEClass, POJO_ATTRIBUTE__KIND);
    createEReference(pojoAttributeEClass, POJO_ATTRIBUTE__TYPE);
    createEAttribute(pojoAttributeEClass, POJO_ATTRIBUTE__NAME);
    createEReference(pojoAttributeEClass, POJO_ATTRIBUTE__INIT_EXPR);

    pojoProcedureEClass = createEClass(POJO_PROCEDURE);
    createEReference(pojoProcedureEClass, POJO_PROCEDURE__ANNOTATIONS);
    createEAttribute(pojoProcedureEClass, POJO_PROCEDURE__FINAL);
    createEAttribute(pojoProcedureEClass, POJO_PROCEDURE__STATIC);
    createEReference(pojoProcedureEClass, POJO_PROCEDURE__TYPE);
    createEAttribute(pojoProcedureEClass, POJO_PROCEDURE__NAME);
    createEReference(pojoProcedureEClass, POJO_PROCEDURE__PARAMS);
    createEReference(pojoProcedureEClass, POJO_PROCEDURE__BODY);

    enumDirectiveEClass = createEClass(ENUM_DIRECTIVE);

    enumEntityEClass = createEClass(ENUM_ENTITY);
    createEReference(enumEntityEClass, ENUM_ENTITY__DIRECTIVES);
    createEReference(enumEntityEClass, ENUM_ENTITY__ATTRIBUTE);

    enumAttributeValueEClass = createEClass(ENUM_ATTRIBUTE_VALUE);
    createEAttribute(enumAttributeValueEClass, ENUM_ATTRIBUTE_VALUE__NAME);
    createEReference(enumAttributeValueEClass, ENUM_ATTRIBUTE_VALUE__VALUE);

    enumAttributeDirectiveEClass = createEClass(ENUM_ATTRIBUTE_DIRECTIVE);

    enumAttributeEClass = createEClass(ENUM_ATTRIBUTE);
    createEReference(enumAttributeEClass, ENUM_ATTRIBUTE__DIRECTIVES);
    createEAttribute(enumAttributeEClass, ENUM_ATTRIBUTE__FINAL);
    createEAttribute(enumAttributeEClass, ENUM_ATTRIBUTE__STATIC);
    createEReference(enumAttributeEClass, ENUM_ATTRIBUTE__TYPE);
    createEAttribute(enumAttributeEClass, ENUM_ATTRIBUTE__NAME);

    daoDirectiveParametersEClass = createEClass(DAO_DIRECTIVE_PARAMETERS);
    createEReference(daoDirectiveParametersEClass, DAO_DIRECTIVE_PARAMETERS__OUT);
    createEReference(daoDirectiveParametersEClass, DAO_DIRECTIVE_PARAMETERS__INS);

    descendantAssignmentEClass = createEClass(DESCENDANT_ASSIGNMENT);
    createEReference(descendantAssignmentEClass, DESCENDANT_ASSIGNMENT__VALUE);
    createEReference(descendantAssignmentEClass, DESCENDANT_ASSIGNMENT__DESCENDANT);

    funProcTypeEClass = createEClass(FUN_PROC_TYPE);

    daoDirectiveEClass = createEClass(DAO_DIRECTIVE);

    daoEntityEClass = createEClass(DAO_ENTITY);
    createEReference(daoEntityEClass, DAO_ENTITY__DIRECTIVES);
    createEAttribute(daoEntityEClass, DAO_ENTITY__ABSTRACT);
    createEReference(daoEntityEClass, DAO_ENTITY__SUPER_TYPE);
    createEReference(daoEntityEClass, DAO_ENTITY__ATTRIBUTES);

    annotationDirectiveEClass = createEClass(ANNOTATION_DIRECTIVE);

    annotationEClass = createEClass(ANNOTATION);
    createEReference(annotationEClass, ANNOTATION__DIRECTIVES);
    createEAttribute(annotationEClass, ANNOTATION__FINAL);
    createEReference(annotationEClass, ANNOTATION__ANNOTATION);

    packageDirectiveImplementationEClass = createEClass(PACKAGE_DIRECTIVE_IMPLEMENTATION);
    createEAttribute(packageDirectiveImplementationEClass, PACKAGE_DIRECTIVE_IMPLEMENTATION__IMPLEMENTATION);

    implementsExtendsDirectiveGenericsEClass = createEClass(IMPLEMENTS_EXTENDS_DIRECTIVE_GENERICS);

    implementsExtendsDirectiveOnlyPojosEClass = createEClass(IMPLEMENTS_EXTENDS_DIRECTIVE_ONLY_POJOS);
    createEReference(implementsExtendsDirectiveOnlyPojosEClass, IMPLEMENTS_EXTENDS_DIRECTIVE_ONLY_POJOS__ONLY_POJOS);

    implementsExtendsDirectiveOnlyDaosEClass = createEClass(IMPLEMENTS_EXTENDS_DIRECTIVE_ONLY_DAOS);
    createEReference(implementsExtendsDirectiveOnlyDaosEClass, IMPLEMENTS_EXTENDS_DIRECTIVE_ONLY_DAOS__ONLY_DAOS);

    implementsExtendsDirectiveExceptPojosEClass = createEClass(IMPLEMENTS_EXTENDS_DIRECTIVE_EXCEPT_POJOS);
    createEReference(implementsExtendsDirectiveExceptPojosEClass, IMPLEMENTS_EXTENDS_DIRECTIVE_EXCEPT_POJOS__EXCEPT_POJOS);

    implementsExtendsDirectiveExceptDaosEClass = createEClass(IMPLEMENTS_EXTENDS_DIRECTIVE_EXCEPT_DAOS);
    createEReference(implementsExtendsDirectiveExceptDaosEClass, IMPLEMENTS_EXTENDS_DIRECTIVE_EXCEPT_DAOS__EXCEPT_DAOS);

    pojoDirectiveToStringEClass = createEClass(POJO_DIRECTIVE_TO_STRING);
    createEReference(pojoDirectiveToStringEClass, POJO_DIRECTIVE_TO_STRING__PROPLIST);

    pojoDirectiveIndexEClass = createEClass(POJO_DIRECTIVE_INDEX);
    createEReference(pojoDirectiveIndexEClass, POJO_DIRECTIVE_INDEX__INDEX);
    createEReference(pojoDirectiveIndexEClass, POJO_DIRECTIVE_INDEX__PROPLIST);

    pojoDirectiveEnumIndexEClass = createEClass(POJO_DIRECTIVE_ENUM_INDEX);
    createEReference(pojoDirectiveEnumIndexEClass, POJO_DIRECTIVE_ENUM_INDEX__INDEX);
    createEReference(pojoDirectiveEnumIndexEClass, POJO_DIRECTIVE_ENUM_INDEX__PROPLIST);

    pojoDirectiveOperatorsEClass = createEClass(POJO_DIRECTIVE_OPERATORS);
    createEAttribute(pojoDirectiveOperatorsEClass, POJO_DIRECTIVE_OPERATORS__OPERATORS_SUFFIX);

    pojoDirectiveSerializableEClass = createEClass(POJO_DIRECTIVE_SERIALIZABLE);
    createEAttribute(pojoDirectiveSerializableEClass, POJO_DIRECTIVE_SERIALIZABLE__SERNUM);

    pojoDirectiveDiscriminatorEClass = createEClass(POJO_DIRECTIVE_DISCRIMINATOR);
    createEAttribute(pojoDirectiveDiscriminatorEClass, POJO_DIRECTIVE_DISCRIMINATOR__DISCRIMINATOR);

    pojoDirectiveEqualsEClass = createEClass(POJO_DIRECTIVE_EQUALS);
    createEReference(pojoDirectiveEqualsEClass, POJO_DIRECTIVE_EQUALS__PROPLIST);

    pojoDirectiveHashCodeEClass = createEClass(POJO_DIRECTIVE_HASH_CODE);
    createEReference(pojoDirectiveHashCodeEClass, POJO_DIRECTIVE_HASH_CODE__PROPLIST);

    pojoDirectiveProcessingIdEClass = createEClass(POJO_DIRECTIVE_PROCESSING_ID);
    createEReference(pojoDirectiveProcessingIdEClass, POJO_DIRECTIVE_PROCESSING_ID__PROPLIST);

    pojoAttributeDirectiveRequiredEClass = createEClass(POJO_ATTRIBUTE_DIRECTIVE_REQUIRED);

    pojoAttributeDirectivePrimaryKeyEClass = createEClass(POJO_ATTRIBUTE_DIRECTIVE_PRIMARY_KEY);
    createEAttribute(pojoAttributeDirectivePrimaryKeyEClass, POJO_ATTRIBUTE_DIRECTIVE_PRIMARY_KEY__FROM_TO);
    createEAttribute(pojoAttributeDirectivePrimaryKeyEClass, POJO_ATTRIBUTE_DIRECTIVE_PRIMARY_KEY__NAME);
    createEAttribute(pojoAttributeDirectivePrimaryKeyEClass, POJO_ATTRIBUTE_DIRECTIVE_PRIMARY_KEY__TYPE);

    pojoAttributeDirectiveDiscriminatorEClass = createEClass(POJO_ATTRIBUTE_DIRECTIVE_DISCRIMINATOR);

    pojoAttributeDirectiveIndexEClass = createEClass(POJO_ATTRIBUTE_DIRECTIVE_INDEX);
    createEReference(pojoAttributeDirectiveIndexEClass, POJO_ATTRIBUTE_DIRECTIVE_INDEX__INDEX);

    pojoAttributeDirectiveEnumIndexEClass = createEClass(POJO_ATTRIBUTE_DIRECTIVE_ENUM_INDEX);
    createEReference(pojoAttributeDirectiveEnumIndexEClass, POJO_ATTRIBUTE_DIRECTIVE_ENUM_INDEX__INDEX);

    pojoAttributeDirectiveVersionEClass = createEClass(POJO_ATTRIBUTE_DIRECTIVE_VERSION);

    pojoAttributeDirectiveUpdateColEClass = createEClass(POJO_ATTRIBUTE_DIRECTIVE_UPDATE_COL);
    createEAttribute(pojoAttributeDirectiveUpdateColEClass, POJO_ATTRIBUTE_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN1);
    createEReference(pojoAttributeDirectiveUpdateColEClass, POJO_ATTRIBUTE_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN2);

    pojoAttributeDirectiveCreateColEClass = createEClass(POJO_ATTRIBUTE_DIRECTIVE_CREATE_COL);
    createEReference(pojoAttributeDirectiveCreateColEClass, POJO_ATTRIBUTE_DIRECTIVE_CREATE_COL__CREATE_COLUMN1);
    createEAttribute(pojoAttributeDirectiveCreateColEClass, POJO_ATTRIBUTE_DIRECTIVE_CREATE_COL__CREATE_COLUMN2);

    pojoAttributeDirectiveToInitEClass = createEClass(POJO_ATTRIBUTE_DIRECTIVE_TO_INIT);

    pojoAttributeDirectiveEnumInitEClass = createEClass(POJO_ATTRIBUTE_DIRECTIVE_ENUM_INIT);

    pojoAttributeDirectiveIsDefEClass = createEClass(POJO_ATTRIBUTE_DIRECTIVE_IS_DEF);

    pojoAttributeDirectiveEnumDefEClass = createEClass(POJO_ATTRIBUTE_DIRECTIVE_ENUM_DEF);

    enumDirectiveSerializableEClass = createEClass(ENUM_DIRECTIVE_SERIALIZABLE);
    createEAttribute(enumDirectiveSerializableEClass, ENUM_DIRECTIVE_SERIALIZABLE__SERNUM);

    enumAttributeDirectiveValuesEClass = createEClass(ENUM_ATTRIBUTE_DIRECTIVE_VALUES);
    createEReference(enumAttributeDirectiveValuesEClass, ENUM_ATTRIBUTE_DIRECTIVE_VALUES__VALUES);

    functionCallQueryEClass = createEClass(FUNCTION_CALL_QUERY);

    procedureCallQueryEClass = createEClass(PROCEDURE_CALL_QUERY);

    functionCallEClass = createEClass(FUNCTION_CALL);

    procedureUpdateEClass = createEClass(PROCEDURE_UPDATE);

    functionQueryEClass = createEClass(FUNCTION_QUERY);

    daoDirectiveSerializableEClass = createEClass(DAO_DIRECTIVE_SERIALIZABLE);
    createEAttribute(daoDirectiveSerializableEClass, DAO_DIRECTIVE_SERIALIZABLE__SERNUM);

    daoDirectiveDiscriminatorEClass = createEClass(DAO_DIRECTIVE_DISCRIMINATOR);
    createEReference(daoDirectiveDiscriminatorEClass, DAO_DIRECTIVE_DISCRIMINATOR__ANCESTOR);
    createEReference(daoDirectiveDiscriminatorEClass, DAO_DIRECTIVE_DISCRIMINATOR__DESCENDANTS);

    daoDirectiveCrudEClass = createEClass(DAO_DIRECTIVE_CRUD);
    createEReference(daoDirectiveCrudEClass, DAO_DIRECTIVE_CRUD__POJO);

    daoDirectiveQueryEClass = createEClass(DAO_DIRECTIVE_QUERY);
    createEReference(daoDirectiveQueryEClass, DAO_DIRECTIVE_QUERY__POJO);

    daoFunProcDirectiveEClass = createEClass(DAO_FUN_PROC_DIRECTIVE);
    createEReference(daoFunProcDirectiveEClass, DAO_FUN_PROC_DIRECTIVE__TYPE);
    createEReference(daoFunProcDirectiveEClass, DAO_FUN_PROC_DIRECTIVE__PARAMLIST);

    daoDirectivePojoEClass = createEClass(DAO_DIRECTIVE_POJO);
    createEReference(daoDirectivePojoEClass, DAO_DIRECTIVE_POJO__POJO);

    annotationDirectiveConflictEClass = createEClass(ANNOTATION_DIRECTIVE_CONFLICT);

    annotationDirectiveStaticEClass = createEClass(ANNOTATION_DIRECTIVE_STATIC);

    annotationDirectiveConstructorEClass = createEClass(ANNOTATION_DIRECTIVE_CONSTRUCTOR);

    annotationDirectiveStandardEClass = createEClass(ANNOTATION_DIRECTIVE_STANDARD);

    annotationDirectiveSetterEClass = createEClass(ANNOTATION_DIRECTIVE_SETTER);

    annotationDirectiveGetterEClass = createEClass(ANNOTATION_DIRECTIVE_GETTER);

    annotationDirectiveAttributeEClass = createEClass(ANNOTATION_DIRECTIVE_ATTRIBUTE);
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
    XtypePackage theXtypePackage = (XtypePackage)EPackage.Registry.INSTANCE.getEPackage(XtypePackage.eNS_URI);
    XbasePackage theXbasePackage = (XbasePackage)EPackage.Registry.INSTANCE.getEPackage(XbasePackage.eNS_URI);
    XAnnotationsPackage theXAnnotationsPackage = (XAnnotationsPackage)EPackage.Registry.INSTANCE.getEPackage(XAnnotationsPackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    implementsEClass.getESuperTypes().add(this.getAbstractEntity());
    extendsEClass.getESuperTypes().add(this.getAbstractEntity());
    annotatedEntityEClass.getESuperTypes().add(this.getAbstractEntity());
    pojoEntityEClass.getESuperTypes().add(this.getEntity());
    enumEntityEClass.getESuperTypes().add(this.getEntity());
    daoEntityEClass.getESuperTypes().add(this.getEntity());
    packageDirectiveImplementationEClass.getESuperTypes().add(this.getPackageDirective());
    implementsExtendsDirectiveGenericsEClass.getESuperTypes().add(this.getImplementsExtendsDirective());
    implementsExtendsDirectiveOnlyPojosEClass.getESuperTypes().add(this.getImplementsExtendsDirective());
    implementsExtendsDirectiveOnlyDaosEClass.getESuperTypes().add(this.getImplementsExtendsDirective());
    implementsExtendsDirectiveExceptPojosEClass.getESuperTypes().add(this.getImplementsExtendsDirective());
    implementsExtendsDirectiveExceptDaosEClass.getESuperTypes().add(this.getImplementsExtendsDirective());
    pojoDirectiveToStringEClass.getESuperTypes().add(this.getPojoDirective());
    pojoDirectiveIndexEClass.getESuperTypes().add(this.getPojoDirective());
    pojoDirectiveEnumIndexEClass.getESuperTypes().add(this.getPojoDirective());
    pojoDirectiveOperatorsEClass.getESuperTypes().add(this.getPojoDirective());
    pojoDirectiveSerializableEClass.getESuperTypes().add(this.getPojoDirective());
    pojoDirectiveDiscriminatorEClass.getESuperTypes().add(this.getPojoDirective());
    pojoDirectiveEqualsEClass.getESuperTypes().add(this.getPojoDirective());
    pojoDirectiveHashCodeEClass.getESuperTypes().add(this.getPojoDirective());
    pojoDirectiveProcessingIdEClass.getESuperTypes().add(this.getPojoDirective());
    pojoAttributeDirectiveRequiredEClass.getESuperTypes().add(this.getPojoAttributeDirective());
    pojoAttributeDirectivePrimaryKeyEClass.getESuperTypes().add(this.getPojoAttributeDirective());
    pojoAttributeDirectiveDiscriminatorEClass.getESuperTypes().add(this.getPojoAttributeDirective());
    pojoAttributeDirectiveIndexEClass.getESuperTypes().add(this.getPojoAttributeDirective());
    pojoAttributeDirectiveEnumIndexEClass.getESuperTypes().add(this.getPojoAttributeDirective());
    pojoAttributeDirectiveVersionEClass.getESuperTypes().add(this.getPojoAttributeDirective());
    pojoAttributeDirectiveUpdateColEClass.getESuperTypes().add(this.getPojoAttributeDirective());
    pojoAttributeDirectiveCreateColEClass.getESuperTypes().add(this.getPojoAttributeDirective());
    pojoAttributeDirectiveToInitEClass.getESuperTypes().add(this.getPojoAttributeDirective());
    pojoAttributeDirectiveEnumInitEClass.getESuperTypes().add(this.getPojoAttributeDirective());
    pojoAttributeDirectiveIsDefEClass.getESuperTypes().add(this.getPojoAttributeDirective());
    pojoAttributeDirectiveEnumDefEClass.getESuperTypes().add(this.getPojoAttributeDirective());
    enumDirectiveSerializableEClass.getESuperTypes().add(this.getEnumDirective());
    enumAttributeDirectiveValuesEClass.getESuperTypes().add(this.getEnumAttributeDirective());
    functionCallQueryEClass.getESuperTypes().add(this.getFunProcType());
    procedureCallQueryEClass.getESuperTypes().add(this.getFunProcType());
    functionCallEClass.getESuperTypes().add(this.getFunProcType());
    procedureUpdateEClass.getESuperTypes().add(this.getFunProcType());
    functionQueryEClass.getESuperTypes().add(this.getFunProcType());
    daoDirectiveSerializableEClass.getESuperTypes().add(this.getDaoDirective());
    daoDirectiveDiscriminatorEClass.getESuperTypes().add(this.getDaoDirective());
    daoDirectiveCrudEClass.getESuperTypes().add(this.getDaoDirective());
    daoDirectiveQueryEClass.getESuperTypes().add(this.getDaoDirective());
    daoFunProcDirectiveEClass.getESuperTypes().add(this.getDaoDirective());
    daoDirectivePojoEClass.getESuperTypes().add(this.getDaoDirective());
    annotationDirectiveConflictEClass.getESuperTypes().add(this.getAnnotationDirective());
    annotationDirectiveStaticEClass.getESuperTypes().add(this.getAnnotationDirective());
    annotationDirectiveConstructorEClass.getESuperTypes().add(this.getAnnotationDirective());
    annotationDirectiveStandardEClass.getESuperTypes().add(this.getAnnotationDirective());
    annotationDirectiveSetterEClass.getESuperTypes().add(this.getAnnotationDirective());
    annotationDirectiveGetterEClass.getESuperTypes().add(this.getAnnotationDirective());
    annotationDirectiveAttributeEClass.getESuperTypes().add(this.getAnnotationDirective());

    // Initialize classes and features; add operations and parameters
    initEClass(artifactsEClass, Artifacts.class, "Artifacts", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getArtifacts_Properties(), this.getProperty(), null, "properties", null, 0, -1, Artifacts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArtifacts_Pojos(), this.getPojoDefinitionModel(), null, "pojos", null, 0, -1, Artifacts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArtifacts_Tables(), this.getTableDefinitionModel(), null, "tables", null, 0, -1, Artifacts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArtifacts_Procedures(), this.getProcedureDefinitionModel(), null, "procedures", null, 0, -1, Artifacts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArtifacts_Functions(), this.getFunctionDefinitionModel(), null, "functions", null, 0, -1, Artifacts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArtifacts_Packages(), this.getPackage(), null, "packages", null, 0, -1, Artifacts.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(valueTypeEClass, ValueType.class, "ValueType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getValueType_Value(), ecorePackage.getEString(), "value", null, 0, 1, ValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getValueType_Number(), ecorePackage.getEInt(), "number", null, 0, 1, ValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getValueType_Id(), ecorePackage.getEString(), "id", null, 0, 1, ValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(indexTypeEClass, IndexType.class, "IndexType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIndexType_Number(), ecorePackage.getEInt(), "number", null, 0, 1, IndexType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getIndexType_Id(), ecorePackage.getEString(), "id", null, 0, 1, IndexType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoTypeEClass, PojoType.class, "PojoType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPojoType_Ident(), this.getValueType(), null, "ident", null, 0, 1, PojoType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoType_Ref(), this.getPojoDefinitionModel(), null, "ref", null, 0, 1, PojoType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoType_Type(), theTypesPackage.getJvmParameterizedTypeReference(), null, "type", null, 0, 1, PojoType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
    initEReference(getPojoDefinitionModel_Classx(), theTypesPackage.getJvmParameterizedTypeReference(), null, "classx", null, 0, 1, PojoDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(tableDefinitionModelEClass, TableDefinitionModel.class, "TableDefinitionModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getTableDefinitionModel_Name(), ecorePackage.getEString(), "name", null, 0, 1, TableDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTableDefinitionModel_Table(), ecorePackage.getEString(), "table", null, 0, 1, TableDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(procedureDefinitionModelEClass, ProcedureDefinitionModel.class, "ProcedureDefinitionModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getProcedureDefinitionModel_Name(), ecorePackage.getEString(), "name", null, 0, 1, ProcedureDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getProcedureDefinitionModel_Table(), ecorePackage.getEString(), "table", null, 0, 1, ProcedureDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(functionDefinitionModelEClass, FunctionDefinitionModel.class, "FunctionDefinitionModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getFunctionDefinitionModel_Name(), ecorePackage.getEString(), "name", null, 0, 1, FunctionDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getFunctionDefinitionModel_Table(), ecorePackage.getEString(), "table", null, 0, 1, FunctionDefinitionModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(packageDirectiveEClass, PackageDirective.class, "PackageDirective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(packageEClass, org.sqlproc.model.processorModel.Package.class, "Package", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPackage_Directives(), this.getPackageDirective(), null, "directives", null, 0, -1, org.sqlproc.model.processorModel.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPackage_Name(), ecorePackage.getEString(), "name", null, 0, 1, org.sqlproc.model.processorModel.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPackage_ImportSection(), theXtypePackage.getXImportSection(), null, "importSection", null, 0, 1, org.sqlproc.model.processorModel.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPackage_Elements(), this.getAbstractEntity(), null, "elements", null, 0, -1, org.sqlproc.model.processorModel.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(abstractEntityEClass, AbstractEntity.class, "AbstractEntity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(implementsExtendsDirectiveEClass, ImplementsExtendsDirective.class, "ImplementsExtendsDirective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(implementsEClass, Implements.class, "Implements", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getImplements_Directives(), this.getImplementsExtendsDirective(), null, "directives", null, 0, -1, Implements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getImplements_Implements(), theTypesPackage.getJvmParameterizedTypeReference(), null, "implements", null, 0, 1, Implements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(extendsEClass, Extends.class, "Extends", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getExtends_Directives(), this.getImplementsExtendsDirective(), null, "directives", null, 0, -1, Extends.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getExtends_Extends(), theTypesPackage.getJvmParameterizedTypeReference(), null, "extends", null, 0, 1, Extends.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(annotatedEntityEClass, AnnotatedEntity.class, "AnnotatedEntity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAnnotatedEntity_Annotations(), this.getAnnotation(), null, "annotations", null, 0, -1, AnnotatedEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAnnotatedEntity_Entity(), this.getEntity(), null, "entity", null, 0, 1, AnnotatedEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(entityEClass, Entity.class, "Entity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEntity_Final(), ecorePackage.getEBoolean(), "final", null, 0, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEntity_Name(), ecorePackage.getEString(), "name", null, 0, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEntity_Procedures(), this.getPojoProcedure(), null, "procedures", null, 0, -1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(directivePropertiesEClass, DirectiveProperties.class, "DirectiveProperties", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDirectiveProperties_Features(), this.getPojoAttribute(), null, "features", null, 0, -1, DirectiveProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoDirectiveEClass, PojoDirective.class, "PojoDirective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(pojoEntityEClass, PojoEntity.class, "PojoEntity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPojoEntity_Directives(), this.getPojoDirective(), null, "directives", null, 0, -1, PojoEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojoEntity_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, PojoEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoEntity_SuperType(), theTypesPackage.getJvmParameterizedTypeReference(), null, "superType", null, 0, 1, PojoEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoEntity_Attributes(), this.getPojoAttribute(), null, "attributes", null, 0, -1, PojoEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoAttributeDirectiveEClass, PojoAttributeDirective.class, "PojoAttributeDirective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(pojoAttributeEClass, PojoAttribute.class, "PojoAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPojoAttribute_Annotations(), this.getAnnotation(), null, "annotations", null, 0, -1, PojoAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoAttribute_Directives(), this.getPojoAttributeDirective(), null, "directives", null, 0, -1, PojoAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojoAttribute_Final(), ecorePackage.getEBoolean(), "final", null, 0, 1, PojoAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojoAttribute_Static(), ecorePackage.getEBoolean(), "static", null, 0, 1, PojoAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojoAttribute_Kind(), ecorePackage.getEString(), "kind", null, 0, 1, PojoAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoAttribute_Type(), theTypesPackage.getJvmTypeReference(), null, "type", null, 0, 1, PojoAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojoAttribute_Name(), ecorePackage.getEString(), "name", null, 0, 1, PojoAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoAttribute_InitExpr(), theXbasePackage.getXExpression(), null, "initExpr", null, 0, 1, PojoAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoProcedureEClass, PojoProcedure.class, "PojoProcedure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPojoProcedure_Annotations(), this.getAnnotation(), null, "annotations", null, 0, -1, PojoProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojoProcedure_Final(), ecorePackage.getEBoolean(), "final", null, 0, 1, PojoProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojoProcedure_Static(), ecorePackage.getEBoolean(), "static", null, 0, 1, PojoProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoProcedure_Type(), theTypesPackage.getJvmTypeReference(), null, "type", null, 0, 1, PojoProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojoProcedure_Name(), ecorePackage.getEString(), "name", null, 0, 1, PojoProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoProcedure_Params(), theTypesPackage.getJvmFormalParameter(), null, "params", null, 0, -1, PojoProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoProcedure_Body(), theXbasePackage.getXExpression(), null, "body", null, 0, 1, PojoProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(enumDirectiveEClass, EnumDirective.class, "EnumDirective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(enumEntityEClass, EnumEntity.class, "EnumEntity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEnumEntity_Directives(), this.getEnumDirective(), null, "directives", null, 0, -1, EnumEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEnumEntity_Attribute(), this.getEnumAttribute(), null, "attribute", null, 0, 1, EnumEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(enumAttributeValueEClass, EnumAttributeValue.class, "EnumAttributeValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEnumAttributeValue_Name(), ecorePackage.getEString(), "name", null, 0, 1, EnumAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEnumAttributeValue_Value(), theXbasePackage.getXExpression(), null, "value", null, 0, 1, EnumAttributeValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(enumAttributeDirectiveEClass, EnumAttributeDirective.class, "EnumAttributeDirective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(enumAttributeEClass, EnumAttribute.class, "EnumAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEnumAttribute_Directives(), this.getEnumAttributeDirective(), null, "directives", null, 0, -1, EnumAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEnumAttribute_Final(), ecorePackage.getEBoolean(), "final", null, 0, 1, EnumAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEnumAttribute_Static(), ecorePackage.getEBoolean(), "static", null, 0, 1, EnumAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEnumAttribute_Type(), theTypesPackage.getJvmTypeReference(), null, "type", null, 0, 1, EnumAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getEnumAttribute_Name(), ecorePackage.getEString(), "name", null, 0, 1, EnumAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(daoDirectiveParametersEClass, DaoDirectiveParameters.class, "DaoDirectiveParameters", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDaoDirectiveParameters_Out(), theTypesPackage.getJvmParameterizedTypeReference(), null, "out", null, 0, 1, DaoDirectiveParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDaoDirectiveParameters_Ins(), theTypesPackage.getJvmParameterizedTypeReference(), null, "ins", null, 0, -1, DaoDirectiveParameters.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(descendantAssignmentEClass, DescendantAssignment.class, "DescendantAssignment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDescendantAssignment_Value(), this.getValueType(), null, "value", null, 0, 1, DescendantAssignment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDescendantAssignment_Descendant(), theTypesPackage.getJvmParameterizedTypeReference(), null, "descendant", null, 0, 1, DescendantAssignment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(funProcTypeEClass, FunProcType.class, "FunProcType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(daoDirectiveEClass, DaoDirective.class, "DaoDirective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(daoEntityEClass, DaoEntity.class, "DaoEntity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDaoEntity_Directives(), this.getDaoDirective(), null, "directives", null, 0, -1, DaoEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDaoEntity_Abstract(), ecorePackage.getEBoolean(), "abstract", null, 0, 1, DaoEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDaoEntity_SuperType(), theTypesPackage.getJvmParameterizedTypeReference(), null, "superType", null, 0, 1, DaoEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDaoEntity_Attributes(), this.getPojoAttribute(), null, "attributes", null, 0, -1, DaoEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(annotationDirectiveEClass, AnnotationDirective.class, "AnnotationDirective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(annotationEClass, Annotation.class, "Annotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAnnotation_Directives(), this.getAnnotationDirective(), null, "directives", null, 0, -1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAnnotation_Final(), ecorePackage.getEBoolean(), "final", null, 0, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAnnotation_Annotation(), theXAnnotationsPackage.getXAnnotation(), null, "annotation", null, 0, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(packageDirectiveImplementationEClass, PackageDirectiveImplementation.class, "PackageDirectiveImplementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPackageDirectiveImplementation_Implementation(), ecorePackage.getEString(), "implementation", null, 0, 1, PackageDirectiveImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(implementsExtendsDirectiveGenericsEClass, ImplementsExtendsDirectiveGenerics.class, "ImplementsExtendsDirectiveGenerics", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(implementsExtendsDirectiveOnlyPojosEClass, ImplementsExtendsDirectiveOnlyPojos.class, "ImplementsExtendsDirectiveOnlyPojos", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getImplementsExtendsDirectiveOnlyPojos_OnlyPojos(), this.getPojoEntity(), null, "onlyPojos", null, 0, -1, ImplementsExtendsDirectiveOnlyPojos.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(implementsExtendsDirectiveOnlyDaosEClass, ImplementsExtendsDirectiveOnlyDaos.class, "ImplementsExtendsDirectiveOnlyDaos", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getImplementsExtendsDirectiveOnlyDaos_OnlyDaos(), this.getDaoEntity(), null, "onlyDaos", null, 0, -1, ImplementsExtendsDirectiveOnlyDaos.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(implementsExtendsDirectiveExceptPojosEClass, ImplementsExtendsDirectiveExceptPojos.class, "ImplementsExtendsDirectiveExceptPojos", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getImplementsExtendsDirectiveExceptPojos_ExceptPojos(), this.getPojoEntity(), null, "exceptPojos", null, 0, -1, ImplementsExtendsDirectiveExceptPojos.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(implementsExtendsDirectiveExceptDaosEClass, ImplementsExtendsDirectiveExceptDaos.class, "ImplementsExtendsDirectiveExceptDaos", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getImplementsExtendsDirectiveExceptDaos_ExceptDaos(), this.getDaoEntity(), null, "exceptDaos", null, 0, -1, ImplementsExtendsDirectiveExceptDaos.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoDirectiveToStringEClass, PojoDirectiveToString.class, "PojoDirectiveToString", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPojoDirectiveToString_Proplist(), this.getDirectiveProperties(), null, "proplist", null, 0, 1, PojoDirectiveToString.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoDirectiveIndexEClass, PojoDirectiveIndex.class, "PojoDirectiveIndex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPojoDirectiveIndex_Index(), this.getIndexType(), null, "index", null, 0, 1, PojoDirectiveIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoDirectiveIndex_Proplist(), this.getDirectiveProperties(), null, "proplist", null, 0, 1, PojoDirectiveIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoDirectiveEnumIndexEClass, PojoDirectiveEnumIndex.class, "PojoDirectiveEnumIndex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPojoDirectiveEnumIndex_Index(), this.getIndexType(), null, "index", null, 0, 1, PojoDirectiveEnumIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoDirectiveEnumIndex_Proplist(), this.getDirectiveProperties(), null, "proplist", null, 0, 1, PojoDirectiveEnumIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoDirectiveOperatorsEClass, PojoDirectiveOperators.class, "PojoDirectiveOperators", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPojoDirectiveOperators_OperatorsSuffix(), ecorePackage.getEString(), "operatorsSuffix", null, 0, 1, PojoDirectiveOperators.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoDirectiveSerializableEClass, PojoDirectiveSerializable.class, "PojoDirectiveSerializable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPojoDirectiveSerializable_Sernum(), ecorePackage.getEInt(), "sernum", null, 0, 1, PojoDirectiveSerializable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoDirectiveDiscriminatorEClass, PojoDirectiveDiscriminator.class, "PojoDirectiveDiscriminator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPojoDirectiveDiscriminator_Discriminator(), ecorePackage.getEString(), "discriminator", null, 0, 1, PojoDirectiveDiscriminator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoDirectiveEqualsEClass, PojoDirectiveEquals.class, "PojoDirectiveEquals", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPojoDirectiveEquals_Proplist(), this.getDirectiveProperties(), null, "proplist", null, 0, 1, PojoDirectiveEquals.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoDirectiveHashCodeEClass, PojoDirectiveHashCode.class, "PojoDirectiveHashCode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPojoDirectiveHashCode_Proplist(), this.getDirectiveProperties(), null, "proplist", null, 0, 1, PojoDirectiveHashCode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoDirectiveProcessingIdEClass, PojoDirectiveProcessingId.class, "PojoDirectiveProcessingId", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPojoDirectiveProcessingId_Proplist(), this.getDirectiveProperties(), null, "proplist", null, 0, 1, PojoDirectiveProcessingId.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoAttributeDirectiveRequiredEClass, PojoAttributeDirectiveRequired.class, "PojoAttributeDirectiveRequired", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(pojoAttributeDirectivePrimaryKeyEClass, PojoAttributeDirectivePrimaryKey.class, "PojoAttributeDirectivePrimaryKey", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPojoAttributeDirectivePrimaryKey_FromTo(), ecorePackage.getEString(), "fromTo", null, 0, 1, PojoAttributeDirectivePrimaryKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojoAttributeDirectivePrimaryKey_Name(), ecorePackage.getEString(), "name", null, 0, 1, PojoAttributeDirectivePrimaryKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojoAttributeDirectivePrimaryKey_Type(), ecorePackage.getEString(), "type", null, 0, 1, PojoAttributeDirectivePrimaryKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoAttributeDirectiveDiscriminatorEClass, PojoAttributeDirectiveDiscriminator.class, "PojoAttributeDirectiveDiscriminator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(pojoAttributeDirectiveIndexEClass, PojoAttributeDirectiveIndex.class, "PojoAttributeDirectiveIndex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPojoAttributeDirectiveIndex_Index(), this.getIndexType(), null, "index", null, 0, 1, PojoAttributeDirectiveIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoAttributeDirectiveEnumIndexEClass, PojoAttributeDirectiveEnumIndex.class, "PojoAttributeDirectiveEnumIndex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPojoAttributeDirectiveEnumIndex_Index(), this.getIndexType(), null, "index", null, 0, 1, PojoAttributeDirectiveEnumIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoAttributeDirectiveVersionEClass, PojoAttributeDirectiveVersion.class, "PojoAttributeDirectiveVersion", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(pojoAttributeDirectiveUpdateColEClass, PojoAttributeDirectiveUpdateCol.class, "PojoAttributeDirectiveUpdateCol", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPojoAttributeDirectiveUpdateCol_UpdateColumn1(), ecorePackage.getEString(), "updateColumn1", null, 0, 1, PojoAttributeDirectiveUpdateCol.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPojoAttributeDirectiveUpdateCol_UpdateColumn2(), this.getPojoAttribute(), null, "updateColumn2", null, 0, 1, PojoAttributeDirectiveUpdateCol.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoAttributeDirectiveCreateColEClass, PojoAttributeDirectiveCreateCol.class, "PojoAttributeDirectiveCreateCol", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPojoAttributeDirectiveCreateCol_CreateColumn1(), this.getPojoAttribute(), null, "createColumn1", null, 0, 1, PojoAttributeDirectiveCreateCol.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPojoAttributeDirectiveCreateCol_CreateColumn2(), ecorePackage.getEString(), "createColumn2", null, 0, 1, PojoAttributeDirectiveCreateCol.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pojoAttributeDirectiveToInitEClass, PojoAttributeDirectiveToInit.class, "PojoAttributeDirectiveToInit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(pojoAttributeDirectiveEnumInitEClass, PojoAttributeDirectiveEnumInit.class, "PojoAttributeDirectiveEnumInit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(pojoAttributeDirectiveIsDefEClass, PojoAttributeDirectiveIsDef.class, "PojoAttributeDirectiveIsDef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(pojoAttributeDirectiveEnumDefEClass, PojoAttributeDirectiveEnumDef.class, "PojoAttributeDirectiveEnumDef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(enumDirectiveSerializableEClass, EnumDirectiveSerializable.class, "EnumDirectiveSerializable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEnumDirectiveSerializable_Sernum(), ecorePackage.getEInt(), "sernum", null, 0, 1, EnumDirectiveSerializable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(enumAttributeDirectiveValuesEClass, EnumAttributeDirectiveValues.class, "EnumAttributeDirectiveValues", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEnumAttributeDirectiveValues_Values(), this.getEnumAttributeValue(), null, "values", null, 0, -1, EnumAttributeDirectiveValues.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(functionCallQueryEClass, FunctionCallQuery.class, "FunctionCallQuery", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(procedureCallQueryEClass, ProcedureCallQuery.class, "ProcedureCallQuery", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(functionCallEClass, FunctionCall.class, "FunctionCall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(procedureUpdateEClass, ProcedureUpdate.class, "ProcedureUpdate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(functionQueryEClass, FunctionQuery.class, "FunctionQuery", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(daoDirectiveSerializableEClass, DaoDirectiveSerializable.class, "DaoDirectiveSerializable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDaoDirectiveSerializable_Sernum(), ecorePackage.getEInt(), "sernum", null, 0, 1, DaoDirectiveSerializable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(daoDirectiveDiscriminatorEClass, DaoDirectiveDiscriminator.class, "DaoDirectiveDiscriminator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDaoDirectiveDiscriminator_Ancestor(), this.getPojoAttribute(), null, "ancestor", null, 0, 1, DaoDirectiveDiscriminator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDaoDirectiveDiscriminator_Descendants(), this.getDescendantAssignment(), null, "descendants", null, 0, -1, DaoDirectiveDiscriminator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(daoDirectiveCrudEClass, DaoDirectiveCrud.class, "DaoDirectiveCrud", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDaoDirectiveCrud_Pojo(), this.getPojoEntity(), null, "pojo", null, 0, 1, DaoDirectiveCrud.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(daoDirectiveQueryEClass, DaoDirectiveQuery.class, "DaoDirectiveQuery", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDaoDirectiveQuery_Pojo(), this.getPojoEntity(), null, "pojo", null, 0, 1, DaoDirectiveQuery.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(daoFunProcDirectiveEClass, DaoFunProcDirective.class, "DaoFunProcDirective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDaoFunProcDirective_Type(), this.getFunProcType(), null, "type", null, 0, 1, DaoFunProcDirective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDaoFunProcDirective_Paramlist(), this.getDaoDirectiveParameters(), null, "paramlist", null, 0, 1, DaoFunProcDirective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(daoDirectivePojoEClass, DaoDirectivePojo.class, "DaoDirectivePojo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDaoDirectivePojo_Pojo(), this.getPojoEntity(), null, "pojo", null, 0, 1, DaoDirectivePojo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(annotationDirectiveConflictEClass, AnnotationDirectiveConflict.class, "AnnotationDirectiveConflict", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(annotationDirectiveStaticEClass, AnnotationDirectiveStatic.class, "AnnotationDirectiveStatic", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(annotationDirectiveConstructorEClass, AnnotationDirectiveConstructor.class, "AnnotationDirectiveConstructor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(annotationDirectiveStandardEClass, AnnotationDirectiveStandard.class, "AnnotationDirectiveStandard", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(annotationDirectiveSetterEClass, AnnotationDirectiveSetter.class, "AnnotationDirectiveSetter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(annotationDirectiveGetterEClass, AnnotationDirectiveGetter.class, "AnnotationDirectiveGetter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(annotationDirectiveAttributeEClass, AnnotationDirectiveAttribute.class, "AnnotationDirectiveAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} //ProcessorModelPackageImpl
