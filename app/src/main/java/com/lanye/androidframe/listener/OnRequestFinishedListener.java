package com.lanye.androidframe.listener;

/**
 * Created by Anthony on 2016/2/15.
 * Class Note:登陆事件监听
 */
public interface OnRequestFinishedListener {

    void onUsernameError();

    void onPasswordError();

    void onSuccess();
}
