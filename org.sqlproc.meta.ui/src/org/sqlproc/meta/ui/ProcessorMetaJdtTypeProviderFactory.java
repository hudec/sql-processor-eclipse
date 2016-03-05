package org.sqlproc.meta.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.xtext.common.types.access.jdt.IJdtTypeProvider;
import org.eclipse.xtext.common.types.access.jdt.JdtTypeProviderFactory;
import org.sqlproc.plugin.lib.resolver.ProcessorClassLoaders;

import com.google.inject.Inject;

public class ProcessorMetaJdtTypeProviderFactory extends JdtTypeProviderFactory {

    protected Logger LOGGER = Logger.getLogger(ProcessorMetaJdtTypeProviderFactory.class);

    @Inject
    ProcessorClassLoaders processorMetaClassLoaders;

    @Override
    protected IJdtTypeProvider createJdtTypeProvider(IJavaProject javaProject, ResourceSet resourceSet) {
        IJdtTypeProvider jdtTypeProvider = super.createJdtTypeProvider(javaProject, resourceSet);
        System.out.println("AAAAA for " + resourceSet + " and " + javaProject + " is " + jdtTypeProvider);
        System.out.println("DDDDDD " + processorMetaClassLoaders);
        if (javaProject != null && resourceSet != null) {
            try {
                processorMetaClassLoaders.getLoaders().put(resourceSet, getProjectClassLoader(javaProject));
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }
        return jdtTypeProvider;
    }

    private URLClassLoader getProjectClassLoader(final IJavaProject javaProject) throws CoreException {
        String[] classPathEntries = JavaRuntime.computeDefaultRuntimeClassPath(javaProject);
        List<URL> urlList = new ArrayList<URL>();
        for (int i = 0; i < classPathEntries.length; i++) {
            String entry = classPathEntries[i];
            IPath path = new Path(entry);
            URL url;
            try {
                url = path.toFile().toURI().toURL();
                urlList.add(url);
            } catch (MalformedURLException e) {
                LOGGER.warn("Can't accept URL for '" + path + "': " + e.getMessage());
            }
        }
        ClassLoader parentClassLoader = javaProject.getClass().getClassLoader();
        URL[] urls = (URL[]) urlList.toArray(new URL[urlList.size()]);
        URLClassLoader classLoader = new URLClassLoader(urls, parentClassLoader);
        return classLoader;
    }
}
