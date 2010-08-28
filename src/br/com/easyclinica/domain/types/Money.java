package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Money implements GeneralType{
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
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Money) ) return false;
		
		Money other = (Money)obj;
		
		return this.getMoney() == other.getMoney();
	}
	
	@Override
	public int hashCode() {
		return new Double(money).hashCode();
	}
	
	public static Money zero() {
		return new Money(0);
	}
	
	public boolean isValid() {		
		if(this.money <= 0) return false;
		
		return true;
	}
}
