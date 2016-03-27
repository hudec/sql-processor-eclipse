/**
 */
package org.sqlproc.model.processorModel.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.sqlproc.model.processorModel.DaoEntity;
import org.sqlproc.model.processorModel.ImplementsExtendsDirectiveOnlyDaos;
import org.sqlproc.model.processorModel.ProcessorModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Implements Extends Directive Only Daos</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.sqlproc.model.processorModel.impl.ImplementsExtendsDirectiveOnlyDaosImpl#getOnlyDaos <em>Only Daos</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ImplementsExtendsDirectiveOnlyDaosImpl extends ImplementsExtendsDirectiveImpl implements ImplementsExtendsDirectiveOnlyDaos
{
  /**
   * The cached value of the '{@link #getOnlyDaos() <em>Only Daos</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOnlyDaos()
   * @generated
   * @ordered
   */
  protected EList<DaoEntity> onlyDaos;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ImplementsExtendsDirectiveOnlyDaosImpl()
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
    return ProcessorModelPackage.Literals.IMPLEMENTS_EXTENDS_DIRECTIVE_ONLY_DAOS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<DaoEntity> getOnlyDaos()
  {
    if (onlyDaos == null)
    {
      onlyDaos = new EObjectResolvingEList<DaoEntity>(DaoEntity.class, this, ProcessorModelPackage.IMPLEMENTS_EXTENDS_DIRECTIVE_ONLY_DAOS__ONLY_DAOS);
    }
    return onlyDaos;
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
      case ProcessorModelPackage.IMPLEMENTS_EXTENDS_DIRECTIVE_ONLY_DAOS__ONLY_DAOS:
        return getOnlyDaos();
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
      case ProcessorModelPackage.IMPLEMENTS_EXTENDS_DIRECTIVE_ONLY_DAOS__ONLY_DAOS:
        getOnlyDaos().clear();
        getOnlyDaos().addAll((Collection<? extends DaoEntity>)newValue);
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
      case ProcessorModelPackage.IMPLEMENTS_EXTENDS_DIRECTIVE_ONLY_DAOS__ONLY_DAOS:
        getOnlyDaos().clear();
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
      case ProcessorModelPackage.IMPLEMENTS_EXTENDS_DIRECTIVE_ONLY_DAOS__ONLY_DAOS:
        return onlyDaos != null && !onlyDaos.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ImplementsExtendsDirectiveOnlyDaosImpl
