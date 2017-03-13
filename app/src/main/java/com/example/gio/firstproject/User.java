package com.example.gio.firstproject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gio on 3/10/2017.
 */

public class User implements Parcelable{
    private int id, isFavourite = 0;
    private String name, company, major, about;

    public User(int id, String name, String company, String major, String about, int isFavourite) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.major = major;
        this.about = about;
        this.isFavourite = isFavourite;
    }

    protected User(Parcel in) {
        id = in.readInt();
        isFavourite = in.readInt();
        name = in.readString();
        company = in.readString();
        major = in.readString();
        about = in.readString();
    }

    public int getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        this.isFavourite = isFavourite;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
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
        parcel.writeInt(isFavourite);
        parcel.writeString(getName());
        parcel.writeString(getCompany());
        parcel.writeString(getMajor());
        parcel.writeString(getAbout());
    }
}
