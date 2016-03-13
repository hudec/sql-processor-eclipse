package org.sqlproc.model.generator;

import com.google.common.base.Objects;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.common.types.JvmCompoundTypeReference;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmType;
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
    boolean _or = false;
    JvmType _type = it.getType();
    boolean _equals = Objects.equal(_type, null);
    if (_equals) {
      _or = true;
    } else {
      JvmType _type_1 = it.getType();
      boolean _eIsProxy = _type_1.eIsProxy();
      _or = _eIsProxy;
    }
    return Boolean.valueOf(_or);
  }
  
  @Override
  public Boolean doVisitCompoundTypeReference(final JvmCompoundTypeReference it) {
    boolean _or = false;
    Boolean _doVisitTypeReference = this.doVisitTypeReference(it);
    if ((_doVisitTypeReference).booleanValue()) {
      _or = true;
    } else {
      EList<JvmTypeReference> _references = it.getReferences();
      final Function1<JvmTypeReference, Boolean> _function = new Function1<JvmTypeReference, Boolean>() {
        @Override
        public Boolean apply(final JvmTypeReference it) {
          return BrokenTypeRefDetector.this.visit(it);
        }
      };
      boolean _exists = IterableExtensions.<JvmTypeReference>exists(_references, _function);
      _or = _exists;
    }
    return Boolean.valueOf(_or);
  }
  
  @Override
  public Boolean doVisitParameterizedTypeReference(final JvmParameterizedTypeReference it) {
    boolean _or = false;
    Boolean _doVisitTypeReference = this.doVisitTypeReference(it);
    if ((_doVisitTypeReference).booleanValue()) {
      _or = true;
    } else {
      EList<JvmTypeReference> _arguments = it.getArguments();
      final Function1<JvmTypeReference, Boolean> _function = new Function1<JvmTypeReference, Boolean>() {
        @Override
        public Boolean apply(final JvmTypeReference it) {
          return BrokenTypeRefDetector.this.visit(it);
        }
      };
      boolean _exists = IterableExtensions.<JvmTypeReference>exists(_arguments, _function);
      _or = _exists;
    }
    return Boolean.valueOf(_or);
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
