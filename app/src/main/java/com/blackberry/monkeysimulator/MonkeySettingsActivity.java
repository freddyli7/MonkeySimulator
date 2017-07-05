package com.blackberry.monkeysimulator;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.blackberry.monkeysimulator.util.AssembleMonkeyCommand;
import com.blackberry.monkeysimulator.util.CheckRoot;
import com.blackberry.monkeysimulator.util.MonkeySettings;
import com.blackberry.monkeysimulator.adapter.SettingValueAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MonkeySettingsActivity extends AppCompatActivity {

    private static ListView list;
    private static TextView app_name;
    private static Button runMonkeyButton;
    private static MonkeySettings monkeySettings = new MonkeySettings();
    private static SettingValueAdapter settingValueAdapter;
    private static RunMonkeyCommand runMonkeyCommand;
    private static String finalMonkeyCommand;
    private static Intent launchIntent;
    private static Intent launchIntent_current;
    private static ComponentName componentName;

    private static String app_name_intent;
    private static String app_version_intent;
    private static String report;
    private static StringBuffer sbReport;
    private static String nameAndVersionPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.blackberry.monkeysimulator.R.layout.activity_monkey_settings);

        app_name_intent = getIntent().getStringExtra("app_name");
        app_version_intent = getIntent().getStringExtra("app_version");

        //get short name
        String packName = app_name_intent.substring(15, app_name_intent.length());
        packName = packName.replaceFirst(packName.substring(0, 1), packName.substring(0, 1).toUpperCase());

        app_name = (TextView) findViewById(com.blackberry.monkeysimulator.R.id.app_name_field_setting);
        nameAndVersionPass = packName + "    Version: " + app_version_intent;
        app_name.setText(nameAndVersionPass);

        list = (ListView) findViewById(com.blackberry.monkeysimulator.R.id.monkey_settings_listView);
        settingValueAdapter = new SettingValueAdapter(this, android.R.layout.simple_list_item_1, monkeySettings.getAllMonkeySettingsName());
        list.setAdapter(settingValueAdapter);

        // click to assemble Monkey Command and run
        runMonkeyButton = (Button) findViewById(com.blackberry.monkeysimulator.R.id.run_monkey);
        runMonkeyCommand = new RunMonkeyCommand();
        runMonkeyButton.setOnClickListener(runMonkeyCommand);

    }

    private class RunMonkeyCommand implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // 1.Must be rooted first
            if(!CheckRoot.isRooted()){
                Toast.makeText(getApplicationContext(), "Please root your device first or use ENG device", Toast.LENGTH_LONG).show();
                return;
            }
            // 2.Assemble command
            finalMonkeyCommand = AssembleMonkeyCommand.assembleMonkeyCommand(app_name_intent, settingValueAdapter.getMonkeySettingsObj());
            Log.e("....",finalMonkeyCommand);
            // 3.Open certain application
            launchIntent = getPackageManager().getLaunchIntentForPackage(app_name_intent);
            startActivity(launchIntent);
            // 4.Execute monkey command
            // TODO use side button to control
            try{
                Process pc = Runtime.getRuntime().exec(finalMonkeyCommand);
                // 5. record report
                BufferedReader buf = new BufferedReader(new InputStreamReader(pc.getInputStream()));
                //BufferedReader buf2 = new BufferedReader(new InputStreamReader(pc.getErrorStream()));
                report = new String();
                sbReport = new StringBuffer();
                while((report=buf.readLine())!=null){
                    Log.e("..report..",report);
                    sbReport.append(report);
                }
            } catch (Exception e) {
                Log.e("....","SOMETHING WRONG");
                e.printStackTrace();
            }
            // 6. Back to MonkeySimulator, Show result
            Toast.makeText(getApplicationContext(), "Monkey execution completed", Toast.LENGTH_LONG).show();
            launchIntent_current = getPackageManager().getLaunchIntentForPackage(getPackageName());
            componentName = new ComponentName(getPackageName(), "com.blackberry.monkeysimulator.ReportActivity");
            launchIntent_current.setComponent(componentName);
            launchIntent_current.putExtra("monkey_report", sbReport.toString());
            launchIntent_current.putExtra("app_name_version", nameAndVersionPass);
            startActivity(launchIntent_current);

        }
    }


}
