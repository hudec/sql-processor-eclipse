package org.sqlproc.plugin.lib.resolver;

import java.io.InputStream;

import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.Singleton;

@Singleton
public class ProcessorClassLoaders {

    PojoClassLoader pojoClassLoader;

    public PojoClassLoader getPojoClassLoader() {
        return pojoClassLoader;
    }

    public void setPojoClassLoader(PojoClassLoader pojoClassLoader) {
        this.pojoClassLoader = pojoClassLoader;
    }

    public Class<?> loadClass(Resource resource, String name) {
        return pojoClassLoader.loadClass(resource, name);
    }

    public InputStream getFile(Resource resource, String filename) {
        return pojoClassLoader.getFile(resource, filename);
    }
}
