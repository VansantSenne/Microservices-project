package fact.it.boekingservice.controller;
import fact.it.boekingservice.dto.BoekingResponse;
import fact.it.boekingservice.dto.BoekingRequest;
import fact.it.boekingservice.model.Boeking;
import fact.it.boekingservice.service.BoekingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boeking")
@RequiredArgsConstructor
public class BoekingController {
    private final BoekingService boekingService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String placeOrder(@RequestBody BoekingRequest boekingRequest) {
        boolean result = boekingService.placeBoeking(boekingRequest);
        return (result ? "Booking placed successfully" : "Order placement failed");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BoekingResponse> getAllBoekingen() {
        return boekingService.getAllBoekingen();
    }
}
