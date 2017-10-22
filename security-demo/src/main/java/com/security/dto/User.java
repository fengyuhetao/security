package com.security.dto;/**
 * Created by HT on 2017/10/06.
 */

import com.fasterxml.jackson.annotation.JsonView;
import com.security.validator.MyConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author HT
 * @create 2017-10-06 19:57
 **/
@Data
public class User {

    public interface UserSimpleView {};

    public interface UserDetailView extends UserSimpleView{};

    private String id;

    @MyConstraint(message = "haha")
    private String username;

    @NotBlank(message = "不能为空")
    private String password;

//    由于前段可能是app,可能是网页，时间的格式往往并不固定，所以返回数据时，一般返回时间戳
    @Past
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }
}
