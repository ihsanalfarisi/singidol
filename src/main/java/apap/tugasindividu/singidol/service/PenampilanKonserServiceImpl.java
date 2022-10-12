package apap.tugasindividu.singidol.service;

import apap.tugasindividu.singidol.model.PenampilanKonserModel;
import apap.tugasindividu.singidol.repository.PenampilanKonserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class PenampilanKonserServiceImpl implements PenampilanKonserService {

    @Autowired
    PenampilanKonserDb penampilanKonserDb;

    @Override
    public void penampilanKonserDelete(PenampilanKonserModel penampilanKonser) {
        penampilanKonserDb.delete(penampilanKonser);
    }

}
