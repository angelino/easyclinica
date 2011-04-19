package br.com.easyclinica.domain.validators;

import java.util.List;

public interface EntityValidator<T> {
	List<Error> validate(T obj);
}
