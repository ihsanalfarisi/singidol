package apap.tugasindividu.singidol.service;

import apap.tugasindividu.singidol.model.IdolModel;
import apap.tugasindividu.singidol.model.KonserModel;
import apap.tugasindividu.singidol.model.PenampilanKonserModel;
import apap.tugasindividu.singidol.repository.KonserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KonserServiceImpl implements KonserService {
    @Autowired
    KonserDb konserDb;

    @Override
    public List<KonserModel> getListKonser() { return konserDb.findAll(); }
    @Override
    public void addKonser(KonserModel konser) { konserDb.save(konser); }

    @Override
    public KonserModel getKonserByIdKonser(Long id) {
        Optional<KonserModel> konser = konserDb.findByIdKonser(id);
        return konser.orElse(null);
    }

    @Override
    public List<KonserModel> cariKonser(IdolModel idol, Long minimalPendapatan) {
        List<KonserModel> listKonser = konserDb.findAll();
        List<KonserModel> listCari = new ArrayList<>();

        for (KonserModel konser : listKonser) {
            if (konser.getTotalPendapatan() >= minimalPendapatan) {
                for (PenampilanKonserModel penampilan : konser.getListTampil()) {
                    if (penampilan.getIdol().equals(idol)) {
                        listCari.add(konser);
                        break;
                    }
                }
            }
        }
        return listCari;
    }

    @Override
    public KonserModel updateKonser(KonserModel konser) {
        konserDb.save(konser);
        return konser;
    }
}
