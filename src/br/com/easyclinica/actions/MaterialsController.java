package br.com.easyclinica.actions;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.repositories.AllMaterials;

@Resource
public class MaterialsController extends BaseController {
	private AllMaterials allMaterials;
	
	public MaterialsController(AllMaterials allMaterials, Result result) {
		super(result);
		
		this.allMaterials = allMaterials;
	}

	@Get
	public void _search(String q) {
		List<Material> materials = allMaterials.search(q);
		
		result.include("materials", materials);
	}
}
