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
import java.nio.channels.GatheringByteChannel;

/**
 * Created by frlee on 7/5/17.
 */

public class ReportActivity extends AppCompatActivity {

    private String report;
    private String nameAndVersionPass;
    private TextView reportArea;
    private TextView appNameArea;
    private Button goBack;
    private TextView goAnrButton;
    private TextView goExceptionButton;
    private Button exportResults;
    private GoBackMonkey goBackMonkey;
    private GoAnr goAnr;
    private GoException goException;
    private ExportMonkeyResults exportMonkeyResults;
    private Intent mainIntent;
    private Intent anrIntent;
    private Intent exceptionIntent;
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
        appIconIntent = getIntent().getParcelableExtra(APP_ICON);

        reportArea = (TextView) findViewById(R.id.report_content);
        appNameArea = (TextView) findViewById(R.id.app_name_field_report);
        goBack = (Button) findViewById(R.id.back_monkey_report);
        exportResults = (Button) findViewById(R.id.export_report);
        goAnrButton = (TextView) findViewById(R.id.anr_report);
        goExceptionButton = (TextView) findViewById(R.id.exception_report);

        // content set
        // TODO set ANR and exception part as RED
        reportArea.setText(report);
        appNameArea.setText(nameAndVersionPass);

        imageView = (ImageView) findViewById(R.id.app_icon_field_report);
        imageView.setImageBitmap(appIconIntent);

        reportArea.setMovementMethod(new ScrollingMovementMethod());

        // go back to all app
        goBackMonkey = new GoBackMonkey();
        goBack.setOnClickListener(goBackMonkey);
        mainIntent = new Intent(this, MainActivity.class);

        // save the whole report to SD card
        exportMonkeyResults = new ExportMonkeyResults();
        exportResults.setOnClickListener(exportMonkeyResults);

        // go to ANR report
        goAnr = new GoAnr();
        goAnrButton.setOnClickListener(goAnr);
        anrIntent = new Intent(this, ReportAnrActivity.class);

        // go to exception report
        goException = new GoException();
        goExceptionButton.setOnClickListener(goException);
        exceptionIntent = new Intent(this, ReportExceptionActivity.class);

    }

    private class GoBackMonkey implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            SettingValueAdapter.setMonkeySettingsObj(new MonkeySettings());
            startActivity(mainIntent);
        }
    }

    private class ExportMonkeyResults implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                CommonTools.saveResultToSDCard(MONKEY_RESULTS + nameAndVersionPass + RESULTS_TYPE, report, false);
            } catch (IOException e) {
                CommonTools.alarmToast(getBaseContext(), SAVE_MONKEY_RESULT_FAIL);
                //e.printStackTrace();
            }
            CommonTools.alarmToast(getBaseContext(), SAVE_MONKEY_SD);
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

    private class GoException implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            exceptionIntent.putExtra(MONKEY_REPORT, report);
            exceptionIntent.putExtra(APP_NAME_VERSION, nameAndVersionPass);
            exceptionIntent.putExtra(APP_ICON, appIconIntent);
            startActivity(exceptionIntent);
        }
    }

}
