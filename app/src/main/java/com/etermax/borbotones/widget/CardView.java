package com.etermax.borbotones.widget;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.etermax.borbotones.R;
import com.etermax.borbotones.model.Card;
import com.etermax.borbotones.ui.CardInformationActivity;

public class CardView extends RelativeLayout implements View.OnClickListener {

    ImageView cardImageView;
    View enabledView;

    Card mCard;

    public CardView(Context context) {
        super(context);
        inflate(getContext(), R.layout.card_view, this);
        setOnClickListener(this);
        cardImageView = (ImageView) findViewById(R.id.card_image);
        enabledView = findViewById(R.id.enabled);
    }

    public void setCard(Card card, boolean enabled) {
        mCard = card;
        enabledView.setEnabled(enabled);
        if (enabled) {
            Glide.with(getContext()).load(mCard.getResourceId()).into(cardImageView);
        } else {
            Glide.with(getContext()).load(R.drawable.card_revere).into(cardImageView);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width * 735 / 505;
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

    PopupWindow popUp;

    @Override
    public void onClick(View v) {
        //new AlertDialog.Builder(getContext()).setView( new I).create().show();

        // Create the AlertDialog object and return it
        Intent intent = new Intent(getContext(), CardInformationActivity.class);
        intent.putExtra(CardInformationActivity.CARD_KEY, mCard);
        getContext().startActivity(intent);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }
}
