package com.security.controller;/**
 * Created by HT on 2017/10/06.
 */

import com.fasterxml.jackson.annotation.JsonView;
import com.security.dto.User;
import com.security.dto.UserQueryCondition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HT
 * @create 2017-10-06 19:54
 **/
@RestController
public class UserController {
    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/user/regist")
    public void regist(User user, HttpServletRequest request) {
//        不管是注册用户还是绑定用户，都会拿到一个用户唯一标识
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest((request)));
    }

//    @GetMapping("/user")
//    public List<User> query(@RequestParam String  username) {
//        List<User> users = new ArrayList<>();
//        users.add(new User());
//        users.add(new User());
//        users.add(new User());
//        return users;
//    }

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails authentication) {
//      public Object getCurrentUser(Authentication authentication) {
//        return SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    @GetMapping("/user")
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务")
    public List<User> query(UserQueryCondition condition, @PageableDefault(page = 0, size = 10, sort = "username,asc") Pageable pageable) {
//        System.out.println(condition);
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @JsonView(User.UserDetailView.class)
    @GetMapping("/user/{id:\\d+}")
    public User getInfo(@PathVariable String id) {
//        throw new UserNotExistException(id);
        System.out.println("进入");
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @PostMapping("/user")
    public User create(@Valid @RequestBody User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(
                    error -> System.out.println(error.getDefaultMessage())
            );
        }
        System.out.println(user);
        user.setId("1");
        return user;
    }

    @PutMapping("/user/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(
                    error -> {
                        FieldError fieldError = (FieldError) error;
                        String message = fieldError.getField() + " " + error.getDefaultMessage();
                        System.out.println(message);
                    }
            );
        }
        System.out.println(user);
        user.setId("1");
        return user;
    }

    @DeleteMapping("/user/{id:\\d+}")
    public void delete(@ApiParam(value = "用户id") @PathVariable String id) {
        System.out.println(id);
    }
}
