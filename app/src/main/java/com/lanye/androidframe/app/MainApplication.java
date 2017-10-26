package com.lanye.androidframe.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.lanye.androidframe.dagger.AppComponent;
import com.lanye.androidframe.dagger.AppModule;
import com.lanye.androidframe.dagger.DaggerAppComponent;

import java.util.ArrayList;
import java.util.List;


public class MainApplication extends Application {
    private static AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    /**打开的activity**/
    private List<Activity> activities = new ArrayList<Activity>();
    /**应用实例**/
    private static MainApplication instance;
    /**
     *  获得实例
     * @return
     */
    public static MainApplication getInstance(){
        return instance;
    }
    /**
     * 新建了一个activity
     * @param activity
     */
    public void addActivity(Activity activity){
        activities.add(activity);
    }
    /**
     *  结束指定的Activity
     * @param activity
     */
    public void finishActivity(Activity activity){
        if (activity!=null) {
            this.activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }
    /**
     * 应用退出，结束所有的activity
     */
    public void exit(){
        for (Activity activity : activities) {
            if (activity!=null) {
                activity.finish();
            }
        }
        System.exit(0);
    }
    /**
     * 关闭Activity列表中的所有Activity*/
    public void finishActivity(){
        for (Activity activity : activities) {
            if (null != activity) {
                activity.finish();
            }
        }
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static MainApplication get(Context context) {
        return (MainApplication) context.getApplicationContext();
    }
    private void setupApplicationComponent() {
        //Dagger开头的注入类DaggerAppComponent
        appComponent = DaggerAppComponent.builder()
                //此时appModule方法是过时方法，因为我们没有使用到任何一个module中提供的对象
                .appModule(new AppModule(this))
                .build();
    }

    //获取AppComponent 以便于SubComponent继承
    public AppComponent getAppComponent() {
        if(appComponent == null){
            this.setupApplicationComponent();
        }
        return appComponent;
    }


    @GlideModule
    public final class MyAppGlideModule extends AppGlideModule {}

}
