package com.security.core.validate.code;/**
 * Created by HT on 2017/10/15.
 */

import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.image.ImageCodeGenerator;
import com.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HT
 * @create 2017-10-15 19:56
 **/
@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsCodeSender")
    public SmsCodeSender smsCodeGenerator() {
        return new DefaultSmsCodeSender();
    }
}
