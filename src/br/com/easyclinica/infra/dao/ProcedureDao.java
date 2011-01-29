package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

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
		return (Procedure)session.load(Procedure.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Procedure> search(String text) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from Procedure procedure ");
		hql.append(" where ");
		hql.append(" procedure.ambCode = :ambCode ");
		hql.append(" or procedure.tussCode = :tussCode ");
		hql.append(" or procedure.name like :name ");
		hql.append(" order by name ");
		
		Query query = session.createQuery(hql.toString())
							 .setString("ambCode", text)
							 .setString("tussCode", text)
							 .setString("name", "%" + text + "%");
				
		return query.list();
	}

}
