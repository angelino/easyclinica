package br.com.easyclinica.domain.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.infra.dao.AppointmentDao;
import br.com.easyclinica.infra.dao.BaseIntegrationTests;
import br.com.easyclinica.infra.dao.HealthCarePlanDao;
import br.com.easyclinica.tests.helpers.DoctorBuilder;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.PatientBuilder;
import br.com.easyclinica.tests.helpers.SpecialtyBuilder;

public class VerifyIfAnAppointmentIsReturnServiceTests  extends BaseIntegrationTests {
	private VerifyIfAnAppointmentIsReturnService service;
	private HealthCarePlan plan;
	private Patient patient;
	private Doctor doctor;
	private Specialty specialty;
	
	@Before
	public void setUp() {
		service = new VerifyIfAnAppointmentIsReturnService(new HealthCarePlanDao(session), new AppointmentDao(session));
		
		this.plan = aSavedHealthCarePlan();
		this.patient = aSavedPatient();
		this.doctor = aSavedDoctor();
		this.specialty = aSavedSpecialty();
		
		this.patient.setHealthCarePlan(this.plan);
		this.doctor.setSpecialty(this.specialty);
	}
	
	@Test
	public void shouldReturnTrueIfIsReturn() {		
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(Calendar.getInstance());
		appointment.setDate(Calendar.getInstance());
		appointment.setDoctor(doctor);
		appointment.setHealthCarePlan(plan);
		appointment.setPatient(patient);
		appointment.setSpecialty(specialty);
		session.save(appointment);
		
		Calendar nextAppointment =  Calendar.getInstance();
		nextAppointment.add(Calendar.DAY_OF_MONTH, 2);
		boolean retorno = service.check(patient.getId(), specialty.getId(), plan.getId(), nextAppointment);
		
		assertTrue(retorno);
	}
	
	@Test
	public void shouldReturnFalseIfIsNotReturn() {
		Calendar appointmentDate = Calendar.getInstance();
		appointmentDate.add(Calendar.DAY_OF_MONTH, -60);
		
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(appointmentDate);
		appointment.setDate(Calendar.getInstance());
		appointment.setDoctor(doctor);
		appointment.setHealthCarePlan(plan);
		appointment.setPatient(patient);
		appointment.setSpecialty(specialty);
		session.save(appointment);
		
		boolean retorno = service.check(patient.getId(), specialty.getId(), plan.getId(), Calendar.getInstance());
		
		assertFalse(retorno);
	}
	
	@Test
	public void shouldReturnFalseIfThereIsNotAnAppointmentForThePatient() {
		boolean retorno = service.check(patient.getId(), specialty.getId(), plan.getId(), Calendar.getInstance());
		
		assertFalse(retorno);
	}
	
	private HealthCarePlan aSavedHealthCarePlan() {
		HealthCarePlan plan = new HealthCarePlanBuilder().withPeriodToReturn(30).instance();
		session.save(plan);
		return plan;
	}
	
	private Patient aSavedPatient() {
		Patient patient = new PatientBuilder().instance();
		session.save(patient);
		return patient;
	}
	
	private Doctor aSavedDoctor() {
		Doctor doctor = new DoctorBuilder().instance();
		session.save(doctor);
		return doctor;
	}
	
	private Specialty aSavedSpecialty() {
		Specialty specialty = new SpecialtyBuilder().instance();
		session.save(specialty);
		return specialty;
	}
}
