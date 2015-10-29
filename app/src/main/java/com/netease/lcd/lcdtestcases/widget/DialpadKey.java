package com.netease.lcd.lcdtestcases.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.netease.lcd.lcdtestcases.R;


/**
 * Created by hzlichengda on 2015/8/21.
 */
public class DialpadKey extends View {

    private static final float PROPORTION = 0.5f;

    private String number;
    private String letters;
    private Paint digitPaint = null;
    private Paint.FontMetrics digitFM = null;
    private Paint charPaint = null;
    private Paint.FontMetrics charFM = null;
    int gap;

    int numberW,numberH;
    int lettersW,lettersH;
    private int measureSpec;

    public DialpadKey(Context context) {
        super(context);
    }

    public DialpadKey(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DialpadKey(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(21)
    public DialpadKey(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DialpadKey);
        try {
            number = a.getString(R.styleable.DialpadKey_number);
            letters = a.getString(R.styleable.DialpadKey_letters);
        } finally {
            a.recycle();
        }

        //boolean mdpi = ScreenUtil.densityDpi <= DisplayMetrics.DENSITY_MEDIUM;// less or equal than 160dpi

        digitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        digitPaint.setFakeBoldText(false);
        digitPaint.setColor(Color.parseColor("#000000"));
        digitPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size_37sp));
        digitFM = digitPaint.getFontMetrics();

        charPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        charPaint.setFakeBoldText(false);
        charPaint.setColor(Color.parseColor("#000000"));
        charPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size_12sp));
        charFM = charPaint.getFontMetrics();

        numberW =  Math.round(digitPaint.measureText(number));
        /*Rect rect = new Rect();
        digitPaint.getTextBounds(number,0,number.length(),rect);
        numberH = rect.height();*/
        numberH =  Math.round(digitFM.bottom - digitFM.top);

        if (!TextUtils.isEmpty(letters)){
            lettersW = Math.round(charPaint.measureText(letters));
            /*rect.setEmpty();
            charPaint.getTextBounds(letters,0,letters.length(),rect);
            lettersH = rect.height();*/
            lettersH = Math.round(charFM.bottom - charFM.top);
        }

        gap = getResources().getDimensionPixelSize(R.dimen.gap_4dp);
        int size = numberH + (lettersH > 0 ? (lettersH + gap ) : 0);
        //int size = Math.round((0 - digitFM.top) + gap + lettersH + (digitFM.ascent - digitFM.top + ));
        measureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
        Log.i("DialpadKey","size : "+ size + ",spacing :  " + digitPaint.getFontSpacing());
        Log.i("DialpadKey digit fontmetrics ",fontMetricsInfo(digitFM));
        Log.i("DialpadKey char fontmetrics ",fontMetricsInfo(charFM));
    }

    public void setTypeFace(Typeface typeface) {
        charPaint.setTypeface(typeface);
        digitPaint.setTypeface(typeface);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(measureSpec, measureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int width = getWidth();
        final int height = getHeight();

        canvas.save();
        //canvas.translate(0, -digitFM.ascent + digitFM.top);
        float baseX = (width - numberW) * PROPORTION;
        float baseY = 0 - digitFM.top ;
        //float baseY = (height + numberH) * PROPORTION - digitFM.bottom;
        canvas.drawText(number, baseX, baseY, digitPaint);
        canvas.restore();

        if (!TextUtils.isEmpty(letters)){
            canvas.save();
            canvas.translate(0, numberH - digitFM.bottom + gap);
            baseX = (width - lettersW) * PROPORTION;
            baseY = 0 - charFM.top;
            canvas.drawText(letters, baseX, baseY, charPaint);
            canvas.restore();
        }
    }

    private String fontMetricsInfo(Paint.FontMetrics fontMetrics){
        StringBuilder builder = new StringBuilder();
        builder.append("top : ");
        builder.append(fontMetrics.top);
        builder.append(", bottom : ");
        builder.append(fontMetrics.bottom);
        builder.append(", ascent : ");
        builder.append(fontMetrics.ascent);
        builder.append(", descent : ");
        builder.append(fontMetrics.descent);
        builder.append(", leading : ");
        builder.append(fontMetrics.leading);
        return builder.toString();
    }

}
