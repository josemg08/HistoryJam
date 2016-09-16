package com.etermax.borbotones.widget;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.etermax.borbotones.model.Card;

public class CardView extends View implements View.OnClickListener{

    Card mCard;

    public CardView(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public void setCard( Card card )
    {
        mCard = card;
        setBackgroundColor( mCard.mType.getColor() );
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
