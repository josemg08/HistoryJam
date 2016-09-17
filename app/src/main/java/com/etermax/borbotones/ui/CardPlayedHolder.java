package com.etermax.borbotones.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.etermax.borbotones.R;

/**
 * .___
 * Created by jose-gonzalez on 17/09/16.
 * __.
 */

class CardPlayedHolder extends LinearLayout{

    Context context;
    private ImageView cardPlayedHolderImage;
    private TextView cardPlayedHolderText;
    LayoutInflater inflater;

    public CardPlayedHolder(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public CardPlayedHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CardPlayedHolder(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public void init() {
        LayoutInflater.from(context).inflate(R.layout.card_played_holder, this);
        cardPlayedHolderText =(TextView) findViewById(R.id.card_played_holder_text);
        cardPlayedHolderImage = (ImageView) findViewById(R.id.card_played_holder_image);
    }

}
