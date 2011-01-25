package br.com.easyclinica.view.model;

import java.util.ArrayList;
import java.util.List;

public class ProcedureData {

	private int id;
	private List<MaterialData> materials;
	private List<MedicineData> medicines;
	
	public ProcedureData() {
		materials = new ArrayList<MaterialData>();
		medicines = new ArrayList<MedicineData>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<MaterialData> getMaterials() {
		return materials;
	}
	public void setMaterials(List<MaterialData> materials) {
		this.materials = materials;
	}
	public List<MedicineData> getMedicines() {
		return medicines;
	}
	public void setMedicines(List<MedicineData> medicines) {
		this.medicines = medicines;
	}
	
	
}
