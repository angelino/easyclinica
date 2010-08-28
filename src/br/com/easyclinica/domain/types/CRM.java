package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class CRM implements GeneralType {
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
	
	public boolean isValid() {		
		if(this.crm == null || this.crm.length() == 0) return false;
		
		return true;
	}
}
