/**
 */
package org.sqlproc.model.processorModel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pojo Attribute Directive Index</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sqlproc.model.processorModel.PojoAttributeDirectiveIndex#getIndex <em>Index</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoAttributeDirectiveIndex()
 * @model
 * @generated
 */
public interface PojoAttributeDirectiveIndex extends PojoAttributeDirective
{
  /**
   * Returns the value of the '<em><b>Index</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Index</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Index</em>' containment reference.
   * @see #setIndex(IndexType)
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoAttributeDirectiveIndex_Index()
   * @model containment="true"
   * @generated
   */
  IndexType getIndex();

  /**
   * Sets the value of the '{@link org.sqlproc.model.processorModel.PojoAttributeDirectiveIndex#getIndex <em>Index</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Index</em>' containment reference.
   * @see #getIndex()
   * @generated
   */
  void setIndex(IndexType value);

} // PojoAttributeDirectiveIndex
