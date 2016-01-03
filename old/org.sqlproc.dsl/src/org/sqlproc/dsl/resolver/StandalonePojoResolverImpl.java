package org.sqlproc.dsl.resolver;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.sqlproc.dsl.property.ModelProperty;
import org.sqlproc.dsl.util.MainUtils;

import com.google.inject.Singleton;

@Singleton
public class StandalonePojoResolverImpl implements PojoResolver {

    protected Logger LOGGER = Logger.getLogger(StandalonePojoResolverImpl.class);

    ModelProperty modelProperty;
    String source;

    public StandalonePojoResolverImpl(ModelProperty modelProperty, String source) {
        this.modelProperty = modelProperty;
        this.source = source;
    }

    @Override
    public Class<?> loadClass(String name, URI uri) {
        try {
            Class<?> clazz = this.getClass().getClassLoader().loadClass(name);
            return clazz;
        } catch (ClassNotFoundException e) {
        }
        LOGGER.warn("Can't find class '" + name);
        return null;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors(String name, URI uri) {
        Class<?> beanClass = loadClass(name, uri);
        if (beanClass == null)
            return null;

        // Look up any cached descriptors for this bean class
        PropertyDescriptor[] descriptors = null;
        // descriptors = (PropertyDescriptor[]) descriptorsCache.get(beanClass);
        // if (descriptors != null) {
        // return (descriptors);
        // }

        // Introspect the bean and cache the generated descriptors
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(beanClass);
        } catch (IntrospectionException e) {
            return (new PropertyDescriptor[0]);
        }
        descriptors = beanInfo.getPropertyDescriptors();
        if (descriptors == null) {
            descriptors = new PropertyDescriptor[0];
        }

        // descriptorsCache.put(beanClass, descriptors);
        return descriptors;
    }

    @Override
    public boolean isResolvePojo(EObject model) {
        if (!modelProperty.isDoResolvePojo(model)) {
            return false;
        }
        return true;
    }

    @Override
    public List<Class<?>> getPojoClasses(URI uri) {
        return Collections.EMPTY_LIST;
    }

    @Override
    public InputStream getFile(String filename, URI uri) {
        File file = new File(MainUtils.getFile(source, filename));
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
        }
        LOGGER.warn("Can't find file '" + filename + "' in project");
        return null;
    }
}
