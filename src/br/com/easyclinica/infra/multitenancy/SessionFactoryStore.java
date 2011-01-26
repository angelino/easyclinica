package br.com.easyclinica.infra.multitenancy;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class SessionFactoryStore {

	private Map<Tenant, SessionFactory> factories;
	
	public SessionFactoryStore() {
		factories = new HashMap<Tenant, SessionFactory>();
	}
	
	public SessionFactory get(Tenant t) {
		return factories.get(t);
	}
	
	public void add(Tenant t, SessionFactory factory) {
		factories.put(t, factory);
	}
}
