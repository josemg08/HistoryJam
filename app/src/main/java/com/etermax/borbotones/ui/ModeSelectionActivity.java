package com.etermax.borbotones.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.etermax.borbotones.R;

/**
 * .___
 * Created by jose-gonzalez on 16/09/16.
 * __.
 */

public class ModeSelectionActivity extends AppCompatActivity implements Button.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_selection_layout);
        Button versusButton = (Button) findViewById(R.id.vs_button);
        assert versusButton != null;
        versusButton.setOnClickListener(this);
        Button historyButton = (Button) findViewById(R.id.history_button);
        assert historyButton != null;
        historyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()){
            case R.id.vs_button:
                intent = new Intent(this, HistoricEventSelectionActivity.class );
                startActivity(intent);
                break;
            case R.id.history_button:
                intent = new Intent(this, HistoricEventSelectionActivity.class );
                startActivity(intent);
                break;

            case R.id.my_deck_button:
                intent = new Intent(this, HistoricEventSelectionActivity.class );
                startActivity(intent);
                break;
        }
    }
}
//.___ End of ModeSelectionActivity __./