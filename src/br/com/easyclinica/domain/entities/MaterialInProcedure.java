package br.com.easyclinica.domain.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.easyclinica.domain.types.Quantity;

@Entity
public class MaterialInProcedure {
	
	@Id @GeneratedValue
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER) 
	private Material material;
	
	@ManyToOne(fetch=FetchType.LAZY) 
	private Procedure procedure;
	
	@Embedded private Quantity qty;

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

	public void setQty(Quantity qty) {
		this.qty = qty;
	}

	public Quantity getQty() {
		return qty;
	}
}
