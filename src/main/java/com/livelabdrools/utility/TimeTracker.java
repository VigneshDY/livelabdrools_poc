package com.livelabdrools.utility;

public class TimeTracker {

    private long startTime=0;
    private long endTime=0;


    public TimeTracker() {
        this.startTime=System.currentTimeMillis();
    }

    public long getEndTime() {
        return endTime;
    }

    public long getStartTime() {
        return startTime;
    }    public void setEndTime(){
        this.endTime=System.currentTimeMillis();
    }
    public long getTotalTimeElapsed(){
         return endTime-startTime;
    }
}
