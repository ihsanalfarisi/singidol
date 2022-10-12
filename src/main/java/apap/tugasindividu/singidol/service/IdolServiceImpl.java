package apap.tugasindividu.singidol.service;

import apap.tugasindividu.singidol.model.IdolModel;

import apap.tugasindividu.singidol.repository.IdolDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IdolServiceImpl implements IdolService {

    @Autowired
    IdolDb idolDb;

    @Override
    public List<IdolModel> getListIdol() { return idolDb.findAll(); }

    @Override
    public void addIdol(IdolModel idol) { idolDb.save(idol); }

    @Override
    public IdolModel getIdolByIdIdol(Long id) {
        Optional<IdolModel> idol = idolDb.findByIdIdol(id);
        return idol.orElse(null);
    }
}
