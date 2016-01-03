package org.sqlproc.dsl.resolver;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

public interface PojoResolver {

    public static final String POJO_ANNOTATION_CLASS = "org.sqlproc.engine.annotation.Pojo";

    Class<?> loadClass(String name, URI uri);

    public PropertyDescriptor[] getPropertyDescriptors(String name, URI uri);

    boolean isResolvePojo(EObject model);

    List<Class<?>> getPojoClasses(URI uri);

    InputStream getFile(String filename, URI uri);
}
