package fact.it.catalogusservice.service;
import fact.it.catalogusservice.dto.CatalogusResponse;
import fact.it.catalogusservice.model.Catalogus;
import fact.it.catalogusservice.repository.CatalogusRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogusService {
    private final CatalogusRepository catalogusRepository;

    @PostConstruct
    public void loadData() {
        if(catalogusRepository.count() > 0){
            Catalogus catalogus = new Catalogus();
            catalogus.setCode("tube6in");
            catalogus.setQuantity(100);

            Catalogus catalogus1 = new Catalogus();
            catalogus1.setCode("beam10ft");
            catalogus1.setQuantity(0);

            catalogusRepository.save(catalogus);
            catalogusRepository.save(catalogus1);
        }
    }

    @Transactional(readOnly = true)
    public List<CatalogusResponse> available(List<String> code) {

        return catalogusRepository.findByCodeIn(code).stream()
                .map(catalogus ->
                        CatalogusResponse.builder()
                                .code(catalogus.getCode())
                                .available(catalogus.getQuantity() > 0)
                                .build()
                ).toList();
    }
}
