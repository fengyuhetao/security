package com.security.core.properties;/**
 * Created by HT on 2017/10/15.
 */

import lombok.Data;

/**
 * @author HT
 * @create 2017-10-15 18:38
 **/
@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();
}
