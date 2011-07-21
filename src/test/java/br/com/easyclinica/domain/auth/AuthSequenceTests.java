package br.com.easyclinica.domain.auth;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Employee;

public class AuthSequenceTests {

	private Authorizer auth1;
	private Authorizer auth2;
	private Authorizer auth3;

	@Before
	public void setUp() {
		auth1 = mock(Authorizer.class);
		auth2 = mock(Authorizer.class);
		auth3 = mock(Authorizer.class);
	}
	
	@Test
	public void shouldRunTheAuthorizerIfItIsAloneAndReturnItsReturn() {
		when(auth1.allows(any(Class.class), any(Employee.class))).thenReturn(true);
		
		AuthSequence sequence = new AuthSequence(Arrays.asList(auth1));
		
		boolean authorized = sequence.checkPermissionsFor(any(Class.class), any(Employee.class));
		
		assertTrue(authorized);
	}
	
	@Test
	public void shouldRunAllOfThemAndStopAtTheFirstFalse() {
		when(auth1.allows(any(Class.class), any(Employee.class))).thenReturn(true);
		when(auth2.allows(any(Class.class), any(Employee.class))).thenReturn(false);
		when(auth3.allows(any(Class.class), any(Employee.class))).thenReturn(true);
		
		AuthSequence sequence = new AuthSequence(Arrays.asList(auth1, auth2, auth3));
		
		boolean authorized = sequence.checkPermissionsFor(any(Class.class), any(Employee.class));
		
		assertFalse(authorized);
		
		verify(auth1).allows(any(Class.class), any(Employee.class));
		verify(auth2).allows(any(Class.class), any(Employee.class));
		verify(auth3, times(0)).allows(any(Class.class), any(Employee.class));
	}
	
	@Test
	public void shouldReturnTrueIfAllOfThemReturnTrue() {
		when(auth1.allows(any(Class.class), any(Employee.class))).thenReturn(true);
		when(auth2.allows(any(Class.class), any(Employee.class))).thenReturn(true);
		when(auth3.allows(any(Class.class), any(Employee.class))).thenReturn(true);
		
		AuthSequence sequence = new AuthSequence(Arrays.asList(auth1, auth2, auth3));
		
		boolean authorized = sequence.checkPermissionsFor(any(Class.class), any(Employee.class));
		
		assertTrue(authorized);
		
		verify(auth1).allows(any(Class.class), any(Employee.class));
		verify(auth2).allows(any(Class.class), any(Employee.class));
		verify(auth3).allows(any(Class.class), any(Employee.class));
	}
}
