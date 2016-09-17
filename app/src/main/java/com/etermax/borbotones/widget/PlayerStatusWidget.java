package com.etermax.borbotones.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.etermax.borbotones.R;

public class PlayerStatusWidget extends RelativeLayout implements View.OnClickListener {

    private CustomFontTextView mPlayerName;
    private CustomFontTextView mEnergy;
    private CustomFontTextView mLevel;
    private OnAvatarOpponentListener opponentListener;

    public PlayerStatusWidget(Context context) {
        super(context);
        setupView();
    }

    public PlayerStatusWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView();
    }

    public PlayerStatusWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView();
    }

    private void setupView() {
        inflate(getContext(), R.layout.player_status_widget_layout, this);

        mPlayerName = (CustomFontTextView) findViewById(R.id.player_name);
        mEnergy = (CustomFontTextView) findViewById(R.id.energy);
        mLevel = (CustomFontTextView) findViewById(R.id.level);
        setOnClickListener(this);
    }

    public void setName(String name) {
        mPlayerName.setText(name);
    }

    public void setGender(boolean isFemale) {
        ((ImageView) findViewById(R.id.avatar)).setImageDrawable(isFemale ? getResources().getDrawable(R.drawable.avatar_m) : getResources().getDrawable(R.drawable.avatar_h));
    }

    public void setEnergy(int energy) {
        mEnergy.setText(Integer.toString(energy));
    }

    public void setLevel(int level) {
        mLevel.setText(Integer.toString(level));
    }

    @Override
    public void onClick(View v) {
        if (opponentListener != null) {
            opponentListener.onAttackAvatar();
        }
    }

    public interface OnAvatarOpponentListener {
        void onAttackAvatar();
    }

    public void setOpponentListener(OnAvatarOpponentListener opponentListener) {
        this.opponentListener = opponentListener;
    }
}
