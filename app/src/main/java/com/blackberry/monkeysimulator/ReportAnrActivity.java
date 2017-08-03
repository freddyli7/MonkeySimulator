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
 * Created by frlee on 7/31/17.
 */

public class ReportAnrActivity extends AppCompatActivity {

    private String report;
    private String nameAndVersionPass;
    private TextView reportArea;
    private TextView appNameArea;
    private Button goBack;
    private Button exportResults;
    private TextView goExceptionButton;
    private TextView goGeneralButton;
    private GoBackMonkey goBackMonkey;
    private GoException goException;
    private GoGeneral goGeneral;
    private ExportMonkeyResults exportMonkeyResults;
    private Intent intent;
    private Intent exceptionIntent;
    private Intent generalIntent;
    private Bitmap appIconIntent;
    private ImageView imageView;
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
        setContentView(R.layout.activity_report_anr);

        MONKEY_REPORT = this.getString(R.string.monkey_report);
        APP_NAME_VERSION = this.getString(R.string.app_name_version_pass);
        APP_ICON = this.getString(R.string.app_icon_pass);
        MONKEY_RESULTS = this.getString(R.string.monkey_results);
        RESULTS_TYPE = this.getString(R.string.results_type);
        SAVE_MONKEY_RESULT_FAIL = this.getString(R.string.save_monkey_result_fail);
        SAVE_MONKEY_SD = this.getString(R.string.save_monkey_to_sd);

        // the original report (full info)
        report = getIntent().getStringExtra(MONKEY_REPORT);
        nameAndVersionPass = getIntent().getStringExtra(APP_NAME_VERSION);
        appIconIntent = getIntent().getParcelableExtra(APP_ICON);

        reportArea = (TextView) findViewById(R.id.report_content_anr);
        appNameArea = (TextView) findViewById(R.id.app_name_field_report_anr);
        goBack = (Button) findViewById(R.id.back_monkey_report_anr);
        exportResults = (Button) findViewById(R.id.export_report_anr);
        goExceptionButton = (TextView) findViewById(R.id.exception_report_anr);
        goGeneralButton = (TextView) findViewById(R.id.all_info_report_anr);

        // content set
        reportArea.setText(CommonTools.reportFilterForANR(report));
        appNameArea.setText(nameAndVersionPass);

        imageView = (ImageView) findViewById(R.id.app_icon_field_report_anr);
        imageView.setImageBitmap(appIconIntent);

        reportArea.setMovementMethod(new ScrollingMovementMethod());

        // go back to all app
        goBackMonkey = new GoBackMonkey();
        goBack.setOnClickListener(goBackMonkey);
        intent = new Intent(this, MainActivity.class);

        // save the whole report to SD card
        exportMonkeyResults = new ExportMonkeyResults();
        exportResults.setOnClickListener(exportMonkeyResults);

        // go to exception report
        goException = new GoException();
        goExceptionButton.setOnClickListener(goException);
        exceptionIntent = new Intent(this, ReportExceptionActivity.class);

        // go to general report
        goGeneral = new GoGeneral();
        goGeneralButton.setOnClickListener(goGeneral);
        generalIntent = new Intent(this, ReportActivity.class);


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
                CommonTools.saveResultToSDCard(MONKEY_RESULTS + nameAndVersionPass + RESULTS_TYPE, report);
            } catch (IOException e) {
                CommonTools.alarmToast(getBaseContext(), SAVE_MONKEY_RESULT_FAIL);
                //e.printStackTrace();
            }
            CommonTools.alarmToast(getBaseContext(), SAVE_MONKEY_SD);
        }
    }

    private class GoException implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            exceptionIntent.putExtra(MONKEY_REPORT, report);
            exceptionIntent.putExtra(APP_NAME_VERSION, nameAndVersionPass);
            exceptionIntent.putExtra(APP_ICON, appIconIntent);
            startActivity(exceptionIntent);
        }
    }

    private class GoGeneral implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            generalIntent.putExtra(MONKEY_REPORT, report);
            generalIntent.putExtra(APP_NAME_VERSION, nameAndVersionPass);
            generalIntent.putExtra(APP_ICON, appIconIntent);
            startActivity(generalIntent);
        }
    }

}
