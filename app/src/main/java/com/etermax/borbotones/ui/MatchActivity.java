package com.etermax.borbotones.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.etermax.borbotones.R;
import com.etermax.borbotones.core.Deck;
import com.etermax.borbotones.core.GameMachine;
import com.etermax.borbotones.core.Player;
import com.etermax.borbotones.model.Card;
import com.etermax.borbotones.widget.ArenaDeck;
import com.etermax.borbotones.widget.DeckView;
import com.etermax.borbotones.widget.GameCard;
import com.etermax.borbotones.widget.PlayerStatusWidget;

import java.util.ArrayList;

public class MatchActivity extends Activity implements  CardPlayedHolder.OnCardHolderOpponent,CardPlayedHolder.OnCardHolderListener{

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

    GameCard gameCardPlayer1;
    GameCard gameCardPlayer2;
    GameCard gameCardPlayer3;
    GameCard gameCardPlayer4;
    GameCard gameCardPlayer5;

    GameCard gameCardOpponent1;
    GameCard gameCardOpponent2;
    GameCard gameCardOpponent3;
    GameCard gameCardOpponent4;
    GameCard gameCardOpponent5;

    PlayerStatusWidget playerStatus,opponentStatus;

    ArrayList<GameCard> mineGameCards = new ArrayList<>();
    ArrayList<GameCard> opponentGameCards = new ArrayList<>();
    ArrayList<CardPlayedHolder> minePlaceholders = new ArrayList<>();

    GameMachine gameMachine;
    private Card currentCard;
    DeckView mineDeck;
    DeckView opponentDeck;
    Player player;
    Player player2;
    com.etermax.borbotones.data.Deck deckDataSource;
    private int currentTurn =0;
    private boolean myTurn = false;
    private int attacks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        player = new Player();
        player.id = 0;
        player.name = "Bananero";
        player.life = 999;

        player2 = new Player();
        player2.id = 1;
        player2.name = "Vasili";
        player2.life = 999;

        Deck randomDeck = new Deck();
        deckDataSource = com.etermax.borbotones.data.Deck.getInstance();

        for (int i = 0; i < 15; i++) {
            randomDeck.cards.add(deckDataSource.getRandomCard());
        }

        Deck randomDeck2 = new Deck();

        for (int i = 0; i < 15; i++) {
            randomDeck2.cards.add(deckDataSource.getRandomCard());
        }

        player.deck = randomDeck;
        player2.deck = randomDeck2;


        mineDeck = (DeckView) findViewById(R.id.deck_player_view);
        opponentDeck = (DeckView) findViewById(R.id.deck_opponent_view);

        playerStatus      = (PlayerStatusWidget) findViewById(R.id.avatar_player);
        playerStatus.setName(player.name);
        playerStatus.setEnergy(player.life);
        playerStatus.setLevel(123);
        playerStatus.setGender(false);
        opponentStatus    = (PlayerStatusWidget) findViewById(R.id.avatar_opponent);
        opponentStatus.setName(player2.name);
        opponentStatus.setEnergy(player2.life);
        opponentStatus.setGender(true);

        playerCard1Played = (CardPlayedHolder) findViewById(R.id.card1_played);
        playerCard2Played = (CardPlayedHolder) findViewById(R.id.card2_played);
        playerCard3Played = (CardPlayedHolder) findViewById(R.id.card3_played);
        playerCard4Played = (CardPlayedHolder) findViewById(R.id.card4_played);
        playerCard5Played = (CardPlayedHolder) findViewById(R.id.card5_played);

        minePlaceholders.add(playerCard1Played);
        minePlaceholders.add(playerCard2Played);
        minePlaceholders.add(playerCard3Played);
        minePlaceholders.add(playerCard4Played);
        minePlaceholders.add(playerCard5Played);

        opponentCard1Played = (CardPlayedHolder) findViewById(R.id.card1_opponent_played);
        opponentCard2Played = (CardPlayedHolder) findViewById(R.id.card2_opponent_played);
        opponentCard3Played = (CardPlayedHolder) findViewById(R.id.card3_opponent_played);
        opponentCard4Played = (CardPlayedHolder) findViewById(R.id.card4_opponent_played);
        opponentCard5Played = (CardPlayedHolder) findViewById(R.id.card5_opponent_played);


