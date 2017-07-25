package com.blackberry.monkeysimulator.entity;

import android.app.Application;

/**
 * Created by ruins7 on 2017-07-25.
 */

public class MonkeyParaInstance extends Application {

    // todo 使用单例模式
    private MonkeySettings monkeySettings = new MonkeySettings();

    public MonkeySettings getMonkeySettings() {
        return monkeySettings;
    }

    public void setMonkeySettings(MonkeySettings monkeySettings) {
        this.monkeySettings = monkeySettings;
    }
}
