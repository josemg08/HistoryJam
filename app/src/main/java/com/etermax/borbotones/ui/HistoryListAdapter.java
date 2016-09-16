package com.etermax.borbotones.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.etermax.borbotones.R;
import com.etermax.borbotones.model.HistoryEvent;

import java.util.ArrayList;

/**
 * .___
 * Created by jose-gonzalez on 16/09/16.
 * __.
 */

public class HistoryListAdapter extends ArrayAdapter<HistoryEvent> {

    public HistoryListAdapter(Context context, ArrayList<HistoryEvent> historyEventArrayList) {
        super(context, 0, historyEventArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HistoryEvent historyEvent = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.history_event_list_item, parent, false);
        }
        ImageView historyEventItemImage = (ImageView) convertView.findViewById(R.id.history_item_image);
        TextView historyEventItemTitle = (TextView) convertView.findViewById(R.id.history_item_title);
        historyEventItemImage.setImageDrawable(getContext().getResources().getDrawable(
                historyEvent.getResourceId()));
        historyEventItemTitle.setText(historyEvent.title);
        return convertView;
    }
}