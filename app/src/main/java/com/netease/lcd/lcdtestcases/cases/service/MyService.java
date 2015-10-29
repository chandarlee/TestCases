package com.netease.lcd.lcdtestcases.cases.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by hzlichengda on 2015/7/22.
 */
public class MyService extends Service {

    @Override
    public void onCreate() {
        log("onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        log("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        log("onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        log("onUnbind");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        log("onRebind");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        log("onDestroy");
        super.onDestroy();
    }

    private void log(String message){
        Log.i(getClass().getSimpleName(),message);
    }
}
