package com.blackberry.monkeysimulator;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.blackberry.monkeysimulator.adapter.AppAdapter;
import com.blackberry.monkeysimulator.entity.ApkApplications;

import java.util.ArrayList;
import java.util.List;

public class ServiceActivity extends AppCompatActivity {

    private ListView service_list;
    private String PACKAGE_NAME;
    private String APP_REAL_NAME;
    private List<ApkApplications> appsName;
    private List<ApkApplications> servicesName;
    private AppAdapter serviceAdapter;
    private PackageInfo packageInfo;
    private List<PackageInfo> packageList;
    private PackageManager pm;
    private Context context;
    private Intent launchIntent;
    private TextView appTitleBtn;
    private CheckAllApps checkAllApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_service);

        this.PACKAGE_NAME = this.getString(R.string.company_package);
        this.APP_REAL_NAME = this.getString(R.string.app_real_name);

        appsName = new ArrayList<>();
        servicesName = new ArrayList<>();
        context = this.getApplication().getBaseContext();
        pm = context.getPackageManager();

        packageList = pm.getInstalledPackages(0);
        for (int i = 0; i < packageList.size(); i++){
            packageInfo = packageList.get(i);
            if(packageInfo.packageName.startsWith(PACKAGE_NAME) && !packageInfo.packageName.endsWith(APP_REAL_NAME)){
                launchIntent = this.getPackageManager().getLaunchIntentForPackage(packageInfo.packageName);
                if(launchIntent == null){
                    servicesName.add(new ApkApplications(packageInfo.packageName, packageInfo.versionName, packageInfo.applicationInfo.loadIcon(getPackageManager())));
                }
            }
        }
        service_list = (ListView) findViewById(R.id.service_listView);
        serviceAdapter = new AppAdapter(this, android.R.layout.simple_list_item_1, servicesName);
        service_list.setAdapter(serviceAdapter);

        appTitleBtn = (TextView) findViewById(R.id.app_title_service);
        checkAllApps = new CheckAllApps();
        appTitleBtn.setOnClickListener(checkAllApps);
        launchIntent = new Intent(this, MainActivity.class);
    }

    private class CheckAllApps implements View.OnClickListener {
        @Override
        public void onClick(View v) {
           startActivity(launchIntent);
        }
    }
}
