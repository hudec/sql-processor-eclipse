/**
 */
package org.sqlproc.model.processorModel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifacts</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.model.processorModel.Artifacts#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.Artifacts#getPojos <em>Pojos</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.Artifacts#getTables <em>Tables</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.Artifacts#getProcedures <em>Procedures</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.Artifacts#getFunctions <em>Functions</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.Artifacts#getPackages <em>Packages</em>}</li>
 * </ul>
 *
 * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getArtifacts()
 * @model
 * @generated
 */
public interface Artifacts extends EObject
{
  /**
   * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.model.processorModel.Property}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Properties</em>' containment reference list.
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getArtifacts_Properties()
   * @model containment="true"
   * @generated
   */
  EList<Property> getProperties();

  /**
   * Returns the value of the '<em><b>Pojos</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.model.processorModel.PojoDefinitionModel}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pojos</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pojos</em>' containment reference list.
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getArtifacts_Pojos()
   * @model containment="true"
   * @generated
   */
  EList<PojoDefinitionModel> getPojos();

  /**
   * Returns the value of the '<em><b>Tables</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.model.processorModel.TableDefinitionModel}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tables</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tables</em>' containment reference list.
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getArtifacts_Tables()
   * @model containment="true"
   * @generated
   */
  EList<TableDefinitionModel> getTables();

  /**
   * Returns the value of the '<em><b>Procedures</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.model.processorModel.ProcedureDefinitionModel}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Procedures</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Procedures</em>' containment reference list.
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getArtifacts_Procedures()
   * @model containment="true"
   * @generated
   */
  EList<ProcedureDefinitionModel> getProcedures();

  /**
   * Returns the value of the '<em><b>Functions</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.model.processorModel.FunctionDefinitionModel}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Functions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Functions</em>' containment reference list.
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getArtifacts_Functions()
   * @model containment="true"
   * @generated
   */
  EList<FunctionDefinitionModel> getFunctions();

  /**
   * Returns the value of the '<em><b>Packages</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.model.processorModel.Package}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Packages</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Packages</em>' containment reference list.
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getArtifacts_Packages()
   * @model containment="true"
   * @generated
   */
  EList<org.sqlproc.model.processorModel.Package> getPackages();

} // Artifacts
