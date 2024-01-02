package fact.it.beoordelingservice.controller;

import fact.it.beoordelingservice.dto.BeoordelingsResponse;
import fact.it.beoordelingservice.model.Beoordeling;
import fact.it.beoordelingservice.service.BeoordelingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/beoordeling")
@RequiredArgsConstructor
public class BeoordelingsController {

    private final BeoordelingsService beoordelingsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BeoordelingsResponse> getAllBeoordelingen() {
        return beoordelingsService.getAllBeoordelingen();
    }
}

