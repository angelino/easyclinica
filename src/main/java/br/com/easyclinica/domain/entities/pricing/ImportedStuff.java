package br.com.easyclinica.domain.entities.pricing;

import java.math.BigDecimal;

public class ImportedStuff {

	private int id;
	private BigDecimal value;
	private String name;
	private int ch;
	private BigDecimal roomTax;

	public ImportedStuff() {}
	public ImportedStuff(int id, String name, BigDecimal value) {
		this(id, name, value, 0, BigDecimal.ZERO);
	}

	public ImportedStuff(int id, String name, BigDecimal value, int ch, BigDecimal roomTax) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.ch = ch;
		this.roomTax = roomTax;
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
	public BigDecimal getRoomTax() {
		return roomTax;
	}
	protected void setRoomTax(BigDecimal roomTax) {
		this.roomTax = roomTax;
	}
	

	
}
