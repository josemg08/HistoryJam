package com.etermax.borbotones.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.etermax.borbotones.R;
import com.etermax.borbotones.model.Card;
import com.etermax.borbotones.model.HistoryEvent;

public class HistoryInformationActivity extends AppCompatActivity {

    public static final String HISTORY_EVENT_KEY = "history_event_key";

    private TextView mHistoryEventTitle;
    private TextView mHistoryEventDescription;
    private ImageView mHistoryEventImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_information);

        mHistoryEventTitle = (TextView) findViewById(R.id.history_event_title);
        mHistoryEventDescription = (TextView) findViewById(R.id.history_event_description);
        mHistoryEventImage = (ImageView) findViewById(R.id.history_event_image);
        Button mPlayButton = (Button) findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Go to game
            }
        });

        Intent intent = getIntent();
        HistoryEvent historyEvent = (HistoryEvent) intent.getSerializableExtra(HISTORY_EVENT_KEY);
        setHistoryEventInfo(historyEvent);
    }

    private void setHistoryEventInfo(HistoryEvent historyEvent){
        mHistoryEventDescription.setText(historyEvent.description);
        mHistoryEventTitle.setText(historyEvent.title);
        try {
            mHistoryEventImage.setImageDrawable(getResources().getDrawable(historyEvent.resource));
        }
        catch (Exception e){}
    }

}
//.___ End of HistoryInformationActivity __./
