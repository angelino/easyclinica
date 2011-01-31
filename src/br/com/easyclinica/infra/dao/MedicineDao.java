package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.dto.MedicineWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.HealthCarePlan;
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
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
			sql.append(" medicine.id as medicineId, ");
			sql.append(" medicine.name as medicineName, ");
			sql.append(" medicineinprocedure.qty as qty, ");
			sql.append(" precifiedmedicine.amount as amount ");
		sql.append(" FROM precifiedmedicine ");
		sql.append(" INNER JOIN medicineinprocedure ON medicineinprocedure.medicine_id = precifiedmedicine.medicine_id ");
		sql.append(" INNER JOIN medicine ON medicine.id = precifiedmedicine.medicine_id ");
		sql.append(" where precifiedmedicine.healthCarePlan_id = :healthCarePlanId ");
		sql.append(" and medicineinprocedure.procedure_id = :procedureId ");
		
		Query query = session.createSQLQuery(sql.toString())
						.addScalar("medicineId")
						.addScalar("medicineName")
						.addScalar("qty")
						.addScalar("amount")
						.setResultTransformer(Transformers.aliasToBean(MedicineWithPriceAndQuantity.class))
						.setParameter("healthCarePlanId", healthCarePlan.getId())
						.setParameter("procedureId", procedure.getId());
		return (List<MedicineWithPriceAndQuantity>) query.list();
	}

}
