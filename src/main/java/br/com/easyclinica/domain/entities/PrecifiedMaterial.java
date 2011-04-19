package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

@Entity
public class PrecifiedMaterial {

	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	@Index(name = "PrecifiedMaterialPlanIndex")
	private HealthCarePlan healthCarePlan;
	@ManyToOne(fetch = FetchType.EAGER)
	private Material material;
	private BigDecimal amount;

	public PrecifiedMaterial() {
	}

	public PrecifiedMaterial(int id) {
		this.id = id;
	}

	public HealthCarePlan getHealthCarePlan() {
		return healthCarePlan;
	}

	public void setHealthCarePlan(HealthCarePlan healthCarePlan) {
		this.healthCarePlan = healthCarePlan;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

}
