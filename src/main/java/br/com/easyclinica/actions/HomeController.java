package br.com.easyclinica.actions;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.domain.repositories.AllMessages;
import br.com.easyclinica.domain.repositories.AllSchedule;
import br.com.easyclinica.domain.services.newbie.Newbie;
import br.com.easyclinica.infra.gravatar.GravatarImage;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@Resource
public class HomeController {

	private final AllMessages messages;
	private final Result result;
	private final LoggedUser loggedUser;
	private final AllSchedule allSchedule;
	private final Newbie newbie;

	public HomeController(AllMessages messages, Result result, LoggedUser loggedUser, AllSchedule allSchedule, Newbie newbie) {
		this.messages = messages;
		this.result = result;
		this.loggedUser= loggedUser;
		this.allSchedule = allSchedule;
		this.newbie = newbie;
	}
	
	@Get
	@Path("/")
	public void dashboard(){
		List<Schedule> schedules = null;
		
		Calendar twoHoursAfter = Calendar.getInstance();
		twoHoursAfter.add(Calendar.HOUR_OF_DAY, 2);
		
		if(loggedUser.isDoctor()) schedules = allSchedule.getDoctorScheduleByPeriod(loggedUser.getDoctor(), Calendar.getInstance(), twoHoursAfter);
		else schedules = allSchedule.getScheduleByPeriod(Calendar.getInstance(), twoHoursAfter);
		
		result.include("schedules", schedules);
		result.include("newbie", newbie);
		result.include("gravatar", new GravatarImage());
		result.include("messages", messages.recents());
	}

	@Get
	@Path("/nao-autorizado")
	public void notAuthorized() {
	}
}
