package com.etermax.borbotones.core;

import com.etermax.borbotones.model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author juan on 16/09/16.
 */

public class GameMachine {

    public interface GameStateListener {
        void cardPlaced(int cardId, int playerId);
        void cardPicked(int cardId, int playerId);
        void playerAttacked(int updatedPlayerLife, int playerTargetId);
        void cardAttacked(int updatedCardLife, int cardTargetId, int playerId);
        void turnChanged(int playerId);
        void gameState(GameStatus status, String message);
        void join(int userId);
    }

    public enum GameStatus {
        IDLE("idle"),
        READY("ready"),
        START("start"),
        INPROGRESS("inprogress"),
        FINISHED("finished");

        private final String value;

        GameStatus(final String text) {
            this.value = text;
        }

        public static GameStatus fromString(String text) {
            if (text != null) {
                for (GameStatus b : GameStatus.values()) {
                    if (text.equalsIgnoreCase(b.value)) {
                        return b;
                    }
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public static final String GAME_STATUS = "gamestatus";
    public static final int NUM_CARD = 5;

    public GameStatus gameStatus = GameStatus.IDLE;
    private static GameMachine gameMachine;
    private GameStateListener listener;
    private Player player;
    private Player opponent;


    private int currentPlayerId = 1;
    private boolean isAdmin;

    private LinkedList<Card> playerOneDeck = new LinkedList<>();
    private LinkedList<Card> playerTwoDeck = new LinkedList<>();

    private ArrayList<Card> currentPlayerOneCards = new ArrayList<>();
    private ArrayList<Card> currentPlayerTwoCards = new ArrayList<>();

    private ArrayList<Card> arenaOneDeckCards = new ArrayList<>();
    private ArrayList<Card> arenaTwoDeckCards = new ArrayList<>();


    private GameMachine(){

    }

    public void setEngineListener(GameStateListener stateListener) {
        listener = stateListener;
    }

    public static GameMachine machine() {
        if(gameMachine == null){
            gameMachine = new GameMachine();
        }
        return gameMachine;
    }

    public void startMatch(Player player, Player opponent, boolean isAdmin){

        this.isAdmin = isAdmin;
        this.player = player;
        this.opponent = opponent;

        gameStatus = GameStatus.START;
        listener.gameState(gameStatus,"started");

        loadDeckForPlayer(player.deck.cards, player.id);
        loadDeckForPlayer(opponent.deck.cards, opponent.id);

        if (isAdmin) {
            changeTurn();
        }
    }

    private void loadDeckForPlayer(List<com.etermax.borbotones.model.Card> cards, int playerId) {

        LinkedList<Card> list = deckForPlayer(playerId);

        for (Card card: cards) {
             list.add(card.getCopy());
        }

        for (int i = 0; i < NUM_CARD; i++) {
            pickCard(playerId);
        }
    }

    public void pickCard(int playerId){

        Card card = deckForPlayer(playerId).poll();
        getCurrentCards(playerId).add(card);

        if(isAdmin) {
            listener.cardPicked(card.id, playerId);
        }
    }

    public void placeMyCard(int cardId) {

        placeCard(cardId,player.id);
        listener.cardPlaced(cardId, player.id);
    }
    public void placeOpponentCard(int cardId) {
        placeCard(cardId,opponent.id);
        listener.cardPlaced(cardId, opponent.id);
    }

    public void placeCard(int cardId, int playerId) {
        List<Card> currentCards = getCurrentCards(playerId);
        Card card = findCard(cardId, currentCards);

        currentCards.remove(card);
        getArenaCards(playerId).add(card);
    }

    private void changeTurn() {
        currentPlayerId = currentPlayerId == player.id ? opponent.id: player.id;
        listener.turnChanged(currentPlayerId);
    }

    private Card findCard(int cardId, List<Card> cards) {
        for (Card card: cards) {
            if(card.id == cardId)
                return card;
        }
        return null;
    }

    public ArrayList<Card> getCurrentCards(int playerId) {
      return playerId == player.id? currentPlayerOneCards : currentPlayerTwoCards;
    }

    public ArrayList<Card> getArenaCards(int playerId) {
        return playerId == player.id? arenaOneDeckCards : arenaTwoDeckCards;
    }

    private LinkedList<Card> deckForPlayer(int playerId) {
        return playerId == player.id? playerOneDeck : playerTwoDeck;
    }

    private Player playerWithId(int playerId) {
        return playerId == player.id? player : opponent;
    }

    public void endTurn() {
        changeTurn();
    }

    public void attackPlayer(int attackPoint) {
        opponent.life -= attackPoint;

        if(opponent.life <= 0){
            finishedGame(true);
        }

        listener.playerAttacked(opponent.life, opponent.id);
    }

    public void receiveUserAttack(int updatedLife,int playerId) {
        playerWithId(playerId).life = updatedLife;
    }

    public void receiveCardAttack(int updatedLife, int cardId, int playerId) {
        List<Card> cards = getArenaCards(playerId);
        Card targetCard = findCard(cardId,cards);
        targetCard.defense = updatedLife;
        if(targetCard.defense<= 0) {
            cards.remove(targetCard);
        }
    }

    public void attackCard(int attackPoint, int cardId) {
        reduceCardLife(attackPoint,cardId,opponent.id);
    }

    private void reduceCardLife(int attackPoint, int cardId, int playerId) {

        List<Card> cards = getArenaCards(playerId);
        Card targetCard = findCard(cardId,cards);

        targetCard.defense-= attackPoint;

        if(targetCard.defense <=0){
            cards.remove(targetCard);
        }

        listener.cardAttacked(targetCard.defense, cardId, playerId);
    }


    public void finishedGame(boolean iWon){
        listener.gameState(GameStatus.FINISHED,iWon? ""+player.id : ""+opponent.id);
    }

    boolean isMineTurn(){
        return currentPlayerId == player.id;
    }

}
