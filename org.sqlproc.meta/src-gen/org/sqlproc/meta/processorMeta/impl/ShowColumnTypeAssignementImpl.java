/**
 */
package org.sqlproc.meta.processorMeta.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.sqlproc.meta.processorMeta.ProcessorMetaPackage;
import org.sqlproc.meta.processorMeta.ShowColumnTypeAssignement;
import org.sqlproc.meta.processorMeta.ValueType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Show Column Type Assignement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.meta.processorMeta.impl.ShowColumnTypeAssignementImpl#getDbColumn <em>Db Column</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.impl.ShowColumnTypeAssignementImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ShowColumnTypeAssignementImpl extends MinimalEObjectImpl.Container implements ShowColumnTypeAssignement
{
  /**
   * The default value of the '{@link #getDbColumn() <em>Db Column</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDbColumn()
   * @generated
   * @ordered
   */
  protected static final String DB_COLUMN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDbColumn() <em>Db Column</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDbColumn()
   * @generated
   * @ordered
   */
  protected String dbColumn = DB_COLUMN_EDEFAULT;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected ValueType type;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ShowColumnTypeAssignementImpl()
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
    return ProcessorMetaPackage.Literals.SHOW_COLUMN_TYPE_ASSIGNEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDbColumn()
  {
    return dbColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDbColumn(String newDbColumn)
  {
    String oldDbColumn = dbColumn;
    dbColumn = newDbColumn;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.SHOW_COLUMN_TYPE_ASSIGNEMENT__DB_COLUMN, oldDbColumn, dbColumn));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ValueType getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetType(ValueType newType, NotificationChain msgs)
  {
    ValueType oldType = type;
    type = newType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.SHOW_COLUMN_TYPE_ASSIGNEMENT__TYPE, oldType, newType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(ValueType newType)
  {
    if (newType != type)
    {
      NotificationChain msgs = null;
      if (type != null)
        msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorMetaPackage.SHOW_COLUMN_TYPE_ASSIGNEMENT__TYPE, null, msgs);
      if (newType != null)
        msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorMetaPackage.SHOW_COLUMN_TYPE_ASSIGNEMENT__TYPE, null, msgs);
      msgs = basicSetType(newType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.SHOW_COLUMN_TYPE_ASSIGNEMENT__TYPE, newType, newType));
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
      case ProcessorMetaPackage.SHOW_COLUMN_TYPE_ASSIGNEMENT__TYPE:
        return basicSetType(null, msgs);
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
      case ProcessorMetaPackage.SHOW_COLUMN_TYPE_ASSIGNEMENT__DB_COLUMN:
        return getDbColumn();
      case ProcessorMetaPackage.SHOW_COLUMN_TYPE_ASSIGNEMENT__TYPE:
        return getType();
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
      case ProcessorMetaPackage.SHOW_COLUMN_TYPE_ASSIGNEMENT__DB_COLUMN:
        setDbColumn((String)newValue);
        return;
      case ProcessorMetaPackage.SHOW_COLUMN_TYPE_ASSIGNEMENT__TYPE:
        setType((ValueType)newValue);
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
      case ProcessorMetaPackage.SHOW_COLUMN_TYPE_ASSIGNEMENT__DB_COLUMN:
        setDbColumn(DB_COLUMN_EDEFAULT);
        return;
      case ProcessorMetaPackage.SHOW_COLUMN_TYPE_ASSIGNEMENT__TYPE:
        setType((ValueType)null);
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
      case ProcessorMetaPackage.SHOW_COLUMN_TYPE_ASSIGNEMENT__DB_COLUMN:
        return DB_COLUMN_EDEFAULT == null ? dbColumn != null : !DB_COLUMN_EDEFAULT.equals(dbColumn);
      case ProcessorMetaPackage.SHOW_COLUMN_TYPE_ASSIGNEMENT__TYPE:
        return type != null;
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
    result.append(" (dbColumn: ");
    result.append(dbColumn);
    result.append(')');
    return result.toString();
  }

} //ShowColumnTypeAssignementImpl
