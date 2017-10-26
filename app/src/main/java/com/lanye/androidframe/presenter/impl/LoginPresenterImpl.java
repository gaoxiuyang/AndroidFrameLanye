package com.lanye.androidframe.presenter.impl;

import com.lanye.androidframe.listener.OnRequestFinishedListener;
import com.lanye.androidframe.model.impl.LoginModelImpl;
import com.lanye.androidframe.model.model.LoginModel;
import com.lanye.androidframe.presenter.presenter.LoginPresenter;
import com.lanye.androidframe.view.view.SuperView;

/**
 * Created by Anthony on 2016/2/15.
 * Class Note:
 * 1 完成presenter的实现。这里面主要是Model层和View层的交互和操作。
 * 2 presenter里面还有个OnLoginFinishedListener，
 * 其在Presenter层实现，给Model层回调，更改View层的状态，
 * 确保 Model层不直接操作View层。如果没有这一接口在LoginPresenterImpl实现的话，
 * LoginPresenterImpl只 有View和Model的引用那么Model怎么把结果告诉View呢？
 */
public class LoginPresenterImpl implements LoginPresenter, OnRequestFinishedListener {
    private SuperView SuperView;
    private LoginModel loginModel;

    public LoginPresenterImpl(SuperView superView) {
        this.SuperView = superView;
        this.loginModel = new LoginModelImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        System.out.print("----------------"+username+password);
        if (SuperView != null) {
            SuperView.showProgress();
        }
        loginModel.login(username, password, this);
    }

    @Override
    public void onDestroy() {
        SuperView = null;
    }

    @Override
    public void onUsernameError() {
        if (SuperView != null) {
            SuperView.setUsernameError();
            SuperView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (SuperView != null) {
            SuperView.setPasswordError();
            SuperView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (SuperView != null) {
            SuperView.succeed();
            SuperView.hideProgress();
        }
    }
}
