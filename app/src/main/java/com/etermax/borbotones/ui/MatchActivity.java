package com.etermax.borbotones.ui;

import android.app.Activity;
import android.os.Bundle;

import com.etermax.borbotones.R;
import com.etermax.borbotones.core.Deck;
import com.etermax.borbotones.core.Player;
import com.etermax.borbotones.widget.ArenaDeck;
import com.etermax.borbotones.widget.PlayerStatusWidget;

public class MatchActivity extends Activity {

    CardPlayedHolder playerCard1Played;
    CardPlayedHolder playerCard2Played;
    CardPlayedHolder playerCard3Played;
    CardPlayedHolder playerCard4Played;
    CardPlayedHolder playerCard5Played;
    CardPlayedHolder opponentCard1Played;
    CardPlayedHolder opponentCard2Played;
    CardPlayedHolder opponentCard3Played;
    CardPlayedHolder opponentCard4Played;
    CardPlayedHolder opponentCard5Played;

    PlayerStatusWidget playerStatus,opponentStatus;


    ArenaDeck mineDeck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        try {
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
        catch (Exception e){
            e.printStackTrace();
            //bite me
        }

        playerStatus      = (PlayerStatusWidget) findViewById(R.id.avatar_player);
        playerStatus.setName("Vasili");
        playerStatus.setEnergy(10);
        playerStatus.setLevel(123);
        playerStatus.setGender(false);
        opponentStatus    = (PlayerStatusWidget) findViewById(R.id.avatar_opponent);
        opponentStatus.setName("Lyudmila");
        opponentStatus.setEnergy(999);
        opponentStatus.setGender(true);

        playerCard1Played = (CardPlayedHolder) findViewById(R.id.card1_played);
        playerCard2Played = (CardPlayedHolder) findViewById(R.id.card2_played);
        playerCard3Played = (CardPlayedHolder) findViewById(R.id.card3_played);
        playerCard4Played = (CardPlayedHolder) findViewById(R.id.card4_played);
        playerCard5Played = (CardPlayedHolder) findViewById(R.id.card5_played);
        opponentCard1Played = (CardPlayedHolder) findViewById(R.id.card1_opponent_played);
        opponentCard2Played = (CardPlayedHolder) findViewById(R.id.card2_opponent_played);
        opponentCard3Played = (CardPlayedHolder) findViewById(R.id.card3_opponent_played);
        opponentCard4Played = (CardPlayedHolder) findViewById(R.id.card4_opponent_played);
        opponentCard5Played = (CardPlayedHolder) findViewById(R.id.card5_opponent_played);
    }
}
