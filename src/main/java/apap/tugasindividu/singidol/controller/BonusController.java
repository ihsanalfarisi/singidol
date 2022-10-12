package apap.tugasindividu.singidol.controller;

import apap.tugasindividu.singidol.model.KonserModel;
import apap.tugasindividu.singidol.service.BonusService;
import apap.tugasindividu.singidol.service.KonserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BonusController {

    @Qualifier("bonusServiceImpl")
    @Autowired
    private BonusService bonusService;

    @Qualifier("konserServiceImpl")
    @Autowired
    private KonserService konserService;

    @GetMapping("/bonus/konser/top")
    public String getTipePage(Model model) {
        return "top-page";
    }

    @GetMapping("/bonus/konser/top/{namaTipe}")
    public String getTipe(@PathVariable(value = "namaTipe") String namaTipe, Model model) {
        Long idKonser = bonusService.getTop(namaTipe);
        KonserModel konser = konserService.getKonserByIdKonser(idKonser);
        model.addAttribute("konser", konser);
        if (konser != null) return "top";
        else return "error-top";
    }
}