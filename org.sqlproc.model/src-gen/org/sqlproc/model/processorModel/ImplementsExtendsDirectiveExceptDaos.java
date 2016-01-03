/**
 */
package org.sqlproc.model.processorModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Implements Extends Directive Except Daos</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.model.processorModel.ImplementsExtendsDirectiveExceptDaos#getExceptDaos <em>Except Daos</em>}</li>
 * </ul>
 *
 * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getImplementsExtendsDirectiveExceptDaos()
 * @model
 * @generated
 */
public interface ImplementsExtendsDirectiveExceptDaos extends ImplementsExtendsDirective
{
  /**
   * Returns the value of the '<em><b>Except Daos</b></em>' reference list.
   * The list contents are of type {@link org.sqlproc.model.processorModel.DaoEntity}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Except Daos</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Except Daos</em>' reference list.
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getImplementsExtendsDirectiveExceptDaos_ExceptDaos()
   * @model
   * @generated
   */
  EList<DaoEntity> getExceptDaos();

} // ImplementsExtendsDirectiveExceptDaos
