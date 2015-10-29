package com.netease.lcd.lcdtestcases.cases.animation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.netease.lcd.lcdtestcases.R;
import com.netease.lcd.lcdtestcases.widget.HostSlaveLayout;

public class GingerbreadAnimationTest extends ActionBarActivity implements View.OnClickListener {

    private HostSlaveLayout hostSlaveLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gingerbread_animation_test);
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gingerbread_animation_test, menu);
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

    private void initViews(){
        findViewById(R.id.ani_in).setOnClickListener(this);
        findViewById(R.id.ani_out).setOnClickListener(this);

        hostSlaveLayout = (HostSlaveLayout) findViewById(R.id.host_slave_layout);
        hostSlaveLayout.setSlaveView(findViewById(R.id.slave_view));
        hostSlaveLayout.setHostView(findViewById(R.id.host_view));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ani_in:{
                //hostSlaveLayout.animateSlaveViewIn();
                //dimWindow(0.5f);
                break;
            }
            case R.id.ani_out:{
                //hostSlaveLayout.animateSlaveViewOut();
                //dimWindow(1.0f);
                break;
            }
        }
    }

    private void dimWindow(float dimAmount){
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.dimAmount = dimAmount;
        wlp.flags = wlp.flags | WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        //window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setAttributes(wlp);
    }
}
