package com.etermax.borbotones.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.etermax.borbotones.R;

public class SplashActivity extends AppCompatActivity implements Runnable {

    private static final int SPLASH_DURATION = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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
