package com.blackberry.monkeysimulatior.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.blackberry.monkeysimulatior.MainActivity;
import com.blackberry.monkeysimulatior.MonkeySettingsActivity;
import com.blackberry.monkeysimulatior.R;
import com.blackberry.monkeysimulatior.util.ApkApplications;

import java.util.List;

/**
 * Created by frlee on 5/30/17.
 */

public class SettingValueAdapter extends ArrayAdapter<String> {



    public SettingValueAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //current parameter's name
        final Object obj = getItem(position);

        final EditText et = new EditText(getContext());

        View oneAppView = LayoutInflater.from(getContext()).inflate(R.layout.activity_monkey_settings, parent, false);

        TextView textView = (TextView) oneAppView.findViewById(R.id.app_name_field);
        final ListView lv = (ListView) oneAppView.findViewById(R.id.monkey_settings_listView);
        textView.setText(obj.toString());



        oneAppView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alDia = new AlertDialog.Builder(getContext()).setTitle("Input value for " + obj.toString()).setIcon(android.R.drawable.ic_dialog_info).setView(et);
                alDia.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //click ok
                        //Log.v("*****************",et.getText()+"");
                        Log.v("*****************","whatwhat OK");
                        /*TextView value = new TextView(getContext());
                        value.setText(et.getText());
                        lv.addView(value);*/

                    }
                });
                alDia.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //click cancel
                        //Log.v("*****************",et.getText()+"");
                        Log.v("*****************","whatwhat CANCELs");
                        /*TextView value = new TextView(getContext());
                        value.setText(et.getText());
                        lv.addView(value);*/

                    }
                });

                
                alDia.show();
            }
        });



        return oneAppView;
    }
}
