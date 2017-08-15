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

public class ReportExceptionActivity extends AppCompatActivity {

    private String report;
    private String nameAndVersionPass;
    private TextView reportArea;
    private TextView appNameArea;
    private Button goBack;
    private TextView goAnrButton;
    private Button exportResults;
    private GoBackMonkey goBackMonkey;
    private ExportMonkeyResults exportMonkeyResults;
    private TextView goGeneralButton;
    private Intent intent;
    private Intent anrIntent;
    private Intent generalIntent;
    private GoAnr goAnr;
    private GoGeneral goGeneral;
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
        setContentView(R.layout.activity_report_exception);

        MONKEY_REPORT = this.getString(R.string.monkey_report);
        APP_NAME_VERSION = this.getString(R.string.app_name_version_pass);
        APP_ICON = this.getString(R.string.app_icon_pass);
        MONKEY_RESULTS = this.getString(R.string.monkey_results);
        RESULTS_TYPE = this.getString(R.string.results_type);
        SAVE_MONKEY_RESULT_FAIL = this.getString(R.string.save_monkey_result_fail);
        SAVE_MONKEY_SD = this.getString(R.string.save_monkey_to_sd);

        report = getIntent().getStringExtra(MONKEY_REPORT);
        nameAndVersionPass = getIntent().getStringExtra(APP_NAME_VERSION);
        appIconIntent = getIntent().getParcelableExtra(APP_ICON);

        reportArea = (TextView) findViewById(R.id.report_content_exception);
        appNameArea = (TextView) findViewById(R.id.app_name_field_report_exception);
        goBack = (Button) findViewById(R.id.back_monkey_report_exception);
        exportResults = (Button) findViewById(R.id.export_report_exception);
        goGeneralButton = (TextView) findViewById(R.id.all_info_report_exception);
        goAnrButton = (TextView) findViewById(R.id.anr_report_exception);

        // content set
        reportArea.setText(CommonTools.reportFilterForIssue(report, "Exception"));
        appNameArea.setText(nameAndVersionPass);

        imageView = (ImageView) findViewById(R.id.app_icon_field_report_exception);
        imageView.setImageBitmap(appIconIntent);

        reportArea.setMovementMethod(new ScrollingMovementMethod());

        // go back to all app
        goBackMonkey = new GoBackMonkey();
        goBack.setOnClickListener(goBackMonkey);
        intent = new Intent(this, MainActivity.class);

        // save the whole report to SD card
        exportMonkeyResults = new ExportMonkeyResults();
        exportResults.setOnClickListener(exportMonkeyResults);

        // go to general report
        goGeneral = new GoGeneral();
        goGeneralButton.setOnClickListener(goGeneral);
        generalIntent = new Intent(this, ReportActivity.class);

        // go to ANR report
        goAnr = new GoAnr();
        goAnrButton.setOnClickListener(goAnr);
        anrIntent = new Intent(this, ReportAnrActivity.class);


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
                CommonTools.saveResultToSDCard(MONKEY_RESULTS + nameAndVersionPass + "(Exception issues)" + RESULTS_TYPE, CommonTools.reportFilterForIssue(report, "Exception"), false);
            } catch (IOException e) {
                CommonTools.alarmToast(getBaseContext(), SAVE_MONKEY_RESULT_FAIL);
                //e.printStackTrace();
            }
            CommonTools.alarmToast(getBaseContext(), SAVE_MONKEY_SD);
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

    private class GoAnr implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            anrIntent.putExtra(MONKEY_REPORT, report);
            anrIntent.putExtra(APP_NAME_VERSION, nameAndVersionPass);
            anrIntent.putExtra(APP_ICON, appIconIntent);
            startActivity(anrIntent);
        }
    }

}
