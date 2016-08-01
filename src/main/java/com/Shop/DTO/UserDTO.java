package com.Shop.DTO;

import com.Shop.beans.User;

/**
 * Created by Administrator on 2016/7/27.
 */
public class UserDTO {
    private User user;
    private int state;
    private String errorMsg;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
