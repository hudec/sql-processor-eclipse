/**
 */
package org.sqlproc.dsl.processorDsl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pojo Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.PojoEntity#getModifiers1 <em>Modifiers1</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.PojoEntity#getModifiers2 <em>Modifiers2</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.PojoEntity#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPojoEntity()
 * @model
 * @generated
 */
public interface PojoEntity extends Entity
{
  /**
   * Returns the value of the '<em><b>Modifiers1</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.dsl.processorDsl.PojoEntityModifier1}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiers1</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiers1</em>' containment reference list.
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPojoEntity_Modifiers1()
   * @model containment="true"
   * @generated
   */
  EList<PojoEntityModifier1> getModifiers1();

  /**
   * Returns the value of the '<em><b>Modifiers2</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.dsl.processorDsl.PojoEntityModifier2}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiers2</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiers2</em>' containment reference list.
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPojoEntity_Modifiers2()
   * @model containment="true"
   * @generated
   */
  EList<PojoEntityModifier2> getModifiers2();

  /**
   * Returns the value of the '<em><b>Features</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.dsl.processorDsl.PojoAnnotatedProperty}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Features</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Features</em>' containment reference list.
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPojoEntity_Features()
   * @model containment="true"
   * @generated
   */
  EList<PojoAnnotatedProperty> getFeatures();

} // PojoEntity
