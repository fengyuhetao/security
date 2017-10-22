package com.security.interceptor;/**
 * Created by HT on 2017/10/09.
 */

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author HT
 * @create 2017-10-09 19:48
 **/
//@Component
public class TimeInteceptor implements HandlerInterceptor {

//    拦截器会拦截所有调用的controller里边的方法，不光是自己写的，还是框架自带的

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        控制器方法调用之前
        System.out.println("preHandler");
        System.out.println(((HandlerMethod)o).getBean().getClass().getName());
        System.out.println(((HandlerMethod)o).getMethod().getName());
        httpServletRequest.setAttribute("start", new Date().getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        控制器方法调用之后，如果抛出异常，该方法不执行
        System.out.println("postHandler");

        Long start = (Long) httpServletRequest.getAttribute("start");
        System.out.println("time imterceptor: " + (new Date().getTime() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//        一定会执行
        System.out.println("afterHandler");
        Long start = (Long) httpServletRequest.getAttribute("start");
        System.out.println("time imterceptor: " + (new Date().getTime() - start));
//        该异常由于被ControllerExceptionHandler处理掉了，所以获取不到
        System.out.println("exception: " + e);
    }
}
