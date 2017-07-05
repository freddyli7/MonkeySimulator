package com.blackberry.monkeysimulator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        report = getIntent().getStringExtra("monkey_report");
        nameAndVersionPass = getIntent().getStringExtra("app_name_version");

        reportArea = (TextView) findViewById(R.id.report_content);
        appNameArea = (TextView) findViewById(R.id.app_name_field_report);
        goBack = (Button) findViewById(R.id.back_monkey);
        exportResults = (Button) findViewById(R.id.export_report);

        goBackMonkey = new GoBackMonkey();
        exportMonkeyResults = new ExportMonkeyResults();

        goBack.setOnClickListener(goBackMonkey);
        intent = new Intent(this, MainActivity.class);

        exportResults.setOnClickListener(exportMonkeyResults);

        reportArea.setText(report);
        appNameArea.setText(nameAndVersionPass);


    }

    private class GoBackMonkey implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(intent);
        }
    }

    private class ExportMonkeyResults implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(intent);
        }
    }
}
