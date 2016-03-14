package org.sqlproc.meta.ui.scoping;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.xtext.common.types.access.jdt.IJdtTypeProvider;
import org.eclipse.xtext.common.types.access.jdt.JdtTypeProviderFactory;
import org.sqlproc.plugin.lib.resolver.PojoClassLoader;
import org.sqlproc.plugin.lib.resolver.ProcessorClassLoader;

import com.google.inject.Inject;

public class ProcessorMetaJdtTypeProviderFactory extends JdtTypeProviderFactory implements PojoClassLoader {

    protected Logger LOGGER = Logger.getLogger(ProcessorMetaJdtTypeProviderFactory.class);

    @Inject
    ProcessorClassLoader processorClassLoader;

    private Map<ResourceSet, URLClassLoader> loaders = new HashMap<>();

    @Override
    protected IJdtTypeProvider createJdtTypeProvider(IJavaProject javaProject, ResourceSet resourceSet) {
        IJdtTypeProvider jdtTypeProvider = super.createJdtTypeProvider(javaProject, resourceSet);
        processorClassLoader.setPojoClassLoader(this);
        if (javaProject != null && resourceSet != null) {
            try {
                loaders.put(resourceSet, getProjectClassLoader(javaProject));
            } catch (CoreException e) {
                LOGGER.warn("Can't get project class loader for '" + javaProject + "'", e);
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

    @Override
    public Class<?> loadClass(Resource resource, String name) {
        LOGGER.info("loadClass, resource=" + resource + ", name=" + name);
        if (resource == null)
            return null;
        ResourceSet resourceSet = resource.getResourceSet();
        URLClassLoader classLoader = loaders.get(resourceSet);
        if (classLoader == null) {
            LOGGER.warn("Can't find classloader for '" + resource + "'");
            return null;
        }
        try {
            return classLoader.loadClass(name);
        } catch (ClassNotFoundException e) {
            LOGGER.warn("Can't load class for '" + name + "': " + e.getMessage());
            return null;
        }
    }

    @Override
    public InputStream loadFile(Resource resource, String filename) {
        if (resource == null)
            return null;
        URI uri = resource.getURI();
        if (uri.isPlatformResource()) {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IFile modelFile = root.getFile(new Path(uri.toPlatformString(false)));
            IContainer modelParent = modelFile.getParent();
            if (modelParent instanceof IFolder) {
                IFolder modelDir = (IFolder) modelParent;
                IFile file = modelDir.getFile(filename);
                try {
                    return file.getContents();
                } catch (CoreException e) {
                    LOGGER.warn("Can't handle file '" + filename + "': " + e.getMessage());
                }
            }
        }
        LOGGER.warn("Can't find file '" + filename + "' in project");
        return null;
    }
}
