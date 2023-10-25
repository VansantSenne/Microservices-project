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

    public boolean placeBoeking(BoekingRequest boekingRequest) {
       Boeking boeking = new Boeking();
        boeking.setBoekingNummer(UUID.randomUUID().toString());

        List<BoekingLineOrder> boekingLineOrders = boekingRequest.getBoekingLineOrdersDtoList()
                .stream()
                .map(this::mapToOrderLineItem)
                .toList();

        boeking.setBoekingLineOrdersList(boekingLineOrders);

        List<String> codes = boeking.getBoekingLineOrdersList().stream()
                .map(BoekingLineOrder::getCode)
                .toList();

        CatalogusResponse[] inventoryResponseArray = webClient.get()
                .uri("http://localhost:8082/api/catalogus",
                        uriBuilder -> uriBuilder.queryParam("code", codes).build())
                .retrieve()
                .bodyToMono(CatalogusResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(catalogusResponseArray)
                .allMatch(CatalogusResponse::available);

        if(allProductsInStock){
            ProfielResponse[] productResponseArray = webClient.get()
                    .uri("http://localhost:8080/api/profiel",
                            uriBuilder -> uriBuilder.queryParam("code", codes).build())
                    .retrieve()
                    .bodyToMono(ProfielResponse[].class)
                    .block();

            boeking.getBoekingLineOrdersList().stream()
                    .map(boekingItem -> {
                        ProfielResponse product = Arrays.stream(profielResponseArray)
                                .filter(p -> p.getCode().equals(boekingItem.getCode()))
                                .findFirst()
                                .orElse(null);
                        if (product != null) {
                            boekingItem.setPrijs(product.getPrijs());
                        }
                        return boekingItem;
                    })
                    .collect(Collectors.toList());

            boekingRepository.save(boeking);
            return true;
        } else {
            return false;
        }
    }

    public List<BoekingResponse> getAllBoekingen() {
        List<Boeking> boekingen = boekingRepository.findAll();

        return boekingen.stream()
                .map(boeking -> new BoekingResponse(
                        boeking.getBoekingNummer(),
                        mapToBoekingLineOrdersDto(boeking.getBoekingLineOrdersList())
                ))
                .collect(Collectors.toList());
    }

    private BoekingLineOrder mapToOrderLineItem(BoekingLineOrderDto boekingLineOrderDto) {
        BoekingLineOrder boekingLineOrder = new BoekingLineOrder();
        boekingLineOrder.setPrijs(boekingLineOrderDto.getPrijs());
        boekingLineOrder.setHoeveelheid(boekingLineOrderDto.getHoeveelheid());
        boekingLineOrder.setCode(boekingLineOrderDto.getCode());
        return boekingLineOrder;
    }

    private List<BoekingLineOrderDto> mapToBoekingLineOrdersDto(List<BoekingLineOrder> boekingLineOrders) {
        return boekingLineOrders.stream()
                .map(boekingLineOrder -> new BoekingLineOrderDto(
                        boekingLineOrder.getId(),
                        boekingLineOrder.getCode(),
                        boekingLineOrder.getPrijs(),
                        boekingLineOrder.getHoeveelheid()
                ))
                .collect(Collectors.toList());
    }



}
