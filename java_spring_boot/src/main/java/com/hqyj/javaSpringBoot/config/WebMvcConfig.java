package com.hqyj.javaSpringBoot.config;

import com.hqyj.javaSpringBoot.filter.RequestParamaFilter;
import com.hqyj.javaSpringBoot.interceptor.RequestViewInterceptor;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/11 12:25
 */
@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class WebMvcConfig implements WebMvcConfigurer {


    @Autowired
    private RequestViewInterceptor interceptor;
    @Autowired
    private ResourceConfigBean resourceConfigBean;
    @Bean
    public Connector connector() {
        Connector connector = new Connector();
        connector.setPort(resourceConfigBean.getHttpPort());
        connector.setScheme("http");
        return connector;
    }

    @Bean
    public ServletWebServerFactory serverFactory() {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        serverFactory.addAdditionalTomcatConnectors(connector());
        return serverFactory;
    }

    @Bean
    public FilterRegistrationBean<RequestParamaFilter> register() {
        FilterRegistrationBean<RequestParamaFilter> register = new FilterRegistrationBean<RequestParamaFilter>();
        register.setFilter(new RequestParamaFilter());
        return register;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String osName = System.getProperty("os.name");
        if (osName.startsWith("win")) {
            registry.addResourceHandler(resourceConfigBean.getRelativePathPattern()).addResourceLocations(
                    ResourceUtils.FILE_URL_PREFIX + resourceConfigBean.getLocationPathForWindows()
            );
        } else {
            registry.addResourceHandler(resourceConfigBean.getRelativePathPattern()).addResourceLocations(
                    ResourceUtils.FILE_URL_PREFIX + resourceConfigBean.getLocationPathForLinux()
            );
        }

    }
}
