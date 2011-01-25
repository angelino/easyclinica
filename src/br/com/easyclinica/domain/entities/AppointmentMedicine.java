package br.com.easyclinica.domain.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AppointmentMedicine {

	@Id @GeneratedValue
	private int id;
	private double unitAmount;
	@ManyToOne(fetch=FetchType.LAZY) 
	private AppointmentProcedure procedure;
	private double totalAmount;
	private float qty;
	@ManyToOne(fetch=FetchType.LAZY) 
	private Medicine medicine;
	
	public int getId() {
		return id;
	}
	public double getUnitAmount() {
		return unitAmount;
	}
	public void setUnitAmount(double unitAmount) {
		this.unitAmount = unitAmount;
	}
	public AppointmentProcedure getProcedure() {
		return procedure;
	}
	public void setProcedure(AppointmentProcedure procedure) {
		this.procedure = procedure;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	public Medicine getMedicine() {
		return medicine;
	}
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	
	
}
