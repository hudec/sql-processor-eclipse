package org.sqlproc.meta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.common.types.access.ClasspathTypeProviderFactory;
import org.eclipse.xtext.common.types.access.impl.ClasspathTypeProvider;
import org.eclipse.xtext.common.types.access.impl.TypeResourceServices;
import org.sqlproc.plugin.lib.resolver.PojoClassLoader;
import org.sqlproc.plugin.lib.resolver.ProcessorClassLoader;
import org.sqlproc.plugin.lib.util.MainUtils;

import com.google.inject.Inject;

public class ProcessorMetaClasspathTypeProviderFactory extends ClasspathTypeProviderFactory implements PojoClassLoader {

    protected Logger LOGGER = Logger.getLogger(ProcessorMetaClasspathTypeProviderFactory.class);

    @Inject
    ProcessorClassLoader processorClassLoader;

    private Map<ResourceSet, ClassLoader> loaders = new HashMap<>();

    @Inject
    public ProcessorMetaClasspathTypeProviderFactory(ClassLoader classLoader, TypeResourceServices services) {
        super(classLoader, services);
    }

    @Override
    protected ClasspathTypeProvider createClasspathTypeProvider(ResourceSet resourceSet) {
        ClasspathTypeProvider classpathTypeProvider = super.createClasspathTypeProvider(resourceSet);
        processorClassLoader.setPojoClassLoader(this);
        loaders.put(resourceSet, getClassLoader(resourceSet));
        return classpathTypeProvider;
    }

    @Override
    public Class<?> loadClass(Resource resource, String name) {
        LOGGER.info("loadClass, resource=" + resource + ", name=" + name);
        if (resource == null)
            return null;
        ResourceSet resourceSet = resource.getResourceSet();
        ClassLoader classLoader = loaders.get(resourceSet);
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
        String sResource = resource.getURI().toString();
        int ix = sResource.lastIndexOf(File.separator);
        String source = (ix >= 0) ? sResource.substring(0, ix + 1) : "";
        File file = new File(MainUtils.getFile(source, filename));
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
        }
        LOGGER.warn("Can't find file '" + filename + "' in project");
        return null;
    }
}
