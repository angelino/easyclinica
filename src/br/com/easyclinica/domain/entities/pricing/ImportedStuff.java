package br.com.easyclinica.domain.entities.pricing;

import java.math.BigDecimal;

public class ImportedStuff {

	private int id;
	private BigDecimal value;

	public ImportedStuff() {}
	public ImportedStuff(int id, BigDecimal value) {
		this.id = id;
		this.value = value;
	}

	public int getId() {
		return id;
	}
	public BigDecimal getValue() {
		return value;
	}

	protected void setId(int id) {
		this.id = id;
	}

	protected void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
