package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class Name implements GeneralType {
	private String name;
	
	protected Name() {}
	public Name(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return getName();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof Name) ) return false;
		
		Name other = (Name)obj;
		
		return this.getName() == other.getName();
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	public static Name empty() {
		return new Name("");
	}
	
	public boolean isValid() {
		
		if(this.name == null || this.name.length() == 0) return false;
		
		return true;
	}
}
