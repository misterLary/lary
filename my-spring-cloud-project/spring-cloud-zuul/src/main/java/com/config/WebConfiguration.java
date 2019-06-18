package com.config;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebConfiguration {

    @Bean
    public RemoteIpFilter remoteIpFilter(){
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName","paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }

    public class MyFilter implements Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            HttpServletResponse res = (HttpServletResponse) servletResponse;
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            String origin = req.getHeader("Origin");
            if (!org.springframework.util.StringUtils.isEmpty(origin)) {
                //带cookie的时候，origin必须是全匹配，不能使用*
                res.addHeader("Access-Control-Allow-Origin", origin);
            }
            res.addHeader("Access-Control-Allow-Methods", "*");
            String headers = req.getHeader("Access-Control-Request-Headers");
            // 支持所有自定义头
            if (!org.springframework.util.StringUtils.isEmpty(headers)) {
                res.addHeader("Access-Control-Allow-Headers", headers);
            }
            res.addHeader("Access-Control-Max-Age", "3600");
            // enable cookie
            res.addHeader("Access-Control-Allow-Credentials", "true");
            filterChain.doFilter(req, res);
        }

        @Override
        public void destroy() {

        }
    }

}