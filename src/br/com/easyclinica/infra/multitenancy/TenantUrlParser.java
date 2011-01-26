package br.com.easyclinica.infra.multitenancy;

public interface TenantUrlParser {

	String parse(String url);

}