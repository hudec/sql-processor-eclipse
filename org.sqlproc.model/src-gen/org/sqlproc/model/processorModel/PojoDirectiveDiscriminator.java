/**
 */
package org.sqlproc.model.processorModel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pojo Directive Discriminator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.model.processorModel.PojoDirectiveDiscriminator#getDiscriminator <em>Discriminator</em>}</li>
 * </ul>
 *
 * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoDirectiveDiscriminator()
 * @model
 * @generated
 */
public interface PojoDirectiveDiscriminator extends PojoDirective
{
  /**
   * Returns the value of the '<em><b>Discriminator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Discriminator</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Discriminator</em>' attribute.
   * @see #setDiscriminator(String)
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoDirectiveDiscriminator_Discriminator()
   * @model
   * @generated
   */
  String getDiscriminator();

  /**
   * Sets the value of the '{@link org.sqlproc.model.processorModel.PojoDirectiveDiscriminator#getDiscriminator <em>Discriminator</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Discriminator</em>' attribute.
   * @see #getDiscriminator()
   * @generated
   */
  void setDiscriminator(String value);

} // PojoDirectiveDiscriminator
