package br.com.easyclinica.infra.multitenancy;

import org.hibernate.SessionFactory;

public interface SessionFactoryStore {
	SessionFactory get(String tenant);
	void add(String tenant, SessionFactory factory);
	boolean contains(String tenant);
}