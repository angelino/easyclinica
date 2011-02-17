package br.com.easyclinica.infra.multitenancy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.ioc.RequestScoped;
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
import br.com.easyclinica.domain.entities.Patient;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.infra.database.DatabaseInfo;

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
		Configuration configuration = DatabaseInfo.config(tenant);
				
		return configuration.buildSessionFactory();
	}

}
