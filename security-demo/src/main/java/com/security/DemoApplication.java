package com.security;/**
 * Created by HT on 2017/10/06.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 启动类
 *
 * @author HT
 * @create 2017-10-06 17:27
 **/
@SpringBootApplication
@RestController
@EnableSwagger2
public class DemoApplication {

    public static void main(String [] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }
}
