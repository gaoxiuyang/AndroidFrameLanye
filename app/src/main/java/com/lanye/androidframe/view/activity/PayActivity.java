package com.lanye.androidframe.view.activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import com.lanye.androidframe.R;
import com.lanye.androidframe.pay.Alipay;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Lanye on 2017/10/25.
 */

public class PayActivity extends AppCompatActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.btn_alipay)
    Button btnAlipay;
    @BindView(R.id.btn_wechatpay)
    Button btnWechatpay;
    @BindView(R.id.btn_unionpay)
    Button btnUnionpay;
    public Alipay alipay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        alipay = new Alipay(this);
    }

    @OnClick(R.id.tv_back)
    public void quit() {
        this.finish();
    }

    @OnClick(R.id.btn_alipay)
    public void alipay() {
        alipay.payV2();
    }


}
