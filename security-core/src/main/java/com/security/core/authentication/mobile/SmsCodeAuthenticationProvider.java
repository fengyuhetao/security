package com.security.core.authentication.mobile;/**
 * Created by HT on 2017/10/23.
 */

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author HT
 * @create 2017-10-23 10:32
 **/
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        未验证token
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        UserDetails user = userDetailsService.loadUserByUsername(String.valueOf(authentication.getPrincipal()));
        if(user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
//        已验证token
        SmsCodeAuthenticationToken authenticationTokenResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
        authenticationTokenResult.setDetails(authentication.getDetails());
        return authenticationTokenResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
