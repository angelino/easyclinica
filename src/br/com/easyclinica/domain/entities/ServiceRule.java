package br.com.easyclinica.domain.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	protected ServiceRule() {}
	
	public ServiceRule(Service service, CH ch) {
		this.service = service;
		this.ch = ch;
		this.value = Money.zero();
	}
	
	public ServiceRule(Service service, Money value) {
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
	
	
}
