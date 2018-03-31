package com.mplatform.framework.data.minsights;

/**
 * Created by mohamed.abdulkadar on 6/3/2017.
 */
public class MI_UserCredentialsVO {

    String username = null;
    String password = null;

    public MI_UserCredentialsVO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
