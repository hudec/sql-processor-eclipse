package org.sqlproc.plugin.lib.resolver;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;

public interface PojoResolver {

    public static final String POJO_ANNOTATION_CLASS = "org.sqlproc.engine.annotation.Pojo";

    Class<?> loadClass(String name, URI uri);

    public PropertyDescriptor[] getPropertyDescriptors(String name, URI uri);

    public Map<String, String> getOrders(String name, URI uri);

    List<Class<?>> getPojoClasses(URI uri);

    InputStream getFile(String filename, URI uri);
}
