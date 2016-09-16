package com.etermax.borbotones.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.etermax.borbotones.R;
import com.etermax.borbotones.data.Deck;
import com.etermax.borbotones.model.Card;
import com.etermax.borbotones.widget.CardView;

import java.util.ArrayList;
import java.util.List;

public class MyDeckActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_deck);

        GridView mGridView = (GridView) findViewById(R.id.my_deck_gridview);
        mGridView.setAdapter( new MyDeckAdapter(this) );
    }

    class MyDeckAdapter extends BaseAdapter {
        private Context mContext;
        private List<Card> mCardList;

        public MyDeckAdapter(Context context)
        {
            mContext = context;
            mCardList = new ArrayList<>();
            mCardList.add(Deck.getInstance().getRandomCard());
            mCardList.add(Deck.getInstance().getRandomCard());
            mCardList.add(Deck.getInstance().getRandomCard());
            mCardList.add(Deck.getInstance().getRandomCard());
            mCardList.add(Deck.getInstance().getRandomCard());
            mCardList.add(Deck.getInstance().getRandomCard());
            mCardList.add(Deck.getInstance().getRandomCard());
            mCardList.add(Deck.getInstance().getRandomCard());
            mCardList.add(Deck.getInstance().getRandomCard());
            mCardList.add(Deck.getInstance().getRandomCard());
            mCardList.add(Deck.getInstance().getRandomCard());
            mCardList.add(Deck.getInstance().getRandomCard());
        }

        @Override
        public int getCount() {
            return mCardList.size();
        }

        @Override
        public Object getItem(int position) {
            return mCardList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CardView view;
            if (convertView == null) {
                view = new CardView( mContext );
            } else {
                view = (CardView)convertView;
            }

            view.setCard( mCardList.get(position) );
            return view;
        }
    }
}
