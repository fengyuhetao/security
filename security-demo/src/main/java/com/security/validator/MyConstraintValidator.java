package com.security.validator;/**
 * Created by HT on 2017/10/09.
 */

import com.security.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * @author HT
 * @create 2017-10-09 11:59
 **/
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("my validator init");
        helloService.greeting();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(o);
        return true;
    }

}
