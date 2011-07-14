package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.domain.entities.pricing.PricedProcedure;
import br.com.easyclinica.domain.entities.pricing.PricedStuff;
import br.com.easyclinica.domain.repositories.PrecifiedThings;

@Component
public class PrecifiedThingsDao implements PrecifiedThings {

	private final Session session;

	public PrecifiedThingsDao(Session session) {
		this.session = session;
	}
	
	public PrecifiedProcedure getPrice(Procedure procedure, HealthCarePlan healthCarePlan) {		
		PrecifiedProcedure price = (PrecifiedProcedure)session.createCriteria(PrecifiedProcedure.class)
										  .add(Restrictions.eq("healthCarePlan.id", healthCarePlan.getId()))
										  .add(Restrictions.eq("procedure.id", procedure.getId()))
										  .uniqueResult();
		
		return price == null ? PrecifiedProcedure.empty() : price;
	}
	
	@SuppressWarnings("unchecked")
	public List<PricedStuff> getMaterialsPrice(HealthCarePlan plan) {
		StringBuilder sql = new StringBuilder();
		sql.append("select new br.com.easyclinica.domain.entities.pricing.PricedStuff(m.id, m.name, pm.amount) from PrecifiedMaterial pm right outer join pm.material m left join pm.healthCarePlan plan with plan.id = :healthCarePlanId");
	
		Query query = session.createQuery(sql.toString())
						.setParameter("healthCarePlanId", plan.getId());
		return (List<PricedStuff>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<PricedStuff> getMedicinesPrice(HealthCarePlan plan) {
		StringBuilder sql = new StringBuilder();
		sql.append("select new br.com.easyclinica.domain.entities.pricing.PricedStuff(m.id, m.name, pm.amount) from PrecifiedMedicine pm right outer join pm.medicine m left join pm.healthCarePlan plan with plan.id = :healthCarePlanId");
	
		Query query = session.createQuery(sql.toString())
						.setParameter("healthCarePlanId", plan.getId());
		return (List<PricedStuff>) query.list();
	}

	@SuppressWarnings("unchecked")
	public List<PricedStuff> getSpecialtiesPrice(HealthCarePlan plan) {
		StringBuilder sql = new StringBuilder();
		sql.append("select new br.com.easyclinica.domain.entities.pricing.PricedStuff(m.id, m.name, pm.amount) from PrecifiedSpecialty pm right outer join pm.specialty m left join pm.healthCarePlan plan with plan.id = :healthCarePlanId");
	
		Query query = session.createQuery(sql.toString())
						.setParameter("healthCarePlanId", plan.getId());
		return (List<PricedStuff>) query.list();
	}


	@SuppressWarnings("unchecked")
	public List<PricedProcedure> getProceduresPrice(HealthCarePlan plan) {
		StringBuilder sql = new StringBuilder();
		sql.append("select new br.com.easyclinica.domain.entities.pricing.PricedProcedure(m.id, m.name, pm.fixedAmount, m.ambCode, pm.ch, pm.roomTaxAmount) from PrecifiedProcedure pm right outer join pm.procedure m left join pm.healthCarePlan plan with plan.id = :healthCarePlanId");
	
		Query query = session.createQuery(sql.toString())
						.setParameter("healthCarePlanId", plan.getId());
		return (List<PricedProcedure>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PrecifiedMaterial> getMaterialsPrice(Procedure procedure, HealthCarePlan healthCarePlan) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from PrecifiedMaterial pm ");
		sql.append(" where pm.healthCarePlan.id = :healthCarePlanId ");
		sql.append(" and pm.material in ( ");
			sql.append(" select m.material from Procedure p ");
			sql.append(" join p.materials m ");
			sql.append(" where p.id = :procedureId ");
		sql.append(" ) ");
		
		Query query = session.createQuery(sql.toString())
						.setParameter("healthCarePlanId", healthCarePlan.getId())
						.setParameter("procedureId", procedure.getId());
		return (List<PrecifiedMaterial>) query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PrecifiedMedicine> getMedicinePrice(Procedure procedure, HealthCarePlan healthCarePlan) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from PrecifiedMedicine pm ");
		sql.append(" where pm.healthCarePlan.id = :healthCarePlanId ");
		sql.append(" and pm.medicine in ( ");
			sql.append(" select m.medicine from Procedure p ");
			sql.append(" join p.medicine m ");
			sql.append(" where p.id = :procedureId ");
		sql.append(" ) ");
		
		Query query = session.createQuery(sql.toString())
						.setParameter("healthCarePlanId", healthCarePlan.getId())
						.setParameter("procedureId", procedure.getId());
		return (List<PrecifiedMedicine>) query.list();
	}
	
	public PrecifiedSpecialty getMedicalAppointmentPrice(Specialty specialty, HealthCarePlan healthCarePlan) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" from PrecifiedSpecialty ps ");
		sql.append(" where ps.healthCarePlan.id = :healthCarePlanId ");
		sql.append(" and ps.specialty.id = :specialtyId ");
		
		Query query = session.createQuery(sql.toString())
						.setParameter("healthCarePlanId", healthCarePlan.getId())
						.setParameter("specialtyId", specialty.getId());
		return (PrecifiedSpecialty) query.uniqueResult();
	}
}
