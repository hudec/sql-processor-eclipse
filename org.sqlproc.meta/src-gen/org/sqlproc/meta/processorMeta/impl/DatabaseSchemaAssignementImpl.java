/**
 */
package org.sqlproc.meta.processorMeta.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.sqlproc.meta.processorMeta.DatabaseSchemaAssignement;
import org.sqlproc.meta.processorMeta.ProcessorMetaPackage;
import org.sqlproc.meta.processorMeta.ValueType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Database Schema Assignement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sqlproc.meta.processorMeta.impl.DatabaseSchemaAssignementImpl#getDbSchema <em>Db Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DatabaseSchemaAssignementImpl extends MinimalEObjectImpl.Container implements DatabaseSchemaAssignement
{
  /**
   * The cached value of the '{@link #getDbSchema() <em>Db Schema</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDbSchema()
   * @generated
   * @ordered
   */
  protected ValueType dbSchema;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DatabaseSchemaAssignementImpl()
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
    return ProcessorMetaPackage.Literals.DATABASE_SCHEMA_ASSIGNEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ValueType getDbSchema()
  {
    return dbSchema;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDbSchema(ValueType newDbSchema, NotificationChain msgs)
  {
    ValueType oldDbSchema = dbSchema;
    dbSchema = newDbSchema;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.DATABASE_SCHEMA_ASSIGNEMENT__DB_SCHEMA, oldDbSchema, newDbSchema);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDbSchema(ValueType newDbSchema)
  {
    if (newDbSchema != dbSchema)
    {
      NotificationChain msgs = null;
      if (dbSchema != null)
        msgs = ((InternalEObject)dbSchema).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorMetaPackage.DATABASE_SCHEMA_ASSIGNEMENT__DB_SCHEMA, null, msgs);
      if (newDbSchema != null)
        msgs = ((InternalEObject)newDbSchema).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorMetaPackage.DATABASE_SCHEMA_ASSIGNEMENT__DB_SCHEMA, null, msgs);
      msgs = basicSetDbSchema(newDbSchema, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.DATABASE_SCHEMA_ASSIGNEMENT__DB_SCHEMA, newDbSchema, newDbSchema));
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
      case ProcessorMetaPackage.DATABASE_SCHEMA_ASSIGNEMENT__DB_SCHEMA:
        return basicSetDbSchema(null, msgs);
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
      case ProcessorMetaPackage.DATABASE_SCHEMA_ASSIGNEMENT__DB_SCHEMA:
        return getDbSchema();
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
      case ProcessorMetaPackage.DATABASE_SCHEMA_ASSIGNEMENT__DB_SCHEMA:
        setDbSchema((ValueType)newValue);
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
      case ProcessorMetaPackage.DATABASE_SCHEMA_ASSIGNEMENT__DB_SCHEMA:
        setDbSchema((ValueType)null);
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
      case ProcessorMetaPackage.DATABASE_SCHEMA_ASSIGNEMENT__DB_SCHEMA:
        return dbSchema != null;
    }
    return super.eIsSet(featureID);
  }

} //DatabaseSchemaAssignementImpl
