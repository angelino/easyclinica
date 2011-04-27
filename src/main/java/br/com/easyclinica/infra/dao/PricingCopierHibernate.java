package br.com.easyclinica.infra.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.services.pricing.PricingCopier;

@Component
public class PricingCopierHibernate implements PricingCopier {

	private final Session session;

	public PricingCopierHibernate(Session session) {
		this.session = session;
	}

	public void copyPrices(HealthCarePlan from, HealthCarePlan to) {
		copyMaterial(from, to);
		copyMedicine(from, to);
		copyProcedure(from, to);
		copySpecialty(from, to);
	}

	private void copyMaterial(HealthCarePlan from, HealthCarePlan to) {
		session.createSQLQuery(
				"insert into PrecifiedMaterial (amount, healthCarePlan_id, material_id) select c.amount, :to, material_id from PrecifiedMaterial c where c.healthCarePlan_id = :from")
				.setParameter("to", to.getId())
				.setParameter("from", from.getId()).executeUpdate();
	}

	private void copyMedicine(HealthCarePlan from, HealthCarePlan to) {
		session.createSQLQuery(
				"insert into PrecifiedMedicine (amount, healthCarePlan_id, medicine_id) select c.amount, :to, medicine_id from PrecifiedMedicine c where c.healthCarePlan_id = :from")
				.setParameter("to", to.getId())
				.setParameter("from", from.getId()).executeUpdate();
	}

	private void copySpecialty(HealthCarePlan from, HealthCarePlan to) {
		session.createSQLQuery(
				"insert into PrecifiedSpecialty (amount, healthCarePlan_id, specialty_id) select c.amount, :to, specialty_id from PrecifiedSpecialty c where c.healthCarePlan_id = :from")
				.setParameter("to", to.getId())
				.setParameter("from", from.getId()).executeUpdate();
	}

	private void copyProcedure(HealthCarePlan from, HealthCarePlan to) {
		session.createSQLQuery(
				"insert into PrecifiedProcedure (ch, fixedAmount, healthCarePlan_id, procedure_id) select c.ch, c.fixedamount, :to, procedure_id from PrecifiedProcedure c where c.healthCarePlan_id = :from")
				.setParameter("to", to.getId())
				.setParameter("from", from.getId()).executeUpdate();
	}


}
