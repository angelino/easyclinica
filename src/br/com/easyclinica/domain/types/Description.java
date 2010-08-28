package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Description {
	private String description;
	
	protected Description() {}
	public Description(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return getDescription();
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Description) ) return false;
		
		Description other = (Description)obj;
		
		return this.getDescription() == other.getDescription();
	}
	
	@Override
	public int hashCode() {
		return description.hashCode();
	}

	public static Description empty() {
		return new Description("");
	}
}
