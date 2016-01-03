/**
 */
package org.sqlproc.dsl.processorDsl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.common.types.JvmType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Implements Assignement Generics</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.ImplementsAssignementGenerics#getToImplement <em>To Implement</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.ImplementsAssignementGenerics#getDbTables <em>Db Tables</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.ImplementsAssignementGenerics#getDbNotTables <em>Db Not Tables</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getImplementsAssignementGenerics()
 * @model
 * @generated
 */
public interface ImplementsAssignementGenerics extends EObject
{
  /**
   * Returns the value of the '<em><b>To Implement</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>To Implement</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>To Implement</em>' reference.
   * @see #setToImplement(JvmType)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getImplementsAssignementGenerics_ToImplement()
   * @model
   * @generated
   */
  JvmType getToImplement();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.ImplementsAssignementGenerics#getToImplement <em>To Implement</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>To Implement</em>' reference.
   * @see #getToImplement()
   * @generated
   */
  void setToImplement(JvmType value);

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
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getImplementsAssignementGenerics_DbTables()
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
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getImplementsAssignementGenerics_DbNotTables()
   * @model unique="false"
   * @generated
   */
  EList<String> getDbNotTables();

} // ImplementsAssignementGenerics
