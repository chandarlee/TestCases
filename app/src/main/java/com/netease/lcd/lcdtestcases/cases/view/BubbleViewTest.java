package com.netease.lcd.lcdtestcases.cases.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.netease.lcd.lcdtestcases.R;

public class BubbleViewTest extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_view_test);
        dimWindow(1.0f);
        //RelativeLayout relativeLayout = new RelativeLayout(this);
        Drawable background = new Drawable() {

            private static final int DX = 100;
            private static final int OFFSET = 20;
            Paint whitePaint = new Paint();

            @Override
            public void setColorFilter(ColorFilter cf) {
                // TODO Auto-generated method stub

            }

            @Override
            public void setAlpha(int alpha) {
                // TODO Auto-generated method stub

            }

            @Override
            public int getOpacity() {
                // TODO Auto-generated method stub
                return PixelFormat.OPAQUE;
            }

            @Override
            public void draw(Canvas canvas) {
                Rect r = getBounds();
                //RectF rect = new RectF(r);
                //rect.inset(-OFFSET, -OFFSET);

                //Build a path
                Path path = new Path();

                //up arrow
                path.moveTo(DX + OFFSET, OFFSET);
                path.lineTo(DX + OFFSET * 2, 0);
                path.lineTo(DX + OFFSET * 3, OFFSET);

                //top horizontal line.
                path.lineTo(r.width() - OFFSET, OFFSET);

                //top right arc
                path.arcTo(new RectF(r.right - OFFSET * 2, OFFSET, r.right, OFFSET * 3), 270, 90); //从270度开始，扫到90度

                //right vertical line.
                path.lineTo(r.width(), r.height() - OFFSET);

                //bottom right arc.
                path.arcTo(new RectF(r.right - OFFSET * 2, r.bottom - OFFSET * 2, r.right, r.bottom), 0, 90);

                //bottom horizontal line.
                path.lineTo(OFFSET, r.height());

                //bottom left arc.
                path.arcTo(new RectF(0, r.bottom - OFFSET * 2, OFFSET * 2, r.bottom), 90, 90);

                //left horizontal line.
                path.lineTo(0, OFFSET);

                //top left arc.
                path.arcTo(new RectF(0, OFFSET, OFFSET * 2, OFFSET * 3), 180, 90);


                path.close();

                whitePaint.setColor(Color.LTGRAY);
                whitePaint.setStyle(Paint.Style.FILL);
                canvas.drawPath(path, whitePaint);
            }
        };
        findViewById(R.id.tv).setBackgroundDrawable(background);
        findViewById(R.id.tv1).setBackgroundDrawable(background);
        //setContentView(relativeLayout);
    }

    private void dimWindow(float dimAmount){
        /*Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.dimAmount = dimAmount;
        wlp.flags = wlp.flags | WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        //window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setAttributes(wlp);*/
        View dimView = new View(this);
        dimView.setBackgroundColor(Color.parseColor("#A0000000"));
        ((ViewGroup)getWindow().getDecorView()).addView(dimView,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bubble_view_test, menu);
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
}
