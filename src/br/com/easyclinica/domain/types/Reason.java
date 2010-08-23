package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Reason {
	public enum Types {	
		APPOINTMENT,
		RETURN
	}
	
	private int reason;
	
	protected Reason() {}
	public Reason(int reason) {
		this.reason = reason;
	}
	
	public int getReason() {
		return reason;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Reason) ) return false;
		
		Reason other = (Reason)obj;
		
		return this.getReason() == other.getReason();
	}
}
