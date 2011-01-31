package br.com.easyclinica.infra.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.dto.MaterialWithPriceAndQuantity;
import br.com.easyclinica.domain.entities.HealthCarePlan;
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
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
			sql.append(" material.id as materialId, ");
			sql.append(" material.name as materialName, ");
			sql.append(" materialinprocedure.qty as qty, ");
			sql.append(" precifiedmaterial.amount as amount ");
		sql.append(" FROM precifiedmaterial ");
		sql.append(" INNER JOIN materialinprocedure ON materialinprocedure.material_id = precifiedmaterial.material_id ");
		sql.append(" INNER JOIN material ON material.id = precifiedmaterial.material_id ");
		sql.append(" where precifiedmaterial.healthCarePlan_id = :healthCarePlanId ");
		sql.append(" and materialinprocedure.procedure_id = :procedureId ");
		
		Query query = session.createSQLQuery(sql.toString())
						.addScalar("materialId")
						.addScalar("materialName")
						.addScalar("qty")
						.addScalar("amount")
						.setResultTransformer(Transformers.aliasToBean(MaterialWithPriceAndQuantity.class))
						.setParameter("healthCarePlanId", healthCarePlan.getId())
						.setParameter("procedureId", procedure.getId());
		return (List<MaterialWithPriceAndQuantity>) query.list();
	}

}
