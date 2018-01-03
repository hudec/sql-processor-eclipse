/**
 * generated by Xtext 2.12.0
 */
package org.sqlproc.model.processorModel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xtext.xbase.XExpression;

import org.sqlproc.model.processorModel.PojoAttribute;
import org.sqlproc.model.processorModel.PojoAttributeDirective;
import org.sqlproc.model.processorModel.ProcessorModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pojo Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoAttributeImpl#getDirectives <em>Directives</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoAttributeImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoAttributeImpl#isArray <em>Array</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoAttributeImpl#getInitExpr <em>Init Expr</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PojoAttributeImpl extends FeatureImpl implements PojoAttribute
{
  /**
   * The cached value of the '{@link #getDirectives() <em>Directives</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDirectives()
   * @generated
   * @ordered
   */
  protected EList<PojoAttributeDirective> directives;

  /**
   * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
  protected static final String KIND_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
  protected String kind = KIND_EDEFAULT;

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
   * The cached value of the '{@link #getInitExpr() <em>Init Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInitExpr()
   * @generated
   * @ordered
   */
  protected XExpression initExpr;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PojoAttributeImpl()
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
    return ProcessorModelPackage.Literals.POJO_ATTRIBUTE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PojoAttributeDirective> getDirectives()
  {
    if (directives == null)
    {
      directives = new EObjectContainmentEList<PojoAttributeDirective>(PojoAttributeDirective.class, this, ProcessorModelPackage.POJO_ATTRIBUTE__DIRECTIVES);
    }
    return directives;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getKind()
  {
    return kind;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKind(String newKind)
  {
    String oldKind = kind;
    kind = newKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_ATTRIBUTE__KIND, oldKind, kind));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_ATTRIBUTE__ARRAY, oldArray, array));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XExpression getInitExpr()
  {
    return initExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInitExpr(XExpression newInitExpr, NotificationChain msgs)
  {
    XExpression oldInitExpr = initExpr;
    initExpr = newInitExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_ATTRIBUTE__INIT_EXPR, oldInitExpr, newInitExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInitExpr(XExpression newInitExpr)
  {
    if (newInitExpr != initExpr)
    {
      NotificationChain msgs = null;
      if (initExpr != null)
        msgs = ((InternalEObject)initExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.POJO_ATTRIBUTE__INIT_EXPR, null, msgs);
      if (newInitExpr != null)
        msgs = ((InternalEObject)newInitExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.POJO_ATTRIBUTE__INIT_EXPR, null, msgs);
      msgs = basicSetInitExpr(newInitExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_ATTRIBUTE__INIT_EXPR, newInitExpr, newInitExpr));
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
      case ProcessorModelPackage.POJO_ATTRIBUTE__DIRECTIVES:
        return ((InternalEList<?>)getDirectives()).basicRemove(otherEnd, msgs);
      case ProcessorModelPackage.POJO_ATTRIBUTE__INIT_EXPR:
        return basicSetInitExpr(null, msgs);
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
      case ProcessorModelPackage.POJO_ATTRIBUTE__DIRECTIVES:
        return getDirectives();
      case ProcessorModelPackage.POJO_ATTRIBUTE__KIND:
        return getKind();
      case ProcessorModelPackage.POJO_ATTRIBUTE__ARRAY:
        return isArray();
      case ProcessorModelPackage.POJO_ATTRIBUTE__INIT_EXPR:
        return getInitExpr();
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
      case ProcessorModelPackage.POJO_ATTRIBUTE__DIRECTIVES:
        getDirectives().clear();
        getDirectives().addAll((Collection<? extends PojoAttributeDirective>)newValue);
        return;
      case ProcessorModelPackage.POJO_ATTRIBUTE__KIND:
        setKind((String)newValue);
        return;
      case ProcessorModelPackage.POJO_ATTRIBUTE__ARRAY:
        setArray((Boolean)newValue);
        return;
      case ProcessorModelPackage.POJO_ATTRIBUTE__INIT_EXPR:
        setInitExpr((XExpression)newValue);
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
      case ProcessorModelPackage.POJO_ATTRIBUTE__DIRECTIVES:
        getDirectives().clear();
        return;
      case ProcessorModelPackage.POJO_ATTRIBUTE__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case ProcessorModelPackage.POJO_ATTRIBUTE__ARRAY:
        setArray(ARRAY_EDEFAULT);
        return;
      case ProcessorModelPackage.POJO_ATTRIBUTE__INIT_EXPR:
        setInitExpr((XExpression)null);
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
      case ProcessorModelPackage.POJO_ATTRIBUTE__DIRECTIVES:
        return directives != null && !directives.isEmpty();
      case ProcessorModelPackage.POJO_ATTRIBUTE__KIND:
        return KIND_EDEFAULT == null ? kind != null : !KIND_EDEFAULT.equals(kind);
      case ProcessorModelPackage.POJO_ATTRIBUTE__ARRAY:
        return array != ARRAY_EDEFAULT;
      case ProcessorModelPackage.POJO_ATTRIBUTE__INIT_EXPR:
        return initExpr != null;
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
    result.append(" (kind: ");
    result.append(kind);
    result.append(", array: ");
    result.append(array);
    result.append(')');
    return result.toString();
  }

} //PojoAttributeImpl
