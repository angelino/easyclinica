package br.com.easyclinica.domain.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalTime;

import br.com.easyclinica.domain.types.Address;

@Entity
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Clinic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private int id;
	
	private String name;
	private String domain;
	private boolean active;
	@Embedded private Address address;
	private String billingEmail;
	private String contactEmail;
	private String telephone;
	private String telephone2;
	private String fax;
	private String website;
	private String cnpj;
	@ManyToOne(fetch=FetchType.EAGER)
	private HealthCarePlan privatePlan;
	
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
	private LocalTime startOperation;
	
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime")
	private LocalTime endOperation;
	
	public Clinic() {}
	
	public Clinic(int id)
	{
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getId() {
		return id;
	}
	public boolean isActive() {
		return active;
	}

	protected void setId(int id) {
		this.id = id;
	}

	protected void setActive(boolean active) {
		this.active = active;
	}

	public HealthCarePlan getPrivatePlan() {
		return privatePlan;
	}

	public void setPrivatePlan(HealthCarePlan privatePlan) {
		this.privatePlan = privatePlan;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getBillingEmail() {
		return billingEmail;
	}

	public void setBillingEmail(String billingEmail) {
		this.billingEmail = billingEmail;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setEndOperation(LocalTime endOperation) {
		this.endOperation = endOperation;
	}

	public LocalTime getEndOperation() {
		return endOperation;
	}

	public void setStartOperation(LocalTime startOperation) {
		this.startOperation = startOperation;
	}

	public LocalTime getStartOperation() {
		return startOperation;
	}
}
