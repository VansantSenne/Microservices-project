package fact.it.boekingservice.repository;

import fact.it.boekingservice.model.Boeking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoekingRepository extends JpaRepository<Boeking, Long> {
    List<Boeking> findByProfielId(Long profielId);
    Boeking findByBoekingNummer(String boekingNummer);
}
