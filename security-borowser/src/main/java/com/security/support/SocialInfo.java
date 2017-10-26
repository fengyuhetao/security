package com.security.support;/**
 * Created by HT on 2017/10/26.
 */

import lombok.Data;

/**
 * @author HT
 * @create 2017-10-26 10:31
 **/
@Data
public class SocialInfo {
    private String providerInfo;

    private String providerUserId;

    private String nickName;

    private String headImg;
}
