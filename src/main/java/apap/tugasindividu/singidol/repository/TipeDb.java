package apap.tugasindividu.singidol.repository;

import apap.tugasindividu.singidol.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipeDb extends JpaRepository<TipeModel, Long>{
    Optional<TipeModel> findByidTipe(Long idIdol);

    @Query("SELECT c FROM TipeModel c WHERE c.idTipe = :idTipe")
    Optional<TipeModel> findByidTipeUsingQuery(@Param("idTipe") Long idTipe);
}
