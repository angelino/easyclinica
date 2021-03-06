package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.tests.helpers.DoctorBuilder;

public class DoctorDaoTests extends DaoBase {
	private DoctorDao dao;
	private Specialty specialty;
	
	@Before
	public void setUp() {
		dao = new DoctorDao(session);
		
		specialty = new Specialty();
		specialty.setName("pediatra");
		session.save(specialty);
	}
		
	@Test
	public void shouldAdd() {
		dao.add(aDoctor());
		
		List<Doctor> list = dao.get();
		assertEquals(1, list.size());
		
		Doctor newOne = list.get(0);
		assertEquals("Doutor", newOne.getName().toString());
		assertEquals("55.555", newOne.getCrm().toString());
		assertEquals("pediatra", newOne.getSpecialty().getName());
		assertEquals("55 11 9999-9999", newOne.getTelephone().toString());
		assertEquals("doutor@easyclinica.com.br", newOne.getEmail().toString());
		assertEquals("", newOne.getObservations().toString());
	}

	@Test
	public void shouldUpdate() {
		Doctor doctor = aDoctor();
		dao.add(doctor);
		
		Doctor updatedDoctor = new DoctorBuilder(doctor.getId())
			.withName("new Doctor")
			.withSpecialty(specialty)
			.instance();
		dao.update(updatedDoctor);
		
		Doctor secondRetrievedDoctor = dao.getById(doctor.getId());
		assertNotNull(secondRetrievedDoctor);
		assertEquals("new Doctor", secondRetrievedDoctor.getName().toString());
	}

	@Test
	public void shouldGetAllActivatedDoctors() {
		Doctor doctorOne = aDoctor();
		Doctor doctorTwo = aDoctor();
		doctorOne.activate();
		doctorTwo.deactivate();
		
		session.save(doctorOne);
		session.save(doctorTwo);
		
		List<Doctor> list = dao.allActive();
		
		assertEquals(1, list.size());
		assertEquals(doctorOne.getId(), list.get(0).getId());
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
		return new DoctorBuilder().withSpecialty(specialty).instance();
	}
	
	@Test
	public void shouldCountElements() {
		Doctor firstDoctor = new DoctorBuilder().withSpecialty(specialty).instance();
		Doctor secondDoctor = new DoctorBuilder().withSpecialty(specialty).instance();
		
		dao.add(firstDoctor);
		dao.add(secondDoctor);
		
		assertEquals(2, dao.count());
	}
	
	@Test
	public void shouldPaginate() {
		Doctor firstDoctor = new DoctorBuilder()
								.withSpecialty(specialty)
								.withName("Doutor 1")
								.instance();
		
		Doctor secondDoctor = new DoctorBuilder()
								.withSpecialty(specialty)
								.withName("Doutor 2")
								.instance();
		
		dao.add(firstDoctor);
		dao.add(secondDoctor);
		
		assertEquals(firstDoctor.getName().toString(), dao.get(0, 1).get(0).getName().toString());
	}
}
