package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Material;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.entities.Procedure;

@Resource
public class ProceduresController extends BaseController {

	public ProceduresController(Result result) {
		super(result);
	}

	@Post
	public void _search(String value, int convenioId) {
		Procedure procedure = new Procedure();
		
		procedure.setName(value);
		procedure.setAmbCode("00.000.00.0");
		procedure.setCh(20);
		
		Material material1 = new Material();
		material1.setName("Material 1");
		procedure.addMaterial(material1);
		
		Material material2 = new Material();
		material1.setName("Material 2");
		procedure.addMaterial(material2);
		
		Medicine medicine1 = new Medicine();
		medicine1.setName("Dorflex");
		procedure.addMedicine(medicine1);
		
		result.include("procedure", procedure);		
	}
	
}
