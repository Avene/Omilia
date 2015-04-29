package com.avene.avene.omilia;

import android.content.Context;

/**
 * Created by yamai on 4/29/2015.
 */
public class Application extends android.app.Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return sContext;
    }
}
