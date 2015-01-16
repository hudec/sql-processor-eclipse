/**
 */
package org.sqlproc.dsl.processorDsl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pojo Dao</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.PojoDao#getDirectives <em>Directives</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.PojoDao#getModifiers1 <em>Modifiers1</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.PojoDao#getName <em>Name</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.PojoDao#isPojoGenerics <em>Pojo Generics</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.PojoDao#getPojo <em>Pojo</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.PojoDao#getModifiers2 <em>Modifiers2</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.PojoDao#getMethods <em>Methods</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPojoDao()
 * @model
 * @generated
 */
public interface PojoDao extends AbstractPojoEntity
{
  /**
   * Returns the value of the '<em><b>Directives</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.dsl.processorDsl.DaoDirective}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Directives</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Directives</em>' containment reference list.
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPojoDao_Directives()
   * @model containment="true"
   * @generated
   */
  EList<DaoDirective> getDirectives();

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
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPojoDao_Modifiers1()
   * @model containment="true"
   * @generated
   */
  EList<PojoEntityModifier1> getModifiers1();

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPojoDao_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.PojoDao#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Pojo Generics</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pojo Generics</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pojo Generics</em>' attribute.
   * @see #setPojoGenerics(boolean)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPojoDao_PojoGenerics()
   * @model
   * @generated
   */
  boolean isPojoGenerics();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.PojoDao#isPojoGenerics <em>Pojo Generics</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pojo Generics</em>' attribute.
   * @see #isPojoGenerics()
   * @generated
   */
  void setPojoGenerics(boolean value);

  /**
   * Returns the value of the '<em><b>Pojo</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pojo</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pojo</em>' reference.
   * @see #setPojo(PojoEntity)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPojoDao_Pojo()
   * @model
   * @generated
   */
  PojoEntity getPojo();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.PojoDao#getPojo <em>Pojo</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pojo</em>' reference.
   * @see #getPojo()
   * @generated
   */
  void setPojo(PojoEntity value);

  /**
   * Returns the value of the '<em><b>Modifiers2</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.dsl.processorDsl.PojoDaoModifier}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiers2</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiers2</em>' containment reference list.
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPojoDao_Modifiers2()
   * @model containment="true"
   * @generated
   */
  EList<PojoDaoModifier> getModifiers2();

  /**
   * Returns the value of the '<em><b>Methods</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.dsl.processorDsl.PojoMethod}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Methods</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Methods</em>' containment reference list.
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPojoDao_Methods()
   * @model containment="true"
   * @generated
   */
  EList<PojoMethod> getMethods();

} // PojoDao
