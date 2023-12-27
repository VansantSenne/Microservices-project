package fact.it.catalogusservice.service;
import fact.it.catalogusservice.dto.CatalogusResponse;
import fact.it.catalogusservice.model.Catalogus;
import fact.it.catalogusservice.repository.CatalogusRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogusService {
    private final CatalogusRepository catalogusRepository;

    @PostConstruct
    public void loadData() {
        if(catalogusRepository.count() <= 0){
            Catalogus catalogus = new Catalogus();
            catalogus.setVluchtNummer("tube6in");
            catalogus.setPrijs(BigDecimal.valueOf(400));
            catalogus.isVolgeboekt();

            Catalogus catalogus1 = new Catalogus();
            catalogus1.setVluchtNummer("beam10ft");
            catalogus1.setPrijs(BigDecimal.valueOf(525.99));
            catalogus1.isVolgeboekt();

            catalogusRepository.save(catalogus);
            catalogusRepository.save(catalogus1);
        }
    }

    @Transactional(readOnly = true)
    public List<CatalogusResponse> available(List<String> vluchtNummer) {
        List<Catalogus> catalogusList = catalogusRepository.findByVluchtNummerIn(vluchtNummer);

        return catalogusList.stream()
                .map(catalogus ->
                        CatalogusResponse.builder()
                                .vluchtNummer(catalogus.getVluchtNummer())
                                .prijs(catalogus.getPrijs())
                                .isVolgeboekt(catalogus.isVolgeboekt())
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CatalogusResponse> getAllFlights() {
        return catalogusRepository.findAll().stream()
                .map(catalogus ->
                        CatalogusResponse.builder()
                                .vluchtNummer(catalogus.getVluchtNummer())
                                .prijs(catalogus.getPrijs())
                                .isVolgeboekt(catalogus.isVolgeboekt())
                                .build()
                ).collect(Collectors.toList());
    }
}
