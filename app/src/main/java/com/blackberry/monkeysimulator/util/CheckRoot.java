package com.blackberry.monkeysimulator.util;

import android.util.Log;

import java.io.File;

/**
 * Created by ruins7 on 2017-06-26.
 */

public class CheckRoot {

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
}
