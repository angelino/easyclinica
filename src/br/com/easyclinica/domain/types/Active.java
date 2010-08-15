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
}
