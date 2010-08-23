package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Active {
	private boolean active;
	
	protected Active() {}
	private Active(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public String toString() {
		return String.valueOf(active);
	}
	
	public static Active active() {
		return new Active(true);
	}
	
	public static Active notActive() {
		return new Active(false);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Active) ) return false;
		
		Active other = (Active)obj;
		
		return this.isActive() == other.isActive();
	}
	
	@Override
	public int hashCode() {
		return active ? 1 : 0;
	}

}
