package com.etermax.borbotones.data;

import com.etermax.borbotones.BorbotonesApplication;
import com.etermax.borbotones.model.Friend;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by charly on 17/9/16.
 */
public class FriendParser {

    private List<Friend> friendList;

    private static FriendParser instance;

    public static FriendParser getInstance() {
        if (instance == null) {
            instance = new FriendParser();
        }

        return instance;
    }

    private FriendParser() {
        Gson gson = new Gson();
        String json = getJson();

        Type listType = new TypeToken<ArrayList<Friend>>(){}.getType();

        friendList = gson.fromJson(json, listType);
    }

    private String getJson() {
        String json = null;
        try {
            InputStream is = BorbotonesApplication.getContext().getAssets().open("friends.json");
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

    public List<Friend> getFriendList() {
        return friendList;
    }
}
