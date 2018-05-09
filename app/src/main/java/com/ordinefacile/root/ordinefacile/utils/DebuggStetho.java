package com.ordinefacile.root.ordinefacile.utils;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by user on 5/9/2018.
 */

public class DebuggStetho extends Application {

    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}