        gameCardPlayer1 = (GameCard) findViewById(R.id.card_player_1);
        gameCardPlayer2 = (GameCard) findViewById(R.id.card_player_2);
        gameCardPlayer3 = (GameCard) findViewById(R.id.card_player_3);
        gameCardPlayer4 = (GameCard) findViewById(R.id.card_player_4);
        gameCardPlayer5 = (GameCard) findViewById(R.id.card_player_5);

        gameCardOpponent1 = (GameCard) findViewById(R.id.card_opponent_1);
        gameCardOpponent2 = (GameCard) findViewById(R.id.card_opponent_2);
        gameCardOpponent3 = (GameCard) findViewById(R.id.card_opponent_3);
        gameCardOpponent4 = (GameCard) findViewById(R.id.card_opponent_4);
        gameCardOpponent5 = (GameCard) findViewById(R.id.card_opponent_5);

        mineGameCards.add(gameCardPlayer1);
        mineGameCards.add(gameCardPlayer2);
        mineGameCards.add(gameCardPlayer3);
        mineGameCards.add(gameCardPlayer4);
        mineGameCards.add(gameCardPlayer5);

        opponentGameCards.add(gameCardOpponent1);
        opponentGameCards.add(gameCardOpponent2);
        opponentGameCards.add(gameCardOpponent3);
        opponentGameCards.add(gameCardOpponent4);
        opponentGameCards.add(gameCardOpponent5);

        gameCardOpponent1 = (GameCard) findViewById(R.id.card_opponent_1);
        gameCardOpponent2 = (GameCard) findViewById(R.id.card_opponent_2);
        gameCardOpponent3 = (GameCard) findViewById(R.id.card_opponent_3);
        gameCardOpponent4 = (GameCard) findViewById(R.id.card_opponent_4);
        gameCardOpponent5 = (GameCard) findViewById(R.id.card_opponent_5);



        mineDeck.setCards(player.deck.cards);
        opponentDeck.setCards(player2.deck.cards);


