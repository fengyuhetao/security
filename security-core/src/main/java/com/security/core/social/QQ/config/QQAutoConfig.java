package com.security.core.social.QQ.config;/**
 * Created by HT on 2017/10/25.
 */

import com.security.core.properties.SecurityProperties;
import com.security.core.social.QQ.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author HT
 * @create 2017-10-25 16:03
 **/
@Configuration
@ConditionalOnProperty(prefix = "mysecurity.social.qq", name = "appId")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        String providerId = securityProperties.getSocial().getQq().getProviderId();
        String appId = securityProperties.getSocial().getQq().getAppId();
        String appSecret = securityProperties.getSocial().getQq().getAppSecret();
        return new QQConnectionFactory(providerId, appId, appSecret);
    }
}
