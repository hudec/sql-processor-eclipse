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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xtext.common.types.JvmType;

import org.sqlproc.dsl.processorDsl.Implements;
import org.sqlproc.dsl.processorDsl.ImplementsExtendsDirective;
import org.sqlproc.dsl.processorDsl.PojoDao;
import org.sqlproc.dsl.processorDsl.PojoEntity;
import org.sqlproc.dsl.processorDsl.ProcessorDslPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Implements</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.ImplementsImpl#getDirectives <em>Directives</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.ImplementsImpl#getImplements <em>Implements</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.ImplementsImpl#getOnlyPojos <em>Only Pojos</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.ImplementsImpl#getOnlyDaos <em>Only Daos</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.ImplementsImpl#getExceptPojos <em>Except Pojos</em>}</li>
 *   <li>{@link org.sqlproc.dsl.processorDsl.impl.ImplementsImpl#getExceptDaos <em>Except Daos</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImplementsImpl extends AbstractPojoEntityImpl implements Implements
{
  /**
   * The cached value of the '{@link #getDirectives() <em>Directives</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDirectives()
   * @generated
   * @ordered
   */
  protected EList<ImplementsExtendsDirective> directives;

  /**
   * The cached value of the '{@link #getImplements() <em>Implements</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImplements()
   * @generated
   * @ordered
   */
  protected JvmType implements_;

  /**
   * The cached value of the '{@link #getOnlyPojos() <em>Only Pojos</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOnlyPojos()
   * @generated
   * @ordered
   */
  protected EList<PojoEntity> onlyPojos;

  /**
   * The cached value of the '{@link #getOnlyDaos() <em>Only Daos</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOnlyDaos()
   * @generated
   * @ordered
   */
  protected EList<PojoDao> onlyDaos;

  /**
   * The cached value of the '{@link #getExceptPojos() <em>Except Pojos</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExceptPojos()
   * @generated
   * @ordered
   */
  protected EList<PojoEntity> exceptPojos;

  /**
   * The cached value of the '{@link #getExceptDaos() <em>Except Daos</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExceptDaos()
   * @generated
   * @ordered
   */
  protected EList<PojoDao> exceptDaos;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ImplementsImpl()
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
    return ProcessorDslPackage.Literals.IMPLEMENTS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ImplementsExtendsDirective> getDirectives()
  {
    if (directives == null)
    {
      directives = new EObjectContainmentEList<ImplementsExtendsDirective>(ImplementsExtendsDirective.class, this, ProcessorDslPackage.IMPLEMENTS__DIRECTIVES);
    }
    return directives;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmType getImplements()
  {
    if (implements_ != null && implements_.eIsProxy())
    {
      InternalEObject oldImplements = (InternalEObject)implements_;
      implements_ = (JvmType)eResolveProxy(oldImplements);
      if (implements_ != oldImplements)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProcessorDslPackage.IMPLEMENTS__IMPLEMENTS, oldImplements, implements_));
      }
    }
    return implements_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmType basicGetImplements()
  {
    return implements_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setImplements(JvmType newImplements)
  {
    JvmType oldImplements = implements_;
    implements_ = newImplements;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ProcessorDslPackage.IMPLEMENTS__IMPLEMENTS, oldImplements, implements_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PojoEntity> getOnlyPojos()
  {
    if (onlyPojos == null)
    {
      onlyPojos = new EObjectResolvingEList<PojoEntity>(PojoEntity.class, this, ProcessorDslPackage.IMPLEMENTS__ONLY_POJOS);
    }
    return onlyPojos;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PojoDao> getOnlyDaos()
  {
    if (onlyDaos == null)
    {
      onlyDaos = new EObjectResolvingEList<PojoDao>(PojoDao.class, this, ProcessorDslPackage.IMPLEMENTS__ONLY_DAOS);
    }
    return onlyDaos;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PojoEntity> getExceptPojos()
  {
    if (exceptPojos == null)
    {
      exceptPojos = new EObjectResolvingEList<PojoEntity>(PojoEntity.class, this, ProcessorDslPackage.IMPLEMENTS__EXCEPT_POJOS);
    }
    return exceptPojos;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<PojoDao> getExceptDaos()
  {
    if (exceptDaos == null)
    {
      exceptDaos = new EObjectResolvingEList<PojoDao>(PojoDao.class, this, ProcessorDslPackage.IMPLEMENTS__EXCEPT_DAOS);
    }
    return exceptDaos;
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
      case ProcessorDslPackage.IMPLEMENTS__DIRECTIVES:
        return ((InternalEList<?>)getDirectives()).basicRemove(otherEnd, msgs);
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
      case ProcessorDslPackage.IMPLEMENTS__DIRECTIVES:
        return getDirectives();
      case ProcessorDslPackage.IMPLEMENTS__IMPLEMENTS:
        if (resolve) return getImplements();
        return basicGetImplements();
      case ProcessorDslPackage.IMPLEMENTS__ONLY_POJOS:
        return getOnlyPojos();
      case ProcessorDslPackage.IMPLEMENTS__ONLY_DAOS:
        return getOnlyDaos();
      case ProcessorDslPackage.IMPLEMENTS__EXCEPT_POJOS:
        return getExceptPojos();
      case ProcessorDslPackage.IMPLEMENTS__EXCEPT_DAOS:
        return getExceptDaos();
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
      case ProcessorDslPackage.IMPLEMENTS__DIRECTIVES:
        getDirectives().clear();
        getDirectives().addAll((Collection<? extends ImplementsExtendsDirective>)newValue);
        return;
      case ProcessorDslPackage.IMPLEMENTS__IMPLEMENTS:
        setImplements((JvmType)newValue);
        return;
      case ProcessorDslPackage.IMPLEMENTS__ONLY_POJOS:
        getOnlyPojos().clear();
        getOnlyPojos().addAll((Collection<? extends PojoEntity>)newValue);
        return;
      case ProcessorDslPackage.IMPLEMENTS__ONLY_DAOS:
        getOnlyDaos().clear();
        getOnlyDaos().addAll((Collection<? extends PojoDao>)newValue);
        return;
      case ProcessorDslPackage.IMPLEMENTS__EXCEPT_POJOS:
        getExceptPojos().clear();
        getExceptPojos().addAll((Collection<? extends PojoEntity>)newValue);
        return;
      case ProcessorDslPackage.IMPLEMENTS__EXCEPT_DAOS:
        getExceptDaos().clear();
        getExceptDaos().addAll((Collection<? extends PojoDao>)newValue);
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
      case ProcessorDslPackage.IMPLEMENTS__DIRECTIVES:
        getDirectives().clear();
        return;
      case ProcessorDslPackage.IMPLEMENTS__IMPLEMENTS:
        setImplements((JvmType)null);
        return;
      case ProcessorDslPackage.IMPLEMENTS__ONLY_POJOS:
        getOnlyPojos().clear();
        return;
      case ProcessorDslPackage.IMPLEMENTS__ONLY_DAOS:
        getOnlyDaos().clear();
        return;
      case ProcessorDslPackage.IMPLEMENTS__EXCEPT_POJOS:
        getExceptPojos().clear();
        return;
      case ProcessorDslPackage.IMPLEMENTS__EXCEPT_DAOS:
        getExceptDaos().clear();
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
      case ProcessorDslPackage.IMPLEMENTS__DIRECTIVES:
        return directives != null && !directives.isEmpty();
      case ProcessorDslPackage.IMPLEMENTS__IMPLEMENTS:
        return implements_ != null;
      case ProcessorDslPackage.IMPLEMENTS__ONLY_POJOS:
        return onlyPojos != null && !onlyPojos.isEmpty();
      case ProcessorDslPackage.IMPLEMENTS__ONLY_DAOS:
        return onlyDaos != null && !onlyDaos.isEmpty();
      case ProcessorDslPackage.IMPLEMENTS__EXCEPT_POJOS:
        return exceptPojos != null && !exceptPojos.isEmpty();
      case ProcessorDslPackage.IMPLEMENTS__EXCEPT_DAOS:
        return exceptDaos != null && !exceptDaos.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ImplementsImpl
