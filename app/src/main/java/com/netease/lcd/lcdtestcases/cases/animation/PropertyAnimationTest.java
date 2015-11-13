package com.netease.lcd.lcdtestcases.cases.animation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.netease.lcd.lcdtestcases.R;

public class PropertyAnimationTest extends AppCompatActivity implements View.OnClickListener{

    private TypeEvaluator pointEvaluator = new PointEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_aniamtion_test);

        findViewById(R.id.valueAnimator).setOnClickListener(this);
        findViewById(R.id.objectAnimator).setOnClickListener(this);
        findViewById(R.id.viewPropertyAnimator).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.valueAnimator:{
                inValueAnimator();
                break;
            }
            case R.id.objectAnimator:{
                inObjectAnimator();
                break;
            }
            case R.id.viewPropertyAnimator:{
                inViewPropertyAnimator();
                break;
            }
        }
    }

    private void inValueAnimator(){
        final View aniView = findViewById(R.id.aniView0);
        final float startX = 0;
        final float startY = aniView.getY();
        float pW = ((ViewGroup)aniView.getParent()).getWidth();
        float pH = ((ViewGroup)aniView.getParent()).getHeight();
        ValueAnimator animator = ValueAnimator.ofObject(pointEvaluator,new Point(startX,startY), new Point(pW / 2,0), new Point(pW,startY),new Point(pW / 2, pH),new Point(startX,startY));
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point point = (Point) animation.getAnimatedValue();
                aniView.setX(point.x);
                aniView.setY(point.y);
            }
        });
        animator.start();
    }

    private void inObjectAnimator(){
        final View aniView = findViewById(R.id.aniView1);
        final float startX = 0;
        final float startY = aniView.getY();
        float pW = ((ViewGroup)aniView.getParent()).getWidth();
        float pH = ((ViewGroup)aniView.getParent()).getHeight();
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(aniView, PropertyValuesHolder.ofFloat("x", startX, pW / 2, pW, pW / 2, startX),
                PropertyValuesHolder.ofFloat("y", startY, 0, startY, pH, startY));
        animator.setDuration(1000);
        animator.start();
    }

    private void inViewPropertyAnimator(){
        final View aniView = findViewById(R.id.aniView2);
        final float startX = 0;
        final float startY = aniView.getY();
        float pW = ((ViewGroup)aniView.getParent()).getWidth();
        float pH = ((ViewGroup)aniView.getParent()).getHeight();
        final ViewPropertyAnimator animator = aniView.animate();
        animator.x(pW).y(pH).withEndAction(new Runnable() {
            @Override
            public void run() {
                animator.x(startX).y(startY).setDuration(500);
            }
        }).setDuration(500);
    }


    private static class Point{
        public float x;
        public float y;

        public Point(){

        }

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class PointEvaluator implements TypeEvaluator<Point>{
        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            Point result = new Point();
            result.x = startValue.x + (fraction * (endValue.x - startValue.x));
            result.y = startValue.y + (fraction * (endValue.y - startValue.y));
            return result;
        }
    }
}
