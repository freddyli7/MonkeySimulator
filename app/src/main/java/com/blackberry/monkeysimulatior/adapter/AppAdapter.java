package com.blackberry.monkeysimulatior.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.blackberry.monkeysimulatior.MonkeySettingsActivity;
import com.blackberry.monkeysimulatior.R;
import com.blackberry.monkeysimulatior.util.ApkApplications;

import java.util.List;

/**
 * Created by frlee on 5/30/17.
 */

public class AppAdapter extends ArrayAdapter<ApkApplications> {

    public AppAdapter(Context context, int resource, List<ApkApplications> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ApkApplications apkApplications = getItem(position);

        View oneAppView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main, parent, false);

        //ImageView imageView = (ImageView) oneTeacherView.findViewById(R.id.app_mall_icon);
        TextView app_name_textView = (TextView) oneAppView.findViewById(R.id.app_name_field_main);

        //imageView.setImageResource(app.getImageId());
        app_name_textView.setText(apkApplications.getApp_name());

        oneAppView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MonkeySettingsActivity.class);
                intent.putExtra("app_name", apkApplications.getApp_name());
                intent.putExtra("app_version", apkApplications.getApp_verison());
                getContext().startActivity(intent);
            }
        });

        return oneAppView;
    }
}
