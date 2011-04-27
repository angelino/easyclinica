package br.com.easyclinica.config;

import java.util.HashMap;

public class HardCodedConfigForTests implements Config{

	private HashMap<String, String> cfgs;

	public HardCodedConfigForTests() {
		cfgs = new HashMap<String, String>();
		
		cfgs.put("connection_string", "jdbc:mysql://localhost:3306/#database#?useUnicode=true&characterEncoding=UTF-8");
		cfgs.put("driver_class", "com.mysql.jdbc.Driver");
		cfgs.put("db_pwd", "3@sycl1n1c@");
		
		cfgs.put("elements_per_page", "10");
		cfgs.put("env", "test");
		
	}
	
	public String get(String key) {
		return cfgs.get(key);
	}

}
