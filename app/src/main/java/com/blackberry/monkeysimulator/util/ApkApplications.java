package com.blackberry.monkeysimulator.util;

/**
 * Created by frlee on 5/30/17.
 */

public class ApkApplications {

    private String app_name;
    private String app_verison;

    public ApkApplications(String app_name) {
        this.app_name = app_name;
    }

    public ApkApplications(String app_name, String app_verison) {
        this.app_name = app_name;
        this.app_verison = app_verison;
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
}
