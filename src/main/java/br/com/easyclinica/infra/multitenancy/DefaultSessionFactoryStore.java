package br.com.easyclinica.infra.multitenancy;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class DefaultSessionFactoryStore implements SessionFactoryStore {

	private Map<String, SessionFactory> factories;
	
	public DefaultSessionFactoryStore() {
		factories = new HashMap<String, SessionFactory>();
	}
	
	public SessionFactory get(String tenant) {
		return factories.get(tenant);
	}
	
	public void add(String tenant, SessionFactory factory) {
		factories.put(tenant, factory);
	}
	
	public boolean contains(String tenant) {
		return factories.containsKey(tenant);
	}
}
