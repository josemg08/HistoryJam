package com.etermax.borbotones.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.etermax.borbotones.R;
import com.etermax.borbotones.model.Card;

/**
 * .___
 * Created by jose-gonzalez on 16/09/16.
 * __.
 */

public class CardInformationActivity extends Activity {

    //.___ Intent argument __./
    public static final String CARD_KEY = "card_key";

    private ImageView mImageView;
    private TextView mCardTitle;
    private TextView mCardType;
    private TextView mCardAttack;
    private TextView mCardDefence;
    private TextView mCardDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_information_layout);

        Intent intent = getIntent();
        Card card = (Card) intent.getSerializableExtra(CARD_KEY);

        mImageView = (ImageView) findViewById(R.id.card_image);
        mCardTitle = (TextView) findViewById(R.id.card_title);
        mCardType = (TextView) findViewById(R.id.card_type);
        mCardAttack = (TextView) findViewById(R.id.card_attack);
        //TODO mCardDefence = (TextView) findViewById(R.id.card_defence);
        mCardDescription = (TextView) findViewById(R.id.card_description);

        setCardAttributes(card);
    }

    private void setCardAttributes(Card card){
        //TODO mImageView.setImageDrawable(getResources().getDrawable(card.getResourceId()));
        mCardTitle.setText(card.name);
        mCardType.setText(card.type.getCardTypeName());
        mCardAttack.setText(Integer.toString(card.attack) );
        //TODO mCardDefence.setText(Integer.toString(card.defense) );
        mCardDescription.setText(card.description);
    }

}
//.___ End of CardInformationActivity __./