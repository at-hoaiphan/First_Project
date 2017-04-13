package com.example.gio.firstproject.retrofit_client;

/**
 * Copyright by Gio.
 * Created on 4/5/2017.
 */

public class ApiUtils {
    private static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
