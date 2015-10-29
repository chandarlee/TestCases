package com.netease.lcd.lcdtestcases.cases.view;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.netease.lcd.lcdtestcases.R;

public class ViewsTest extends ActionBarActivity implements View.OnClickListener{

    private static final int[] offsets = new int[]{50,10,-30};

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views_test);
        findViewById(R.id.action).setOnClickListener(this);
        findViewById(R.id.action1).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_views_test, menu);
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
            case R.id.action:{
                View actionView = findViewById(R.id.actionView);
                //actionView.offsetTopAndBottom(offsets[index++ % 3]);
                actionView.offsetTopAndBottom(100);
                break;
            }
            case R.id.action1:{

                AnimationSet set = new AnimationSet(true);
                TranslateAnimation move = new TranslateAnimation(0,0,100,0);
                set.addAnimation(move);
                //set.setFillEnabled(true);
                //set.setFillBefore(true);
                set.setDuration(1000);
                //actionView.offsetTopAndBottom(-100);

                set.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                final View actionView = findViewById(R.id.actionView);
                actionView.offsetTopAndBottom(-100);
                /*RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) actionView.getLayoutParams();
                params.topMargin = -100;
                actionView.setLayoutParams(params);*/

                findViewById(R.id.actionView).startAnimation(set);
                break;
            }
        }
    }
}
