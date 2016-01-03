/**
 */
package org.sqlproc.dsl.processorDsl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dao Directive Parameters</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.DaoDirectiveParameters#getOut <em>Out</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.DaoDirectiveParameters#getIns <em>Ins</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getDaoDirectiveParameters()
 * @model
 * @generated
 */
public interface DaoDirectiveParameters extends EObject
{
  /**
   * Returns the value of the '<em><b>Out</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Out</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Out</em>' containment reference.
   * @see #setOut(PojoType)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getDaoDirectiveParameters_Out()
   * @model containment="true"
   * @generated
   */
  PojoType getOut();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.DaoDirectiveParameters#getOut <em>Out</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Out</em>' containment reference.
   * @see #getOut()
   * @generated
   */
  void setOut(PojoType value);

  /**
   * Returns the value of the '<em><b>Ins</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.dsl.processorDsl.PojoType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ins</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ins</em>' containment reference list.
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getDaoDirectiveParameters_Ins()
   * @model containment="true"
   * @generated
   */
  EList<PojoType> getIns();

} // DaoDirectiveParameters
