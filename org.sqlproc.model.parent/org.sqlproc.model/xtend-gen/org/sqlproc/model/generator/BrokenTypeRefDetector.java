package org.sqlproc.model.generator;

import com.google.common.base.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.common.types.JvmCompoundTypeReference;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmTypeConstraint;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmWildcardTypeReference;
import org.eclipse.xtext.common.types.util.AbstractTypeReferenceVisitor;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class BrokenTypeRefDetector extends AbstractTypeReferenceVisitor.InheritanceAware<Boolean> {
  @Override
  protected Boolean handleNullReference() {
    return Boolean.valueOf(true);
  }
  
  @Override
  public Boolean doVisitTypeReference(final JvmTypeReference it) {
    return Boolean.valueOf((Objects.equal(it.getType(), null) || it.getType().eIsProxy()));
  }
  
  @Override
  public Boolean doVisitCompoundTypeReference(final JvmCompoundTypeReference it) {
    return Boolean.valueOf(((this.doVisitTypeReference(it)).booleanValue() || IterableExtensions.<JvmTypeReference>exists(it.getReferences(), new Function1<JvmTypeReference, Boolean>() {
      @Override
      public Boolean apply(final JvmTypeReference it) {
        return BrokenTypeRefDetector.this.visit(it);
      }
    })));
  }
  
  @Override
  public Boolean doVisitParameterizedTypeReference(final JvmParameterizedTypeReference it) {
    return Boolean.valueOf(((this.doVisitTypeReference(it)).booleanValue() || IterableExtensions.<JvmTypeReference>exists(it.getArguments(), new Function1<JvmTypeReference, Boolean>() {
      @Override
      public Boolean apply(final JvmTypeReference it) {
        return BrokenTypeRefDetector.this.visit(it);
      }
    })));
  }
  
  @Override
  public Boolean doVisitWildcardTypeReference(final JvmWildcardTypeReference it) {
    EList<JvmTypeConstraint> _constraints = it.getConstraints();
    final Function1<JvmTypeConstraint, Boolean> _function = new Function1<JvmTypeConstraint, Boolean>() {
      @Override
      public Boolean apply(final JvmTypeConstraint it) {
        JvmTypeReference _typeReference = it.getTypeReference();
        return BrokenTypeRefDetector.this.visit(_typeReference);
      }
    };
    return Boolean.valueOf(IterableExtensions.<JvmTypeConstraint>exists(_constraints, _function));
  }
}
