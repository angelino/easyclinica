package br.com.easyclinica.infra.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.easyclinica.domain.entities.HealthCarePlan;
import br.com.easyclinica.tests.helpers.HealthCarePlanBuilder;

public class HealthCarePlanDaoTests extends BaseIntegrationTests {
	private HealthCarePlanDao dao;
	
	@Before
	public void setUp() {
		dao = new HealthCarePlanDao(session);
	}
	
	@Test
	public void shouldAdd() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		dao.add(plan);
		
		List<HealthCarePlan> list = dao.get();
		assertEquals(1, list.size());
		
		HealthCarePlan newOne = list.get(0);
		assertEquals(plan.getName(), newOne.getName());
		assertEquals(plan.getAddress().getStreet(), newOne.getAddress().getStreet());
		assertEquals(plan.getAddress().getNeighborhood(), newOne.getAddress().getNeighborhood());
		assertEquals(plan.getAddress().getPostalCode(), newOne.getAddress().getPostalCode());
		assertEquals(plan.getAddress().getCity(), newOne.getAddress().getCity());
		assertEquals(plan.getAddress().getState(), newOne.getAddress().getState());
		assertEquals(plan.getTelephone(), newOne.getTelephone());
		assertEquals(plan.getEmail(), newOne.getEmail());
		assertEquals(plan.getWebsite(), newOne.getWebsite());
		assertEquals(plan.getContact(), newOne.getContact());
		assertEquals(plan.getObservations(), newOne.getObservations());
		assertEquals(plan.getCh(), newOne.getCh(), 0.001);
		assertEquals(plan.isActive(), newOne.isActive());
	}

	@Test
	public void shouldUpdate() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		dao.add(plan);
		
		HealthCarePlan updatedPlan = new HealthCarePlanBuilder(plan.getId())
			.withName("new Amil")
			.instance();
		dao.update(updatedPlan);
		
		HealthCarePlan secondRetrievedPlan = dao.getById(plan.getId());
		assertNotNull(secondRetrievedPlan);
		assertEquals("new Amil", secondRetrievedPlan.getName());
	}
	
	@Test
	public void shouldGetById() {
		HealthCarePlan plan = new HealthCarePlanBuilder().instance();
		dao.add(plan);
		
		HealthCarePlan retrievedPlan = dao.getById(plan.getId());
		
		assertNotNull(retrievedPlan);
		assertEquals(plan.getId(), retrievedPlan.getId());
	}
	
	@Test
	public void shouldCountElements() {
		HealthCarePlan firstPlan = new HealthCarePlanBuilder().instance();
		HealthCarePlan secondPlan = new HealthCarePlanBuilder().instance();
		
		dao.add(firstPlan);
		dao.add(secondPlan);
		
		assertEquals(2, dao.count());
	}
	
	@Test
	public void shouldPaginate() {
		HealthCarePlan firstPlan = new HealthCarePlanBuilder().withName("a").instance();
		HealthCarePlan secondPlan = new HealthCarePlanBuilder().withName("b").instance();
		
		dao.add(firstPlan);
		dao.add(secondPlan);
		
		assertEquals(firstPlan.getName(), dao.get(0, 1).get(0).getName());
	}
}
