/**
 */
package org.sqlproc.dsl.processorDsl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.xtext.common.types.JvmType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Implements</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.Implements#getDirectives <em>Directives</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.Implements#getImplements <em>Implements</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.Implements#getOnlyPojos <em>Only Pojos</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.Implements#getOnlyDaos <em>Only Daos</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.Implements#getExceptPojos <em>Except Pojos</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.Implements#getExceptDaos <em>Except Daos</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getImplements()
 * @model
 * @generated
 */
public interface Implements extends AbstractPojoEntity
{
  /**
   * Returns the value of the '<em><b>Directives</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.dsl.processorDsl.ImplementsExtendsDirective}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Directives</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Directives</em>' containment reference list.
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getImplements_Directives()
   * @model containment="true"
   * @generated
   */
  EList<ImplementsExtendsDirective> getDirectives();

  /**
   * Returns the value of the '<em><b>Implements</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Implements</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Implements</em>' reference.
   * @see #setImplements(JvmType)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getImplements_Implements()
   * @model
   * @generated
   */
  JvmType getImplements();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.Implements#getImplements <em>Implements</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Implements</em>' reference.
   * @see #getImplements()
   * @generated
   */
  void setImplements(JvmType value);

  /**
   * Returns the value of the '<em><b>Only Pojos</b></em>' reference list.
   * The list contents are of type {@link org.sqlproc.dsl.processorDsl.PojoEntity}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Only Pojos</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Only Pojos</em>' reference list.
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getImplements_OnlyPojos()
   * @model
   * @generated
   */
  EList<PojoEntity> getOnlyPojos();

  /**
   * Returns the value of the '<em><b>Only Daos</b></em>' reference list.
   * The list contents are of type {@link org.sqlproc.dsl.processorDsl.PojoDao}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Only Daos</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Only Daos</em>' reference list.
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getImplements_OnlyDaos()
   * @model
   * @generated
   */
  EList<PojoDao> getOnlyDaos();

  /**
   * Returns the value of the '<em><b>Except Pojos</b></em>' reference list.
   * The list contents are of type {@link org.sqlproc.dsl.processorDsl.PojoEntity}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Except Pojos</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Except Pojos</em>' reference list.
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getImplements_ExceptPojos()
   * @model
   * @generated
   */
  EList<PojoEntity> getExceptPojos();

  /**
   * Returns the value of the '<em><b>Except Daos</b></em>' reference list.
   * The list contents are of type {@link org.sqlproc.dsl.processorDsl.PojoDao}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Except Daos</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Except Daos</em>' reference list.
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getImplements_ExceptDaos()
   * @model
   * @generated
   */
  EList<PojoDao> getExceptDaos();

} // Implements
