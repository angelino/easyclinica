package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

@Entity
public class PrecifiedProcedure {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	@Index(name = "PrecifiedProcedurePlanIndex")
	private HealthCarePlan healthCarePlan;
	@ManyToOne(fetch = FetchType.EAGER)
	private Procedure procedure;
	private BigDecimal fixedAmount;
	private BigDecimal roomTaxAmount;
	private int ch;

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

	public void setFixedAmount(BigDecimal fixedAmount) {
		this.fixedAmount = fixedAmount;
	}

	public BigDecimal getFixedAmount() {
		return fixedAmount;
	}

	public int getCh() {
		return ch;
	}

	public void setCh(int ch) {
		this.ch = ch;
	}

	public BigDecimal getRoomTaxAmount() {
		return roomTaxAmount;
	}

	public void setRoomTaxAmount(BigDecimal roomTaxAmount) {
		this.roomTaxAmount = roomTaxAmount;
	}

}
