package fact.it.boekingservice.controller;
import fact.it.boekingservice.dto.BoekingLineOrderDto;
import fact.it.boekingservice.dto.BoekingResponse;
import fact.it.boekingservice.dto.BoekingRequest;
import fact.it.boekingservice.model.Boeking;
import fact.it.boekingservice.service.BoekingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        boolean result = boekingService.maakBoeking(boekingRequest);
        return (result ? "Booking placed successfully" : "Order placement failed");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BoekingResponse> getBoekingen(@RequestParam(value = "profielId", required = false) Long profielId) {
        return boekingService.getBoekingen(profielId);

    }
    @PutMapping("/{boekingNummer}")
    public ResponseEntity<String> updateBoekingLineOrder(
            @PathVariable String boekingNummer,
            @RequestBody BoekingLineOrderDto boekingLineOrderDto
    ) {
        boolean success = boekingService.putBoekingLineOrder(boekingNummer, boekingLineOrderDto);
    
        if (success) {
            return ResponseEntity.ok("BoekingLineOrder successfully updated.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Boeking or BoekingLineOrder not found.");
        }
    }

    @DeleteMapping("/{boekingNummer}")
    public ResponseEntity<String> deleteBoeking(@PathVariable String boekingNummer) {
        boolean success = boekingService.deleteBoeking(boekingNummer);

        if (success) {
            return ResponseEntity.ok("Boeking successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Boeking not found.");
        }
    }
}
