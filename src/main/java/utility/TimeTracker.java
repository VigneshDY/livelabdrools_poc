package utility;

import org.apache.commons.lang3.time.DurationFormatUtils;

public class TimeTracker {
	private long startTime;
	private long endTime;

	public TimeTracker() {
		this.startTime = System.currentTimeMillis();
	}

	public long getStartTime() {
		// return DurationFormatUtils.formatDuration(startTime, "HH:mm:ss,SSS");
		return startTime;
	}

	public void setEndTime() {
		this.endTime = System.currentTimeMillis();
	}

	public long getEndTime() {
		// return DurationFormatUtils.formatDuration(endTime, "HH:mm:ss,SSS");
		return endTime;
	}

	public long totalTimeElapsed() {
		// long totalTimeElapsed = endTime - startTime;
		// return DurationFormatUtils.formatDuration(totalTimeElapsed,
		// "HH:mm:ss,SSS");
		return endTime - startTime;
	}

}
