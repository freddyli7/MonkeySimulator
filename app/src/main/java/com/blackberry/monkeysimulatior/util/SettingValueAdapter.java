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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blackberry.monkeysimulatior.MainActivity;
import com.blackberry.monkeysimulatior.MonkeySettingsActivity;
import com.blackberry.monkeysimulatior.R;
import com.blackberry.monkeysimulatior.util.ApkApplications;

import java.util.List;

import static android.R.style.Widget;

/**
 * Created by frlee on 5/30/17.
 */

public class SettingValueAdapter extends ArrayAdapter<String> {



    public SettingValueAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);

    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        final Object settingsNameObj = getItem(position);
        final EditText paramatreTextView = new EditText(getContext());
        final View oneAppView = LayoutInflater.from(getContext()).inflate(R.layout.activity_monkey_settings, parent, false);
        final TextView settingsName = (TextView) oneAppView.findViewById(R.id.setting_name_field);
        //final ListView settingsList = (ListView) oneAppView.findViewById(R.id.monkey_settings_listView);

        settingsName.setText(settingsNameObj.toString());

        settingsName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alDia = new AlertDialog.Builder(getContext()).setTitle("Input value for " + settingsNameObj.toString()).setIcon(android.R.drawable.ic_dialog_info).setView(paramatreTextView);
                alDia.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String currentSettingName = settingsName.getText()+"";
                        //click ok
                        //Log.v("*****************",paramaterTextView.getText()+"");
                        Log.d("*****************","whatwhat OK");
                        settingsName.setText(currentSettingName + ": "+ paramatreTextView.getText());
                    }
                });
                alDia.show();
                alDia.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //click cancel
                        //Log.v("*****************",paramaterTextView.getText()+"");
                        Log.d("*****************","whatwhat CANCEL");
                        /*TextView value = new TextView(getContext());
                        value.setText(paramaterTextView.getText());
                        settingsList.addView(value);*/
                    }
                });

                alDia.show();
            }

        });




        /*settingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.e("FFFFFFFFFF",""+settingsList.getSelectedItemPosition());



                *//*AlertDialog.Builder alDia = new AlertDialog.Builder(getContext()).setTitle("Input value for " + settingsNameObj.toString()).setIcon(android.R.drawable.ic_dialog_info).setView(paramaterTextView);
                alDia.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        //click ok
                        //Log.v("*****************",paramaterTextView.getText()+"");
                        Log.d("*****************","whatwhat OK");
                       *//**//* TextView value = new TextView(getContext());
                        value.setText(paramaterTextView.getText());
                        settingsList.addView(value);*//**//*
                    }

                });
                alDia.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //click cancel
                        //Log.v("*****************",paramaterTextView.getText()+"");
                        Log.d("*****************","whatwhat CANCEL");
                        *//**//*TextView value = new TextView(getContext());
                        value.setText(paramaterTextView.getText());
                        settingsList.addView(value);*//**//*

                    }
                });
<<<<<<< HEAD
                alDia.show();*//*
=======

                
                alDia.show();
>>>>>>> dev
            }

        });*/



        return oneAppView;
    }


}
