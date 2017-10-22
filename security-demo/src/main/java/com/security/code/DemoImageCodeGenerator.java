package com.security.code;/**
 * Created by HT on 2017/10/15.
 */

import com.security.core.validate.code.image.ImageCode;
import com.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author HT
 * @create 2017-10-15 21:24
 **/
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(HttpServletRequest request) {
        System.out.println("生成验证码");
        return null;
    }
}
