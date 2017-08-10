package com.blackberry.monkeysimulator.tools;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.blackberry.monkeysimulator.entity.MonkeySettings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.regex.Pattern;

/**
 * Created by ruins7 on 2017-06-26.
 */

public class CommonTools {

    // input verify
    private static String NULL_STRING = "";
    private static String SERIALVERSIONID = "serialversionuid";
    private static String $CHANGE = "$change";
    private static String MONKNEYSETTINGS = "MonkeySettings";

    // save to SD card
    private static boolean isExternalStorageAvailable = false;
    private static File file;
    private static FileOutputStream fileOutputStream;

    // self-object
    private static CommonTools commonTools;

    private static StringBuffer finalExceptionReport;
    private static final String EXCEPTION_TEMP = "Exception";

    public static CommonTools getCommonTools() {
        if(commonTools == null){
            return new CommonTools();
        }
        return commonTools;
    }

    public static void setCommonTools(CommonTools commonToolsPass) {
        commonTools = commonToolsPass;
    }

    /**
     * Checks if the device is rooted.
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

    /**
     * Alarm Toast public method
     * @param context avtivity content
     * @param message message shows on the screen
     */
    public static void alarmToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Monkey parameters inout verification
     * @param monkeySettings monkeySettings Object which contains all input value
     * @param context activity content
     * @return
     */
    public static boolean paraInputVerify(@NonNull MonkeySettings monkeySettings, Context context) {
        boolean flag = true;
        Field[] fields = monkeySettings.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase(SERIALVERSIONID) && !field.getName().equalsIgnoreCase($CHANGE) && !field.getName().equalsIgnoreCase(MONKNEYSETTINGS)) {
                field.setAccessible(true);
                try {
                    // pct para verify
                    if (field.getName().startsWith("pct") && field.get(monkeySettings) != null && field.get(monkeySettings).toString().startsWith("0")) {
                        alarmToast(context, "Illegal input with " + field.getName() + ", " + field.getName() + "could be empty or a percentage(1% - 100%)");
                        flag = false;
                        break;
                    }
                    if (field.getName().startsWith("pct") && field.get(monkeySettings) != null && Integer.valueOf(field.get(monkeySettings).toString()) > 100) {
                        alarmToast(context, "Illegal input with " + field.getName() + ", " + field.getName() + "could be empty or a percentage(1% - 100%)");
                        flag = false;
                        break;
                    }
                    // event number verify
                    if (field.getName().equalsIgnoreCase("event_number") && ((NULL_STRING.equals(monkeySettings.getEvent_number()) || monkeySettings.getEvent_number() == null))) {
                        alarmToast(context, "event number is required parameter");
                        flag = false;
                        break;
                    }
                    if (field.getName().equalsIgnoreCase("event_number") && ((!NULL_STRING.equals(monkeySettings.getEvent_number()) || monkeySettings.getEvent_number() != null) && field.get(monkeySettings).toString().startsWith("0"))) {
                        alarmToast(context, "Illegal input with " + field.getName() + ", event number should be positive integer");
                        flag = false;
                        break;
                    }
                    if (field.getName().equalsIgnoreCase("event_number") && ((!NULL_STRING.equals(monkeySettings.getEvent_number()) || monkeySettings.getEvent_number() != null) && Integer.valueOf(field.get(monkeySettings).toString()) > 5000)) {
                        alarmToast(context, "Illegal input with " + field.getName() + ", event number should be positive integer but less then 5000");
                        flag = false;
                        break;
                    }
                    // seed number and information level verify
                    if ((field.getName().equalsIgnoreCase("seed_number") || field.getName().equalsIgnoreCase("information_level")) && field.get(monkeySettings) != null && field.get(monkeySettings).toString().startsWith("0")) {
                        alarmToast(context, "Illegal input with " + field.getName());
                        flag = false;
                        break;
                    }
                    if (field.getName().equalsIgnoreCase("seed_number") && field.get(monkeySettings) != null && Integer.valueOf(field.get(monkeySettings).toString()) > 2000) {
                        alarmToast(context, "Illegal input with " + field.getName() + ", " + field.getName() + " should be less than 2000");
                        flag = false;
                        break;
                    }
                    if (field.getName().equalsIgnoreCase("information_level") && field.get(monkeySettings) != null && Integer.valueOf(field.get(monkeySettings).toString()) > 5) {
                        alarmToast(context, "Illegal input with " + field.getName() + ", " + field.getName() + " should be less than or equal to 5");
                        flag = false;
                        break;
                    }
                    // debug para verify
                    if (!field.getName().equals("throttle") && !field.getName().startsWith("pct") && !field.getName().equalsIgnoreCase("event_number") && !field.getName().equalsIgnoreCase("seed_number") && !field.getName().equalsIgnoreCase("information_level") && field.get(monkeySettings) != null && !field.get(monkeySettings).toString().equals("1")) {
                        alarmToast(context, "Illegal input with " + field.getName() + ", value could be empty or 1");
                        flag = false;
                        break;
                    }
                    // throttle para verify
                    if (field.getName().equals("throttle") && field.get(monkeySettings) != null && field.get(monkeySettings).toString().startsWith("0")) {
                        alarmToast(context, "Illegal input with " + field.getName() + ", value could be empty or less then 1000 milliseconds");
                        flag = false;
                        break;
                    }
                    if (field.getName().equals("throttle") && field.get(monkeySettings) != null && Integer.valueOf(field.get(monkeySettings).toString()) > 1000) {
                        alarmToast(context, "Illegal input with " + field.getName() + ", value could be empty or less then 1000 milliseconds");
                        flag = false;
                        break;
                    }
                } catch (IllegalAccessException e) {
                    //e.printStackTrace();
                    alarmToast(context, "Can't access to this parameter currently");
                }
            }
        }
        return flag;
    }

    /**
     * save Monkey report to SD card
     * @param fileName file name
     * @param content report content
     * @param isAppend report appended? if true, report content would be appended; if false, clean old content and rewrite new content
     * @return
     * @throws IOException
     */
    public static boolean saveResultToSDCard(String fileName, String content, boolean isAppend) throws IOException {
        file = new File(Environment.getExternalStorageDirectory(), fileName);
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            isExternalStorageAvailable = true;
            if(isAppend){
                fileOutputStream = new FileOutputStream(file, true);
            } else{
                fileOutputStream = new FileOutputStream(file);
            }
            fileOutputStream.write(content.getBytes());
            if(fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
        return isExternalStorageAvailable;
    }

    /**
     * filter original report to ANR only
     * @param originalReport original report
     * @return Crash(ANR) report
     */
    public static String reportFilterForANR(String originalReport){



        return "ANR REPORT";
    }

    /**
     * filter original report to Exception only
     * @param originalReport original report
     * @return exception report
     */
    public static String reportFilterForException(String originalReport){
        finalExceptionReport = new StringBuffer();
        while (true){
            int start = originalReport.indexOf(EXCEPTION_TEMP);
            if(start == -1){
                break;
            }
            int end = originalReport.indexOf("\n",start);
            String frontHalf = originalReport.substring(0,start);
            int lastTwo_N = frontHalf.lastIndexOf("\n", frontHalf.lastIndexOf("\n")-1);
            finalExceptionReport.append(originalReport.substring(lastTwo_N, end));
            finalExceptionReport.append("\n\n");
            originalReport = originalReport.substring(end, originalReport.length()-1);
        }
        if(finalExceptionReport.length() == 0) {
            return "There is not Exception found";
        }
        return finalExceptionReport.toString();
    }
}
