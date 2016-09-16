package com.etermax.borbotones.ui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.etermax.borbotones.R;
import com.etermax.borbotones.core.Deck;
import com.etermax.borbotones.core.Player;
import com.etermax.borbotones.widget.ArenaDeck;

public class MatchActivity extends Activity {

    ArenaDeck mineDeck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        mineDeck = (ArenaDeck) findViewById(R.id.adMineDeck);


        Player player = new Player();
        player.name = "dummyName";
        player.life = 1000;

        Deck randomDeck = new Deck();

        for (int i = 0; i < 15; i++) {
            randomDeck.cards.add(com.etermax.borbotones.data.Deck.getInstance().getRandomCard());
        }
//
        mineDeck.buildArena(player, randomDeck);
    }
}
