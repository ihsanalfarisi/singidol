package apap.tugasindividu.singidol.repository;

import apap.tugasindividu.singidol.model.TiketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TiketDb extends JpaRepository<TiketModel, String>{
    Optional<TiketModel> findByidTiket(Long idIdol);

    @Query("SELECT c FROM TiketModel c WHERE c.idTiket = :idTiket")
    Optional<TiketModel> findByidTiketUsingQuery(@Param("idTiket") Long idTiket);
}
