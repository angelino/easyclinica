package br.com.easyclinica.domain.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.easyclinica.domain.types.Money;

@Entity
public class PrecifiedMaterial {

	@Id @GeneratedValue
	private int id;
	@ManyToOne(fetch=FetchType.LAZY) 
	private HealthCarePlan healthCarePlan;
	@ManyToOne(fetch=FetchType.LAZY) 
	private Material material;
	@Embedded private Money amount;
	
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
	public void setAmount(Money amount) {
		this.amount = amount;
	}
	public Money getAmount() {
		return amount;
	}
	
	
}
