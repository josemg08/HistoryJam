package com.etermax.borbotones.model;

import android.content.res.Resources;
import android.graphics.Color;

import com.etermax.borbotones.BorbotonesApplication;

import java.io.Serializable;
import java.util.Random;

public class Card implements Serializable {

    Random random;

    public enum CardType {
        SCIENCIST(Color.RED, "scientist"),
        POLITICIAN(Color.YELLOW, "politician"),
        MILITARY(Color.GREEN, "military"),
        ATHLETE(Color.BLUE, "athlete"),
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
        int drawable = random.nextInt(7);

        mType = CardType.values()[index];

        Resources resources = BorbotonesApplication.getContext().getResources();
        mDrawableId = resources.getIdentifier("card" + drawable, "drawable", BorbotonesApplication.getContext().getPackageName());
    }

    public CardType mType;
    public String   mName;
    public String   mDescription;
    public int      mAttack;
    public int      mDefense;
    public int      mDrawableId;
}
