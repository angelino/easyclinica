package br.com.easyclinica.view.schedule;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.Schedule;
import br.com.easyclinica.helper.CalendarUtils;
import br.com.easyclinica.infra.multitenancy.LoggedUser;

@Component
public class ScheduleUtils {

	private CalendarUtils calendarUtils;
	private LoggedUser loggedUser;
	
	public ScheduleUtils(CalendarUtils calendarUtils, LoggedUser loggedUser) {
		this.calendarUtils = calendarUtils;
		this.loggedUser = loggedUser;
	}
	
	public List<Time> buildDoctorSchedule(List<Schedule> schedules, int interval) {
		Calendar start = getFirstHourOfTheSchedule();		
		Calendar end = getLastHourOfTheSchedule();
		
		List<Time> timeTable = new LinkedList<Time>();
		while(start.before(end) || start.equals(end))
		{
			Time time = new Time(start);
			
			for(Schedule schedule : schedules) {
				if(schedule.getStartTime().get(Calendar.HOUR_OF_DAY) == start.get(Calendar.HOUR_OF_DAY) &&
				   schedule.getStartTime().get(Calendar.MINUTE) == start.get(Calendar.MINUTE))
				{
					time.addCommitment(schedule);
				}
			}
			
			timeTable.add(time);
			
			start.add(Calendar.MINUTE, interval);
		}
		
		return timeTable;
	}

	private Calendar getFirstHourOfTheSchedule() {
		int hourStartOperation = this.loggedUser.getClinic().getStartOperation().getHourOfDay();
		int minuteStartOperation = this.loggedUser.getClinic().getStartOperation().getMinuteOfHour();
		Calendar start = calendarUtils.CloneDateAndSetTime(Calendar.getInstance(), hourStartOperation, minuteStartOperation, 0);
		return start;
	}
	
	private Calendar getLastHourOfTheSchedule() {
		int hourStartOperation = this.loggedUser.getClinic().getEndOperation().getHourOfDay();
		int minuteStartOperation = this.loggedUser.getClinic().getEndOperation().getMinuteOfHour();
		Calendar end = calendarUtils.CloneDateAndSetTime(Calendar.getInstance(), hourStartOperation, minuteStartOperation, 0);
		return end;
	}
}
