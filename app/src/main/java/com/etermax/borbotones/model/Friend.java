package com.etermax.borbotones.model;

import android.content.res.Resources;

import com.etermax.borbotones.BorbotonesApplication;

import java.io.Serializable;

/**
 * @author juan on 16/09/16.
 */

public class Friend implements Serializable {

    public int id;
    public String name;
    public int live;
    public int experience;
    public int level;
    public String resource;

    public int getResourceId() {
        Resources resources = BorbotonesApplication.getContext().getResources();
        return resources.getIdentifier(resource, "drawable", BorbotonesApplication.getContext().getPackageName());
    }
}
