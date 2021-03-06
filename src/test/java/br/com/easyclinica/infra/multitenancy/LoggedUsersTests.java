package br.com.easyclinica.infra.multitenancy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.config.Config;
import br.com.easyclinica.infra.clock.Clock;

public class LoggedUsersTests {

	private LoggedUsers loggedUsers;
	private Clock clock;
	private Config config;

	@Before
	public void setUp() {
		this.clock = mock(Clock.class);
		when(clock.now()).thenReturn(Calendar.getInstance());
		
		this.config = mock(Config.class);
		when(config.get("max_logged_time")).thenReturn("20");
		
		this.loggedUsers = new LoggedUsers(clock, config);
	}
	
	@Test
	public void shouldUpdateUser() {
		assertFalse(loggedUsers.isActive("tenant1", "mauricio"));
		
		Calendar date = Calendar.getInstance();
		when(clock.now()).thenReturn(date);
		loggedUsers.passedBy("tenant1", "mauricio");
		assertEquals(date, loggedUsers.lastPassedBy("tenant1", "mauricio"));
		assertEquals(date, loggedUsers.firstPassedBy("tenant1", "mauricio"));
	}
	
	@Test
	public void shouldRemoveUser() {
		Calendar date = Calendar.getInstance();
		when(clock.now()).thenReturn(date);
		loggedUsers.passedBy("tenant1", "mauricio");
		
		assertTrue(loggedUsers.isActive("tenant1", "mauricio"));

		loggedUsers.remove("tenant1", "mauricio");
		
		assertFalse(loggedUsers.isActive("tenant1", "mauricio"));
	}
	
	@Test
	public void shouldGetOnlineUsers() {
		Calendar now = Calendar.getInstance();
		when(clock.now()).thenReturn(now);
		
		loggedUsers.passedBy("tenant1", "mauricio");
		loggedUsers.passedBy("tenant1", "murilo");
		loggedUsers.passedBy("tenant1", "marcelo");
		
		loggedUsers.passedBy("tenant2", "joao");
		loggedUsers.passedBy("tenant2", "jose");
		
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		when(clock.now()).thenReturn(yesterday);
		loggedUsers.passedBy("tenant2", "ricardo");
		
		when(clock.now()).thenReturn(now);
		
		List<String> tenant1 = loggedUsers.getLoggedUsersIn("tenant1");
		List<String> tenant2 = loggedUsers.getLoggedUsersIn("tenant2");
		
		assertEquals(3, tenant1.size());
		assertEquals(2, tenant2.size());
		
	}
	
	@Test
	public void shouldSetAsFirstPassedByIfLimitTimePassed() {
		Calendar firstTime = Calendar.getInstance();
		when(clock.now()).thenReturn(firstTime);
		loggedUsers.passedBy("tenant1", "mauricio");
		
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		
		when(clock.now()).thenReturn(tomorrow);
		
		assertFalse(loggedUsers.isActive("tenant1", "mauricio"));
		
		Calendar aLittleBitLater = Calendar.getInstance();
		when(clock.now()).thenReturn(aLittleBitLater);
		loggedUsers.passedBy("tenant1", "mauricio");
		
		assertEquals(firstTime, loggedUsers.firstPassedBy("tenant1", "mauricio"));
	}

	@Test
	public void shouldBeAvailableIfLimitTimeNotPassed() {
		when(clock.now()).thenReturn(Calendar.getInstance());
		loggedUsers.passedBy("tenant1", "mauricio");
		
		Calendar twoMinutesLater = Calendar.getInstance();
		twoMinutesLater.add(Calendar.MINUTE, 2);
		when(clock.now()).thenReturn(twoMinutesLater);
		
		assertTrue(loggedUsers.isActive("tenant1", "mauricio"));
	}
	
	@Test
	public void shouldNotBeAvailableIfLimitTimePassed() {
		when(clock.now()).thenReturn(Calendar.getInstance());
		loggedUsers.passedBy("tenant1", "mauricio");
		
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		
		when(clock.now()).thenReturn(tomorrow);
		
		assertFalse(loggedUsers.isActive("tenant1", "mauricio"));
	}
	
	
}
