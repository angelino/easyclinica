package br.com.easyclinica.domain.types;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Day {
	private Date day;

	protected Day() {}
	
	public Day(Date day) {
		this.day = day;
	}
	
	public Date getDay() {
		return day;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Day) ) return false;
		
		Day other = (Day)obj;
		
		return this.getDay() == other.getDay();
	}
}
