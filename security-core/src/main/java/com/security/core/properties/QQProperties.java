package com.security.core.properties;/**
 * Created by HT on 2017/10/25.
 */

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author HT
 * @create 2017-10-25 15:52
 **/
@Data
public class QQProperties extends SocialProperties {

    private String providerId = "qq";
}
