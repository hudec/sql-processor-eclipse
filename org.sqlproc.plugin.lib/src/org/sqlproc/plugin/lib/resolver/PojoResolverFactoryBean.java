package org.sqlproc.plugin.lib.resolver;

import com.google.inject.Singleton;

@Singleton
public class PojoResolverFactoryBean implements PojoResolverFactory {
    private PojoResolver pojoResolver;

    public PojoResolver getPojoResolver() {
        return pojoResolver;
    }

    public void setPojoResolver(PojoResolver pojoResolver) {
        this.pojoResolver = pojoResolver;
    }
}
