package br.com.easyclinica.infra.database;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import br.com.easyclinica.domain.entities.Anamnese;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.AppointmentMaterial;
import br.com.easyclinica.domain.entities.AppointmentMedicine;
import br.com.easyclinica.domain.entities.AppointmentProcedure;
import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.MaterialInProcedure;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.MedicineInProcedure;
import br.com.easyclinica.domain.entities.Message;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.AppointmentAssistant;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.Receipt;
import br.com.easyclinica.domain.entities.Reply;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.domain.entities.Specialty;

public class DatabaseConfigurator {

	public static Configuration config(String database) {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/" + database + "?useUnicode=true&characterEncoding=UTF-8");
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
		configuration.setProperty("hibernate.connection.username", "easyclinica");
		configuration.setProperty("hibernate.connection.password", "3@sycl1n1c@");
		configuration.setProperty("hibernate.hbm2ddl.auto", "true");
		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.format_sql", "true");
		configuration.setProperty("hibernate.jdbc.batch_size", "20");
		
		configuration.addAnnotatedClass(Appointment.class);
		configuration.addAnnotatedClass(AppointmentMaterial.class);
		configuration.addAnnotatedClass(AppointmentMedicine.class);
		configuration.addAnnotatedClass(AppointmentProcedure.class);
		configuration.addAnnotatedClass(Clinic.class);
		configuration.addAnnotatedClass(Doctor.class);
		configuration.addAnnotatedClass(HealthCarePlan.class);
		configuration.addAnnotatedClass(Material.class);
		configuration.addAnnotatedClass(Medicine.class);
		configuration.addAnnotatedClass(Patient.class);
		configuration.addAnnotatedClass(PrecifiedMaterial.class);
		configuration.addAnnotatedClass(PrecifiedMedicine.class);
		configuration.addAnnotatedClass(PrecifiedProcedure.class);
		configuration.addAnnotatedClass(PrecifiedSpecialty.class);
		configuration.addAnnotatedClass(MaterialInProcedure.class);
		configuration.addAnnotatedClass(MedicineInProcedure.class);
		configuration.addAnnotatedClass(Procedure.class);
		configuration.addAnnotatedClass(Specialty.class);
		configuration.addAnnotatedClass(Employee.class);
		configuration.addAnnotatedClass(Anamnese.class);
		configuration.addAnnotatedClass(Schedule.class);
		configuration.addAnnotatedClass(Message.class);
		configuration.addAnnotatedClass(Reply.class);
		configuration.addAnnotatedClass(AppointmentAssistant.class);
		configuration.addAnnotatedClass(Receipt.class);

		// TODO: Colocar c3p0
//		hibernate.c3p0.min_size=5
//		hibernate.c3p0.max_size=20
//		hibernate.c3p0.timeout=1800
//		hibernate.c3p0.max_statements=50

		return configuration;
	}
}
