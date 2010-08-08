package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Website {
	private String website;
	
	protected Website() {}
	public Website(String website) {
		this.website = website;
	}
	
	public String get() {
		return website;
	}
	
	public String toString() {
		return get();
	}
	public static Website empty() {
		return new Website("");
	}
}
