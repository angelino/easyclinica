package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

@Entity
public class AppointmentMaterial {

	@Id
	@GeneratedValue
	private int id;
	private BigDecimal unitAmount;
	@ManyToOne(fetch = FetchType.LAZY)
	@Index(name = "appMaterialProcedureIndex")
	private AppointmentProcedure procedure;
	private BigDecimal qty;
	@ManyToOne(fetch = FetchType.EAGER)
	private Material material;

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
