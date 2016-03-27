package org.sqlproc.model.generator;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmSpecializedTypeReference;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmUnknownTypeReference;
import org.eclipse.xtext.xbase.compiler.ErrorSafeExtensions;
import org.eclipse.xtext.xbase.compiler.TypeReferenceSerializer;
import org.eclipse.xtext.xbase.compiler.output.ErrorTreeAppendable;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.sqlproc.model.generator.BrokenTypeRefDetector;

@SuppressWarnings("all")
public class ProcessorModelErrorSafeExtensions extends ErrorSafeExtensions {
  @Inject
  @Extension
  private TypeReferenceSerializer _typeReferenceSerializer;
  
  @Override
  protected ITreeAppendable openErrorAppendable(final ITreeAppendable parent, final ITreeAppendable child) {
    ITreeAppendable _xifexpression = null;
    if ((!(child instanceof ErrorTreeAppendable))) {
      ErrorTreeAppendable _errorChild = parent.errorChild();
      _xifexpression = _errorChild.append(" ");
    } else {
      _xifexpression = child;
    }
    return _xifexpression;
  }
  
  @Override
  protected ITreeAppendable closeErrorAppendable(final ITreeAppendable parent, final ITreeAppendable child) {
    ITreeAppendable _xblockexpression = null;
    {
      boolean _and = false;
      if (!(child instanceof ErrorTreeAppendable)) {
        _and = false;
      } else {
        boolean _notEquals = (!Objects.equal(child, parent));
        _and = _notEquals;
      }
      if (_and) {
        child.append("");
      }
      _xblockexpression = parent;
    }
    return _xblockexpression;
  }
  
  @Override
  public void serializeSafely(final JvmTypeReference typeRef, final String surrogateType, final ITreeAppendable appendable) {
    boolean _or = false;
    boolean _equals = Objects.equal(typeRef, null);
    if (_equals) {
      _or = true;
    } else {
      JvmType _type = typeRef.getType();
      boolean _equals_1 = Objects.equal(_type, null);
      _or = _equals_1;
    }
    if (_or) {
      boolean _matched = false;
      if (!_matched) {
        if (typeRef instanceof JvmSpecializedTypeReference) {
          _matched=true;
          JvmTypeReference _equivalent = ((JvmSpecializedTypeReference)typeRef).getEquivalent();
          this.serializeSafely(_equivalent, surrogateType, appendable);
        }
      }
      if (!_matched) {
        if (typeRef instanceof JvmUnknownTypeReference) {
          _matched=true;
          String _qualifiedName = ((JvmUnknownTypeReference)typeRef).getQualifiedName();
          appendable.append(_qualifiedName);
        }
      }
      if (!_matched) {
        {
          appendable.append("Object");
          final ITreeAppendable errorChild = this.openErrorAppendable(appendable, appendable);
          errorChild.append("type is \'null\'");
          this.closeErrorAppendable(appendable, errorChild);
        }
      }
    } else {
      BrokenTypeRefDetector _brokenTypeRefDetector = new BrokenTypeRefDetector();
      Boolean _accept = typeRef.<Boolean>accept(_brokenTypeRefDetector);
      if ((_accept).booleanValue()) {
        final ITreeAppendable errorChild = this.openErrorAppendable(appendable, appendable);
        try {
          EObject _eContainer = typeRef.eContainer();
          this._typeReferenceSerializer.serialize(typeRef, _eContainer, errorChild);
        } catch (final Throwable _t) {
          if (_t instanceof Exception) {
            final Exception ignoreMe = (Exception)_t;
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        }
        this.closeErrorAppendable(appendable, errorChild);
      } else {
        EObject _eContainer_1 = typeRef.eContainer();
        this._typeReferenceSerializer.serialize(typeRef, _eContainer_1, appendable);
      }
    }
  }
}
