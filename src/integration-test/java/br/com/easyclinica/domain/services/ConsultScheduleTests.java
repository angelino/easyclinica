package br.com.easyclinica.domain.services;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.infra.dao.DaoBase;
import br.com.easyclinica.infra.dao.ScheduleDao;
import br.com.easyclinica.tests.helpers.DoctorBuilder;
import br.com.easyclinica.tests.helpers.ScheduleBuilder;

public class ConsultScheduleTests extends DaoBase {

	private SearchSchedule service;
	
	@Before
	public void setUp() {
		service = new SearchSchedule(new ScheduleDao(session));
	}

	@Test
	public void shouldReturnTheDoctorScheduleOfADay() {
		Doctor doctor = aDoctor();
		
		Schedule schedule1 = new ScheduleBuilder().ofTheDoctor(doctor)
												 .withSubject("Compromisso 1")
												 .withStartTime(Calendar.getInstance())
												 .withEndTime(Calendar.getInstance())
												 .instance();
		session.save(schedule1);
		
		Schedule schedule2 = new ScheduleBuilder().ofTheDoctor(doctor)
												 .withSubject("Compromisso 2")
												 .withStartTime(Calendar.getInstance())
												 .withEndTime(Calendar.getInstance())
												 .instance();
		session.save(schedule2);
		
		List<Schedule> schedules = service.ofTheDoctor(doctor)
										  .from(Calendar.getInstance())
										  .execute(SearchSchedule.VIEW_TYPE_DAY);
		
		assertEquals(schedules.size(), 2);
	}
	
	@Test
	public void shouldReturnTheDoctorScheduleOfTheWeek() {
		Doctor doctor = aDoctor();
		
		Schedule schedule1 = new ScheduleBuilder().ofTheDoctor(doctor)
												 .withSubject("Compromisso 1")
												 .withStartTime(Calendar.getInstance())
												 .withEndTime(Calendar.getInstance())
												 .instance();
		session.save(schedule1);
		
		Calendar lastDayOfThisWeek = service.getLastDayOfWeek(schedule1.getStartTime());
		Schedule schedule2 = new ScheduleBuilder().ofTheDoctor(doctor)
												 .withSubject("Compromisso 2")
												 .withStartTime(lastDayOfThisWeek)
												 .withEndTime(lastDayOfThisWeek)
												 .instance();
		session.save(schedule2);
		
		List<Schedule> schedules = service.ofTheDoctor(doctor)
										  .from(Calendar.getInstance())
										  .execute(SearchSchedule.VIEW_TYPE_WEEK);
		
		assertEquals(schedules.size(), 2);
	}
	
	@Test
	public void shouldReturnTheDoctorScheduleOfTheMonth() {
		Doctor doctor = aDoctor();
		
		Calendar firstDayOfMonth = Calendar.getInstance();
		firstDayOfMonth.set(Calendar.DAY_OF_MONTH, 1);
		Schedule schedule1 = new ScheduleBuilder().ofTheDoctor(doctor)
												 .withSubject("Compromisso 1")
												 .withStartTime(firstDayOfMonth)
												 .withEndTime(firstDayOfMonth)
												 .instance();
		session.save(schedule1);
		
		Calendar twoWeeksAfter = Calendar.getInstance();
		twoWeeksAfter.set(Calendar.DAY_OF_MONTH, 20);
		Schedule schedule2 = new ScheduleBuilder().ofTheDoctor(doctor)
												 .withSubject("Compromisso 2")
												 .withStartTime(twoWeeksAfter)
												 .withEndTime(twoWeeksAfter)
												 .instance();
		session.save(schedule2);
		
		List<Schedule> schedules = service.ofTheDoctor(doctor)
										  .from(Calendar.getInstance())
										  .execute(SearchSchedule.VIEW_TYPE_MONTH);
		
		assertEquals(schedules.size(), 2);
	}
	
	private Doctor aDoctor() {
		Specialty specialty = new Specialty();
		specialty.setName("pediatra");
		session.save(specialty);
		
		Doctor doctor = new DoctorBuilder().withSpecialty(specialty).instance();
		session.save(doctor);
		
		return doctor;
	}
}
