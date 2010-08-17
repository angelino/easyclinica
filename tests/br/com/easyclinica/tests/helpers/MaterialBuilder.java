package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.ServicesTable;
import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Name;

public class MaterialBuilder {

	private Material material;
	
	public MaterialBuilder(ServicesTable table) {
		material = new Material(table, 0, new Name("material"), new CH(10));
	}
	
	public Material instance() {
		return material;
	}

	public MaterialBuilder withId(int id) {
		material = new Material(material.getTable(), id, material.getName(), material.getCh());
		return this;
	}
}
