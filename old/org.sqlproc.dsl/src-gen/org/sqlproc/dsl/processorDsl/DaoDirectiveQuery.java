/**
 */
package org.sqlproc.dsl.processorDsl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dao Directive Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.DaoDirectiveQuery#getPojo <em>Pojo</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getDaoDirectiveQuery()
 * @model
 * @generated
 */
public interface DaoDirectiveQuery extends DaoDirective
{
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
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getDaoDirectiveQuery_Pojo()
   * @model containment="true"
   * @generated
   */
  PojoType getPojo();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.DaoDirectiveQuery#getPojo <em>Pojo</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pojo</em>' containment reference.
   * @see #getPojo()
   * @generated
   */
  void setPojo(PojoType value);

} // DaoDirectiveQuery
