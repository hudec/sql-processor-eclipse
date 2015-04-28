/**
 */
package org.sqlproc.meta.processorMeta.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.sqlproc.meta.processorMeta.MetaSqlTypeAssignement;
import org.sqlproc.meta.processorMeta.ProcessorMetaPackage;
import org.sqlproc.meta.processorMeta.ValueType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Meta Sql Type Assignement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sqlproc.meta.processorMeta.impl.MetaSqlTypeAssignementImpl#getSqlType <em>Sql Type</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.impl.MetaSqlTypeAssignementImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.impl.MetaSqlTypeAssignementImpl#getExtension <em>Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetaSqlTypeAssignementImpl extends MinimalEObjectImpl.Container implements MetaSqlTypeAssignement
{
  /**
   * The cached value of the '{@link #getSqlType() <em>Sql Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSqlType()
   * @generated
   * @ordered
   */
  protected ValueType sqlType;

  /**
   * The default value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected static final String TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected String type = TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #getExtension() <em>Extension</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExtension()
   * @generated
   * @ordered
   */
  protected static final String EXTENSION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getExtension() <em>Extension</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExtension()
   * @generated
   * @ordered
   */
  protected String extension = EXTENSION_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MetaSqlTypeAssignementImpl()
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
    return ProcessorMetaPackage.Literals.META_SQL_TYPE_ASSIGNEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ValueType getSqlType()
  {
    return sqlType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSqlType(ValueType newSqlType, NotificationChain msgs)
  {
    ValueType oldSqlType = sqlType;
    sqlType = newSqlType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__SQL_TYPE, oldSqlType, newSqlType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSqlType(ValueType newSqlType)
  {
    if (newSqlType != sqlType)
    {
      NotificationChain msgs = null;
      if (sqlType != null)
        msgs = ((InternalEObject)sqlType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__SQL_TYPE, null, msgs);
      if (newSqlType != null)
        msgs = ((InternalEObject)newSqlType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__SQL_TYPE, null, msgs);
      msgs = basicSetSqlType(newSqlType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__SQL_TYPE, newSqlType, newSqlType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(String newType)
  {
    String oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__TYPE, oldType, type));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getExtension()
  {
    return extension;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExtension(String newExtension)
  {
    String oldExtension = extension;
    extension = newExtension;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__EXTENSION, oldExtension, extension));
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
      case ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__SQL_TYPE:
        return basicSetSqlType(null, msgs);
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
      case ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__SQL_TYPE:
        return getSqlType();
      case ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__TYPE:
        return getType();
      case ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__EXTENSION:
        return getExtension();
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
      case ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__SQL_TYPE:
        setSqlType((ValueType)newValue);
        return;
      case ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__TYPE:
        setType((String)newValue);
        return;
      case ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__EXTENSION:
        setExtension((String)newValue);
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
      case ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__SQL_TYPE:
        setSqlType((ValueType)null);
        return;
      case ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__TYPE:
        setType(TYPE_EDEFAULT);
        return;
      case ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__EXTENSION:
        setExtension(EXTENSION_EDEFAULT);
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
      case ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__SQL_TYPE:
        return sqlType != null;
      case ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__TYPE:
        return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
      case ProcessorMetaPackage.META_SQL_TYPE_ASSIGNEMENT__EXTENSION:
        return EXTENSION_EDEFAULT == null ? extension != null : !EXTENSION_EDEFAULT.equals(extension);
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
    result.append(" (type: ");
    result.append(type);
    result.append(", extension: ");
    result.append(extension);
    result.append(')');
    return result.toString();
  }

} //MetaSqlTypeAssignementImpl
