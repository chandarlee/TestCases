package com.netease.lcd.lcdtestcases.cases.launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.netease.lcd.lcdtestcases.R;

public abstract class SimpleBaseActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_base);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView info = (TextView) findViewById(R.id.activity_info);
        info.setText(getClass().getSimpleName() + " , " + toString());

        findViewById(R.id.start_new_activity).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(getNewActivityIntent());
            }
        });
    }

    protected abstract Intent getNewActivityIntent();



}
