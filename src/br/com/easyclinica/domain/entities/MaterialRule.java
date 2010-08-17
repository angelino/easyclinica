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
public class MaterialRule {

	@Id @GeneratedValue
	private int id;
	@OneToOne(fetch=FetchType.EAGER) @JoinColumn(name="material_fk")
	private Material material;
	@Embedded 
	private CH ch;
	@Embedded @AttributeOverride(name="money", column = @Column(name="value")) 
	private Money value;
	
	@ManyToOne
	@JoinColumn(name="healthcareplan_fk")
	private HealthCarePlan healthCarePlan;

	protected MaterialRule() {}
	
	public MaterialRule(int id) {
		this.id = id;
	}
	
	public MaterialRule(HealthCarePlan healthCarePlan, Material material, CH ch) {
		this.healthCarePlan = healthCarePlan;
		this.material = material;
		this.ch = ch;
		this.value = Money.zero();
	}
	
	public MaterialRule(HealthCarePlan healthCarePlan, Material material, Money value) {
		this.healthCarePlan = healthCarePlan;
		this.material = material;
		this.value = value;
		this.ch = CH.zero();
	}

	public Material getMaterial() {
		return material;
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
