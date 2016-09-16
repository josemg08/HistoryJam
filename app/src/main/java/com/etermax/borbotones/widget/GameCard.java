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
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int widthWithoutPadding = width - getPaddingLeft() - getPaddingRight();
        int heigthWithoutPadding = height - getPaddingTop() - getPaddingBottom();

        int maxWidth = (int) (heigthWithoutPadding * RATIO);
        int maxHeight = (int) (widthWithoutPadding / RATIO);

        width = getContext().getResources().getDimensionPixelSize(R.dimen.card_width);
        height = getContext().getResources().getDimensionPixelSize(R.dimen.card_height);
//        if (widthWithoutPadding  < maxWidth) {
//
//            width = maxWidth + getPaddingLeft() + getPaddingRight();
//        } else {
//            height = maxHeight + getPaddingTop() + getPaddingBottom();
//        }

        setMeasuredDimension(width, height);
    }
    private void setupView() {

        inflate(getContext(), R.layout.gamecard_layout, this);

        float width = getContext().getResources().getDimensionPixelSize(R.dimen.card_width);
        float height = getContext().getResources().getDimensionPixelSize(R.dimen.card_width);

//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams((int) width,(int)height);
//        rootView.setLayoutParams(layoutParams);
//        addView(rootView);

        rlContainer = (RelativeLayout) findViewById(R.id.rlContainer);
        ivCardBackgrond = (ImageView) findViewById(R.id.ivCardBackground);
        ivFlippedBackground = (ImageView) findViewById(R.id.ivFlippedBackground);
        tvAttack = (TextView) findViewById(R.id.tvAttackPoints);
        tvLife = (TextView) findViewById(R.id.tvLifePoints);


        isFlipped = true;
        rlContainer.setVisibility(INVISIBLE);
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
        rlContainer.setVisibility(isFlipped ? INVISIBLE : VISIBLE);
        ivFlippedBackground.setVisibility(isFlipped ? VISIBLE : INVISIBLE);
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
