package com.lanye.androidframe.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lanye on 2017/10/18.
 */

@Singleton
//关键代码在这！component会把Module里的提供的对象，注册到容器里
@Component(modules = {AppModule.class})
public interface AppComponent {
    //SubComponent 继承当前Component
    MainComponent addSub(MainModule mainModule);
}
