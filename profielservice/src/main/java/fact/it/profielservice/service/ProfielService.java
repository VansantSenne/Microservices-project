package fact.it.profielservice.service;

import fact.it.profielservice.dto.ProfielResponse;
import fact.it.profielservice.model.Profiel;
import fact.it.profielservice.repository.ProfielRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfielService {

    private final ProfielRepository profielRepository;

    @PostConstruct
    public void loadData() {
        if(profielRepository.count() <= 0){
            Profiel profiel = new Profiel();
            profiel.setActief(true);


            Profiel profiel1 = new Profiel();
            profiel1.setActief(true);


            profielRepository.save(profiel);
            profielRepository.save(profiel1);
        }
    }

    public List<ProfielResponse> getAllProfielen() {
        return profielRepository.findAll().stream()
                .map(profiel ->
                        ProfielResponse.builder()
                                .actief(profiel.isActief())
                                .build()
                ).collect(Collectors.toList());
    }
}
