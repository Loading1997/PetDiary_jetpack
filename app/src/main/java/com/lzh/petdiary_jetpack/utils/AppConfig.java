package com.lzh.petdiary_jetpack.utils;

import android.content.res.AssetManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lzh.petdiary_jetpack.model.BottomBar;
import com.lzh.petdiary_jetpack.model.Destination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class AppConfig {
    private static HashMap<String, Destination> sDestConfig;
    private static BottomBar sBottomBar;
    public static HashMap<String, Destination> getDestConfig() {
        if (sDestConfig == null) {
            String conent = parseFile("destination.json");
            sDestConfig = JSON.parseObject(conent, new TypeReference<HashMap<String, Destination>>(){}.getType());
        }
        return sDestConfig;
    }
    public static BottomBar getBottomBar(){
        if(sBottomBar == null){
            String content = parseFile("main_tabs_config.json");
            sBottomBar = JSON.parseObject(content, BottomBar.class);
        }
        return sBottomBar;
    }
    private static String parseFile(String fileName){
        AssetManager assets = AppGlobals.getApplication().getResources().getAssets();
        InputStream stream= null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
             stream = assets.open(fileName);
             reader = new BufferedReader(new InputStreamReader(stream));
             String  line = null;
             while((line = reader.readLine()) != null){
                 builder.append(line);
             }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (stream !=null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }
}
