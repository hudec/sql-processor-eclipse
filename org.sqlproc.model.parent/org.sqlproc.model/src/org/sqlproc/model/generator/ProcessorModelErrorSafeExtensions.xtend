package org.sqlproc.model.generator

import org.eclipse.xtext.xbase.compiler.ErrorSafeExtensions
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable
import org.eclipse.xtext.xbase.compiler.output.ErrorTreeAppendable
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.common.types.JvmSpecializedTypeReference
import org.eclipse.xtext.common.types.JvmUnknownTypeReference
import org.eclipse.xtext.common.types.util.AbstractTypeReferenceVisitor
import org.eclipse.xtext.common.types.JvmCompoundTypeReference
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference
import org.eclipse.xtext.common.types.JvmWildcardTypeReference
import com.google.inject.Inject
import org.eclipse.xtext.xbase.compiler.TypeReferenceSerializer
import org.eclipse.emf.ecore.EObject

class ProcessorModelErrorSafeExtensions extends ErrorSafeExtensions {
	
	@Inject extension TypeReferenceSerializer 
	
	override protected openErrorAppendable(ITreeAppendable parent, ITreeAppendable child) {
		if(!(child instanceof ErrorTreeAppendable))
			parent.errorChild().append(" ")
		else 
			child
	}

	override protected closeErrorAppendable(ITreeAppendable parent, ITreeAppendable child) {
		if(child instanceof ErrorTreeAppendable && child != parent)
			child.append("")
		parent
	}
	
	override void serializeSafely(JvmTypeReference typeRef, String surrogateType, ITreeAppendable appendable) {
		if(typeRef == null || typeRef.type == null) {
			switch(typeRef) {
				JvmSpecializedTypeReference: typeRef.equivalent.serializeSafely(surrogateType, appendable)
				JvmUnknownTypeReference: appendable.append(typeRef.qualifiedName)
				default: {
					appendable.append('Object')
					val errorChild = appendable.openErrorAppendable(appendable)
					errorChild.append("type is 'null'")
					appendable.closeErrorAppendable(errorChild)
				}
			}
		} else {
			if(typeRef.accept(new BrokenTypeRefDetector)) {
				val errorChild = appendable.openErrorAppendable(appendable)
				try {
					serialize(typeRef, typeRef.eContainer, errorChild)
				} catch(Exception ignoreMe) {}
				appendable.closeErrorAppendable(errorChild)
//				if(surrogateType != null) 
//					appendable.append(surrogateType)
			} else {
				serialize(typeRef, typeRef.eContainer, appendable)
			}
		}
	}
}

class BrokenTypeRefDetector extends AbstractTypeReferenceVisitor.InheritanceAware<Boolean> {
	
	override protected handleNullReference() {
		true
	}
	
	override doVisitTypeReference(JvmTypeReference it) {
		type==null || type.eIsProxy
	}
	
	override doVisitCompoundTypeReference(JvmCompoundTypeReference it) {
		doVisitTypeReference || references.exists[visit]
	}
	
	override doVisitParameterizedTypeReference(JvmParameterizedTypeReference it) {
		doVisitTypeReference || arguments.exists[visit]
	}
	
	override doVisitWildcardTypeReference(JvmWildcardTypeReference it) {
		constraints.exists[typeReference.visit]
	}
	
}