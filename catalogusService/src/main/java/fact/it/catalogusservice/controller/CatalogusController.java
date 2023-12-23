package fact.it.catalogusservice.controller;
import fact.it.catalogusservice.dto.CatalogusResponse;
import fact.it.catalogusservice.service.CatalogusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogus")
@RequiredArgsConstructor
public class CatalogusController {

    private final CatalogusService catalogusService;

    // http://localhost:8082/api/inventory?skuCode=tube6in&skuCode=beam10ft
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogusResponse> available
    (@RequestParam List<String> code) {
        return catalogusService.available(code);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CatalogusResponse> getAllFlights() {
        return catalogusService.getAllFlights();
    }
}
