package br.com.easyclinica.domain.validators;

import java.util.List;

public interface Validator<T> {
	List<Error> validate(T obj);
}
