package org.sqlproc.plugin.lib.resolver;

public interface PojoResolverFactory {

    PojoResolver getPojoResolver();

    void setPojoResolver(PojoResolver pojoResolver);

}
