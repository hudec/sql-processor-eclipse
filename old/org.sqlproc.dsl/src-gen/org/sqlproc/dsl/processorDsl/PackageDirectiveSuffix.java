/**
 */
package org.sqlproc.dsl.processorDsl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Directive Suffix</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.PackageDirectiveSuffix#getSuffix <em>Suffix</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPackageDirectiveSuffix()
 * @model
 * @generated
 */
public interface PackageDirectiveSuffix extends PackageDirective
{
  /**
   * Returns the value of the '<em><b>Suffix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Suffix</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Suffix</em>' attribute.
   * @see #setSuffix(String)
   * @see org.sqlproc.dsl.processorDsl.ProcessorDslPackage#getPackageDirectiveSuffix_Suffix()
   * @model
   * @generated
   */
  String getSuffix();

  /**
   * Sets the value of the '{@link org.sqlproc.dsl.processorDsl.PackageDirectiveSuffix#getSuffix <em>Suffix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suffix</em>' attribute.
   * @see #getSuffix()
   * @generated
   */
  void setSuffix(String value);

} // PackageDirectiveSuffix
