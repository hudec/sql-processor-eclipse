package org.sqlproc.plugin.lib.resolver;

import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.ResourceSet;

import com.google.inject.Singleton;

@Singleton
public class ProcessorClassLoaders {

    Map<ResourceSet, URLClassLoader> loaders = new HashMap<>();

    public Map<ResourceSet, URLClassLoader> getLoaders() {
        return loaders;
    }
}
