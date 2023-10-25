package fact.it.catalogusservice.service;
import fact.it.catalogusservice.repository.CatalogusRepository;
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
            stockItem1.setSkuCode("beam10ft");
            stockItem1.setQuantity(0);

            inventoryRepository.save(stockItem);
            inventoryRepository.save(stockItem1);
        }
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {

        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(stockItem ->
                        InventoryResponse.builder()
                                .skuCode(stockItem.getSkuCode())
                                .isInStock(stockItem.getQuantity() > 0)
                                .build()
                ).toList();
    }
}
