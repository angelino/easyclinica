package br.com.easyclinica.domain.repositories;

import br.com.easyclinica.domain.entities.Employee;

public interface AllEmployees {

	Employee getByLogin(String login);

}
