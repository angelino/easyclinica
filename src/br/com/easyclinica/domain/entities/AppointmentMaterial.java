package br.com.easyclinica.domain.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AppointmentMaterial {

	@Id @GeneratedValue
	private int id;
	private double unitAmount;
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="appointmentProcedure_fk") 
	private AppointmentProcedure procedure;
	private double totalAmount;
	private float qty;
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="material_fk")
	private Material material;
	
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
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	
}
