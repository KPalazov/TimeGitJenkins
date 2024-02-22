package time;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	@Test
	void testGetTotalSecondsGood() {
		int seconds = Time.getTotalSeconds("05:05:05");
		assertTrue("The seconds were not correct", seconds == 18305);
	}
	
	@Test
	public void testGetTotalSecondsBad()
	{
	assertThrows(
	StringIndexOutOfBoundsException.class,
	()-> {Time.getTotalSeconds("10:00");});
	}
	
	@Test
	void testGetTotalSecondsBoundary() {
		int seconds = Time.getTotalSeconds("00:00:00");
		assertTrue("The seconds were not correct", seconds == 0);
	}

	
	@ParameterizedTest
	@ValueSource(strings = {"05:05:05", "23:59:59"})
	void testGetSecondsGood(String candidate) {
		int seconds = Time.getSeconds(candidate);
		if (candidate.equals("05:05:05")) {
			assertTrue("The seconds were not correct", seconds == 5);
		} else {
			assertTrue("The seconds were not correct", seconds == 59);
		}
	}
	
	@Test
	public void testGetSecondsBad() {
	assertThrows(NumberFormatException.class,
			() -> {Time.getSeconds("10:00:mm");});
    }
	
	@Test
	public void testGetSecondsBoundary() {
		int seconds = Time.getSeconds("00:00:00");
		assertTrue("The seconds were not correct", seconds == 0);
	}

	
	@Test
	void testGetTotalMinutesGood() {
		int minutes = Time.getTotalMinutes("05:05:05");
		assertTrue("The minutes were not correct", minutes == 5);
	}
	
	@Test
	public void testGetTotalMinutesBad() {
		assertThrows(NumberFormatException.class, () -> {
			Time.getTotalMinutes("10:mm:00");
		});
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"05:00:05", "23:59:59"})
	public void testGetTotalMinutesBoundary(String candidate) {
		int minutes = Time.getTotalMinutes(candidate);
		if (candidate.equals("05:00:05")) {
			assertTrue("The minutes were not correct", minutes == 0);
		} else {
			assertTrue("The minutes were not correct", minutes == 59);
		}
	}

	@ParameterizedTest
	@ValueSource(strings = {"05:05:05", "05:10:10", "05:15:15"})
	void testGetTotalHoursGood(String candidate) {
		int hours = Time.getTotalHours(candidate);
		assertTrue("The hours were not correct", hours == 5);
	}
	
	@Test
	public void testGetTotalHoursBad() {
		assertThrows(NumberFormatException.class, () -> {
			Time.getTotalHours("hh:00:00");
		});
	}
	
	@Test
	public void testGetTotalHoursBoundary() {
		int hours = Time.getTotalHours("00:00:00");
		assertTrue("The hours were not correct", hours == 0);
	}

}
