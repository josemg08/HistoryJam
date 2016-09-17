package com.etermax.borbotones.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.etermax.borbotones.R;
import com.etermax.borbotones.model.Friend;

import java.util.Random;

/**
 * Created by charly on 17/9/16.
 */
public class FriendRowView extends RelativeLayout {

    private TextView nameTextView;
    private ImageView avatarImageView;
    private int backgrounds[] = {R.drawable.aqua_selector_background, R.drawable.blue_selector_background, R.drawable.brown_selector_background, R.drawable.yellow_selector_background,
            R.drawable.purple_selector_background, R.drawable.orange_selector_background};

    public FriendRowView(Context context) {
        super(context);
        init();
    }

    public FriendRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FriendRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FriendRowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.friend_row_layout, this);
        nameTextView = (TextView) findViewById(R.id.name);
        avatarImageView = (ImageView) findViewById(R.id.avatar);
    }

    public void loadData(Friend friend) {
        setBackgroundResource(backgrounds[friend.id % backgrounds.length]);
        nameTextView.setText(friend.name);

    }

    public static FriendRowView build(Context context) {
        FriendRowView friendRowView = new FriendRowView(context);
        return friendRowView;
    }


}
