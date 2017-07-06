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
import android.widget.Toast;

import com.blackberry.monkeysimulator.MonkeySettingsActivity;
import com.blackberry.monkeysimulator.R;
import com.blackberry.monkeysimulator.util.ApkApplications;

import java.util.List;

/**
 * Created by frlee on 5/30/17.
 */

public class AppAdapter extends ArrayAdapter<ApkApplications> {

    private static Intent launchIntent;
    private static Intent intent;
    private static String APP_NAME = "app_name";
    private static String APP_VERSION = "app_version";
    private static String APP_NOT_OPEN = "Current service can not be opened";
    private static String APP_ICON = "app_icon";
    private static Bitmap bitmap_icon;
    private static View oneAppView;
    private static ImageView imageView;
    private static TextView app_name_textView;

    public AppAdapter(Context context, int resource, List<ApkApplications> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ApkApplications apkApplications = getItem(position);
        oneAppView = LayoutInflater.from(getContext()).inflate(R.layout.app_list, parent, false);

        imageView = (ImageView) oneAppView.findViewById(R.id.app_icon_field_main);
        app_name_textView = (TextView) oneAppView.findViewById(R.id.app_name_field_main);

        imageView.setImageDrawable(apkApplications.getApp_icon());
        app_name_textView.setText(apkApplications.getApp_name() + "\nv" + apkApplications.getApp_verison());

        oneAppView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchIntent = getContext().getPackageManager().getLaunchIntentForPackage(apkApplications.getApp_name());
                if(launchIntent == null){
                    Toast.makeText(getContext(), APP_NOT_OPEN, Toast.LENGTH_LONG).show();
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
