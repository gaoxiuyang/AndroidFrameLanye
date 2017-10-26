package com.lanye.androidframe.dagger;

import android.app.Activity;

import dagger.Subcomponent;

/**
 * Created by Lanye on 2017/10/18.
 */

@ActivityScope
//很重要！这个Component应该是AppComponent的子Component，所以要使用这个注解
//不使用@Component注解的Dependents属性是因为希望能统一管理子Component
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    //方法参数中，只能传递被注入对象！要在哪个类中注入，写哪个类，注入到父类没用！
    void inject(Activity mainActivity);
}