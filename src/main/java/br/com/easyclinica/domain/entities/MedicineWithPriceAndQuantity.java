package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;

public class MedicineWithPriceAndQuantity {

	private Medicine medicine;
	private BigDecimal qty;
	private BigDecimal amount;

	public MedicineWithPriceAndQuantity() {
	}

	public MedicineWithPriceAndQuantity(Medicine medicine, BigDecimal qty,
			BigDecimal amount) {
		this.medicine = medicine;
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

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Medicine getMedicine() {
		return medicine;
	}
}
