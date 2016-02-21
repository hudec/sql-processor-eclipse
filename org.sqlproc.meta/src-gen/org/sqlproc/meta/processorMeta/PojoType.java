/**
 */
package org.sqlproc.meta.processorMeta;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.common.types.JvmType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pojo Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.meta.processorMeta.PojoType#getIdent <em>Ident</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.PojoType#getRef <em>Ref</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.PojoType#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getPojoType()
 * @model
 * @generated
 */
public interface PojoType extends EObject
{
  /**
   * Returns the value of the '<em><b>Ident</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ident</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ident</em>' containment reference.
   * @see #setIdent(ValueType)
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getPojoType_Ident()
   * @model containment="true"
   * @generated
   */
  ValueType getIdent();

  /**
   * Sets the value of the '{@link org.sqlproc.meta.processorMeta.PojoType#getIdent <em>Ident</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ident</em>' containment reference.
   * @see #getIdent()
   * @generated
   */
  void setIdent(ValueType value);

  /**
   * Returns the value of the '<em><b>Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ref</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ref</em>' reference.
   * @see #setRef(PojoDefinitionModel)
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getPojoType_Ref()
   * @model
   * @generated
   */
  PojoDefinitionModel getRef();

  /**
   * Sets the value of the '{@link org.sqlproc.meta.processorMeta.PojoType#getRef <em>Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ref</em>' reference.
   * @see #getRef()
   * @generated
   */
  void setRef(PojoDefinitionModel value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(JvmType)
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getPojoType_Type()
   * @model
   * @generated
   */
  JvmType getType();

  /**
   * Sets the value of the '{@link org.sqlproc.meta.processorMeta.PojoType#getType <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */
  void setType(JvmType value);

} // PojoType
