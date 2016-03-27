/**
 */
package org.sqlproc.model.processorModel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;

import org.eclipse.xtext.xbase.XExpression;

import org.sqlproc.model.processorModel.Annotation;
import org.sqlproc.model.processorModel.PojoProcedure;
import org.sqlproc.model.processorModel.ProcessorModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pojo Procedure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoProcedureImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoProcedureImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoProcedureImpl#isStatic <em>Static</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoProcedureImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoProcedureImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoProcedureImpl#getParams <em>Params</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoProcedureImpl#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PojoProcedureImpl extends MinimalEObjectImpl.Container implements PojoProcedure
{
  /**
   * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotations()
   * @generated
   * @ordered
   */
  protected EList<Annotation> annotations;

  /**
   * The default value of the '{@link #isFinal() <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFinal()
   * @generated
   * @ordered
   */
  protected static final boolean FINAL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isFinal() <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFinal()
   * @generated
   * @ordered
   */
  protected boolean final_ = FINAL_EDEFAULT;

  /**
   * The default value of the '{@link #isStatic() <em>Static</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStatic()
   * @generated
   * @ordered
   */
  protected static final boolean STATIC_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isStatic() <em>Static</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isStatic()
   * @generated
   * @ordered
   */
  protected boolean static_ = STATIC_EDEFAULT;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected JvmParameterizedTypeReference type;

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
   * The cached value of the '{@link #getParams() <em>Params</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParams()
   * @generated
   * @ordered
   */
  protected EList<JvmFormalParameter> params;

  /**
   * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBody()
   * @generated
   * @ordered
   */
  protected XExpression body;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PojoProcedureImpl()
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
    return ProcessorModelPackage.Literals.POJO_PROCEDURE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Annotation> getAnnotations()
  {
    if (annotations == null)
    {
      annotations = new EObjectContainmentEList<Annotation>(Annotation.class, this, ProcessorModelPackage.POJO_PROCEDURE__ANNOTATIONS);
    }
    return annotations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isFinal()
  {
    return final_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFinal(boolean newFinal)
  {
    boolean oldFinal = final_;
    final_ = newFinal;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_PROCEDURE__FINAL, oldFinal, final_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isStatic()
  {
    return static_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStatic(boolean newStatic)
  {
    boolean oldStatic = static_;
    static_ = newStatic;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_PROCEDURE__STATIC, oldStatic, static_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmParameterizedTypeReference getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetType(JvmParameterizedTypeReference newType, NotificationChain msgs)
  {
    JvmParameterizedTypeReference oldType = type;
    type = newType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_PROCEDURE__TYPE, oldType, newType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(JvmParameterizedTypeReference newType)
  {
    if (newType != type)
    {
      NotificationChain msgs = null;
      if (type != null)
        msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.POJO_PROCEDURE__TYPE, null, msgs);
      if (newType != null)
        msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.POJO_PROCEDURE__TYPE, null, msgs);
      msgs = basicSetType(newType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_PROCEDURE__TYPE, newType, newType));
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
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_PROCEDURE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<JvmFormalParameter> getParams()
  {
    if (params == null)
    {
      params = new EObjectContainmentEList<JvmFormalParameter>(JvmFormalParameter.class, this, ProcessorModelPackage.POJO_PROCEDURE__PARAMS);
    }
    return params;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XExpression getBody()
  {
    return body;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBody(XExpression newBody, NotificationChain msgs)
  {
    XExpression oldBody = body;
    body = newBody;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_PROCEDURE__BODY, oldBody, newBody);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBody(XExpression newBody)
  {
    if (newBody != body)
    {
      NotificationChain msgs = null;
      if (body != null)
        msgs = ((InternalEObject)body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.POJO_PROCEDURE__BODY, null, msgs);
      if (newBody != null)
        msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.POJO_PROCEDURE__BODY, null, msgs);
      msgs = basicSetBody(newBody, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_PROCEDURE__BODY, newBody, newBody));
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
      case ProcessorModelPackage.POJO_PROCEDURE__ANNOTATIONS:
        return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
      case ProcessorModelPackage.POJO_PROCEDURE__TYPE:
        return basicSetType(null, msgs);
      case ProcessorModelPackage.POJO_PROCEDURE__PARAMS:
        return ((InternalEList<?>)getParams()).basicRemove(otherEnd, msgs);
      case ProcessorModelPackage.POJO_PROCEDURE__BODY:
        return basicSetBody(null, msgs);
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
      case ProcessorModelPackage.POJO_PROCEDURE__ANNOTATIONS:
        return getAnnotations();
      case ProcessorModelPackage.POJO_PROCEDURE__FINAL:
        return isFinal();
      case ProcessorModelPackage.POJO_PROCEDURE__STATIC:
        return isStatic();
      case ProcessorModelPackage.POJO_PROCEDURE__TYPE:
        return getType();
      case ProcessorModelPackage.POJO_PROCEDURE__NAME:
        return getName();
      case ProcessorModelPackage.POJO_PROCEDURE__PARAMS:
        return getParams();
      case ProcessorModelPackage.POJO_PROCEDURE__BODY:
        return getBody();
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
      case ProcessorModelPackage.POJO_PROCEDURE__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection<? extends Annotation>)newValue);
        return;
      case ProcessorModelPackage.POJO_PROCEDURE__FINAL:
        setFinal((Boolean)newValue);
        return;
      case ProcessorModelPackage.POJO_PROCEDURE__STATIC:
        setStatic((Boolean)newValue);
        return;
      case ProcessorModelPackage.POJO_PROCEDURE__TYPE:
        setType((JvmParameterizedTypeReference)newValue);
        return;
      case ProcessorModelPackage.POJO_PROCEDURE__NAME:
        setName((String)newValue);
        return;
      case ProcessorModelPackage.POJO_PROCEDURE__PARAMS:
        getParams().clear();
        getParams().addAll((Collection<? extends JvmFormalParameter>)newValue);
        return;
      case ProcessorModelPackage.POJO_PROCEDURE__BODY:
        setBody((XExpression)newValue);
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
      case ProcessorModelPackage.POJO_PROCEDURE__ANNOTATIONS:
        getAnnotations().clear();
        return;
      case ProcessorModelPackage.POJO_PROCEDURE__FINAL:
        setFinal(FINAL_EDEFAULT);
        return;
      case ProcessorModelPackage.POJO_PROCEDURE__STATIC:
        setStatic(STATIC_EDEFAULT);
        return;
      case ProcessorModelPackage.POJO_PROCEDURE__TYPE:
        setType((JvmParameterizedTypeReference)null);
        return;
      case ProcessorModelPackage.POJO_PROCEDURE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ProcessorModelPackage.POJO_PROCEDURE__PARAMS:
        getParams().clear();
        return;
      case ProcessorModelPackage.POJO_PROCEDURE__BODY:
        setBody((XExpression)null);
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
      case ProcessorModelPackage.POJO_PROCEDURE__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
      case ProcessorModelPackage.POJO_PROCEDURE__FINAL:
        return final_ != FINAL_EDEFAULT;
      case ProcessorModelPackage.POJO_PROCEDURE__STATIC:
        return static_ != STATIC_EDEFAULT;
      case ProcessorModelPackage.POJO_PROCEDURE__TYPE:
        return type != null;
      case ProcessorModelPackage.POJO_PROCEDURE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ProcessorModelPackage.POJO_PROCEDURE__PARAMS:
        return params != null && !params.isEmpty();
      case ProcessorModelPackage.POJO_PROCEDURE__BODY:
        return body != null;
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
    result.append(" (final: ");
    result.append(final_);
    result.append(", static: ");
    result.append(static_);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //PojoProcedureImpl
