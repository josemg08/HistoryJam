package com.etermax.borbotones.model;

import android.graphics.Color;

import java.util.Random;

public class Card {

    Random random;

    public enum CardType {
        SCIENCIST(Color.RED),
        POLITICIAN(Color.YELLOW),
        MILITARY(Color.GREEN),
        ATHLETE(Color.BLUE),
        ARTIST(Color.GRAY);

        private int mColor;

        CardType(int color) {
            mColor = color;
        }

        public int getColor() {
            return mColor;
        }
    }

    public Card()
    {
        random = new Random();
        int index = random.nextInt(CardType.values().length);
        mType = CardType.values()[index];
    }

    public CardType mType;
    public String   mName;
    public String   mDescription;
    public int      mAttack;
    public int      mDefense;
    public int      mDrawableId;
}
