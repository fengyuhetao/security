package com.security.config;/**
 * Created by HT on 2017/10/09.
 */

import com.security.filter.TimeFilter;
import com.security.interceptor.TimeInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HT
 * @create 2017-10-09 19:41
 **/
//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private TimeInteceptor timeInteceptor;

//    @Override
//    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//            拦截异步请求
//        super.configureAsyncSupport(configurer);
//    }

    //    加载第三方filter
    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInteceptor);
    }

}
