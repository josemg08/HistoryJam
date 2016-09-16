package com.etermax.borbotones.data;

import com.etermax.borbotones.BorbotonesApplication;
import com.etermax.borbotones.model.Card;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Deck {

    List<Card> deckList;

    private static Deck instance;

    public static Deck getInstance() {
        if (instance == null) {
            new Deck();
        }

        return instance;
    }

    private Deck() {
        Gson gson = new Gson();
        String json = getJson();
        deckList = (List<Card>) gson.fromJson(json, Card.class);
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
}
