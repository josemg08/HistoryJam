package com.etermax.borbotones.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.etermax.borbotones.R;
import com.etermax.borbotones.model.Card;

/**
 * @author juan on 16/09/16.
 */

public class GameCard extends RelativeLayout implements View.OnClickListener {


    private static final float RATIO = 16 / 9;
    private boolean isFlipped = true;
    private RelativeLayout rlContainer;
    private ImageView ivCardBackgrond;
    private ImageView ivFlippedBackground;
    private TextView tvAttack;
    private TextView tvLife;
    private View rootView;
    private boolean isOpponent;

    private int life;
    private int attack;

    private Card card;
    private OnGameCardListener listener = getDummyListener();
    public Card getCard() {
        return card;
    }

    public GameCard(Context context) {
        super(context);
        setupView(null);
    }

    public GameCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(attrs);
    }

    public GameCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GameCard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setupView(attrs);
    }

    private void setupView(AttributeSet attrs) {

        inflate(getContext(), R.layout.gamecard_layout, this);
        rlContainer = (RelativeLayout) findViewById(R.id.rlContainer);
        ivCardBackgrond = (ImageView) findViewById(R.id.ivCardBackground);
        ivFlippedBackground = (ImageView) findViewById(R.id.ivFlippedBackground);
        tvAttack = (TextView) findViewById(R.id.tvAttackPoints);
        tvLife = (TextView) findViewById(R.id.tvLifePoints);
        isFlipped = true;
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.GameCard);
            isFlipped = a.getBoolean(R.styleable.GameCard_isFlipped, true);
            a.recycle();
        }
        setOnClickListener(this);
        updateUI();
    }

    public void buildCard(Card card, boolean isOpponent) {
        this.isOpponent = isOpponent;
        this.card = card;

        life = card.defense;
        attack = card.attack;

        updateUI();
    }

    public void flip() {
        isFlipped = !isFlipped;

        TransitionSet set = new TransitionSet()
                .addTransition(new Fade())
                .setInterpolator(new FastOutLinearInInterpolator());

//        TransitionManager.beginDelayedTransition((RelativeLayout) rootView, set);
//        rlContainer.setVisibility(isFlipped ? INVISIBLE : VISIBLE);
//        ivFlippedBackground.setVisibility(isFlipped ? VISIBLE : INVISIBLE);
    }

    private void updateUI() {
        updateCard();
        updateLifeUI();
        updateAttackUI();
    }

    private void updateCard() {
        ivFlippedBackground.setVisibility(isFlipped || card == null ? VISIBLE : GONE);
        if(!isOpponent){
            ivCardBackgrond.setVisibility(!isFlipped && card != null ? VISIBLE : GONE);
        }
    }

    private void updateAttackUI() {
        tvAttack.setText("A:" + attack);
    }

    private void updateLifeUI() {
        tvLife.setText("V:" + life);
    }

    public void animateAttackAndUpdateLife(int updatedLife) {
        life = Math.max(updatedLife, 0);
        updateLifeUI();
    }

    public void setGameCardListener(OnGameCardListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
//        && card!=null
        if(!isFlipped ){
            ivCardBackgrond.setVisibility(INVISIBLE);
            ivFlippedBackground.setVisibility(INVISIBLE);
            listener.onCardConsume(card);
            card = null;
        }
    }

    public void hide(){
        ivCardBackgrond.setVisibility(INVISIBLE);
        ivFlippedBackground.setVisibility(INVISIBLE);
        card = null;
    }

    private OnGameCardListener getDummyListener(){
        return new OnGameCardListener() {
            @Override
            public void onCardConsume(Card card) {

            }
        };
    }
    public  interface OnGameCardListener{
        void onCardConsume(Card card);
    }

}
