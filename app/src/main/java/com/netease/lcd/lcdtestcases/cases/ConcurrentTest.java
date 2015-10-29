package com.netease.lcd.lcdtestcases.cases;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.netease.lcd.lcdtestcases.R;

public class ConcurrentTest extends ActionBarActivity {

    private int mOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concurrent_test);
        test();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_concurrent_test, menu);
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

    private void test(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                print();
            }
        }).start();
    }

    private void increment(){
        synchronized (this) {
            Log.i(this.getClass().getSimpleName(),"increment start");
            while (++mOperator <= 10){
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.i(this.getClass().getSimpleName(),"increment stop");
        }
    }

    private void print(){
        synchronized (ConcurrentTest.class) {
            Log.i(this.getClass().getSimpleName(), String.valueOf(mOperator));
        }
    }
}
