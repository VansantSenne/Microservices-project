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

    @PostConstruct
    public void loadData() {
        if(BeoordelingsRepository.count() <= 0){
            Beoordeling beoordeling = new Beoordeling();
            beoordeling.setProfielId(3);
            beoordeling.setVluchtNummer("ABC123")
            beoordeling.setSter(5);
            beoordeling.setBeschrijving("mooi")


   


            beoordelingsRepository.save(beoordeling);
        }
    }

    public List<BeoordelingResponse> getAllBeoordelingen() {
        return beoordelingsRepository.findAll().stream()
                .map(beoordeling ->
                        BeoordelingResponse.builder()
                                .profielId(beoordeling.GetProfielId())
                                .vluchtNummer(beoordeling.GetVluchtNummer())
                                .ster(beoordeling.GetSter())
                                .beschrijving(beoordeling.GetBeschrijving())
                                .build()
                ).collect(Collectors.toList());
    }


}
