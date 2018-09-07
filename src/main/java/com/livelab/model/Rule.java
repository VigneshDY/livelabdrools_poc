package com.livelab.model;

public class Rule {
    private String location;
    private String TimeZone;
    public Rule() {

        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Rule [location=" + location + ", TimeZone=" + TimeZone + "]";
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getTimeZone() {
        return TimeZone;
    }
    public void setTimeZone(String timeZone) {
        TimeZone = timeZone;
    }

}

