package com.lanye.androidframe.net;

import android.content.Context;
import android.widget.Toast;

import com.lanye.androidframe.app.MainApplication;
import com.lanye.androidframe.utils.Constant;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpMethod {

    public static final String BASE_URL = Constant.BaseUrl;
    private static Retrofit retrofit;

    //构造方法私有
    private HttpMethod() {
        retrofit = new Retrofit.Builder()
                .client(genericClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static <T> T createApi(Class<T> clazz) {
        return retrofit.create(clazz);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethod INSTANCE = new HttpMethod();
    }

    //获取单例
    public static HttpMethod getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static OkHttpClient genericClient() {
        LoggingInterceptor logging = new LoggingInterceptor();
        logging.setLevel(LoggingInterceptor.Level.BODY);
        //设置缓存路径
        File httpCacheDirectory = new File(MainApplication.getInstance().getExternalCacheDir().getAbsolutePath(), "responses");
        //设置缓存 10M
        Cache cache = new Cache(httpCacheDirectory, 50 * 1024 * 1024);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = null;
                        request = chain.request().newBuilder()
                                .build();
                        if (!NetUtil.getNetworkIsConnected(MainApplication.getInstance())) {
                            request = request.newBuilder()
                                    .cacheControl(CacheControl.FORCE_CACHE)
                                    .build();
                        }
                        Response response = chain.proceed(request);
                        if (NetUtil.getNetworkIsConnected(MainApplication.getInstance())) {
                            int maxAge = 0 * 60; // 有网络时 设置缓存超时时间0个小时
                            response.newBuilder()
                                    .addHeader("Cache-Control", "public, max-age=" + maxAge)
                                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                                    .build();
                        } else {
                            int maxStale = 60 * 60 * 24 * 7; // 无网络时，设置超时为1周
                            response.newBuilder()
                                    .addHeader("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                    .removeHeader("Pragma")
                                    .build();
                        }
                        return response;
                    }

                }).
                        addInterceptor(logging).
                        cache(cache)
                .build();
        return httpClient;
    }


    public static void errReminder(Context context) {
        switch (Constant.RESPONSECODE) {
            case 500:
                Toast.makeText(context, "服务器异常请稍后再试", Toast.LENGTH_SHORT).show();
                break;
            case 404:
                Toast.makeText(context, "抱歉，没有找到", Toast.LENGTH_SHORT).show();
                break;
            case 400:
                Toast.makeText(context, "参数错误，请检查参数！", Toast.LENGTH_SHORT).show();
                break;
            case 502:
                Toast.makeText(context, "网关问题，请稍后再试", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}