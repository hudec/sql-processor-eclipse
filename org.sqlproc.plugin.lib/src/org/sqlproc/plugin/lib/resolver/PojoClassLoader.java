package org.sqlproc.plugin.lib.resolver;

import java.io.InputStream;

import org.eclipse.emf.ecore.resource.Resource;

public interface PojoClassLoader {
    public Class<?> loadClass(Resource resource, String name);

    public InputStream loadFile(Resource resource, String filename);
}
