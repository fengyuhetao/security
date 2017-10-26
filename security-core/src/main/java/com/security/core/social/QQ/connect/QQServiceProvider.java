package com.security.core.social.QQ.connect;/**
 * Created by HT on 2017/10/25.
 */
import com.security.core.social.QQ.api.impl.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author HT
 * @create 2017-10-25 14:40
 **/
public class QQServiceProvider extends AbstractOAuth2ServiceProvider {
    private String appId;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOauth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public Object getApi(String accessToken) {
        return new QQImpl(accessToken, this.appId);
    }
}
