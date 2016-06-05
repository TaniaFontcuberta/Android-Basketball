package com.taniafontcuberta.basketball.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CustomProperties {

    private Properties properties;
    private static CustomProperties ourInstance;
    private Context context;

    public static CustomProperties getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = new CustomProperties(context);
        }

        ourInstance.context = context;

        return ourInstance;
    }

    private CustomProperties(Context context) {
        try {
            this.context = context;
            AssetManager assetManager = this.context.getAssets();
            InputStream inputStream = assetManager.open("app.properties");
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String propertyName){
        return properties.getProperty(propertyName);
    }
}
