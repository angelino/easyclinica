package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;

public class MaterialWithPriceAndQuantity {

	private Material material;
	private BigDecimal qty;
	private BigDecimal amount;

	public MaterialWithPriceAndQuantity() {
	}

	public MaterialWithPriceAndQuantity(Material material, BigDecimal qty,
			BigDecimal amount) {
		this.material = material;
		this.qty = qty;
		this.amount = amount;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Material getMaterial() {
		return material;
	}
}
