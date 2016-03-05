package org.sqlproc.plugin.lib.resolver;

import org.eclipse.emf.common.util.URI;

public interface PojoClassLoader {
    Class<?> loadClass(String name, URI uri);
}
