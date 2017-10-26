package com.security.core.social.QQ.api.impl;/**
 * Created by HT on 2017/10/25.
 */

import com.security.core.social.QQ.api.QQ;
import com.security.core.social.QQ.api.QQUserInfo;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @author HT
 * @create 2017-10-25 13:47
 **/
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ{
//    QQImpl 多实例对象 每个用户对应有个oaccesstoken
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);

        this.openId = StringUtils.substringBetween(result, "\"openid\":", "}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);

        String result = getRestTemplate().getForObject(url, String.class);

        QQUserInfo qqUserInfo = null;
        try {
            qqUserInfo.setOpenId(openId);
            qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
            return qqUserInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户数据失败");
        }
    }
}
