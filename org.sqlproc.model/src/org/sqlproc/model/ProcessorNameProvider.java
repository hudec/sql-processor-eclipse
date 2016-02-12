package org.sqlproc.model;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.SimpleAttributeResolver;
import org.sqlproc.model.processorModel.AnnotatedEntity;

import com.google.inject.Inject;

public class ProcessorNameProvider extends IQualifiedNameProvider.AbstractImpl {

    @Inject
    private IQualifiedNameConverter qualifiedNameConverter;

    public QualifiedName getFullyQualifiedName(EObject obj) {
        // System.out.println("1111 " + obj);
        if (obj instanceof AnnotatedEntity) {
            obj = ((AnnotatedEntity) obj).getEntity();
        }
        // System.out.println("2222 " + obj);
        String name = SimpleAttributeResolver.NAME_RESOLVER.apply(obj);
        // System.out.println("3333 " + name);
        if (name == null)
            return null;
        QualifiedName result = qualifiedNameConverter.toQualifiedName(name);
        // System.out.println("4444 " + result);
        return result;
    }
}
