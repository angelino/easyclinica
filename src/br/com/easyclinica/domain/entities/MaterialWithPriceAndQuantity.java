package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;

import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.domain.types.Quantity;


public class MaterialWithPriceAndQuantity {
	
	private Material material;
	private Quantity qty;
	private Money amount;
	
	public MaterialWithPriceAndQuantity() { }
	public MaterialWithPriceAndQuantity(Material material, BigDecimal qty, BigDecimal amount) {
		this.material = material;
		this.qty = new Quantity(qty);
		this.amount = new Money(amount);
	}
	
	public void setQty(Quantity qty) {
		this.qty = qty;
	}
	public Quantity getQty() {
		return qty;
	}
	
	public void setAmount(Money amount) {
		this.amount = amount;
	}
	public Money getAmount() {
		return amount;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public Material getMaterial() {
		return material;
	}
}
