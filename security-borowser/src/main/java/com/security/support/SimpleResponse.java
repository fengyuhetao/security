package com.security.support;/**
 * Created by HT on 2017/10/11.
 */

import lombok.Data;

/**
 * @author HT
 * @create 2017-10-11 15:57
 **/
@Data
public class SimpleResponse {
    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }
}
