package fact.it.profielservice.service;

import fact.it.profielservice.dto.ProfielResponse;
import fact.it.profielservice.model.Profiel;
import fact.it.profielservice.repository.ProfielRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProfielService {

    private final ProfielRepository profielRepository;

    @PostConstruct
    public void loadData() {
        if(profielRepository.count() > 0){
            Profiel profiel = new Profiel();
            profiel.setCode("tube6in");
            profiel.setName("plop");

            Profiel profiel1 = new Profiel();
            profiel1.setCode("beam10ft");
            profiel1.setName("kwebbel");

            profielRepository.save(profiel);
            profielRepository.save(profiel1);
        }
    }

    @Transactional(readOnly = true)
    public List<ProfielResponse> actief(List<String> code) {

        return profielRepository.findByCodeIn(code).stream()
                .map(profiel ->
                        ProfielResponse.builder()
                                .code(profiel.getCode())
                                .active(!Objects.equals(profiel.getName(), ""))
                                .build()
                ).toList();
    }
}
