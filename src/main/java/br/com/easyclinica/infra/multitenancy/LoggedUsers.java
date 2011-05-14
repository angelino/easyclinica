package br.com.easyclinica.infra.multitenancy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.config.Config;
import br.com.easyclinica.infra.clock.Clock;

@ApplicationScoped
@Component
public class LoggedUsers {

	private final Map<String, List<EmployeeVersusTime>> users;
	private final Clock clock;
	private final int maxLoggedTime;
	
	public LoggedUsers(Clock clock, Config config) {
		this.clock = clock;
		this.maxLoggedTime = Integer.parseInt(config.get("max_logged_time"));
		this.users = new HashMap<String, List<EmployeeVersusTime>>();
	}
	
	public void passedBy(String tenant, String user) {
		if(!users.containsKey(tenant)) {
			createTenantList(tenant);
		}
		
		EmployeeVersusTime employee = findUser(tenant, user);
		employee.passedBy();
	}

	private EmployeeVersusTime findUser(String tenant, String user) {
		List<EmployeeVersusTime> all = users.get(tenant);
		for (EmployeeVersusTime employeeVersusTime : all) {
			if(employeeVersusTime.isAbout(user)) {
				return employeeVersusTime;
			}
		}
		
		EmployeeVersusTime newLogged = new EmployeeVersusTime(user);
		all.add(newLogged);
		return newLogged;
	}

	private void createTenantList(String tenant) {
		users.put(tenant, new ArrayList<EmployeeVersusTime>());
		
	}

	public boolean isActive(String tenant, String user) {
		if(!users.containsKey(tenant)) {
			return false;
		}

		return findUser(tenant, user).isActive();
	}	

	private class EmployeeVersusTime {
		private String employee;
		private Calendar lastTimeSaw;
		private Calendar firstTimeSaw;

		public void passedBy() {
			if(!isActive()) {
				firstTimeSaw = (Calendar) clock.now().clone();
			}
			lastTimeSaw = (Calendar) clock.now().clone();
		}
		
		public boolean isAbout(String user) {
			return employee.equals(user);
		}
		public Calendar getLastTimeSaw() {
			return lastTimeSaw;
		}
		
		public Calendar getFirstTimeSaw() {
			return firstTimeSaw;
		}

		public EmployeeVersusTime(String employee) {
			this.employee = employee;
			this.firstTimeSaw = clock.now();
			this.lastTimeSaw = clock.now();
		}
		
		public boolean isActive() {
			Calendar maxLastTimeSaw = (Calendar) clock.now().clone();
			maxLastTimeSaw.add(Calendar.MINUTE, -1 * maxLoggedTime);
			
			return this.lastTimeSaw.after(maxLastTimeSaw);
		}

		
	}

	public Calendar firstPassedBy(String tenant, String user) {
		if(!users.containsKey(tenant)) {
			return null;
		}
		
		return findUser(tenant, user).getFirstTimeSaw();
	}
	
	public Calendar lastPassedBy(String tenant, String user) {
		if(!users.containsKey(tenant)) {
			return null;
		}
		
		return findUser(tenant, user).getLastTimeSaw();
	}

}
