package com.lzh.petdiary_jetpack.utils;

import android.util.Log;

public class MLog {
    private static final int VERBOSE =0;
    private static final int DEBUG =1;
    private static final int INFO =2;
    private static final int WARN =3;
    private static final int ERROR =4;
    private static final int ASSERT =5;
    private static int intercept_grade = 0;
    private static final String tag = "mlog";
    public static void e(String log){
        if (intercept_grade <ERROR){
            Log.e(tag +" ERROR:", log);
        }
    }
    public static void d(String log){
        if (intercept_grade <DEBUG){
            Log.e(tag+" DEBUG:", log);
        }
    }
    public static void i(String log){
        if (intercept_grade <INFO){
            Log.e(tag+" INFO:", log);
        }
    }

}
