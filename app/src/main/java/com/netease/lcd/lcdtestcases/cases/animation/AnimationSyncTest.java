package com.netease.lcd.lcdtestcases.cases.animation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.netease.lcd.lcdtestcases.R;
import com.netease.lcd.lcdtestcases.utils.ViewServer;

public class AnimationSyncTest extends ActionBarActivity implements View.OnClickListener {

    View blueView;
    View greenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_sync_test);
        blueView = findViewById(R.id.blue_view);
        greenView = findViewById(R.id.green_view);
        findViewById(R.id.start_animation).setOnClickListener(this);
        ViewServer.get(this).addWindow(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_animation_sync_test, menu);
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
        switch (v.getId()){
            case R.id.start_animation:{
                startAnimation();
            }
        }
    }

    private void startAnimation(){
        Animation animation = new TranslateAnimation(0,0,0,100);
        animation.setDuration(3000);
        //animation.setStartTime(Animation.START_ON_FIRST_FRAME);

        //Animation animation2 = new TranslateAnimation(0,0,0,100);
        //animation.setDuration(3000);

        blueView.startAnimation(animation);
        greenView.startAnimation(animation);

        //blueView.setAnimation(animation);
        //greenView.setAnimation(animation);

        //animation.startNow();
        //blueView.invalidate();
        //greenView.invalidate();
    }
}
