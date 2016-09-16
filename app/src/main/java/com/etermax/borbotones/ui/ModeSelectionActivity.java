package com.etermax.borbotones.ui;

import android.app.Activity;
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

public class ModeSelectionActivity extends Activity implements Button.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mode_selection_layout);

        findViewById(R.id.my_deck_button).setOnClickListener( this );
        findViewById(R.id.vs_button).setOnClickListener( this );
        findViewById(R.id.history_button).setOnClickListener( this );
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch(view.getId()){
            case R.id.vs_button:
                intent = new Intent(this, HistoricEventSelectionActivity.class );
                break;
            case R.id.history_button:
                intent = new Intent(this, HistoricEventSelectionActivity.class );
                break;
            case R.id.my_deck_button:
                intent = new Intent(this, MyDeckActivity.class );
                break;
        }

        if( intent != null ) {
            startActivity(intent);
        }
    }
}
//.___ End of ModeSelectionActivity __./