package com.lanye.androidframe.view.view;

/**
 * Created by Lanye on 2017/10/18.
 */

public interface SuperView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void succeed();
}
