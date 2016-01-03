/**
 */
package org.sqlproc.dsl.processorDsl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Procedure Pojo Assignement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.ProcedurePojoAssignement#getDbProcedure <em>Db Procedure</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.ProcedurePojoAssignement#getPojo <em>Pojo</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getProcedurePojoAssignement()
 * @model
 * @generated
 */
public interface ProcedurePojoAssignement extends EObject
{
  /**
   * Returns the value of the '<em><b>Db Procedure</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Db Procedure</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Db Procedure</em>' attribute.
   * @see #setDbProcedure(String)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getProcedurePojoAssignement_DbProcedure()
   * @model
   * @generated
   */
  String getDbProcedure();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.ProcedurePojoAssignement#getDbProcedure <em>Db Procedure</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Db Procedure</em>' attribute.
   * @see #getDbProcedure()
   * @generated
   */
  void setDbProcedure(String value);

  /**
   * Returns the value of the '<em><b>Pojo</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pojo</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pojo</em>' containment reference.
   * @see #setPojo(PojoType)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getProcedurePojoAssignement_Pojo()
   * @model containment="true"
   * @generated
   */
  PojoType getPojo();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.ProcedurePojoAssignement#getPojo <em>Pojo</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pojo</em>' containment reference.
   * @see #getPojo()
   * @generated
   */
  void setPojo(PojoType value);

} // ProcedurePojoAssignement
