package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Material;

public class MaterialBuilder {
	private Material material;
	
	public MaterialBuilder() {
		this(0);
	}
	
	public MaterialBuilder(int id) {
		material = new Material(id);
		material.setName("Material");
	}
	
	public MaterialBuilder withName(String name) {
		material.setName(name);
		return this;
	}
	
	public Material instance() {
		return material;
	}
}
