package com.example.gio.firstproject.model;

/**
 * Created by Gio on 3/17/2017.
 */

public abstract class ListItem {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;

    abstract public int getType();
}
