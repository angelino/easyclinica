package br.com.easyclinica.domain.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.easyclinica.domain.exceptions.InvalidMaterialRuleException;
import br.com.easyclinica.domain.exceptions.InvalidServiceRuleException;
import br.com.easyclinica.domain.types.Active;
import br.com.easyclinica.domain.types.Address;
import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Email;
import br.com.easyclinica.domain.types.Money;
import br.com.easyclinica.domain.types.Name;
import br.com.easyclinica.domain.types.Observations;
import br.com.easyclinica.domain.types.Telephone;
import br.com.easyclinica.domain.types.Website;

@Entity
public class HealthCarePlan {

	@Id @GeneratedValue
	private int id;
	@Embedded private Name name;
	@Embedded private Address address;
	@Embedded private Telephone telephone;
	@Embedded private Email email;
	@Embedded private Website website;
	@Embedded private Observations observations;
	@Embedded @AttributeOverride(name="money", column = @Column(name="ch"))
	private Money ch;
	@Embedded @AttributeOverride(name="name", column = @Column(name="contact"))
	private Name contact;
	@Embedded private Active active;

	@OneToOne(fetch=FetchType.EAGER) @JoinColumn(name="table_fk")	
	private ServicesTable table;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="healthCarePlan")
	private List<ServiceRule> serviceRules;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="healthCarePlan")
	private List<MaterialRule> materialRules;
	
	protected HealthCarePlan() {}

	public HealthCarePlan(int id, Name name, Address address, Telephone telephone,
			Email email, Website website, Name contact,
			Observations observations, ServicesTable table, Money ch) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
		this.website = website;
		this.contact = contact;
		this.observations = observations;
		this.table = table;
		this.ch = ch;
		this.active = Active.active();
		this.serviceRules = new ArrayList<ServiceRule>();
		this.materialRules = new ArrayList<MaterialRule>();
	}

	public Name getName() {
		return name;
	}
	
	public List<ServiceRule> getServiceRules() {
		return Collections.unmodifiableList(serviceRules);
	}

	public List<MaterialRule> getMaterialRules() {
		return Collections.unmodifiableList(materialRules);
	}
	
	public int getId() {
		return id;
	}

	public Address getAddress() {
		return address;
	}

	public Telephone getTelephone() {
		return telephone;
	}

	public Email getEmail() {
		return email;
	}

	public Website getWebsite() {
		return website;
	}

	public Name getContact() {
		return contact;
	}

	public Observations getObservations() {
		return observations;
	}
	
	public Money getCh() {
		return ch;
	}

	public ServicesTable getTable() {
		return table;
	}
	
	public Active getActive() {
		return active;
	}
	public static HealthCarePlan empty() {
		return new HealthCarePlan();
	}

	public void deactivate() {
		active = Active.notActive();
	}

	public void addServiceRule(Service service, Money money) throws InvalidServiceRuleException {
		if(findServiceRule(service)!=null) throw new InvalidServiceRuleException(service);
		
		serviceRules.add(new ServiceRule(this, service, money));
	}

	public void addServiceRule(Service service, CH ch) throws InvalidServiceRuleException {
		if(findServiceRule(service)!=null) throw new InvalidServiceRuleException(service);
		
		serviceRules.add(new ServiceRule(this, service, ch));
	}
	
	public void removeServiceRuleById(int ruleId) {
		ServiceRule ruleToBeDeleted = null;
		for(ServiceRule rule : serviceRules) {
			if(rule.getId() == ruleId) ruleToBeDeleted = rule;
		}
		
		if(ruleToBeDeleted != null) serviceRules.remove(ruleToBeDeleted);
	}
	
	public void addMaterialRule(Material material, Money money) throws InvalidMaterialRuleException {
		if(findMaterialRule(material)!=null) throw new InvalidMaterialRuleException(material);
		
		materialRules.add(new MaterialRule(this, material, money));
	}

	public void addMaterialRule(Material material, CH ch) throws InvalidMaterialRuleException {
		if(findMaterialRule(material)!=null) throw new InvalidMaterialRuleException(material);
		
		materialRules.add(new MaterialRule(this, material, ch));
	}

	public void removeMaterialRuleById(int ruleId) {
		MaterialRule ruleToBeDeleted = null;
		for(MaterialRule rule : materialRules) {
			if(rule.getId() == ruleId) ruleToBeDeleted = rule;
		}
		
		if(ruleToBeDeleted != null) materialRules.remove(ruleToBeDeleted);
	}
	
	private MaterialRule findMaterialRule(Material material) {
		for(MaterialRule rule : materialRules) {
			if(rule.getMaterial().getId() == material.getId()) return rule;
		}
		
		return null;
	}
	
	private ServiceRule findServiceRule(Service service) {
		for(ServiceRule rule : serviceRules) {
			if(rule.getService().getId() == service.getId()) return rule;
		}
		
		return null;
	}

	public List<Service> getServicesWithNoRules() {
		List<Service> services = new ArrayList<Service>();
		
		for(Service service : getTable().getServices()) {
			if(findServiceRule(service)==null) services.add(service);
		}
		
		return services;
	}

	public List<Material> getMaterialsWithNoRules() {
		List<Material> materials = new ArrayList<Material>();
		
		for(Material material : getTable().getMaterials()) {
			if(findMaterialRule(material)==null) materials.add(material);
		}
		
		return materials;
	}

}
