package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class PostalCode {
	private String postalCode;
	
	protected PostalCode() {}
	public PostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public String toString() {
		return getPostalCode();
	}
	

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof PostalCode) ) return false;
		
		PostalCode other = (PostalCode)obj;
		
		return this.getPostalCode() == other.getPostalCode();
	}
	
	@Override
	public int hashCode() {
		return postalCode.hashCode();
	}

}
