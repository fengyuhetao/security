package com.security.core.validate.code;/**
 * Created by HT on 2017/10/12.
 */

import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.image.ImageCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author HT~
 * @create 2017-10-12 15:49
 **/
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        if(StringUtils.isNotBlank(securityProperties.getCode().getImage().getUrl())) {
            String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ",");
            urls.addAll(Arrays.asList(configUrls));
        }

        urls.add("/authentication/form");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        if(null != httpServletRequest.getSession().getAttribute(ValidateCodeController.SESSION_KEY)) {
//            logger.info("session：" + ((ImageCode) httpServletRequest.getSession().getAttribute(ValidateCodeController.SESSION_KEY)).getCode());
//        }
//        if(StringUtils.equals("/authentication/form", httpServletRequest.getRequestURI())
//                    && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")) {

        boolean action = false;

        for (String url: urls) {
            if(pathMatcher.match(url, httpServletRequest.getRequestURI())) {
                action = true;
            }
        }

        if(action) {
            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");

        if(StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if(codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if(codeInSession.isExpried()) {
            sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已经过期");
        }

        if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
