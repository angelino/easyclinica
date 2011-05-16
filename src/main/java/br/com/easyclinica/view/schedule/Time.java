package br.com.easyclinica.view.schedule;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import br.com.easyclinica.domain.entities.Schedule;

public class Time {
	
	private Calendar time;
	private List<Schedule> commitments;

	public Time(Calendar time) {
		this.time = time;
		
		this.commitments = new LinkedList<Schedule>();
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	public Calendar getTime() {
		return time;
	}

	public void setCommitments(List<Schedule> commitments) {
		this.commitments = commitments;
	}

	public List<Schedule> getCommitments() {
		return commitments;
	}
	
	public void addCommitment(Schedule schedule) {
		this.commitments.add(schedule);
	}
}
