package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

@Entity
public class PrecifiedSpecialty {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Specialty specialty;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Index(name = "PrecifiedSpecialtyPlanIndex")
	private HealthCarePlan healthCarePlan;

	private BigDecimal amount;

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public HealthCarePlan getHealthCarePlan() {
		return healthCarePlan;
	}

	public void setHealthCarePlan(HealthCarePlan healthCarePlan) {
		this.healthCarePlan = healthCarePlan;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
