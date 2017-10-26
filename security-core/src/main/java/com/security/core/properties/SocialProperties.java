package com.security.core.properties;/**
 * Created by HT on 2017/10/25.
 */

import lombok.Data;

/**
 * @author HT
 * @create 2017-10-25 15:53
 **/
@Data
public class SocialProperties {
        private QQProperties qq = new QQProperties();

        private String filterProcessUrl = "/auth";
}
