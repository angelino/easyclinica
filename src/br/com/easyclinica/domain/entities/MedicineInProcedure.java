package br.com.easyclinica.domain.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.easyclinica.domain.types.Quantity;

@Entity
public class MedicineInProcedure {
	
	@Id @GeneratedValue
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Medicine medicine;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Procedure procedure;
	
	private Quantity qty;
	
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

	public Quantity getQty() {
		return qty;
	}

	public void setQty(Quantity qty) {
		this.qty = qty;
	}
}
