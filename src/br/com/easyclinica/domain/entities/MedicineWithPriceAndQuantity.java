package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;

import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.domain.types.Quantity;

public class MedicineWithPriceAndQuantity {

	private Medicine medicine;
	private Quantity qty;
	private Money amount;
	
	public MedicineWithPriceAndQuantity(){}
	public MedicineWithPriceAndQuantity(Medicine medicine, BigDecimal qty, BigDecimal amount) {
		this.medicine = medicine;
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
	
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	public Medicine getMedicine() {
		return medicine;
	}
}
