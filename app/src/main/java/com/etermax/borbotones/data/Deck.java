package com.etermax.borbotones.data;

import com.etermax.borbotones.BorbotonesApplication;
import com.etermax.borbotones.model.Card;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    public List<Card> deckList;

    private static Deck instance;

    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }

        return instance;
    }

    private Deck() {
        Gson gson = new Gson();
        String json = getJson();

        Type listType = new TypeToken<ArrayList<Card>>(){}.getType();

        deckList = gson.fromJson(json, listType);
    }

    private String getJson() {
        String json = null;
        try {
            InputStream is = BorbotonesApplication.getContext().getAssets().open("deck.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    public List<Card> getCards(){
        return  deckList;
    }

    public Card cardWithId(int id) {
        for (Card card: deckList) {
           if(card.id == id)
               return card;
        }
        return null;
    }
    public Card getRandomCard()
    {
        return deckList.get(new Random().nextInt(deckList.size()));
    }
}
