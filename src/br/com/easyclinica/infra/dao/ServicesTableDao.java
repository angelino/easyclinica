package br.com.easyclinica.infra.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.ServicesTable;
import br.com.easyclinica.domain.repositories.AllServiceTables;

@Component
public class ServicesTableDao implements AllServiceTables {

	private final EntityManager em;

	public ServicesTableDao(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<ServicesTable> get() {
		return (List<ServicesTable>)em.createQuery("from ServicesTable st order by name").getResultList();
	}

}
