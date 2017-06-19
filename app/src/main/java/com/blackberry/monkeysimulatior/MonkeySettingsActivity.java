package com.blackberry.monkeysimulatior;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.blackberry.monkeysimulatior.util.MonkeySettings;
import com.blackberry.monkeysimulatior.adapter.SettingValueAdapter;

public class MonkeySettingsActivity extends AppCompatActivity {

    private static ListView list;
    private static TextView app_name;
    private static MonkeySettings monkeySettings = new MonkeySettings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monkey_settings);

        String app_name_intent = getIntent().getStringExtra("app_name");
        String app_version_intent = getIntent().getStringExtra("app_version");

        //get short name
        String packName = app_name_intent.substring(15, app_name_intent.length());
        packName = packName.replaceFirst(packName.substring(0, 1), packName.substring(0, 1).toUpperCase());

        app_name = (TextView) findViewById(R.id.app_name_field_setting);
        app_name.setText(packName + "    Version: " + app_version_intent);

        list = (ListView) findViewById(R.id.monkey_settings_listView);

        SettingValueAdapter settingValueAdapter = new SettingValueAdapter(this, android.R.layout.simple_list_item_1, monkeySettings.getAllMonkeySettings());

        list.setAdapter(settingValueAdapter);

    }
}
