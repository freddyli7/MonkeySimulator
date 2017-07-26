package com.blackberry.monkeysimulator.entity;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by frlee on 5/30/17.
 */

public class ApkApplications implements Serializable{

    private String app_name;
    private String app_version;
    private Drawable app_icon;

    public ApkApplications(String app_name) {
        this.app_name = app_name;
    }

    public ApkApplications(String app_name, String app_version, Drawable app_icon) {
        this.app_name = app_name;
        this.app_version = app_version;
        this.app_icon = app_icon;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public Drawable getApp_icon() {
        return app_icon;
    }

    public void setApp_icon(Drawable app_icon) {
        this.app_icon = app_icon;
    }
}
