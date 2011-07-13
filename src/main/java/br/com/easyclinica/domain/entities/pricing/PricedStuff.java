package br.com.easyclinica.domain.entities.pricing;

import java.math.BigDecimal;

public class PricedStuff {

	private BigDecimal amount;
	private int id;
	private String name;
	private BigDecimal roomTaxAmount;
	
	public PricedStuff(int id, String name, BigDecimal amount) {
		this.amount = (amount == null ? BigDecimal.ZERO : amount);
		this.id = id;
		this.name = name;
	}

	public PricedStuff(int id, String name, BigDecimal amount, BigDecimal roomTaxAmount) {
		this.amount = (amount == null ? BigDecimal.ZERO : amount);
		this.roomTaxAmount = (roomTaxAmount == null ? BigDecimal.ZERO : roomTaxAmount);
		this.id = id;
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public BigDecimal getRoomTaxAmount() {
		return roomTaxAmount;
	}
	
	
	
}
