package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

public class Modeldipso {

	private Medecin medecin;
	List<LocalDateTime> dates;
	public Medecin getMedecin() {
		return medecin;
	}
	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}
	public List<LocalDateTime> getDates() {
		return dates;
	}
	public void setDates(List<LocalDateTime> dates) {
		this.dates = dates;
	}
	
}
