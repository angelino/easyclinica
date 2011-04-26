package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.MedicineWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.repositories.AllMedicines;

@Component
public class MedicineDao implements AllMedicines {

	private final Session session;

	public MedicineDao(Session session) {
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	public List<MedicineWithPriceAndQuantity> getMedicinesWithPriceAndQuantity(
			Procedure procedure, HealthCarePlan healthCarePlan) {
		
		StringBuilder hql = new StringBuilder();
		hql.append(" select new br.com.easyclinica.domain.entities.MedicineWithPriceAndQuantity( ");
			hql.append(" m.medicine, ");
			hql.append(" m.qty.qty, ");
			hql.append(" pm.amount.amount ");
		hql.append(" ) ");
		hql.append(" from PrecifiedMedicine as pm, MedicineInProcedure as m ");
		hql.append(" where ");
		hql.append(" pm.medicine.id = m.medicine.id ");
		hql.append(" and pm.healthCarePlan.id = :healthCarePlanId ");
		hql.append(" and m.procedure.id = :procedureId ");
		
		Query query = session.createQuery(hql.toString())
							 .setParameter("healthCarePlanId", healthCarePlan.getId())
							 .setParameter("procedureId", procedure.getId());
		
		return (List<MedicineWithPriceAndQuantity>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Medicine> getAll() {
		return session.createCriteria(Medicine.class).addOrder(Order.asc("name")).list();
	}

	public Medicine getById(int id) {
		return (Medicine) session.load(Medicine.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Medicine> search(String text) {
		return session.createCriteria(Medicine.class).add(Restrictions.or(
													Restrictions.like("name", text, MatchMode.ANYWHERE), 
													Restrictions.like("code", text, MatchMode.ANYWHERE)
												  )
											   )
											  .addOrder(Order.asc("name"))
											  .list();
	}

}
