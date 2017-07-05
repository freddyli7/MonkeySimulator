package com.blackberry.monkeysimulator.util;

import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruins7 on 2017-06-25.
 */

public class AssembleMonkeyCommand {

    private static String finalAdbCommandString;
    private static String SERIALVERSIONID = "serialversionuid";
    private static String $CHANGE = "$change";

    public static String assembleMonkeyCommand(String targetAppName, @NonNull MonkeySettings monkeySettingsObj){
        // command example: monkey -p com.teslacoilsw.launcher 200
        finalAdbCommandString = "monkey -p " + targetAppName + " ";
        for (String nameAndValue : getAllMonkeySettingsValues(monkeySettingsObj)) {
            finalAdbCommandString += nameAndValue+" ";
        }
        return finalAdbCommandString.trim();
    }

    private static List<String> getAllMonkeySettingsValues(@NonNull MonkeySettings obj){
        List<String> values = new ArrayList<String>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field :fields) {
            if(!field.getName().equalsIgnoreCase(SERIALVERSIONID) && !field.getName().equalsIgnoreCase($CHANGE)){
                field.setAccessible(true);
                try{
                    if(field.get(obj) != null){
                        if(field.getName().equalsIgnoreCase("informationLevel")){
                            int infoTimes = Integer.valueOf(field.get(obj).toString());
                            String v = "-v ";
                            String vf = "";
                            for (int i = 0; i < infoTimes; i++){
                                vf += v;
                            }
                            values.add(vf.trim());
                        } else if(field.getName().equalsIgnoreCase("seedNumber")) {
                            values.add("-s " + field.get(obj).toString() + " ");
                        } else if(field.getName().equalsIgnoreCase("eventNumber")) {
                            values.add(field.get(obj).toString() + " ");
                        } else if (field.getName().equalsIgnoreCase("throttle")) {
                            values.add("--throttle " + field.get(obj).toString() + " ");
                        } else if (field.getName().equalsIgnoreCase("pctTouch")) {
                            values.add("--pct-touch " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase("pctMotion")) {
                            values.add("--pct-motion " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase("pctTrackball")) {
                            values.add("--pct-trackball " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase("pctNav")) {
                            values.add("--pct-nav " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase("pctMajornav")) {
                            values.add("--pct-majornav " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase("pctSyskeys")) {
                            values.add("--pct-syskeys " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase("pctAppswitch")) {
                            values.add("--pct-appswitch " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase("pctAnyevent")) {
                            values.add("--pct-anyevent " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase("dbgnoevent") && field.get(obj).toString().equals("1")) {
                            values.add("--dbg-no-events ");
                        } else if (field.getName().equalsIgnoreCase("hprof") && field.get(obj).toString().equals("1")) {
                            values.add("--hprof ");
                        } else if (field.getName().equalsIgnoreCase("ignoreCrashes") && field.get(obj).toString().equals("1")) {
                            values.add("--ignore-crashes ");
                        } else if (field.getName().equalsIgnoreCase("ignoreTimeout") && field.get(obj).toString().equals("1")) {
                            values.add("--ignore-timeout ");
                        } else if (field.getName().equalsIgnoreCase("ignoreSecurityExceptions") && field.get(obj).toString().equals("1")) {
                            values.add("--ignore-security-exceptions ");
                        } else if (field.getName().equalsIgnoreCase("killProcessAfterError") && field.get(obj).toString().equals("1")) {
                            values.add("--kill-process-after-error ");
                        } else if (field.getName().equalsIgnoreCase("monitorNativeCrashes") && field.get(obj).toString().equals("1")) {
                            values.add("--monitor-native-crashes ");
                        } else if (field.getName().equalsIgnoreCase("waitDbg") && field.get(obj).toString().equals("1")) {
                            values.add("--wait-dbg ");
                        }
                        //TODO other paras
                    }
                } catch (IllegalAccessException e) {
                    Log.e("ERROR TAG", "Illegal access field for monkeySettingsObj");
                }
            }
        }
        return values;
    }

}
