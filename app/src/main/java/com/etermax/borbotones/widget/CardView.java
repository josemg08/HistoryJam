package com.etermax.borbotones.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.etermax.borbotones.R;
import com.etermax.borbotones.model.Card;
import com.etermax.borbotones.ui.CardInformationActivity;

public class CardView extends RelativeLayout implements View.OnClickListener{

    ImageView cardImageView;
    View      enabledView;

    Card mCard;

    public CardView(Context context) {
        super(context);
//        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        inflate(getContext(), R.layout.card_view, this);

        setOnClickListener(this);
        cardImageView = (ImageView)findViewById(R.id.card_image);
        setBackgroundColor(Color.RED);
        //enabledView = findViewById(R.id.enabled);
    }

    public void setCard( Card card )
    {
        mCard = card;
        Glide.with(getContext()).load(mCard.getResourceId()).into(cardImageView);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), CardInformationActivity.class);
        intent.putExtra(CardInformationActivity.CARD_KEY, mCard);
        getContext().startActivity(intent);
    }

//    @Override
//    public void setEnabled(boolean enabled) {
//        super.setEnabled(enabled);
//        findViewById(R.id.enabled).setEnabled(enabled);
//    }
}
