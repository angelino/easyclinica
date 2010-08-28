package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Website {
	private String website;
	
	protected Website() {}
	public Website(String website) {
		this.website = website;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public String toString() {
		return getWebsite();
	}
	public static Website empty() {
		return new Website("");
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Website) ) return false;
		
		Website other = (Website)obj;
		
		return this.getWebsite() == other.getWebsite();
	}
	
	@Override
	public int hashCode() {
		return website.hashCode();
	}

}
