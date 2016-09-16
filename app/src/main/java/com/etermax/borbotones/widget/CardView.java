package com.etermax.borbotones.widget;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.etermax.borbotones.model.Card;

public class CardView extends View implements View.OnClickListener{

    Card mCard;

    public CardView(Context context, Card card) {
        super(context);
        mCard = card;
        setBackgroundColor( mCard.mType.getColor() );
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec > 0 ? heightMeasureSpec : widthMeasureSpec);//Square
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "CLICK", Toast.LENGTH_SHORT).show();
    }
}
