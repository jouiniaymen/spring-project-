package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Modelrdv {
	Long idmedci;
	Long idpatient;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")

	LocalDateTime daterdv;
	public Long getIdmedci() {
		return idmedci;
	}
	public void setIdmedci(Long idmedci) {
		this.idmedci = idmedci;
	}
	public Long getIdpatient() {
		return idpatient;
	}
	public void setIdpatient(Long idpatient) {
		this.idpatient = idpatient;
	}
	public LocalDateTime getDaterdv() {
		return daterdv;
	}
	public void setDaterdv(LocalDateTime daterdv) {
		this.daterdv = daterdv;
	}
}
