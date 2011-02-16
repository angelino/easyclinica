package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.domain.types.Quantity;

@Entity
public class AppointmentMaterial {

	@Id @GeneratedValue
	private int id;
	@Embedded private Money unitAmount;
	@ManyToOne(fetch=FetchType.LAZY) 
	private AppointmentProcedure procedure;
	@Embedded private Quantity qty;
	@ManyToOne(fetch=FetchType.EAGER)
	private Material material;
	
	public int getId() {
		return id;
	}
	public Money getUnitAmount() {
		return unitAmount;
	}
	public void setUnitAmount(Money unitAmount) {
		this.unitAmount = unitAmount;
	}
	public AppointmentProcedure getProcedure() {
		return procedure;
	}
	public void setProcedure(AppointmentProcedure procedure) {
		this.procedure = procedure;
	}
	public Money getTotalAmount() {
		BigDecimal total = unitAmount.getAmount().multiply(qty.getQty());
		
		return new Money(total);
	}
	public Quantity getQty() {
		return qty;
	}
	public void setQty(Quantity qty) {
		this.qty = qty;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	protected void setId(int id) {
		this.id = id;
	}
	
	
}
