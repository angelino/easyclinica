package br.com.easyclinica.domain.validators;

import java.math.BigDecimal;

import br.com.easyclinica.domain.entities.IDable;
import br.com.easyclinica.domain.types.Money;

public class ValidatorUtils {
	
	public static boolean isNullOrEmpty(String value) 
	{
		if(value == "" || value == null) return true;		
		return false;
	}
	
	public static boolean isMoreThanZero(double value)
	{
		if(value > 0) return true;
		
		return false;
	}
	
	public static boolean isMoreThanZero(Money value)
	{
		if(value.getAmount().compareTo(new BigDecimal(0.0)) > 0 ) return true;
		
		return false;
	}

	public static boolean exists(IDable entity) {
		return entity != null && entity.getId() > 0;
	}
	
}
