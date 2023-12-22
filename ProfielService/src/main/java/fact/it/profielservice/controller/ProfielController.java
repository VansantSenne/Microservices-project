package fact.it.profielservice.controller;

import fact.it.profielservice.dto.ProfielResponse;
import fact.it.profielservice.service.ProfielService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiel")
@RequiredArgsConstructor
public class ProfielController {

    private final ProfielService profielService;

    // http://localhost:8082/api/inventory?skuCode=tube6in&skuCode=beam10ft
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProfielResponse> actief
    (@RequestParam List<String> code) {
        return profielService.actief(code);
    }
}
