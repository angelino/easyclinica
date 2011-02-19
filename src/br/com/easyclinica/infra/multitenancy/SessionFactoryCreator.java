package br.com.easyclinica.infra.multitenancy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.easyclinica.infra.database.DatabaseConfigurator;

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
		Configuration configuration = DatabaseConfigurator.config(tenant);
				
		return configuration.buildSessionFactory();
	}

}
