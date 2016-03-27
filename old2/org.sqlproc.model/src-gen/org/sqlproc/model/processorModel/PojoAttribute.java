/**
 */
package org.sqlproc.model.processorModel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pojo Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.model.processorModel.PojoAttribute#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.PojoAttribute#getDirectives <em>Directives</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.PojoAttribute#isFinal <em>Final</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.PojoAttribute#isStatic <em>Static</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.PojoAttribute#getKind <em>Kind</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.PojoAttribute#getType <em>Type</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.PojoAttribute#isArray <em>Array</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.PojoAttribute#getName <em>Name</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.PojoAttribute#getInitExpr <em>Init Expr</em>}</li>
 * </ul>
 *
 * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoAttribute()
 * @model
 * @generated
 */
public interface PojoAttribute extends EObject
{
  /**
   * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.model.processorModel.Annotation}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotations</em>' containment reference list.
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoAttribute_Annotations()
   * @model containment="true"
   * @generated
   */
  EList<Annotation> getAnnotations();

  /**
   * Returns the value of the '<em><b>Directives</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.model.processorModel.PojoAttributeDirective}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Directives</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Directives</em>' containment reference list.
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoAttribute_Directives()
   * @model containment="true"
   * @generated
   */
  EList<PojoAttributeDirective> getDirectives();

  /**
   * Returns the value of the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Final</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Final</em>' attribute.
   * @see #setFinal(boolean)
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoAttribute_Final()
   * @model
   * @generated
   */
  boolean isFinal();

  /**
   * Sets the value of the '{@link org.sqlproc.model.processorModel.PojoAttribute#isFinal <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Final</em>' attribute.
   * @see #isFinal()
   * @generated
   */
  void setFinal(boolean value);

  /**
   * Returns the value of the '<em><b>Static</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Static</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Static</em>' attribute.
   * @see #setStatic(boolean)
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoAttribute_Static()
   * @model
   * @generated
   */
  boolean isStatic();

  /**
   * Sets the value of the '{@link org.sqlproc.model.processorModel.PojoAttribute#isStatic <em>Static</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Static</em>' attribute.
   * @see #isStatic()
   * @generated
   */
  void setStatic(boolean value);

  /**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Kind</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see #setKind(String)
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoAttribute_Kind()
   * @model
   * @generated
   */
  String getKind();

  /**
   * Sets the value of the '{@link org.sqlproc.model.processorModel.PojoAttribute#getKind <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see #getKind()
   * @generated
   */
  void setKind(String value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(JvmParameterizedTypeReference)
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoAttribute_Type()
   * @model containment="true"
   * @generated
   */
  JvmParameterizedTypeReference getType();

  /**
   * Sets the value of the '{@link org.sqlproc.model.processorModel.PojoAttribute#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(JvmParameterizedTypeReference value);

  /**
   * Returns the value of the '<em><b>Array</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Array</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array</em>' attribute.
   * @see #setArray(boolean)
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoAttribute_Array()
   * @model
   * @generated
   */
  boolean isArray();

  /**
   * Sets the value of the '{@link org.sqlproc.model.processorModel.PojoAttribute#isArray <em>Array</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array</em>' attribute.
   * @see #isArray()
   * @generated
   */
  void setArray(boolean value);

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
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoAttribute_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.sqlproc.model.processorModel.PojoAttribute#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Init Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Init Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Init Expr</em>' containment reference.
   * @see #setInitExpr(XExpression)
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getPojoAttribute_InitExpr()
   * @model containment="true"
   * @generated
   */
  XExpression getInitExpr();

  /**
   * Sets the value of the '{@link org.sqlproc.model.processorModel.PojoAttribute#getInitExpr <em>Init Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Init Expr</em>' containment reference.
   * @see #getInitExpr()
   * @generated
   */
  void setInitExpr(XExpression value);

} // PojoAttribute
