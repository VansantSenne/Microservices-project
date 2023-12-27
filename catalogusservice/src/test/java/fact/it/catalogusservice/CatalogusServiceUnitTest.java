package fact.it.catalogusservice;
import fact.it.catalogusservice.dto.CatalogusResponse;
import fact.it.catalogusservice.model.Catalogus;
import fact.it.catalogusservice.repository.CatalogusRepository;
import fact.it.catalogusservice.service.CatalogusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CatalogusServiceUnitTest {

    @Mock
    private CatalogusRepository catalogusRepository;

    @InjectMocks
    private CatalogusService catalogusService;


    @Test
    public void testAvailable() {
        List<String> vluchtNummers = Arrays.asList("tube6in", "beam10ft");
        Catalogus catalogus1 = new Catalogus();
        catalogus1.setId(1L);
        catalogus1.setVluchtNummer("tube6in");
        catalogus1.setPrijs(BigDecimal.valueOf(400));
        catalogus1.setVolgeboekt(false);

        Catalogus catalogus2 = new Catalogus();
        catalogus2.setId(2L);
        catalogus2.setVluchtNummer("beam10ft");
        catalogus2.setPrijs(BigDecimal.valueOf(525.99));
        catalogus2.setVolgeboekt(false);

        List<Catalogus> catalogus = Arrays.asList(catalogus1,catalogus2);

        when(catalogusRepository.findByVluchtNummerIn(vluchtNummers)).thenReturn(catalogus);

        List<CatalogusResponse> catalogusResponses = catalogusService.available(vluchtNummers);

        assertEquals(2, catalogusResponses.size());
        verify(catalogusRepository, times(1)).findByVluchtNummerIn(vluchtNummers);
    }

    @Test
    public void testGetAllFlights() {
        Catalogus catalogus1 = new Catalogus();
        catalogus1.setId(1L);
        catalogus1.setVluchtNummer("tube6in");
        catalogus1.setPrijs(BigDecimal.valueOf(400));
        catalogus1.setVolgeboekt(false);

        when(catalogusRepository.findAll()).thenReturn(Arrays.asList(catalogus1));

        List<CatalogusResponse> catalogus = catalogusService.getAllFlights();

        assertEquals(1, catalogus.size());
        verify(catalogusRepository, times(1)).findAll();
    }
}