        bindListeners();
        setupGame();
    }

    private void bindListeners(){
        for (GameCard gamecard : mineGameCards  ) {
            gamecard.setGameCardListener(new GameCard.OnGameCardListener() {
                @Override
                public void onCardConsume(Card card) {
                    gameMachine.placeMyCard(card.id);
                }
            });
        }


        mineDeck.setOnDeckListener(new DeckView.OnDeckListener() {
            @Override
            public void onCardSelected(Card card) {
                 addcardtofirstSlot(card.id,player.id);
            }

            @Override
            public void onEmptiedDeck() {

            }
        });
    }

    private void setupGame(){
//
//        mineDeck.buildArena(player, randomDeck);

        gameMachine = GameMachine.machine();
        findViewById(R.id.placeCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCard = gameMachine.getCurrentCards(player2.id).get(0);
                opponentCard1Played.setCard(currentCard);
                gameCardForUserAndCard(currentCard.id,player2.id).hide();
                gameMachine.placeCard(currentCard.id,player2.id);
            }
        });

        findViewById(R.id.attack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMachine.receiveUserAttack(gameMachine.playerLife() -100,player.id);
                playerStatus.setEnergy(gameMachine.playerLife());
            }
        });

        findViewById(R.id.attackCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card card = gameMachine.getArenaCards(player2.id).get(0);
                int value = card.defense -10;

                gameMachine.receiveCardAttack(card.defense-10,card.id,player.id);
                if (value<=0) {
                   opponentCard1Played.setCard(null);
                }
            }
        });

        findViewById(R.id.endTurn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMachine.endTurn();
            }
        });

        gameMachine.setEngineListener(new GameMachine.GameStateListener() {
            @Override
            public void cardPlaced(int cardId, int playerId) {
                Log.d(MatchActivity.class.getSimpleName(),"CARD PLACED");
                if(playerId == player.id){
                    playerCard1Played.setCard(deckDataSource.cardWithId(cardId));

                }else{
                    gameCardForUserAndCard(cardId,player2.id).hide();
                    opponentCard1Played.setCard(deckDataSource.cardWithId(cardId));
                }
            }

            @Override
            public void cardPicked(int cardId, int playerId) {
                Log.d(MatchActivity.class.getSimpleName(),"CARD PICKED");
                addcardtofirstSlot(cardId,playerId);
            }

            @Override
            public void playerAttacked(int updatedPlayerLife, int playerTargetId) {
                Log.d(MatchActivity.class.getSimpleName(),"PLAYER ATTACKED");
                if(playerTargetId == player.id){
                    playerStatus.setEnergy(updatedPlayerLife);
                }
                else {
                    opponentStatus.setEnergy(updatedPlayerLife);
                }
            }

            @Override
            public void cardAttacked(int updatedCardLife, int cardTargetId, int playerId) {
                Log.d(MatchActivity.class.getSimpleName(),"CARD ATTACKED");
            }

            @Override
            public void turnChanged(int playerId) {
                Log.d(MatchActivity.class.getSimpleName(),"TURN CHANGED");
//                if(playerId == player2.id){
//                    myTurn = false;
//                    if(gameMachine.getArenaCards(player2.id).size() == 0){
//                        currentCard = gameMachine.getCurrentCards(player2.id).get(0);
//                        opponentCard1Played.setCard(currentCard);
//                        gameCardForUserAndCard(currentCard.id,player2.id).hide();
//                        gameMachine.placeCard(currentCard.id,player2.id);
//                    }
//                }else {
//                    currentTurn ++;
//                    myTurn = true;
//
//                }
            }

            @Override
            public void gameState(GameMachine.GameStatus status, String message) {
                Log.d(MatchActivity.class.getSimpleName(),"STATUS "+message);
            }

            @Override
            public void join(int userId) {
                Log.d(MatchActivity.class.getSimpleName(),"user JOINED");
            }
        });
        manageFight();
        gameMachine.startMatch(player,player2,true);
    }

    private void addcardtofirstSlot(int cardId, int playerId) {
        boolean isOpponent = playerId != player.id;
        for (GameCard gameCard: isOpponent? opponentGameCards:mineGameCards) {
            if (gameCard.getCard() != null){
            }else{
                gameCard.buildCard(deckDataSource.cardWithId(cardId),isOpponent);
                return;
            }
        }
    }

    private GameCard gameCardForUserAndCard(int cardId,int playerId){
        for (GameCard gameCard: playerId == player.id ?mineGameCards:opponentGameCards) {
            if( gameCard.getCard() == null ){

            }else if(gameCard.getCard().id == cardId){
                return gameCard;
            }
        }
        return null;
    }

    private CardPlayedHolder placeholdeForCard(int cardId) {
        for (CardPlayedHolder placeholder: minePlaceholders) {
              if(placeholder.getCard().id == cardId){
                  return placeholder;
              }
        }
        return null;
    }


    private void manageFight(){

        playerCard1Played.setHolderListener(this);
        playerCard2Played.setHolderListener(this);
        playerCard3Played.setHolderListener(this);
        playerCard4Played.setHolderListener(this);
        playerCard5Played.setHolderListener(this);

        opponentCard1Played.setHolderOpponentListener(this);
        opponentCard2Played.setHolderOpponentListener(this);
        opponentCard3Played.setHolderOpponentListener(this);
        opponentCard4Played.setHolderOpponentListener(this);
        opponentCard5Played.setHolderOpponentListener(this);
        opponentStatus.setOpponentListener(new PlayerStatusWidget.OnAvatarOpponentListener() {
            @Override
            public void onAttackAvatar() {
                if(onCardPlayerSelected && cardSelected!=null){
                    gameMachine.attackPlayer(cardSelected.attack);
                    onCardPlayerSelected = false;
                    cardSelected = null;
                }
            }
        });
    }

    private boolean onCardPlayerSelected = false;
    private Card cardSelected = null;


    @Override
    public void onCardAttacked(Card card, View view) {
        if(onCardPlayerSelected && cardSelected!=null){
            gameMachine.attackCard(cardSelected.attack, card.id);
            onCardPlayerSelected = false;
            cardSelected = null;
            Toast.makeText(this, "onCardAttacked " +card.name, Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onCardMarked(Card card, View view) {
        if(!onCardPlayerSelected) {
            Toast.makeText(this, "onCardMarked " +card.name, Toast.LENGTH_SHORT).show();
            onCardPlayerSelected = true;
            cardSelected = card;
        }
    }
}
