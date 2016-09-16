package com.etermax.borbotones.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.etermax.borbotones.R;
import com.etermax.borbotones.model.HistoryEvent;

/**
 * .___
 * Created by jose-gonzalez on 16/09/16.
 * __.
 */

public class HistoricEventSelectionActivity extends AppCompatActivity {

    ListView historicEventsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historic_event_selection_layout);
        historicEventsListView = (ListView) findViewById(R.id.history_events_list);

        // Defined Array values to show in ListView
        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        historicEventsListView.setAdapter(adapter);

        // ListView Item Click Listener
        historicEventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HistoryEvent historyEvent = new HistoryEvent();
                Intent intent = new Intent(getApplicationContext(), HistoryInformationActivity.class);
                intent.putExtra(HistoryInformationActivity.HISTORY_EVENT_KEY, historyEvent);
                startActivity(intent);
            }
        });
    }

}
//.___ End of HistoricEventSelectionActivity __./