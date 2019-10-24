package com.example.dyq.guava;

import java.io.Serializable;

/**
 * @Auther: dongyuqiang
 * @Date: 2019/9/16 11:10
 * @Description:
 */
public class User implements Serializable {
    private String userName;
    private String userId;

    public User(String userName, String userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return userId + " --- " + userName;
    }
}
