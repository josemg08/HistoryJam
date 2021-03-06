package com.etermax.borbotones.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.etermax.borbotones.R;
import com.etermax.borbotones.data.Deck;
import com.etermax.borbotones.data.FriendParser;

public class SplashActivity extends Activity implements Runnable {

    private static final int SPLASH_DURATION = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Deck.getInstance();
        FriendParser.getInstance();
        runSplash();
    }

    private void onTimeElapsed() {
        Intent intent = new Intent(this, ModeSelectionActivity.class );
        startActivity(intent);
    }

    private void runSplash() {
        new Handler().postDelayed(this, SPLASH_DURATION);
    }

    @Override
    public void run() {
        onTimeElapsed();
        finish();
    }
}
