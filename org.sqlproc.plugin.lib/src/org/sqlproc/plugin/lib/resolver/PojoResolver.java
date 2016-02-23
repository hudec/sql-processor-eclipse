package org.sqlproc.plugin.lib.resolver;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;

public interface PojoResolver {

    public static final String POJO_ANNOTATION_CLASS = "org.sqlproc.engine.annotation.Pojo";

    // TODO ProcessorMetaProposalProvider

    // ProcessorMetaValidator
    // DbResolverBean
    Class<?> loadClass(String name, URI uri);

    // ProcessorMetaValidator
    // ProcessorMetaProposalProvider
    public PropertyDescriptor[] getPropertyDescriptors(String name, URI uri);

    // ProcessorMetaValidator
    // ProcessorMetaProposalProvider
    public Map<String, String> getOrders(String name, URI uri);

    // ProcessorMetaTemplateContextType.PojoDefinitionsResolver
    // ProcessorModelTemplateContextType.PojoDefinitionsResolver
    List<Class<?>> getPojoClasses(URI uri);

    // DbResolverBean
    InputStream getFile(String filename, URI uri);
}
