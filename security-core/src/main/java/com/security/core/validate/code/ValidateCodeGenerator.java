package com.security.core.validate.code;/**
 * Created by HT on 2017/10/15.
 */

import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author HT
 * @create 2017-10-15 19:51
 **/
public interface ValidateCodeGenerator {
    ValidateCode generate(HttpServletRequest request);
}
