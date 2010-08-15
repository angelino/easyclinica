package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.entities.Service;
import br.com.easyclinica.domain.types.CH;
import br.com.easyclinica.domain.types.Name;

public class ServiceBuilder {

	private Service service;
	
	public ServiceBuilder() {
		service = new Service(0, new Name("some service"), new CH(10));
	}
	
	public Service instance() {
		return service;
	}

	public ServiceBuilder withId(int id) {
		service = new Service(id, service.getName(), service.getCh());
		return this;
	}
}
