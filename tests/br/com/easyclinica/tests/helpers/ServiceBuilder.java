package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Service;
import br.com.easyclinica.domain.entities.ServicesTable;
import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Name;

public class ServiceBuilder {

	private Service service;
	
	public ServiceBuilder(ServicesTable table) {
		service = new Service(0, table, new Name("some service"), new CH(10));
	}
	
	public Service instance() {
		return service;
	}

	public ServiceBuilder withId(int id) {
		service = new Service(id, service.getTable(), service.getName(), service.getCh());
		return this;
	}
}
