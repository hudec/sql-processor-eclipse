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

import org.eclipse.emf.ecore.util.EDataTypeEList;

import org.sqlproc.dsl.processorDsl.DaogenProperty;
import org.sqlproc.dsl.processorDsl.DebugLevelAssignement;
import org.sqlproc.dsl.processorDsl.ExtendsAssignement;
import org.sqlproc.dsl.processorDsl.ImplementsAssignement;
import org.sqlproc.dsl.processorDsl.PojoType;
import org.sqlproc.dsl.processorDsl.ProcessorDslPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Daogen Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.DaogenPropertyImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.DaogenPropertyImpl#getDbTables <em>Db Tables</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.DaogenPropertyImpl#getImplPackage <em>Impl Package</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.DaogenPropertyImpl#getToImplements <em>To Implements</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.DaogenPropertyImpl#getToExtends <em>To Extends</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.DaogenPropertyImpl#getDbFunction <em>Db Function</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.DaogenPropertyImpl#getResultType <em>Result Type</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.DaogenPropertyImpl#getDebug <em>Debug</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DaogenPropertyImpl extends MinimalEObjectImpl.Container implements DaogenProperty
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

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
   * The default value of the '{@link #getImplPackage() <em>Impl Package</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImplPackage()
   * @generated
   * @ordered
   */
  protected static final String IMPL_PACKAGE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getImplPackage() <em>Impl Package</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImplPackage()
   * @generated
   * @ordered
   */
  protected String implPackage = IMPL_PACKAGE_EDEFAULT;

  /**
   * The cached value of the '{@link #getToImplements() <em>To Implements</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getToImplements()
   * @generated
   * @ordered
   */
  protected ImplementsAssignement toImplements;

  /**
   * The cached value of the '{@link #getToExtends() <em>To Extends</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getToExtends()
   * @generated
   * @ordered
   */
  protected ExtendsAssignement toExtends;

  /**
   * The default value of the '{@link #getDbFunction() <em>Db Function</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDbFunction()
   * @generated
   * @ordered
   */
  protected static final String DB_FUNCTION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDbFunction() <em>Db Function</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDbFunction()
   * @generated
   * @ordered
   */
  protected String dbFunction = DB_FUNCTION_EDEFAULT;

  /**
   * The cached value of the '{@link #getResultType() <em>Result Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResultType()
   * @generated
   * @ordered
   */
  protected PojoType resultType;

  /**
   * The cached value of the '{@link #getDebug() <em>Debug</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDebug()
   * @generated
   * @ordered
   */
  protected DebugLevelAssignement debug;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DaogenPropertyImpl()
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
    return ProcessorDslPackage.Literals.DAOGEN_PROPERTY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DAOGEN_PROPERTY__NAME, oldName, name));
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
      dbTables = new EDataTypeEList<String>(String.class, this, ProcessorDslPackage.DAOGEN_PROPERTY__DB_TABLES);
    }
    return dbTables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getImplPackage()
  {
    return implPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setImplPackage(String newImplPackage)
  {
    String oldImplPackage = implPackage;
    implPackage = newImplPackage;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DAOGEN_PROPERTY__IMPL_PACKAGE, oldImplPackage, implPackage));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ImplementsAssignement getToImplements()
  {
    return toImplements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetToImplements(ImplementsAssignement newToImplements, NotificationChain msgs)
  {
    ImplementsAssignement oldToImplements = toImplements;
    toImplements = newToImplements;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DAOGEN_PROPERTY__TO_IMPLEMENTS, oldToImplements, newToImplements);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setToImplements(ImplementsAssignement newToImplements)
  {
    if (newToImplements != toImplements)
    {
      NotificationChain msgs = null;
      if (toImplements != null)
        msgs = ((InternalEObject)toImplements).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorDslPackage.DAOGEN_PROPERTY__TO_IMPLEMENTS, null, msgs);
      if (newToImplements != null)
        msgs = ((InternalEObject)newToImplements).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorDslPackage.DAOGEN_PROPERTY__TO_IMPLEMENTS, null, msgs);
      msgs = basicSetToImplements(newToImplements, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DAOGEN_PROPERTY__TO_IMPLEMENTS, newToImplements, newToImplements));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExtendsAssignement getToExtends()
  {
    return toExtends;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetToExtends(ExtendsAssignement newToExtends, NotificationChain msgs)
  {
    ExtendsAssignement oldToExtends = toExtends;
    toExtends = newToExtends;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DAOGEN_PROPERTY__TO_EXTENDS, oldToExtends, newToExtends);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setToExtends(ExtendsAssignement newToExtends)
  {
    if (newToExtends != toExtends)
    {
      NotificationChain msgs = null;
      if (toExtends != null)
        msgs = ((InternalEObject)toExtends).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorDslPackage.DAOGEN_PROPERTY__TO_EXTENDS, null, msgs);
      if (newToExtends != null)
        msgs = ((InternalEObject)newToExtends).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorDslPackage.DAOGEN_PROPERTY__TO_EXTENDS, null, msgs);
      msgs = basicSetToExtends(newToExtends, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DAOGEN_PROPERTY__TO_EXTENDS, newToExtends, newToExtends));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDbFunction()
  {
    return dbFunction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDbFunction(String newDbFunction)
  {
    String oldDbFunction = dbFunction;
    dbFunction = newDbFunction;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DAOGEN_PROPERTY__DB_FUNCTION, oldDbFunction, dbFunction));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoType getResultType()
  {
    return resultType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetResultType(PojoType newResultType, NotificationChain msgs)
  {
    PojoType oldResultType = resultType;
    resultType = newResultType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DAOGEN_PROPERTY__RESULT_TYPE, oldResultType, newResultType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResultType(PojoType newResultType)
  {
    if (newResultType != resultType)
    {
      NotificationChain msgs = null;
      if (resultType != null)
        msgs = ((InternalEObject)resultType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorDslPackage.DAOGEN_PROPERTY__RESULT_TYPE, null, msgs);
      if (newResultType != null)
        msgs = ((InternalEObject)newResultType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorDslPackage.DAOGEN_PROPERTY__RESULT_TYPE, null, msgs);
      msgs = basicSetResultType(newResultType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DAOGEN_PROPERTY__RESULT_TYPE, newResultType, newResultType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DebugLevelAssignement getDebug()
  {
    return debug;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDebug(DebugLevelAssignement newDebug, NotificationChain msgs)
  {
    DebugLevelAssignement oldDebug = debug;
    debug = newDebug;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DAOGEN_PROPERTY__DEBUG, oldDebug, newDebug);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDebug(DebugLevelAssignement newDebug)
  {
    if (newDebug != debug)
    {
      NotificationChain msgs = null;
      if (debug != null)
        msgs = ((InternalEObject)debug).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorDslPackage.DAOGEN_PROPERTY__DEBUG, null, msgs);
      if (newDebug != null)
        msgs = ((InternalEObject)newDebug).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorDslPackage.DAOGEN_PROPERTY__DEBUG, null, msgs);
      msgs = basicSetDebug(newDebug, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.DAOGEN_PROPERTY__DEBUG, newDebug, newDebug));
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
      case ProcessorDslPackage.DAOGEN_PROPERTY__TO_IMPLEMENTS:
        return basicSetToImplements(null, msgs);
      case ProcessorDslPackage.DAOGEN_PROPERTY__TO_EXTENDS:
        return basicSetToExtends(null, msgs);
      case ProcessorDslPackage.DAOGEN_PROPERTY__RESULT_TYPE:
        return basicSetResultType(null, msgs);
      case ProcessorDslPackage.DAOGEN_PROPERTY__DEBUG:
        return basicSetDebug(null, msgs);
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
      case ProcessorDslPackage.DAOGEN_PROPERTY__NAME:
        return getName();
      case ProcessorDslPackage.DAOGEN_PROPERTY__DB_TABLES:
        return getDbTables();
      case ProcessorDslPackage.DAOGEN_PROPERTY__IMPL_PACKAGE:
        return getImplPackage();
      case ProcessorDslPackage.DAOGEN_PROPERTY__TO_IMPLEMENTS:
        return getToImplements();
      case ProcessorDslPackage.DAOGEN_PROPERTY__TO_EXTENDS:
        return getToExtends();
      case ProcessorDslPackage.DAOGEN_PROPERTY__DB_FUNCTION:
        return getDbFunction();
      case ProcessorDslPackage.DAOGEN_PROPERTY__RESULT_TYPE:
        return getResultType();
      case ProcessorDslPackage.DAOGEN_PROPERTY__DEBUG:
        return getDebug();
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
      case ProcessorDslPackage.DAOGEN_PROPERTY__NAME:
        setName((String)newValue);
        return;
      case ProcessorDslPackage.DAOGEN_PROPERTY__DB_TABLES:
        getDbTables().clear();
        getDbTables().addAll((Collection<? extends String>)newValue);
        return;
      case ProcessorDslPackage.DAOGEN_PROPERTY__IMPL_PACKAGE:
        setImplPackage((String)newValue);
        return;
      case ProcessorDslPackage.DAOGEN_PROPERTY__TO_IMPLEMENTS:
        setToImplements((ImplementsAssignement)newValue);
        return;
      case ProcessorDslPackage.DAOGEN_PROPERTY__TO_EXTENDS:
        setToExtends((ExtendsAssignement)newValue);
        return;
      case ProcessorDslPackage.DAOGEN_PROPERTY__DB_FUNCTION:
        setDbFunction((String)newValue);
        return;
      case ProcessorDslPackage.DAOGEN_PROPERTY__RESULT_TYPE:
        setResultType((PojoType)newValue);
        return;
      case ProcessorDslPackage.DAOGEN_PROPERTY__DEBUG:
        setDebug((DebugLevelAssignement)newValue);
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
      case ProcessorDslPackage.DAOGEN_PROPERTY__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ProcessorDslPackage.DAOGEN_PROPERTY__DB_TABLES:
        getDbTables().clear();
        return;
      case ProcessorDslPackage.DAOGEN_PROPERTY__IMPL_PACKAGE:
        setImplPackage(IMPL_PACKAGE_EDEFAULT);
        return;
      case ProcessorDslPackage.DAOGEN_PROPERTY__TO_IMPLEMENTS:
        setToImplements((ImplementsAssignement)null);
        return;
      case ProcessorDslPackage.DAOGEN_PROPERTY__TO_EXTENDS:
        setToExtends((ExtendsAssignement)null);
        return;
      case ProcessorDslPackage.DAOGEN_PROPERTY__DB_FUNCTION:
        setDbFunction(DB_FUNCTION_EDEFAULT);
        return;
      case ProcessorDslPackage.DAOGEN_PROPERTY__RESULT_TYPE:
        setResultType((PojoType)null);
        return;
      case ProcessorDslPackage.DAOGEN_PROPERTY__DEBUG:
        setDebug((DebugLevelAssignement)null);
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
      case ProcessorDslPackage.DAOGEN_PROPERTY__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ProcessorDslPackage.DAOGEN_PROPERTY__DB_TABLES:
        return dbTables != null && !dbTables.isEmpty();
      case ProcessorDslPackage.DAOGEN_PROPERTY__IMPL_PACKAGE:
        return IMPL_PACKAGE_EDEFAULT == null ? implPackage != null : !IMPL_PACKAGE_EDEFAULT.equals(implPackage);
      case ProcessorDslPackage.DAOGEN_PROPERTY__TO_IMPLEMENTS:
        return toImplements != null;
      case ProcessorDslPackage.DAOGEN_PROPERTY__TO_EXTENDS:
        return toExtends != null;
      case ProcessorDslPackage.DAOGEN_PROPERTY__DB_FUNCTION:
        return DB_FUNCTION_EDEFAULT == null ? dbFunction != null : !DB_FUNCTION_EDEFAULT.equals(dbFunction);
      case ProcessorDslPackage.DAOGEN_PROPERTY__RESULT_TYPE:
        return resultType != null;
      case ProcessorDslPackage.DAOGEN_PROPERTY__DEBUG:
        return debug != null;
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
    result.append(" (name: ");
    result.append(name);
    result.append(", dbTables: ");
    result.append(dbTables);
    result.append(", implPackage: ");
    result.append(implPackage);
    result.append(", dbFunction: ");
    result.append(dbFunction);
    result.append(')');
    return result.toString();
  }

} //DaogenPropertyImpl
