package br.com.easyclinica.domain.entities;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Procedure {

	@Id @GeneratedValue
	private int id;
	private String name;
	private int ch;
	private String ambCode;
	private String tussCode;
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY) 
	private List<Material> materials;
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Medicine> medicine;
	
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
	public List<Material> getMaterials() {
		return Collections.unmodifiableList(materials);
	}
	public List<Medicine> getMedicine() {
		return Collections.unmodifiableList(medicine);
	}
	
}
