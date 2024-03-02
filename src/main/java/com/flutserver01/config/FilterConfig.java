package com.flutserver01.config;

import com.flutserver01.filter.SecurityFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean<SecurityFilter> filterConfig01 () {
//        FilterRegistrationBean<SecurityFilter> bean = new FilterRegistrationBean<>(new SecurityFilter());
//        bean.addUrlPatterns("/*");
//        bean.setOrder(0);
//        return bean;
//    }

}

