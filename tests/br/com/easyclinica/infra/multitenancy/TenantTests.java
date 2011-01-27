package br.com.easyclinica.infra.multitenancy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.tests.helpers.ClinicBuilder;
import br.com.easyclinica.tests.helpers.EmployeeBuilder;

public class TenantTests {

	private LoggedUser loggedUser;
	private TenantUrlParser parser;
	private Tenant tenant;

	@Before
	public void setUp() {
		
		loggedUser = new LoggedUser();
		parser = mock(TenantUrlParser.class);
		tenant = new Tenant(parser, loggedUser);

		when(parser.parse()).thenReturn("fromUrl");
	}
	
	@Test
	public void shouldGetDomainFromUrlIfUserIsNotLogged() {
		assertEquals("fromUrl", tenant.getDomain());
	}
	
	@Test
	public void shouldGetFromLoggedClinic() {
		
		Clinic clinic = new ClinicBuilder().withDomain("fromClinic").instance();
		Employee employee = new EmployeeBuilder().instance();
		
		loggedUser.set(clinic, employee);
		
		assertEquals("fromClinic", tenant.getDomain());
	}
}
