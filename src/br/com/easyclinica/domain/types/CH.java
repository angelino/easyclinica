package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class CH {
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
}
