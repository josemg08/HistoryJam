package com.etermax.borbotones.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.etermax.borbotones.R;
import com.etermax.borbotones.model.Card;

/**
 * .___
 * Created by jose-gonzalez on 16/09/16.
 * __.
 */

public class CardInformationActivity extends AppCompatActivity {

    //.___ Intent argument __./
    protected static final String CARD_KEY = "card_key";

    private ImageView mImageView;
    private TextView mCardTitle;
    private TextView mCardType;
    private TextView mCardAttack;
    private TextView mCardDefence;
    private TextView mCardDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_selection_layout);

        Intent intent = getIntent();
        Card card = (Card) intent.getSerializableExtra(CARD_KEY);

        mImageView = (ImageView) findViewById(R.id.card_image);
        mCardTitle = (TextView) findViewById(R.id.card_title);
        mCardType = (TextView) findViewById(R.id.card_type);
        mCardAttack = (TextView) findViewById(R.id.card_attack);
        mCardDefence = (TextView) findViewById(R.id.card_defence);
        mCardDescription = (TextView) findViewById(R.id.card_description);

        setCardAttributes(card);
    }

    private void setCardAttributes(Card card){
        mImageView.setImageDrawable(getResources().getDrawable(card.mDrawableId));
        mCardTitle.setText(card.mName);
        mCardType.setText(card.mType.getCardTypeName());
        mCardAttack.setText(card.mAttack);
        mCardDefence.setText(card.mDefense);
        mCardDescription.setText(card.mDescription);
    }

}