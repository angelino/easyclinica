package br.com.easyclinica.infra.dao;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;

import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.tests.helpers.ClinicBuilder;

public abstract class BaseIntegrationTests {

	protected Clinic clinic;
	protected Session session;

	@Before
	public void createEMandDefaultClinic() {
		session = new Configuration().configure("hibernate-test.cfg.xml").buildSessionFactory().openSession();
		session.getTransaction().begin();
		
		ClinicDao clinicDao = new ClinicDao(session);
		clinic = new ClinicBuilder().withName("EasyClinica").withDomain("easyclinica").instance();
		clinicDao.add(clinic);
	}
	
	@After
	public void rollbackDatabaseChanges() {
		// TODO: o melhor aqui eh commitar e truncar todos os dados
		session.getTransaction().rollback();
		
		session.close();
	}
	
}
