package com.mplatform.framework.base.minsights.api;

/**
 * Created by mohamed.abdulkadar on 6/18/2017.
 */
public class LoginRequestVO {

    String user = null;
    String password = null;

    public LoginRequestVO(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
