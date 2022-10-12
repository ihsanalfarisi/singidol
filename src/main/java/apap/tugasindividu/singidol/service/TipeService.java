package apap.tugasindividu.singidol.service;

import apap.tugasindividu.singidol.model.TipeModel;

import java.util.List;

public interface TipeService {

    List<TipeModel> getListTiket();
    TipeModel getTipeByIdTipe(Long id);
}
