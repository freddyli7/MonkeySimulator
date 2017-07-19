package com.blackberry.monkeysimulator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blackberry.monkeysimulator.adapter.SettingValueAdapter;
import com.blackberry.monkeysimulator.entity.MonkeySettings;
import com.blackberry.monkeysimulator.tools.CommonTools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by frlee on 7/5/17.
 */

public class ReportActivity extends AppCompatActivity {

    private static String report;
    private static String nameAndVersionPass;
    private static TextView reportArea;
    private static TextView appNameArea;
    private static Button goBack;
    private static Button exportResults;
    private static GoBackMonkey goBackMonkey;
    private static ExportMonkeyResults exportMonkeyResults;
    private static Intent intent;
    private static boolean isExternalStorageAvailable = false;
    private static FileOutputStream fileOutputStream;
    private static File file;
    private static Bitmap app_icon_intent;
    private static ImageView imageView;
    private static CommonTools commonTools = new CommonTools();
    private String MONKEY_REPORT;
    private String APP_NAME_VERSION;
    private String APP_ICON;
    private String MONKEY_RESULTS;
    private String RESULTS_TYPE;
    private String SAVE_MONKEY_RESULT_FAIL;
    private String SAVE_MONKEY_SD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        MONKEY_REPORT = this.getString(R.string.monkey_report);
        APP_NAME_VERSION = this.getString(R.string.app_name_version_pass);
        APP_ICON = this.getString(R.string.app_icon_pass);
        MONKEY_RESULTS = this.getString(R.string.monkey_results);
        RESULTS_TYPE = this.getString(R.string.results_type);
        SAVE_MONKEY_RESULT_FAIL = this.getString(R.string.save_monkey_result_fail);
        SAVE_MONKEY_SD = this.getString(R.string.save_monkey_to_sd);

        report = getIntent().getStringExtra(MONKEY_REPORT);
        nameAndVersionPass = getIntent().getStringExtra(APP_NAME_VERSION);
        app_icon_intent = getIntent().getParcelableExtra(APP_ICON);

        reportArea = (TextView) findViewById(R.id.report_content);
        appNameArea = (TextView) findViewById(R.id.app_name_field_report);
        goBack = (Button) findViewById(R.id.back_monkey_report);
        exportResults = (Button) findViewById(R.id.export_report);

        reportArea.setMovementMethod(new ScrollingMovementMethod());

        goBackMonkey = new GoBackMonkey();
        exportMonkeyResults = new ExportMonkeyResults();

        goBack.setOnClickListener(goBackMonkey);
        intent = new Intent(this, MainActivity.class);

        exportResults.setOnClickListener(exportMonkeyResults);

        reportArea.setText(report);
        appNameArea.setText(nameAndVersionPass);

        imageView = (ImageView) findViewById(R.id.app_icon_field_report);
        imageView.setImageBitmap(app_icon_intent);

    }

    private class GoBackMonkey implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            SettingValueAdapter.setMonkeySettingsObj(new MonkeySettings());
            startActivity(intent);
        }
    }

    private class ExportMonkeyResults implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                saveResultToSDCard(MONKEY_RESULTS + nameAndVersionPass+RESULTS_TYPE, report);
            } catch (IOException e) {
                commonTools.alarmToast(getBaseContext(), SAVE_MONKEY_RESULT_FAIL);
                e.printStackTrace();
            }
            commonTools.alarmToast(getBaseContext(), SAVE_MONKEY_SD);
        }
    }

    public boolean saveResultToSDCard(String fileName, String content) throws IOException {
        file = new File(Environment.getExternalStorageDirectory(), fileName);
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            isExternalStorageAvailable = true;
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            if(fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
        return isExternalStorageAvailable;
    }

}
