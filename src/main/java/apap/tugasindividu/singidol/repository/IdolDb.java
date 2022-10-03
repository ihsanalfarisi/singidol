package apap.tugasindividu.singidol.repository;

import apap.tugasindividu.singidol.model.IdolModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdolDb extends JpaRepository<IdolModel, String>{
    Optional<IdolModel> findByidIdol(Long idIdol);

    @Query("SELECT c FROM IdolModel c WHERE c.idIdol = :idIdol")
    Optional<IdolModel> findByidIdolUsingQuery(@Param("idIdol") Long idIdol);
}
