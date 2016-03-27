/**
 */
package org.sqlproc.model.processorModel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pojo Directive Operators</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.model.processorModel.PojoDirectiveOperators#getOperatorsSuffix <em>Operators Suffix</em>}</li>
 * </ul>
 *
 * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoDirectiveOperators()
 * @model
 * @generated
 */
public interface PojoDirectiveOperators extends PojoDirective
{
  /**
   * Returns the value of the '<em><b>Operators Suffix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Operators Suffix</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Operators Suffix</em>' attribute.
   * @see #setOperatorsSuffix(String)
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoDirectiveOperators_OperatorsSuffix()
   * @model
   * @generated
   */
  String getOperatorsSuffix();

  /**
   * Sets the value of the '{@link org.sqlproc.model.processorModel.PojoDirectiveOperators#getOperatorsSuffix <em>Operators Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Operators Suffix</em>' attribute.
   * @see #getOperatorsSuffix()
   * @generated
   */
  void setOperatorsSuffix(String value);

} // PojoDirectiveOperators
