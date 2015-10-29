package com.netease.lcd.lcdtestcases.cases.launch;

import android.content.Intent;

/**
 * Created by hzlichengda on 2015/10/20.
 */
public class CActivity extends SimpleBaseActivity {

    @Override
    protected Intent getNewActivityIntent() {
        Intent intent = new Intent(this,AActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }
}
