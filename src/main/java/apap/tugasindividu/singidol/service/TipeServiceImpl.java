package apap.tugasindividu.singidol.service;

import apap.tugasindividu.singidol.model.TipeModel;
import apap.tugasindividu.singidol.repository.TipeDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipeServiceImpl implements TipeService{

    @Autowired
    TipeDb tipeDb;

    @Override
    public List<TipeModel> getListTiket() { return tipeDb.findAll(); }

    public TipeModel getTipeByIdTipe(Long id) {
        Optional<TipeModel> tipe = tipeDb.findByidTipe(id);
        return tipe.orElse(null);
    }
}
