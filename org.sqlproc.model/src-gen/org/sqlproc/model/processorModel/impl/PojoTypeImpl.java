/**
 */
package org.sqlproc.model.processorModel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.xtext.common.types.JvmType;

import org.sqlproc.model.processorModel.PojoDefinitionModel;
import org.sqlproc.model.processorModel.PojoType;
import org.sqlproc.model.processorModel.ProcessorModelPackage;
import org.sqlproc.model.processorModel.ValueType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pojo Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoTypeImpl#getIdent <em>Ident</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoTypeImpl#getRef <em>Ref</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoTypeImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoTypeImpl#getGtype <em>Gtype</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoTypeImpl#isArray <em>Array</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PojoTypeImpl extends MinimalEObjectImpl.Container implements PojoType
{
  /**
   * The cached value of the '{@link #getIdent() <em>Ident</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdent()
   * @generated
   * @ordered
   */
  protected ValueType ident;

  /**
   * The cached value of the '{@link #getRef() <em>Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRef()
   * @generated
   * @ordered
   */
  protected PojoDefinitionModel ref;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected JvmType type;

  /**
   * The cached value of the '{@link #getGtype() <em>Gtype</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGtype()
   * @generated
   * @ordered
   */
  protected JvmType gtype;

  /**
   * The default value of the '{@link #isArray() <em>Array</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isArray()
   * @generated
   * @ordered
   */
  protected static final boolean ARRAY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isArray() <em>Array</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isArray()
   * @generated
   * @ordered
   */
  protected boolean array = ARRAY_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PojoTypeImpl()
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
    return ProcessorModelPackage.Literals.POJO_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ValueType getIdent()
  {
    return ident;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIdent(ValueType newIdent, NotificationChain msgs)
  {
    ValueType oldIdent = ident;
    ident = newIdent;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_TYPE__IDENT, oldIdent, newIdent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIdent(ValueType newIdent)
  {
    if (newIdent != ident)
    {
      NotificationChain msgs = null;
      if (ident != null)
        msgs = ((InternalEObject)ident).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.POJO_TYPE__IDENT, null, msgs);
      if (newIdent != null)
        msgs = ((InternalEObject)newIdent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.POJO_TYPE__IDENT, null, msgs);
      msgs = basicSetIdent(newIdent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_TYPE__IDENT, newIdent, newIdent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoDefinitionModel getRef()
  {
    if (ref != null && ref.eIsProxy())
    {
      InternalEObject oldRef = (InternalEObject)ref;
      ref = (PojoDefinitionModel)eResolveProxy(oldRef);
      if (ref != oldRef)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessorModelPackage.POJO_TYPE__REF, oldRef, ref));
      }
    }
    return ref;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PojoDefinitionModel basicGetRef()
  {
    return ref;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRef(PojoDefinitionModel newRef)
  {
    PojoDefinitionModel oldRef = ref;
    ref = newRef;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_TYPE__REF, oldRef, ref));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmType getType()
  {
    if (type != null && type.eIsProxy())
    {
      InternalEObject oldType = (InternalEObject)type;
      type = (JvmType)eResolveProxy(oldType);
      if (type != oldType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessorModelPackage.POJO_TYPE__TYPE, oldType, type));
      }
    }
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmType basicGetType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(JvmType newType)
  {
    JvmType oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_TYPE__TYPE, oldType, type));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmType getGtype()
  {
    if (gtype != null && gtype.eIsProxy())
    {
      InternalEObject oldGtype = (InternalEObject)gtype;
      gtype = (JvmType)eResolveProxy(oldGtype);
      if (gtype != oldGtype)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessorModelPackage.POJO_TYPE__GTYPE, oldGtype, gtype));
      }
    }
    return gtype;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmType basicGetGtype()
  {
    return gtype;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGtype(JvmType newGtype)
  {
    JvmType oldGtype = gtype;
    gtype = newGtype;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_TYPE__GTYPE, oldGtype, gtype));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isArray()
  {
    return array;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArray(boolean newArray)
  {
    boolean oldArray = array;
    array = newArray;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_TYPE__ARRAY, oldArray, array));
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
      case ProcessorModelPackage.POJO_TYPE__IDENT:
        return basicSetIdent(null, msgs);
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
      case ProcessorModelPackage.POJO_TYPE__IDENT:
        return getIdent();
      case ProcessorModelPackage.POJO_TYPE__REF:
        if (resolve) return getRef();
        return basicGetRef();
      case ProcessorModelPackage.POJO_TYPE__TYPE:
        if (resolve) return getType();
        return basicGetType();
      case ProcessorModelPackage.POJO_TYPE__GTYPE:
        if (resolve) return getGtype();
        return basicGetGtype();
      case ProcessorModelPackage.POJO_TYPE__ARRAY:
        return isArray();
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
      case ProcessorModelPackage.POJO_TYPE__IDENT:
        setIdent((ValueType)newValue);
        return;
      case ProcessorModelPackage.POJO_TYPE__REF:
        setRef((PojoDefinitionModel)newValue);
        return;
      case ProcessorModelPackage.POJO_TYPE__TYPE:
        setType((JvmType)newValue);
        return;
      case ProcessorModelPackage.POJO_TYPE__GTYPE:
        setGtype((JvmType)newValue);
        return;
      case ProcessorModelPackage.POJO_TYPE__ARRAY:
        setArray((Boolean)newValue);
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
      case ProcessorModelPackage.POJO_TYPE__IDENT:
        setIdent((ValueType)null);
        return;
      case ProcessorModelPackage.POJO_TYPE__REF:
        setRef((PojoDefinitionModel)null);
        return;
      case ProcessorModelPackage.POJO_TYPE__TYPE:
        setType((JvmType)null);
        return;
      case ProcessorModelPackage.POJO_TYPE__GTYPE:
        setGtype((JvmType)null);
        return;
      case ProcessorModelPackage.POJO_TYPE__ARRAY:
        setArray(ARRAY_EDEFAULT);
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
      case ProcessorModelPackage.POJO_TYPE__IDENT:
        return ident != null;
      case ProcessorModelPackage.POJO_TYPE__REF:
        return ref != null;
      case ProcessorModelPackage.POJO_TYPE__TYPE:
        return type != null;
      case ProcessorModelPackage.POJO_TYPE__GTYPE:
        return gtype != null;
      case ProcessorModelPackage.POJO_TYPE__ARRAY:
        return array != ARRAY_EDEFAULT;
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
    result.append(" (array: ");
    result.append(array);
    result.append(')');
    return result.toString();
  }

} //PojoTypeImpl
