/**
 */
package org.sqlproc.meta.processorMeta.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.sqlproc.meta.processorMeta.ImportAssignement;
import org.sqlproc.meta.processorMeta.ProcessorMetaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import Assignement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.meta.processorMeta.impl.ImportAssignementImpl#getDbColumn <em>Db Column</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.impl.ImportAssignementImpl#getPkTable <em>Pk Table</em>}</li>
 *   <li>{@link org.sqlproc.meta.processorMeta.impl.ImportAssignementImpl#getPkColumn <em>Pk Column</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ImportAssignementImpl extends MinimalEObjectImpl.Container implements ImportAssignement
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
   * The default value of the '{@link #getPkTable() <em>Pk Table</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPkTable()
   * @generated
   * @ordered
   */
  protected static final String PK_TABLE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPkTable() <em>Pk Table</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPkTable()
   * @generated
   * @ordered
   */
  protected String pkTable = PK_TABLE_EDEFAULT;

  /**
   * The default value of the '{@link #getPkColumn() <em>Pk Column</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPkColumn()
   * @generated
   * @ordered
   */
  protected static final String PK_COLUMN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPkColumn() <em>Pk Column</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPkColumn()
   * @generated
   * @ordered
   */
  protected String pkColumn = PK_COLUMN_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ImportAssignementImpl()
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
    return ProcessorMetaPackage.Literals.IMPORT_ASSIGNEMENT;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.IMPORT_ASSIGNEMENT__DB_COLUMN, oldDbColumn, dbColumn));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPkTable()
  {
    return pkTable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPkTable(String newPkTable)
  {
    String oldPkTable = pkTable;
    pkTable = newPkTable;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.IMPORT_ASSIGNEMENT__PK_TABLE, oldPkTable, pkTable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPkColumn()
  {
    return pkColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPkColumn(String newPkColumn)
  {
    String oldPkColumn = pkColumn;
    pkColumn = newPkColumn;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorMetaPackage.IMPORT_ASSIGNEMENT__PK_COLUMN, oldPkColumn, pkColumn));
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
      case ProcessorMetaPackage.IMPORT_ASSIGNEMENT__DB_COLUMN:
        return getDbColumn();
      case ProcessorMetaPackage.IMPORT_ASSIGNEMENT__PK_TABLE:
        return getPkTable();
      case ProcessorMetaPackage.IMPORT_ASSIGNEMENT__PK_COLUMN:
        return getPkColumn();
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
      case ProcessorMetaPackage.IMPORT_ASSIGNEMENT__DB_COLUMN:
        setDbColumn((String)newValue);
        return;
      case ProcessorMetaPackage.IMPORT_ASSIGNEMENT__PK_TABLE:
        setPkTable((String)newValue);
        return;
      case ProcessorMetaPackage.IMPORT_ASSIGNEMENT__PK_COLUMN:
        setPkColumn((String)newValue);
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
      case ProcessorMetaPackage.IMPORT_ASSIGNEMENT__DB_COLUMN:
        setDbColumn(DB_COLUMN_EDEFAULT);
        return;
      case ProcessorMetaPackage.IMPORT_ASSIGNEMENT__PK_TABLE:
        setPkTable(PK_TABLE_EDEFAULT);
        return;
      case ProcessorMetaPackage.IMPORT_ASSIGNEMENT__PK_COLUMN:
        setPkColumn(PK_COLUMN_EDEFAULT);
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
      case ProcessorMetaPackage.IMPORT_ASSIGNEMENT__DB_COLUMN:
        return DB_COLUMN_EDEFAULT == null ? dbColumn != null : !DB_COLUMN_EDEFAULT.equals(dbColumn);
      case ProcessorMetaPackage.IMPORT_ASSIGNEMENT__PK_TABLE:
        return PK_TABLE_EDEFAULT == null ? pkTable != null : !PK_TABLE_EDEFAULT.equals(pkTable);
      case ProcessorMetaPackage.IMPORT_ASSIGNEMENT__PK_COLUMN:
        return PK_COLUMN_EDEFAULT == null ? pkColumn != null : !PK_COLUMN_EDEFAULT.equals(pkColumn);
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
    result.append(", pkTable: ");
    result.append(pkTable);
    result.append(", pkColumn: ");
    result.append(pkColumn);
    result.append(')');
    return result.toString();
  }

} //ImportAssignementImpl
