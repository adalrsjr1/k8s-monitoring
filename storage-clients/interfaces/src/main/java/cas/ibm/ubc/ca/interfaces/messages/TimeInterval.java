package cas.ibm.ubc.ca.interfaces.messages;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class TimeInterval {
	
	private final Long begin;
	private final Long end;
	
	private TimeInterval(Long begin, Long end) {
		this.begin = begin;
		this.end = end;
	}
	
	public static TimeInterval create(Long begin, Long end) {
		return new TimeInterval(begin, end);
	}
	
	public static TimeInterval last(Long interval, TimeUnit timeUnit) {
		Long endTime = TimeUnit.MILLISECONDS.convert(interval, timeUnit);
		long now = Instant.now().toEpochMilli();
		long begin = now - endTime;

		return TimeInterval.create(begin, now);
	}
	
	public Long getBegin() {
		return begin;
	}
	
	public Long getEnd() {
		return end;
	}
	
	public Long getIntervalInMillis() {
		return end - begin;
	}
}
