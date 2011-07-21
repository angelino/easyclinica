package br.com.easyclinica.infra.database;

import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.config.Config;
import br.com.easyclinica.domain.entities.Anamnese;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.AppointmentAssistant;
import br.com.easyclinica.domain.entities.AppointmentMaterial;
import br.com.easyclinica.domain.entities.AppointmentMedicine;
import br.com.easyclinica.domain.entities.AppointmentProcedure;
import br.com.easyclinica.domain.entities.CID;
import br.com.easyclinica.domain.entities.ChatMessage;
import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.GeneralObservations;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.MaterialInProcedure;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.MedicineInProcedure;
import br.com.easyclinica.domain.entities.Message;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.Prescription;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.Receipt;
import br.com.easyclinica.domain.entities.Reply;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.domain.entities.Specialty;

@Component
@ApplicationScoped
public class DatabaseConfigurator {
	
	private final Config cfg;

	public DatabaseConfigurator(Config cfg) {
		this.cfg = cfg;
	}

	public Configuration config(String database) {
		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.connection.driver_class", cfg.get("driver_class"));
		configuration.setProperty("hibernate.connection.url", cfg.get("connection_string").replace("#database#", nameOf(database)));
		configuration.setProperty("hibernate.dialect", cfg.get("dialect"));
		configuration.setProperty("hibernate.connection.username", nameOf(database));
		configuration.setProperty("hibernate.connection.password", cfg.get("db_pwd"));
		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.format_sql", "true");
		configuration.setProperty("hibernate.jdbc.batch_size", "20");
		
		if(cfg.get("env").equals("production")){
			configuration.setProperty("hibernate.c3p0.acquire_increment", "5");
			configuration.setProperty("hibernate.c3p0.idle_test_period", "100");
			configuration.setProperty("hibernate.c3p0.max_size", "20");
			configuration.setProperty("hibernate.c3p0.max_statements", "0");
			configuration.setProperty("hibernate.c3p0.min_size", "3");
			configuration.setProperty("hibernate.c3p0.timeout", "1800");
			
		}

		configuration.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.EhCacheProvider");
		configuration.setProperty("hibernate.cache.use_second_level_cache", "true");
		configuration.setProperty("hibernate.cache.use_query_cache", "true");
		configuration.setProperty("hibernate.cache.provider_configuration_file_resource_path", "/ehcache.xml");
		
		if(cfg.get("env").equals("dev")){
			configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		}
		
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
		configuration.addAnnotatedClass(ChatMessage.class);
		configuration.addAnnotatedClass(GeneralObservations.class);
		configuration.addAnnotatedClass(CID.class);
		configuration.addAnnotatedClass(Prescription.class);

		return configuration;
	}

	private String nameOf(String database) {
		String prefix = cfg.get("db_prefix");
		return (prefix==null?"":prefix) + database;
	}
}
