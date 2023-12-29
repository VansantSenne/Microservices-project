package fact.it.beoordelingservice.service;


import fact.it.beoordelingservice.dto.BeoordelingsResponse;
import fact.it.beoordelingservice.model.Beoordeling;
import fact.it.beoordelingservice.repository.BeoordelingsRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeoordelingsService {

    private final BeoordelingsRepository beoordelingsRepository;

    @PostConstruct
    public void loadData() {
        if(beoordelingsRepository.count() <= 0){
            Beoordeling beoordeling = new Beoordeling();
            beoordeling.setProfielId(Long.valueOf(3));
            beoordeling.setVluchtNummer("ABC123");
            beoordeling.setSter(5);
            beoordeling.setBeschrijving("mooi");


   


            beoordelingsRepository.save(beoordeling);
        }
    }

    public List<BeoordelingsResponse> getAllBeoordelingen() {
        return beoordelingsRepository.findAll().stream()
                .map(beoordeling ->
                        BeoordelingsResponse.builder()
                                .profielId(beoordeling.getProfielId())
                                .vluchtNummer(beoordeling.getVluchtNummer())
                                .ster(beoordeling.getSter())
                                .beschrijving(beoordeling.getBeschrijving())
                                .build()
                ).collect(Collectors.toList());
    }


}
