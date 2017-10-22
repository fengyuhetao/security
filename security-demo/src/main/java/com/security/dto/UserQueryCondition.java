package com.security.dto;/**
 * Created by HT on 2017/10/06.
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author HT
 * @create 2017-10-06 20:13
 **/
@Data
public class UserQueryCondition {
    @ApiModelProperty(value = "用户名")
    private String username;

    private int age;

    private int ageTo;

    private String xxx;
}
