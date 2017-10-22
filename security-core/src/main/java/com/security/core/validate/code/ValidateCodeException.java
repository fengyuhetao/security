package com.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by HT on 2017/10/12.

/**
 * @author HT
 * @create 2017-10-12 15:52
 **/
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -7889376983125229775L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
