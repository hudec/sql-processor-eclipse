/**
 */
package org.sqlproc.meta.processorMeta.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.sqlproc.meta.processorMeta.ColumnAssignement;
import org.sqlproc.meta.processorMeta.ProcessorMetaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Column Assignement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.meta.processorMeta.impl.ColumnAssignementImpl#getDbColumn <em>Db Column</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.impl.ColumnAssignementImpl#getNewName <em>New Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ColumnAssignementImpl extends MinimalEObjectImpl.Container implements ColumnAssignement
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
   * The default value of the '{@link #getNewName() <em>New Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNewName()
   * @generated
   * @ordered
   */
  protected static final String NEW_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNewName() <em>New Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNewName()
   * @generated
   * @ordered
   */
  protected String newName = NEW_NAME_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ColumnAssignementImpl()
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
    return ProcessorMetaPackage.Literals.COLUMN_ASSIGNEMENT;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.COLUMN_ASSIGNEMENT__DB_COLUMN, oldDbColumn, dbColumn));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNewName()
  {
    return newName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNewName(String newNewName)
  {
    String oldNewName = newName;
    newName = newNewName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.COLUMN_ASSIGNEMENT__NEW_NAME, oldNewName, newName));
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
      case ProcessorMetaPackage.COLUMN_ASSIGNEMENT__DB_COLUMN:
        return getDbColumn();
      case ProcessorMetaPackage.COLUMN_ASSIGNEMENT__NEW_NAME:
        return getNewName();
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
      case ProcessorMetaPackage.COLUMN_ASSIGNEMENT__DB_COLUMN:
        setDbColumn((String)newValue);
        return;
      case ProcessorMetaPackage.COLUMN_ASSIGNEMENT__NEW_NAME:
        setNewName((String)newValue);
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
      case ProcessorMetaPackage.COLUMN_ASSIGNEMENT__DB_COLUMN:
        setDbColumn(DB_COLUMN_EDEFAULT);
        return;
      case ProcessorMetaPackage.COLUMN_ASSIGNEMENT__NEW_NAME:
        setNewName(NEW_NAME_EDEFAULT);
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
      case ProcessorMetaPackage.COLUMN_ASSIGNEMENT__DB_COLUMN:
        return DB_COLUMN_EDEFAULT == null ? dbColumn != null : !DB_COLUMN_EDEFAULT.equals(dbColumn);
      case ProcessorMetaPackage.COLUMN_ASSIGNEMENT__NEW_NAME:
        return NEW_NAME_EDEFAULT == null ? newName != null : !NEW_NAME_EDEFAULT.equals(newName);
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
    result.append(", newName: ");
    result.append(newName);
    result.append(')');
    return result.toString();
  }

} //ColumnAssignementImpl
