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
import br.com.easyclinica.domain.repositories.AllHealthCarePlans;

@Component
public class HealthCarePlanDao implements AllHealthCarePlans {

	private final Session session;

	public HealthCarePlanDao(Session session) {
		this.session = session;
	}
	
	public void add(HealthCarePlan healthCare) {
		session.save(healthCare);
	}
	
	@SuppressWarnings("unchecked")
	public List<HealthCarePlan> get() {
		return (List<HealthCarePlan>)session.createQuery("from HealthCarePlan hc order by name").list();
	}

	public HealthCarePlan getById(int id) {
		return (HealthCarePlan)session.load(HealthCarePlan.class, id);
	}

	public void update(HealthCarePlan plan) {
		session.merge(plan);
	}

	@SuppressWarnings("unchecked")
	public List<HealthCarePlan> get(int firstResult, int maxResults) {
		Query query = session.createQuery("from HealthCarePlan hc order by name");
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		
		return query.list();
	}

	public int count() {
		Query query = session.createQuery("select count(*) from HealthCarePlan");
		return ((Long) query.uniqueResult()).intValue();
	}

	public void updatePrices(HealthCarePlan plan) {
		int count=0;
		
		for(PrecifiedMaterial material : plan.getPrecifiedMaterials()){
			session.saveOrUpdate(material);
			if(count++ % 20 == 0) flushIt();
		}
		for(PrecifiedMedicine medicine : plan.getPrecifiedMedicines()){
			session.saveOrUpdate(medicine);
			if(count++ % 20 == 0) flushIt();
		}
		for(PrecifiedSpecialty specialty : plan.getPrecifiedSpecialties()){
			session.saveOrUpdate(specialty);
			if(count++ % 20 == 0) flushIt();
		}	
		for(PrecifiedProcedure procedure : plan.getPrecifiedProcedures()){
			session.saveOrUpdate(procedure);
			if(count++ % 20 == 0) flushIt();
		}	
	}

	private void flushIt() {
		session.flush();
		session.clear();
	}

	@SuppressWarnings("unchecked")
	public List<HealthCarePlan> search(String textToSearch, int firstResult,
			int maxResults) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from HealthCarePlan plan ");
		hql.append(" where ");
		hql.append(" plan.name like :text_like ");
		hql.append(" order by name ");
		
		Query query = session.createQuery(hql.toString())
							 .setString("text_like", "%" + textToSearch + "%");
		
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		
		return query.list();
	}

	public int count(String textToSearch) {
		StringBuilder hql = new StringBuilder();
		hql.append(" select count(*) from HealthCarePlan plan ");
		hql.append(" where ");
		hql.append(" plan.name like :text_like ");
		
		Query query = session.createQuery(hql.toString())
							 .setString("text_like", "%" + textToSearch + "%");
		
		return ((Long) query.uniqueResult()).intValue();
	}
}
