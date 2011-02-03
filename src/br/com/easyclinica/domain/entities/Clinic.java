package br.com.easyclinica.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Clinic {

	@Id @GeneratedValue
	private int id;
	
	private String name;
	private String domain;
	private boolean active;
	@ManyToOne
	private HealthCarePlan privatePlan;
	
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

	
}
