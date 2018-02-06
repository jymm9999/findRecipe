package com.example.cho.findrecipe.kakao;

import android.app.Activity;
import android.app.Application;

/**
 * Created by cho on 2018-02-03.
 */
public class GlobalApplication extends Application {

    private static volatile Activity currentActivity = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    // Activity가 올라올때마다 Activity의 onCreate에서 호출해줘야한다.
    public static void setCurrentActivity(Activity currentActivity) {
        GlobalApplication.currentActivity = currentActivity;
    }
}