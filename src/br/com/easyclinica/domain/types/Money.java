package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Money {
	private double money;
	
	protected Money() {}
	public Money(double money) {
		this.money = money;
	}
	
	public double getMoney() {
		return money;
	}
	
	public String toString() {
		return String.valueOf(money);
	}
}
