package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class CRM {
	private String crm;
	
	protected CRM() {}
	public CRM(String crm) {
		this.crm = crm;
	}
	
	public String getCrm() {
		return crm;
	}
	
	public String toString() {
		return getCrm();
	}
	
	public static CRM empty() {
		return new CRM("");
	}
}
