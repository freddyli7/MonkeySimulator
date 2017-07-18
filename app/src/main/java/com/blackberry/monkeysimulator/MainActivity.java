package com.blackberry.monkeysimulator;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.blackberry.monkeysimulator.entity.ApkApplications;
import com.blackberry.monkeysimulator.adapter.AppAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static ListView app_list;
    private String PACKAGE_NAME;
    private String APP_REAL_NAME;
    private List<ApkApplications> appsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.blackberry.monkeysimulator.R.layout.activity_main);

        this.PACKAGE_NAME = this.getString(R.string.company_package);
        this.APP_REAL_NAME = this.getString(R.string.app_real_name);

        appsName = new ArrayList<>();
        Context context = this.getApplication().getBaseContext();
        PackageManager pm = context.getPackageManager();

        List<PackageInfo> packageList = pm.getInstalledPackages(0);
        for (int i = 0; i < packageList.size(); i++){
            PackageInfo packageInfo = packageList.get(i);
            if(packageInfo.packageName.startsWith(PACKAGE_NAME) && !packageInfo.packageName.endsWith(APP_REAL_NAME)){
                appsName.add(new ApkApplications(packageInfo.packageName, packageInfo.versionName, packageInfo.applicationInfo.loadIcon(getPackageManager())));
            }
        }
        app_list = (ListView) findViewById(com.blackberry.monkeysimulator.R.id.app_listView);
        AppAdapter appAdapter = new AppAdapter(this, android.R.layout.simple_list_item_1, appsName);
        app_list.setAdapter(appAdapter);
    }
}
