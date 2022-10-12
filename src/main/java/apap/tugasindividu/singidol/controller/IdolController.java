package apap.tugasindividu.singidol.controller;

import apap.tugasindividu.singidol.model.IdolModel;
import apap.tugasindividu.singidol.service.IdolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IdolController {

    @Qualifier("idolServiceImpl")
    @Autowired
    private IdolService idolService;

    @GetMapping("/idol")
    public String listIdol(Model model) {
        List<IdolModel> listIdol = idolService.getListIdol();
        model.addAttribute("listIdol", listIdol);
        return "idol/viewall-idol";
    }

    @GetMapping("/idol/tambah")
    public String addIdolFormPage(Model model) {
        IdolModel idol = new IdolModel();
        model.addAttribute("idol", idol);

        return "idol/form-add-idol";
    }

    @PostMapping("/idol/tambah")
    public String addKonserSubmitPage(@ModelAttribute IdolModel idol, Model model) {

        idolService.addIdol(idol);
        model.addAttribute("namaIdol", idol.getNamaIdol());

        return "idol/add-idol";
    }

    @GetMapping("/idol/{idIdol}")
    public String viewDetailIdolPage(@PathVariable(value = "idIdol") Long idIdol, Model model) {
        IdolModel idol = idolService.getIdolByIdIdol(idIdol);

        model.addAttribute("idol", idol);
        return "idol/view-idol";
    }

}
