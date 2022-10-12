package apap.tugasindividu.singidol.controller;

import apap.tugasindividu.singidol.model.IdolModel;
import apap.tugasindividu.singidol.model.KonserModel;
import apap.tugasindividu.singidol.model.PenampilanKonserModel;
import apap.tugasindividu.singidol.service.IdolService;
import apap.tugasindividu.singidol.service.KonserService;
import apap.tugasindividu.singidol.service.PenampilanKonserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class KonserController {

    @Qualifier("konserServiceImpl")
    @Autowired
    private KonserService konserService;

    @Qualifier("idolServiceImpl")
    @Autowired
    private IdolService idolService;

    @Qualifier("penampilanKonserServiceImpl")
    @Autowired
    private PenampilanKonserService penampilanKonserService;

    @GetMapping("/konser")
    public String listKonser(Model model) {
        List<KonserModel> listKonser = konserService.getListKonser();
        model.addAttribute("listKonser", listKonser);
        return "konser/viewall-konser";
    }

    @GetMapping("/konser/tambah")
    public String addKonserFormPage(Model model) {
        KonserModel konser = new KonserModel();
        List<IdolModel> listIdol = idolService.getListIdol();
        List<PenampilanKonserModel> listTampilNew = new ArrayList<>();

        konser.setListTampil(listTampilNew);
        konser.getListTampil().add(new PenampilanKonserModel());

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);

        return "konser/form-add-konser";
    }

    @PostMapping("/konser/tambah")
    public String addKonserSubmitPage(@ModelAttribute KonserModel konser, Model model) {
        if (konser.getListTampil() == null) {
            konser.setListTampil(new ArrayList<>());
        }

        List<PenampilanKonserModel> listIdolKonser = konser.getListTampil();
        for (PenampilanKonserModel penampilan : listIdolKonser) {
            penampilan.setKonser(konser);
        }
        konser.setListTampil(listIdolKonser);

        konser.setTotalPendapatan(0L);
        konserService.addKonser(konser);

        model.addAttribute("namaKonser", konser.getNamaKonser());

        return "konser/add-konser";
    }

    @GetMapping("/konser/{idTiket}")
    public String viewDetailKonserPage(@PathVariable(value = "idTiket") Long idTiket, Model model) {
        KonserModel konser = konserService.getKonserByIdKonser(idTiket);

        model.addAttribute("konser", konser);
        return "konser/view-konser";
    }

    @GetMapping("konser/ubah/{idTiket}")
    public String ubahKonserFormPage(@PathVariable(value = "idTiket") Long idTiket, Model model) {
        KonserModel konser = konserService.getKonserByIdKonser(idTiket);
        List<IdolModel> listIdol = idolService.getListIdol();

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);

        return "konser/form-ubah-konser";
    }

    @PostMapping(value="konser/ubah", params = {"save"})
    public String ubahKonserSubmitPage(@ModelAttribute KonserModel konser, Model model) {
        if (konser.getListTampil() == null) {
            konser.setListTampil(new ArrayList<>());
        }

        List<PenampilanKonserModel> listPenampilan = konser.getListTampil();
        for (PenampilanKonserModel penampilan : listPenampilan) {
            penampilan.setKonser(konser);
        }

        KonserModel updatedKonser = konserService.updateKonser(konser);
        model.addAttribute("idKonser", updatedKonser.getIdKonser());

        return "konser/ubah-konser";
    }

    @PostMapping(value="/konser/tambah", params = {"addRow"})
    private String addRow(@ModelAttribute KonserModel konser, Model model) {
        if(konser.getListTampil() == null || konser.getListTampil().size() == 0) {
            konser.setListTampil(new ArrayList<>());
        }

        konser.getListTampil().add(new PenampilanKonserModel());
        List<IdolModel> listIdol = idolService.getListIdol();

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);

        return "konser/form-add-konser";
    }

    @PostMapping(value = "/konser/tambah", params = {"deleteRow"})
    private String deleteRow(@ModelAttribute KonserModel konser, @RequestParam("deleteRow") Integer row, Model model) {
        final Integer rowId = Integer.valueOf(row);
        konser.getListTampil().remove(rowId.intValue());
        List<IdolModel> listIdol = idolService.getListIdol();
        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);
        return "konser/form-add-konser";
    }

    @PostMapping(value="/konser/ubah", params = {"addRowIdolUpdate"})
    private String addRowIdolUpdate(@ModelAttribute KonserModel konser, Model model) {
        if(konser.getListTampil() == null || konser.getListTampil().size() == 0) {
            konser.setListTampil(new ArrayList<>());
        }

        konser.getListTampil().add(new PenampilanKonserModel());
        List<IdolModel> listIdol = idolService.getListIdol();

        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);

        return "konser/form-ubah-konser";
    }

    @PostMapping(value = "/konser/ubah", params = {"deleteRowIdolUpdate"})
    private String deleteRowIdolUpdate(@ModelAttribute KonserModel konser, @RequestParam("deleteRowIdolUpdate") Integer row, Model model) {
        if(konser.getListTampil() == null || konser.getListTampil().size() == 0) {
            konser.setListTampil(new ArrayList<>());
        }

        final Integer rowId = Integer.valueOf(row);

        PenampilanKonserModel penampilanDelete = konser.getListTampil().remove(rowId.intValue());
        penampilanKonserService.penampilanKonserDelete(penampilanDelete);

        List<IdolModel> listIdol = idolService.getListIdol();
        model.addAttribute("konser", konser);
        model.addAttribute("listIdolExisting", listIdol);
        return "konser/form-ubah-konser";
    }

    @GetMapping(value = "/carikonser")
    private String cariKonser(Model model) {
        List<IdolModel> listIdol = idolService.getListIdol();
        List<KonserModel> listCari = new ArrayList<>();
        model.addAttribute("listIdolExisting", listIdol);
        model.addAttribute("listCari", listCari);
        return "konser/cari-konser";
    }

    @GetMapping(value = "/carikonser", params = "search")
    public String cariKonserPage(@RequestParam(value = "idIdol") String idIdol, @RequestParam(value = "minimalPendapatan") Long minimalPendapatan, Model model) {
        List<IdolModel> listIdol = idolService.getListIdol();
        IdolModel idol = idolService.getIdolByIdIdol(Long.parseLong(idIdol));
        List<KonserModel> listCari = konserService.cariKonser(idol, minimalPendapatan);
        model.addAttribute("listIdolExisting", listIdol);
        model.addAttribute("listCari", listCari);

        return "konser/cari-konser";
    }
}
