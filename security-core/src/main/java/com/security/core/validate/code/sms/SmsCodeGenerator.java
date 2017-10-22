package com.security.core.validate.code.sms;/**
 * Created by HT on 2017/10/15.
 */

import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.ValidateCode;
import com.security.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author HT
 * @create 2017-10-15 19:51
 **/
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(HttpServletRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
