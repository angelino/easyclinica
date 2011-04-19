package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

@Entity
public class PrecifiedMedicine {

	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	@Index(name = "PrecifiedMedicinePlanIndex")
	private HealthCarePlan healthCarePlan;
	@ManyToOne(fetch = FetchType.EAGER)
	private Medicine medicine;
	private BigDecimal amount;

	public HealthCarePlan getHealthCarePlan() {
		return healthCarePlan;
	}

	public void setHealthCarePlan(HealthCarePlan healthCarePlan) {
		this.healthCarePlan = healthCarePlan;
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

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

}
