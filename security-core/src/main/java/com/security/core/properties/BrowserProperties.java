package com.security.core.properties;/**
 * Created by HT on 2017/10/11.
 */

import lombok.Data;

/**
 * @author HT
 * @create 2017-10-11 16:06
 **/
@Data
public class BrowserProperties {
    private String loginPage = "/security-singIn.html";

    private LoginType loginType = LoginType.JSON;

    private Integer rememberMeSeconds = 3600;

//    注册页面
    private String signUpUrl = "/security-signUp.html";
}
