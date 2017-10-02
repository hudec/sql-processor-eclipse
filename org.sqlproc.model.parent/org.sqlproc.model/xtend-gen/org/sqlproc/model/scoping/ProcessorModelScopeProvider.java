/**
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.model.scoping;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.sqlproc.model.jvmmodel.ProcessorGeneratorUtils;
import org.sqlproc.model.processorModel.AnnotatedFeature;
import org.sqlproc.model.processorModel.DaoEntity;
import org.sqlproc.model.processorModel.Feature;
import org.sqlproc.model.processorModel.PojoEntity;
import org.sqlproc.model.processorModel.ProcessorModelPackage;
import org.sqlproc.model.scoping.AbstractProcessorModelScopeProvider;

/**
 * This class contains custom scoping description.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
@SuppressWarnings("all")
public class ProcessorModelScopeProvider extends AbstractProcessorModelScopeProvider {
  @Inject
  @Extension
  private ProcessorGeneratorUtils _processorGeneratorUtils;
  
  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    boolean _equals = Objects.equal(reference, ProcessorModelPackage.Literals.DIRECTIVE_PROPERTIES__FEATURES);
    if (_equals) {
      final PojoEntity pojo = EcoreUtil2.<PojoEntity>getContainerOfType(context, PojoEntity.class);
      final Function1<AnnotatedFeature, Feature> _function = (AnnotatedFeature it) -> {
        return it.getFeature();
      };
      final IScope scope = Scopes.scopeFor(ListExtensions.<AnnotatedFeature, Feature>map(pojo.getFeatures(), _function));
      return scope;
    } else {
      boolean _equals_1 = Objects.equal(reference, ProcessorModelPackage.Literals.DAO_DIRECTIVE_DISCRIMINATOR__ANCESTOR);
      if (_equals_1) {
        final DaoEntity dao = EcoreUtil2.<DaoEntity>getContainerOfType(context, DaoEntity.class);
        boolean _notEquals = (!Objects.equal(dao, null));
        if (_notEquals) {
          final PojoEntity pojo_1 = this._processorGeneratorUtils.getPojo(dao);
          final IScope scope_1 = Scopes.scopeFor(this._processorGeneratorUtils.allAttributes(pojo_1));
          return scope_1;
        }
      }
    }
    final IScope _scope = super.getScope(context, reference);
    return _scope;
  }
}
