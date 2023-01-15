package com.example.demo.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Modelreservation {

	Long idmecin;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime daterdv;
	Long idpatient;
	public Long getIdmecin() {
		return idmecin;
	}
	public void setIdmecin(Long idmecin) {
		this.idmecin = idmecin;
	}
	public LocalDateTime getDaterdv() {
		return daterdv;
	}
	public void setDaterdv(LocalDateTime daterdv) {
		this.daterdv = daterdv;
	}
	public Long getIdpatient() {
		return idpatient;
	}
	public void setIdpatient(Long idpatient) {
		this.idpatient = idpatient;
	}
	
	
	
}
