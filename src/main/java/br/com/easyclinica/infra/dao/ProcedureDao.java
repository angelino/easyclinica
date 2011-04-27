package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.repositories.AllProcedures;

@Component
public class ProcedureDao implements AllProcedures {
	private final Session session;

	public ProcedureDao(Session session) {
		this.session = session;
	}

	public Procedure getById(int id) {
		return (Procedure) session.load(Procedure.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Procedure> search(String text) {
		return session.createCriteria(Procedure.class)
				.add(Restrictions
						.disjunction()
						.add(Restrictions.eq("ambCode", text))
						.add(Restrictions.eq("tussCode", text))
						.add(Restrictions.eq("lpmCode", text))
						.add(Restrictions.eq("cbhpmCode", text))
						.add(Restrictions.like("name", text, MatchMode.ANYWHERE)))
						.addOrder(Order.asc("name"))
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Procedure> getAll() {
		return session.createCriteria(Procedure.class)
				.addOrder(Order.asc("name")).list();
	}

}
