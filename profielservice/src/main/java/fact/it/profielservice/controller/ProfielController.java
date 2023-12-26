package fact.it.profielservice.controller;

import fact.it.profielservice.dto.ProfielResponse;
import fact.it.profielservice.model.Profiel;
import fact.it.profielservice.service.ProfielService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiel")
@RequiredArgsConstructor
public class ProfielController {

    private final ProfielService profielService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Profiel> getAllProfielen() {
        return profielService.getAllProfielen();
    }

}
