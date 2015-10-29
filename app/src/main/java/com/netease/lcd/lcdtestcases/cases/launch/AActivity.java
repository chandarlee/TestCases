package com.netease.lcd.lcdtestcases.cases.launch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by hzlichengda on 2015/10/20.
 */
public class AActivity extends SimpleBaseActivity {

    @Override
    protected Intent getNewActivityIntent() {
        return new Intent(this,BActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
