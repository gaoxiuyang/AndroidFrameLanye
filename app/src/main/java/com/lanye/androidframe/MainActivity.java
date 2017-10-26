package com.lanye.androidframe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.lanye.androidframe.event.MessageEvent;
import com.lanye.androidframe.view.activity.FunctionModuleActivity;
import com.lanye.androidframe.view.activity.LoadImageActivity;
import com.lanye.androidframe.view.activity.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_load_image)
    Button btnLoadImage;
    @BindView(R.id.btn_function_module)
    Button btnFunctionModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //注册事件
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消事件注册
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.btn_login)
    public void login() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_load_image)
    public void loadImage() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, LoadImageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_function_module)
    public void function() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, FunctionModuleActivity.class);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(MessageEvent messageEvent) {
        Toast.makeText(this, messageEvent.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
