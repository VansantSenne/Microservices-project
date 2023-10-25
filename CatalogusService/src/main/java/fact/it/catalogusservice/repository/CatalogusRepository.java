package fact.it.catalogusservice.repository;


import fact.it.catalogusservice.model.Catalogus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CatalogusRepository {
    List<Catalogus> findByCodeIn(List<String> code);
}
