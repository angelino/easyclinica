package br.com.easyclinica.domain.entities.builder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.domain.repositories.AllDoctors;
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;
import br.com.easyclinica.domain.repositories.AllPatients;
import br.com.easyclinica.domain.repositories.AllProcedures;
import br.com.easyclinica.domain.repositories.AllSpecialties;
import br.com.easyclinica.tests.helpers.DoctorBuilder;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.PatientBuilder;
import br.com.easyclinica.tests.helpers.ProcedureBuilder;
import br.com.easyclinica.tests.helpers.SpecialtyBuilder;
import br.com.easyclinica.view.model.AppointmentData;
import br.com.easyclinica.view.model.ProcedureData;

public class AppointmentBuilderTests {

	private Patient patient;
	private AppointmentBuilder builder;
	private AllPatients allPatients;
	private AllHealthCarePlans allHealthCarePlans;
	private HealthCarePlan healthCarePlan;
	private Doctor doctor;
	private AllDoctors allDoctors;
	private Specialty specialty;
	private AllSpecialties allSpecialties;
	private Calendar appointmentDate;
	private AllProcedures allProcedures;

	@Before
	public void setUp() {
		patient = new PatientBuilder(15).withName("John Doe").instance();
		healthCarePlan = new HealthCarePlanBuilder(25).withName("Amil").instance();
		doctor = new DoctorBuilder(35).withName("Dr. House").instance();
		specialty = new SpecialtyBuilder(45).instance();
		
		allPatients = mock(AllPatients.class);
		allHealthCarePlans = mock(AllHealthCarePlans.class);
		allDoctors = mock(AllDoctors.class);
		allSpecialties = mock(AllSpecialties.class);
		allProcedures = mock(AllProcedures.class);
		
		when(allPatients.getById(patient.getId())).thenReturn(patient);
		when(allHealthCarePlans.getById(healthCarePlan.getId())).thenReturn(healthCarePlan);
		when(allDoctors.getById(doctor.getId())).thenReturn(doctor);
		when(allSpecialties.getById(specialty.getId())).thenReturn(specialty);
				
		appointmentDate = Calendar.getInstance();
		
		builder = new AppointmentBuilder(allPatients, allHealthCarePlans, allDoctors, allSpecialties, allProcedures);
	}
	
	@Test
	public void shouldGetPatient() {
		Appointment a = builder.basedOn(aFormData());
		assertEquals(patient, a.getPatient());
	}
	
	@Test
	public void shouldGetHealthCarePlan() {
		Appointment a = builder.basedOn(aFormData());
		assertEquals(healthCarePlan, a.getHealthCarePlan());
	}
	
	@Test
	public void shouldGetDoctor() {
		Appointment a = builder.basedOn(aFormData());
		assertEquals(doctor, a.getDoctor());		
	}

	@Test
	public void shouldGetSpecialty() {
		Appointment a = builder.basedOn(aFormData());
		assertEquals(specialty, a.getSpecialty());		
	}
	
	@Test
	public void shouldGetAppointmentBasicInfo() {
		Appointment a = builder.basedOn(aFormData());
		assertEquals(appointmentDate, a.getDate());
		assertEquals(false, a.isReturn());
	}
	
	@Test
	public void shouldGetAllProcedures() {
		AppointmentData data = aFormData();
		
		ProcedureData firstProcedureData = new ProcedureData();
		firstProcedureData.setId(123);
		data.getProcedures().add(firstProcedureData);

		ProcedureData secondProcedureData = new ProcedureData();
		secondProcedureData.setId(456);
		data.getProcedures().add(secondProcedureData);
		
		when(allProcedures.getById(123)).thenReturn(new ProcedureBuilder(123).instance());
		when(allProcedures.getById(456)).thenReturn(new ProcedureBuilder(456).instance());
		
		Appointment a = builder.basedOn(data);
		assertEquals(123, a.getProcedures().get(0).getProcedure().getId());
		assertEquals(456, a.getProcedures().get(1).getProcedure().getId());
		
		assertEquals(0, a.getProcedureAmount(), 0.000001);
	}
	
	private AppointmentData aFormData() {
		AppointmentData formData = new AppointmentData();
		formData.setPatient(patient.getId());
		formData.setHealthCarePlan(healthCarePlan.getId());
		formData.setDoctor(doctor.getId());
		formData.setSpecialty(specialty.getId());
		formData.setDate(appointmentDate);
		formData.setReturn(false);
		
		return formData;
	}
	
	
}
