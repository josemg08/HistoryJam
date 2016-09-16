package com.etermax.borbotones.model;

import android.graphics.Color;

public class Card {

    public enum CardType
    {
        SCIENCIST(Color.RED),
        POLITICIAN(Color.YELLOW),
        MILITAR(Color.GREEN),
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

    public CardType mType;
    public String   mName;
    public String   mDescription;
    public int      mAttack;
    public int      mDefense;
    public int      mDrawableId;
}
