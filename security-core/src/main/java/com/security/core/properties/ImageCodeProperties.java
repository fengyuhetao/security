package com.security.core.properties;/**
 * Created by HT on 2017/10/15.
 */

import lombok.Data;

/**
 * @author HT
 * @create 2017-10-15 18:37
 **/
@Data
public class ImageCodeProperties {
//    public ImageCodeProperties(){
//        setLength(4);
//    }

    private int width = 57;

    private int height = 23;

    private int length = 4;

    private int expireIn = 60;

    private String url;
}
