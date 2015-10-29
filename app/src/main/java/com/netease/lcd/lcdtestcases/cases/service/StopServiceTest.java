package com.netease.lcd.lcdtestcases.cases.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.netease.lcd.lcdtestcases.R;

public class StopServiceTest extends ActionBarActivity {

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            log("onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            log("onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_service_test);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stop_service_test, menu);
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

    public void onViewClick(View view) {
        switch (view.getId()){
            case R.id.btn1:{
                Intent bind = new Intent(this,MyService.class);
                bindService(bind,serviceConnection, 0);
                break;
            }
            case R.id.btn2:{
                unbindService(serviceConnection);
                break;
            }
            case R.id.btn3:{
                startService(new Intent(this,MyService.class));
                break;
            }
            case R.id.btn4:{
                stopService(new Intent(this,MyService.class));
                break;
            }
        }
    }

    private void log(String message){
        Log.i(getClass().getSimpleName(), message);
    }

}
