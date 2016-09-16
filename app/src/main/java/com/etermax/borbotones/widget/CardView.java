package com.etermax.borbotones.widget;

import android.content.Context;
import android.view.View;

import com.etermax.borbotones.model.Card;

public class CardView extends View {

    Card mCard;

    public CardView(Context context, Card card) {
        super(context);
        mCard = card;
        setBackgroundColor( mCard.mType.getColor() );
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec > 0 ? heightMeasureSpec : widthMeasureSpec);//Square
    }
}
