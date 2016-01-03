/**
 */
package org.sqlproc.dsl.processorDsl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dao Directive Discriminator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.DaoDirectiveDiscriminator#getAncestor <em>Ancestor</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.DaoDirectiveDiscriminator#getDescendants <em>Descendants</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getDaoDirectiveDiscriminator()
 * @model
 * @generated
 */
public interface DaoDirectiveDiscriminator extends DaoDirective
{
  /**
   * Returns the value of the '<em><b>Ancestor</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ancestor</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ancestor</em>' reference.
   * @see #setAncestor(PojoProperty)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getDaoDirectiveDiscriminator_Ancestor()
   * @model
   * @generated
   */
  PojoProperty getAncestor();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.DaoDirectiveDiscriminator#getAncestor <em>Ancestor</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ancestor</em>' reference.
   * @see #getAncestor()
   * @generated
   */
  void setAncestor(PojoProperty value);

  /**
   * Returns the value of the '<em><b>Descendants</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.dsl.processorDsl.DescendantAssignment}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Descendants</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Descendants</em>' containment reference list.
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getDaoDirectiveDiscriminator_Descendants()
   * @model containment="true"
   * @generated
   */
  EList<DescendantAssignment> getDescendants();

} // DaoDirectiveDiscriminator
