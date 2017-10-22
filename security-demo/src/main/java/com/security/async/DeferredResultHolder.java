package com.security.async;/**
 * Created by HT on 2017/10/10.
 */

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HT
 * @create 2017-10-10 14:18
 **/
@Component
@Data
public class DeferredResultHolder {
    private Map<String, DeferredResult<String>> map = new HashMap<>();

}
