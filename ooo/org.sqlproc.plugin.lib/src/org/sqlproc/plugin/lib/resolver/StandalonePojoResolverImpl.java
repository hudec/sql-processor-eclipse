package org.sqlproc.plugin.lib.resolver;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.sqlproc.plugin.lib.util.MainUtils;

import com.google.inject.Singleton;

@Singleton
public class StandalonePojoResolverImpl implements PojoResolver {

    protected Logger LOGGER = Logger.getLogger(StandalonePojoResolverImpl.class);

    String source;

    public StandalonePojoResolverImpl(String source) {
        this.source = source;
    }

    @Override
    public Class<?> loadClass(String name, URI uri) {
        LOGGER.info("loadClass, name=" + name + ", uri=" + uri);
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
    public Map<String, String> getOrders(String name, URI uri) {
        Class<?> beanClass = loadClass(name, uri);
        if (beanClass == null)
            return null;

        Map<String, String> orders = new HashMap<>();
        while (beanClass != null) {
            Field[] fields = beanClass.getFields();
            for (Field f : fields) {
                if (Modifier.isStatic(f.getModifiers())) {
                    try {
                        if (f.getType() == int.class)
                            orders.put(f.getName(), "" + f.getInt(null));
                        else if (f.getType() == String.class)
                            orders.put(f.getName(), (String) f.get(null));
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                    }
                }
            }
            beanClass = beanClass.getSuperclass();
        }

        beanClass = loadClass(name, uri);
        Class<?> orderBeanClass = loadClass(name + "$Order", uri);
        while (orderBeanClass != null && orderBeanClass.isEnum()) {
            for (Object en : orderBeanClass.getEnumConstants()) {
                String ename = en.toString();
                orders.put(ename, ename);
            }

            beanClass = beanClass.getSuperclass();
            if (beanClass == null)
                break;
            orderBeanClass = loadClass(beanClass.getName() + "$Order", uri);
        }
        return orders;
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
