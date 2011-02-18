package br.com.easyclinica.domain.validators;

import java.math.BigDecimal;

import br.com.easyclinica.domain.entities.IDable;

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
	
	public static boolean isMoreThanZero(BigDecimal value)
	{
		if(value.compareTo(BigDecimal.ZERO) > 0 ) return true;
		
		return false;
	}

	public static boolean exists(IDable entity) {
		return entity != null && entity.getId() > 0;
	}
	
}
