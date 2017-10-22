package com.security.exception;/**
 * Created by HT on 2017/10/09.
 */

/**
 * @author HT
 * @create 2017-10-09 17:48
 **/
public class UserNotExistException extends RuntimeException {

    private String id;

    private static final long serialVersionUID = -5611218196167034498L;

    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
