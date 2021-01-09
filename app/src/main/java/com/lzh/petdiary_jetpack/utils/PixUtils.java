package com.lzh.petdiary_jetpack.utils;

public class PixUtils {
    public static int dp2px (int dpValue){
        return (int) (AppGlobals.getApplication().getResources().getDisplayMetrics().density * dpValue);

    }

    public static int getScreenWidth(){
        return  (int) (AppGlobals.getApplication().getResources().getDisplayMetrics().widthPixels);
    }
    public static int getScreenHeight(){
        return  (int) (AppGlobals.getApplication().getResources().getDisplayMetrics().heightPixels);
    }
}
