/**
 */
package org.sqlproc.dsl.processorDsl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.sqlproc.dsl.processorDsl.PojoPropertyDirectiveCreateCol;
import org.sqlproc.dsl.processorDsl.ProcessorDslPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pojo Property Directive Create Col</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveCreateColImpl#getCreateColumn1 <em>Create Column1</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.PojoPropertyDirectiveCreateColImpl#getCreateColumn2 <em>Create Column2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PojoPropertyDirectiveCreateColImpl extends PojoPropertyDirectiveImpl implements PojoPropertyDirectiveCreateCol
{
  /**
   * The default value of the '{@link #getCreateColumn1() <em>Create Column1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCreateColumn1()
   * @generated
   * @ordered
   */
  protected static final String CREATE_COLUMN1_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCreateColumn1() <em>Create Column1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCreateColumn1()
   * @generated
   * @ordered
   */
  protected String createColumn1 = CREATE_COLUMN1_EDEFAULT;

  /**
   * The default value of the '{@link #getCreateColumn2() <em>Create Column2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCreateColumn2()
   * @generated
   * @ordered
   */
  protected static final String CREATE_COLUMN2_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCreateColumn2() <em>Create Column2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCreateColumn2()
   * @generated
   * @ordered
   */
  protected String createColumn2 = CREATE_COLUMN2_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PojoPropertyDirectiveCreateColImpl()
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
    return ProcessorDslPackage.Literals.POJO_PROPERTY_DIRECTIVE_CREATE_COL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCreateColumn1()
  {
    return createColumn1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCreateColumn1(String newCreateColumn1)
  {
    String oldCreateColumn1 = createColumn1;
    createColumn1 = newCreateColumn1;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_CREATE_COL__CREATE_COLUMN1, oldCreateColumn1, createColumn1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCreateColumn2()
  {
    return createColumn2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCreateColumn2(String newCreateColumn2)
  {
    String oldCreateColumn2 = createColumn2;
    createColumn2 = newCreateColumn2;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_CREATE_COL__CREATE_COLUMN2, oldCreateColumn2, createColumn2));
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
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_CREATE_COL__CREATE_COLUMN1:
        return getCreateColumn1();
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_CREATE_COL__CREATE_COLUMN2:
        return getCreateColumn2();
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
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_CREATE_COL__CREATE_COLUMN1:
        setCreateColumn1((String)newValue);
        return;
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_CREATE_COL__CREATE_COLUMN2:
        setCreateColumn2((String)newValue);
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
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_CREATE_COL__CREATE_COLUMN1:
        setCreateColumn1(CREATE_COLUMN1_EDEFAULT);
        return;
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_CREATE_COL__CREATE_COLUMN2:
        setCreateColumn2(CREATE_COLUMN2_EDEFAULT);
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
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_CREATE_COL__CREATE_COLUMN1:
        return CREATE_COLUMN1_EDEFAULT == null ? createColumn1 != null : !CREATE_COLUMN1_EDEFAULT.equals(createColumn1);
      case ProcessorDslPackage.POJO_PROPERTY_DIRECTIVE_CREATE_COL__CREATE_COLUMN2:
        return CREATE_COLUMN2_EDEFAULT == null ? createColumn2 != null : !CREATE_COLUMN2_EDEFAULT.equals(createColumn2);
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
    result.append(" (createColumn1: ");
    result.append(createColumn1);
    result.append(", createColumn2: ");
    result.append(createColumn2);
    result.append(')');
    return result.toString();
  }

} //PojoPropertyDirectiveCreateColImpl
