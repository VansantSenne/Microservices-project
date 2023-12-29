package fact.it.boekingservice;
import fact.it.boekingservice.dto.*;
import fact.it.boekingservice.model.Boeking;
import fact.it.boekingservice.model.BoekingLineOrder;
import fact.it.boekingservice.repository.BoekingRepository;
import fact.it.boekingservice.service.BoekingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoekingServiceUnitTest {

    @InjectMocks
    private BoekingService boekingService;

    @Mock
    private BoekingRepository boekingRepository;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(boekingService, "catalogusServiceBaseUrl", "http://localhost:8082");
    }

    @Test
    void testMaakBoeking_FlightAvailable() {
        // Arrange
        BoekingRequest boekingRequest = createSampleBoekingRequest();

        // Mocking behavior of BoekingRepository
        when(boekingRepository.save(any(Boeking.class))).thenReturn(new Boeking());

        // Mocking behavior of WebClient
        CatalogusResponse[] catalogusResponseArray = {
                new CatalogusResponse("1234", BigDecimal.valueOf(500), false)
        };

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono((Class<Object>) any())).thenReturn(Mono.just(catalogusResponseArray));

        // Act
        boolean result = boekingService.maakBoeking(boekingRequest);

        // Assert
        assertTrue(result);
        verify(boekingRepository, times(1)).save(any(Boeking.class));
    }

    @Test
    void testMaakBoeking_FlightNotAvailable() {
        // Arrange
        BoekingRequest boekingRequest = createSampleBoekingRequest();

        // Mocking behavior of WebClient
        CatalogusResponse[] catalogusResponseArray = {
                new CatalogusResponse("1234", BigDecimal.valueOf(500), true)
        };

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono((Class<Object>) any())).thenReturn(Mono.just(catalogusResponseArray));

        // Act
        boolean result = boekingService.maakBoeking(boekingRequest);

        // Assert
        assertFalse(result);
        verify(boekingRepository, never()).save(any(Boeking.class));
    }




    @Test
    void putBoekingLineOrder_Successful() {
        Boeking boeking = createSampleBoeking();
        BoekingLineOrderDto boekingLineOrderDto = createSampleBoekingLineOrderDto();

        when(boekingRepository.findByBoekingNummer(any()))
                .thenReturn(boeking);

        assertTrue(boekingService.putBoekingLineOrder(boeking.getBoekingNummer(), boekingLineOrderDto));
    }

    @Test
    void deleteBoeking_Successful() {
        Boeking boeking = createSampleBoeking();
        when(boekingRepository.findByBoekingNummer(any()))
                .thenReturn(boeking);

        assertTrue(boekingService.deleteBoeking(boeking.getBoekingNummer()));
    }

    @Test
    void getBoekingen_Successful() {
        Long profielId = 1L;
        List<Boeking> boekingen = Arrays.asList(createSampleBoeking());

        when(boekingRepository.findByProfielId(eq(profielId)))
                .thenReturn(boekingen);

        List<BoekingResponse> result = boekingService.getBoekingen(profielId);
        assertFalse(result.isEmpty());
    }

    // Helper methods to create sample objects for testing

    private BoekingRequest createSampleBoekingRequest() {
        BoekingLineOrderDto boekingLineOrderDto = createSampleBoekingLineOrderDto();
        return new BoekingRequest(1L, Arrays.asList(boekingLineOrderDto));
    }

    private BoekingLineOrderDto createSampleBoekingLineOrderDto() {
        return new BoekingLineOrderDto(1L, "ABC123", BigDecimal.valueOf(500.00), 2);
    }

    private Boeking createSampleBoeking() {
        Boeking boeking = new Boeking();
        boeking.setBoekingNummer(UUID.randomUUID().toString());
        boeking.setProfielId(1L);

        BoekingLineOrder boekingLineOrder = new BoekingLineOrder();
        boekingLineOrder.setId(Long.valueOf(1));
        boekingLineOrder.setVluchtNummer("ABC123");
        boekingLineOrder.setPrijs(BigDecimal.valueOf(500.00));
        boekingLineOrder.setHoeveelheid(2);

        boeking.setBoekingLineOrdersList(Arrays.asList(boekingLineOrder));
        return boeking;
    }
}
