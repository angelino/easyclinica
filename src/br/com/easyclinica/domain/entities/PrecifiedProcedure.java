package br.com.easyclinica.domain.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PrecifiedProcedure {
	@Id @GeneratedValue
	private int id;
	@ManyToOne(fetch=FetchType.LAZY)
	private HealthCarePlan healthCarePlan;
	@ManyToOne(fetch=FetchType.LAZY) 
	private Procedure procedure;
	private double fixedAmount;
	
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
	public double getFixedAmount() {
		return fixedAmount;
	}
	public void setFixedAmount(double fixedAmount) {
		this.fixedAmount = fixedAmount;
	}
	public int getId() {
		return id;
	}
	
	
	
}
