package com.security.util;

import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by HT on 2017/10/12.
 */
public class ClassUtilTest {
    @Test
    public void getAllClassByInterface() throws Exception {
        List<Class> list = ClassUtil.getAllClassByInterface(UserDetailsService.class);
        for (Class c: list) {
            System.out.println(c.getName());
        }
    }
}
