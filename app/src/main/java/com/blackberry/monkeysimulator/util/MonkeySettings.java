package com.blackberry.monkeysimulator.util;

import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frlee on 5/30/17.
 */

public class MonkeySettings {

    private String packageName;
    private String eventNumber;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(String eventNumber) {
        this.eventNumber = eventNumber;
    }

    public List<String> getAllMonkeySettingsName(){
        List<String> settings = new ArrayList<String>();
        Field[] fields = MonkeySettings.class.getDeclaredFields();
        for (Field field :fields) {
            if(!field.getName().equalsIgnoreCase("serialversionuid") && !field.getName().equalsIgnoreCase("$change")){
                settings.add(field.getName());
            }
        }
        return settings;
    }

    //TODO 注意value组装顺序
    public List<String> getAllMonkeySettingsValues(@NonNull MonkeySettings obj){
        List<String> values = new ArrayList<String>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field :fields) {
            if(!field.getName().equalsIgnoreCase("serialversionuid") && !field.getName().equalsIgnoreCase("$change")){
                field.setAccessible(true);
                try{
                    if(field.get(obj) != null){
                        values.add(field.get(obj).toString());
                    }
                } catch (IllegalAccessException e) {
                    Log.e("ERROR TAG", "Illegal access field for monkeySettingsObj");
                }
            }
        }
        return values;
    }
}
