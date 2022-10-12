package apap.tugasindividu.singidol.service;

import apap.tugasindividu.singidol.model.IdolModel;
import apap.tugasindividu.singidol.model.KonserModel;

import java.util.List;

public interface KonserService {
    List<KonserModel> getListKonser();
    void addKonser(KonserModel konser);
    KonserModel getKonserByIdKonser(Long id);
    List<KonserModel> cariKonser(IdolModel idol, Long minimalPendapatan);
    KonserModel updateKonser(KonserModel konser);

}
