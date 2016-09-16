package com.etermax.borbotones.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;
import com.etermax.borbotones.R;
import com.etermax.borbotones.util.Typefaces;

/**
 * Created by charly on 16/09/16.
 */

public class CustomFontButton extends Button {
    public CustomFontButton(Context context) {
        super(context);
    }

    public CustomFontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setCustomFont(context, attrs);
    }

    public CustomFontButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setCustomFont(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomFontButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
}
