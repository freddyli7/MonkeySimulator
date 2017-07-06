package com.blackberry.monkeysimulator.entity;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by frlee on 5/30/17.
 */

public class ApkApplications implements Serializable{

    private String app_name;
    private String app_verison;
    private Drawable app_icon;

    public ApkApplications(String app_name) {
        this.app_name = app_name;
    }

    public ApkApplications(String app_name, String app_verison, Drawable app_icon) {
        this.app_name = app_name;
        this.app_verison = app_verison;
        this.app_icon = app_icon;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getApp_verison() {
        return app_verison;
    }

    public void setApp_verison(String app_verison) {
        this.app_verison = app_verison;
    }

    public Drawable getApp_icon() {
        return app_icon;
    }

    public void setApp_icon(Drawable app_icon) {
        this.app_icon = app_icon;
    }
}
