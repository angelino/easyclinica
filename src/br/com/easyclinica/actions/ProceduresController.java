package br.com.easyclinica.actions;

import java.util.List;

import br.com.caelum.vraptor.Post;
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
	
	@Post
	public void _searchProcedure(String text) {
		List<Procedure> procedures = allProcedures.search(text);
		
		result.include("procedures", procedures);
	}
}
