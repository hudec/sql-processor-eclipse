/**
 */
package org.sqlproc.meta.processorMeta;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extended Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.meta.processorMeta.ExtendedColumn#getCol <em>Col</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.ExtendedColumn#getModifiers <em>Modifiers</em>}</li>
 * </ul>
 *
 * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getExtendedColumn()
 * @model
 * @generated
 */
public interface ExtendedColumn extends EObject
{
  /**
   * Returns the value of the '<em><b>Col</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Col</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Col</em>' containment reference.
   * @see #setCol(ExtendedColumnName)
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getExtendedColumn_Col()
   * @model containment="true"
   * @generated
   */
  ExtendedColumnName getCol();

  /**
   * Sets the value of the '{@link org.sqlproc.meta.processorMeta.ExtendedColumn#getCol <em>Col</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Col</em>' containment reference.
   * @see #getCol()
   * @generated
   */
  void setCol(ExtendedColumnName value);

  /**
   * Returns the value of the '<em><b>Modifiers</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiers</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiers</em>' attribute list.
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getExtendedColumn_Modifiers()
   * @model unique="false"
   * @generated
   */
  EList<String> getModifiers();

} // ExtendedColumn
