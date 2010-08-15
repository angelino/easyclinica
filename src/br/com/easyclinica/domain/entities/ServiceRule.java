package br.com.easyclinica.domain.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Money;

@Entity
public class ServiceRule {
	
	@Id @GeneratedValue
	private int id;
	@OneToOne(fetch=FetchType.EAGER) @JoinColumn(name="service_fk")
	private Service service;
	@Embedded 
	private CH ch;
	@Embedded @AttributeOverride(name="money", column = @Column(name="value")) 
	private Money value;
	
	@ManyToOne
	@JoinColumn(name="healthcareplan_fk")
	private HealthCarePlan healthCarePlan;

	protected ServiceRule() {}
	
	public ServiceRule(int id) {
		this.id = id;
	}
	
	public ServiceRule(HealthCarePlan healthCarePlan, Service service, CH ch) {
		this.healthCarePlan = healthCarePlan;
		this.service = service;
		this.ch = ch;
		this.value = Money.zero();
	}
	
	public ServiceRule(HealthCarePlan healthCarePlan, Service service, Money value) {
		this.healthCarePlan = healthCarePlan;
		this.service = service;
		this.value = value;
		this.ch = CH.zero();
	}

	public Service getService() {
		return service;
	}

	public CH getCh() {
		return ch;
	}

	public Money getValue() {
		return value;
	}

	public int getId() {
		return id;
	}

	public boolean isRulingCh() {
		return !ch.equals(CH.zero());
	}

	public boolean isRulingValue() {
		return !value.equals(Money.zero());
	}
	
	public HealthCarePlan getHealthCarePlan() {
		return healthCarePlan;
	}	
}
