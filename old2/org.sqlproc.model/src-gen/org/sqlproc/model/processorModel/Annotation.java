/**
 */
package org.sqlproc.model.processorModel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Annotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.model.processorModel.Annotation#getDirectives <em>Directives</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.Annotation#isFinal <em>Final</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.Annotation#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 *
 * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getAnnotation()
 * @model
 * @generated
 */
public interface Annotation extends EObject
{
  /**
   * Returns the value of the '<em><b>Directives</b></em>' containment reference list.
   * The list contents are of type {@link org.sqlproc.model.processorModel.AnnotationDirective}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Directives</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Directives</em>' containment reference list.
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getAnnotation_Directives()
   * @model containment="true"
   * @generated
   */
  EList<AnnotationDirective> getDirectives();

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
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getAnnotation_Final()
   * @model
   * @generated
   */
  boolean isFinal();

  /**
   * Sets the value of the '{@link org.sqlproc.model.processorModel.Annotation#isFinal <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Final</em>' attribute.
   * @see #isFinal()
   * @generated
   */
  void setFinal(boolean value);

  /**
   * Returns the value of the '<em><b>Annotation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotation</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation</em>' containment reference.
   * @see #setAnnotation(XAnnotation)
   * @see org.sqlproc.model.processorModel.ProcessorModelPackage#getAnnotation_Annotation()
   * @model containment="true"
   * @generated
   */
  XAnnotation getAnnotation();

  /**
   * Sets the value of the '{@link org.sqlproc.model.processorModel.Annotation#getAnnotation <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Annotation</em>' containment reference.
   * @see #getAnnotation()
   * @generated
   */
  void setAnnotation(XAnnotation value);

} // Annotation