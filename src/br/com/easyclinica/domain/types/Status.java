package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Status {
	public enum Types {	
		ACTIVE,
		CANCELED
	}
	
	private int status;
	
	protected Status() {}
	public Status(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Status) ) return false;
		
		Status other = (Status)obj;
		
		return this.getStatus() == other.getStatus();
	}
}
