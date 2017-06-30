package com.blackberry.monkeysimulator;

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

    private static String app_name_intent ;
    private static String app_version_intent ;

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
        app_name.setText(packName + "    Version: " + app_version_intent);

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
            if(CheckRoot.isRooted() == false){
                Toast.makeText(getApplicationContext(), "Please root your device first or use ENG device", Toast.LENGTH_LONG).show();
                return;
            }
            // 2.Assemble command
            finalMonkeyCommand = AssembleMonkeyCommand.assembleMonkeyCommand(settingValueAdapter.getMonkeySettingsObj());
            // 3.Open certain application
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage(app_name_intent);
            if(launchIntent != null){
                startActivity(launchIntent);
            } else {
                Toast.makeText(getApplicationContext(), "Current application can not be opened", Toast.LENGTH_LONG).show();
                return;
            }
            // 4.Execute monkey command
            // use side button to control
            try{
                Process pc = Runtime.getRuntime().exec(finalMonkeyCommand);
                //Process pc = Runtime.getRuntime().exec("ls -l");
                //int ii = pc.exitValue();

                int i = pc.waitFor();
                Log.e("Fuck....",finalMonkeyCommand+ "--status: " + i + "");


                BufferedReader buf = new BufferedReader(new InputStreamReader(pc.getInputStream()));
                BufferedReader buf2 = new BufferedReader(new InputStreamReader(pc.getErrorStream()));
                String str = new String();
                while((str=buf2.readLine())!=null){
                    Log.e("Fuck....",str);
                }


            } catch (Exception e) {
                Log.e("Fuck....","SOMETHING WRONG IO");
                e.printStackTrace();
            }
            // 5. Back to MonkeySimulator
           /* Log.e("FFFFF",getPackageName()+"");
            Intent launchIntent_current = getPackageManager().getLaunchIntentForPackage(getPackageName());
            if(launchIntent_current != null){
                startActivity(launchIntent_current);
            }*/
            // 6. Show result

            /*BufferedReader reader = null;
            String content = "";
            try {
                //("ps -P|grep bg")执行失败，PC端adb shell ps -P|grep bg执行成功
                //Process process = Runtime.getRuntime().exec("ps -P|grep tv");
                //-P 显示程序调度状态，通常是bg或fg，获取失败返回un和er
                // Process process = Runtime.getRuntime().exec("ps -P");
                //打印进程信息，不过滤任何条件
                Process process = Runtime.getRuntime().exec("ps");
                reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuffer output = new StringBuffer();
                int read;
                char[] buffer = new char[4096];
                while ((read = reader.read(buffer)) > 0) {
                    output.append(buffer, 0, read);
                }
                reader.close();
                content = output.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }


}
