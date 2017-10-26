package com.lanye.androidframe.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lanye.androidframe.R;
import com.lanye.androidframe.app.MainApplication;
import com.lanye.androidframe.dagger.MainModule;
import com.lanye.androidframe.event.MessageEvent;
import com.lanye.androidframe.presenter.impl.LoginPresenterImpl;
import com.lanye.androidframe.view.view.SuperView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lanye on 2017/8/29.
 */

public class LoginActivity extends AppCompatActivity implements SuperView {

    @Inject
    LoginPresenterImpl presenter;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_back)
    TextView tvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenterImpl(this);
        ButterKnife.bind(this);
        MainApplication.get(this)
                .getAppComponent()
                //将AppComponent继承然后转换成MainComponent
                //MainModule的构造器中传递的是View接口的实例化对象
                .addSub(new MainModule(this))
                //注入到当前类中
                .inject(this);

    }

    @OnClick(R.id.tv_back)
    public void quit(){
        this.finish();
    }

    @OnClick(R.id.btn_login)
    public void login() {
        presenter.validateCredentials("gys", "123456");
    }

    @Override
    public void showProgress() {
        Log.e("-------", "showProgress");
    }

    @Override
    public void hideProgress() {
        Log.e("-------", "hideProgress");
    }

    @Override
    public void setUsernameError() {
        Log.e("-------", "setUsernameError");
    }

    @Override
    public void setPasswordError() {
        Log.e("-------", "setPasswordError");
    }

    @Override
    public void succeed() {
        EventBus.getDefault().post(new MessageEvent("这个成功了"));
    }

}
