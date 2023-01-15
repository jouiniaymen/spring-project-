package service;

import com.example.demo.model.Consultation;
import com.example.demo.model.Medecin;
import com.example.demo.model.Patient;
import com.example.demo.model.RendezVous;

public interface ICbinetService {

	
	Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
