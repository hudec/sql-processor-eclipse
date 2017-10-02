package org.sqlproc.model.generator;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import org.eclipse.xtext.common.types.JvmSpecializedTypeReference;
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
      _xifexpression = parent.errorChild().append(" ");
    } else {
      _xifexpression = child;
    }
    return _xifexpression;
  }
  
  @Override
  protected ITreeAppendable closeErrorAppendable(final ITreeAppendable parent, final ITreeAppendable child) {
    ITreeAppendable _xblockexpression = null;
    {
      if (((child instanceof ErrorTreeAppendable) && (!Objects.equal(child, parent)))) {
        child.append("");
      }
      _xblockexpression = parent;
    }
    return _xblockexpression;
  }
  
  @Override
  public void serializeSafely(final JvmTypeReference typeRef, final String surrogateType, final ITreeAppendable appendable) {
    if ((Objects.equal(typeRef, null) || Objects.equal(typeRef.getType(), null))) {
      boolean _matched = false;
      if (typeRef instanceof JvmSpecializedTypeReference) {
        _matched=true;
        this.serializeSafely(((JvmSpecializedTypeReference)typeRef).getEquivalent(), surrogateType, appendable);
      }
      if (!_matched) {
        if (typeRef instanceof JvmUnknownTypeReference) {
          _matched=true;
          appendable.append(((JvmUnknownTypeReference)typeRef).getQualifiedName());
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
          this._typeReferenceSerializer.serialize(typeRef, typeRef.eContainer(), errorChild);
        } catch (final Throwable _t) {
          if (_t instanceof Exception) {
            final Exception ignoreMe = (Exception)_t;
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        }
        this.closeErrorAppendable(appendable, errorChild);
      } else {
        this._typeReferenceSerializer.serialize(typeRef, typeRef.eContainer(), appendable);
      }
    }
  }
}
