package com.blackberry.monkeysimulatior;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blackberry.monkeysimulatior.util.ApkApplications;
import com.blackberry.monkeysimulatior.util.AppAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private static ListView list;
    private static final String PACKAGE_NAME = "com.blackberry.";
    private List<ApkApplications> appsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appsName = new ArrayList<ApkApplications>();
        Context context = this.getApplication().getBaseContext();
        PackageManager pm = context.getPackageManager();

        List<PackageInfo> packageList = pm.getInstalledPackages(0);
        for (int i = 0; i < packageList.size(); i++){
            PackageInfo packageInfo = (PackageInfo) packageList.get(i);
            if(packageInfo.packageName.startsWith(PACKAGE_NAME)){
                appsName.add(new ApkApplications(packageInfo.packageName, packageInfo.versionName+""));
            }
        }

        list = (ListView) findViewById(R.id.app_listView);

        AppAdapter appAdapter = new AppAdapter(this, android.R.layout.simple_list_item_1, appsName);

        ListView listView = (ListView) findViewById(R.id.app_listView);

        listView.setAdapter(appAdapter);



    }



}
