package br.com.easyclinica.domain.dto;

public class MedicineWithPriceAndQuantity {

	private int medicineId;
	private String medicineName;
	private float qty;
	private double amount;
	
	public void setQty(float qty) {
		this.qty = qty;
	}
	public float getQty() {
		return qty;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getAmount() {
		return amount;
	}
	
	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}
	public int getMedicineId() {
		return medicineId;
	}
	
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getMedicineName() {
		return medicineName;
	}
}
