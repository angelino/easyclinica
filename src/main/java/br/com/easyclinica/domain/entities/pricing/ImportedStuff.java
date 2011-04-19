package br.com.easyclinica.domain.entities.pricing;

import java.math.BigDecimal;

public class ImportedStuff {

	private int id;
	private BigDecimal value;
	private String name;

	public ImportedStuff() {}
	public ImportedStuff(int id, String name, BigDecimal value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
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
