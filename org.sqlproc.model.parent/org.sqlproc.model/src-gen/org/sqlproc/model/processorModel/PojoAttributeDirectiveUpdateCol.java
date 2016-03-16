/**
 */
package org.sqlproc.model.processorModel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pojo Attribute Directive Update Col</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.model.processorModel.PojoAttributeDirectiveUpdateCol#getUpdateColumn1 <em>Update Column1</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.PojoAttributeDirectiveUpdateCol#getUpdateColumn2 <em>Update Column2</em>}</li>
 * </ul>
 *
 * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoAttributeDirectiveUpdateCol()
 * @model
 * @generated
 */
public interface PojoAttributeDirectiveUpdateCol extends PojoAttributeDirective
{
  /**
   * Returns the value of the '<em><b>Update Column1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Update Column1</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Update Column1</em>' attribute.
   * @see #setUpdateColumn1(String)
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoAttributeDirectiveUpdateCol_UpdateColumn1()
   * @model
   * @generated
   */
  String getUpdateColumn1();

  /**
   * Sets the value of the '{@link org.sqlproc.model.processorModel.PojoAttributeDirectiveUpdateCol#getUpdateColumn1 <em>Update Column1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Update Column1</em>' attribute.
   * @see #getUpdateColumn1()
   * @generated
   */
  void setUpdateColumn1(String value);

  /**
   * Returns the value of the '<em><b>Update Column2</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Update Column2</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Update Column2</em>' reference.
   * @see #setUpdateColumn2(PojoAttribute)
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoAttributeDirectiveUpdateCol_UpdateColumn2()
   * @model
   * @generated
   */
  PojoAttribute getUpdateColumn2();

  /**
   * Sets the value of the '{@link org.sqlproc.model.processorModel.PojoAttributeDirectiveUpdateCol#getUpdateColumn2 <em>Update Column2</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Update Column2</em>' reference.
   * @see #getUpdateColumn2()
   * @generated
   */
  void setUpdateColumn2(PojoAttribute value);

} // PojoAttributeDirectiveUpdateCol
