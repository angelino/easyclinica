package br.com.easyclinica.actions;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Procedure;
import br.com.easyclinica.domain.repositories.AllProcedures;

@Resource
public class ProceduresController extends BaseController {
	private AllProcedures allProcedures;
	
	public ProceduresController(AllProcedures allProcedures, Result result) {
		super(result);
		
		this.allProcedures = allProcedures;
	}
	
	@Get
	public void _search(String q) {
		List<Procedure> procedures = allProcedures.search(q);
		
		result.include("procedures", procedures);
	}
}
