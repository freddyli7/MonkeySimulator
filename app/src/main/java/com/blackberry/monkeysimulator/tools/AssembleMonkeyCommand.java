package com.blackberry.monkeysimulator.tools;

import android.support.annotation.NonNull;
import android.util.Log;

import com.blackberry.monkeysimulator.entity.MonkeySettings;

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

    // General
    private static String EVENT_NUMBER = "event_number";
    private static String SEED_NUMBER = "seed_number";
    private static String INFORMATION_LEVEL = "information_level";

    // Events
    private static String THROTTLE = "throttle";
    private static String PAC_TOUCH = "pct_touch";
    private static String PAC_MOTION = "pct_motion";
    private static String PAC_TRACKBALL = "pct_trackball";
    private static String PAC_NAV = "pct_nav";
    private static String PAC_MAJORNAV = "pct_majornav";
    private static String PAC_SYSKEYS = "pct_syskeys";
    private static String PAC_APPSWITCH = "pct_appswitch";
    private static String PAC_ANYEVENT = "pct_anyevent";

    // Debugging
    private static String DBG_NO_EVENT = "dbg_no_event";
    private static String HPROF = "hprof";
    private static String IGNORE_CRASHES = "ignore_crashes";
    private static String IGNORE_TIMEOUT = "ignore_timeout";
    private static String IGNORE_SECURITY_EXCEPTIONS = "ignore_security_exceptions";
    private static String KILL_PROCESS_AFTER_ERROR = "kill_process_after_error";
    private static String MONITOR_NATIVE_CRASHES = "monitor_native_crashes";
    private static String WAIT_DBG = "wait_dbg";

    public static String assembleMonkeyCommand(String targetAppName, @NonNull MonkeySettings monkeySettingsObj){
        // command example: monkey -p com.teslacoilsw.launcher 200
        finalAdbCommandString = "monkey -p " + targetAppName + " ";
        for (String nameAndValue : getAllMonkeySettingsValues(monkeySettingsObj)) {
            finalAdbCommandString += nameAndValue+" ";
        }
        Log.e("RRR", finalAdbCommandString);
        return finalAdbCommandString.trim();
    }

    private static List<String> getAllMonkeySettingsValues(@NonNull MonkeySettings obj){
        List<String> values = new ArrayList<String>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(!field.getName().equalsIgnoreCase(SERIALVERSIONID) && !field.getName().equalsIgnoreCase($CHANGE)){
                field.setAccessible(true);
                try{
                    if(field.get(obj) != null){
                        if(field.getName().equalsIgnoreCase(INFORMATION_LEVEL)){
                            int infoTimes = Integer.valueOf(field.get(obj).toString());
                            String v = "-v ";
                            String vfinal = "";
                            for (int i = 0; i < infoTimes; i++){
                                vfinal += v;
                            }
                            values.add(vfinal.trim());
                        } else if(field.getName().equalsIgnoreCase(SEED_NUMBER)) {
                            values.add("-s " + field.get(obj).toString() + " ");
                        } else if(field.getName().equalsIgnoreCase(EVENT_NUMBER)) {
                            values.add(field.get(obj).toString() + " ");
                        } else if (field.getName().equalsIgnoreCase(THROTTLE)) {
                            values.add("--throttle " + field.get(obj).toString() + " ");
                        } else if (field.getName().equalsIgnoreCase(PAC_TOUCH)) {
                            values.add("--pct-touch " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase(PAC_MOTION)) {
                            values.add("--pct-motion " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase(PAC_TRACKBALL)) {
                            values.add("--pct-trackball " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase(PAC_NAV)) {
                            values.add("--pct-nav " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase(PAC_MAJORNAV)) {
                            values.add("--pct-majornav " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase(PAC_SYSKEYS)) {
                            values.add("--pct-syskeys " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase(PAC_APPSWITCH)) {
                            values.add("--pct-appswitch " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase(PAC_ANYEVENT)) {
                            values.add("--pct-anyevent " + field.get(obj).toString() + "% ");
                        } else if (field.getName().equalsIgnoreCase(DBG_NO_EVENT) && field.get(obj).toString().equals("1")) {
                            values.add("--dbg-no-events ");
                        } else if (field.getName().equalsIgnoreCase(HPROF) && field.get(obj).toString().equals("1")) {
                            values.add("--hprof ");
                        } else if (field.getName().equalsIgnoreCase(IGNORE_CRASHES) && field.get(obj).toString().equals("1")) {
                            values.add("--ignore-crashes ");
                        } else if (field.getName().equalsIgnoreCase(IGNORE_TIMEOUT) && field.get(obj).toString().equals("1")) {
                            values.add("--ignore-timeout ");
                        } else if (field.getName().equalsIgnoreCase(IGNORE_SECURITY_EXCEPTIONS) && field.get(obj).toString().equals("1")) {
                            values.add("--ignore-security-exceptions ");
                        } else if (field.getName().equalsIgnoreCase(KILL_PROCESS_AFTER_ERROR) && field.get(obj).toString().equals("1")) {
                            values.add("--kill-process-after-error ");
                        } else if (field.getName().equalsIgnoreCase(MONITOR_NATIVE_CRASHES) && field.get(obj).toString().equals("1")) {
                            values.add("--monitor-native-crashes ");
                        } else if (field.getName().equalsIgnoreCase(WAIT_DBG) && field.get(obj).toString().equals("1")) {
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
