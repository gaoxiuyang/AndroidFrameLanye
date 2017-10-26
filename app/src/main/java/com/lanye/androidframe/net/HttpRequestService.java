package com.lanye.androidframe.net;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lanye on 2017/3/2.
 */

public interface HttpRequestService {
    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @GET("/index.php?m=api&c=index&a=login")
    Observable<ResponseBody> login(@Query("username") String username, @Query("password") String password);
}
