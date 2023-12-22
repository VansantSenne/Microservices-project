package fact.it.profielservice.repository;

import fact.it.profielservice.model.Profiel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Repository
@Transactional
public interface ProfielRepository extends  JpaRepository<Profiel, Long>{

    List<Profiel> findByCodeIn(List<String> code);
}
