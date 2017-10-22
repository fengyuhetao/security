package com.security.core.validate.code;/**
 * Created by HT on 2017/10/12.
 */

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author HT
 * @create 2017-10-12 15:19
 **/
@Data
public class ValidateCode {
    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public ValidateCode(String code, Integer expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
