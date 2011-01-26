package br.com.easyclinica.infra.multitenancy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.infra.web.HttpRequestWrapper;
import br.com.easyclinica.tests.helpers.ClinicBuilder;
import br.com.easyclinica.tests.helpers.EmployeeBuilder;

public class TenantTests {

	private TenantUrlParser parser;
	private HttpRequestWrapper request;
	private Tenant tenant;
	private Clinic clinic;
	private Employee employee;

	@Before
	public void setUp() {
		parser = mock(TenantUrlParser.class);
		request = mock(HttpRequestWrapper.class);
		
		tenant = new Tenant(request, parser);
		
		clinic = new ClinicBuilder().instance();
		employee = new EmployeeBuilder().instance();
	}
	
	@Test
	public void shouldGetDomainFromUrl() {
		when(request.getUrl()).thenReturn("url");
		when(parser.parse("url")).thenReturn("clinicadavila");
		
		assertEquals("clinicadavila", tenant.getDomain());
	}
	
	@Test
	public void shouldLogATenant() {
		tenant.set(clinic, employee);
		
		assertSame(clinic, tenant.getClinic());
		assertSame(employee, tenant.getEmployee());
	}
	
	@Test
	public void shouldNotGetDomainFromUrlIfIsAlreadyLogged() {
		when(request.getUrl()).thenReturn("url");
		when(parser.parse("url")).thenReturn("clinicadavila");
		
		assertEquals("clinicadavila", tenant.getDomain());
		
		tenant.set(clinic, employee);
		
		when(parser.parse("url")).thenReturn("clinicadavila2");
		assertEquals("clinicadavila", tenant.getDomain());
	}
}
