package br.com.easyclinica.infra.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.services.pricing.PricingZerifier;

@Component
@RequestScoped
public class PricingZerifierHibernate implements PricingZerifier {

	private final Session session;

	public PricingZerifierHibernate(Session session) {
		this.session = session;
	}
	
	public void zeroPrices(HealthCarePlan healthCarePlan) {
		session.createSQLQuery(
				"insert into PrecifiedMaterial (amount, healthCarePlan_id, material_id) select 0, :to, id from Material")
				.setParameter("to", healthCarePlan.getId())
				.executeUpdate();
		
		session.createSQLQuery(
				"insert into PrecifiedMedicine (amount, healthCarePlan_id, medicine_id) select 0, :to, id from Medicine")
				.setParameter("to", healthCarePlan.getId())
				.executeUpdate();
		
		session.createSQLQuery(
				"insert into PrecifiedSpecialty (amount, healthCarePlan_id, specialty_id) select 0, :to, id from Specialty")
				.setParameter("to", healthCarePlan.getId())
				.executeUpdate();
		
		session.createSQLQuery(
				"insert into PrecifiedProcedure (roomTaxAmount, ch, fixedAmount, healthCarePlan_id, procedure_id) select 0, 0, 0, :to, id from medical_procedures")
				.setParameter("to", healthCarePlan.getId())
				.executeUpdate();
	}
}
