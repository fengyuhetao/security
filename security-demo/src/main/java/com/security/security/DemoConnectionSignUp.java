package com.security.security;/**
 * Created by HT on 2017/10/26.
 */

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @author HT
 * @create 2017-10-26 11:14
 **/
@Component
public class DemoConnectionSignUp implements ConnectionSignUp{

//    根据社交用户信息默认创建用户并返回用户唯一标识
    @Override
    public String execute(Connection<?> connection) {
        return connection.getDisplayName();
    }
}
