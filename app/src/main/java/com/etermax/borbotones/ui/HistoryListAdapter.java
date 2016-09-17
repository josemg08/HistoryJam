package com.etermax.borbotones.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.etermax.borbotones.model.HistoryEvent;
import com.etermax.borbotones.widget.HistoryRowView;

import java.util.List;

/**
 * .___
 * Created by jose-gonzalez on 16/09/16.
 * __.
 */

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.ViewHolder> {

    private List<HistoryEvent> historyEvents;
    private OnHistoryListener onHistoryListener = getDummyListener();


    public HistoryListAdapter(List<HistoryEvent> historyEvents) {
        this.historyEvents = historyEvents;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public HistoryRowView historyRowView;

        public ViewHolder(HistoryRowView v) {
            super(v);
            historyRowView = v;
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HistoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(HistoryRowView.build(parent.getContext()));
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.historyRowView.loadData(historyEvents.get(position));
        holder.historyRowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHistoryListener.onSelectedEvent(historyEvents.get(position));

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return historyEvents.size();
    }

    private OnHistoryListener getDummyListener(){
        return new OnHistoryListener() {
            @Override
            public void onSelectedEvent(HistoryEvent event) {

            }
        };
    }
    public void setOnHistoryListener(OnHistoryListener onHistoryListener) {
        this.onHistoryListener = onHistoryListener;
    }

    public interface OnHistoryListener{
        void onSelectedEvent(HistoryEvent event);
    }

}