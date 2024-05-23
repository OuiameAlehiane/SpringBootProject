package com.emsi.appmed.web;


import com.emsi.appmed.entities.Patient;

import com.emsi.appmed.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@AllArgsConstructor
@Controller
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/indexPatient")
    public String indexPatient(Model model,
                               @RequestParam(name = "page",defaultValue = "0") int page,
                               @RequestParam(name = "size",defaultValue = "3") int size,
                               @RequestParam(name = "keyword",defaultValue = "") String kw){
        Page<Patient> patientPage = patientRepository.findByNomContains(kw, PageRequest.of(page,size));
        model.addAttribute("listPatients",patientPage.getContent());
        model.addAttribute("pages",new int[patientPage.getTotalPages()]);// collecter un tableau avec une size de nombre de page
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        return "patients";
    }
    @GetMapping("/deletePatient")
    public String deletePatient(@RequestParam(name = "id") int id,
                         @RequestParam(name ="keyword",defaultValue ="") String keyword,
                         @RequestParam(name ="page",defaultValue ="0") int page){
        patientRepository.deleteById(id);
        return "redirect:/indexPatient?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/Patient")
    public String index(){
        return "redirect:/indexPatient";
    }
    @GetMapping("/editPatient")
    public String editPatient(@RequestParam("id") int id, Model model) {
        Patient patient = patientRepository.findById(id).orElse(null);
        model.addAttribute("patient", patient);
        return "formPatient";
    }

    @GetMapping("/formPatient")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }
    @PostMapping("/savePatient")
    public String saveEtudiant(Patient patient){
        patientRepository.save(patient);
        return "redirect:/Patient";
    }
}
