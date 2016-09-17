package com.etermax.borbotones.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.etermax.borbotones.R;
import com.etermax.borbotones.core.Player;

public class PlayerStatusWidget extends RelativeLayout {

    private CustomFontTextView mPlayerName;
    private CustomFontTextView mEnergy;
    private CustomFontTextView mLevel;

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

        mPlayerName = (CustomFontTextView)findViewById(R.id.player_name);
        mEnergy     = (CustomFontTextView)findViewById(R.id.energy);
        mLevel      = (CustomFontTextView)findViewById(R.id.level);
    }

    public void setName(String name)
    {
        mPlayerName.setText(name);
    }

    public void setEnergy(int available, int total)
    {
        mEnergy.setText(available + "/" + total );
    }

    public void setLevel(int level)
    {
        mEnergy.setText(Integer.toString(level) );
    }

    public void build(Player player) {
        //mPlayer = player;
        //mPlayerName.setText(player.name);
        //mEnergy.setText(player.life);
    }
}
