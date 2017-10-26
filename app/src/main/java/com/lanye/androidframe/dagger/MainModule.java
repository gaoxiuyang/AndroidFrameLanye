package com.lanye.androidframe.dagger;

import com.lanye.androidframe.view.view.SuperView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lanye on 2017/10/18.
 */

@Module
public class MainModule {

    private SuperView view;

    //构造方法传递View 接口的实例化对象
    public MainModule(SuperView view){
        this.view = view;
    }

    //在DI容器中提供View接口的实例化对象
    @ActivityScope
    @Provides
    public SuperView providerView(){
        return view;
    }

}