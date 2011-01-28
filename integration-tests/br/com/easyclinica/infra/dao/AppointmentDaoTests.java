package br.com.easyclinica.infra.dao;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.tests.helpers.DoctorBuilder;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.PatientBuilder;
import br.com.easyclinica.tests.helpers.SpecialtyBuilder;

public class AppointmentDaoTests extends BaseIntegrationTests {

	private AppointmentDao dao;

	@Before
	public void setUp() {
		dao = new AppointmentDao(session);
	}
	
	@Test
	public void shouldAdd() {
		Specialty specialty = aSavedSpecialty();
		Doctor doctor = aSavedDoctor(specialty);
		HealthCarePlan healthCarePlan = aSavedHealthCarePlan();
		Patient patient = aSavedPatient(healthCarePlan);
		
		
		Appointment newAppointment = new Appointment();
		newAppointment.setDoctor(doctor);
		newAppointment.setHealthCarePlan(healthCarePlan);
		newAppointment.setSpecialty(specialty);
		newAppointment.setObservations("obs");
		newAppointment.setPatient(patient);
		newAppointment.setReturn(true);
		Calendar appointmentDate = Calendar.getInstance();
		newAppointment.setAppointmentDate(appointmentDate);
		
		dao.save(newAppointment);
		assertTrue(newAppointment.getId()>0);
		
		Appointment loadedAppointment = dao.getById(newAppointment.getId()); 
		
		assertEquals(patient.getName(), loadedAppointment.getPatient().getName());
		assertEquals(doctor.getName(), loadedAppointment.getDoctor().getName());
		assertEquals(specialty.getName(), loadedAppointment.getSpecialty().getName());
		assertEquals(healthCarePlan.getName(), loadedAppointment.getHealthCarePlan().getName());
		assertEquals("obs", loadedAppointment.getObservations());
		assertTrue(loadedAppointment.isReturn());
		assertEquals(appointmentDate, loadedAppointment.getAppointmentDate());
	}

	private Patient aSavedPatient(HealthCarePlan healthCarePlan) {
		Patient p = new PatientBuilder().withHealthCarePlan(healthCarePlan).withName("John Doe").instance();
		session.save(p);
		return p;
	}

	private Specialty aSavedSpecialty() {
		Specialty s = new SpecialtyBuilder().withName("ortopedia").instance();
		session.save(s);
		return s;
	}

	private HealthCarePlan aSavedHealthCarePlan() {
		HealthCarePlan plan = new HealthCarePlanBuilder().withName("bla").withCh(10).instance();
		session.save(plan);
		return plan;
	}

	private Doctor aSavedDoctor(Specialty specialty) {
		Doctor d = new DoctorBuilder().withName("Dr. House").withSpecialty(specialty).instance();
		session.save(d);
		return d;
	}
}
