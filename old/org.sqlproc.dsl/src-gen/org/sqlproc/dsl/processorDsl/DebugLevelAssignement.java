/**
 */
package org.sqlproc.dsl.processorDsl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Debug Level Assignement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.DebugLevelAssignement#getDebug <em>Debug</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.DebugLevelAssignement#getScope <em>Scope</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getDebugLevelAssignement()
 * @model
 * @generated
 */
public interface DebugLevelAssignement extends EObject
{
  /**
   * Returns the value of the '<em><b>Debug</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Debug</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Debug</em>' attribute.
   * @see #setDebug(String)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getDebugLevelAssignement_Debug()
   * @model
   * @generated
   */
  String getDebug();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.DebugLevelAssignement#getDebug <em>Debug</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Debug</em>' attribute.
   * @see #getDebug()
   * @generated
   */
  void setDebug(String value);

  /**
   * Returns the value of the '<em><b>Scope</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scope</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scope</em>' attribute.
   * @see #setScope(String)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getDebugLevelAssignement_Scope()
   * @model
   * @generated
   */
  String getScope();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.DebugLevelAssignement#getScope <em>Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Scope</em>' attribute.
   * @see #getScope()
   * @generated
   */
  void setScope(String value);

} // DebugLevelAssignement
