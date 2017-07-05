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
import android.widget.Toast;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        report = getIntent().getStringExtra("monkey_report");
        nameAndVersionPass = getIntent().getStringExtra("app_name_version");
        app_icon_intent = getIntent().getParcelableExtra("app_icon");

        reportArea = (TextView) findViewById(R.id.report_content);
        appNameArea = (TextView) findViewById(R.id.app_name_field_report);
        goBack = (Button) findViewById(R.id.back_monkey);
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
            startActivity(intent);
        }
    }

    private class ExportMonkeyResults implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                saveResultToSDCard("MonkeyResults:" + nameAndVersionPass+".txt", report);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Saving monkey result failed", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), "Monkey result has been saved in SD card", Toast.LENGTH_LONG).show();
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
