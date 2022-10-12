package apap.tugasindividu.singidol.service;

import apap.tugasindividu.singidol.model.KonserModel;
import apap.tugasindividu.singidol.model.TiketModel;
import apap.tugasindividu.singidol.model.TipeModel;

import java.util.List;

public interface TiketService {

    List<TiketModel> getListTiket();
    TiketModel getTiketByIdTiket(Long id);
    void pesanTiket(TiketModel tiket);
    String generateNoTiket(TiketModel tiket, KonserModel konser, TipeModel tipe);
    void deleteTiket(TiketModel tiket);

}
