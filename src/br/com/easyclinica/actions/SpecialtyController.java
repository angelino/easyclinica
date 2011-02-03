package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.domain.entities.PrecifiedSpecialty;
import br.com.easyclinica.domain.entities.Specialty;
import br.com.easyclinica.domain.repositories.PrecifiedThings;

@Resource
public class SpecialtyController extends BaseController {

	private PrecifiedThings precifiedThings;
	
	public SpecialtyController(PrecifiedThings precifiedThings, Result result) {
		super(result);
		
		this.precifiedThings = precifiedThings;
	}

	@Get
	@Path("/especialidades/{specialty.id}/{healthCarePlan.id}")
	public void getPrice(Specialty specialty, HealthCarePlan healthCarePlan)
	{
		PrecifiedSpecialty precifiedSpecialty = precifiedThings.getMedicalAppointmentPrice(specialty, healthCarePlan);
	
		result.use(Results.json()).from(precifiedSpecialty).serialize();
	}
}
