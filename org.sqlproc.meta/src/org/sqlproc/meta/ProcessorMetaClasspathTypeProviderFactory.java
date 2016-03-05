package org.sqlproc.meta;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.common.types.access.ClasspathTypeProviderFactory;
import org.eclipse.xtext.common.types.access.impl.ClasspathTypeProvider;
import org.eclipse.xtext.common.types.access.impl.TypeResourceServices;

public class ProcessorMetaClasspathTypeProviderFactory extends ClasspathTypeProviderFactory {

    public ProcessorMetaClasspathTypeProviderFactory(ClassLoader classLoader, TypeResourceServices services) {
        super(classLoader, services);
    }

    @Override
    protected ClasspathTypeProvider createClasspathTypeProvider(ResourceSet resourceSet) {
        ClasspathTypeProvider classpathTypeProvider = super.createClasspathTypeProvider(resourceSet);
        // System.out.println(
        // "BBBBB for " + resourceSet + " and " + getClassLoader(resourceSet) + " is " + classpathTypeProvider);
        return classpathTypeProvider;
    }

}
