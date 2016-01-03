/**
 */
package org.sqlproc.dsl.processorDsl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Descendant Assignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.DescendantAssignment#getValue <em>Value</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.DescendantAssignment#getDescendant <em>Descendant</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getDescendantAssignment()
 * @model
 * @generated
 */
public interface DescendantAssignment extends EObject
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(String)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getDescendantAssignment_Value()
   * @model
   * @generated
   */
  String getValue();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.DescendantAssignment#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(String value);

  /**
   * Returns the value of the '<em><b>Descendant</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Descendant</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Descendant</em>' containment reference.
   * @see #setDescendant(PojoType)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getDescendantAssignment_Descendant()
   * @model containment="true"
   * @generated
   */
  PojoType getDescendant();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.DescendantAssignment#getDescendant <em>Descendant</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Descendant</em>' containment reference.
   * @see #getDescendant()
   * @generated
   */
  void setDescendant(PojoType value);

} // DescendantAssignment
