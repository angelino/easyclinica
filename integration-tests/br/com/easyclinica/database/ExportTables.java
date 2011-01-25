package br.com.easyclinica.database;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.AppointmentMaterial;
import br.com.easyclinica.domain.entities.AppointmentMedicine;
import br.com.easyclinica.domain.entities.AppointmentProcedure;
import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.Specialty;


public class ExportTables {

	@Test
	public void exportDatabase() {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.addAnnotatedClass(Appointment.class);
		cfg.addAnnotatedClass(AppointmentMaterial.class);
		cfg.addAnnotatedClass(AppointmentMedicine.class);
		cfg.addAnnotatedClass(AppointmentProcedure.class);
		cfg.addAnnotatedClass(Clinic.class);
		cfg.addAnnotatedClass(Doctor.class);
		cfg.addAnnotatedClass(HealthCarePlan.class);
		cfg.addAnnotatedClass(Material.class);
		cfg.addAnnotatedClass(Medicine.class);
		cfg.addAnnotatedClass(Patient.class);
		cfg.addAnnotatedClass(PrecifiedMaterial.class);
		cfg.addAnnotatedClass(PrecifiedMedicine.class);
		cfg.addAnnotatedClass(PrecifiedProcedure.class);
		cfg.addAnnotatedClass(Procedure.class);
		cfg.addAnnotatedClass(Specialty.class);
		cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		cfg.setProperty("hibernate.connection.username", "easyclinica");
		cfg.setProperty("hibernate.connection.password", "3@sycl1n1c@");
		cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/easyclinicatest");
		cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		
		
		SchemaExport se = new SchemaExport(cfg);
		se.execute(true, false, false, false);
	}
}
