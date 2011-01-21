package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Domain implements GeneralType {

	private String domain;
	
	protected Domain() {}
	public Domain(String domain) {
		this.domain = domain;
	}
	
	public String getDomain() {
		return domain;
	}
	
	public String toString() {
		return getDomain();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Domain) ) return false;
		
		Domain other = (Domain)obj;
		
		return this.getDomain() == other.getDomain();
	}
	
	@Override
	public int hashCode() {
		return domain.hashCode();
	}
	
	public static Domain empty() {
		return new Domain("");
	}
	
	public boolean isValid() {
		
		if(this.domain == null || this.domain.length() == 0) return false;
		
		return true;
	}
}
