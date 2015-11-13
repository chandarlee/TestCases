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

    //使用ValueAnimator 需要手动更新target的属性，添加AnimatorUpdateListener，
    //在onAnimationUpdate回调中拿到动画的值并设置属性
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

    //使用ObjectAnimator时指定要操作的属性名称，系统在动画过程中会自动使用反射帮助设置属性值
    //(对应的属性需要有setXXX方法，如果没有设置初始值，则还需要有getXXX方法)
    //如果因为target中不存在上面的setXXX或者getXXX方法，动画执行过程中会抛出异常
    //PropertyValuesHolder允许我们同时对一个target的多种属性进行动画
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

    // ViewPropertyAnimator 是系统为了方便针对view的多种属性
    //(包括 x,y,z,alpha,scaleX,scaleY,rotationX,rotationY,translationX,translationY)进行动画而设置的类
    //相应的在api11之后，view中新增了这些属性对应的set和get方法，更加方便动画的操作
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
