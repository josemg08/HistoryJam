package com.etermax.borbotones.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import android.widget.ImageView;

import com.etermax.borbotones.R;
import com.etermax.borbotones.model.Card;
import com.etermax.borbotones.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by charly on 16/09/16.
 */

public class DeckView extends ImageView implements View.OnClickListener {

    private ArrayList<Card> cards = new ArrayList<>(0);
    private OnDeckListener onDeckListener = getDummyListener();

    public DeckView(Context context) {
        super(context);
        init(context, null);
    }

    public DeckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DeckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DeckView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet set) {
        setOnClickListener(this);
        load();
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
        load();
    }

    public void setCards(Card... cards) {
        this.cards = new ArrayList<>(Arrays.asList(cards));
        load();
    }

    public boolean isEmpty() {
        return this.cards.isEmpty();
    }


    public List<Card> consumeCards(int total) {
        List<Card> cardsTemp;
        if (cards.size() < total) {
            cardsTemp = (List<Card>) cards.clone();
            cards = new ArrayList<>();
            return cardsTemp;
        }
        cardsTemp = new ArrayList<>(total);
        while (total>0){
            cardsTemp.add( cards.remove(--total));
        }
        return cardsTemp;

    }

    private void load() {
        int size = cards.size();
        if (size > Constants.NUMBER_CARD_DECK / 2) {
            setImageResource(R.drawable.card_revere);
        } else if (size < Constants.NUMBER_CARD_DECK / 2 && size > Constants.NUMBER_CARD_DECK / 4) {
            setImageResource(R.drawable.card_revere);
        } else if (size < Constants.NUMBER_CARD_DECK / 4) {
            setImageResource(R.drawable.card_revere);
        } else {
            setImageDrawable(null);
        }
    }


    @Override
    public void onClick(View v) {
        if (cards.isEmpty()) {
            onDeckListener.onEmptiedDeck();
        } else {
            Card card = cards.get(new Random().nextInt(cards.size()));
            cards.remove(card);
            onDeckListener.onCardSelected(card);
            load();
        }
    }

    public void setOnDeckListener(OnDeckListener onDeckListener) {
        this.onDeckListener = onDeckListener;
    }

    private OnDeckListener getDummyListener() {
        return new OnDeckListener() {
            @Override
            public void onCardSelected(Card card) {

            }

            @Override
            public void onEmptiedDeck() {

            }
        };
    }

    public interface OnDeckListener {

        void onCardSelected(Card card);

        void onEmptiedDeck();
    }

}
