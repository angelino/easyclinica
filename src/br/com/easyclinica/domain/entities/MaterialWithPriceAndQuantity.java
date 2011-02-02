package br.com.easyclinica.domain.entities;


public class MaterialWithPriceAndQuantity {
	
	private int materialId;
	private String materialName;
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
	
	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}
	public int getMaterialId() {
		return materialId;
	}
	
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialName() {
		return materialName;
	}
}
