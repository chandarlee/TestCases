package com.netease.lcd.lcdtestcases;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.netease.lcd.lcdtestcases.cases.ConcurrentTest;
import com.netease.lcd.lcdtestcases.cases.animation.AnimationSyncTest;
import com.netease.lcd.lcdtestcases.cases.animation.GingerbreadAnimationTest;
import com.netease.lcd.lcdtestcases.cases.binder.BinderTest;
import com.netease.lcd.lcdtestcases.cases.bitmap.InBitmapTest;
import com.netease.lcd.lcdtestcases.cases.fragment.FragmentAnimationTest;
import com.netease.lcd.lcdtestcases.cases.fragment.FragmentRequestCodeTest;
import com.netease.lcd.lcdtestcases.cases.handler.UILooperTest;
import com.netease.lcd.lcdtestcases.cases.launch.LaunchTest;
import com.netease.lcd.lcdtestcases.cases.service.StopServiceTest;
import com.netease.lcd.lcdtestcases.cases.view.BubbleViewTest;
import com.netease.lcd.lcdtestcases.cases.view.ViewsTest;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private List<Class> mCases = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCases();
        initListview();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void initCases(){
        mCases.add(ConcurrentTest.class);
        mCases.add(StopServiceTest.class);
        mCases.add(InBitmapTest.class);
        mCases.add(BinderTest.class);
        mCases.add(UILooperTest.class);
        mCases.add(FragmentAnimationTest.class);
        mCases.add(FragmentRequestCodeTest.class);
        mCases.add(AnimationSyncTest.class);
        mCases.add(ViewsTest.class);
        mCases.add(BubbleViewTest.class);
        mCases.add(GingerbreadAnimationTest.class);
        mCases.add(LaunchTest.class);
    }

    private void initListview(){
        ListView lst = (ListView) findViewById(R.id.listview);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.this.startActivity(new Intent(MainActivity.this,mCases.get(position)));
            }
        });
        lst.setAdapter(new CustomAdapter());
    }

    private class CustomAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return mCases.size();
        }

        @Override
        public Object getItem(int position) {
            return mCases.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_list_item_1,parent,false);
            }
            Class item = (Class) getItem(position);
            ((TextView)convertView).setText(item.getSimpleName());
            return convertView;
        }
    }


}
