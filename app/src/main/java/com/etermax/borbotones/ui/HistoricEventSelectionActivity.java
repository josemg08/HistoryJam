package com.etermax.borbotones.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.etermax.borbotones.R;
import com.etermax.borbotones.data.AnnalsOfHistory;
import com.etermax.borbotones.model.HistoryEvent;

/**
 * .___
 * Created by jose-gonzalez on 16/09/16.
 * __.
 */

public class HistoricEventSelectionActivity extends Activity {

    ListView historicEventsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historic_event_selection_layout);
        historicEventsListView = (ListView) findViewById(R.id.history_events_list);

        ArrayAdapter<HistoryEvent> adapter = new HistoryListAdapter(this, AnnalsOfHistory.getInstance().historyEventsList);

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