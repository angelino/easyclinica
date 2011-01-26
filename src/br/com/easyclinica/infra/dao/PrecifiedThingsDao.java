package br.com.easyclinica.infra.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.repositories.PrecifiedThings;

@Component
public class PrecifiedThingsDao implements PrecifiedThings {

	private final Session session;

	public PrecifiedThingsDao(Session session) {
		this.session = session;
	}
	
	public PrecifiedProcedure getPrice(Procedure procedure, HealthCarePlan healthCarePlan) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from PrecifiedProcedure p ");
		sql.append(" inner join fetch HealthCarePlan h ");
		sql.append(" inner join fetch Procedure proc ");
		sql.append(" where h.id = :healthCarePlanId ");
		sql.append(" and proc.id = :procedureId ");
		
		Query query = session.createQuery(sql.toString())
						.setParameter("healthCarePlan", healthCarePlan.getId())
						.setParameter("procedureId", procedure.getId());
		return (PrecifiedProcedure) query.uniqueResult();
	}

}
