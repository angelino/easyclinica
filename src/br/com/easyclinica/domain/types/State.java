package br.com.easyclinica.domain.types;

import javax.persistence.Embeddable;

@Embeddable
public class State {
	private String state;
	
	protected State() {}
	public State(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
	
	public String toString() {
		return getState();
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		
		if( !(obj instanceof State) ) return false;
		
		State other = (State)obj;
		
		return this.getState() == other.getState();
	}
	
	@Override
	public int hashCode() {
		return state.hashCode();
	}

}
