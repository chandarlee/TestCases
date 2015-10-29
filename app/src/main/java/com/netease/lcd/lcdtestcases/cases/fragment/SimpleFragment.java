package com.netease.lcd.lcdtestcases.cases.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.netease.lcd.lcdtestcases.R;

/**
 * Created by hzlichengda on 2015/8/14.
 */
public class SimpleFragment extends Fragment {

    private static final int[] COLORS = new int[]{
            android.R.color.holo_green_dark,
            android.R.color.holo_blue_bright,
            android.R.color.holo_orange_dark
    };
    private static int index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = (TextView) getView();
        textView.setText("SimpleFragment " + toString());
        textView.setBackgroundResource(COLORS[index++ % COLORS.length]);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(SimpleFragment.this).commit();
            }
        });
        Log.i("SimpleFragment", "onActivityCreated");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("SimpleFragment","onDestroy");
    }
}
