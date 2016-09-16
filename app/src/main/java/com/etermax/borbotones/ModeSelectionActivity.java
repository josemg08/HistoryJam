package com.etermax.borbotones;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * .___
 * Created by jose-gonzalez on 16/09/16.
 * __.
 */

public class ModeSelectionActivity extends AppCompatActivity implements Button.OnClickListener {

    private Button versusButton;
    private Button historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_selection_layout);
        versusButton = (Button) findViewById(R.id.vs_button);
        versusButton.setOnClickListener(this);
        historyButton = (Button) findViewById(R.id.history_button);
        historyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.vs_button:
                break;
            case R.id.history_button:
                break;
        }
    }
}
//.___ End of ModeSelectionActivity __./