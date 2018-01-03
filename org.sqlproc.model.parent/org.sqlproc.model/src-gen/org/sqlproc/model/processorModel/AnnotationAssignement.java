/**
 * generated by Xtext 2.12.0
 */
package org.sqlproc.model.processorModel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Annotation Assignement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.model.processorModel.AnnotationAssignement#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.AnnotationAssignement#getDbTables <em>Db Tables</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.AnnotationAssignement#getDbNotTables <em>Db Not Tables</em>}</li>
 * </ul>
 *
 * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getAnnotationAssignement()
 * @model
 * @generated
 */
public interface AnnotationAssignement extends EObject
{
  /**
   * Returns the value of the '<em><b>Annotations</b></em>' reference list.
   * The list contents are of type {@link org.sqlproc.model.processorModel.AnnotationDefinitionModel}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotations</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotations</em>' reference list.
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getAnnotationAssignement_Annotations()
   * @model
   * @generated
   */
  EList<AnnotationDefinitionModel> getAnnotations();

  /**
   * Returns the value of the '<em><b>Db Tables</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Db Tables</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Db Tables</em>' attribute list.
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getAnnotationAssignement_DbTables()
   * @model unique="false"
   * @generated
   */
  EList<String> getDbTables();

  /**
   * Returns the value of the '<em><b>Db Not Tables</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Db Not Tables</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Db Not Tables</em>' attribute list.
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getAnnotationAssignement_DbNotTables()
   * @model unique="false"
   * @generated
   */
  EList<String> getDbNotTables();

} // AnnotationAssignement
