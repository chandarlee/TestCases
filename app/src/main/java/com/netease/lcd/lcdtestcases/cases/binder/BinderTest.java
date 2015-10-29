package com.netease.lcd.lcdtestcases.cases.binder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.netease.lcd.lcdtestcases.R;

import java.util.Map;

public class BinderTest extends ActionBarActivity implements View.OnClickListener{

    TextView info,calc;
    IRemoteService service;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BinderTest.this.service = IRemoteService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            BinderTest.this.service = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_test);
        info = (TextView) findViewById(R.id.info);
        calc = (TextView) findViewById(R.id.calc);
        calc.setOnClickListener(this);
        getAllThread();
        bindService();
        getAllThread();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_binder_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (service != null){
            try {
                calc.setText(String.valueOf(service.calc(10,10)));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindService();
    }

    private void bindService(){
        Intent intent = new Intent(this,RemoteService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }

    private void unBindService(){
        unbindService(connection);
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
        Log.i(getClass().getSimpleName(),threadsInfo.toString());
        info.setText(threadsInfo.toString());
    }
}
