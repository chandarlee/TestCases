package com.netease.lcd.lcdtestcases.cases.view;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.netease.lcd.lcdtestcases.R;

public class TextViewMeasureTest extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_measure_test);
        measureViewsManual();
    }

    private void measureViewsManual(){
        TextView measureView;
        TextView infoView;
        StringBuilder builder = new StringBuilder();

        measureView = (TextView) findViewById(R.id.measuredView0);//width match , height wrap
        infoView = (TextView) findViewById(R.id.measuredView0Info);
        builder.append("dimension info before measure:\n");
        builder.append(dimensionInfo(measureView));
        measureView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        builder.append("dimension info after measure:\n");
        builder.append(dimensionInfo(measureView));
        infoView.setText(builder.toString());

        builder = new StringBuilder();
        measureView = (TextView) findViewById(R.id.measuredView1);//width wrap , height wrap
        infoView = (TextView) findViewById(R.id.measuredView1Info);
        //builder.append("dimension info before measure:\n");
        //builder.append(dimensionInfo(measureView));
        measureView.setText("Hello Everybody!");//set text , to see if there is a differences?
        measureView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        builder.append("dimension info after measure:\n");
        builder.append(dimensionInfo(measureView));
        infoView.setText(builder.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        final TextView infoView = (TextView) findViewById(R.id.measuredView1Info);
        infoView.postDelayed(new Runnable() {
            @Override
            public void run() {
                infoView.setText(infoView.getText() + "\n" + dimensionInfo(findViewById(R.id.measuredView1)));
            }
        },2000);

    }

    private String dimensionInfo(View view){
        StringBuilder builder = new StringBuilder();
        builder.append("width:");
        builder.append(view.getWidth());
        builder.append("\n");
        builder.append("height:");
        builder.append(view.getHeight());
        builder.append("\n");
        builder.append("measured width:");
        builder.append(view.getMeasuredWidth());
        builder.append("\n");
        builder.append("measured height:");
        builder.append(view.getMeasuredHeight());
        builder.append("\n");
        return builder.toString();
    }

}
