package com.etermax.borbotones.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.etermax.borbotones.R;
import com.etermax.borbotones.core.Player;

/**
 * @author juan on 16/09/16.
 */

public class PlayerStatusWidget extends RelativeLayout {

    private CustomFontTextView ctftvPlayerName;
    private CustomFontTextView ctftvPlayerLife;
    private Player player;
    private View rootView;

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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PlayerStatusWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setupView();
    }

    private void setupView() {

        rootView = inflate(getContext(), R.layout.player_status_widget_layout, null);
        addView(rootView);

        ctftvPlayerName = (CustomFontTextView)findViewById(R.id.cftvPlayerName);
        ctftvPlayerLife = (CustomFontTextView)findViewById(R.id.cftvPlayerLife);
    }

    public void build(Player player) {
        this.player = player;

        ctftvPlayerName.setText(player.name);
        ctftvPlayerLife.setText(player.life);
    }

}
