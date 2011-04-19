package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.hibernate.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Anamnese;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.tests.helpers.AnamneseBuilder;
import br.com.easyclinica.tests.helpers.DoctorBuilder;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;
import br.com.easyclinica.tests.helpers.PatientBuilder;

public class AnamneseDaoTests extends DaoBase {

	private AnamneseDao dao;
	private Doctor doctor;
	private Patient patient;
	private HealthCarePlan plan;

	@Before
	public void setUp() {
		dao = new AnamneseDao(session);
		
		doctor = new DoctorBuilder().withName("john doe").instance();
		session.save(doctor);
		
		plan = new HealthCarePlanBuilder().withName("plan").instance();
		session.save(plan);
		
		patient = new PatientBuilder().withName("patient").withHealthCarePlan(plan).instance();
		session.save(patient);
	}

	@Test(expected=ObjectNotFoundException.class)
	public void shouldDelete() {
		Anamnese anamnese = aAnamnese();
		dao.add(anamnese);
		
		Anamnese retrievedAnamnese = dao.getById(anamnese.getId());
		assertNotNull(retrievedAnamnese);
		
		dao.delete(retrievedAnamnese);
		
		dao.getById(anamnese.getId());
	}
	
	@Test
	public void shouldGetById() {
		Anamnese anamnese = aAnamnese();
		dao.add(anamnese);
		
		Anamnese retrievedAnamnese = dao.getById(anamnese.getId());
		
		assertNotNull(retrievedAnamnese);
		assertEquals(anamnese.getId(), retrievedAnamnese.getId());
	}
	
	@Test
	public void shouldUpdate() {
		Anamnese anamnese = aAnamnese();
		dao.add(anamnese);
		
		Anamnese updatedAnamnese = new AnamneseBuilder(anamnese.getId())
			.withText("new text")
			.instance();
		dao.update(updatedAnamnese);
		
		Anamnese secondRetrievedAnamnese = dao.getById(anamnese.getId());
		assertNotNull(secondRetrievedAnamnese);
		assertEquals("new text", secondRetrievedAnamnese.getText().toString());
	}

	private Anamnese aAnamnese() {
		return new AnamneseBuilder().withDoctor(doctor).withText("anamnese here").withPatient(patient).instance();
	}

}
