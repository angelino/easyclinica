package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class CH implements GeneralType {
	private double ch;
	
	protected CH() {}
	public CH(double ch) {
		this.ch = ch;
	}
	
	public double getCh() {
		return ch;
	}
	
	public String toString() {
		return String.valueOf(ch);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof CH) ) return false;
		
		CH other = (CH)obj;
		
		return this.getCh() == other.getCh();
	}
	
	@Override
	public int hashCode() {
		return new Double(ch).hashCode();
	}
	
	public static CH zero() {
		return new CH(0);
	}
	public boolean isZero() {
		return this.equals(CH.zero());
	}
	
	public boolean isValid() {		
		if(this.isZero()) return false;
		
		return true;
	}
}
