/**
 */
package org.sqlproc.model.processorModel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.sqlproc.model.processorModel.DaoDirectiveParameters;
import org.sqlproc.model.processorModel.DaoFunProcDirective;
import org.sqlproc.model.processorModel.FunProcType;
import org.sqlproc.model.processorModel.ProcessorModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dao Fun Proc Directive</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.model.processorModel.impl.DaoFunProcDirectiveImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.impl.DaoFunProcDirectiveImpl#getParamlist <em>Paramlist</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DaoFunProcDirectiveImpl extends DaoDirectiveImpl implements DaoFunProcDirective
{
  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected FunProcType type;

  /**
   * The cached value of the '{@link #getParamlist() <em>Paramlist</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParamlist()
   * @generated
   * @ordered
   */
  protected DaoDirectiveParameters paramlist;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DaoFunProcDirectiveImpl()
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
    return ProcessorModelPackage.Literals.DAO_FUN_PROC_DIRECTIVE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunProcType getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetType(FunProcType newType, NotificationChain msgs)
  {
    FunProcType oldType = type;
    type = newType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__TYPE, oldType, newType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(FunProcType newType)
  {
    if (newType != type)
    {
      NotificationChain msgs = null;
      if (type != null)
        msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__TYPE, null, msgs);
      if (newType != null)
        msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__TYPE, null, msgs);
      msgs = basicSetType(newType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__TYPE, newType, newType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DaoDirectiveParameters getParamlist()
  {
    return paramlist;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParamlist(DaoDirectiveParameters newParamlist, NotificationChain msgs)
  {
    DaoDirectiveParameters oldParamlist = paramlist;
    paramlist = newParamlist;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__PARAMLIST, oldParamlist, newParamlist);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParamlist(DaoDirectiveParameters newParamlist)
  {
    if (newParamlist != paramlist)
    {
      NotificationChain msgs = null;
      if (paramlist != null)
        msgs = ((InternalEObject)paramlist).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__PARAMLIST, null, msgs);
      if (newParamlist != null)
        msgs = ((InternalEObject)newParamlist).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__PARAMLIST, null, msgs);
      msgs = basicSetParamlist(newParamlist, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__PARAMLIST, newParamlist, newParamlist));
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
      case ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__TYPE:
        return basicSetType(null, msgs);
      case ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__PARAMLIST:
        return basicSetParamlist(null, msgs);
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
      case ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__TYPE:
        return getType();
      case ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__PARAMLIST:
        return getParamlist();
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
      case ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__TYPE:
        setType((FunProcType)newValue);
        return;
      case ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__PARAMLIST:
        setParamlist((DaoDirectiveParameters)newValue);
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
      case ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__TYPE:
        setType((FunProcType)null);
        return;
      case ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__PARAMLIST:
        setParamlist((DaoDirectiveParameters)null);
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
      case ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__TYPE:
        return type != null;
      case ProcessorModelPackage.DAO_FUN_PROC_DIRECTIVE__PARAMLIST:
        return paramlist != null;
    }
    return super.eIsSet(featureID);
  }

} //DaoFunProcDirectiveImpl
