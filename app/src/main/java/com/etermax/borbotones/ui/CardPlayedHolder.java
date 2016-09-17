package com.etermax.borbotones.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etermax.borbotones.R;
import com.etermax.borbotones.model.Card;

/**
 * .___
 * Created by jose-gonzalez on 17/09/16.
 * __.
 */

class CardPlayedHolder extends RelativeLayout implements View.OnClickListener{

    Context context;
    private ImageView cardPlayedHolderImage;
    private TextView cardPlayedHolderText;
    LayoutInflater inflater;
    Card card;
    View selectorView;
    private boolean selected = false;

    public void setHolderOpponentListener(OnCardHolderOpponent holderOpponentListener) {
        this.holderOpponentListener = holderOpponentListener;
    }

    private OnCardHolderOpponent holderOpponentListener;

    public void setHolderListener(OnCardHolderListener holderListener) {
        this.holderListener = holderListener;
    }

    private OnCardHolderListener holderListener = getDummyListener();

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
        selectorView = findViewById(R.id.card_selector_view);
        selectorView.setVisibility(GONE);
        setOnClickListener(this);
    }

    public void setCard(Card card){
        if(card== null){
            cardPlayedHolderImage.setVisibility(INVISIBLE);
            return;
        }
        this.card = card;
        cardPlayedHolderImage.setVisibility(VISIBLE);
        cardPlayedHolderImage.setImageDrawable(getResources().getDrawable(card.getResourceId()));
    }

    public Card consumeCard(){
        Card card = this.card;
        this.card = null;
        cardPlayedHolderImage.setImageDrawable(getResources().getDrawable(R.drawable.placeholder));
        selected = false;
        selectorView.setVisibility(GONE);
        return card;
    }

    public Card getCard(){
        return card;
    }

    public void setEmptySloth(){
        card = null;
        cardPlayedHolderImage.setImageDrawable(getResources().getDrawable(R.drawable.placeholder));
    }


    public boolean isMarked() {
        return selected;
    }

    @Override
    public void onClick(View v) {
        if(card !=null && !selected){
            selected = true;
            selectorView.setVisibility(VISIBLE);
            holderListener.onCardMarked(card);
        }
        if(holderOpponentListener!=null && card!=null){
            holderOpponentListener.onCardAttacked(card, this);
        }
    }


    private OnCardHolderListener getDummyListener(){
        return new OnCardHolderListener() {
            @Override
            public void onCardMarked(Card card) {
                Toast.makeText(CardPlayedHolder.this.getContext(), card.name, Toast.LENGTH_LONG).show();
            }
        };
    }
    public interface OnCardHolderListener{

        void onCardMarked(Card card);
    }

    public interface  OnCardHolderOpponent{
        void onCardAttacked(Card card, View view);
    }

}
