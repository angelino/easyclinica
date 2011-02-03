package br.com.easyclinica.domain.entities;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="medical_procedures")
public class Procedure {

	@Id @GeneratedValue
	private int id;
	private String name;
	private int ch;
	private String ambCode;
	private String tussCode;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="procedure")
	private List<MaterialInProcedure> materials;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="procedure")
	private List<MedicineInProcedure> medicine;
	
	public Procedure() {
		this(0);
	}
	
	public Procedure(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCh() {
		return ch;
	}
	public void setCh(int ch) {
		this.ch = ch;
	}
	public String getAmbCode() {
		return ambCode;
	}
	public void setAmbCode(String ambCode) {
		this.ambCode = ambCode;
	}
	public String getTussCode() {
		return tussCode;
	}
	public void setTussCode(String tussCode) {
		this.tussCode = tussCode;
	}
	
	protected void setId(int id) {
		this.id = id;
	}
	
	protected void setMaterials(List<MaterialInProcedure> materials) {
		this.materials = materials;
	}
	
	public List<MaterialInProcedure> getMaterials() {
		return Collections.unmodifiableList(materials);
	}

	protected void setMedicines(List<MedicineInProcedure> medicine) {
		this.medicine = medicine;
	}

	public List<MedicineInProcedure> getMedicines() {
		return Collections.unmodifiableList(medicine);
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
