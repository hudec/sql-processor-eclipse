/**
 */
package org.sqlproc.dsl.processorDsl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.sqlproc.dsl.processorDsl.DaoDirectiveParameters;
import org.sqlproc.dsl.processorDsl.PojoType;
import org.sqlproc.dsl.processorDsl.ProcessorDslPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dao Directive Parameters</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.DaoDirectiveParametersImpl#getOut <em>Out</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.DaoDirectiveParametersImpl#getIns <em>Ins</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DaoDirectiveParametersImpl extends MinimalEObjectImpl.Container implements DaoDirectiveParameters
{
  /**
   * The cached value of the '{@link #getOut() <em>Out</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOut()
   * @generated
   * @ordered
   */
  protected PojoType out;

  /**
   * The cached value of the '{@link #getIns() <em>Ins</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIns()
   * @generated
   * @ordered
   */
  protected EList<PojoType> ins;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DaoDirectiveParametersImpl()
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
    return ProcessorDslPackage.Literals.DAO_DIRECTIVE_PARAMETERS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoType getOut()
  {
    return out;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOut(PojoType newOut, NotificationChain msgs)
  {
    PojoType oldOut = out;
    out = newOut;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS__OUT, oldOut, newOut);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOut(PojoType newOut)
  {
    if (newOut != out)
    {
      NotificationChain msgs = null;
      if (out != null)
        msgs = ((InternalEObject)out).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS__OUT, null, msgs);
      if (newOut != null)
        msgs = ((InternalEObject)newOut).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS__OUT, null, msgs);
      msgs = basicSetOut(newOut, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS__OUT, newOut, newOut));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PojoType> getIns()
  {
    if (ins == null)
    {
      ins = new EObjectContainmentEList<PojoType>(PojoType.class, this, ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS__INS);
    }
    return ins;
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
      case ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS__OUT:
        return basicSetOut(null, msgs);
      case ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS__INS:
        return ((InternalEList<?>)getIns()).basicRemove(otherEnd, msgs);
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
      case ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS__OUT:
        return getOut();
      case ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS__INS:
        return getIns();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS__OUT:
        setOut((PojoType)newValue);
        return;
      case ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS__INS:
        getIns().clear();
        getIns().addAll((Collection<? extends PojoType>)newValue);
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
      case ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS__OUT:
        setOut((PojoType)null);
        return;
      case ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS__INS:
        getIns().clear();
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
      case ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS__OUT:
        return out != null;
      case ProcessorDslPackage.DAO_DIRECTIVE_PARAMETERS__INS:
        return ins != null && !ins.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //DaoDirectiveParametersImpl
