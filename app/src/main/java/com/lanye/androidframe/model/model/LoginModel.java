package com.lanye.androidframe.model.model;

import com.lanye.androidframe.listener.OnRequestFinishedListener;

/**
 * Created by Lanye on 2017/8/29.
 */

public interface LoginModel {
    void login(String username, String password, OnRequestFinishedListener listener);
}
