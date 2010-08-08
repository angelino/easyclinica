package br.com.easyclinica.tests.helpers;

import br.com.easyclinica.domain.types.Address;
import br.com.easyclinica.domain.types.City;
import br.com.easyclinica.domain.types.Neighborhood;
import br.com.easyclinica.domain.types.PostalCode;
import br.com.easyclinica.domain.types.State;
import br.com.easyclinica.domain.types.Street;

public class AnExampleOf {

	public static Address address() {
		return new Address(
				new Street("street"), 
				new Neighborhood("neighborhood"), 
				new PostalCode("123"), 
				new City("city"), 
				new State("SP"));
	}
}
