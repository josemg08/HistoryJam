package com.etermax.borbotones.widget;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.etermax.borbotones.model.Card;
import com.etermax.borbotones.ui.CardInformationActivity;

public class CardView extends ImageView implements View.OnClickListener{

    Card mCard;

    public CardView(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public void setCard( Card card ) {
        mCard = card;
        //setBackgroundColor( mCard.type.getColor() );
        Glide.with(getContext()).load(mCard.getResourceId()).into(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), CardInformationActivity.class);
        intent.putExtra(CardInformationActivity.CARD_KEY, mCard);
        getContext().startActivity(intent);
    }

}
