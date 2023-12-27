package fact.it.boekingservice;



import fact.it.boekingservice.dto.BoekingLineOrderDto;
import fact.it.boekingservice.dto.BoekingResponse;
import fact.it.boekingservice.dto.BoekingRequest;

import fact.it.boekingservice.model.Boeking;
import fact.it.boekingservice.model.BoekingLineOrder;
import fact.it.boekingservice.repository.BoekingRepository;
import fact.it.boekingservice.service.BoekingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoekingserviceUnitTest {


    @InjectMocks
    private BoekingService boekingService;

    @Mock
    private ProductRepository productRepository;
    @Test
    public void testMaakBoeking() {
        // Arrange
        BoekingRequest boekingRequest = new BoekingRequest();
        boekingRequest.setProfielId(1L);
        boekingRequest.getBoekingLineOrderDtoList().add(new BoekingLineOrderDto("KL1234", 2));
        boekingRequest.getBoekingLineOrderDtoList().add(new BoekingLineOrderDto("BE5678", 1));

        // Act
        boolean isBoekingSuccesvol = boekingService.maakBoeking(boekingRequest);

        // Assert
        assertThat(isBoekingSuccesvol).isTrue();
    }
    @Test
    public void testPutBoekingLineOrder() {
        // Arrange
        Boeking boeking = new Boeking();
        boeking.setBoekingNummer("123456");
        boeking.getBoekingLineOrdersList().add(new BoekingLineOrder("KL1234", 2));

        BoekingLineOrderDto boekingLineOrderDto = new BoekingLineOrderDto();
        boekingLineOrderDto.setVluchtNummer("KL1234");
        boekingLineOrderDto.setPrijs(500);
        boekingLineOrderDto.setHoeveelheid(1);

        // Act
        boolean isBoekingLineOrderSuccesvol = boekingService.putBoekingLineOrder("123456", boekingLineOrderDto);

        // Assert
        assertThat(isBoekingLineOrderSuccesvol).isTrue();

        // Controleer dat de boekingsregel is bijgewerkt
        Boeking updatedBoeking = boekingRepository.findByBoekingNummer("123456");
        BoekingLineOrder updatedBoekingLineOrder = updatedBoeking.getBoekingLineOrdersList().stream()
                .filter(lineOrder -> lineOrder.getVluchtNummer().equals("KL1234"))
                .findFirst()
                .orElse(null);

        assertThat(updatedBoekingLineOrder.getPrijs()).isEqualTo(500);
        assertThat(updatedBoekingLineOrder.getHoeveelheid()).isEqualTo(1);
    }
    @Test
    public void testDeleteBoeking() {
        // Arrange
        Boeking boeking = new Boeking();
        boeking.setBoekingNummer("123456");

        // Act
        boolean isBoekingSuccesvol = boekingService.deleteBoeking("123456");

        // Assert
        assertThat(isBoekingSuccesvol).isTrue();

        // Controleer dat de boeking is verwijderd
        assertThat(boekingRepository.findByBoekingNummer("123456")).isNull();
    }


}
