package br.com.easyclinica.infra.multitenancy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.easyclinica.domain.entities.Appointment;
import br.com.easyclinica.domain.entities.AppointmentMaterial;
import br.com.easyclinica.domain.entities.AppointmentMedicine;
import br.com.easyclinica.domain.entities.AppointmentProcedure;
import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Doctor;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.Specialty;

@Component
@RequestScoped
public class SessionFactoryCreator implements ComponentFactory<SessionFactory> {

	private final SessionFactoryStore store;
	private final Tenant tenant;

	public SessionFactoryCreator(Tenant tenant, SessionFactoryStore store) {
		this.tenant = tenant;
		this.store = store;
	}
	
	public SessionFactory getInstance() {
		if(!store.contains(tenant.getDomain())) {
			SessionFactory sf = createSessionFactoryForTenant(tenant.getDomain());
			store.add(tenant.getDomain(), sf);		
		}
		
		return store.get(tenant.getDomain());
	}
	
	private SessionFactory createSessionFactoryForTenant(String tenant) {
		// TODO: pegar url do banco de algum arquivo de config
		Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/" + tenant);
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		configuration.setProperty("hibernate.connection.username", "easyclinica");
		configuration.setProperty("hibernate.connection.password", "3@sycl1n1c@");
		configuration.setProperty("hibernate.hbm2ddl.auto", "true");
		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.format_sql", "true");
		
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
		configuration.addAnnotatedClass(Procedure.class);
		configuration.addAnnotatedClass(Specialty.class);
		configuration.addAnnotatedClass(Employee.class);
		
		// TODO: Colocar c3p0
//			hibernate.c3p0.min_size=5
//			hibernate.c3p0.max_size=20
//			hibernate.c3p0.timeout=1800
//			hibernate.c3p0.max_statements=50
		
		return configuration.buildSessionFactory();
	}

}
