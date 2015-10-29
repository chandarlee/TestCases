package com.netease.lcd.lcdtestcases.cases.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.netease.lcd.lcdtestcases.R;

public class FragmentAnimationTest extends ActionBarActivity implements View.OnClickListener{

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(RESULT_OK);
        setContentView(R.layout.activity_fragment_animation_test);
        findViewById(R.id.action).setOnClickListener(this);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment, new SimpleFragment())
                .commit();
        /*getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment, new SimpleFragment())
                .add(R.id.fragment, new SimpleFragment())
                .add(R.id.fragment, new SimpleFragment())
                .add(R.id.fragment, new SimpleFragment())
                .add(R.id.fragment, new SimpleFragment())
                .commit();*/
        /*findViewById(R.id.removeAllFragment).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment);
                int index = 0;
                if (f != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    do {
                        Log.i("FragmentAnimationTest","fragment index "+ ++index);
                        transaction.setCustomAnimations(0, R.anim.slide_out_to_top).remove(f);
                        f = getSupportFragmentManager().findFragmentById(R.id.fragment);
                    } while (f != null);
                    transaction.commit();
                }
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fragment_animation_test, menu);
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
        if (fragment == null || !fragment.isAdded()){
            if (fragment == null){
                fragment = new SimpleFragment();
                fragment.setRetainInstance(true);
            }
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_from_top,0)
                    .replace(R.id.fragment, fragment)
                    .commit();
        }else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(0, R.anim.slide_out_to_top)
                    //.remove(fragment)
                    .replace(R.id.fragment,new NoUiFragment())
                    .commit();
            fragment = null;
        }
       /* View aniView = findViewById(R.id.aniView);
        aniView.startAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_out_to_top));
        ((ViewGroup)findViewById(R.id.fragment)).removeView(aniView);*/
    }

    static class NoUiFragment extends Fragment{

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return null;
        }
    }
}
