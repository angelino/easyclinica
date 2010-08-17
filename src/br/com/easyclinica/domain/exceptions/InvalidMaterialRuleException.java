package br.com.easyclinica.domain.exceptions;

import br.com.easyclinica.domain.entities.Material;

public class InvalidMaterialRuleException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidMaterialRuleException(Material material) {
		super("Servi�o " + material.getName() + " j� possui uma regra diferenciada!");
	}
}
