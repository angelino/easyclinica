package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.tests.helpers.DoctorBuilder;
import br.com.easyclinica.tests.helpers.ScheduleBuilder;

public class ScheduleDaoTests  extends BaseIntegrationTests  {

	private ScheduleDao dao;
	
	@Before
	public void setUp() {
		dao = new ScheduleDao(session);
	}
	
	@Test
	public void shouldGetDoctorScheduleByPeriod() { 
		Doctor doctor = aDoctor();
		
		Schedule schedule1 = new ScheduleBuilder().ofTheDoctor(doctor)
												 .withSubject("Compromisso 1")
												 .withStartTime(Calendar.getInstance())
												 .withEndTime(Calendar.getInstance())
												 .instance();
		session.save(schedule1);
		
		Schedule schedule2 = new ScheduleBuilder().ofTheDoctor(doctor)
												 .withSubject("Compromisso 1")
												 .withStartTime(Calendar.getInstance())
												 .withEndTime(Calendar.getInstance())
												 .instance();
		session.save(schedule2);
		
		Calendar start = Calendar.getInstance();
		start.set(Calendar.HOUR_OF_DAY, 0);
		
		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, 23);
		
		List<Schedule> schedules = dao.getDoctorScheduleByPeriod(doctor, start, end);
		
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
