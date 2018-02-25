package com.oproom;

import android.app.Application;

/**
 * Created by Cezar Carneiro on 16/1/2018.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OpRoom.init(this).withDefaults().start();
    }
}
