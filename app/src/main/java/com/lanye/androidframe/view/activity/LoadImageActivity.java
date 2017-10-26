package com.lanye.androidframe.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanye.androidframe.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lanye on 2017/10/17.
 */

public class LoadImageActivity extends AppCompatActivity {
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.iv_image)
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        ButterKnife.bind(this);
        Glide.with(this)
                .load("http://apod.nasa.gov/apod/image/1410/20141008tleBaldridge001h990.jpg")
                .into(ivImage);

    }
    @OnClick(R.id.tv_back )
    public void quit(){
        this.finish();
    }

}
