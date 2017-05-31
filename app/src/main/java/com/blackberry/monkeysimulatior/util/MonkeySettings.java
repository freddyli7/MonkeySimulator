package com.blackberry.monkeysimulatior.util;

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

    public List<String> getAllMonkeySettings(){
        List<String> settings = new ArrayList<String>();
        Field[] fields = MonkeySettings.class.getDeclaredFields();
        for (Field field :fields) {
            if(!field.getName().equalsIgnoreCase("serialversionuid") && !field.getName().equalsIgnoreCase("$change")){
                settings.add(field.getName());
            }

        }
        return settings;
    }
}
