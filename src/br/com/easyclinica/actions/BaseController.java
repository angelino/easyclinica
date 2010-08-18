package br.com.easyclinica.actions;

import br.com.caelum.vraptor.Result;

public class BaseController {

	public static final String SUCCESS_KEY = "successKey";
	public static final String ERROR_KEY = "errorKey";
	
	protected Result result;

	public BaseController(Result result) {
		this.result = result;
	}
	
	public void successMsg(String key) {
		result.include(SUCCESS_KEY, key);
	}
	
	public void errorMsg(String key) {
		result.include(ERROR_KEY, key);
	}
}
