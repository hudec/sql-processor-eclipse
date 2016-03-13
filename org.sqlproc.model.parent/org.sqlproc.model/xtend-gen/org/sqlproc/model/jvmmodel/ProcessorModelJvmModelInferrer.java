package org.sqlproc.model.jvmmodel;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.lib.Extension;
import org.sqlproc.model.jvmmodel.DaoJvmModelInferrer;
import org.sqlproc.model.jvmmodel.EnumJvmModelInferrer;
import org.sqlproc.model.jvmmodel.PojoJvmModelInferrer;
import org.sqlproc.model.jvmmodel.ProcessorGeneratorUtils;
import org.sqlproc.model.processorModel.DaoEntity;
import org.sqlproc.model.processorModel.EnumEntity;
import org.sqlproc.model.processorModel.PojoEntity;

/**
 * <p>Infers a JVM model from the source model.</p>
 * 
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
@SuppressWarnings("all")
public class ProcessorModelJvmModelInferrer extends AbstractModelInferrer {
  /**
   * convenience API to build and initialize JVM types and their members.
   */
  @Inject
  @Extension
  private PojoJvmModelInferrer _pojoJvmModelInferrer;
  
  @Inject
  @Extension
  private EnumJvmModelInferrer _enumJvmModelInferrer;
  
  @Inject
  @Extension
  private DaoJvmModelInferrer _daoJvmModelInferrer;
  
  @Inject
  @Extension
  private ProcessorGeneratorUtils _processorGeneratorUtils;
  
  protected void _infer(final PojoEntity entity, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    this._pojoJvmModelInferrer._annotationTypesBuilder = this._annotationTypesBuilder;
    this._pojoJvmModelInferrer._typeReferenceBuilder = this._typeReferenceBuilder;
    this._pojoJvmModelInferrer.inferPojo(entity, acceptor, isPreIndexingPhase);
  }
  
  protected void _infer(final EnumEntity entity, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    this._enumJvmModelInferrer._annotationTypesBuilder = this._annotationTypesBuilder;
    this._enumJvmModelInferrer._typeReferenceBuilder = this._typeReferenceBuilder;
    this._enumJvmModelInferrer.inferEnum(entity, acceptor, isPreIndexingPhase);
  }
  
  protected void _infer(final DaoEntity entity, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    this._daoJvmModelInferrer._annotationTypesBuilder = this._annotationTypesBuilder;
    this._daoJvmModelInferrer._typeReferenceBuilder = this._typeReferenceBuilder;
    final String implPackage = this._processorGeneratorUtils.getImplPackage(entity);
    boolean _notEquals = (!Objects.equal(implPackage, null));
    if (_notEquals) {
      this._daoJvmModelInferrer.inferDaoIfx(entity, acceptor, isPreIndexingPhase);
      this._daoJvmModelInferrer.inferDao(entity, acceptor, isPreIndexingPhase, implPackage);
    } else {
      this._daoJvmModelInferrer.inferDao(entity, acceptor, isPreIndexingPhase, null);
    }
  }
  
  public void infer(final EObject entity, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    if (entity instanceof DaoEntity) {
      _infer((DaoEntity)entity, acceptor, isPreIndexingPhase);
      return;
    } else if (entity instanceof EnumEntity) {
      _infer((EnumEntity)entity, acceptor, isPreIndexingPhase);
      return;
    } else if (entity instanceof PojoEntity) {
      _infer((PojoEntity)entity, acceptor, isPreIndexingPhase);
      return;
    } else if (entity != null) {
      _infer(entity, acceptor, isPreIndexingPhase);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(entity, acceptor, isPreIndexingPhase).toString());
    }
  }
}
