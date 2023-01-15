package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Consultation;
import com.example.demo.model.Medecin;
import com.example.demo.model.Patient;
import com.example.demo.model.RendezVous;
import com.example.demo.repository.ConsultationRepository;
import com.example.demo.repository.MedecinRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.RendezVousRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import com.example.demo.model.Patient;


@AllArgsConstructor
@Getter
@Setter
@Service
public class ICabinetImpl implements ICbinetService {
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
    private MedecinRepository medecinRepository;
	@Autowired
    private RendezVousRepository rendezVousRepository;
	@Autowired
    private ConsultationRepository consultationRepository;

    public  void IHospitalServiceImpl(PatientRepository patientRepository,
                                MedecinRepository medecinRepository,
                                RendezVousRepository rendezVousRepository,
                                ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository=medecinRepository;
        this.rendezVousRepository=rendezVousRepository;
        this.consultationRepository=consultationRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save((patient));
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save((medecin));
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        if(rendezVous.getPatient()==null){
            Patient patient= patientRepository.findById((Math.random()>0.5)? 1L:2L).get();
            rendezVous.setPatient(patient);
        }
        if(rendezVous.getMedecin()==null){
            Medecin medecin= medecinRepository.findById((Math.random()>0.5)? ((Math.random()>0.5)? 4L:5L):((Math.random()>0.5)? 1L:2L)).get();
            rendezVous.setMedecin(medecin);
        }


        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        if(consultation.getRendezVous()==null) {
     RendezVous rendezVous= rendezVousRepository.findById((Math.random()>0.5)? ((Math.random()>0.5)? 4L:5L):((Math.random()>0.5)? 1L:2L)).get();
            consultation.setRendezVous(rendezVous);}
        return consultationRepository.save(consultation);
    }



}
