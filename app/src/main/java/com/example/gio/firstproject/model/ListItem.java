package com.example.gio.firstproject.model;

/**
 * Copyright by Gio.
 * Created on 3/17/2017.
 */

public abstract class ListItem {

    static final int TYPE_HEADER = 0;
    static final int TYPE_ITEM = 1;

    abstract public int getType();
}
