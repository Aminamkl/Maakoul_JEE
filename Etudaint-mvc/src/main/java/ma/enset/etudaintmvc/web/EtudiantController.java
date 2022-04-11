package ma.enset.etudaintmvc.web;

import lombok.AllArgsConstructor;
import ma.enset.etudaintmvc.entities.Etudiant;
import ma.enset.etudaintmvc.repositories.EtudiantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;


@Controller
@AllArgsConstructor
public class EtudiantController {
    private EtudiantRepository etudiantRepository;

    @GetMapping(path = "/user/index")
    public String etudaints(Model model, @RequestParam(name = "page", defaultValue = "0") int page
            , @RequestParam(name = "size", defaultValue = "5") int size,
             @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Etudiant> pageEtudiants = etudiantRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listEtudiants", pageEtudiants.getContent());
        model.addAttribute("pages", new int[pageEtudiants.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "etudaints";
    }


    @GetMapping(path = "/admin/delete")
    public String delete(Long id,String keyword, int page) {
        etudiantRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword ;
    }

    @GetMapping(path = "/")
    public String home() {
        return "home";
    }

    @GetMapping("/user/etudiants")
    @ResponseBody
    public List<Etudiant> listPatients(){
        return etudiantRepository.findAll();
    }

    @GetMapping("/admin/formEtudiant")
    public String formPatient(Model model){
        model.addAttribute("etudiant",new Etudiant());
        return "FormEtudiant";
    }

    @PostMapping(path="/admin/save")
    public String save(Model model, @Valid Etudiant etudiant, BindingResult bindingResult,
                       @RequestParam(defaultValue = "") String keyword,@RequestParam(defaultValue = "0") int page){
       if(bindingResult.hasErrors()) return "FormEtudiant";
        etudiantRepository.save(etudiant);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editEtudiant")
    public String editEtudiant(Model model, Long id,String keyword, int page){
        Etudiant etudiant= etudiantRepository.findById(id).orElse(null);
        if(etudiant==null) throw new RuntimeException("Etudiant introuvable");
        model.addAttribute("etudiant",etudiant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editEtudiant";
    }

}
