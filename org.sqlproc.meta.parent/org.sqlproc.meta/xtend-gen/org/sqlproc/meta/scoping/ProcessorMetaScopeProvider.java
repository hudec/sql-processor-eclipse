/**
 * generated by Xtext 2.10.0-SNAPSHOT
 */
package org.sqlproc.meta.scoping;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;

/**
 * This class contains custom scoping description.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#scoping
 * on how and when to use it.
 */
@SuppressWarnings("all")
public class ProcessorMetaScopeProvider extends AbstractDeclarativeScopeProvider {
  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    return super.getScope(context, reference);
  }
}
