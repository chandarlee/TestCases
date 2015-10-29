package com.netease.lcd.lcdtestcases.cases.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.Map;

public class RemoteService extends Service {
    public RemoteService() {
    }

    private IRemoteService self = new IRemoteService.Stub(){
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int calc(int a, int b) throws RemoteException {
            Log.i("RemoteService","Thread["+Thread.currentThread().getId()+","+Thread.currentThread().getName()+","+Thread.currentThread().getThreadGroup()+"]");
            getAllThread();
            return a + b;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return self.asBinder();
    }

    private void getAllThread(){
        Map<Thread, StackTraceElement[]> threads = Thread.getAllStackTraces();
        StringBuilder threadsInfo = new StringBuilder();
        for (Thread thread : threads.keySet()){
            threadsInfo.append("Thread[");
            threadsInfo.append(thread.getId());
            threadsInfo.append(",");
            threadsInfo.append(thread.getName());
            threadsInfo.append("]  ");
        }
        Log.i(getClass().getSimpleName(), threadsInfo.toString());
    }
}
