package com.ma.menglong;

import android.app.Application;
import android.content.Context;

/**
 * Created by Long on 2018/3/22.
 */

public class MyApplication extends Application {
    private static  Context context;

    @Override
    public void onCreate() {
        context=getApplicationContext();
        super.onCreate();
    }
    public  static Context getContext() {
        return context;
    }

}
