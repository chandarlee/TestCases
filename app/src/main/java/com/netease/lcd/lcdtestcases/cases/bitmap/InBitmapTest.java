package com.netease.lcd.lcdtestcases.cases.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.netease.lcd.lcdtestcases.R;

public class InBitmapTest extends ActionBarActivity {

    ImageView imageView1;
    TextView info1;
    ImageView imageView2;
    TextView info2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_bitmap_test);

        imageView1 = (ImageView) findViewById(R.id.img1);
        imageView2 = (ImageView) findViewById(R.id.img2);
        info1 = (TextView) findViewById(R.id.img1_info);
        info2 = (TextView) findViewById(R.id.img2_info);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_in_bitmap_test, menu);
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

    public void onActionViewClick(View view) {
        switch (view.getId()){
            case R.id.img1_info:{
                loadImg1();
                break;
            }
            case R.id.img2_info:{
                loadImg2();
                break;
            }
        }
    }

    private void loadImg1(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        /*BitmapDrawable drawable = (BitmapDrawable) imageView2.getDrawable();
        if (drawable != null){
            options.inBitmap = drawable.getBitmap();
        }*/
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.happy_new_year_aji,options);
        imageView1.setImageBitmap(bitmap);

        infos();
    }

    private void loadImg2(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inBitmap = ((BitmapDrawable)imageView1.getDrawable()).getBitmap();
        options.inMutable = true;
        imageView2.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher,options));

        infos();
    }

    private void infos(){
        imageView1.invalidate();
        imageView2.invalidate();
        info(imageView1,info1);
        info(imageView2,info2);
    }

    private void info(ImageView imageView,TextView textView){
        StringBuilder info = new StringBuilder();
        info.append("Imageview width * height: ");
        info.append(imageView.getWidth()).append(" * ").append(imageView.getHeight()).append("\n");
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        if (drawable != null) {
            info.append("drawable width * height: ").append(drawable.getIntrinsicWidth()).append(" * ").append(drawable.getIntrinsicHeight()).append("\n");
            Bitmap bitmap = drawable.getBitmap();
            if (bitmap != null) {
                info.append("Bitmap width * height: ").append(bitmap.getWidth()).append(" * ").append(bitmap.getHeight()).append("\n");
                info.append("Bitmap").append(bitmap.isMutable() ? " mutable " : " immutable ").append("\n");
            }
        }
        textView.setText(info.toString());
    }
}
