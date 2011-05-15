package br.com.easyclinica.infra.multitenancy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
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
		
		EmployeeVersusTime employee = findOrCreateUser(tenant, user);
		employee.passedBy();
	}


	public boolean isActive(String tenant, String user) {
		if(!users.containsKey(tenant)) {
			return false;
		}

		EmployeeVersusTime userInChat = findUser(tenant, user);
		return userInChat == null ? false : userInChat.isActive();
	}	

	public Calendar firstPassedBy(String tenant, String user) {
		if(!users.containsKey(tenant)) {
			return null;
		}
		
		return findOrCreateUser(tenant, user).getFirstTimeSaw();
	}
	
	public Calendar lastPassedBy(String tenant, String user) {
		if(!users.containsKey(tenant)) {
			return null;
		}
		
		return findOrCreateUser(tenant, user).getLastTimeSaw();
	}

	public List<String> getLoggedUsersIn(String tenant) {
		List<String> online = new ArrayList<String>();

		Iterator<EmployeeVersusTime> it = users.get(tenant).iterator();
		while(it.hasNext()){
			EmployeeVersusTime employee = it.next();
			if(employee.isActive()) online.add(employee.getEmployee());
			else it.remove();
		}
		
		return online;
	}

	public void remove(String tenant, String user) {
		Iterator<EmployeeVersusTime> it = users.get(tenant).iterator();
		while(it.hasNext()){
			EmployeeVersusTime employee = it.next();
			if(employee.isAbout(user)) it.remove();
		}
	}
	
	private EmployeeVersusTime findOrCreateUser(String tenant, String user) {
		EmployeeVersusTime e = findUser(tenant, user);
		return e!=null? e : logUser(tenant, user);
	}

	private EmployeeVersusTime findUser(String tenant, String user) {
		List<EmployeeVersusTime> all = users.get(tenant);
		for (EmployeeVersusTime employeeVersusTime : all) {
			if(employeeVersusTime.isAbout(user)) {
				return employeeVersusTime;
			}
		}

		return null;
	}

	private EmployeeVersusTime logUser(String tenant, String user) {
		List<EmployeeVersusTime> all = users.get(tenant);
		EmployeeVersusTime newLogged = new EmployeeVersusTime(user);
		all.add(newLogged);
		return newLogged;
	}

	private void createTenantList(String tenant) {
		users.put(tenant, new ArrayList<EmployeeVersusTime>());
		
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

		public String getEmployee() {
			return employee;
		}

		
	}

}
