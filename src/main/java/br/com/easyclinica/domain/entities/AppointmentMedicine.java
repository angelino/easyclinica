package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

@Entity
public class AppointmentMedicine {

	@Id
	@GeneratedValue
	private int id;
	private BigDecimal unitAmount;
	@ManyToOne(fetch = FetchType.LAZY)
	@Index(name = "appMedicineProcedureIndex")
	private AppointmentProcedure procedure;
	private BigDecimal qty;
	@ManyToOne(fetch = FetchType.EAGER)
	private Medicine medicine;

	public AppointmentMedicine() {
		this.unitAmount = BigDecimal.ZERO;
		this.qty = BigDecimal.ZERO;
	}
	
	public int getId() {
		return id;
	}

	public BigDecimal getUnitAmount() {
		return unitAmount;
	}

	public void setUnitAmount(BigDecimal unitAmount) {
		this.unitAmount = unitAmount;
	}

	public AppointmentProcedure getProcedure() {
		return procedure;
	}

	public void setProcedure(AppointmentProcedure procedure) {
		this.procedure = procedure;
	}

	public BigDecimal getTotalAmount() {
		return unitAmount.multiply(qty);
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
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
