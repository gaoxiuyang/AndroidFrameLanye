package com.lanye.androidframe.model.impl;

import com.lanye.androidframe.listener.OnRequestFinishedListener;
import com.lanye.androidframe.model.model.LoginModel;
import com.lanye.androidframe.net.HttpRequestApi;
import com.lanye.androidframe.net.HttpSubscriber;
import com.lanye.androidframe.net.SubscriberOnListener;

import dagger.Module;
import okhttp3.ResponseBody;

/**
 * Created by Lanye on 2017/8/29.
 */
@Module
public class LoginModelImpl implements LoginModel {

    @Override
    public void login(String username, String password, final OnRequestFinishedListener listener) {
        HttpRequestApi.getInstance().login(username, password, new HttpSubscriber<ResponseBody>
                (new SubscriberOnListener<ResponseBody>() {
            @Override
            public void onSucceed(final ResponseBody responseBody) {
                listener.onSuccess();
            }

            @Override
            public void onError(int code, String msg) {
                listener.onPasswordError();
            }
        }));
    }

}
