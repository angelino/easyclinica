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
	private final DatabaseConfigurator configurator;

	public SessionFactoryCreator(Tenant tenant, SessionFactoryStore store, DatabaseConfigurator configurator) {
		this.tenant = tenant;
		this.store = store;
		this.configurator = configurator;
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
		Configuration configuration = configurator.config(tenant);
				
		return configuration.buildSessionFactory();
	}

}
