/**
 */
package org.sqlproc.meta.processorMeta;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Implements Assignement Generics</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.meta.processorMeta.ImplementsAssignementGenerics#getToImplement <em>To Implement</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.ImplementsAssignementGenerics#getDbTables <em>Db Tables</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.ImplementsAssignementGenerics#getDbNotTables <em>Db Not Tables</em>}</li>
 * </ul>
 *
 * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getImplementsAssignementGenerics()
 * @model
 * @generated
 */
public interface ImplementsAssignementGenerics extends EObject
{
  /**
   * Returns the value of the '<em><b>To Implement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>To Implement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>To Implement</em>' containment reference.
   * @see #setToImplement(PojoType)
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getImplementsAssignementGenerics_ToImplement()
   * @model containment="true"
   * @generated
   */
  PojoType getToImplement();

  /**
   * Sets the value of the '{@link org.sqlproc.meta.processorMeta.ImplementsAssignementGenerics#getToImplement <em>To Implement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>To Implement</em>' containment reference.
   * @see #getToImplement()
   * @generated
   */
  void setToImplement(PojoType value);

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
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getImplementsAssignementGenerics_DbTables()
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
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getImplementsAssignementGenerics_DbNotTables()
   * @model unique="false"
   * @generated
   */
  EList<String> getDbNotTables();

} // ImplementsAssignementGenerics
