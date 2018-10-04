package com.livelabdrools.utility;

public class TimeTracker {

	private Long startTime = (long)0;
	private Long endTime = (long)0;

	public TimeTracker() {
		this.startTime = System.currentTimeMillis();
	}

	public Long getEndTime() {
		return endTime;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setEndTime() {
		this.endTime = System.currentTimeMillis();
	}

	public Long getTotalTimeElapsed() {
		return endTime - startTime;
	}
}
