package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Hour {
	private String hour;
	
	protected Hour() {}
	public Hour(String hour) {
		this.hour = hour;
	}
	
	public String getHour() {
		return hour;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Hour) ) return false;
		
		Hour other = (Hour)obj;
		
		return this.getHour().equals(other.getHour());
	}
}
