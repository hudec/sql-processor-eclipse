/**
 */
package org.sqlproc.dsl.processorDsl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.sqlproc.dsl.processorDsl.DirectiveProperties;
import org.sqlproc.dsl.processorDsl.PojoDirectiveToString;
import org.sqlproc.dsl.processorDsl.ProcessorDslPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pojo Directive To String</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.PojoDirectiveToStringImpl#getProplist <em>Proplist</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PojoDirectiveToStringImpl extends PojoDirectiveImpl implements PojoDirectiveToString
{
  /**
   * The cached value of the '{@link #getProplist() <em>Proplist</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProplist()
   * @generated
   * @ordered
   */
  protected DirectiveProperties proplist;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PojoDirectiveToStringImpl()
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
    return ProcessorDslPackage.Literals.POJO_DIRECTIVE_TO_STRING;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DirectiveProperties getProplist()
  {
    return proplist;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetProplist(DirectiveProperties newProplist, NotificationChain msgs)
  {
    DirectiveProperties oldProplist = proplist;
    proplist = newProplist;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.POJO_DIRECTIVE_TO_STRING__PROPLIST, oldProplist, newProplist);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProplist(DirectiveProperties newProplist)
  {
    if (newProplist != proplist)
    {
      NotificationChain msgs = null;
      if (proplist != null)
        msgs = ((InternalEObject)proplist).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorDslPackage.POJO_DIRECTIVE_TO_STRING__PROPLIST, null, msgs);
      if (newProplist != null)
        msgs = ((InternalEObject)newProplist).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorDslPackage.POJO_DIRECTIVE_TO_STRING__PROPLIST, null, msgs);
      msgs = basicSetProplist(newProplist, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.POJO_DIRECTIVE_TO_STRING__PROPLIST, newProplist, newProplist));
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
      case ProcessorDslPackage.POJO_DIRECTIVE_TO_STRING__PROPLIST:
        return basicSetProplist(null, msgs);
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
      case ProcessorDslPackage.POJO_DIRECTIVE_TO_STRING__PROPLIST:
        return getProplist();
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
      case ProcessorDslPackage.POJO_DIRECTIVE_TO_STRING__PROPLIST:
        setProplist((DirectiveProperties)newValue);
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
      case ProcessorDslPackage.POJO_DIRECTIVE_TO_STRING__PROPLIST:
        setProplist((DirectiveProperties)null);
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
      case ProcessorDslPackage.POJO_DIRECTIVE_TO_STRING__PROPLIST:
        return proplist != null;
    }
    return super.eIsSet(featureID);
  }

} //PojoDirectiveToStringImpl
