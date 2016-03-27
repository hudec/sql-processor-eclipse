/**
 */
package org.sqlproc.meta.processorMeta.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.sqlproc.meta.processorMeta.PojoType;
import org.sqlproc.meta.processorMeta.ProcedurePojoAssignement;
import org.sqlproc.meta.processorMeta.ProcessorMetaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Procedure Pojo Assignement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.meta.processorMeta.impl.ProcedurePojoAssignementImpl#getDbProcedure <em>Db Procedure</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.impl.ProcedurePojoAssignementImpl#getPojo <em>Pojo</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProcedurePojoAssignementImpl extends MinimalEObjectImpl.Container implements ProcedurePojoAssignement
{
  /**
   * The default value of the '{@link #getDbProcedure() <em>Db Procedure</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDbProcedure()
   * @generated
   * @ordered
   */
  protected static final String DB_PROCEDURE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDbProcedure() <em>Db Procedure</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDbProcedure()
   * @generated
   * @ordered
   */
  protected String dbProcedure = DB_PROCEDURE_EDEFAULT;

  /**
   * The cached value of the '{@link #getPojo() <em>Pojo</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPojo()
   * @generated
   * @ordered
   */
  protected PojoType pojo;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ProcedurePojoAssignementImpl()
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
    return ProcessorMetaPackage.Literals.PROCEDURE_POJO_ASSIGNEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDbProcedure()
  {
    return dbProcedure;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDbProcedure(String newDbProcedure)
  {
    String oldDbProcedure = dbProcedure;
    dbProcedure = newDbProcedure;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.PROCEDURE_POJO_ASSIGNEMENT__DB_PROCEDURE, oldDbProcedure, dbProcedure));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoType getPojo()
  {
    return pojo;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPojo(PojoType newPojo, NotificationChain msgs)
  {
    PojoType oldPojo = pojo;
    pojo = newPojo;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.PROCEDURE_POJO_ASSIGNEMENT__POJO, oldPojo, newPojo);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPojo(PojoType newPojo)
  {
    if (newPojo != pojo)
    {
      NotificationChain msgs = null;
      if (pojo != null)
        msgs = ((InternalEObject)pojo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorMetaPackage.PROCEDURE_POJO_ASSIGNEMENT__POJO, null, msgs);
      if (newPojo != null)
        msgs = ((InternalEObject)newPojo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorMetaPackage.PROCEDURE_POJO_ASSIGNEMENT__POJO, null, msgs);
      msgs = basicSetPojo(newPojo, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.PROCEDURE_POJO_ASSIGNEMENT__POJO, newPojo, newPojo));
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
      case ProcessorMetaPackage.PROCEDURE_POJO_ASSIGNEMENT__POJO:
        return basicSetPojo(null, msgs);
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
      case ProcessorMetaPackage.PROCEDURE_POJO_ASSIGNEMENT__DB_PROCEDURE:
        return getDbProcedure();
      case ProcessorMetaPackage.PROCEDURE_POJO_ASSIGNEMENT__POJO:
        return getPojo();
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
      case ProcessorMetaPackage.PROCEDURE_POJO_ASSIGNEMENT__DB_PROCEDURE:
        setDbProcedure((String)newValue);
        return;
      case ProcessorMetaPackage.PROCEDURE_POJO_ASSIGNEMENT__POJO:
        setPojo((PojoType)newValue);
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
      case ProcessorMetaPackage.PROCEDURE_POJO_ASSIGNEMENT__DB_PROCEDURE:
        setDbProcedure(DB_PROCEDURE_EDEFAULT);
        return;
      case ProcessorMetaPackage.PROCEDURE_POJO_ASSIGNEMENT__POJO:
        setPojo((PojoType)null);
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
      case ProcessorMetaPackage.PROCEDURE_POJO_ASSIGNEMENT__DB_PROCEDURE:
        return DB_PROCEDURE_EDEFAULT == null ? dbProcedure != null : !DB_PROCEDURE_EDEFAULT.equals(dbProcedure);
      case ProcessorMetaPackage.PROCEDURE_POJO_ASSIGNEMENT__POJO:
        return pojo != null;
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
    result.append(" (dbProcedure: ");
    result.append(dbProcedure);
    result.append(')');
    return result.toString();
  }

} //ProcedurePojoAssignementImpl
