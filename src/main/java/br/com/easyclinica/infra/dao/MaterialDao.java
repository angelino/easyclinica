package br.com.easyclinica.infra.dao;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.MaterialInProcedure;
import br.com.easyclinica.domain.entities.MaterialWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.repositories.AllMaterials;

@Component
public class MaterialDao implements AllMaterials {

	private final Session session;

	public MaterialDao(Session session) {
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	public List<MaterialWithPriceAndQuantity> getMaterialsWithPriceAndQuantity(
			Procedure procedure, HealthCarePlan healthCarePlan) {
		
		StringBuilder hql = new StringBuilder();
		hql.append(" select new br.com.easyclinica.domain.entities.MaterialWithPriceAndQuantity( ");
			hql.append(" m.material, ");
			hql.append(" m.qty.qty, ");
			hql.append(" pm.amount.amount ");
		hql.append(" ) ");
		hql.append(" from PrecifiedMaterial as pm, MaterialInProcedure as m ");
		hql.append(" where ");
		hql.append(" pm.material.id = m.material.id ");
		hql.append(" and pm.healthCarePlan.id = :healthCarePlanId ");
		hql.append(" and m.procedure.id = :procedureId ");
		
		List<MaterialWithPriceAndQuantity> materials = (List<MaterialWithPriceAndQuantity>)session.createQuery(hql.toString())
							 .setParameter("healthCarePlanId", healthCarePlan.getId())
							 .setParameter("procedureId", procedure.getId())
							 .list();
		
		List<MaterialWithPriceAndQuantity> result = new LinkedList<MaterialWithPriceAndQuantity>();
		if(procedure.getMaterials().size() == materials.size()) result = materials;
		else 
		{
			for(MaterialInProcedure materialInProcedure : procedure.getMaterials())
			{
				boolean found = false;
				for(MaterialWithPriceAndQuantity materialWithPrice : materials)
				{
					if(materialWithPrice.getMaterial().getId() == materialInProcedure.getMaterial().getId())
					{
						result.add(materialWithPrice);
						found = true;
						break;
					}
				}
				
				if(!found)
				{
					MaterialWithPriceAndQuantity materialWithPrice = new MaterialWithPriceAndQuantity(materialInProcedure.getMaterial(), BigDecimal.ZERO, BigDecimal.ZERO);
					result.add(materialWithPrice);
				}
			}
		}
		
		return  result;
	}

	@SuppressWarnings("unchecked")
	public List<Material> getAll() {
		return session.createCriteria(Material.class).addOrder(Order.asc("name")).list();
	}

	public Material getById(int id) {
		return (Material) session.load(Material.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Material> search(String text) {
		return session.createCriteria(Material.class).add(Restrictions.like("name", text, MatchMode.ANYWHERE)													   )
													 .addOrder(Order.asc("name"))
													 .list();
	}

}
