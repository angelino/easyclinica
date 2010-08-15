package br.com.easyclinica.domain.repositories;

import java.util.List;

public interface Pagging<T> {
	List<T> get(int firstResult, int maxResults);
	int count();
}
