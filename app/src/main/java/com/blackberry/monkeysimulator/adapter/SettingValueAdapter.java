package com.blackberry.monkeysimulator.adapter;import android.app.AlertDialog;import android.content.Context;import android.content.DialogInterface;import android.text.InputType;import android.util.Log;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ArrayAdapter;import android.widget.EditText;import android.widget.TextView;import com.blackberry.monkeysimulator.R;import com.blackberry.monkeysimulator.entity.MonkeySettings;import java.lang.reflect.Field;import java.util.List;import java.util.Objects;/** * Created by frlee on 5/30/17. */public class SettingValueAdapter extends ArrayAdapter<String> {    private static MonkeySettings monkeySettingsObj = new MonkeySettings();    private Object settingsNameObj;    private TextView settingsName;    private View oneParaView;    private static String REQUIRED;    private static String settingValue;    private static String OK_BTN;    private static String CANCEL_BTN;    private static String DIALOG_TITLE;    private static String inputValue = "";    private static String emptyReturnValue = "";    private static String connection = " : ";    public MonkeySettings getMonkeySettingsObj() {        return monkeySettingsObj;    }    public static void setMonkeySettingsObj(MonkeySettings monkeySettingsObj) {        SettingValueAdapter.monkeySettingsObj = monkeySettingsObj;    }    public SettingValueAdapter(Context context, int resource, List<String> objects) {        super(context, resource, objects);        REQUIRED = getContext().getResources().getString(R.string.para_required);        OK_BTN = getContext().getResources().getString(R.string.ok_button);        CANCEL_BTN = getContext().getResources().getString(R.string.cancel_button);        DIALOG_TITLE = getContext().getResources().getString(R.string.dialog_title);    }    @Override    public View getView(int position, View convertView, final ViewGroup parent) {        settingsNameObj = getItem(position);        final String settingName = settingsNameObj.toString();        oneParaView = LayoutInflater.from(getContext()).inflate(R.layout.settings_list, parent, false);        settingsName = (TextView) oneParaView.findViewById(R.id.setting_name_field);        settingValue = getSettingValueForObj(settingName, monkeySettingsObj);        if (settingName.equalsIgnoreCase("event_number")) {            settingsName.setText(settingName + REQUIRED + settingValue);        } else {            settingsName.setText(settingName + settingValue);        }        settingsName.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                // create a new EditText object inside the onClickListener each time and use setView()                final EditText settingsValue = new EditText(oneParaView.getContext());                // set up a Dialog                AlertDialog.Builder alDia = new AlertDialog.Builder(getContext());                alDia.setView(settingsValue);                alDia.setTitle(DIALOG_TITLE + settingName);                settingsValue.setInputType(InputType.TYPE_CLASS_NUMBER);                //alDia.setIcon(android.settingsValue.drawable.ic_dialog_info);                alDia.setPositiveButton(OK_BTN, new DialogInterface.OnClickListener() {                    @Override                    public void onClick(DialogInterface dialog, int which) {                        for (String eachSetting : monkeySettingsObj.getAllMonkeySettingsName()) {                            if (eachSetting.equalsIgnoreCase(settingName)) {                                try {                                    Field field = monkeySettingsObj.getClass().getDeclaredField(settingName);                                    field.setAccessible(true);                                    inputValue = settingsValue.getText().toString().trim();                                    if (inputValue.equals("")) {                                        field.set(monkeySettingsObj, null);                                        settingsName.setText(" ");                                        return;                                    }                                    field.set(monkeySettingsObj, inputValue);                                } catch (NoSuchFieldException e) {                                    Log.e("ERROR TAG", "No such field for monkeySettingsObj");                                } catch (IllegalAccessException e) {                                    Log.e("ERROR TAG", "Illegal access field for monkeySettingsObj");                                }                            }                        }                    }                });                alDia.setNegativeButton(CANCEL_BTN, new DialogInterface.OnClickListener() {                    @Override                    public void onClick(DialogInterface dialog, int which) {                        // do nothing                    }                });                alDia.show();            }        });        return oneParaView;    }    private String getSettingValueForObj(String settingName, MonkeySettings monkeySettingsObj) {        for (String eachSettingName : monkeySettingsObj.getAllMonkeySettingsName()) {            try {                if (eachSettingName.equalsIgnoreCase(settingName)) {                    Field field = monkeySettingsObj.getClass().getDeclaredField(settingName);                    field.setAccessible(true);                    if (field.get(monkeySettingsObj) != null) {                        return connection + field.get(monkeySettingsObj).toString();                    } else {                        return emptyReturnValue;                    }                }            } catch (Exception e) {                e.printStackTrace();            }        }        return emptyReturnValue;    }}