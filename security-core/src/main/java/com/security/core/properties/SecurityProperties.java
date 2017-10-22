package com.security.core.properties;/**
 * Created by HT on 2017/10/11.
 */

import com.sun.xml.internal.bind.v2.TODO;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author HT
 * @create 2017-10-11 16:06
 **/
@ConfigurationProperties(prefix = "mysecurity")
@Data
public class SecurityProperties {
//    TODO browser可以初始化可以不初始化，但是code一定要初始化 ？
    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();
}
