package fact.it.beoordelingservice.service;


import fact.it.beoordelingservice.dto.BeoordelingsResponse;
import fact.it.beoordelingservice.model.Beoordeling;
import fact.it.beoordelingservice.repository.BeoordelingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BeoordelingsService {

    private final BeoordelingsRepository beoordelingsRepository;


}
