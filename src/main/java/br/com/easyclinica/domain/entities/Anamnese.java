package br.com.easyclinica.domain.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;

@Entity
public class Anamnese {

	@Id
	@GeneratedValue
	private int id;
	private Calendar date;
	@Type(type = "text")
	private String complaintAndDuration;
	@Type(type = "text")
	private String hpma;
	@Type(type = "text")
	private String hsda;
	@Type(type = "text")
	private String hf;
	@Type(type = "text")
	private String clinicExam;
	@Type(type = "text")
	private String supplementaryExam;
	@Type(type = "text")
	private String hypothesis;
	@ManyToOne(fetch=FetchType.EAGER)
	private CID cid;
	@Type(type = "text")
	private String conduct;
	@ManyToOne(fetch = FetchType.EAGER)
	private Doctor doctor;
	@ManyToOne(fetch = FetchType.LAZY)
	@Index(name = "anamnesePatientIndex")
	private Patient patient;

	public Anamnese(int id) {
		this.id = id;
	}

	public Anamnese() {
		this(0);
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public static Anamnese empty() {
		return new Anamnese();
	}

	public String getComplaintAndDuration() {
		return complaintAndDuration;
	}

	public void setComplaintAndDuration(String complaintAndDuration) {
		this.complaintAndDuration = complaintAndDuration;
	}

	public String getHpma() {
		return hpma;
	}

	public void setHpma(String hpma) {
		this.hpma = hpma;
	}

	public String getHsda() {
		return hsda;
	}

	public void setHsda(String hsda) {
		this.hsda = hsda;
	}

	public String getHf() {
		return hf;
	}

	public void setHf(String hf) {
		this.hf = hf;
	}

	public String getClinicExam() {
		return clinicExam;
	}

	public void setClinicExam(String clinicExam) {
		this.clinicExam = clinicExam;
	}

	public String getSupplementaryExam() {
		return supplementaryExam;
	}

	public void setSupplementaryExam(String supplementaryExam) {
		this.supplementaryExam = supplementaryExam;
	}

	public String getHypothesis() {
		return hypothesis;
	}

	public void setHypothesis(String hypothesis) {
		this.hypothesis = hypothesis;
	}

	public CID getCid() {
		return cid;
	}

	public void setCid(CID cid) {
		this.cid = cid;
	}

	public String getConduct() {
		return conduct;
	}

	public void setConduct(String conduct) {
		this.conduct = conduct;
	}

	
}
