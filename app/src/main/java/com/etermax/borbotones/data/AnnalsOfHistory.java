package com.etermax.borbotones.data;

import com.etermax.borbotones.BorbotonesApplication;
import com.etermax.borbotones.model.Card;
import com.etermax.borbotones.model.HistoryEvent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * .___
 * Created by jose-gonzalez on 16/09/16.
 * __.
 */

public class AnnalsOfHistory {

    public ArrayList<HistoryEvent> historyEventsList;

    private static AnnalsOfHistory instance;

    public static AnnalsOfHistory getInstance() {
        if (instance == null) {
            new AnnalsOfHistory();
        }

        return instance;
    }

    private AnnalsOfHistory() {
        Gson gson = new Gson();
        String json = getJson();
        Type listType = new TypeToken<ArrayList<HistoryEvent>>(){}.getType();
        historyEventsList = gson.fromJson(json, listType);
    }

    private String getJson() {
        String json = null;
        try {
            InputStream is = BorbotonesApplication.getContext().getAssets().open("history.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

}
//.___ End of AnnalsOfHistory __./
