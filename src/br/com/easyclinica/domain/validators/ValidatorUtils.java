package br.com.easyclinica.domain.validators;

import java.math.BigDecimal;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.vraptor.ioc.Component;
import br.com.easyclinica.domain.entities.IDable;

@Component
public class ValidatorUtils {

	private final CPFValidator cpfValidator;
	
	public ValidatorUtils(CPFValidator cpfValidator) {
		this.cpfValidator = cpfValidator;
	}
	
	public boolean isNullOrEmpty(String value) 
	{
		if(value == "" || value == null) return true;		
		return false;
	}
		
	public boolean isMoreThanZero(double value)
	{
		if(value > 0) return true;
		
		return false;
	}
	
	public boolean isMoreThanZero(BigDecimal value)
	{
		if(value.compareTo(BigDecimal.ZERO) > 0 ) return true;
		
		return false;
	}

	public boolean exists(IDable entity) {
		return entity != null && entity.getId() > 0;
	}
	
	public boolean cpfIsValid(String cpf) {
		try {
			cpfValidator.assertValid(cpf);
			return false;
		}
		catch(Exception e) {
			return true;
		}
	}
}
