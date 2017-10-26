package com.lanye.androidframe.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Lanye on 2017/10/18.
 * 该类用于区分与@Sigleton或其他@Scope的作用域。
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}