package br.com.easyclinica.domain.entities;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

@Entity
@Table(name="medical_procedures")
public class Procedure {

	@Id @GeneratedValue
	private int id;
	private String name;
	@Index(name="idx_procedure_ambcode") 
	private String ambCode;
	@Index(name="idx_procedure_lpmcode")
	private String lpmCode;
	@Index(name="idx_procedure_cbhpmcode")
	private String cbhpmCode;
	@Index(name="idx_procedure_tusscode")
	private String tussCode;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="procedure")
	private List<MaterialInProcedure> materials;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="procedure")
	private List<MedicineInProcedure> medicine;
	
	public Procedure() {
		this(0);
	}
	
	public Procedure(int id) {
		this.id = id;
		
		materials = new LinkedList<MaterialInProcedure>();
		medicine = new LinkedList<MedicineInProcedure>();
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
	
	public String getLpmCode() {
		return lpmCode;
	}

	public void setLpmCode(String lpmCode) {
		this.lpmCode = lpmCode;
	}

	public String getCbhpmCode() {
		return cbhpmCode;
	}

	public void setCbhpmCode(String cbhpmCode) {
		this.cbhpmCode = cbhpmCode;
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
	
	public void addMaterial(Material material, BigDecimal qty) {
		MaterialInProcedure materialInProcedure = new MaterialInProcedure();
		materialInProcedure.setMaterial(material);
		materialInProcedure.setProcedure(this);
		materialInProcedure.setQty(qty);
		
		this.materials.add(materialInProcedure);
	}
	
	public void addMedicine(Medicine medicine, BigDecimal qty) {
		MedicineInProcedure medicineInProcedure = new MedicineInProcedure();
		medicineInProcedure.setMedicine(medicine);
		medicineInProcedure.setProcedure(this);
		medicineInProcedure.setQty(qty);
		
		this.medicine.add(medicineInProcedure);
	}	
}
