package br.com.easyclinica.domain.entities.pricing;

import java.math.BigDecimal;

public class PricedProcedure extends PricedStuff {

	private BigDecimal roomTaxAmount;
	private String ambCode;
	private int ch;

	public PricedProcedure(int id, String name, BigDecimal amount, String ambCode, Integer ch, BigDecimal roomTaxAmount) {
		super(id, name, amount);
		this.ambCode = ambCode;
		this.roomTaxAmount = roomTaxAmount;
		this.ch = ch == null ? 0 : ch;
	}
	
	public BigDecimal getRoomTaxAmount() {
		return roomTaxAmount;
	}

	public String getAmbCode() {
		return ambCode;
	}

	public int getCh() {
		return ch;
	}

	
	
}
