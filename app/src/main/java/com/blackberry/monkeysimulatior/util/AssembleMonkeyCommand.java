package com.blackberry.monkeysimulatior.util;

import android.support.annotation.NonNull;

/**
 * Created by ruins7 on 2017-06-25.
 */

public class AssembleMonkeyCommand {

    private static String NO_VALUE_FOUND = "No values found";

    public static String assembleMonkeyCommand(@NonNull MonkeySettings monkeySettingsObj){
        // TODO assmble Monkey command
        if(monkeySettingsObj == null){
            return NO_VALUE_FOUND;
        }

        return "adb shell Monkey ......";
    }

}
