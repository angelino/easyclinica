package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.CID;
import br.com.easyclinica.domain.repositories.AllCIDs;

@Component
public class CIDDao implements AllCIDs{

	private final Session session;

	public CIDDao(Session session){
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	public List<CID> get() {
		return session.createCriteria(CID.class).addOrder(Order.asc("name")).list();
	}

	@SuppressWarnings("unchecked")
	public List<CID> search(String q) {
		return session.createCriteria(CID.class)
		.add(Restrictions
				.disjunction()
				.add(Restrictions.eq("code", q))
				.add(Restrictions.like("name", q, MatchMode.ANYWHERE)))
				.addOrder(Order.asc("name"))
		.list();
	}

}
