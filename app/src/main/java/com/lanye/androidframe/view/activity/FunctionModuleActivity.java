package com.lanye.androidframe.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.lanye.androidframe.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lanye on 2017/10/25.
 */

public class FunctionModuleActivity extends AppCompatActivity {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.btn_IM)
    Button btnIM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_module);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.tv_back)
    public void quit() {
        this.finish();
    }

    @OnClick(R.id.btn_IM)
    public void im() {
        Intent intent = new Intent();
        intent.setClass(FunctionModuleActivity.this, IMActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_pay)
    public void function() {
        Intent intent = new Intent();
        intent.setClass(FunctionModuleActivity.this, PayActivity.class);
        startActivity(intent);
    }
}
