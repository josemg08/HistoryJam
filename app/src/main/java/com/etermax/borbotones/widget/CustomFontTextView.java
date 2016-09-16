package com.etermax.borbotones.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.etermax.borbotones.R;
import com.etermax.borbotones.util.Typefaces;

public class CustomFontTextView extends TextView {

    public CustomFontTextView(Context context) {
        super(context);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setCustomFont(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {

        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.TextViewFont);
        String customFont = a.getString(R.styleable.TextViewFont_font_custom);

        if (customFont != null) {
            this.setCustomFont(ctx, customFont);
        }

        a.recycle();
    }

    private void setCustomFont(Context ctx, String asset) {
        Typeface tf = Typefaces.get(ctx, asset);
        this.setTypeface(tf);
    }

    private void refitText(int textWidth) {
        if (textWidth <= 0){
            return;
        }
        float hi = getTextSize();
        float lo = 2;
        final float threshold = 0.5f; // How close we have to be

        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, hi);

        Paint testPaint = new Paint();
        testPaint.set(this.getPaint());

        int targetWidth = textWidth - getPaddingLeft() - getPaddingRight();

        testPaint.setTypeface(getTypeface());
        if (testPaint.measureText(getText().toString()) <= targetWidth) {
            return;
        }

        while (hi - lo > threshold) {
            float size = (hi + lo) / 2;
            testPaint.setTextSize(size);
            testPaint.setTypeface(getTypeface());
            if (testPaint.measureText(getText().toString()) >= targetWidth){
                hi = size; // too big
            }
            else{
                lo = size; // too small
            }
        }

        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, lo);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if ( w != oldw) {
            refitText(w);
        }
    }

}
