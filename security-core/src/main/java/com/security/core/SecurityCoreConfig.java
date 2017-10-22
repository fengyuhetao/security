package com.security.core;/**
 * Created by HT on 2017/10/11.
 */

import com.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author HT
 * @create 2017-10-11 16:09
 **/
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
