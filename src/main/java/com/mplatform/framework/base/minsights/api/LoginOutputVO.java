package com.mplatform.framework.base.minsights.api;

/**
 * Created by mohamed.abdulkadar on 6/18/2017.
 */
public class LoginOutputVO {

    int user_id = 0;
    String access_token = null;
    String refresh_token = null;
    long expire = 1484764556;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}
