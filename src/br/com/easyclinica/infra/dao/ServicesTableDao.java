package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.ServicesTable;
import br.com.easyclinica.domain.repositories.AllServiceTables;

@Component
public class ServicesTableDao implements AllServiceTables {
	private final Session session;

	public ServicesTableDao(Session session) {
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	public List<ServicesTable> get() {
		return (List<ServicesTable>)session.createQuery("from ServicesTable st order by name").list();
	}

}
