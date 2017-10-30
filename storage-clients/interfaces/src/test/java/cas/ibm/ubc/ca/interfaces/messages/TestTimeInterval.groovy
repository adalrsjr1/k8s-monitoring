package cas.ibm.ubc.ca.interfaces.messages

import java.util.concurrent.TimeUnit

class TestTimeInterval extends GroovyTestCase {

	void testTimeInterval() {
		TimeInterval t = TimeInterval.last(10L, TimeUnit.MILLISECONDS)
		assert (t.getEnd() - t.getBegin()) == 10L
		
		t = TimeInterval.last(1L, TimeUnit.SECONDS)
		assert (t.getEnd() - t.getBegin()) == 1000L
		
		t = TimeInterval.last(1L, TimeUnit.HOURS)
		assert (t.getEnd() - t.getBegin()) == 1000L * 60 * 60
		
		t = TimeInterval.last(1L, TimeUnit.DAYS)
		assert (t.getEnd() - t.getBegin()) == 1000L * 60 * 60 * 24
		
		t = TimeInterval.last(2L, TimeUnit.DAYS)
		assert (t.getEnd() - t.getBegin()) == 2000L * 60 * 60 * 24
	}
	
	void testTimeIntervalDiff() {
		TimeInterval t = TimeInterval.last(10L, TimeUnit.MILLISECONDS)
		assert t.getIntervalInMillis() == 10L
		
		t = TimeInterval.last(1L, TimeUnit.SECONDS)
		assert t.getIntervalInMillis() == 1000L
		
		t = TimeInterval.last(1L, TimeUnit.HOURS)
		assert t.getIntervalInMillis() == 1000L * 60 * 60
		
		t = TimeInterval.last(1L, TimeUnit.DAYS)
		assert t.getIntervalInMillis() == 1000L * 60 * 60 * 24
		
		t = TimeInterval.last(2L, TimeUnit.DAYS)
		assert t.getIntervalInMillis() == 2000L * 60 * 60 * 24
	}
	
}
