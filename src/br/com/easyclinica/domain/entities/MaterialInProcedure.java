package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MaterialInProcedure {
	
	@Id @GeneratedValue
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER) 
	private Material material;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	private Procedure procedure;
	
	private BigDecimal qty;

	protected void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Material getMaterial() {
		return material;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}

	public Procedure getProcedure() {
		return procedure;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getQty() {
		return qty;
	}
}
