/**
 */
package org.sqlproc.dsl.processorDsl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fun Proc Directive</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.FunProcDirective#getType <em>Type</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.FunProcDirective#getParamlist <em>Paramlist</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getFunProcDirective()
 * @model
 * @generated
 */
public interface FunProcDirective extends DaoDirective
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(FunProcType)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getFunProcDirective_Type()
   * @model containment="true"
   * @generated
   */
  FunProcType getType();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.FunProcDirective#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(FunProcType value);

  /**
   * Returns the value of the '<em><b>Paramlist</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Paramlist</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Paramlist</em>' containment reference.
   * @see #setParamlist(DaoDirectiveParameters)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getFunProcDirective_Paramlist()
   * @model containment="true"
   * @generated
   */
  DaoDirectiveParameters getParamlist();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.FunProcDirective#getParamlist <em>Paramlist</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Paramlist</em>' containment reference.
   * @see #getParamlist()
   * @generated
   */
  void setParamlist(DaoDirectiveParameters value);

} // FunProcDirective
