package com.etermax.borbotones.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.etermax.borbotones.R;
import com.etermax.borbotones.core.Deck;
import com.etermax.borbotones.core.Player;
import com.etermax.borbotones.model.Card;

import java.util.ArrayList;

/**
 * @author juan on 16/09/16.
 */

public class ArenaDeck extends RelativeLayout {

    private static final int DISPLAY_CARD_NUMBER = 5;

    public interface DeckActions {
        void userPressed(Card attackerCard);
        void arenaCardPressed(Card attackerCard);
        void nextCard(Card selectedCard);
        void cardSelected(Card selectedCard);
    }

    private View rootView;
    private LinearLayout llArenaCards;
    private LinearLayout llCurrentCards;
    private DeckActions deckActions;

    private ArrayList<GameCard> arenaCards = new ArrayList<>();
    private ArrayList<GameCard> currentCards = new ArrayList<>();

    public ArenaDeck(Context context) {
        super(context);
        setupView();
    }

    public ArenaDeck(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView();
    }

    public ArenaDeck(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ArenaDeck(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setupView();
    }

    public void buildArena(Player player, Deck deck) {

        //TODO: deck.setCards = deck.cards;

    }

    private void setupView() {

        rootView = inflate(getContext(), R.layout.deck_arena_layout, null);
        addView(rootView);
//
//        rlContainer = (RelativeLayout) rootView.findViewById(R.id.rlContainer);
//        ivCardBackgrond = (ImageView) rootView.findViewById(R.id.ivCardBackground);
//        ivFlippedBackground = (ImageView) rootView.findViewById(R.id.ivFlippedBackground);
//        tvAttack = (TextView) rootView.findViewById(R.id.tvAttackPoints);
//        tvLife = (TextView) rootView.findViewById(R.id.tvLifePoints);
//
//
//        isFlipped = true;
//        rlContainer.setVisibility(INVISIBLE);
    }

    private void setupCards() {

        //TODO: addCardsToCurrentCards(deck.getcards(5))
        addCardsToCurrentCards(null);
    }

    private void addCardsToCurrentCards(Card... cards) {

        for (Card card: cards) {

            final GameCard gameCard = new GameCard(getContext());

            gameCard.buildCard(card);
            gameCard.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCardClicked(gameCard.getCard(),v);
                }
            });

            llCurrentCards.addView(gameCard);
        }
    }

    public void onCardClicked(Card card,View cardView) {
        deckActions.cardSelected(card);
        moveCardToArena(card,cardView);
    }

    public void moveCardToArena(Card card,View cardView) {
        llCurrentCards.removeView(cardView);

        //TODO: arena formatted card
        llArenaCards.addView(cardView);

        addCardToArena(card);
    }

    public void onArenaCardClicked(Card card,View cardView) {
        deckActions.arenaCardPressed(card);
//        moveCardToArena(card,cardView);
    }

    private void addCardToArena(Card card) {

        final GameCard gameCard = new GameCard(getContext());

        gameCard.buildCard(card);
        gameCard.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onArenaCardClicked(gameCard.getCard(),v);
            }
        });

        llArenaCards.addView(new View(getContext()));
    }

    public void receiveCardDamage(int targetCardId, int attackPoints, int updatedLife) {

        for (GameCard gameCard: arenaCards) {
            if(gameCard.getCard().mDrawableId == targetCardId){
                gameCard.animateAttackAndUpdateLife(updatedLife);
                return;
            }
        }
    }

    public void receiveUserDamage(int attackPoints, int updatedLife) {
    }
}
