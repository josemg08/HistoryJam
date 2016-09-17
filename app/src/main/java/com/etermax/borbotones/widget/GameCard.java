package com.etermax.borbotones.widget;

import android.annotation.TargetApi;
import android.content.Context;
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

public class GameCard extends RelativeLayout {


    private static final float RATIO = 16/9;
    private boolean isFlipped = true;
    private RelativeLayout rlContainer;
    private ImageView ivCardBackgrond;
    private ImageView ivFlippedBackground;
    private TextView tvAttack;
    private TextView tvLife;
    private View rootView;

    private int life;
    private int attack;

    private Card card;

    public Card getCard() {
        return card;
    }

    public GameCard(Context context) {
        super(context);
        setupView();
    }

    public GameCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView();
    }

    public GameCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GameCard(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setupView();
    }

    private void setupView() {

        inflate(getContext(), R.layout.gamecard_layout, this);

        rlContainer = (RelativeLayout) findViewById(R.id.rlContainer);
        ivCardBackgrond = (ImageView) findViewById(R.id.ivCardBackground);
        ivFlippedBackground = (ImageView) findViewById(R.id.ivFlippedBackground);
        tvAttack = (TextView) findViewById(R.id.tvAttackPoints);
        tvLife = (TextView) findViewById(R.id.tvLifePoints);


        isFlipped = true;
        rlContainer.setVisibility(GONE);
    }

    public void buildCard(Card card) {
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
        flip();
        updateLifeUI();
        updateAttackUI();
    }

    private void updateAttackUI() {
        tvAttack.setText("A:"+attack);
    }

    private void updateLifeUI() {
        tvLife.setText("V:"+life);
    }

    public void animateAttackAndUpdateLife(int updatedLife) {
        life = Math.max(updatedLife, 0);
        updateLifeUI();
    }
}
