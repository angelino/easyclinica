package br.com.easyclinica.domain.entities.pricing;

import java.math.BigDecimal;

public class ImportedStuff {

	private int id;
	private BigDecimal value;
	private String name;
	private int ch;

	public ImportedStuff() {}
	public ImportedStuff(int id, String name, BigDecimal value) {
		this(id, name, value, 0);
	}

	public ImportedStuff(int id, String name, BigDecimal value, int ch) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.ch = ch;
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
	public int getCh() {
		return ch;
	}
	protected void setCh(int ch) {
		this.ch = ch;
	}
	
	
	
}
