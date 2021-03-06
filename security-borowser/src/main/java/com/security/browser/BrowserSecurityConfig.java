package com.security.browser;/**
 * Created by HT on 2017/10/10.
 */

import com.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.SmsCodeFilter;
import com.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author HT
 * @create 2017-10-10 22:01
 **/
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler securityAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler securityAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter filter = new ValidateCodeFilter();
        filter.setAuthenticationFailureHandler(securityAuthenticationFailureHandler) ;
        filter.setSecurityProperties(securityProperties);
        filter.afterPropertiesSet();

        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(securityAuthenticationFailureHandler) ;
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

        //        http.httpBasic()
        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
            .apply(springSocialConfigurer)
                .and()
            .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(securityAuthenticationSuccessHandler)
                .failureHandler(securityAuthenticationFailureHandler)
                .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
            .authorizeRequests()
                .antMatchers("/security-signIn.html", "/user/regist", "/code/*", "/authentication/require", "/authentication/mobile", securityProperties.getBrowser().getLoginPage(), securityProperties.getBrowser().getSignUpUrl()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .csrf()
                .disable()
            .apply(smsCodeAuthenticationSecurityConfig);
    }
}
