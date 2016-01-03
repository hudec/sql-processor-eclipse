/**
 */
package org.sqlproc.dsl.processorDsl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

import org.eclipse.xtext.common.types.JvmType;

import org.sqlproc.dsl.processorDsl.ImplementsAssignementGenerics;
import org.sqlproc.dsl.processorDsl.ProcessorDslPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Implements Assignement Generics</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.ImplementsAssignementGenericsImpl#getToImplement <em>To Implement</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.ImplementsAssignementGenericsImpl#getDbTables <em>Db Tables</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.ImplementsAssignementGenericsImpl#getDbNotTables <em>Db Not Tables</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImplementsAssignementGenericsImpl extends MinimalEObjectImpl.Container implements ImplementsAssignementGenerics
{
  /**
   * The cached value of the '{@link #getToImplement() <em>To Implement</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getToImplement()
   * @generated
   * @ordered
   */
  protected JvmType toImplement;

  /**
   * The cached value of the '{@link #getDbTables() <em>Db Tables</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDbTables()
   * @generated
   * @ordered
   */
  protected EList<String> dbTables;

  /**
   * The cached value of the '{@link #getDbNotTables() <em>Db Not Tables</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDbNotTables()
   * @generated
   * @ordered
   */
  protected EList<String> dbNotTables;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ImplementsAssignementGenericsImpl()
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
    return ProcessorDslPackage.Literals.IMPLEMENTS_ASSIGNEMENT_GENERICS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmType getToImplement()
  {
    if (toImplement != null && toImplement.eIsProxy())
    {
      InternalEObject oldToImplement = (InternalEObject)toImplement;
      toImplement = (JvmType)eResolveProxy(oldToImplement);
      if (toImplement != oldToImplement)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__TO_IMPLEMENT, oldToImplement, toImplement));
      }
    }
    return toImplement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmType basicGetToImplement()
  {
    return toImplement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setToImplement(JvmType newToImplement)
  {
    JvmType oldToImplement = toImplement;
    toImplement = newToImplement;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__TO_IMPLEMENT, oldToImplement, toImplement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getDbTables()
  {
    if (dbTables == null)
    {
      dbTables = new EDataTypeEList<String>(String.class, this, ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_TABLES);
    }
    return dbTables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getDbNotTables()
  {
    if (dbNotTables == null)
    {
      dbNotTables = new EDataTypeEList<String>(String.class, this, ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_NOT_TABLES);
    }
    return dbNotTables;
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
      case ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__TO_IMPLEMENT:
        if (resolve) return getToImplement();
        return basicGetToImplement();
      case ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_TABLES:
        return getDbTables();
      case ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_NOT_TABLES:
        return getDbNotTables();
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
      case ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__TO_IMPLEMENT:
        setToImplement((JvmType)newValue);
        return;
      case ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_TABLES:
        getDbTables().clear();
        getDbTables().addAll((Collection<? extends String>)newValue);
        return;
      case ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_NOT_TABLES:
        getDbNotTables().clear();
        getDbNotTables().addAll((Collection<? extends String>)newValue);
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
      case ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__TO_IMPLEMENT:
        setToImplement((JvmType)null);
        return;
      case ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_TABLES:
        getDbTables().clear();
        return;
      case ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_NOT_TABLES:
        getDbNotTables().clear();
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
      case ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__TO_IMPLEMENT:
        return toImplement != null;
      case ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_TABLES:
        return dbTables != null && !dbTables.isEmpty();
      case ProcessorDslPackage.IMPLEMENTS_ASSIGNEMENT_GENERICS__DB_NOT_TABLES:
        return dbNotTables != null && !dbNotTables.isEmpty();
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
    result.append(" (dbTables: ");
    result.append(dbTables);
    result.append(", dbNotTables: ");
    result.append(dbNotTables);
    result.append(')');
    return result.toString();
  }

} //ImplementsAssignementGenericsImpl
