package com.lzh.petdiary_jetpack;

import android.app.Application;

import com.lzh.libnetwork.ApiService;

public class PDApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApiService.init("http://192.168.31.110:8080/serverdemo", null);

    }
}
