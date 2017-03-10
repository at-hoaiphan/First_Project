package com.example.gio.firstproject;

/**
 * Created by Gio on 3/10/2017.
 */

public class User {
    private String name, company, major;

    public User(String name, String company, String major) {
        this.name = name;
        this.company = company;
        this.major = major;
    }

    public void setName(String name) {
        this.name = name;
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
}
