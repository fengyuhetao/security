package com.security.core.properties;/**
 * Created by HT on 2017/10/15.
 */

import lombok.Data;

/**
 * @author HT
 * @create 2017-10-15 18:37
 **/
@Data
public class SmsCodeProperties {
    private int length = 4;

    private int expireIn = 60;

    private String url;
}
