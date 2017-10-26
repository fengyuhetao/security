package com.security.core.social.QQ.connect;/**
 * Created by HT on 2017/10/25.
 */

import com.security.core.social.QQ.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author HT
 * @create 2017-10-25 15:13
 **/
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ>{
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
