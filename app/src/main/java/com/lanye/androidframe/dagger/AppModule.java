package com.lanye.androidframe.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lanye.androidframe.app.MainApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lanye on 2017/10/18.
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(MainApplication application) {
        this.context = application;
    }

    @Singleton
    @Provides
    public Context ProviderApplicationContext(){
        return context;
    }

    @Singleton
    @Provides
    @Named("default")
    public SharedPreferences providerDefaultSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    @Named("encode")
    public SharedPreferences providerEncodeSharedPreferences(){
        return context.getSharedPreferences("encode",Context.MODE_PRIVATE);
    }

}
