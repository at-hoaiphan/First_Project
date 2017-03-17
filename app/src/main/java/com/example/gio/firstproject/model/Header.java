package com.example.gio.firstproject.model;

/**
 * Created by Gio on 3/17/2017.
 */

public class Header extends ListItem{
    private String header;

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeader() {

        return header;
    }

    public Header(String header) {

        this.header = header;
    }

    @Override
    public int getType() {
        return TYPE_HEADER;
    }
}
