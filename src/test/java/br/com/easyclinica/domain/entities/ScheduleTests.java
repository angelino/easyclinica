package br.com.easyclinica.domain.entities;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import br.com.easyclinica.tests.helpers.ScheduleBuilder;

public class ScheduleTests {

	@Test
	public void shouldCalculateTheDurationOfTheEvent() {
		Calendar startTime = Calendar.getInstance();
		Calendar endTime = (Calendar) startTime.clone();
		endTime.add(Calendar.HOUR, 2);
		endTime.add(Calendar.MINUTE, 15);
		
		Schedule schedule = new ScheduleBuilder().withStartTime(startTime)
												 .withEndTime(endTime)
												 .instance();
		
		assertEquals("2:15", schedule.getDuration());
	}
}
