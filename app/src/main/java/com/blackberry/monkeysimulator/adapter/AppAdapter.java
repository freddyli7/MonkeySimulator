package com.blackberry.monkeysimulator.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blackberry.monkeysimulator.MonkeySettingsActivity;
import com.blackberry.monkeysimulator.R;
import com.blackberry.monkeysimulator.entity.ApkApplications;
import com.blackberry.monkeysimulator.tools.CommonTools;

import java.util.List;

/**
 * Created by frlee on 5/30/17.
 */

public class AppAdapter extends ArrayAdapter<ApkApplications> {

    private Intent launchIntent;
    private Intent intent;
    private String APP_NAME;
    private String APP_VERSION;
    private String APP_NOT_OPEN;
    private String APP_ICON;
    private String RETURN_LINE;
    private String VERSION_SMALL;
    private Bitmap bitmap_icon;
    private View oneAppView;
    private ImageView imageView;
    private TextView app_name_textView;
    private CommonTools commonTools = new CommonTools();


    public AppAdapter(Context context, int resource, List<ApkApplications> objects) {
        super(context, resource, objects);
        APP_NAME = getContext().getResources().getString(R.string.app_name_pass);
        APP_VERSION = getContext().getResources().getString(R.string.app_version_pass);
        APP_NOT_OPEN = getContext().getResources().getString(R.string.app_not_open_warning);
        APP_ICON = getContext().getResources().getString(R.string.app_icon_pass);
        RETURN_LINE = getContext().getResources().getString(R.string.return_line);
        VERSION_SMALL = getContext().getResources().getString(R.string.app_version_small);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ApkApplications apkApplications = getItem(position);
        oneAppView = LayoutInflater.from(getContext()).inflate(R.layout.app_list, parent, false);

        imageView = (ImageView) oneAppView.findViewById(R.id.app_icon_field_main);
        app_name_textView = (TextView) oneAppView.findViewById(R.id.app_name_field_main);

        imageView.setImageDrawable(apkApplications.getApp_icon());
        app_name_textView.setText(apkApplications.getApp_name() + RETURN_LINE + VERSION_SMALL + apkApplications.getApp_verison());

        oneAppView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchIntent = getContext().getPackageManager().getLaunchIntentForPackage(apkApplications.getApp_name());
                if(launchIntent == null){
                    commonTools.alarmToast(getContext(), APP_NOT_OPEN);
                } else {
                    intent = new Intent(getContext(), MonkeySettingsActivity.class);
                    intent.putExtra(APP_NAME, apkApplications.getApp_name());
                    intent.putExtra(APP_VERSION, apkApplications.getApp_verison());
                    bitmap_icon = ((BitmapDrawable) apkApplications.getApp_icon()).getBitmap();
                    intent.putExtra(APP_ICON, bitmap_icon);
                    getContext().startActivity(intent);
                }
            }
        });
        return oneAppView;
    }
}
