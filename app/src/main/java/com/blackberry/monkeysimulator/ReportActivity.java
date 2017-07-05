package com.blackberry.monkeysimulator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    private static boolean saveFile = false;

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
            try {
                saveFile = saveContentToSDCard("Monkey_results_for_" + nameAndVersionPass, report);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean saveContentToSDCard(String fileNmae, String content) throws IOException {

        boolean isExternalStorageAvailable = false;            //SD卡可读写的标志位
        FileOutputStream fileOutputStream = null;            //FileOutputStream对象

        //创建File对象，以SD卡所在的路径作为文件存储路径
        File file = new File(Environment.getExternalStorageDirectory(), fileNmae);

        //判断SD卡是否可读写
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            isExternalStorageAvailable = true;
            fileOutputStream = new FileOutputStream(file);            //创建FileOutputStream对象
            fileOutputStream.write(content.getBytes());                //向FileOutputStream对象中写入数据
            if(fileOutputStream != null) {            //关闭FileOutputStream对象
                fileOutputStream.close();
            }
        }
        return isExternalStorageAvailable;
    }

}
