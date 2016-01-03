package org.sqlproc.dsl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.SimpleAttributeResolver;
import org.sqlproc.dsl.processorDsl.AnnotatedEntity;

import com.google.inject.Inject;

public class ProcessorNameProvider extends IQualifiedNameProvider.AbstractImpl {

    @Inject
    private IQualifiedNameConverter qualifiedNameConverter;

    public QualifiedName getFullyQualifiedName(EObject obj) {
        // System.out.println("XXXX1 " + obj);
        if (obj instanceof AnnotatedEntity) {
            obj = ((AnnotatedEntity) obj).getEntity();
        }
        if (obj == null)
            return null;
        // System.out.println("XXXX2 " + obj);
        // if (obj instanceof Entity) {
        // Entity e = (Entity) obj;
        // System.out.println("XXXX3 " + e.getName());
        // }
        String name = SimpleAttributeResolver.NAME_RESOLVER.apply(obj);
        // System.out.println("YYYY1 " + name);
        if (name == null)
            return null;
        QualifiedName result = qualifiedNameConverter.toQualifiedName(name);
        // System.out.println("YYYY2 " + result);
        return result;
    }
}
