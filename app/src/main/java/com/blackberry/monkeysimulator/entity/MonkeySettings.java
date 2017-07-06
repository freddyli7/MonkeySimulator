package com.blackberry.monkeysimulator.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frlee on 5/30/17.
 */

public class MonkeySettings {

    // General
    private String event_number; // eventnumber
    private String seed_number; // seed value for pseudo-random number generator
    private String information_level; // information level

    // Events
    private String throttle;
    private String pct_touch;
    private String pct_motion;
    private String pct_trackball;
    private String pct_nav;
    private String pct_majornav;
    private String pct_syskeys;
    private String pct_appswitch;
    private String pct_anyevent;

    // Debugging
    private String dbg_no_event;
    private String hprof;
    private String ignore_crashes;
    private String ignore_timeout;
    private String ignore_security_exceptions;
    private String kill_process_after_error;
    private String monitor_native_crashes;
    private String wait_dbg;

    // Constraints
//    private String c;

    // help
//    private String help;


    public String getEvent_number() {
        return event_number;
    }

    public void setEvent_number(String event_number) {
        this.event_number = event_number;
    }

    public String getSeed_number() {
        return seed_number;
    }

    public void setSeed_number(String seed_number) {
        this.seed_number = seed_number;
    }

    public String getInformation_level() {
        return information_level;
    }

    public void setInformation_level(String information_level) {
        this.information_level = information_level;
    }

    public String getThrottle() {
        return throttle;
    }

    public void setThrottle(String throttle) {
        this.throttle = throttle;
    }

    public String getPct_touch() {
        return pct_touch;
    }

    public void setPct_touch(String pct_touch) {
        this.pct_touch = pct_touch;
    }

    public String getPct_motion() {
        return pct_motion;
    }

    public void setPct_motion(String pct_motion) {
        this.pct_motion = pct_motion;
    }

    public String getPct_trackball() {
        return pct_trackball;
    }

    public void setPct_trackball(String pct_trackball) {
        this.pct_trackball = pct_trackball;
    }

    public String getPct_nav() {
        return pct_nav;
    }

    public void setPct_nav(String pct_nav) {
        this.pct_nav = pct_nav;
    }

    public String getPct_majornav() {
        return pct_majornav;
    }

    public void setPct_majornav(String pct_majornav) {
        this.pct_majornav = pct_majornav;
    }

    public String getPct_syskeys() {
        return pct_syskeys;
    }

    public void setPct_syskeys(String pct_syskeys) {
        this.pct_syskeys = pct_syskeys;
    }

    public String getPct_appswitch() {
        return pct_appswitch;
    }

    public void setPct_appswitch(String pct_appswitch) {
        this.pct_appswitch = pct_appswitch;
    }

    public String getPct_anyevent() {
        return pct_anyevent;
    }

    public void setPct_anyevent(String pct_anyevent) {
        this.pct_anyevent = pct_anyevent;
    }

    public String getDbg_no_event() {
        return dbg_no_event;
    }

    public void setDbg_no_event(String dbg_no_event) {
        this.dbg_no_event = dbg_no_event;
    }

    public String getHprof() {
        return hprof;
    }

    public void setHprof(String hprof) {
        this.hprof = hprof;
    }

    public String getIgnore_crashes() {
        return ignore_crashes;
    }

    public void setIgnore_crashes(String ignore_crashes) {
        this.ignore_crashes = ignore_crashes;
    }

    public String getIgnore_timeout() {
        return ignore_timeout;
    }

    public void setIgnore_timeout(String ignore_timeout) {
        this.ignore_timeout = ignore_timeout;
    }

    public String getIgnore_security_exceptions() {
        return ignore_security_exceptions;
    }

    public void setIgnore_security_exceptions(String ignore_security_exceptions) {
        this.ignore_security_exceptions = ignore_security_exceptions;
    }

    public String getKill_process_after_error() {
        return kill_process_after_error;
    }

    public void setKill_process_after_error(String kill_process_after_error) {
        this.kill_process_after_error = kill_process_after_error;
    }

    public String getMonitor_native_crashes() {
        return monitor_native_crashes;
    }

    public void setMonitor_native_crashes(String monitor_native_crashes) {
        this.monitor_native_crashes = monitor_native_crashes;
    }

    public String getWait_dbg() {
        return wait_dbg;
    }

    public void setWait_dbg(String wait_dbg) {
        this.wait_dbg = wait_dbg;
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
