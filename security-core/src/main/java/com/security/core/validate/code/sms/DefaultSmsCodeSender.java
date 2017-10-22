package com.security.core.validate.code.sms;/**
 * Created by HT on 2017/10/16.
 */

/**
 * @author HT
 * @create 2017-10-16 22:55
 **/
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机" + mobile + "发送短信验证码" + code);
    }
}
