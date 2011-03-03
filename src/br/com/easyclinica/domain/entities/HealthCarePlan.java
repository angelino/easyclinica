package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import br.com.easyclinica.domain.types.Address;

@Entity
public class HealthCarePlan {

	@Id
	@GeneratedValue
	private int id;

	private String name;
	@Embedded
	private Address address;
	private String telephone;
	private String email;
	private String website;
	@Type(type = "text")
	private String observations;
	private BigDecimal ch;
	private String contact;
	private boolean active;
	private int periodToReturn;
	private boolean payForRoomRate;
	private BigDecimal roomRateDefaultAmount;

	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "healthCarePlan", cascade=CascadeType.ALL)
	private List<PrecifiedMaterial> precifiedMaterials;
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "healthCarePlan", cascade=CascadeType.ALL)
	private List<PrecifiedMedicine> precifiedMedicines;
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "healthCarePlan", cascade=CascadeType.ALL)
	private List<PrecifiedProcedure> precifiedProcedures;
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "healthCarePlan", cascade=CascadeType.ALL)
	private List<PrecifiedSpecialty> precifiedSpecialties;

	public HealthCarePlan() {
		precifiedMaterials = new ArrayList<PrecifiedMaterial>();
		precifiedMedicines = new ArrayList<PrecifiedMedicine>();
		precifiedProcedures = new ArrayList<PrecifiedProcedure>();
		precifiedSpecialties = new ArrayList<PrecifiedSpecialty>();
	}

	public HealthCarePlan(int id) {
		this();
		this.id = id;
	}

	public static HealthCarePlan empty() {
		HealthCarePlan emptyPlan = new HealthCarePlan();
		emptyPlan.activate();
		return emptyPlan;
	}

	public void deactivate() {
		active = false;
	}

	public void activate() {
		active = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public boolean isActive() {
		return active;
	}

	protected void setActive(boolean active) {
		this.active = active;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public List<PrecifiedMaterial> getPrecifiedMaterials() {
		return Collections.unmodifiableList(precifiedMaterials);
	}

	public List<PrecifiedMedicine> getPrecifiedMedicines() {
		return Collections.unmodifiableList(precifiedMedicines);
	}

	public List<PrecifiedProcedure> getPrecifiedProcedures() {
		return Collections.unmodifiableList(precifiedProcedures);
	}

	public List<PrecifiedSpecialty> getPrecifiedSpecialties() {
		return precifiedSpecialties;
	}

	protected void setPrecifiedSpecialties(
			List<PrecifiedSpecialty> precifiedSpecialties) {
		this.precifiedSpecialties = precifiedSpecialties;
	}

	protected void setPrecifiedMaterials(
			List<PrecifiedMaterial> precifiedMaterials) {
		this.precifiedMaterials = precifiedMaterials;
	}


	public void setPeriodToReturn(int periodToReturn) {
		this.periodToReturn = periodToReturn;
	}

	public int getPeriodToReturn() {
		return periodToReturn;
	}

	public void setCh(BigDecimal ch) {
		this.ch = ch;
	}

	public BigDecimal getCh() {
		return ch;
	}

	public void setPayForRoomRate(boolean payForRoomRate) {
		this.payForRoomRate = payForRoomRate;
	}

	public boolean isPayForRoomRate() {
		return payForRoomRate;
	}

	public void setRoomRateDefaultAmount(BigDecimal roomRateDefaultAmount) {
		this.roomRateDefaultAmount = roomRateDefaultAmount;
	}

	public BigDecimal getRoomRateDefaultAmount() {
		return roomRateDefaultAmount;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public PrecifiedMedicine addPrecifiedMedicine(Medicine medicine, BigDecimal amount) {
		PrecifiedMedicine precifiedMedicine = new PrecifiedMedicine();
		precifiedMedicine.setAmount(amount);
		precifiedMedicine.setMedicine(medicine);
		precifiedMedicine.setHealthCarePlan(this);
		this.precifiedMedicines.add(precifiedMedicine);
		
		return precifiedMedicine;
	}

	public PrecifiedSpecialty addPrecifiedSpecialty(Specialty specialty, BigDecimal amount) {
		PrecifiedSpecialty precifiedSpecialty = new PrecifiedSpecialty();
		precifiedSpecialty.setSpecialty(specialty);
		precifiedSpecialty.setAmount(amount);
		precifiedSpecialty.setHealthCarePlan(this);
		this.precifiedSpecialties.add(precifiedSpecialty);
		
		return precifiedSpecialty;
	}

	public PrecifiedProcedure addPrecifiedProcedure(Procedure procedure, BigDecimal amount) {
		PrecifiedProcedure precifiedProcedure = new PrecifiedProcedure();
		precifiedProcedure.setProcedure(procedure);
		precifiedProcedure.setFixedAmount(amount);
		precifiedProcedure.setHealthCarePlan(this);
		this.precifiedProcedures.add(precifiedProcedure);
		
		return precifiedProcedure;
	}
	
	public PrecifiedMaterial addPrecifiedMaterial(Material material, BigDecimal amount) {
		PrecifiedMaterial pm = new PrecifiedMaterial();
		pm.setAmount(amount);
		pm.setMaterial(material);
		pm.setHealthCarePlan(this);
		
		this.precifiedMaterials.add(pm);
		
		return pm;
	}
}
