package apap.tugasindividu.singidol.controller;

import apap.tugasindividu.singidol.model.KonserModel;
import apap.tugasindividu.singidol.model.TiketModel;
import apap.tugasindividu.singidol.model.TipeModel;
import apap.tugasindividu.singidol.service.KonserService;
import apap.tugasindividu.singidol.service.TiketService;
import apap.tugasindividu.singidol.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TiketController {

    @Qualifier("tiketServiceImpl")
    @Autowired
    private TiketService tiketService;

    @Qualifier("konserServiceImpl")
    @Autowired
    private KonserService konserService;

    @Qualifier("tipeServiceImpl")
    @Autowired
    private TipeService tipeService;

    @GetMapping("/tiket")
    public String listTiket(Model model) {
        List<TiketModel> listTiket = tiketService.getListTiket();
        model.addAttribute("listTiket", listTiket);
        return "tiket/viewall-tiket";
    }

    @GetMapping("/tiket/{idTiket}")
    public String viewDetailTiketPage(@PathVariable(value = "idTiket") Long idTiket, Model model) {
        TiketModel tiket = tiketService.getTiketByIdTiket(idTiket);

        model.addAttribute("tiket", tiket);
        return "tiket/view-tiket";
    }

    @GetMapping("/tiket/pesan")
    public String pesanTiketFormPage(Model model) {
        TiketModel tiket = new TiketModel();
        List<KonserModel> listKonser = konserService.getListKonser();
        List<TipeModel> listTipe = tipeService.getListTiket();

        model.addAttribute("tiket", tiket);
        model.addAttribute("listKonser", listKonser);
        model.addAttribute("listTipe", listTipe);

        return "tiket/form-pesan-tiket";
    }

    @PostMapping("/tiket/pesan")
    public String pesanTiketSubmitPage(@ModelAttribute TiketModel tiket, Model model) {
        tiket.setTanggalPembelian(LocalDateTime.now());
        KonserModel konser = konserService.getKonserByIdKonser(tiket.getKonser().getIdKonser());
        TipeModel tipe = tipeService.getTipeByIdTipe(tiket.getTipe().getIdTipe());
        tiket.setNomorTiket(tiketService.generateNoTiket(tiket, konser, tipe));

        konser.setTotalPendapatan(konser.getTotalPendapatan() + tipe.getHarga());
        tiketService.pesanTiket(tiket);
        model.addAttribute("tiket", tiket);

        return "tiket/pesan-tiket";
    }

    @GetMapping("/tiket/hapus/{idTiket}")
    public String deleteTiket(@PathVariable(value = "idTiket") String idTiket, Model model) {
        TiketModel tiket = tiketService.getTiketByIdTiket(Long.parseLong(idTiket));

        KonserModel konser = tiket.getKonser();
        konser.setTotalPendapatan(konser.getTotalPendapatan() - tiket.getTipe().getHarga());

        tiketService.deleteTiket(tiket);
        model.addAttribute("tiket", tiket);
        model.addAttribute("konser", konser);
        return "tiket/delete-tiket";
    }

}
