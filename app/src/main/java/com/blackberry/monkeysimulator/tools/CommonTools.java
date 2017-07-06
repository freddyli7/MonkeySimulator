package com.blackberry.monkeysimulator.tools;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.blackberry.monkeysimulator.adapter.SettingValueAdapter;
import com.blackberry.monkeysimulator.entity.MonkeySettings;

import java.io.File;
import java.lang.reflect.Field;
import java.util.NoSuchElementException;

/**
 * Created by ruins7 on 2017-06-26.
 */

public class CommonTools {

    private static String NULL_STRING = "";
    private static String SERIALVERSIONID = "serialversionuid";
    private static String $CHANGE = "$change";

    /**
     * Checks if the device is rooted.
     *
     * @return <code>true</code> if the device is rooted, <code>false</code> otherwise.
     */
    public static boolean isRooted() {
        // get from build info
        String buildTags = android.os.Build.TAGS;
        if (buildTags != null && buildTags.contains("test-keys")) {
            return true;
        }
        // check if /system/app/Superuser.apk is present
        try {
            File file = new File("/system/app/Superuser.apk");
            if (file.exists()) {
                return true;
            }
        } catch (Exception exception) {
            // ignore
            exception.printStackTrace();
        }
        String[] commands = {
                "/system/xbin/which su",
                "/system/bin/which su",
                "which su"
        };
        for (String command : commands) {
            try {
                Runtime.getRuntime().exec(command);
                return true;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        Log.d("message-startUp: ", "RootUtil");
        return false;
    }

    public void alarmToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public boolean paraInputVerify(@NonNull MonkeySettings monkeySettings, Context context){
        boolean flag = true;
        Field[] fields = monkeySettings.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(!field.getName().equalsIgnoreCase(SERIALVERSIONID) && !field.getName().equalsIgnoreCase($CHANGE)){


            field.setAccessible(true);
            try {
                // pct para verify
                if(field.getName().startsWith("pct") && field.get(monkeySettings) != null && field.get(monkeySettings).toString().startsWith("0")){
                    alarmToast(context, "Illegal input with " + field.getName());
                    flag = false;
                    break;
                }
                // event number verify
                if(field.getName().equalsIgnoreCase("event_number") && ((NULL_STRING.equals(monkeySettings.getEvent_number()) || monkeySettings.getEvent_number() == null))){
                    alarmToast(context, "event number is required parameter");
                    flag = false;
                    break;
                }
                if(field.getName().equalsIgnoreCase("event_number") && ((!NULL_STRING.equals(monkeySettings.getEvent_number()) || monkeySettings.getEvent_number() != null) && field.get(monkeySettings).toString().startsWith("0"))){
                    alarmToast(context, "Illegal input with " + field.getName() + ", event number should be positive integer");
                    flag = false;
                    break;
                }
                // seed number and information level verify
                if((field.getName().equalsIgnoreCase("seed_number") || field.getName().equalsIgnoreCase("information_level")) && field.get(monkeySettings) != null && field.get(monkeySettings).toString().startsWith("0")){
                    alarmToast(context, "Illegal input with " + field.getName());
                    flag = false;
                    break;
                }
                // debug para verify
                if(!field.getName().startsWith("pct") && !field.getName().equalsIgnoreCase("event_number") && !field.getName().equalsIgnoreCase("seed_number") && !field.getName().equalsIgnoreCase("information_level") && field.get(monkeySettings) != null && field.get(monkeySettings).toString() != "1"){
                    alarmToast(context, "Illegal input with " + field.getName() + ", value could be empty or 1");
                    flag = false;
                    break;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            }
        }
        return flag;
    }
}
