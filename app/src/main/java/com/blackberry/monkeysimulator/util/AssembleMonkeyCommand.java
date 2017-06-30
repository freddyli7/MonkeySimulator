package com.blackberry.monkeysimulator.util;

import android.support.annotation.NonNull;

/**
 * Created by ruins7 on 2017-06-25.
 */

public class AssembleMonkeyCommand {

    public static String assembleMonkeyCommand(@NonNull MonkeySettings monkeySettingsObj){
        // TODO assmble Monkey command
        // command example: monkey com.teslacoilsw.launcher 200
        String finalAdbCommandString = "monkey ";
        for (String value : monkeySettingsObj.getAllMonkeySettingsValues(monkeySettingsObj)) {
            finalAdbCommandString += value+" ";
        }
        return finalAdbCommandString.trim();
    }

}
