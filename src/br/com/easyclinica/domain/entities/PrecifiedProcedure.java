package br.com.easyclinica.domain.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.easyclinica.domain.types.Money;

@Entity
public class PrecifiedProcedure {
	@Id @GeneratedValue
	private int id;
	@ManyToOne(fetch=FetchType.LAZY)
	private HealthCarePlan healthCarePlan;
	@ManyToOne(fetch=FetchType.LAZY) 
	private Procedure procedure;
	@Embedded private Money fixedAmount;
	
	public HealthCarePlan getHealthCarePlan() {
		return healthCarePlan;
	}
	public void setHealthCarePlan(HealthCarePlan healthCarePlan) {
		this.healthCarePlan = healthCarePlan;
	}
	public Procedure getProcedure() {
		return procedure;
	}
	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}
	
	public int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	public void setFixedAmount(Money fixedAmount) {
		this.fixedAmount = fixedAmount;
	}
	public Money getFixedAmount() {
		return fixedAmount;
	}
	
	
	
}
