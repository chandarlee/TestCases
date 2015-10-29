package com.netease.lcd.lcdtestcases.cases.launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.netease.lcd.lcdtestcases.R;

public class LaunchTest extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_test);

        startActivity(new Intent(this,AActivity.class));
    }

}
