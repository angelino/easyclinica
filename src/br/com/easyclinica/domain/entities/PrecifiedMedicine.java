package br.com.easyclinica.domain.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PrecifiedMedicine {

	@Id @GeneratedValue
	private int id;
	@ManyToOne(fetch=FetchType.LAZY) 
	private HealthCarePlan healthCarePlan;
	@ManyToOne(fetch=FetchType.LAZY)
	private Medicine medicine;
	private double amount;
	
	public HealthCarePlan getHealthCarePlan() {
		return healthCarePlan;
	}
	public void setHealthCarePlan(HealthCarePlan healthCarePlan) {
		this.healthCarePlan = healthCarePlan;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public Medicine getMedicine() {
		return medicine;
	}
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	protected void setId(int id) {
		this.id = id;
	}
	
	
}
