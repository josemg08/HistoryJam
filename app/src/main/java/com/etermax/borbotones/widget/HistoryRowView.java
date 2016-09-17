package com.etermax.borbotones.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.etermax.borbotones.R;
import com.etermax.borbotones.model.HistoryEvent;

/**
 * Created by charly on 17/9/16.
 */
public class HistoryRowView extends LinearLayout{


    private TextView titleTextView;
    private ImageView historyImageView;


    public HistoryRowView(Context context) {
        super(context);
        init();
    }

    public HistoryRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HistoryRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HistoryRowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init(){
        inflate(getContext(), R.layout.history_event_list_item, this);
        titleTextView = (TextView) findViewById(R.id.history_item_title);
        historyImageView = (ImageView) findViewById(R.id.history_item_image);
    }

    public void loadData(HistoryEvent event){
        titleTextView.setText(event.title);
        Glide.with(getContext()).load(event.getResourceId()).into(historyImageView);
    }


    public static HistoryRowView build( Context context){
        HistoryRowView historyRowView = new HistoryRowView(context);
        return historyRowView;
    }
}
