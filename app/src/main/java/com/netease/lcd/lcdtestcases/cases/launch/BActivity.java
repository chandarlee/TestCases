package com.netease.lcd.lcdtestcases.cases.launch;

import android.content.Intent;

/**
 * Created by hzlichengda on 2015/10/20.
 */
public class BActivity extends SimpleBaseActivity {


    @Override
    protected Intent getNewActivityIntent() {
        return new Intent(this,CActivity.class);
    }
}
