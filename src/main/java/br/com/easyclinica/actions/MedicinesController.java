package br.com.easyclinica.actions;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Medicine;
import br.com.easyclinica.domain.repositories.AllMedicines;

@Resource
public class MedicinesController extends BaseController {

	private AllMedicines allMedicines;
	
	public MedicinesController(AllMedicines allMedicines, Result result) {
		super(result);
		
		this.allMedicines = allMedicines;
	}

	@Get
	public void _search(String q) {
		List<Medicine> medicines = allMedicines.search(q);
		
		result.include("medicines", medicines);
	}
}
