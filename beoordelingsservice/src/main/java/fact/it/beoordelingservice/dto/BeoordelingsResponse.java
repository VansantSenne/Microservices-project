package fact.it.beoordelingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeoordelingsResponse {
    private Long id;
    private String vluchtNummer;
    private Long profielId;
    private Integer ster;
    private String beschrijving;
}
