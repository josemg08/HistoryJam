package com.etermax.borbotones.model;

import android.graphics.Color;

import java.io.Serializable;
import java.util.Random;

public class Card implements Serializable {

    Random random;

    public enum CardType {
        SCIENCIST(Color.RED, "scientist"),
        POLITICIAN(Color.YELLOW, "politician"),
        MILITAR(Color.GREEN, "militar"),
        ATHLETE(Color.BLUE, "athlet"),
        ARTIST(Color.GRAY, "artist");

        private int mColor;
        private String mCardTypeName;

        CardType(int color, String cardTypeName) {
            mColor = color;
            mCardTypeName = cardTypeName;
        }

        public int getColor() {
            return mColor;
        }

        public String getCardTypeName(){
            return mCardTypeName;
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
