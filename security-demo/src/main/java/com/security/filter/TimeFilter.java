package com.security.filter;
/**
 * Created by HT on 2017/10/09.
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @author HT
 * @create 2017-10-09 19:33
 **/
//@Component
//@Order(Integer.MAX_VALUE) 设置过滤顺序
public class TimeFilter implements Filter {
//    缺陷  只能获得请求和响应，无法获取该请求具体有哪个控制器或方法
// 来执行
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter start");
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("time filter:" + (new Date().getTime() - start));
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter detroy");
    }
}
