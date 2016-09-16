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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.etermax.borbotones.R;
import com.etermax.borbotones.model.Card;

/**
 * @author juan on 16/09/16.
 */

public class GameCard extends RelativeLayout {

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

        rootView = inflate(getContext(), R.layout.gamecard_layout, null);
        addView(rootView);

        rlContainer = (RelativeLayout) rootView.findViewById(R.id.rlContainer);
        ivCardBackgrond = (ImageView) rootView.findViewById(R.id.ivCardBackground);
        ivFlippedBackground = (ImageView) rootView.findViewById(R.id.ivFlippedBackground);
        tvAttack = (TextView) rootView.findViewById(R.id.tvAttackPoints);
        tvLife = (TextView) rootView.findViewById(R.id.tvLifePoints);


        isFlipped = true;
        rlContainer.setVisibility(INVISIBLE);
    }

    public void buildCard(Card card) {
        this.card = card;

        life = card.mDefense;
        attack = card.mAttack;

        updateLifeUI();
        updateAttackUI();
    }

    public void flip() {
        isFlipped = !isFlipped;

        TransitionSet set = new TransitionSet()
                .addTransition(new Fade())
                .setInterpolator(new FastOutLinearInInterpolator());

        TransitionManager.beginDelayedTransition((RelativeLayout) rootView, set);
        rlContainer.setVisibility(isFlipped ? INVISIBLE : VISIBLE);
        ivFlippedBackground.setVisibility(isFlipped ? VISIBLE : INVISIBLE);
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
