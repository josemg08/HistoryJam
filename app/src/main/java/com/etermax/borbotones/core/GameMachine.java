package com.etermax.borbotones.core;

/**
 * @author juan on 16/09/16.
 */

public class GameMachine {



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
    public GameStatus gameStatus = GameStatus.IDLE;


    public static GameMachine gameMachine;

    private GameMachine(){
    }

    public static GameMachine machine() {
        if(gameMachine == null){
            gameMachine = new GameMachine();
        }
        return gameMachine;
    }

    public void startMatch(Player playerOne, Player playerTwo){
        
    }
}
