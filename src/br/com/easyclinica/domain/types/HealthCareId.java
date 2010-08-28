package br.com.easyclinica.domain.types;


public class HealthCareId {

	private String healthCareId;

	protected HealthCareId() {}
	public HealthCareId(String healthCareId) {
		this.healthCareId = healthCareId;
	}

	public String getHealthCareId() {
		return healthCareId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof HealthCareId) ) return false;
		
		HealthCareId other = (HealthCareId)obj;
		
		return this.getHealthCareId() == other.getHealthCareId();
	}
	
	@Override
	public int hashCode() {
		return healthCareId.hashCode();
	}
	
	public String toString() {
		return getHealthCareId();
	}
	public static HealthCareId empty() {
		return new HealthCareId("");
	}


}
