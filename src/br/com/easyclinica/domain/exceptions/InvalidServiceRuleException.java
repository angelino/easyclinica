package br.com.easyclinica.domain.exceptions;

import br.com.easyclinica.domain.entities.Service;

public class InvalidServiceRuleException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidServiceRuleException(Service service) {
		super("Servi�o " + service.getName() + " j� possui uma regra diferenciada!");
	}
}
