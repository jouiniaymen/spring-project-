package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Medecin;
import com.example.demo.repository.MedecinRepository;

@RestController
@RequestMapping(value="medecin")
public class MedecinController {
	
	@Autowired
    MedecinRepository medecinRepository;
	
    @GetMapping(path= "/user/medecins")
    public String medecin (Model model,
                           @RequestParam(name= "page", defaultValue = "0") int page,
                           @RequestParam(name="size", defaultValue = "5") int size,
                           @RequestParam(name="keyword", defaultValue = "") String keyword){
        Page<Medecin> pagemedecins= medecinRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listMedecins",pagemedecins);
        model.addAttribute("pages", new int[pagemedecins.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword",keyword);
        return "medecins";
    }
    
    @GetMapping(path="/admin/formMedecin")
    public String formMedecin(Model model){
        model.addAttribute("medecin", new Medecin());
        return "medecin/fromMedecin";
    }
    
    @GetMapping(path="/admin/deleteMedecin")
    public String deleteMedecin(Long id, String keyword, int page){
        medecinRepository.deleteById(id);
        return "redirect:/user/medecins?page="+page+"&keyword="+keyword;
    }

    @PostMapping(path="/admin/saveMedecin")
    public String saveMedecin(
                       @RequestBody Medecin medecin,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
       
        medecinRepository.save(medecin);
        return "bbb";
    }
    
    @GetMapping(path="/admin/EditMedecin/{id}")
    public String EditMedecin(@PathVariable Long id,             
            @RequestBody Medecin medecin){
        Medecin medecinupdate = medecinRepository.findById(id).orElse(null);
        if(medecin==null) throw new RuntimeException("Medecin introuvable");
        
        if(medecin.getNom().length()>0) {medecinupdate.setNom(medecin.getNom());}
        if(medecin.getEmail().length()>0) {medecinupdate.setEmail(medecin.getEmail());}
        if(medecin.getSpecialite().length()>0) {medecinupdate.setSpecialite(medecin.getSpecialite());}
        medecinRepository.save(medecinupdate);


        return "Medecin/EditMedecin";
    }
}