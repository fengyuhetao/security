package com.security.core.validate.code.sms;/**
 * Created by HT on 2017/10/16.
 */

/**
 * @author HT
 * @create 2017-10-16 22:54
 **/
public interface SmsCodeSender {
    void send(String mobile, String code);
}
