package fact.it.beoordelingservice.repository;

import fact.it.beoordelingservice.model.Beoordeling;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
public interface BeoordelingsRepository extends MongoRepository<Beoordeling, String> {
    List<Beoordeling> findByCodeIn(List<String> code);
}
