package br.com.easyclinica.domain.validators;

public class Error {

	private final String category;
	private final String key;

	public Error(String category, String key) {
		this.category = category;
		this.key = key;
	}

	public String getCategory() {
		return category;
	}

	public String getKey() {
		return key;
	}
}
