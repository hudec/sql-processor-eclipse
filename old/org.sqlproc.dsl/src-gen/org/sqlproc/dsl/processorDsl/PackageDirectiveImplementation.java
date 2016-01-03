/**
 */
package org.sqlproc.dsl.processorDsl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Directive Implementation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.PackageDirectiveImplementation#getImplementation <em>Implementation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPackageDirectiveImplementation()
 * @model
 * @generated
 */
public interface PackageDirectiveImplementation extends PackageDirective
{
  /**
   * Returns the value of the '<em><b>Implementation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Implementation</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Implementation</em>' attribute.
   * @see #setImplementation(String)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPackageDirectiveImplementation_Implementation()
   * @model
   * @generated
   */
  String getImplementation();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.PackageDirectiveImplementation#getImplementation <em>Implementation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Implementation</em>' attribute.
   * @see #getImplementation()
   * @generated
   */
  void setImplementation(String value);

} // PackageDirectiveImplementation
