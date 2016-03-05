package org.sqlproc.meta.ui;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.xtext.common.types.access.jdt.IJdtTypeProvider;
import org.eclipse.xtext.common.types.access.jdt.JdtTypeProviderFactory;

public class ProcessorMetaJdtTypeProviderFactory extends JdtTypeProviderFactory {

    @Override
    protected IJdtTypeProvider createJdtTypeProvider(IJavaProject javaProject, ResourceSet resourceSet) {
        IJdtTypeProvider jdtTypeProvider = super.createJdtTypeProvider(javaProject, resourceSet);
        System.out.println("AAAAA for " + resourceSet + " and " + javaProject + " is " + jdtTypeProvider);
        return jdtTypeProvider;
    }
}
