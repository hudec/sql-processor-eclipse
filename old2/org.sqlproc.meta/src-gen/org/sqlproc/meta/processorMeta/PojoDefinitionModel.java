/**
 */
package org.sqlproc.meta.processorMeta;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.common.types.JvmType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pojo Definition Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.meta.processorMeta.PojoDefinitionModel#getName <em>Name</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.PojoDefinitionModel#getClass_ <em>Class</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.PojoDefinitionModel#getClassx <em>Classx</em>}</li>
 * </ul>
 *
 * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getPojoDefinitionModel()
 * @model
 * @generated
 */
public interface PojoDefinitionModel extends EObject
{
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
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getPojoDefinitionModel_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.sqlproc.meta.processorMeta.PojoDefinitionModel#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Class</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Class</em>' attribute.
   * @see #setClass(String)
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getPojoDefinitionModel_Class()
   * @model
   * @generated
   */
  String getClass_();

  /**
   * Sets the value of the '{@link org.sqlproc.meta.processorMeta.PojoDefinitionModel#getClass_ <em>Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Class</em>' attribute.
   * @see #getClass_()
   * @generated
   */
  void setClass(String value);

  /**
   * Returns the value of the '<em><b>Classx</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Classx</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Classx</em>' reference.
   * @see #setClassx(JvmType)
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getPojoDefinitionModel_Classx()
   * @model
   * @generated
   */
  JvmType getClassx();

  /**
   * Sets the value of the '{@link org.sqlproc.meta.processorMeta.PojoDefinitionModel#getClassx <em>Classx</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Classx</em>' reference.
   * @see #getClassx()
   * @generated
   */
  void setClassx(JvmType value);

} // PojoDefinitionModel
