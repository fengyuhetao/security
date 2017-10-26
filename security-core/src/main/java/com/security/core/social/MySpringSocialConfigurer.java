package com.security.core.social;/**
 * Created by HT on 2017/10/26.
 */

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author HT
 * @create 2017-10-26 09:04
 **/
public class MySpringSocialConfigurer extends SpringSocialConfigurer{
    private String filterProcessUrl;

    public MySpringSocialConfigurer(String filterProcessUrl) {
        this.filterProcessUrl = filterProcessUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessUrl);
        return super.postProcess(object);
    }
}
