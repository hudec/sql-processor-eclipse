/**
 */
package org.sqlproc.meta.processorMeta;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extends Assignement Generics</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.meta.processorMeta.ExtendsAssignementGenerics#getToExtends <em>To Extends</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.ExtendsAssignementGenerics#getDbTables <em>Db Tables</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.ExtendsAssignementGenerics#getDbNotTables <em>Db Not Tables</em>}</li>
 * </ul>
 *
 * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getExtendsAssignementGenerics()
 * @model
 * @generated
 */
public interface ExtendsAssignementGenerics extends EObject
{
  /**
   * Returns the value of the '<em><b>To Extends</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>To Extends</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>To Extends</em>' containment reference.
   * @see #setToExtends(PojoType)
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getExtendsAssignementGenerics_ToExtends()
   * @model containment="true"
   * @generated
   */
  PojoType getToExtends();

  /**
   * Sets the value of the '{@link org.sqlproc.meta.processorMeta.ExtendsAssignementGenerics#getToExtends <em>To Extends</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>To Extends</em>' containment reference.
   * @see #getToExtends()
   * @generated
   */
  void setToExtends(PojoType value);

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
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getExtendsAssignementGenerics_DbTables()
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
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getExtendsAssignementGenerics_DbNotTables()
   * @model unique="false"
   * @generated
   */
  EList<String> getDbNotTables();

} // ExtendsAssignementGenerics
