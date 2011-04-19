package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

@Entity
public class MedicineInProcedure {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Medicine medicine;

	@ManyToOne(fetch = FetchType.LAZY)
	@Index(name = "MedicineInProcedureIndex")
	private Procedure procedure;

	private BigDecimal qty;

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Procedure getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
}
