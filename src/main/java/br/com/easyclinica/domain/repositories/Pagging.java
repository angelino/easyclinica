package br.com.easyclinica.domain.repositories;

import java.util.List;

public interface Pagging<T> {
	List<T> get(int firstResult, int maxResults);
	List<T> search(String textToSearch, int firstResult, int maxResults);
	
	int count();
	int count(String textToSearch);
}
