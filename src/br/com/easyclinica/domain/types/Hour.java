package br.com.easyclinica.domain.types;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Hour {
	private Date hour;
	
	protected Hour() {}
	public Hour(Date hour) {
		this.hour = hour;
		
		if(this.hour == null) this.hour = new Date();
	}
	
	public Date getHour() {
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
