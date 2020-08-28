package com.hqyj.javaSpringBoot.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * @author qb
 * @version 1.0
 * NO.1
 * come on
 * @date 2020/8/17 16:21
 */
@WebFilter(filterName = "requestParamaFilter",urlPatterns = "/**")
/*多个过滤器用@Order(1) 数值越小优先级高*/
public class RequestParamaFilter implements Filter {
    private final static Logger LOGGER= LoggerFactory.getLogger(RequestParamaFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("==========Init Request Param Filter===========");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("=======Do Request Param Filter========");
        HttpServletRequest httpRequest=(HttpServletRequest)servletRequest;
        HttpServletRequestWrapper wrapper=new HttpServletRequestWrapper(httpRequest){
            @Override
            public String getParameter(String name) {
                String value=httpRequest.getParameter(name);
                if(StringUtils.isNoneBlank(value)){
                    return value.replaceAll("fuck","****");
                }
                return super.getParameter(name);
            }

            @Override
            public String[] getParameterValues(String name) {
                String[] values=httpRequest.getParameterValues(name);
                if(values!=null){
                    for (int i = 0; i < values.length; i++) {
                        values[i]=values[i].replace("fuck","****");
                    }
                    return values;
                }
                return super.getParameterValues(name);
            }
        };
        filterChain.doFilter(wrapper,servletResponse);
    }

    @Override
    public void destroy() {
        LOGGER.debug("==========Destroy Request Param Filter===========");
    }
}
