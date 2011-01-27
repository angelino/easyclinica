package br.com.easyclinica.infra.multitenancy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.Clinic;
import br.com.easyclinica.domain.entities.Employee;
import br.com.easyclinica.tests.helpers.ClinicBuilder;
import br.com.easyclinica.tests.helpers.EmployeeBuilder;

public class TenantTests {

	private Tenant tenant;
	private Clinic clinic;
	private Employee employee;

	@Before
	public void setUp() {
		
		tenant = new Tenant();
		
		clinic = new ClinicBuilder().withDomain("clinicadavila").instance();
		employee = new EmployeeBuilder().instance();
	}
	
	@Test
	public void shouldAcceptATemporaryDomain() {
		tenant.setTempDomain("clinicadavila");
		assertEquals("clinicadavila", tenant.getDomain());
	}
	
	@Test
	public void shouldLogATenant() {
		tenant.set(clinic, employee);
		
		assertSame(clinic, tenant.getClinic());
		assertSame(employee, tenant.getEmployee());
	}
	
	@Test
	public void shouldNotGetDomainFromUserIfIsAlreadyLogged() {
		tenant.setTempDomain("clinicadavila");
		assertEquals("clinicadavila", tenant.getDomain());

		tenant.setTempDomain("clinicadavila");
		assertEquals("clinicadavila", tenant.getDomain());

		tenant.set(clinic, employee);

		tenant.setTempDomain("xyz");
		assertEquals("clinicadavila", tenant.getDomain());
	}
	
	@Test
	public void shouldBeLogged() {
		assertFalse(tenant.isLogged());
		
		tenant.set(clinic, employee);
		
		assertTrue(tenant.isLogged());
	}
}
