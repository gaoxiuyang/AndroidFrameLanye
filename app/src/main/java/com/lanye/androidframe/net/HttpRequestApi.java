package com.lanye.androidframe.net;


import android.widget.Toast;

import com.lanye.androidframe.app.MainApplication;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Lanye on 2017/3/2.
 */

public class HttpRequestApi extends BaseApi {
    public static HttpRequestApi httpRequestApi;
    public HttpRequestService httpRequestService;

    public HttpRequestApi() {
        httpRequestService = HttpMethod.getInstance().createApi(HttpRequestService.class);
    }

    public static HttpRequestApi getInstance() {
        if (httpRequestApi == null) {
            httpRequestApi = new HttpRequestApi();
        }
        if (NetUtil.getNetworkState(MainApplication.getInstance()) == 0) {
            Toast.makeText(MainApplication.getInstance(), "网络出错", Toast.LENGTH_SHORT).show();
        }
        return httpRequestApi;
    }

    /**
     * 登录
     *
     * @param username   用户名
     * @param password   密码
     * @param subscriber
     */
    public void login(String username, String password, Subscriber<ResponseBody> subscriber) {
        Observable observable = httpRequestService.login(username, password)
                .map(new HttpResultFunc<ResponseBody>());
        toSubscribe(observable, subscriber);
    }
}
