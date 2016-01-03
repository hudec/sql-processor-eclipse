/**
 */
package org.sqlproc.dsl.processorDsl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.common.types.JvmType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extends Assignement Generics</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.ExtendsAssignementGenerics#getToExtends <em>To Extends</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.ExtendsAssignementGenerics#getDbTables <em>Db Tables</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.ExtendsAssignementGenerics#getDbNotTables <em>Db Not Tables</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getExtendsAssignementGenerics()
 * @model
 * @generated
 */
public interface ExtendsAssignementGenerics extends EObject
{
  /**
   * Returns the value of the '<em><b>To Extends</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>To Extends</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>To Extends</em>' reference.
   * @see #setToExtends(JvmType)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getExtendsAssignementGenerics_ToExtends()
   * @model
   * @generated
   */
  JvmType getToExtends();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.ExtendsAssignementGenerics#getToExtends <em>To Extends</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>To Extends</em>' reference.
   * @see #getToExtends()
   * @generated
   */
  void setToExtends(JvmType value);

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
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getExtendsAssignementGenerics_DbTables()
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
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getExtendsAssignementGenerics_DbNotTables()
   * @model unique="false"
   * @generated
   */
  EList<String> getDbNotTables();

} // ExtendsAssignementGenerics
