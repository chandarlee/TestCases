package com.netease.lcd.lcdtestcases.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2015/4/22.
 */
public class HostSlaveLayout extends RelativeLayout {

    //private static final String TAG = "HostSlaveLayout";

    private View hostView;
    private View slaveView;
    private int hostOffsetTop;

    @SuppressWarnings("deprecation")
    //public static final boolean NEED_RELAYOUT = Integer.valueOf(Build.VERSION.SDK) < Build.VERSION_CODES.HONEYCOMB;
    public static final boolean NEED_RELAYOUT = false;
    private Interpolator interpolator = new AccelerateDecelerateInterpolator();

    public HostSlaveLayout(Context context) {
        super(context);
    }

    public HostSlaveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HostSlaveLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void animateSlaveViewIn() {
        if (hostOffsetTop == 0) {
            slaveView.setVisibility(VISIBLE);

            int dy = getDy();
            //先设置到目标位置,然后从初始位置移动到目标位置
            setHostViewTopmargin(dy);

            if (!NEED_RELAYOUT) {
                final TranslateAnimation slideIn = new TranslateAnimation(0, 0, -dy, 0);
                slideIn.setDuration(700);
                slideIn.setInterpolator(interpolator);
                slaveView.startAnimation(slideIn);
                hostView.startAnimation(slideIn);
            }
        }
    }

    public void animateSlaveViewOut(){
        if(hostOffsetTop > 0){
            //先设置到目标位置,然后从初始位置移动到目标位置
            setHostViewTopmargin(0);
            if (!NEED_RELAYOUT) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, getDy(), 0);
                translateAnimation.setInterpolator(interpolator);
                translateAnimation.setDuration(600);
                translateAnimation.setFillBefore(true);

                hostView.startAnimation(translateAnimation);
            }
            AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
            fadeOut.setDuration(2000);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    slaveView.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            fadeOut.setInterpolator(interpolator);
            slaveView.startAnimation(fadeOut);
        }
    }

    // 无动画
    public void slaveViewOut(){
        if(hostOffsetTop > 0){
            setHostViewTopmargin(0);
            slaveView.setVisibility(View.GONE);
        }
    }

    private int getDy(){
        int dy = slaveView.getMeasuredHeight();
        if(dy == 0) dy = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,getResources().getDisplayMetrics());
        return dy;
    }

    private void setHostViewTopmargin(int topMargin){
        //if(NEED_RELAYOUT){
            //hostView.layout(0, topMargin, hostView.getWidth(), topMargin + hostView.getHeight());
        //}else {
            LayoutParams params = (LayoutParams) hostView.getLayoutParams();
            hostOffsetTop = params.topMargin = topMargin;
            hostView.setLayoutParams(params);
        //}
    }

    public View getHostView() {
        return hostView;
    }

    public void setHostView(View hostView) {
        if(this.hostView == null) {
            this.hostView = hostView;
        }
    }

    public void setHostViewId(int id){
        if(this.hostView == null) {
            this.hostView = findViewById(id);
        }
    }

    public View getSlaveView() {
        return slaveView;
    }

    public void setSlaveViewId(int id){
        if(this.slaveView == null){
            this.slaveView = findViewById(id);
        }
    }

    public void setSlaveView(View slaveView) {
        if(this.slaveView == null) {
            this.slaveView = slaveView;
        }
    }

}
