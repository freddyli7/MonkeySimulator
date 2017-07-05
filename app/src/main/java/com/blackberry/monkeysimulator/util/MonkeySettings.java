package com.blackberry.monkeysimulator.util;

import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by frlee on 5/30/17.
 */

public class MonkeySettings {

    // General
    private String eventNumber; // eventnumber
    private String seedNumber; // seed value for pseudo-random number generator
    private String informationLevel; // information level

    // Events
    private String throttle;
    private String pctTouch;
    private String pctMotion;
    private String pctTrackball;
    private String pctNav;
    private String pctMajornav;
    private String pctSyskeys;
    private String pctAppswitch;
    private String pctAnyevent;

    // Debugging
    private String dbgnoevent;
    private String hprof;
    private String ignoreCrashes;
    private String ignoreTimeout;
    private String ignoreSecurityExceptions;
    private String killProcessAfterError;
    private String monitorNativeCrashes;
    private String waitDbg;

    // Constraints
//    private String c;

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

    public String getInformationLevel() {
        return informationLevel;
    }

    public void setInformationLevel(String informationLevel) {
        this.informationLevel = informationLevel;
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

    public String getIgnoreSecurityExceptions() {
        return ignoreSecurityExceptions;
    }

    public void setIgnoreSecurityExceptions(String ignoreSecurityExceptions) {
        this.ignoreSecurityExceptions = ignoreSecurityExceptions;
    }

    public String getKillProcessAfterError() {
        return killProcessAfterError;
    }

    public void setKillProcessAfterError(String killProcessAfterError) {
        this.killProcessAfterError = killProcessAfterError;
    }

    public String getMonitorNativeCrashes() {
        return monitorNativeCrashes;
    }

    public void setMonitorNativeCrashes(String monitorNativeCrashes) {
        this.monitorNativeCrashes = monitorNativeCrashes;
    }

    public String getWaitDbg() {
        return waitDbg;
    }

    public void setWaitDbg(String waitDbg) {
        this.waitDbg = waitDbg;
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

}
