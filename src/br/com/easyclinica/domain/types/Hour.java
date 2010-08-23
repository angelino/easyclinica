package br.com.easyclinica.domain.types;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Hour {
	private Date hour;
	
	protected Hour() {}
	public Hour(Date hour) {
		this.hour = hour;
	}
	
	public Date getHour() {
		return hour;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Hour) ) return false;
		
		Hour other = (Hour)obj;
		
		return this.getHour() == other.getHour();
	}
}
