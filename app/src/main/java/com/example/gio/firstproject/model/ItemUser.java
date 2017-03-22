package com.example.gio.firstproject.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gio on 3/10/2017.
 */

public class ItemUser extends ListItem implements Parcelable {
    private int id;
    private boolean isFavourite = false;
    private String name, company, major, about;

    public ItemUser(int id, String name, String company, String major, String about, boolean isFavourite) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.major = major;
        this.about = about;
        this.isFavourite = isFavourite;
    }

    private ItemUser(Parcel in) {
        id = in.readInt();
        isFavourite = in.readByte() != 0;
        name = in.readString();
        company = in.readString();
        major = in.readString();
        about = in.readString();
    }

    public boolean getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public static final Creator<ItemUser> CREATOR = new Creator<ItemUser>() {
        @Override
        public ItemUser createFromParcel(Parcel in) {
            return new ItemUser(in);
        }

        @Override
        public ItemUser[] newArray(int size) {
            return new ItemUser[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeInt(isFavourite ? 1 : 0);
        parcel.writeString(getName());
        parcel.writeString(getCompany());
        parcel.writeString(getMajor());
        parcel.writeString(getAbout());
    }

    @Override
    public int getType() {
        return TYPE_ITEM;
    }
}
