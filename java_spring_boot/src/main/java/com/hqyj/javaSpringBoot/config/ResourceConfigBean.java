package com.hqyj.javaSpringBoot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/24 15:01
 */
@Component
@PropertySource("classpath:config/application.properties")
public class ResourceConfigBean {
    @Value("${server.http.port}")
    private int httpPort;
    @Value("${spring.resource.path}")
    private String relativePath;
    @Value("${spring.resource.path.pattern}")
    private String relativePathPattern;
    @Value("${spring.resource.folder.windows}")
    private String locationPathForWindows;
    @Value("${spring.resource.folder.linux}")
    private String locationPathForLinux;

    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getRelativePathPattern() {
        return relativePathPattern;
    }

    public void setRelativePathPattern(String relativePathPattern) {
        this.relativePathPattern = relativePathPattern;
    }

    public String getLocationPathForWindows() {
        return locationPathForWindows;
    }

    public void setLocationPathForWindows(String locationPathForWindows) {
        this.locationPathForWindows = locationPathForWindows;
    }

    public String getLocationPathForLinux() {
        return locationPathForLinux;
    }

    public void setLocationPathForLinux(String locationPathForLinux) {
        this.locationPathForLinux = locationPathForLinux;
    }
}
