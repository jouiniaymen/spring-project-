package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Medecin;
import com.example.demo.model.Modeldipso;
import com.example.demo.model.Modelrdv;
import com.example.demo.model.Patient;
import com.example.demo.model.RendezVous;
import com.example.demo.repository.MedecinRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.RendezVousRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RDVController {
	@Autowired
	RendezVousRepository rendezVousRepository;
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	MedecinRepository medecinRepository;
	// ICabinetImpl hopitalService;

	@GetMapping(path = "user/rdv")
	public String Rendezvous(Model model, @RequestParam(name = "page", defaultValue = "0") int page, // parametre d'url
																										// :
																										// request.getparametre(page),
																										// si on
																										// specifie pas
																										// le parametre
																										// il va prendre
																										// la valeur 0
																										// par defaut
			@RequestParam(name = "size", defaultValue = "5") int size

	) {
		Page<RendezVous> pageRDV = (Page<RendezVous>) rendezVousRepository.findAll();
		model.addAttribute("listRDV", pageRDV.getContent()); // getcontent donne la liste des patients de la page
		model.addAttribute("pages", new int[pageRDV.getTotalPages()]);
		model.addAttribute("currentPage", page);
		return "RDV/Rendezvous";// nom de la vue
	}

	@GetMapping(path = "/admin/formRendezVous")
	public String formRendezVous(Model model/* ,String nomPatient,String nomMedecin */) {
		model.addAttribute("rendezvous", new RendezVous());
		/*
		 * model.addAttribute("nomPatient",nomPatient);
		 * model.addAttribute("nomMedecin",nomMedecin);
		 */
		model.addAttribute("patients", patientRepository.findAll());
		model.addAttribute("medecins", medecinRepository.findAll());
		return "RDV/formRDV";
	}

	@PostMapping(path = "/admin/saveRDV")
	public void saveRDV(Model model, @RequestBody Modelrdv modelrdv, BindingResult bindingResult, // =>stock les erreurs
			@RequestParam(defaultValue = "0") int page) {

		RendezVous rendezVous = new RendezVous();
		Patient patient = patientRepository.findById(modelrdv.getIdpatient()).orElse(null);
		Medecin medecin = medecinRepository.findById(modelrdv.getIdmedci()).orElse(null);
		rendezVous.setPatient(patient);
		rendezVous.setMedecin(medecin);
		rendezVous.setDate(modelrdv.getDaterdv());
		rendezVousRepository.save(rendezVous);
		;

		;
		;
		;
		;
		;
		;
		;
		;
		;
		;
		;
		;
		;

	}

	@GetMapping(path = "/admin/deleteRDV/{id}")
	public void deleteRDV(@PathVariable Long id) {
		rendezVousRepository.deleteById(id);
	}

	@GetMapping(path = "/admin/EditRDV/{id}")
	public String EditRDV(Model model, @PathVariable Long id) {
		RendezVous rendezVous = rendezVousRepository.findById(id).orElse(null); // avec .get je le recuper s'il existe
																				// mais on peut utiliser orElse(null)
																				// null s'il ne trouve pas le patient
		if (rendezVous == null)
			throw new RuntimeException("Rendez-vous introuvable");
		model.addAttribute("rendezVous", rendezVous);
		return "RDV/EditRDV";
	}

	@GetMapping(path = "/admin/findmeddispo/{spec}")
	public List<Modeldipso> getmedcinbyspecialite( @PathVariable String spec) {
		List<Medecin> medecin = medecinRepository.findByspecialite(spec); // avec .get je le recuper s'il existe mais on
																			// peut utiliser orElse(null) null s'il ne
		List<Modeldipso> model =new ArrayList<>();															// trouve pas le patient
		HashMap<Medecin, List<LocalDateTime>> MedNonDispo = new HashMap<Medecin, List<LocalDateTime>>();
		for (Medecin Med : medecin) {
			Modeldipso modelRModel =new Modeldipso();
			List<LocalDateTime> date = new ArrayList();
			List<RendezVous> rdvList = rendezVousRepository.findByMedecin(Med);
			for (RendezVous v : rdvList) {
				if (LocalDateTime.now().isBefore(v.getDate())) {
					date.add(v.getDate());
				

				}
			}

			modelRModel.setMedecin( Med);
			modelRModel.setDates(date);	

			model.add(modelRModel);
		}
		return model;

	}

}
