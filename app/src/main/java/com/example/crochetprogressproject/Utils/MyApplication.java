package com.example.crochetprogressproject.Utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import java.util.Locale;

public class MyApplication extends Application {

    public static void setLocaleDe (Context context){
        Locale locale = new Locale("de");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    public static void setLocaleEn (Context context){
        Locale locale = new Locale("en_US");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config, null);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Start","on Created called");

    }
}
