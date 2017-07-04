package com.blackberry.monkeysimulator.util;

import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frlee on 5/30/17.
 */

public class MonkeySettings {


    private String eventNumber;
    private String seedNumber;
    private String infoLevel;
    private String throttle;
    private String pctTouch;
    private String pctMotion;
    private String pctTrackball;
    private String pctNav;
    private String pctMajornav;
    private String pctSyskeys;
    private String pctAppswitch;
    private String pctAnyevent;
    private String c;
    private String dbgnoevent;
    private String hprof;
    private String ignoreCrashes;
    private String ignoreTimeout;
    private String ignoreSecurityException;
    private String killProcessAfterError;
    private String monitorNativeCrash;
    private String waitDbg;
    private String help;

    public String getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(String eventNumber) {
        this.eventNumber = eventNumber;
    }

    public String getSeedNumber() {
        return seedNumber;
    }

    public void setSeedNumber(String seedNumber) {
        this.seedNumber = seedNumber;
    }

    public String getInfoLevel() {
        return infoLevel;
    }

    public void setInfoLevel(String infoLevel) {
        this.infoLevel = infoLevel;
    }

    public String getThrottle() {
        return throttle;
    }

    public void setThrottle(String throttle) {
        this.throttle = throttle;
    }

    public String getPctTouch() {
        return pctTouch;
    }

    public void setPctTouch(String pctTouch) {
        this.pctTouch = pctTouch;
    }

    public String getPctMotion() {
        return pctMotion;
    }

    public void setPctMotion(String pctMotion) {
        this.pctMotion = pctMotion;
    }

    public String getPctTrackball() {
        return pctTrackball;
    }

    public void setPctTrackball(String pctTrackball) {
        this.pctTrackball = pctTrackball;
    }

    public String getPctNav() {
        return pctNav;
    }

    public void setPctNav(String pctNav) {
        this.pctNav = pctNav;
    }

    public String getPctMajornav() {
        return pctMajornav;
    }

    public void setPctMajornav(String pctMajornav) {
        this.pctMajornav = pctMajornav;
    }

    public String getPctSyskeys() {
        return pctSyskeys;
    }

    public void setPctSyskeys(String pctSyskeys) {
        this.pctSyskeys = pctSyskeys;
    }

    public String getPctAppswitch() {
        return pctAppswitch;
    }

    public void setPctAppswitch(String pctAppswitch) {
        this.pctAppswitch = pctAppswitch;
    }

    public String getPctAnyevent() {
        return pctAnyevent;
    }

    public void setPctAnyevent(String pctAnyevent) {
        this.pctAnyevent = pctAnyevent;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getDbgnoevent() {
        return dbgnoevent;
    }

    public void setDbgnoevent(String dbgnoevent) {
        this.dbgnoevent = dbgnoevent;
    }

    public String getHprof() {
        return hprof;
    }

    public void setHprof(String hprof) {
        this.hprof = hprof;
    }

    public String getIgnoreCrashes() {
        return ignoreCrashes;
    }

    public void setIgnoreCrashes(String ignoreCrashes) {
        this.ignoreCrashes = ignoreCrashes;
    }

    public String getIgnoreTimeout() {
        return ignoreTimeout;
    }

    public void setIgnoreTimeout(String ignoreTimeout) {
        this.ignoreTimeout = ignoreTimeout;
    }

    public String getIgnoreSecurityException() {
        return ignoreSecurityException;
    }

    public void setIgnoreSecurityException(String ignoreSecurityException) {
        this.ignoreSecurityException = ignoreSecurityException;
    }

    public String getKillProcessAfterError() {
        return killProcessAfterError;
    }

    public void setKillProcessAfterError(String killProcessAfterError) {
        this.killProcessAfterError = killProcessAfterError;
    }

    public String getMonitorNativeCrash() {
        return monitorNativeCrash;
    }

    public void setMonitorNativeCrash(String monitorNativeCrash) {
        this.monitorNativeCrash = monitorNativeCrash;
    }

    public String getWaitDbg() {
        return waitDbg;
    }

    public void setWaitDbg(String waitDbg) {
        this.waitDbg = waitDbg;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public List<String> getAllMonkeySettingsName(){
        List<String> settings = new ArrayList<String>();
        Field[] fields = MonkeySettings.class.getDeclaredFields();
        for (Field field :fields) {
            if(!field.getName().equalsIgnoreCase("serialversionuid") && !field.getName().equalsIgnoreCase("$change")){
                settings.add(field.getName());
            }
        }
        return settings;
    }

    //TODO 注意value组装顺序
    public List<String> getAllMonkeySettingsValues(@NonNull MonkeySettings obj){
        List<String> values = new ArrayList<String>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field :fields) {
            if(!field.getName().equalsIgnoreCase("serialversionuid") && !field.getName().equalsIgnoreCase("$change")){
                field.setAccessible(true);
                try{
                    if(field.get(obj) != null){
                        values.add(field.get(obj).toString());
                    }
                } catch (IllegalAccessException e) {
                    Log.e("ERROR TAG", "Illegal access field for monkeySettingsObj");
                }
            }
        }
        return values;
    }
}
