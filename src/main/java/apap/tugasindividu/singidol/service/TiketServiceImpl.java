package apap.tugasindividu.singidol.service;

import apap.tugasindividu.singidol.model.KonserModel;
import apap.tugasindividu.singidol.model.TiketModel;
import apap.tugasindividu.singidol.model.TipeModel;
import apap.tugasindividu.singidol.repository.TiketDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class TiketServiceImpl implements TiketService{

    @Autowired
    TiketDb tiketDb;

    @Override
    public List<TiketModel> getListTiket() { return tiketDb.findAll(); }

    public TiketModel getTiketByIdTiket(Long id) {
        Optional<TiketModel> tiket = tiketDb.findByidTiket(id);
        return tiket.orElse(null);
    }

    @Override
    public void pesanTiket(TiketModel tiket) { tiketDb.save(tiket); }

    @Override
    public String generateNoTiket(TiketModel tiket, KonserModel konser, TipeModel tipe) {
        String no;

        // 3 huruf pertama
        String firstPartial = tiket.getNamaLengkap().split(" ")[0].substring(0,3).toUpperCase();
        // 4 karakter tanggal
        String lahir = String.valueOf(tiket.getTanggalLahir().getDayOfMonth()) +
                String.valueOf(tiket.getTanggalLahir().getMonthValue());
        String beli = String.valueOf(tiket.getTanggalPembelian().getDayOfMonth()) +
                String.valueOf(tiket.getTanggalPembelian().getMonthValue());
        int secPartialInt = Integer.parseInt(lahir) + Integer.parseInt(beli);
        String secPartial;
        if (secPartialInt < 1000) secPartial = "0" + String.valueOf(secPartialInt);
        else secPartial = String.valueOf(secPartialInt);
        // 2 karakter nama konser
        String firstKonser = konser.getNamaKonser().split(" ")[0].substring(0,1).toLowerCase();
        String thirdPartial = String.valueOf(firstKonser.charAt(0) - 'a' + 1);
        if (thirdPartial.length() == 1) thirdPartial = "0" + thirdPartial;
        // 3 karakter jenis tiket
        String namaTipe = tipe.getNama();
        String fourthPartial;
        switch (namaTipe) {
            case "vip":
                fourthPartial = "VIP";
                break;
            case "platinum":
                fourthPartial = "PLT";
                break;
            case "gold":
                fourthPartial = "GLD";
                break;
            default:
                fourthPartial = "SLV";
                break;
        }
        // 1 karakter random
        Random rd = new Random();
        String fifthPartial = String.valueOf((char) (rd.nextInt(26) + 'a')).toUpperCase();

        no = firstPartial + secPartial + thirdPartial + fourthPartial + fifthPartial;

        return no;
    }

    @Override
    public void deleteTiket(TiketModel tiket) {
        tiketDb.delete(tiket);
    }

}
