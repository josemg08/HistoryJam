package com.etermax.borbotones.model;

import android.content.res.Resources;

import com.etermax.borbotones.BorbotonesApplication;

import java.io.Serializable;

/**
 * .___
 * Created by jose-gonzalez on 16/09/16.
 * __.
 */

public class HistoryEvent implements Serializable {

    public String description;
    public String title;
    public String resource;

    public int getResourceId() {
        Resources resources = BorbotonesApplication.getContext().getResources();
        return resources.getIdentifier(resource, "drawable", BorbotonesApplication.getContext().getPackageName());
    }

}
//.___ End of HistoryEvent __./
