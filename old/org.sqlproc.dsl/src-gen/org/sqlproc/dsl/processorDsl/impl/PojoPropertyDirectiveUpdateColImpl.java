/**
 */
package org.sqlproc.dsl.processorDsl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveUpdateCol;
import org.sqlproc.dsl.processorDsl.ProcessorDslPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pojo Property Directive Update Col</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveUpdateColImpl#getUpdateColumn1 <em>Update Column1</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveUpdateColImpl#getUpdateColumn2 <em>Update Column2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PojoPropertyDirectiveUpdateColImpl extends PojoPropertyDirectiveImpl implements PojoPropertyDirectiveUpdateCol
{
  /**
   * The default value of the '{@link #getUpdateColumn1() <em>Update Column1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUpdateColumn1()
   * @generated
   * @ordered
   */
  protected static final String UPDATE_COLUMN1_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getUpdateColumn1() <em>Update Column1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUpdateColumn1()
   * @generated
   * @ordered
   */
  protected String updateColumn1 = UPDATE_COLUMN1_EDEFAULT;

  /**
   * The default value of the '{@link #getUpdateColumn2() <em>Update Column2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUpdateColumn2()
   * @generated
   * @ordered
   */
  protected static final String UPDATE_COLUMN2_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getUpdateColumn2() <em>Update Column2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUpdateColumn2()
   * @generated
   * @ordered
   */
  protected String updateColumn2 = UPDATE_COLUMN2_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PojoPropertyDirectiveUpdateColImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ProcessorDslPackage.Literals.POJO_PROPERTY_DIRECTIVE_UPDATE_COL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getUpdateColumn1()
  {
    return updateColumn1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUpdateColumn1(String newUpdateColumn1)
  {
    String oldUpdateColumn1 = updateColumn1;
    updateColumn1 = newUpdateColumn1;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN1, oldUpdateColumn1, updateColumn1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getUpdateColumn2()
  {
    return updateColumn2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUpdateColumn2(String newUpdateColumn2)
  {
    String oldUpdateColumn2 = updateColumn2;
    updateColumn2 = newUpdateColumn2;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN2, oldUpdateColumn2, updateColumn2));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN1:
        return getUpdateColumn1();
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN2:
        return getUpdateColumn2();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN1:
        setUpdateColumn1((String)newValue);
        return;
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN2:
        setUpdateColumn2((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN1:
        setUpdateColumn1(UPDATE_COLUMN1_EDEFAULT);
        return;
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN2:
        setUpdateColumn2(UPDATE_COLUMN2_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN1:
        return UPDATE_COLUMN1_EDEFAULT == null ? updateColumn1 != null : !UPDATE_COLUMN1_EDEFAULT.equals(updateColumn1);
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_UPDATE_COL__UPDATE_COLUMN2:
        return UPDATE_COLUMN2_EDEFAULT == null ? updateColumn2 != null : !UPDATE_COLUMN2_EDEFAULT.equals(updateColumn2);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (updateColumn1: ");
    result.append(updateColumn1);
    result.append(", updateColumn2: ");
    result.append(updateColumn2);
    result.append(')');
    return result.toString();
  }

} //PojoPropertyDirectiveUpdateColImpl
