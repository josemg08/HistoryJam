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

    public CardType type;
    public String name;
    public String description;
    public int id;
    public int attack;
    public int defense;
    public String resource;

    public int getResourceId()
    {
        Resources resources = BorbotonesApplication.getContext().getResources();
        return resources.getIdentifier(resource, "drawable", BorbotonesApplication.getContext().getPackageName());
    }

    public Card getCopy(){

        Card copy = new Card();
        copy.type = type;
        copy.name = name;
        copy.description = description;
        copy.id = id;
        copy.attack = attack;
        copy.defense = defense;
        copy.resource = resource;

        return copy;
    }
}

