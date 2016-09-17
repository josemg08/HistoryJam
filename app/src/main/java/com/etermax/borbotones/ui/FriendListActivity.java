package com.etermax.borbotones.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.etermax.borbotones.R;
import com.etermax.borbotones.data.FriendParser;
import com.etermax.borbotones.model.Friend;
import com.etermax.borbotones.widget.FriendRowView;

import java.util.List;

/**
 * Created by charly on 17/9/16.
 */
public class FriendListActivity extends Activity {


    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, FriendListActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.friend_recyclerView);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        FriendAdapter adapter = new FriendAdapter(FriendParser.getInstance().getFriendList());
        recyclerView.setAdapter(adapter);

    }


    public  class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
        private List<Friend> friends;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public  class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public FriendRowView friendRowView;

            public ViewHolder(FriendRowView v) {
                super(v);
                friendRowView = v;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public FriendAdapter(List<Friend> friends) {
            this.friends = friends;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public FriendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {

            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(FriendRowView.build(parent.getContext()));
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.friendRowView.loadData(friends.get(position));
            holder.friendRowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FriendListActivity.this.startGame(friends.get(position));
                }
            });

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return friends.size();
        }
    }

    private void startGame(Friend friend){
        Intent intent = new Intent(getApplicationContext(), MatchActivity.class);
        startActivity(intent);
        //Toast.makeText(this, friend.name, Toast.LENGTH_SHORT).show();
    }

}
