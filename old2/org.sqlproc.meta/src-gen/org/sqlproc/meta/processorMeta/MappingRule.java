/**
 */
package org.sqlproc.meta.processorMeta;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mapping Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.meta.processorMeta.MappingRule#getName <em>Name</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.MappingRule#getType <em>Type</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.MappingRule#getModifiers <em>Modifiers</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.MappingRule#getMapping <em>Mapping</em>}</li>
 * </ul>
 *
 * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getMappingRule()
 * @model
 * @generated
 */
public interface MappingRule extends EObject
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
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getMappingRule_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.sqlproc.meta.processorMeta.MappingRule#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' attribute.
   * @see #setType(String)
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getMappingRule_Type()
   * @model
   * @generated
   */
  String getType();

  /**
   * Sets the value of the '{@link org.sqlproc.meta.processorMeta.MappingRule#getType <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' attribute.
   * @see #getType()
   * @generated
   */
  void setType(String value);

  /**
   * Returns the value of the '<em><b>Modifiers</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifiers</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifiers</em>' attribute list.
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getMappingRule_Modifiers()
   * @model unique="false"
   * @generated
   */
  EList<String> getModifiers();

  /**
   * Returns the value of the '<em><b>Mapping</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mapping</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mapping</em>' containment reference.
   * @see #setMapping(Mapping)
   * @see org.sqlproc.meta.processorMeta.ProcessorMetaPackage#getMappingRule_Mapping()
   * @model containment="true"
   * @generated
   */
  Mapping getMapping();

  /**
   * Sets the value of the '{@link org.sqlproc.meta.processorMeta.MappingRule#getMapping <em>Mapping</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mapping</em>' containment reference.
   * @see #getMapping()
   * @generated
   */
  void setMapping(Mapping value);

} // MappingRule
