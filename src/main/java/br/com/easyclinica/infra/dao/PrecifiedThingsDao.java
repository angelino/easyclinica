package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedMaterial;
import br.com.easyclinica.domain.entities.PrecifiedMedicine;
import br.com.easyclinica.domain.entities.PrecifiedProcedure;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.entities.Specialty;
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
		sql.append(" where p.healthCarePlan.id = :healthCarePlanId ");
		sql.append(" and p.procedure.id = :procedureId ");
		
		Query query = session.createQuery(sql.toString())
						.setParameter("healthCarePlanId", healthCarePlan.getId())
						.setParameter("procedureId", procedure.getId());
		return (PrecifiedProcedure) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<PrecifiedMaterial> getMaterialsPrice(Procedure procedure, HealthCarePlan healthCarePlan) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from PrecifiedMaterial pm ");
		sql.append(" where pm.healthCarePlan.id = :healthCarePlanId ");
		sql.append(" and pm.material in ( ");
			sql.append(" select m from Procedure p ");
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
			sql.append(" select m from Procedure p ");
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