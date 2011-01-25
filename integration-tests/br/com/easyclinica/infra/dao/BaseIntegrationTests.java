package br.com.easyclinica.infra.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.tests.helpers.ClinicBuilder;

public abstract class BaseIntegrationTests {

	protected Clinic clinic;
	protected EntityManager em;

	@Before
	public void createEMandDefaultClinic() {
		em = Persistence.createEntityManagerFactory("test").createEntityManager();
		em.getTransaction().begin();
		
		ClinicDao clinicDao = new ClinicDao(em);
		clinic = new ClinicBuilder().withName("EasyClinica").withDomain("easyclinica").instance();
		clinicDao.add(clinic);
	}
	
	@After
	public void rollbackDatabaseChanges() {
		em.getTransaction().rollback();
		em.close();
	}
	
}
