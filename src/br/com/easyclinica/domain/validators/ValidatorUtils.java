package br.com.easyclinica.domain.validators;

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
	
}
