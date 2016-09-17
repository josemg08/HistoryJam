package com.etermax.borbotones.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class HistoricEventSelectionActivity extends Activity implements HistoryListAdapter.OnHistoryListener {

    RecyclerView historicEventsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historic_event_selection_layout);
        historicEventsListView = (RecyclerView) findViewById(R.id.history_events_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        historicEventsListView.setLayoutManager(layoutManager);
        HistoryListAdapter adapter = new HistoryListAdapter(AnnalsOfHistory.getInstance().historyEventsList);
        adapter.setOnHistoryListener(this);
        historicEventsListView.setAdapter(adapter);
    }

    @Override
    public void onSelectedEvent(HistoryEvent event) {
        Intent intent = new Intent(getApplicationContext(), HistoryInformationActivity.class);
        intent.putExtra(HistoryInformationActivity.HISTORY_EVENT_KEY, event);
        startActivity(intent);
    }
}
//.___ End of HistoricEventSelectionActivity __./