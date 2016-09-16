package com.etermax.borbotones.model;

import android.graphics.Color;

import java.io.Serializable;

public class Card implements Serializable {

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

    public CardType type;
    public String name;
    public String description;
    public int id;
    public int attack;
    public int defense;
    public String resource;
    public int resourceId;
}

