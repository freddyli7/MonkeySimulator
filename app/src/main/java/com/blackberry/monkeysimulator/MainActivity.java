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

public class MainActivity extends AppCompatActivity {

    private ListView app_list;
    private String PACKAGE_NAME;
    private String APP_REAL_NAME;
    private List<ApkApplications> appsName;
    private AppAdapter appAdapter;
    private PackageInfo packageInfo;
    private List<PackageInfo> packageList;
    private PackageManager pm;
    private Context context;
    private Intent launchIntent;
    private TextView serviceTitleBtn;
    private CheckAllServices checkAllServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.blackberry.monkeysimulator.R.layout.activity_main);

        this.PACKAGE_NAME = this.getString(R.string.company_package);
        this.APP_REAL_NAME = this.getString(R.string.app_real_name);

        appsName = new ArrayList<>();
        context = this.getApplication().getBaseContext();
        pm = context.getPackageManager();

        packageList = pm.getInstalledPackages(0);
        for (int i = 0; i < packageList.size(); i++){
            packageInfo = packageList.get(i);
            // full installed apps
            if(PACKAGE_NAME == null){
                appsName.add(new ApkApplications(packageInfo.packageName, packageInfo.versionName, packageInfo.applicationInfo.loadIcon(getPackageManager())));
            } else
            // specific app (PACKAGE_NAME is app's full name)
            if(packageInfo.packageName.equals(PACKAGE_NAME)){
                appsName.add(new ApkApplications(packageInfo.packageName, packageInfo.versionName, packageInfo.applicationInfo.loadIcon(getPackageManager())));
            } else
            // specific company's apps (com.xxx.)
            if(packageInfo.packageName.startsWith(PACKAGE_NAME) && !packageInfo.packageName.endsWith(APP_REAL_NAME)){
                launchIntent = this.getPackageManager().getLaunchIntentForPackage(packageInfo.packageName);
                if(launchIntent != null){
                    appsName.add(new ApkApplications(packageInfo.packageName, packageInfo.versionName, packageInfo.applicationInfo.loadIcon(getPackageManager())));
                }
            }
        }
        app_list = (ListView) findViewById(R.id.app_listView);
        appAdapter = new AppAdapter(this, android.R.layout.simple_list_item_1, appsName);
        app_list.setAdapter(appAdapter);

        // disable services part
        //serviceTitleBtn = (TextView) findViewById(R.id.service_title_main);
        checkAllServices = new CheckAllServices();
        //serviceTitleBtn.setOnClickListener(checkAllServices);
        launchIntent = new Intent(this, ServiceActivity.class);

    }

    private class CheckAllServices implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(launchIntent);
        }
    }
}
