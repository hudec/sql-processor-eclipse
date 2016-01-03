/**
 */
package org.sqlproc.dsl.processorDsl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.sqlproc.dsl.processorDsl.DescendantAssignment;
import org.sqlproc.dsl.processorDsl.PojoType;
import org.sqlproc.dsl.processorDsl.ProcessorDslPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Descendant Assignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.DescendantAssignmentImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.DescendantAssignmentImpl#getDescendant <em>Descendant</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DescendantAssignmentImpl extends MinimalEObjectImpl.Container implements DescendantAssignment
{
  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final String VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected String value = VALUE_EDEFAULT;

  /**
   * The cached value of the '{@link #getDescendant() <em>Descendant</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescendant()
   * @generated
   * @ordered
   */
  protected PojoType descendant;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DescendantAssignmentImpl()
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
    return ProcessorDslPackage.Literals.DESCENDANT_ASSIGNMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(String newValue)
  {
    String oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DESCENDANT_ASSIGNMENT__VALUE, oldValue, value));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoType getDescendant()
  {
    return descendant;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDescendant(PojoType newDescendant, NotificationChain msgs)
  {
    PojoType oldDescendant = descendant;
    descendant = newDescendant;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DESCENDANT_ASSIGNMENT__DESCENDANT, oldDescendant, newDescendant);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDescendant(PojoType newDescendant)
  {
    if (newDescendant != descendant)
    {
      NotificationChain msgs = null;
      if (descendant != null)
        msgs = ((InternalEObject)descendant).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorDslPackage.DESCENDANT_ASSIGNMENT__DESCENDANT, null, msgs);
      if (newDescendant != null)
        msgs = ((InternalEObject)newDescendant).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorDslPackage.DESCENDANT_ASSIGNMENT__DESCENDANT, null, msgs);
      msgs = basicSetDescendant(newDescendant, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DESCENDANT_ASSIGNMENT__DESCENDANT, newDescendant, newDescendant));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ProcessorDslPackage.DESCENDANT_ASSIGNMENT__DESCENDANT:
        return basicSetDescendant(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case ProcessorDslPackage.DESCENDANT_ASSIGNMENT__VALUE:
        return getValue();
      case ProcessorDslPackage.DESCENDANT_ASSIGNMENT__DESCENDANT:
        return getDescendant();
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
      case ProcessorDslPackage.DESCENDANT_ASSIGNMENT__VALUE:
        setValue((String)newValue);
        return;
      case ProcessorDslPackage.DESCENDANT_ASSIGNMENT__DESCENDANT:
        setDescendant((PojoType)newValue);
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
      case ProcessorDslPackage.DESCENDANT_ASSIGNMENT__VALUE:
        setValue(VALUE_EDEFAULT);
        return;
      case ProcessorDslPackage.DESCENDANT_ASSIGNMENT__DESCENDANT:
        setDescendant((PojoType)null);
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
      case ProcessorDslPackage.DESCENDANT_ASSIGNMENT__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
      case ProcessorDslPackage.DESCENDANT_ASSIGNMENT__DESCENDANT:
        return descendant != null;
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
    result.append(" (value: ");
    result.append(value);
    result.append(')');
    return result.toString();
  }

} //DescendantAssignmentImpl
