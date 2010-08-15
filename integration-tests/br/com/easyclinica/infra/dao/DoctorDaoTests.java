package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.tests.helpers.DoctorBuilder;

public class DoctorDaoTests {
	private Session session;
	private DoctorDao dao;

	@Before
	public void setUp() {
		session = new AnnotationConfiguration().configure("test-hibernate.cfg.xml").buildSessionFactory().openSession();
		session.beginTransaction();
		dao = new DoctorDao(session);
	}
	
	@After
	public void tearDown() {
		session.getTransaction().rollback();
		session.close();
	}
	
	@Test
	public void shouldAdd() {
		dao.add(aDoctor());
		
		List<Doctor> list = dao.get();
		assertEquals(1, list.size());
		
		Doctor newOne = list.get(0);
		assertEquals("Doutor", newOne.getName().toString());
		assertEquals("55.555", newOne.getCrm().toString());
		assertEquals("pediatra", newOne.getSpecialty().toString());
		assertEquals("55 11 9999-9999", newOne.getTelephone().toString());
		assertEquals("doutor@easyclinica.com.br", newOne.getEmail().toString());
		assertEquals("", newOne.getObservations().toString());
	}

	@Test
	public void shouldUpdate() {
		Doctor doctor = aDoctor();
		dao.add(doctor);
		
		Doctor updatedDoctor = aDoctor(doctor.getId(), "new Doctor");
		dao.update(updatedDoctor);
		
		Doctor secondRetrievedDoctor = dao.getById(doctor.getId());
		assertNotNull(secondRetrievedDoctor);
		assertEquals("new Doctor", secondRetrievedDoctor.getName().toString());
	}
	
	@Test
	public void shouldGetById() {
		Doctor doctor = aDoctor();
		dao.add(doctor);
		
		Doctor retrievedDoctor = dao.getById(doctor.getId());
		
		assertNotNull(retrievedDoctor);
		assertEquals(doctor.getId(), retrievedDoctor.getId());
	}
	
	private Doctor aDoctor() {
		return new DoctorBuilder().instance();
	}
	
	private Doctor aDoctor(int id, String name) {
		return new DoctorBuilder().withIdAndName(id, name).instance();
	}
}
