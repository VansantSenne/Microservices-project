package fact.it.boekingservice.service;
import fact.it.boekingservice.dto.*;
import fact.it.boekingservice.model.Boeking;
import fact.it.boekingservice.model.BoekingLineOrder;
import fact.it.boekingservice.repository.BoekingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoekingService {
    private final BoekingRepository boekingRepository;
    private final WebClient webClient;
    public boolean maakBoeking(BoekingRequest boekingRequest) {
        Boeking boeking = new Boeking();
        boeking.setBoekingNummer(UUID.randomUUID().toString());
        boeking.setProfielId(boekingRequest.getProfielId());
        List<BoekingLineOrder> boekingLineItems = boekingRequest.getBoekingLineOrderDtoList().stream()
                .map(this::toBoekingLineOrder)
                .toList();

        boeking.setBoekingLineOrdersList(boekingLineItems);

        List<String> vluchtNummers = boeking.getBoekingLineOrdersList().stream()
                .map(BoekingLineOrder::getVluchtNummer)
                .toList();

        CatalogusResponse[] catalogusResponseArray = webClient.get()
                .uri("http://localhost:8081/api/catalogus",
                        uriBuilder -> uriBuilder.queryParam("vluchtNummer", vluchtNummers).build())
                .retrieve()
                .bodyToMono(CatalogusResponse[].class)
                .block();

        boolean allFlightsAvailable = Arrays.stream(catalogusResponseArray).noneMatch(CatalogusResponse::isVolgeboekt);

        if(allFlightsAvailable){
            for (BoekingLineOrder boekingLineItem: boeking.getBoekingLineOrdersList()) {
                CatalogusResponse catalogusResponse = Arrays.stream(catalogusResponseArray).filter(c -> c.getVluchtNummer().equals(boekingLineItem.getVluchtNummer())).findFirst().orElse(null);
                if (catalogusResponse != null) {
                    boekingLineItem.setPrijs(catalogusResponse.getPrijs());
                }
            }

            boekingRepository.save(boeking);
            return true;
        } else {
            return false;
        }
    }
    public boolean putBoekingLineOrder(String boekingNummer, BoekingLineOrderDto boekingLineOrderDto) {
        Boeking boeking = boekingRepository.findByBoekingNummer(boekingNummer);

        if (boeking != null) {
            // Zoek de boekingsregel die moet worden bijgewerkt
            BoekingLineOrder boekingLineOrder = boeking.getBoekingLineOrdersList().stream()
                    .filter(lineOrder -> lineOrder.getVluchtNummer().equals(boekingLineOrderDto.getVluchtNummer()))
                    .findFirst()
                    .orElse(null);

            if (boekingLineOrder != null) {
                // Werk de boekingsregel bij met de nieuwe informatie
                boekingLineOrder.setPrijs(boekingLineOrderDto.getPrijs());
                boekingLineOrder.setHoeveelheid(boekingLineOrderDto.getHoeveelheid());

                // Andere velden blijven ongewijzigd

                // Opslaan van de bijgewerkte boeking
                boekingRepository.save(boeking);
                return true;
            }
        }

        return false;
    }
    public boolean deleteBoeking(String boekingNummer) {
        Boeking boeking = boekingRepository.findByBoekingNummer(boekingNummer);

        if (boeking != null) {
            // Verwijder de hele boeking
            boekingRepository.delete(boeking);
            return true;
        }

        return false;
    }
    public List<BoekingResponse> getBoekingen(Long profielId) {
        List<Boeking> boekingen = boekingRepository.findByProfielId(profielId);
        return boekingen.stream().map(this::toBoekingResponse).collect(Collectors.toList());
    }

    private BoekingResponse toBoekingResponse(Boeking boeking) {
        BoekingResponse boekingResponse = new BoekingResponse();
        boekingResponse.setBoekingNummer(boeking.getBoekingNummer());
        boekingResponse.setProfielId(boeking.getProfielId());
        boekingResponse.setBoekingLineOrdersList(boeking.getBoekingLineOrdersList().stream().map(this::toBoekingLineOrderDto).collect(Collectors.toList()));
        return boekingResponse;
    }
    private BoekingLineOrder toBoekingLineOrder(BoekingLineOrderDto boekingLineOrderDto){
        BoekingLineOrder boekingLineOrder = new BoekingLineOrder();
        boekingLineOrder.setPrijs(boekingLineOrderDto.getPrijs());
        boekingLineOrder.setHoeveelheid(boekingLineOrderDto.getHoeveelheid());
        boekingLineOrder.setVluchtNummer(boekingLineOrderDto.getVluchtNummer());
        return boekingLineOrder;
    }

    private BoekingLineOrderDto toBoekingLineOrderDto(BoekingLineOrder boekingLineOrder) {
        BoekingLineOrderDto boekingLineOrderDto = new BoekingLineOrderDto();
        boekingLineOrderDto.setVluchtNummer(boekingLineOrder.getVluchtNummer());
        boekingLineOrderDto.setPrijs(boekingLineOrder.getPrijs());
        boekingLineOrderDto.setHoeveelheid(boekingLineOrder.getHoeveelheid());
        return boekingLineOrderDto;
    }


}
