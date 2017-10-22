package com.security.service.impl;/**
 * Created by HT on 2017/10/09.
 */

import com.security.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author HT
 * @create 2017-10-09 12:03
 **/
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public void greeting() {
        System.out.println("hello");
    }
}
