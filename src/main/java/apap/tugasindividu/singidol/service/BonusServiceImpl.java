package apap.tugasindividu.singidol.service;

import apap.tugasindividu.singidol.model.KonserModel;
import apap.tugasindividu.singidol.model.TiketModel;
import apap.tugasindividu.singidol.repository.KonserDb;
import apap.tugasindividu.singidol.repository.TiketDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BonusServiceImpl implements BonusService {

    @Autowired
    TiketDb tiketDb;

    @Autowired
    KonserDb konserDb;

    @Qualifier("konserServiceImpl")
    @Autowired
    private KonserService konserService;

    @Override
    public Long getTop(String namaTipe) {
        List<TiketModel> listTiket = tiketDb.findAll();
        List<KonserModel> listKonser = konserDb.findAll();
        HashMap<Long, Long> count = new HashMap<>();

        for (KonserModel konser : listKonser) {
            count.put(konser.getIdKonser(), 0L);
            for (TiketModel tiket : listTiket) {
                if (tiket.getTipe().getNama().equals(namaTipe) &&
                        tiket.getKonser().getNamaKonser().equals(konser.getNamaKonser())) {
                    count.put(konser.getIdKonser(), count.get(konser.getIdKonser()) + 1);
                }
            }
        }

        Long top = 0L;
        KonserModel konserTemp = new KonserModel();
        konserTemp.setNamaKonser("~");
        Long buffer = 0L;
        for (Map.Entry<Long, Long> c : count.entrySet()) {
            if (c.getValue() > buffer) {
                top = c.getKey();
                buffer = c.getValue();
                konserTemp = konserService.getKonserByIdKonser(top);
            } else if (c.getValue().equals(buffer) &&
                    konserService.getKonserByIdKonser(c.getKey()).getNamaKonser().split(" ")[0].toLowerCase().charAt(0) <
                    konserTemp.getNamaKonser().split(" ")[0].toLowerCase().charAt(0)) {
                top = c.getKey();
                buffer = c.getValue();
                konserTemp = konserService.getKonserByIdKonser(top);
            }
        }
        if (!count.get(top).equals(0L)) {
            return top;
        }
        else {
            return 0L;
        }
    }
}
