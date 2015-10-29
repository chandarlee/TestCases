package com.netease.lcd.lcdtestcases.cases.handler;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.netease.lcd.lcdtestcases.R;

public class UILooperTest extends ActionBarActivity implements View.OnClickListener{

    Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            Log.i("UILooperTest","pid : " + thread.getId() + ",name : " + thread.getName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uilooper_test);
        findViewById(R.id.content).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uilooper_test, menu);
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
        handler.postDelayed(runnable, 1000);
        Thread thread = Thread.currentThread();
        Log.i("UILooperTest", "pid : " + thread.getId() + ",name : " + thread.getName());
    }
}
