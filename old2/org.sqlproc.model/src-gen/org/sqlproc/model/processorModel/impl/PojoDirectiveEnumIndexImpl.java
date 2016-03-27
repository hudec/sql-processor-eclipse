/**
 */
package org.sqlproc.model.processorModel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.sqlproc.model.processorModel.DirectiveProperties;
import org.sqlproc.model.processorModel.IndexType;
import org.sqlproc.model.processorModel.PojoDirectiveEnumIndex;
import org.sqlproc.model.processorModel.ProcessorModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pojo Directive Enum Index</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoDirectiveEnumIndexImpl#getIndex <em>Index</em>}</li>
 *   <li>{@link org.sqlproc.model.processorModel.impl.PojoDirectiveEnumIndexImpl#getProplist <em>Proplist</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PojoDirectiveEnumIndexImpl extends PojoDirectiveImpl implements PojoDirectiveEnumIndex
{
  /**
   * The cached value of the '{@link #getIndex() <em>Index</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndex()
   * @generated
   * @ordered
   */
  protected IndexType index;

  /**
   * The cached value of the '{@link #getProplist() <em>Proplist</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProplist()
   * @generated
   * @ordered
   */
  protected DirectiveProperties proplist;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PojoDirectiveEnumIndexImpl()
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
    return ProcessorModelPackage.Literals.POJO_DIRECTIVE_ENUM_INDEX;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IndexType getIndex()
  {
    return index;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIndex(IndexType newIndex, NotificationChain msgs)
  {
    IndexType oldIndex = index;
    index = newIndex;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__INDEX, oldIndex, newIndex);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIndex(IndexType newIndex)
  {
    if (newIndex != index)
    {
      NotificationChain msgs = null;
      if (index != null)
        msgs = ((InternalEObject)index).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__INDEX, null, msgs);
      if (newIndex != null)
        msgs = ((InternalEObject)newIndex).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__INDEX, null, msgs);
      msgs = basicSetIndex(newIndex, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__INDEX, newIndex, newIndex));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DirectiveProperties getProplist()
  {
    return proplist;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetProplist(DirectiveProperties newProplist, NotificationChain msgs)
  {
    DirectiveProperties oldProplist = proplist;
    proplist = newProplist;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__PROPLIST, oldProplist, newProplist);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProplist(DirectiveProperties newProplist)
  {
    if (newProplist != proplist)
    {
      NotificationChain msgs = null;
      if (proplist != null)
        msgs = ((InternalEObject)proplist).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__PROPLIST, null, msgs);
      if (newProplist != null)
        msgs = ((InternalEObject)newProplist).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__PROPLIST, null, msgs);
      msgs = basicSetProplist(newProplist, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__PROPLIST, newProplist, newProplist));
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
      case ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__INDEX:
        return basicSetIndex(null, msgs);
      case ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__PROPLIST:
        return basicSetProplist(null, msgs);
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
      case ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__INDEX:
        return getIndex();
      case ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__PROPLIST:
        return getProplist();
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
      case ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__INDEX:
        setIndex((IndexType)newValue);
        return;
      case ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__PROPLIST:
        setProplist((DirectiveProperties)newValue);
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
      case ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__INDEX:
        setIndex((IndexType)null);
        return;
      case ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__PROPLIST:
        setProplist((DirectiveProperties)null);
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
      case ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__INDEX:
        return index != null;
      case ProcessorModelPackage.POJO_DIRECTIVE_ENUM_INDEX__PROPLIST:
        return proplist != null;
    }
    return super.eIsSet(featureID);
  }

} //PojoDirectiveEnumIndexImpl
